package ticketMarket.bean;

public class CartDataBean {
	private int cart_no;			// 번호
	private String ct_id;			// 유저ID
	private String pf_id;			// 공연ID
	private int cart_quantity;		// 수량
	private int cart_totalPrice;	// 총구매금액
	private String seat_location;	// 좌석 정보
	private int payment_check;		// 결제여부
	
	public int getCart_no() {
		return cart_no;
	}
	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}
	public String getCt_id() {
		return ct_id;
	}
	public void setCt_id(String ct_id) {
		this.ct_id = ct_id;
	}
	public String getPf_id() {
		return pf_id;
	}
	public void setPf_id(String pf_id) {
		this.pf_id = pf_id;
	}
	public int getCart_quantity() {
		return cart_quantity;
	}
	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
	}
	public int getCart_totalPrice() {
		return cart_totalPrice;
	}
	public void setCart_totalPrice(int cart_totalPrice) {
		this.cart_totalPrice = cart_totalPrice;
	}
	public String getSeat_location() {
		return seat_location;
	}
	public void setSeat_location(String seat_location) {
		this.seat_location = seat_location;
	}
	public int getPayment_check() {
		return payment_check;
	}
	public void setPayment_check(int payment_check) {
		this.payment_check = payment_check;
	}
	
	
}
