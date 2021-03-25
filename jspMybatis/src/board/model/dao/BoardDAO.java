package board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import board.model.dto.BoardDTO;
import board.model.dto.Board_commentDTO;
import sqlmap.MybatisManager;

public class BoardDAO {

	String tableName01 = "board";
	String tableName02 = "board_comment";

	public int setInsert(BoardDTO dto) {
		int result = 0;
		System.out.println(dto.toString());
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("dto", dto);

		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.insert("board.setInsert", map);
		session.commit();
		session.close();
		return result;
	}

	public int getTotalRecord(String tbl, String search_option, String search_data) {
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("tbl", tbl);
		map.put("search_option", search_option);
		map.put("search_data", search_data);

		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.selectOne("board.getTotalRecord", map);
		session.close();
		return result;
	}

	public List<BoardDTO> getList(int startRecord, int lastRecord, String tbl, String search_option,
			String search_data) {
		List<BoardDTO> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		map.put("tbl", tbl);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);

		SqlSession session = MybatisManager.getInstance().openSession();
		list = session.selectList("board.getList", map);
		session.close();
		return list;
	}

	public BoardDTO getView(int no, String tbl, String search_option, String search_data) {
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("no", no);
		map.put("tbl", tbl);
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		SqlSession session = MybatisManager.getInstance().openSession();
		BoardDTO dto = session.selectOne("board.getView", map);
		session.close();
		return dto;
	}

	public int getMaxNoticeNo(String tbl) {
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("tbl", tbl);
		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.selectOne("board.getMaxNoticeNo", map);
		session.close();
		return result;
	}

	public int getMaxNum() {
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.selectOne("board.getMaxNum", map);
		session.close();
		return result;
	}

	public int getMaxRefNo() {
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.selectOne("board.getMaxRefNo", map);
		session.close();
		return result;
	}

	public void setUpdateHit(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("no", no);
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("board.setUpdateHit", map);
		session.commit();
		session.close();
	}

	public void setUpdateReLevel(BoardDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("dto", dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("board.setUpdateReLevel", map);
		session.commit();
		session.close();
	}

	public int setModify(BoardDTO dto) {
		System.out.println(dto.toString());
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("dto", dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.update("board.setModify", map);
		session.commit();
		session.close();
		return result;
	}

	public int setDel(int no) {
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("no", no);
		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.delete("board.setDel", map);
		session.commit();
		session.close();
		return result;
	}

	public int getTotalRecordComment(int no) {
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("tableName02", tableName02);
		map.put("no", no);
		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.selectOne("board.getTotalRecordComment", map);
		session.commit();
		session.close();
		return result;
	}

	public List<Board_commentDTO> getListComment(int startRecord, int lastRecord, int no) {
		List<Board_commentDTO> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("startRecord", startRecord);
		map.put("lastRecord", lastRecord);
		map.put("no", no);
		map.put("tableName02", tableName02);

		SqlSession session = MybatisManager.getInstance().openSession();
		list = session.selectList("board.getListComment", map);
		session.close();
		return list;
	}

	public int setCommentInsert(Board_commentDTO dto) {
		System.out.println(dto.toString());
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("tableName02", tableName02);
		map.put("dto", dto);
		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.insert("board.setCommentInsert", map);
		session.commit();
		session.close();
		return result;
	}
	
	public Board_commentDTO getComment(int comment_no) {
		Map<String, Object> map = new HashMap<>();
		map.put("tableName02", tableName02);
		map.put("comment_no", comment_no);
		SqlSession session = MybatisManager.getInstance().openSession();
		Board_commentDTO result = session.selectOne("board.getComment", map);
		session.close();
		return result;
	}
	
	public int setCommentModify(Board_commentDTO cdto) {
		System.out.println(cdto.toString());
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("tableName02", tableName02);
		map.put("cdto", cdto);
		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.update("board.setCommentModify", map);
		session.commit();
		session.close();
		return result;
	}
	
	public int setCommentDel(int comment_no, String passwd) {
		System.out.println(comment_no+" / "+passwd);
		int result = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("tableName02", tableName02);
		map.put("comment_no", comment_no);
		map.put("passwd", passwd);
		SqlSession session = MybatisManager.getInstance().openSession();
		result = session.delete("board.setCommentDel", map);
		session.commit();
		session.close();
		return result;
	}
}
