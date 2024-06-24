package ticketMarket.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dbcon.DBUtil;
import oracle.jdbc.OracleTypes;

public class CartDBBean {
	private static CartDBBean instance = null;

	private CartDBBean() {
	}

	public static CartDBBean getInstance() {
		if (instance == null) {
			synchronized (CartDBBean.class) {
				instance = new CartDBBean();
			}
		}
		return instance;
	}
	
	//예매내역 가져오기
	public ArrayList<HashMap<String,String>> getCartList(String id){
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String,String>> mList = new ArrayList<HashMap<String,String>>();
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{ Call CART_LIST(?,?)}");
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet)cstmt.getObject(2);
			while(rs.next()) {
				HashMap<String,String> map = new HashMap<>();
				map.put("pf_no", rs.getString("pf_no"));
				map.put("pf_id", rs.getString("pf_id"));
				map.put("pf_name", rs.getString("pf_name"));
				map.put("pf_venue", rs.getString("pf_venue"));
				map.put("pf_date", rs.getString("pf_date"));
				map.put("pf_genre", rs.getString("pf_genre"));
				map.put("pf_imageurl", rs.getString("pf_imageurl"));
				map.put("seat_location", rs.getString("seat_location"));
				map.put("cart_no",  String.valueOf(rs.getInt("cart_no")));
				map.put("cart_quantity",  String.valueOf(rs.getInt("cart_quantity")));
				map.put("cart_totalprice", String.valueOf(rs.getInt("cart_totalprice")));
				mList.add(map);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, cstmt, conn);
		}
		return mList;
	}
	
	//예매내역 존재여부
	public int checkCountCart(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) as cnt from cart where ct_id=? and payment_check = ?");
			pstmt.setString(1, id);
			pstmt.setInt(2, 0);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt("cnt");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}
	
	//결제내역 존재여부
	public int checkCountPayment(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) as cnt from cart where ct_id=? and payment_check = 1");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt("cnt");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}
	
	//공연 예매하기
	public void registerCart(CartDataBean cdb) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call cart_add_proc(?,?,?,?,?)}");
			cstmt.setString(1, cdb.getCt_id());
			cstmt.setString(2, cdb.getPf_id());
			cstmt.setInt(3, cdb.getCart_quantity());
			cstmt.setInt(4, cdb.getCart_totalPrice());
			cstmt.setString(5, cdb.getSeat_location());
			cstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
	
	//지정 공연 삭제
	public void deleteCart(int orderNum) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call CART_DELETEONE_PROC(?)}");
			cstmt.setInt(1, orderNum);
			cstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
	
	//예매내역 삭제하기
	public void deleteAllCart(String id) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call cart_delete_proc(?)}");
			cstmt.setString(1, id);
			cstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
	//결제 전 총 상품 가격 가져오기
	public int getTotalPriceCart(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalPrice = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select sum(cart_totalprice) as price from cart where ct_id=? and payment_check=? ");
			pstmt.setString(1, id);
			pstmt.setInt(2, 0);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalPrice = rs.getInt("price");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return totalPrice;
	}
	
	//결제 안된 내역 가져오기
	public ArrayList<CartDataBean> getNoPaymentList(String id){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CartDataBean> cartList = null;
		try {
			cartList = new ArrayList<CartDataBean>();
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select * from cart where ct_id=? and payment_check=?");
			pstmt.setString(1, id);
			pstmt.setInt(2, 0);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CartDataBean cart = new CartDataBean();
				cart.setCart_no(rs.getInt("cart_no"));
				cart.setCt_id(rs.getString("ct_id"));
				cart.setPf_id(rs.getString("pf_id"));
				cart.setCart_quantity(rs.getInt("cart_quantity"));
				cart.setCart_totalPrice(rs.getInt("cart_totalprice"));
				cart.setSeat_location(rs.getString("seat_location"));
				cartList.add(cart);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return cartList;
	}
	
	//결제하기
	public void updatePayment_check(String id) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{Call cart_payment_proc(?)}");
			cstmt.setString(1, id);
			cstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
	
}
