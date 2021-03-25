package memo.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import memo.model.dto.MemoDTO;
import sqlmap.MybatisManager;

public class MemoDAO {

	String tableName01 = "memo";
	String tableName02 = "";
	
	public int setInsert(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("memo.setInsert", map);
		session.commit();
		session.close();
		return result;
	}

	public int getTotalRecord() {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("memo.getTotalRecord");
		session.close();
		return result;
	}

	public List<MemoDTO> getList(int startRecord, int lastRecord) {
		Map<String, String> map = new HashMap<>();
		map.put("startRecord", startRecord+"");
		map.put("lastRecord", lastRecord+"");
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<MemoDTO> list = session.selectList("memo.getList", map);
		session.close();
		return list;
	}
	
	public int setModify(MemoDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("memo.setModify", map);
		session.commit();
		session.close();
		return result;
	}
	public int setDel(int no) {
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("memo.setDel", no);
		session.commit();
		session.close();
		return result;
	}
}