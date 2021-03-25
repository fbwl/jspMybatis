package guestbook.controller;

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

import guestbook.model.dao.GuestbookDAO;
import guestbook.model.dto.GuestbookDTO;
import guestbook.util.UtilGuestbook;
import member.model.dto.MemberDTO;

@WebServlet("/guestbook_servlet/*")
public class GuestbookController extends HttpServlet {
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

		UtilGuestbook util = new UtilGuestbook();

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

		GuestbookDTO dto = new GuestbookDTO();
		GuestbookDAO dao = new GuestbookDAO();

		String page = "/main/main.jsp";

		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "guestbook_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("chugaProc.do") != -1) {
			String name = request.getParameter("name");
			String passwd = request.getParameter("passwd");
			String email = request.getParameter("email");
			String content = request.getParameter("content");

			dto.setName(name);
			dto.setPasswd(passwd);
			dto.setEmail(email);
			dto.setContent(content);
			System.out.println(dto.toString());

			int result = dao.setInsert(dto);
			if (result > 0) {
				System.out.println("등록되었습니다.");
				temp = path + "/guestbook_servlet/list.do";
			} else {
				System.out.println("결과코드: " + result);
				temp = path + "/guestbook_servlet/write.do";
			}
			response.sendRedirect(temp);
		} else if (url.indexOf("list.do") != -1) {
			int pageSize = 5;
			int blockSize = 10;
			int totalRecord = dao.getTotalRecord(search_option, search_data);
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int number = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];

			List<GuestbookDTO> list = dao.getList(startRecord, lastRecord, search_option, search_data);
			request.setAttribute("menu_gubun", "guestbook_list");
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

			page = "/guestbook/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);

		} else if (url.indexOf("modifyProc.do") != -1) {
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String content = request.getParameter("content");
			dto.setNo(no);
			dto.setPasswd(passwd);
			dto.setName(name);
			dto.setEmail(email);
			dto.setContent(content);
			System.out.println("modify : " + dto.toString());
			int result = dao.setModify(dto);

			page = "/guestbook/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("del_passwdChk.do") != -1) {
			page = "/guestbook/del_passwdChk.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		} else if (url.indexOf("delProc.do") != -1) {
			String passwd = request.getParameter("passwd");
			int result = dao.setDel(no, passwd);
		}
	}
}
