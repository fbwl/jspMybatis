package chart.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import shop.model.dao.CartDAO;
import shop.model.dto.CartDTO;
import survey.model.dao.SurveyDAO;
import survey.model.dto.SurveyAnswerDTO;
import survey.model.dto.SurveyDTO;


public class ChartService {
	public JSONObject getChartData() {
		CartDAO cartDao = new CartDAO();
		List<CartDTO> items = cartDao.getListCartProductGroup();
		JSONObject data = new JSONObject();
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		JSONArray title = new JSONArray();
		
		col1.put("label", "상품명");
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type", "number");
		
		title.add(col1);
		title.add(col2);
		data.put("cols", title);
		
		JSONArray body = new JSONArray(); // 실제 데이터
		for(CartDTO dto : items) {
			JSONObject name = new JSONObject();
			name.put("v", dto.getProduct_name());
			JSONObject money = new JSONObject();
			money.put("v", dto.getBuy_money());
			JSONArray row = new JSONArray();
			row.add(name);
			row.add(money);
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			body.add(cell);
		}
		data.put("rows", body);
		return data;
	}

	public JSONObject getChartSurveyAnswer(int no) {
		// TODO Auto-generated method stub
		SurveyDAO survayDao = new SurveyDAO();
		List<SurveyAnswerDTO> items = survayDao.getListAns(no);
		JSONObject data = new JSONObject();
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		JSONArray title = new JSONArray();
		
		col1.put("label", "문항");
		col1.put("type", "string");
		col2.put("label", "응답수");
		col2.put("type", "number");
		
		title.add(col1);
		title.add(col2);
		data.put("cols", title);
		
		JSONArray body = new JSONArray(); // 실제 데이터
		SurveyDTO sDTO = survayDao.getView(no);
		for(SurveyAnswerDTO dto : items) {
			JSONObject answer = new JSONObject();
			if (dto.getAnswer()==1) {
				answer.put("v", sDTO.getAns1());
			}else if (dto.getAnswer()==2) {
				answer.put("v", sDTO.getAns2());
			}else if (dto.getAnswer()==3) {
				answer.put("v", sDTO.getAns3());
			}else if (dto.getAnswer()==4) {
				answer.put("v", sDTO.getAns4());
			}
			JSONObject answer_count = new JSONObject();
			answer_count.put("v", dto.getAnswer_count());
			JSONArray row = new JSONArray();
			row.add(answer);
			row.add(answer_count);
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			body.add(cell);
		}
		data.put("rows", body);
		return data;
	}
}
