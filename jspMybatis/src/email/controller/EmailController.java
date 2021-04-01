package email.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Util;
import email.model.dto.EmailDTO;
import email.service.EmailService;
import member.model.dao.MemberDAO;

@WebServlet("/email_servlet/*")
public class EmailController extends HttpServlet {
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
		Util util = new Util();

		int[] nalja = util.getDateTime();
		Map<String, Integer> naljaMap = new HashMap<>();
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
		
		String[] sesstionArray = util.sessionCheck(request);
		int cookNo = Integer.parseInt(sesstionArray[0]);
		String cookId = sesstionArray[1];
		String cookName = sesstionArray[2];

		request.setAttribute("nalja", nalja);
		request.setAttribute("ip", ip);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("no", no);
		
		String page = "/main/main.jsp";

		if (url.indexOf("index.do") != -1) {
			request.setAttribute("menu_gubun", "email_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("send.do") != -1) {
			request.setAttribute("menu_gubun", "email_send");
			page = "/email/send.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if (url.indexOf("sendProc.do") != -1) {
			String fromName = request.getParameter("fromName");
			String fromEmail = request.getParameter("fromEmail");
			String toEmail = request.getParameter("toEmail");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			EmailDTO dto = new EmailDTO();
			dto.setFromName(fromName);
			dto.setFromEmail(fromEmail);
			dto.setSubject(subject);
			dto.setContent(content);
			
			String[] toEmailArray = toEmail.split(",");
			EmailService service = new EmailService();
			for (int i = 0; i < toEmailArray.length; i++) {
				try {
					dto.setToEmail(toEmailArray[i]);
					System.out.println(dto.getToEmail());
					service.mailSender(dto);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			page = "/email/send.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}else if (url.indexOf("selectBirthday.do") != -1) {
			MemberDAO dao = new MemberDAO();
			String result = dao.getSelectBirthday();
			System.out.println(result);
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		}
	}

}
