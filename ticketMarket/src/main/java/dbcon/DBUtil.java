package dbcon;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtil {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/myOracle");
			conn = ds.getConnection();
		}catch(Exception e) {
			System.err.println("Connection 생성실패");
		}
		System.out.println("Connection 생성성공");
		return conn;
	}
	
	public static void dbReleaseClose(ResultSet rs, PreparedStatement pstmt,Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			rs = null;
		}
		try {
			if(pstmt != null) {
				pstmt.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			pstmt = null;
		}
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			conn = null;
		}
	}
	
	public static void dbReleaseClose(PreparedStatement pstmt,Connection conn) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			pstmt = null;
		}
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			conn = null;
		}
	}
	
	public static void dbReleaseClose(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			conn = null;
		}
	}
	public static void dbReleaseClose(CallableStatement cstmt,Connection conn) {
		try {
			if(cstmt != null) {
				cstmt.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			cstmt = null;
		}
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			conn = null;
		}
	}
	
	public static void dbReleaseClose(ResultSet rs, CallableStatement cstmt,Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			rs = null;
		}
		try {
			if(cstmt != null) {
				cstmt.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			cstmt = null;
		}
		try {
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			conn = null;
		}
	}
	
	
}
