package survey.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;
import survey.model.dto.SurveyAnswerDTO;
import survey.model.dto.SurveyDTO;

public class SurveyDAO {

	String tableName01 = "survey";
	String tableName02 = "survey_answer";

	public int setInsert(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);

		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("survey.setInsert", map);
		session.commit();
		session.close();
		return result;
	}
	
	public int setInsertAnswer(SurveyAnswerDTO saDTO) {
		Map<String, Object> map = new HashMap<>();
		map.put("saDTO", saDTO);

		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("survey.setInsertAnswer", map);
		session.commit();
		session.close();
		return result;
	}
	public int getTotalRecord(String list_gubun, String search_option, String search_data, String search_date_s,
			String search_date_e, String search_date_check) {
		if (search_date_check.equals("O") && search_date_s.length() > 0 && search_date_e.length() > 0) {
			search_date_s = search_date_s + " 00:00:00.0";
			search_date_e = search_date_e + " 23:59:59.999999";
//				java.sql.Timestamp start_date =  java.sql.Timestamp.valueOf(search_date_s);
//				java.sql.Timestamp last_date =  java.sql.Timestamp.valueOf(search_date_e);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("list_gubun", list_gubun);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("search_date_s", search_date_s);
		map.put("search_date_e", search_date_e);
		map.put("search_date_check", search_date_check);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);

		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("survey.getTotalRecord", map);
		session.close();
		return result;
	}

	public List<SurveyDTO> getList(int startRecord, int lastRecord, String list_gubun, String search_option,
			String search_data, String search_date_s, String search_date_e, String search_date_check) {
		List<SurveyDTO> list = new ArrayList<>();
		if (search_date_check.equals("O") && search_date_s.length() > 0 && search_date_e.length() > 0) {
			search_date_s = search_date_s + " 00:00:00.0";
			search_date_e = search_date_e + " 23:59:59.999999";
		}
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		map.put("list_gubun", list_gubun);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("search_date_s", search_date_s);
		map.put("search_date_e", search_date_e);
		map.put("search_date_check", search_date_check);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);

		SqlSession session = MybatisManager.getInstance().openSession();
		list = session.selectList("survey.getList", map);
		session.close();
		return list;
	}

	public SurveyDTO getView(int no) {
		SqlSession session = MybatisManager.getInstance().openSession();
		SurveyDTO dto = session.selectOne("survey.getView", no);
		session.close();
		return dto;
	}

	public int setModify(SurveyDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("dto", dto);

		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("survey.setModify", map);
		session.commit();
		session.close();
		return result;
	}

	public int setDel(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("tableName01", tableName01);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("survey.setDel", map);
		session.commit();
		session.close();
		return result;
	}

	public int setDelAnswer(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("survey.setDelAnswer", map);
		session.commit();
		session.close();
		return result;
	}
}
