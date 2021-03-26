package shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import db.DbExample;
import shop.model.dto.CartDTO;
import shop.model.dto.ProductDTO;
import sqlmap.MybatisManager;

public class CartDAO {

	String tableName01 = "cart";
	String tableName02 = "product";

	public int setInsert(CartDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("tableName01", tableName01);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("cart.setInsert", map);
		session.commit();
		session.close();
		return result;
	}

	public int getTotalRecord(int memberNo) {
		System.out.println(memberNo);
		Map<String, String> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("memberNo", memberNo+"");
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("cart.getTotalRecord", map);
		session.close();
		return result;
	}

	public List<CartDTO> getList(int startRecord, int lastRecord, int memberNo) {
		Map<String, String> map = new HashMap<>();
		map.put("startRecord", startRecord+"");
		map.put("lastRecord", lastRecord+"");
		map.put("tableName01", tableName01);
		map.put("memberNo", memberNo+"");
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CartDTO> list = session.selectList("cart.getList", map);
		session.close();
		return list;
	}

	public void setDeleteBatch(List<String> list) {
		Map<String, Object> map = new HashMap<>();
		map.put("array", list);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("cart.setDeleteBatch", map);
		session.commit();
		session.close();
	}

	public List<CartDTO> getListCartProductGroup() {
		Map<String, String> map = new HashMap<>();
		map.put("tableName01", tableName01);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CartDTO> list = session.selectList("cart.getListCartProductGroup", map);
		session.close();
		return list;
	}
}
