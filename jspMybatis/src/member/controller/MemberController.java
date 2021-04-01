package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.dao.MemberDAO;
import member.model.dto.MemberDTO;
import member.util.UtilMember;

@WebServlet("/member_servlet/*")
public class MemberController extends HttpServlet {
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

		UtilMember util = new UtilMember();

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

		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();

		String page = "/main/main.jsp";

		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "member_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("indexL.do") != -1) {
			request.setAttribute("menu_gubun", "member_login2");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("indexC.do") != -1) {
			request.setAttribute("menu_gubun", "member_chuga2");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("indexM.do") != -1) {
			request.setAttribute("menu_gubun", "member_modify2");
			request.setAttribute("no", cookNo);
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
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

			List<MemberDTO> list = dao.getList(startRecord, lastRecord, search_option, search_data);
			request.setAttribute("menu_gubun", "member_list");
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

			page = "/member/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("chuga.do") != -1) {
			request.setAttribute("menu_gubun", "member_chuga");
			request.setAttribute("proc", "chuga");
			page = "/member/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("chugaProc.do") != -1) {
			String id = request.getParameter("id");
			String idChk = id.replace(" ", "");
			if (id.length() == idChk.length()) {
				dto.setId(id);
				System.out.println("id O");
			} else {
				System.out.println("id X");
			}
			String passwd = request.getParameter("passwd");
			passwd = passwd.replace("<", "&lt;");
			passwd = passwd.replace(">", "&gt;");
			passwd = passwd.replace("&", "&amp;");
			passwd = passwd.replace("\"", "&quot;");
			passwd = passwd.replace("'", "&apos;");

			String passwdChk = passwd.replace(" ", "");
			if (passwd.length() == passwdChk.length()) {
				dto.setPasswd(passwd);
				System.out.println("passwd O");
			} else {
				System.out.println("passwd X");
			}
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			if (gender.equals("F") || gender.equals("M")) {
				dto.setGender(gender);
				System.out.println("gender O");
			} else {
				System.out.println("gender X");
			}
			temp = request.getParameter("bornYear");
			int bornYear = util.numberCheck(temp, 0);
			String postcode = request.getParameter("postcode");
			String address = request.getParameter("address");
			String detailAddress = request.getParameter("detailAddress");
			String extraAddress = request.getParameter("extraAddress");
			String email = request.getParameter("email");

			dto.setName(name);
			dto.setBornYear(bornYear);
			dto.setPostcode(postcode);
			dto.setAddress(address);
			dto.setDetailAddress(detailAddress);
			dto.setExtraAddress(extraAddress);
			dto.setEmail(email);
			System.out.println("insert : " + dto.toString());
			int result = dao.setInsert(dto);
			if (result > 0) {
				temp = path + "/member_servlet/login.do";
			} else {
				temp = path + "/member_servlet/chuga.do";
			}
			response.sendRedirect(temp);
		} else if (url.indexOf("login.do") != -1) {
			request.setAttribute("menu_gubun", "member_login");
			request.setAttribute("proc", "login");
			page = "/member/login.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("loginProc.do") != -1) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			passwd = passwd.replace("<", "&lt;");
			passwd = passwd.replace(">", "&gt;");
			passwd = passwd.replace("&", "&amp;");
			passwd = passwd.replace("\"", "&quot;");
			passwd = passwd.replace("'", "&apos;");
			MemberDTO resultDto = dao.login(id, passwd);
			System.out.println("login : " + resultDto);
			PrintWriter out = response.getWriter();
			if (resultDto != null) {
				HttpSession session = request.getSession();
				session.setAttribute("cookNo", resultDto.getNo());
				session.setAttribute("cookName", resultDto.getName());
				session.setAttribute("cookId", resultDto.getId());
				session.setAttribute("cookEmail", resultDto.getEmail());
				out.println("<script>");
                out.println("location.href='"+path+"/index.do';");
                out.println("</script>");
			} else {
				temp = path + "/member_servlet/login.do";
				response.sendRedirect(temp);
			}
		} else if (url.indexOf("logout.do") != -1) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그아웃\\n')");
			out.println("location.href='" + path + "';");
			out.println("</script>");
		} else if (url.indexOf("view.do") != -1) {
			dto = dao.getSelectOne(no);
			System.out.println("view : " + dto.toString());
			request.setAttribute("menu_gubun", "member_view");
			request.setAttribute("dto", dto);
			page = "/member/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("modify_passwdChk.do") != -1) {
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			request.setAttribute("no", no);
			request.setAttribute("menu_gubun", "member_passwdChk");
			page = "/member/modify_passwdChk.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("modify.do") != -1) {
			request.setAttribute("proc", "modify");
			String passwd = request.getParameter("passwd");
			MemberDTO resultDto = dao.getModify(no, passwd);
//			MemberDTO resultDto = dao.getSelectOne(no);
			if (resultDto == null) {
				temp = path + "/member_servlet/list.do";
				response.sendRedirect(temp);
			} else {
				request.setAttribute("dto", resultDto);
				request.setAttribute("menu_gubun", "member_modify");
				page = "/member/modify.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(page);
				rd.forward(request, response);
			}
		} else if (url.indexOf("modifyProc.do") != -1) {
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			temp = request.getParameter("bornYear");
			int bornYear = util.numberCheck(temp, 0);
			String postcode = request.getParameter("postcode");
			String address = request.getParameter("address");
			String detailAddress = request.getParameter("detailAddress");
			String extraAddress = request.getParameter("extraAddress");
			String email = request.getParameter("email");
			dto.setId(id);
			dto.setPasswd(passwd);
			dto.setName(name);
			dto.setGender(gender);
			dto.setBornYear(bornYear);
			dto.setPostcode(postcode);
			dto.setAddress(address);
			dto.setDetailAddress(detailAddress);
			dto.setExtraAddress(extraAddress);
			dto.setEmail(email);
			System.out.println("modify : " + dto.toString());
			int result = dao.setModify(dto);
			if (result > 0) {
				response.sendRedirect(temp);
			} else {
				temp = path + "/member_servlet/view?id=" + dto.getId();
				response.sendRedirect(temp);
			}
		} else if (url.indexOf("id_check.do") != -1) {
			request.setAttribute("proc", "id_check");
			String id = request.getParameter("id");
			String result = dao.getIdCheck(id);
			if (result == null || result.equals("")) {
				result = id;
			} else {
				result = "";
			}
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} else if (url.indexOf("id_check_win_open.do") != -1) {
			page = "/member/id_check.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("del_passwdChk.do") != -1) {
			page = "/member/del_passwdChk.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("delProc.do") != -1) {
			String passwd = request.getParameter("passwd");
			int result = dao.setDel(no, passwd);
			if (result == 0) {
				temp = path + "/member_servlet/del_passwdChk.do?no"+no;
				response.sendRedirect(temp);
			}else {
				temp = path + "/member_servlet/list.do";
				response.sendRedirect(temp);
			}
		}
	}

}
