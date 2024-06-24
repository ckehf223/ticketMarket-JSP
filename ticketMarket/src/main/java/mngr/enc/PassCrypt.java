package mngr.enc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbcon.DBUtil;
import work.crypt.BCrypt;
import work.crypt.SHA256;

//관리자용 암호화를 만들어주는 클래스 (단, 한번만 실행해야한다)
public class PassCrypt {
	//싱글톤 방식
	private static PassCrypt instance = null;

	public static PassCrypt getInstance() {
		if(instance == null) {
			synchronized (PassCrypt.class) {
				instance = new PassCrypt();
			}
		}
		return instance;
	}

	private PassCrypt() {
	}

	public void cryptProcess() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 1.SHA-256 를 사용하는 SHA256 클래스의 객체를 얻어낸다.
		SHA256 sha = SHA256.getInsatnce();

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select managerId, managerPasswd from manager");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("managerId");
				String orgPass = rs.getString("managerPasswd");
				// 2.패스워드를 암호화처리를 진행한다.
				String shaPass = sha.getSha256(orgPass.getBytes());
				String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());

				pstmt = conn.prepareStatement("update manager set managerPasswd=? where managerId=?");
				pstmt.setString(1, bcPass);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
	}

}
