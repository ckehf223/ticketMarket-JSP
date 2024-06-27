package ticketMarket.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dbcon.DBUtil;

public class PerformanceDBBean {
	private static PerformanceDBBean instance = null;

	private PerformanceDBBean() {
	}

	public static PerformanceDBBean getInstance() {
		if (instance == null) {
			synchronized (PerformanceDBBean.class) {
				instance = new PerformanceDBBean();
			}
		}
		return instance;
	}
	
	//공연정보 category별로 가져오기
	public ArrayList<PerformanceDataBean> getPerformanceList(String category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PerformanceDataBean> list = null;
		try {
			list = new ArrayList<>();
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from performance where pf_genre=? and pf_allowCheck = ?");
			pstmt.setString(1, category);
			pstmt.setInt(2, 1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PerformanceDataBean pd = new PerformanceDataBean();
				pd.setPf_no(rs.getInt("pf_no"));
				pd.setPf_id(rs.getString("pf_id"));
				pd.setPf_name(rs.getString("pf_name"));
				pd.setPf_genre(rs.getString("pf_genre"));
				pd.setPf_date(rs.getString("pf_date"));
				pd.setPf_venue(rs.getString("pf_venue"));
				pd.setPf_limitAge(rs.getInt("pf_limitage"));
				pd.setPf_totalSeats(rs.getInt("pf_totalseats"));
				pd.setPf_imageUrl(rs.getString("pf_imageurl"));
				pd.setPf_pageUrl(rs.getString("pf_pageurl"));
				pd.setPf_price(rs.getInt("pf_price"));
				list.add(pd);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//공연정보가져오기
	public PerformanceDataBean getPerformance(int no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PerformanceDataBean pd = null;
		try {
			pd = new PerformanceDataBean();
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from performance where pf_no=?");
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pd.setPf_no(rs.getInt("pf_no"));
				pd.setPf_id(rs.getString("pf_id"));
				pd.setPf_name(rs.getString("pf_name"));
				pd.setPf_genre(rs.getString("pf_genre"));
				pd.setPf_date(rs.getString("pf_date"));
				pd.setPf_venue(rs.getString("pf_venue"));
				pd.setPf_limitAge(rs.getInt("pf_limitage"));
				pd.setPf_totalSeats(rs.getInt("pf_totalseats"));
				pd.setPf_imageUrl(rs.getString("pf_imageurl"));
				pd.setPf_pageUrl(rs.getString("pf_pageurl"));
				pd.setPf_price(rs.getInt("pf_price"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return pd;
	}
	//관리자 모든 공연 리스트 가져오기
	public ArrayList<PerformanceDataBean> adminPerformanceList(){
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<PerformanceDataBean> list = null;
		try {
			list = new ArrayList<>();
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("get_pfList");
			cstmt.executeQuery();
			while(rs.next()) {
				PerformanceDataBean pd = new PerformanceDataBean();
				pd.setPf_no(rs.getInt("pf_no"));
				pd.setPf_id(rs.getString("pf_id"));
				pd.setPf_name(rs.getString("pf_name"));
				pd.setPf_genre(rs.getString("pf_genre"));
				pd.setPf_date(rs.getString("pf_date"));
				pd.setPf_venue(rs.getString("pf_venue"));
				pd.setPf_limitAge(rs.getInt("pf_limitage"));
				pd.setPf_totalSeats(rs.getInt("pf_totalseats"));
				pd.setPf_imageUrl(rs.getString("pf_imageurl"));
				pd.setPf_pageUrl(rs.getString("pf_pageurl"));
				pd.setPf_price(rs.getInt("pf_price"));
				list.add(pd);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, cstmt, conn);
		}
		return list;
	}
}
