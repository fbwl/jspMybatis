package shop.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DbExample;
import shop.model.dto.CartDTO;

public class CartDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	String tableName01 = "cart";
	String tableName02 = "product";

	public Connection getConn() {
		conn = DbExample.dbConn();
		return conn;
	}

	public void dbConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.dbConnClose(rs, pstmt, conn);
	}

	public int setInsert(CartDTO dto) {
		System.out.println(dto.toString());
		getConn();
		int result = 0;
		try {
			String sql = "insert into " + tableName01 + " values(seq_cart.nextval,?,?,?,CURRENT_TIMESTAMP)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getMemberNo());
			pstmt.setInt(2, dto.getProductNo());
			pstmt.setInt(3, dto.getAmount());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public int getTotalRecord(int memberNo) {
		int result = 0;
		conn = getConn();
		try {
			String sql = "select count(no) from " + tableName01 + " where memberNo = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
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

	public ArrayList<CartDTO> getList(int startRecord, int lastRecord, int memberNo) {
		ArrayList<CartDTO> list = new ArrayList<CartDTO>();
		conn = getConn();
		try {
			String basicSql = "";
			basicSql += "select C.*,P.name,P.price,P.description,P.product_img,(C.amount * P.price) buy_money ";
			basicSql += "from " + tableName01 + " C, " + tableName02 + " P ";
			basicSql += "where C.productNo = P.no and C.memberNo = ? ";
			basicSql += "order by P.name asc";
			int k = 0;
			String sql = "";
			sql += "select * from (select A.*, Rownum rnum from (" + basicSql + ") A) where rnum >=? and rnum <=? ";
//			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++k, memberNo);
			pstmt.setInt(++k, startRecord);
			pstmt.setInt(++k, lastRecord);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setNo(rs.getInt("no"));
				dto.setMemberNo(rs.getInt("memberNo"));
				dto.setProductNo(rs.getInt("productNo"));
				dto.setAmount(rs.getInt("amount"));
				dto.setRegi_date(rs.getTimestamp("regi_date"));
				dto.setProduct_name(rs.getString("name"));
				dto.setProduct_price(rs.getInt("price"));
				dto.setProduct_description(rs.getString("description"));
				dto.setProduct_img(rs.getString("product_img"));
				dto.setBuy_money(rs.getInt("buy_money"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return list;
	}

	public boolean setDeleteBatch(String[] array) {
		int[] count = new int[array.length];
		boolean result = false;
		conn = getConn();
		try {
			conn.setAutoCommit(false);
			for (int i = 0; i < array.length; i++) {
				String sql = "delete from " + tableName01 + " where no = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(array[i]));
				pstmt.addBatch();
			}
			pstmt.executeBatch() ;
            conn.commit() ;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			}catch (Exception e2) {
				e2.printStackTrace();
			}
			dbConnClose(rs, pstmt, conn);
		}
		for (int i = 0; i < count.length; i++) {
			if (count[i]!=-2) {
				result = false;
				break;
			}
		}
		return result;
	}

	public List<CartDTO> getListCartProductGroup() {
		ArrayList<CartDTO> list = new ArrayList<CartDTO>();
		conn = getConn();
		try {
			String sql = "select p.name product_name, sum(c.amount * p.price) buy_money ";
			sql += "from cart c inner join product p on c.productNo = p.no ";
			sql += "group by p.name ";
			sql += "order by product_name asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setProduct_name(rs.getString("product_name"));
				dto.setBuy_money(rs.getInt("buy_money"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return list;
	}
}
