package ticketMarket.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import dbcon.DBUtil;
import oracle.jdbc.OracleTypes;

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

	// 공연정보 category별로 가져오기
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
			while (rs.next()) {
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
	
	//검색한 공연 정보가져오기
	public ArrayList<PerformanceDataBean> getSearchPerformanceList(String text) {
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<PerformanceDataBean> list = null;
		try {
			list = new ArrayList<>();
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call search_pfList(?,?)}");
			cstmt.setString(1, text);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet) cstmt.getObject(2);
			while (rs.next()) {
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

	// 공연정보가져오기
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
			while (rs.next()) {
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
				pd.setPf_allowcheck(rs.getInt("pf_allowcheck"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return pd;
	}

	// 공연정보 가져오기 아이디로
	public PerformanceDataBean getPerformanceId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PerformanceDataBean pd = null;
		try {
			pd = new PerformanceDataBean();
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from performance where pf_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
				pd.setPf_allowcheck(rs.getInt("pf_allowcheck"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return pd;
	}

	// 관리자 모든 공연 리스트 가져오기
	public ArrayList<PerformanceDataBean> adminPerformanceList() {
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<PerformanceDataBean> list = null;
		try {
			list = new ArrayList<>();
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call get_pfList(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet) cstmt.getObject(1);
			while (rs.next()) {
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
				pd.setPf_price(rs.getInt("pf_price"));
				pd.setPf_allowcheck(rs.getInt("pf_allowcheck"));
				pd.setPf_quantity(rs.getInt("quantity"));
				list.add(pd);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, cstmt, conn);
		}
		return list;
	}

	// 공연정보 수정
	public void updatePerformance(PerformanceDataBean pData) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call PF_UPDATE_PROC(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, pData.getPf_id());
			cstmt.setString(2, pData.getPf_name());
			cstmt.setString(3, pData.getPf_genre());
			cstmt.setString(4, pData.getPf_date());
			cstmt.setString(5, pData.getPf_venue());
			cstmt.setInt(6, pData.getPf_limitAge());
			cstmt.setInt(7, pData.getPf_totalSeats());
			cstmt.setInt(8, pData.getPf_price());
			cstmt.setString(9, pData.getPf_imageUrl());
			cstmt.setInt(10, pData.getPf_allowcheck());
			cstmt.executeQuery();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}

	// 공연등록
	public void registerPerformance(PerformanceDataBean pData) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call PF_ADD_PROC(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, pData.getPf_id());
			cstmt.setString(2, pData.getPf_name());
			cstmt.setString(3, pData.getPf_genre());
			cstmt.setString(4, pData.getPf_date());
			cstmt.setString(5, pData.getPf_venue());
			cstmt.setInt(6, pData.getPf_limitAge());
			cstmt.setInt(7, pData.getPf_totalSeats());
			cstmt.setInt(8, pData.getPf_price());
			cstmt.setString(9, pData.getPf_imageUrl());
			cstmt.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
	
	//공연 삭제
	public void deletePerformance(int no) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call PF_DELETE_PROC(?)}");
			cstmt.setInt(1, no);
			cstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
	
	//예매내역 가져오기
		public ArrayList<HashMap<String,String>> getRankTopList(){
			Connection conn = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
			try {
				conn = DBUtil.getConnection();
				cstmt = conn.prepareCall("{ Call getRank_topTenProc(?)}");
				cstmt.registerOutParameter(1, OracleTypes.CURSOR);
				cstmt.executeQuery();
				rs = (ResultSet)cstmt.getObject(1);
				while(rs.next()) {
					HashMap<String,String> map = new HashMap<>();
					map.put("pf_no", rs.getString("pf_no"));
					map.put("pf_name", rs.getString("pf_name"));
					map.put("pf_venue", rs.getString("pf_venue"));
					map.put("pf_date", rs.getString("pf_date"));
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
