package ticketMarket.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbcon.DBUtil;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class CustomerDBBean {
	private static CustomerDBBean instance = null;

	private CustomerDBBean() {
	}

	public static CustomerDBBean getInstance() {
		if (instance == null) {
			synchronized (CustomerDBBean.class) {
				instance = new CustomerDBBean();
			}
		}
		return instance;
	}

	// 로그인 폼 처리의 사용자 인증 처리에서 사용하는 메소드
	public int userCheck(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try {
			conn = DBUtil.getConnection();
			// 2. 암호를 가져와서 비교하는 방법
			pstmt = conn.prepareStatement("select ct_pw from customer where ct_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			// 해당 아이디가 있으면 수행
			if (rs.next()) {
				if (passwd.equals(rs.getString("ct_pw")))
					x = 1; // 인증성공
				else
					x = 0; // 비밀번호 틀림
			} else// 해당 아이디 없으면 수행
				x = -1;// 아이디 없음
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 회원 가입
	public void insertMember(CustomerDataBean member) {
		Connection conn = null;
		CallableStatement cstmt = null;

		// 1. SHA256 객체를 가져온다.
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{ Call ct_signin_proc(?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, member.getCt_id());
			cstmt.setString(2, member.getCt_pw());
			cstmt.setString(3, member.getCt_name());
			cstmt.setInt(4, member.getCt_age());
			cstmt.setString(5, member.getCt_birth());
			cstmt.setString(6, member.getCt_email());
			cstmt.setString(7, member.getCt_phone());
			cstmt.setString(8, member.getCt_address());
			cstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}

	// 아이디 중복체크
	public int confirmId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select ct_id from customer where ct_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())// 아이디 존재
				x = 1; // 같은 아이디 있음
			else
				x = -1;// 같은 아이디 없음
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 회원정보 가져오기
	public CustomerDataBean getCustomerInfo(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerDataBean member = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from customer where ct_id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new CustomerDataBean();
				member.setCt_no(rs.getInt("ct_no"));
				member.setCt_id(rs.getString("ct_id"));
				member.setCt_pw(rs.getString("ct_pw"));
				member.setCt_name(rs.getString("ct_name"));
				member.setCt_age(rs.getInt("ct_age"));
				member.setCt_email(rs.getString("ct_email"));
				member.setCt_birth(rs.getString("ct_birth"));
				member.setCt_email(rs.getString("ct_email"));
				member.setCt_phone(rs.getString("ct_phone"));
				member.setCt_address(rs.getString("ct_address").replaceAll("/", "  "));
				member.setCt_grade(rs.getString("ct_grade"));
				member.setCt_mileage(rs.getInt("ct_mileage"));
				member.setCt_mileageSale(rs.getDouble("ct_mileageratio"));
				member.setCt_saleRatio(rs.getDouble("ct_saleratio"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return member;
	}
	
	//회원정보 수정하기
	public void updateCustomer(CustomerDataBean member) {
		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{ Call ct_update_proc(?,?,?,?,?)}");
			cstmt.setString(1, member.getCt_pw());
			cstmt.setString(2, member.getCt_email());
			cstmt.setString(3, member.getCt_phone());
			cstmt.setString(4, member.getCt_address());
			cstmt.setString(5, member.getCt_id());
			cstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
	
	//회원 탈퇴
	public int deleteCustomer(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int x = -1;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from customer where ct_id = ?");
			pstmt.setString(1, id);
			x = pstmt.executeUpdate();
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return x;
	}
	
	//회원 나이 가져오기
	public int getCustomerAge(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int age = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select ct_age from customer where ct_id= ? ");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				age = rs.getInt("ct_age");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return age;
	}
	
	//결제후 회원 등급,누적금액,할인룰 update
	public void updateCustomrAccount(CustomerDataBean member) {
		Connection conn = null;
		CallableStatement cstmt = null;

		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{ Call cart_payment_customer_proc(?,?,?,?,?,?)}");
			cstmt.setString(1, member.getCt_grade());
			cstmt.setDouble(2, member.getCt_saleRatio());
			cstmt.setInt(3, member.getCt_totalamount());
			cstmt.setInt(4, member.getCt_mileage());
			cstmt.setDouble(5, member.getCt_mileageSale());
			cstmt.setString(6, member.getCt_id());
			cstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
}
