package ticketMarket.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dbcon.DBUtil;

public class NoticeDBBean {
	private static NoticeDBBean instance = null;

	private NoticeDBBean() {
	}

	public static NoticeDBBean getInstance() {
		if (instance == null) {
			synchronized (NoticeDBBean.class) {
				instance = new NoticeDBBean();
			}
		}
		return instance;
	}

	// 게시물 list 가져오기
	public ArrayList<NoticeDataBean> getNoticeList(int start, int end) {
		SimpleDateFormat sdf = new SimpleDateFormat("YY.MM.dd hh:mm");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NoticeDataBean> noList = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(
					"select * from (select rownum rnum,no,title,content,regdate,hits from notice order by no desc) "
							+ "where rnum >=?  and rnum <=?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				NoticeDataBean notice = new NoticeDataBean();
				notice.setNo(rs.getInt("no"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(sdf.format(rs.getTimestamp("regdate")));
				notice.setHits(rs.getInt("hits"));
				noList.add(notice);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return noList;
	}

	// 게시글 지정 가져오기
	public NoticeDataBean getNotice(int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("YY.MM.dd hh:mm");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDataBean notice = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("update notice set hits=hits+1 where no = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt = conn.prepareStatement("select * from notice where no=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				notice = new NoticeDataBean();
				notice.setNo(rs.getInt("no"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(sdf.format(rs.getTimestamp("regdate")));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return notice;
	}

	// 게시글 추가하기
	public void registerNotice(String title, String content) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call ADD_NOTICE_PROC(?,?) }");
			cstmt.setString(1, title);
			cstmt.setString(2, content);
			cstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}

	// 수정할 게시글 가져오기
	public NoticeDataBean adminGetNotice(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDataBean notice = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from notice where no=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				notice = new NoticeDataBean();
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return notice;
	}
	
	//게시글 수정하기
	public void modifyNotice(int num,String title,String content) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call UPDATE_NOTICE_PROC(?,?,?)}");
			cstmt.setInt(1, num);
			cstmt.setString(2, title);
			cstmt.setString(3, content);
			cstmt.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
	
	//게시글 삭제하기
	public void deleteNotice(int num) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call DELETE_NOTICE_PROC(?)}");
			cstmt.setInt(1, num);
			cstmt.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
}
