package shop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import shop.model.dto.ProductDTO;
import sqlmap.MybatisManager;

public class ProductDAO {

	String tableName01 = "product";
	String tableName02 = "cart";

	public int setInsert(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("tableName01", tableName01);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.insert("product.setInsert", map);
		session.commit();
		session.close();
		return result;
	}

	public int getTotalRecord(String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("tableName01", tableName01);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.selectOne("product.getTotalRecord", map);
		session.close();
		return result;
	}

	public List<ProductDTO> getList(int startRecord, int lastRecord, String search_option, String search_data) {
		Map<String, String> map = new HashMap<>();
		map.put("startRecord", startRecord+"");
		map.put("lastRecord", lastRecord+"");
		map.put("search_option", search_option);
		map.put("search_data", search_data);
		map.put("tableName01", tableName01);
		map.put("tableName02", tableName02);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		List<ProductDTO> list = session.selectList("product.getList", map);
		session.close();
		return list;
	}

	public ProductDTO getSelectOne(int no) {
		Map<String, String> map = new HashMap<>();
		map.put("tableName01", tableName01);
		map.put("no", no+"");
		
		SqlSession session = MybatisManager.getInstance().openSession();
		ProductDTO dto = session.selectOne("product.getSelectOne", map);
		session.close();
		return dto;
	}
	
	public int setModify(ProductDTO dto) {
		Map<String, Object> map = new HashMap<>();
		map.put("dto", dto);
		map.put("tableName01", tableName01);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.update("product.setModify", map);
		session.commit();
		session.close();
		return result;
	}

	public int setDel(int no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("tableName01", tableName01);
		
		SqlSession session = MybatisManager.getInstance().openSession();
		int result = session.delete("product.setDel", map);
		session.commit();
		session.close();
		return result;
	}
}
