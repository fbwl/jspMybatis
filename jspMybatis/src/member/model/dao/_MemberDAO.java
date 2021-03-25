package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import db.DbExample;
import member.model.dto.MemberDTO;

public class _MemberDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String tableName01 = "member";
	String tableName02 = "";

	public Connection getConn() {
		conn = DbExample.dbConn();
		return conn;
	}

	public void dbConnClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		DbExample.dbConnClose(rs, pstmt, conn);
	}

	public int setInsert(MemberDTO dto) {
		int result = 0;
		conn = getConn();
		try {
			String sql = "insert into member (no,id,passwd,name,gender,bornyear,regidate,postcode,address,detailaddress,extraaddress,email) "
					+ "values(seq_member.nextval,?,?,?,?,?,current_timestamp,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPasswd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getGender());
			pstmt.setInt(5, dto.getBornYear());
			pstmt.setString(6, dto.getPostcode());
			pstmt.setString(7, dto.getAddress());
			pstmt.setString(8, dto.getDetailAddress());
			pstmt.setString(9, dto.getExtraAddress());
			pstmt.setString(10, dto.getEmail());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public MemberDTO login(String id, String passwd) {
		MemberDTO dto = new MemberDTO();
		conn = getConn();
		try {
			String sql = "select * from member where id=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				dto.setPostcode(rs.getString("postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setDetailAddress(rs.getString("detailAddress"));
				dto.setExtraAddress(rs.getString("extraAddress"));
				dto.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return dto;
	}

	public MemberDTO getSelectOne(int no) {
		MemberDTO dto = new MemberDTO();
		conn = getConn();
		try {
			String sql = "select * from member where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				dto.setPostcode(rs.getString("postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setDetailAddress(rs.getString("detailAddress"));
				dto.setExtraAddress(rs.getString("extraAddress"));
				dto.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return dto;
	}

	public MemberDTO getModify(int no, String passwd) {
		MemberDTO dto = new MemberDTO();
		conn = getConn();
		try {
			String sql = "select * from member where no=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, passwd);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				dto.setPostcode(rs.getString("postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setDetailAddress(rs.getString("detailAddress"));
				dto.setExtraAddress(rs.getString("extraAddress"));
				dto.setEmail(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return dto;
	}

	public int setModify(MemberDTO dto) {
		int result = 0;
		conn = getConn();
		System.out.println("setModify : " + dto.toString());
		try {
			String sql = "update member set passwd=?, name=?, gender=?, postcode=?, address=?, detailAddress=?, extraAddress=?, email=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPasswd());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getGender());
			pstmt.setString(4, dto.getPostcode());
			pstmt.setString(5, dto.getAddress());
			pstmt.setString(6, dto.getDetailAddress());
			pstmt.setString(7, dto.getExtraAddress());
			pstmt.setString(8, dto.getEmail());
			pstmt.setString(9, dto.getId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public ArrayList<MemberDTO> getList(int startRecord, int lastRecord, String search_option, String search_data) {
		ArrayList<MemberDTO> list = new ArrayList<>();
		conn = getConn();
		try {
			String basicSql = "";
			basicSql += "select * from " + tableName01 + " where no > 0 ";

			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					basicSql += " and " + search_option + " like ? ";
				} else if (search_option.equals("id_name_gender")) {
					basicSql += " and (id like ? or name like ? or gender like ?) ";
				}
			}
			basicSql += "order by no desc";

			int k = 0;
			String sql = "";
			sql += "select * from (select A.*, Rownum rnum from (" + basicSql + ") A) where rnum >=? and rnum <=? ";

			pstmt = conn.prepareStatement(sql);

			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					pstmt.setString(++k, '%' + search_data + '%');
				} else if (search_option.equals("id_name_gender")) {
					pstmt.setString(++k, '%' + search_data + '%');
					pstmt.setString(++k, '%' + search_data + '%');
					pstmt.setString(++k, '%' + search_data + '%');
				}
			}
			pstmt.setInt(++k, startRecord);
			pstmt.setInt(++k, lastRecord);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setPasswd(rs.getString("passwd"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				dto.setPostcode(rs.getString("postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setDetailAddress(rs.getString("detailAddress"));
				dto.setExtraAddress(rs.getString("extraAddress"));
				dto.setEmail(rs.getString("email"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return list;
	}

	public ArrayList<MemberDTO> getSelectAll() {
		ArrayList<MemberDTO> list = new ArrayList<>();
		conn = getConn();
		try {
			String sql = "select * from member order by no desc";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setNo(rs.getInt("no"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setGender(rs.getString("gender"));
				dto.setBornYear(rs.getInt("bornYear"));
				dto.setRegiDate(rs.getTimestamp("regiDate"));
				dto.setPostcode(rs.getString("postcode"));
				dto.setAddress(rs.getString("address"));
				dto.setDetailAddress(rs.getString("detailAddress"));
				dto.setExtraAddress(rs.getString("extraAddress"));
				dto.setEmail(rs.getString("email"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return list;
	}

	public int getTotalRecord(String search_option, String search_data) {
		int result = 0;
		conn = getConn();
		try {
			String sql = "select count(no) from " + tableName01 + " where no > 0 ";
			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					sql += " and " + search_option + " like ? ";
				} else if (search_option.equals("id_name_gender")) {
					sql += " and (id like ? or name like ? or gender like ?) ";
				}
			}
			int k = 0;
			pstmt = conn.prepareStatement(sql);

			if (search_option.length() > 0 && search_data.length() > 0) {
				if (search_option.equals("id") || search_option.equals("name") || search_option.equals("gender")) {
					pstmt.setString(++k, '%' + search_data + '%');
				} else if (search_option.equals("id_name_gender")) {
					pstmt.setString(++k, '%' + search_data + '%');
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
	
	public String getSelectBirthday() {
		String result = "";
		conn = getConn();
		try {
			String sql = "select email from member where bornyear = (select to_char(sysdate, 'MMDD') from dual)";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result += ","+rs.getString("email");
			}
			if (result!="") {
				result = result.substring(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return result;
	}

	public String getIdCheck(String id) {
		String result = "";
		conn = getConn();
		try {
			String sql = "select id from member where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
	
	public int setDel(int no, String passwd) {
		int result = 0;
		conn = getConn();
		try {
			String sql = "delete from member where no=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, passwd);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnClose(rs, pstmt, conn);
		}
		return result;
	}
}
