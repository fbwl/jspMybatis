package shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import shop.model.dto.ProductDTO;

public class ProductDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String tableName01 = "product";
	String tableName02 = "";

	public Connection getConn() {
		conn = DbExample.dbConn();
		return conn;
	}

	public void dbConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.dbConnClose(rs, pstmt, conn);
	}

	public int setInsert(ProductDTO dto) {
		System.out.println(dto.toString());
		getConn();
		int result = 0;
		try {
			String sql = "insert into " + tableName01 + " values(seq_product.nextval,?,?,?,?,CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getDescription());
			pstmt.setString(4, dto.getProduct_img());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int getTotalRecord(String search_option, String search_data) {
		int result = 0;
		conn = getConn();
		try {
			String sql = "select count(no) from " + tableName01 + " where no > 0 ";
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("name") || search_option.equals("description")) {
					sql += " and " + search_option + " like ? ";
				} else if (search_option.equals("name_description")) {
					sql += " and (name like ? or description like ?) ";
				}
			}
			int k = 0;
			pstmt = conn.prepareStatement(sql);

			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("name") || search_option.equals("description")) {
					pstmt.setString(++k, '%' + search_data + '%');
				} else if (search_option.equals("name_description")) {
					pstmt.setString(++k, '%' + search_data + '%');
					pstmt.setString(++k, '%' + search_data + '%');
				}
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public ArrayList<ProductDTO> getList(int startRecord, int lastRecord, String search_option, String search_data) {
		ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
		conn = getConn();
		try {
			String basicSql = "";
			basicSql += "select * from " + tableName01 + " where no > 0 ";

			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("name") || search_option.equals("description")) {
					basicSql += " and " + search_option + " like ? ";
				} else if (search_option.equals("name_description")) {
					basicSql += " and (name like ? or description like ?) ";
				}
			}
			basicSql += "order by no desc";

			int k = 0;
			String sql = "";
			sql += "select * from (select A.*, Rownum rnum from (" + basicSql + ") A) where rnum >=? and rnum <=? ";

			pstmt = conn.prepareStatement(sql);

			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("name") || search_option.equals("description")) {
					pstmt.setString(++k, '%' + search_data + '%');
				} else if (search_option.equals("name_description")) {
					pstmt.setString(++k, '%' + search_data + '%');
					pstmt.setString(++k, '%' + search_data + '%');
				}
			}

			pstmt.setInt(++k, startRecord);
			pstmt.setInt(++k, lastRecord);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setDescription(rs.getString("description"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setRegi_date(rs.getTimestamp("regi_date"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return list;
	}

	public ProductDTO getView(int no) {
		conn = getConn();
		ProductDTO dto = new ProductDTO();
		try {
			String sql = "select * from " + tableName01 + " where no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setDescription(rs.getString("description"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setRegi_date(rs.getTimestamp("regi_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return dto;
	}
	
	public int setModify(ProductDTO dto) {
		getConn();
		int result = 0;
		try {
			String sql = "update " + tableName01 + " set name=?, price=?, description=?, product_img=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getDescription());
			pstmt.setString(4, dto.getProduct_img());
			pstmt.setInt(5, dto.getNo());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int delete(int no) {
		getConn();
		int result = 0;
		try {
			String sql = "delete from " + tableName01 + " where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
}
