package member.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import member.model.dto.MemberDTO;
import sqlmap.MybatisManager;

public class MemberDAO {

	String tableName01 = "member";
	String tableName02 = "";

	public int setInsert(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);

		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("member.setInsert", map);
		session.commit();
		session.close();
		return result;
	}

	public MemberDTO login(String id, String passwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("passwd", passwd);

		SqlSession session = MybatisManager.getInstance().openSession();
		MemberDTO dto = session.selectOne("member.login", map);
		session.close();
		return dto;
	}

	public MemberDTO getSelectOne(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		SqlSession session = MybatisManager.getInstance().openSession();
		MemberDTO dto = session.selectOne("member.getSelectOne", map);
		session.close();
		return dto;
	}

	public MemberDTO getModify(int no, String passwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("passwd", passwd);
		SqlSession session = MybatisManager.getInstance().openSession();
		MemberDTO dto = session.selectOne("member.getModify", map);
		session.close();
		return dto;
	}

	public int setModify(MemberDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("member.setModify", map);
		session.commit();
		session.close();
		return result;
	}

	public List<MemberDTO> getList(int startRecord, int lastRecord, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startRecord", startRecord + "");
		map.put("lastRecord", lastRecord + "");
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("tableName01", tableName01);

		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemberDTO> list = session.selectList("member.getList", map);
		session.close();
		return list;
	}

	public int getTotalRecord(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("tableName01", tableName01);

		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("member.getTotalRecord", map);
		session.close();
		return result;
	}

	public String getIdCheck(String id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		String result = session.selectOne("member.getIdCheck", id);
		session.close();
		return result;
	}

	public int setDel(int no, String passwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("passwd", passwd);
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("member.setDel", map);
		session.commit();
		session.close();
		return result;
	}

	public String getSelectBirthday() {
		String result = "";
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		SqlSession session = MybatisManager.getInstance().openSession();
		List<String> list  = session.selectList("member.getSelectBirthday", map);
		session.commit();
		session.close();
		for (int i = 0; i < list.size(); i++) {
			result += "," + list.get(i);
		}
		if (result != "") {
			result = result.substring(1);
		}
		System.out.println(result);
		return result;
	}
}
