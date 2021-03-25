package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import board.model.dao.BoardDAO;
import board.model.dto.BoardDTO;
import board.model.dto.Board_commentDTO;
import board.util.UtilBoard;

@WebServlet("/board_servlet/*")
public class BoardController extends HttpServlet {
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
		UtilBoard util = new UtilBoard();

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
//		BoardDAO dao = new BoardDAO();

		String temp;
		temp = request.getParameter("pageNumber");
		int pageNumber = util.numberCheck(temp, 1);

		temp = request.getParameter("tbl");
		String tbl = util.tblCheck(temp, "freeboard");

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
		request.setAttribute("tbl", tbl);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("no", no);
		request.setAttribute("search_option", search_option);
		request.setAttribute("search_data", search_data);

		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();

		String page = "/main/main.jsp";

		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "board_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("chuga.do") != -1 || url.indexOf("reply.do") != -1) {
			if (no > 0) {
				dto = dao.getView(no, tbl, search_option, search_data);
				temp = "[" + dto.getWriter() + "]님이 작성한 글입니다.\n";
				temp += dto.getContent();
				temp = temp.replace("\n", "\n> ");
				temp += "\n------------------------------------------\n";
				dto.setContent(temp);
				request.setAttribute("dto", dto);
			}
			request.setAttribute("menu_gubun", "board_chuga");
			page = "/board/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("chugaProc.do") != -1) {
			String writer = request.getParameter("writer");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String noticeGubun = request.getParameter("noticeGubun");

			int noticeNo;
			if (noticeGubun == null || noticeGubun.trim().equals("") || !noticeGubun.equals("T")) {
				noticeNo = 0;
			} else {
				noticeNo = dao.getMaxNoticeNo(tbl) + 1;
			}
			String secretGubun = request.getParameter("secretGubun");
			if (secretGubun == null || secretGubun.trim().equals("") || !secretGubun.equals("T")) {
				secretGubun = "F";
			} else {
				secretGubun = "T";
			}
			int num = dao.getMaxNum() + 1;
			int refNo = dao.getMaxRefNo() + 1; // 글 그룹을 의미 = 쿼리를 실행시켜서 가장 큰 refNo 값을 가져온 후 +1
			int stepNo = 1;
			int levelNo = 1;
			int parentNo = 0;
			if (no > 0) {
				BoardDTO dto2 = dao.getView(no, tbl, search_option, search_data);
				dao.setUpdateReLevel(dto2); // 답변글 // 부모 글보다 큰 levelNo의 값 +1
				refNo = dto2.getRefNo();
				stepNo = dto2.getStepNo() + 1;
				levelNo = dto2.getLevelNo() + 1;
				parentNo = dto2.getNo();
			}
			int hit = 0;

			dto.setNo(no);
			dto.setNum(num);
			dto.setTbl(tbl);
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setEmail(email);
			dto.setPasswd(passwd);

			dto.setRefNo(refNo);
			dto.setStepNo(stepNo);
			dto.setLevelNo(levelNo);
			dto.setParentNo(parentNo);
			dto.setHit(hit);
			dto.setIp(ip);

			dto.setMemberNo(cookNo);
			dto.setNoticeNo(noticeNo);
//			dto.setNoticeGubun(noticeGubun);
			dto.setSecretGubun(secretGubun);
			dao.setInsert(dto);

		} else if (url.indexOf("list.do") != -1) {
			int pageSize = 10;
			int blockSize = 10;
			int totalRecord = dao.getTotalRecord(tbl, search_option, search_data);
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int number = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];

			List<BoardDTO> list = dao.getList(startRecord, lastRecord, tbl, search_option, search_data);

			request.setAttribute("menu_gubun", "board_list");
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

			page = "/board/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("view.do") != -1) {
			dao.setUpdateHit(no);
			dto = dao.getView(no, tbl, search_option, search_data);
			dto.setContent(dto.getContent().replace("\n", "<br>"));
			String imsiPage = "viewPage";
			if (dto.getSecretGubun().equals("T")) { // 비밀글 체크
				String view_passwd = util.nullCheck(request.getParameter("view_passwd"));
				if (dto.getPasswd().equals(view_passwd) && !dto.getPasswd().equals("")) {

				} else {
					imsiPage = "viewPasswdPage";
				}
			}
			request.setAttribute("menu_gubun", "board_view");
			request.setAttribute("dto", dto);
			request.setAttribute("imsiPage", imsiPage);

			page = "/board/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("modify.do") != -1) {
			request.setAttribute("menu_gubun", "board_modify");

			dto = dao.getView(no, tbl, search_option, search_data);
			request.setAttribute("dto", dto);

			page = "/board/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("modifyProc.do") != -1) {
			request.setAttribute("menu_gubun", "board_view");

			String writer = request.getParameter("writer");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String noticeGubun = request.getParameter("noticeGubun");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if (dao.getView(no, tbl, search_option, search_data).getPasswd().equals(passwd)) {
				out.println("<script>$('#span_passwd').text('T');</script>");
				int noticeNo;
				if (noticeGubun == null || noticeGubun.trim().equals("") || !noticeGubun.equals("T")) {
					noticeNo = 0;
				} else {
					noticeNo = dao.getMaxNoticeNo(tbl) + 1;
				}
				String secretGubun = request.getParameter("secretGubun");
				if (secretGubun == null || secretGubun.trim().equals("") || !secretGubun.equals("T")) {
					secretGubun = "F";
				} else {
					secretGubun = "T";
				}

				dto.setNo(no);
				dto.setWriter(writer);
				dto.setSubject(subject);
				dto.setContent(content);
				dto.setEmail(email);
				dto.setPasswd(passwd);

				dto.setIp(ip);

				dto.setMemberNo(cookNo);
				dto.setNoticeNo(noticeNo);
				dto.setSecretGubun(secretGubun);

				int result = dao.setModify(dto);
			} else {
				out.println("<script>$('#span_passwd').text('F');</script>");
			}
			out.flush();
			out.close();
		} else if (url.indexOf("del.do") != -1) {
			request.setAttribute("menu_gubun", "board_delete");

			page = "/board/delete.jsp";

			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("delProc.do") != -1) {
			String passwd = request.getParameter("passwd");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			int result = 0;
			if (dao.getView(no, tbl, search_option, search_data).getPasswd().equals(passwd)) {
				System.out.println("delete success");
				result = dao.setDel(no);
				out.println("<script>$('#span_passwd').text('T');</script>");
			} else {
				System.out.println("delete fail");
				out.println("<script>$('#span_passwd').text('F');</script>");
			}
			out.flush();
			out.close();
		} else if (url.indexOf("commentList.do") != -1) {
			temp = request.getParameter("commentPageNumber");
			int commentPageNumber = util.numberCheck(temp, 1);

			int pageSize = 5;
			int blockSize = 10;
			int totalRecord = dao.getTotalRecordComment(no);
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, commentPageNumber);
			int number = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];

			List<Board_commentDTO> list = dao.getListComment(startRecord, lastRecord, no);

			request.setAttribute("menu_gubun", "board_comment_list");
			request.setAttribute("list", list);

			request.setAttribute("commentPageNumber", commentPageNumber);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("totalRecord", totalRecord);
			request.setAttribute("number", number);

			request.setAttribute("startRecord", startRecord);
			request.setAttribute("lastRecord", lastRecord);

			request.setAttribute("totalPage", totalPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("lastPage", lastPage);

			page = "/board/comment_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("commentProc.do") != -1) {
			String writer = request.getParameter("writer");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");

			Board_commentDTO comment_dto = new Board_commentDTO();

			comment_dto.setBoard_no(no);
			comment_dto.setWriter(writer);
			comment_dto.setPasswd(passwd);
			comment_dto.setContent(content);
			comment_dto.setIp(ip);

			dao.setCommentInsert(comment_dto);
		} else if (url.indexOf("commentModifyProc.do") != -1) {
			String writer = request.getParameter("writer");
			temp = request.getParameter("board_no");
			int board_no = util.numberCheck(temp, 0);
			temp = request.getParameter("comment_no");
			int comment_no = util.numberCheck(temp, 0);
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");

			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if (dao.getComment(comment_no).getPasswd().equals(passwd)) {
				out.println("<script>$('#span_passwd').text('T');</script>");
				Board_commentDTO comment_dto = new Board_commentDTO();

				comment_dto.setComment_no(comment_no);
				comment_dto.setBoard_no(board_no);
				comment_dto.setWriter(writer);
				comment_dto.setPasswd(passwd);
				comment_dto.setContent(content);
				dao.setCommentModify(comment_dto);
			} else {
				out.println("<script>$('#span_passwd').text('F');</script>");
			}
			out.flush();
			out.close();
		} else if (url.indexOf("commentDelProc.do") != -1) {
			temp = request.getParameter("comment_no");
			int comment_no = util.numberCheck(temp, 0);
			String passwd = request.getParameter("passwd");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if (dao.getComment(comment_no).getPasswd().equals(passwd)) {
				out.println("<script>$('#span_passwd').text('T');</script>");
				dao.setCommentDel(comment_no, passwd);
			} else {
				out.println("<script>$('#span_passwd').text('F');</script>");
			}
			out.flush();
			out.close();
		}
	}
}
