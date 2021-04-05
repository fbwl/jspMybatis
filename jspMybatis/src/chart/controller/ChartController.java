package chart.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import chart.service.ChartService;
import common.Util;

@WebServlet("/chart_servlet/*")
public class ChartController extends HttpServlet {
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
			request.setAttribute("menu_gubun", "chart_index");
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("googleChartJson.do") != -1) {
			request.setAttribute("menu_gubun", "googleChartJson");
			page = "/chart/googleChartJson.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("googleChartDb.do") != -1) {
			request.setAttribute("menu_gubun", "googleChartDb");
			page = "/chart/googleChartDb.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("createJsonProductChart.do") != -1) {
			request.setAttribute("menu_gubun", "chart_createJson");
			page = "/chart/createJson.jsp";
			ChartService service = new ChartService();
			JSONObject json = service.getChartData();
			System.out.println(json.toString());
			request.setAttribute("data", json);

			String img_path01 = request.getSession().getServletContext().getRealPath("/attach/json/");
			java.io.File isDir = new java.io.File(img_path01);
			if (!isDir.isDirectory()) {
				isDir.mkdir();
			}
			String img_path02 = img_path01.replace("\\", "/");
			String img_path03 = img_path01.replace("\\", "\\\\");

			util.fileDelete(request, img_path03);

			String newFileName = util.getDateTimeType() + "_" + util.create_uuid() + ".json";
			File file = new File(img_path03 + newFileName);
			file.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(json.toString());
			bufferedWriter.close();
			System.out.println(newFileName);

			request.setAttribute("menu_gubun", "product_list");
			request.setAttribute("chart_subject", "매출");
			request.setAttribute("chart_type", "PieChart");
			request.setAttribute("chart_jsonFileName", newFileName);

			page = "/chart/myChart.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		} else if (url.indexOf("createJsonSurveyAnswerChart.do") != -1) {
			ChartService service = new ChartService();
			JSONObject json = service.getChartSurveyAnswer(no);
			request.setAttribute("data", json);

			String img_path01 = request.getSession().getServletContext().getRealPath("/attach/json/");
			java.io.File isDir = new java.io.File(img_path01);
			if (!isDir.isDirectory()) {
				isDir.mkdir();
			}
			String img_path02 = img_path01.replace("\\", "/");
			String img_path03 = img_path01.replace("\\", "\\\\");

			util.fileDelete(request, img_path03);

			String newFileName = util.getDateTimeType() + "_" + util.create_uuid() + ".json";
			File file = new File(img_path03 + newFileName);
			file.createNewFile();
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(json.toString());
			bufferedWriter.close();
			System.out.println(newFileName);

			request.setAttribute("chart_type", "PieChart");
			request.setAttribute("chart_jsonFileName", newFileName);
			
			page = "/chart/myChart.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}

}
