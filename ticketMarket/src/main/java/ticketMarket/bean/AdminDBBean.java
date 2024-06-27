package ticketMarket.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import dbcon.DBUtil;
import oracle.jdbc.OracleTypes;
import work.crypt.BCrypt;
import work.crypt.SHA256;

public class AdminDBBean {
	private static AdminDBBean instance = null;

	private AdminDBBean() {
	}

	public static AdminDBBean getInstance() {
		if (instance == null) {
			synchronized (AdminDBBean.class) {
				instance = new AdminDBBean();
			}
		}
		return instance;
	}
	
	// 관리자 인증 메소드
	public int adminCheck(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = DBUtil.getConnection();
			String orgPass = passwd;
			String shaPass = sha.getSha256(orgPass.getBytes());

			pstmt = conn.prepareStatement("select pw from admin where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 해당 아이디가 있으면 수행
				String dbpasswd = rs.getString("pw");
				if (BCrypt.checkpw(shaPass, dbpasswd))
					x = 1; // 인증성공
				else
					x = 0; // 비밀번호 틀림
			}
			//해당 아이디가 없으면 x = -1
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}
	
	public ArrayList<HashMap<String,String>> getTopRankPerformance(){
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{CALL get_rankPf_proc(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet)cstmt.getObject(1);
			while(rs.next()) {
				HashMap<String,String> map = new HashMap<String, String>();
				map.put("pf_name", rs.getString("pf_name"));
				map.put("pf_imageurl", rs.getString("pf_imageurl"));
				list.add(map);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, cstmt, conn);
		}
		return list;
	}

}
