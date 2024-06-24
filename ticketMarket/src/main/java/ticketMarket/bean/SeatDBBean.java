package ticketMarket.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbcon.DBUtil;

public class SeatDBBean {
	private static SeatDBBean instance = null;

	private SeatDBBean() {
	}

	public static SeatDBBean getInstance() {
		if (instance == null) {
			synchronized (SeatDBBean.class) {
				instance = new SeatDBBean();
			}
		}
		return instance;
	}

	// 공연 예매된 좌석 가져오기
	public String getSeats(String pf_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sf = new StringBuffer();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from cart where pf_id=?");
			pstmt.setString(1, pf_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sf.append(rs.getString("seat_location")+",");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return sf.toString();
	}
}
