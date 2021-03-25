package guestbook.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import guestbook.model.dto.GuestbookDTO;
import sqlmap.MybatisManager;

public class GuestbookDAO {

	public int setInsert(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("guestbook.setInsert", map);
		session.commit();
		session.close();
		return result;
	}

	public int getTotalRecord(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("guestbook.getTotalRecord", map);
		session.close();
		return result;
	}

	public List<GuestbookDTO> getList(int startRecord, int lastRecord, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startRecord", startRecord+"");
		map.put("lastRecord", lastRecord+"");
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<GuestbookDTO> list = session.selectList("guestbook.getList", map);
		session.close();
		return list;
	}
	
	public int setModify(GuestbookDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("guestbook.setModify", map);
		session.commit();
		session.close();
		return result;
	}
	public int setDel(int no, String passwd) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("passwd", passwd);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("guestbook.setDel", map);
		session.commit();
		session.close();
		return result;
	}
}
