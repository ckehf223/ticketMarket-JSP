package ticketMarket.bean;

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
	//게시물 list 가져오기
	public ArrayList<NoticeDataBean> getNoticeList(int start, int end){
		SimpleDateFormat sdf = new SimpleDateFormat("YY.MM.dd hh:mm");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NoticeDataBean> noList = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from (select rownum rnum,no,title,content,regdate,hits from notice order by no desc) "
					+ "where rnum >=?  and rnum <=?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
	
	//게시글 지정 가져오기
	public NoticeDataBean getNotice(int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("YY.MM.dd hh:mm");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeDataBean notice = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from notice where no=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				notice = new NoticeDataBean();
				notice.setNo(rs.getInt("no"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(sdf.format(rs.getTimestamp("regdate")));
				notice.setHits(rs.getInt("hits"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return notice;
	}
}
