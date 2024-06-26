package ticketMarket.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dbcon.DBUtil;

public class CommentDBBean {
	private static CommentDBBean instance = null;

	private CommentDBBean() {
	}

	public static CommentDBBean getInstance() {
		if (instance == null) {
			synchronized (CommentDBBean.class) {
				instance = new CommentDBBean();
			}
		}
		return instance;
	}

	// 게시글 댓글 리스트 가져오기
	public ArrayList<CommentDataBean> getCommentList(int num) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd hh:mm");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CommentDataBean> commentList = null;
		try {
			commentList = new ArrayList<>();
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from notice_comment where n_num=? order by regdate desc");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentDataBean comment = new CommentDataBean();
				comment.setNo(rs.getInt("no"));
				comment.setN_num(rs.getInt("n_num"));
				comment.setId(rs.getString("id"));
				comment.setContent(rs.getString("content"));
				comment.setRegdate(sdf.format(rs.getTimestamp("regdate")));
				commentList.add(comment);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return commentList;
	}
	
	//댓글 삭제
	public void deleteComment(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from notice_comment where no=?");
			pstmt.setInt(1, num);
			pstmt.executeQuery();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
	}
	
	//댓글 추가
	public CommentDataBean registerComment(CommentDataBean comment) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd hh:mm");
		Date date = new Date();
		Connection conn = null;
		CallableStatement cstmt = null;
		int num = 0;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{CALL ADD_COMMENT(?,?,?,?)}");
			cstmt.setInt(1, comment.getN_num());
			cstmt.setString(2, comment.getId());
			cstmt.setString(3, comment.getContent());
			cstmt.registerOutParameter(4, Types.NUMERIC);
			cstmt.execute();
			num = cstmt.getInt(4);
			comment.setNo(num);
			comment.setRegdate(sdf.format(date));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
		return comment;
	}
}
