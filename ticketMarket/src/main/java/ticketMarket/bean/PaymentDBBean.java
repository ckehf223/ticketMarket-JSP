package ticketMarket.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import dbcon.DBUtil;
import oracle.jdbc.OracleTypes;

public class PaymentDBBean {
	private static PaymentDBBean instance = null;

	private PaymentDBBean() {
	}

	public static PaymentDBBean getInstance() {
		if (instance == null) {
			synchronized (PaymentDBBean.class) {
				instance = new PaymentDBBean();
			}
		}
		return instance;
	}
	
	public void registerPaymentList(ArrayList<PaymentDataBean> payList) {
		Connection conn = null;
		CallableStatement cstmt = null;
		try {
			conn = DBUtil.getConnection();
			for(PaymentDataBean data : payList) {
				cstmt = conn.prepareCall("{Call add_payment(?,?,?,?,?,?,?,?,?,?)}");
				cstmt.setInt(1, data.getCart_no());
				cstmt.setString(2, data.getCt_id());
				cstmt.setString(3, data.getPf_id());
				cstmt.setInt(4, data.getCart_quantity());
				cstmt.setString(5, data.getSeat_location());
				cstmt.setInt(6, data.getCart_totalPrice());
				cstmt.setString(7, data.getDelivery_address());
				cstmt.setString(8, data.getDelivery_name());
				cstmt.setString(9, data.getDelivery_message());
				cstmt.setString(10, data.getDelivery_date());
				cstmt.executeUpdate();				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(cstmt, conn);
		}
	}
	
	//결제내역 가져오기
	public ArrayList<HashMap<String,String>> getPaymentList(String id){
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String,String>> mList = new ArrayList<HashMap<String,String>>();
		try {
			conn = DBUtil.getConnection();
			cstmt = conn.prepareCall("{ Call PAYMENT_LIST(?,?)}");
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			cstmt.executeQuery();
			rs = (ResultSet)cstmt.getObject(2);
			while(rs.next()) {
				HashMap<String,String> map = new HashMap<>();
				map.put("pf_id", rs.getString("pf_id"));
				map.put("pf_name", rs.getString("pf_name"));
				map.put("pf_venue", rs.getString("pf_venue"));
				map.put("pf_date", rs.getString("pf_date"));
				map.put("pf_genre", rs.getString("pf_genre"));
				map.put("pf_imageurl", rs.getString("pf_imageurl"));
				map.put("seat_location", rs.getString("seat_location"));
				map.put("cart_quantity",  String.valueOf(rs.getInt("cart_quantity")));
				map.put("cart_totalprice", String.valueOf(rs.getInt("cart_totalprice")));
				map.put("delivery_address", rs.getString("delivery_address"));
				map.put("delivery_name", rs.getString("delivery_name"));
				map.put("delivery_message", rs.getString("delivery_message"));
				map.put("delivery_date", rs.getString("delivery_date"));
				mList.add(map);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, cstmt, conn);
		}
		return mList;
	}
	

}
