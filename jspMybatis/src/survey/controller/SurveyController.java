package survey.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import survey.model.dao.SurveyDAO;
import survey.model.dto.SurveyAnswerDTO;
import survey.model.dto.SurveyDTO;
import survey.util.UtilSurvey;

@WebServlet("/survey_servlet/*")
public class SurveyController extends HttpServlet {
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

		UtilSurvey util = new UtilSurvey();
		SurveyDAO dao = new SurveyDAO();
		SurveyDTO dto = new SurveyDTO();
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
		String search_date_s = request.getParameter("search_date_s");
		String search_date_e = request.getParameter("search_date_e");
		String search_date_check = request.getParameter("search_date_check");
		String[] searchArray = util.searchCheck(search_option, search_data, search_date_s, search_date_e,
				search_date_check);
		search_option = searchArray[0];
		search_data = searchArray[1];
		search_date_s = searchArray[2];
		search_date_e = searchArray[3];
		search_date_check = searchArray[4];

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

		String page = "/main/main.jsp";

		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "survey_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("index2.do") != -1) {
			request.setAttribute("menu_gubun", "survey2_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("chuga.do") != -1) {
			request.setAttribute("menu_gubun", "survey_chuga");
			page = "/survey/chuga.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("chugaProc.do") != -1 || url.indexOf("modifyProc.do") != -1) {
			String question = request.getParameter("question");
			String ans1 = request.getParameter("ans1");
			String ans2 = request.getParameter("ans2");
			String ans3 = request.getParameter("ans3");
			String ans4 = request.getParameter("ans4");
			String status = request.getParameter("status");

			String syear = request.getParameter("syear");
			String smonth = request.getParameter("smonth");
			String sday = request.getParameter("sday");

			String lyear = request.getParameter("lyear");
			String lmonth = request.getParameter("lmonth");
			String lday = request.getParameter("lday");

			String start_date_ = syear + "-" + smonth + "-" + sday + " 00:00:00.0";
			String last_date_ = lyear + "-" + lmonth + "-" + lday + " 23:59:59.9";
			System.out.println("start:" + start_date_);
			System.out.println("last:" + last_date_);
			java.sql.Timestamp start_date = java.sql.Timestamp.valueOf(start_date_);
			java.sql.Timestamp last_date = java.sql.Timestamp.valueOf(last_date_);

			dto.setQuestion(question);
			dto.setAns1(ans1);
			dto.setAns2(ans2);
			dto.setAns3(ans3);
			dto.setAns4(ans4);
			dto.setStatus(status);
			dto.setStart_date(start_date);
			dto.setLast_date(last_date);
			System.out.println(dto.toString());
			if (url.indexOf("chugaProc.do") != -1) {
				dao.setInsert(dto);
			} else if (url.indexOf("modifyProc.do") != -1) {
				dto.setNo(no);
				dao.setModify(dto);
			}
		} else if (url.indexOf("list.do") != -1 || url.indexOf("list_2.do") != -1) {
			int pageSize = 5;
			int blockSize = 10;
			int totalRecord = dao.getTotalRecord(list_gubun, search_option, search_data, search_date_s, search_date_e,
					search_date_check);
			if (url.indexOf("list.do") != -1) {
				page = "/survey/list.jsp";
			} else {
				page = "/survey/list_2.jsp";
				pageSize = totalRecord;
			}
			int[] pagerArray = util.pager(pageSize, blockSize, totalRecord, pageNumber);
			int number = pagerArray[0];
			int startRecord = pagerArray[1];
			int lastRecord = pagerArray[2];
			int totalPage = pagerArray[3];
			int startPage = pagerArray[4];
			int lastPage = pagerArray[5];

			List<SurveyDTO> list = dao.getList(startRecord, lastRecord, list_gubun, search_option, search_data,
					search_date_s, search_date_e, search_date_check);
			request.setAttribute("menu_gubun", "survey_list");
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
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("view.do") != -1) {
			dto = dao.getView(no);
			request.setAttribute("menu_gubun", "survey_view");
			request.setAttribute("dto", dto);
			page = "/survey/view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("viewProc.do") != -1) {
			String answer_ = request.getParameter("answer");
			int answer = util.numberCheck(answer_, 0);
			SurveyAnswerDTO sadto = new SurveyAnswerDTO();
			sadto.setNo(no);
			sadto.setAnswer(answer);
			System.out.println(sadto.toString());
			dao.setInsertAnswer(sadto);
		} else if (url.indexOf("ansProc.do") != -1) {
			String answer_ = request.getParameter("answer");
			int answer = util.numberCheck(answer_, 0);
			SurveyAnswerDTO sadto = new SurveyAnswerDTO();
			sadto.setNo(no);
			sadto.setAnswer(answer);
			dao.setInsertAnswer(sadto);
		} else if (url.indexOf("saveProc.do") != -1) {
			String answer_total = request.getParameter("answer_total");
			System.out.println("answer_total : " + answer_total);
			String[] answer_totalArr = answer_total.split("[|]");
			System.out.println("answer_total_arr : " + answer_totalArr.length);
			for (int i = 0; i < answer_totalArr.length; i++) {
				String[] imsiArr = answer_totalArr[i].split(":");
				int tempNo = Integer.parseInt(imsiArr[0]);
				int tempAnswer = Integer.parseInt(imsiArr[1]);
				System.err.println("tempNo:" + tempNo + "|tempAnswer:" + tempAnswer);
				SurveyAnswerDTO answerDto = new SurveyAnswerDTO();
				answerDto.setNo(tempNo);
				answerDto.setAnswer(tempAnswer);
				dao.setInsertAnswer(answerDto);
			}
		} else if (url.indexOf("modify.do") != -1) {
			request.setAttribute("menu_gubun", "survey_modify");
			dto = dao.getView(no);
			naljaMap.put("syear", dto.getStart_date().getYear() + 1900);
			naljaMap.put("smonth", dto.getStart_date().getMonth() + 1);
			naljaMap.put("sday", dto.getStart_date().getDate());
			naljaMap.put("lyear", dto.getLast_date().getYear() + 1900);
			naljaMap.put("lmonth", dto.getLast_date().getMonth() + 1);
			naljaMap.put("lday", dto.getLast_date().getDate());
			request.setAttribute("dto", dto);
			request.setAttribute("naljaMap", naljaMap);
			page = "/survey/modify.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("delProc.do") != -1) {
			dao.setDelAnswer(no);
			dao.setDel(no);
		}
	}

}
