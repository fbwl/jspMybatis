package shop.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shop.model.dao.ProductDAO;
import shop.model.dto.ProductDTO;
import shop.util.UtilProduct;

@WebServlet("/product_servlet/*")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doProc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UtilProduct util = new UtilProduct();

		int[] nalja = util.getDateTime();
		Map<String, Integer> naljaMap = new HashMap<String, Integer>();
		naljaMap.put("now_y", nalja[0]);
		naljaMap.put("now_m", nalja[1]);
		naljaMap.put("now_d", nalja[2]);
		request.setAttribute("naljaMap", naljaMap);

		String serverInfo[] = util.getServerInfo(request);
		String refer = serverInfo[0];
		String path = serverInfo[1];
		String url = serverInfo[2];
		String uri = serverInfo[3];
		String ip = serverInfo[4];
//		String ip6 = serverInfo[5];

		String temp;
		temp = request.getParameter("pageNumber");
		int pageNumber = util.numberCheck(temp, 1);

		temp = request.getParameter("no");
		int no = util.numberCheck(temp, 0);

		temp = request.getParameter("list_gubun");
		String list_gubun = util.list_gubunCheck(temp);

		String search_option = request.getParameter("search_option");
		String search_data = request.getParameter("search_data");
		String[] searchArray = util.searchCheck(search_option, search_data);
		search_option = searchArray[0];
		search_data = searchArray[1];

		String[] sesstionArray = util.sessionCheck(request);
		int cookNo = Integer.parseInt(sesstionArray[0]);
		String cookId = sesstionArray[1];
		String cookName = sesstionArray[2];

		request.setAttribute("nalja", nalja);
		request.setAttribute("ip", ip);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);

		ProductDTO dto = new ProductDTO();
		ProductDAO dao = new ProductDAO();

		String page = "/main/main.jsp";

		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "product_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("chuga.do") != -1) {
			request.setAttribute("menu_gubun", "product_chuga");
			page = "/shop/product/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("chugaProc.do") != -1 || url.indexOf("modifyProc.do") != -1) {

			String img_path01 = request.getSession().getServletContext().getRealPath("/attach/product_img/");
			java.io.File isDir = new java.io.File(img_path01);
			if (!isDir.isDirectory()) {
				System.out.println("디렉토리 없음");
				isDir.mkdir();
			}
			String img_path02 = img_path01.replace("\\", "/");
			String img_path03 = img_path01.replace("\\", "\\\\");
			int max_size = 10 * 1024 * 1024; // 업로드 10mb 제한
			System.out.println(img_path01);
			MultipartRequest multi = new MultipartRequest(request, img_path03, max_size, "utf-8",
					new DefaultFileRenamePolicy());

			String[] array = new String[3];
			for (int i = 0; i < array.length; i++) {
				array[i] = "-";
			}

			Enumeration files = multi.getFileNames();
			while (files.hasMoreElements()) {
				String formName = (String) files.nextElement();
				String filename = multi.getFilesystemName(formName);
				String fileType = multi.getContentType(formName);
				if (filename == null || filename.trim().equals("")) {
					filename = "-";
				}

//				String fileOrgName = multi.getOriginalFileName(formName);

//				System.out.println("filename : " + filename);
//				System.out.println("formName : " + formName);
//				System.out.println("fileOrgName : " + fileOrgName);
//				System.out.println("fileType : " + fileType);

				int k = Integer.parseInt(formName);
				array[k] = filename;

//				if (formName.equals("0")) {
//					array[0] = filename;
//				} else if (formName.equals("1")) {
//					array[1] = filename;
//				} else if (formName.equals("2")) {
//					array[2] = filename;
//				}
			}

			java.io.File f1;
			for (int i = 0; i < array.length; i++) {
				String filename = array[i];
				if (filename.equals("-")) {
					continue;
				}

				String old_path = img_path03 + filename; // 원본이 업로드된 절대경로와 파일명
				f1 = new java.io.File(old_path);
				if (!f1.exists()) {
					array[i] = "-";
					continue;
				}

				String ext = "";
				int point_index = filename.lastIndexOf(".");
				if (point_index == -1) { // 확장자 표시(.)가 없으면
					f1.delete();
					array[i] = "-";
					continue;
				}

				ext = filename.substring(point_index + 1).toLowerCase();
				if (!ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif")) {
					f1.delete();
					array[i] = "-";
					continue;
				}

				String uuid = util.create_uuid();
				String new_filename = util.getDateTimeType() + "_" + uuid + "." + ext;
				System.out.println(new_filename);

				java.io.File newFile = new java.io.File(img_path03 + new_filename);
				f1.renameTo(newFile); // 파일 이동
				array[i] = array[i] + "|" + new_filename;

			}

			String str = "";
			for (int i = 0; i < array.length; i++) {
				str += "," + array[i];
			}

//			temp = "";
//			for (int i = 0; i < array.length; i++) {
//				if (array[i] == null) {
//					temp += ",-";
//				} else {
//					temp += "," + array[i];
//				}
//			}
			str = str.substring(1);
			System.out.println("str: " + str);

			temp = multi.getParameter("no");
			no = util.numberCheck(temp, 0);
			String name = multi.getParameter("name");
			temp = multi.getParameter("price");
			int price = util.numberCheck(temp, 0);
			String description = multi.getParameter("description");
			dto.setNo(no);
			dto.setName(name);
			dto.setPrice(price);
			dto.setDescription(description);

			int result = 0;
			if (url.indexOf("chugaProc.do") != -1) {
				request.setAttribute("menu_gubun", "product_chugaProc");
				dto.setProduct_img(str);
				result = dao.setInsert(dto);
			} else if (url.indexOf("modifyProc.do") != -1) {
				request.setAttribute("menu_gubun", "product_modifyProc");
				ProductDTO dto2 = dao.getSelectOne(no);
				String db_product_img = dto2.getProduct_img();

				String deleteFileName = "";
				if (str.trim().equals("-,-,-")) {
					dto.setProduct_img(db_product_img);
				} else {
					temp = "";
					String[] dbArray = db_product_img.split(",");
					for (int i = 0; i < array.length; i++) {
						if (array[i].equals("-")) {
							temp += "," + dbArray[i];
						} else {
							temp += "," + array[i];
							deleteFileName += "," + dbArray[i].substring(dbArray[i].lastIndexOf("|") + 1);
						}
					}
					deleteFileName = deleteFileName.substring(1);
					System.out.println(deleteFileName);
					temp = temp.substring(1);
					System.out.println(temp);
					dto.setProduct_img(temp);
				}
				result = dao.setModify(dto);

				String[] arrayDelete = deleteFileName.split(",");
				for (int i = 0; i < arrayDelete.length; i++) {
					if (!arrayDelete[i].trim().equals("-")) {
						f1 = new java.io.File(img_path03 + arrayDelete[i]);
						f1.delete();
					}
				}
			}

			System.out.println(dto.toString());

		} else if (url.indexOf("list.do") != -1) {
			int pageSize = 10;
			int blockSize = 10;
			int totalRecord = dao.getTotalRecord(search_option, search_data);
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int number = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];

			List<ProductDTO> list = dao.getList(startRecord, lastRecord, search_option, search_data);

			request.setAttribute("menu_gubun", "product_list");
			request.setAttribute("list", list);

			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("number", number);

			request.setAttribute("startRecord", startRecord);
			request.setAttribute("lastRecord", lastRecord);

			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("lastPage", lastPage);

			page = "/shop/product/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("view.do") != -1) {
			dto = dao.getSelectOne(no);
			request.setAttribute("menu_gubun", "product_view");
			request.setAttribute("dto", dto);
			System.out.println("dto : " + dto);

			page = "/shop/product/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("modify.do") != -1) {
			dto = dao.getSelectOne(no);
			request.setAttribute("menu_gubun", "product_modify");
			request.setAttribute("dto", dto);

			page = "/shop/product/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("del.do") != -1) {
			String img_path01 = request.getSession().getServletContext().getRealPath("/attach/product_img/");
			String img_path03 = img_path01.replace("\\", "\\\\");
			java.io.File f1;
			request.setAttribute("menu_gubun", "product_delete");
			dto = dao.getSelectOne(no);
			int result = dao.setDel(no);
			String db_product_img = dto.getProduct_img();
			if (!db_product_img.trim().equals("-,-,-")) {
				String[] arrayDelete = db_product_img.split(",");
				for (int i = 0; i < arrayDelete.length; i++) {
					if (!arrayDelete[i].trim().equals("-")) {
						arrayDelete[i] = arrayDelete[i].substring(arrayDelete[i].lastIndexOf("|") + 1);
						f1 = new java.io.File(img_path03 + arrayDelete[i]);
						f1.delete();
					}
				}
			}
		}
	}
}
