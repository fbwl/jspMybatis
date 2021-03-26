package shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.model.dao.CartDAO;
import shop.model.dao.ProductDAO;
import shop.model.dto.CartDTO;
import shop.model.dto.ProductDTO;
import shop.util.UtilProduct;


@WebServlet("/mall_servlet/*")
public class MallController extends HttpServlet {
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

		ProductDTO productDto = new ProductDTO();
		ProductDAO productDao = new ProductDAO();

		CartDTO cartDto = new CartDTO();
		CartDAO cartDao = new CartDAO();

		String page = "/main/main.jsp";

		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "mall_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("mall_list.do") != -1) {
			int pageSize = 12;
			int blockSize = 10;
			int totalRecord = productDao.getTotalRecord(search_option, search_data);
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int number = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];

			List<ProductDTO> list = productDao.getList(startRecord, lastRecord, search_option, search_data);

			request.setAttribute("menu_gubun", "mall_list");
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

			page = "/shop/mall/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("view.do") != -1) {
			productDto = productDao.getSelectOne(no);
			request.setAttribute("menu_gubun", "product_view");
			request.setAttribute("dto", productDto);

			page = "/shop/mall/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("cart_insertProc.do") != -1) {
			temp = request.getParameter("productNo");
			int productNo = util.numberCheck(temp, 0);
			temp = request.getParameter("amount");
			int amount = util.numberCheck(temp, 0);
			
			cartDto.setMemberNo(cookNo);
			cartDto.setProductNo(productNo);
			cartDto.setAmount(amount);
			int result = cartDao.setInsert(cartDto);
			
		} else if (url.indexOf("cartList.do") != -1) {
			int pageSize = 10;
			int blockSize = 10;
			int totalRecord = cartDao.getTotalRecord(cookNo);
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int number = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];

			List<CartDTO> list = cartDao.getList(startRecord, lastRecord, cookNo);
			request.setAttribute("menu_gubun", "cartList");
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
			
			page = "/shop/mall/cart_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("cart_clear.do") != -1) {
			List<String> list = new ArrayList<>();
			temp = request.getParameter("chk_no");
			String[] array = temp.split(",");
			for (int i = 0; i < array.length; i++) {
				list.add(array[i]);
			}
			cartDao.setDeleteBatch(list);
		}
	}
}
