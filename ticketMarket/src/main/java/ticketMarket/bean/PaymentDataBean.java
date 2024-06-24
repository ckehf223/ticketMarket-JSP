package ticketMarket.bean;

public class PaymentDataBean {
	private int payment_no;
	private int cart_no;
	private String ct_id;
	private String pf_id;
	private int cart_quantity;
	private String seat_location;
	private int cart_totalPrice;
	private String delivery_address;
	private String delivery_name;
	private String delivery_message;
	private String delivery_date;
	
	public int getCart_totalPrice() {
		return cart_totalPrice;
	}
	public void setCart_totalPrice(int cart_totalPrice) {
		this.cart_totalPrice = cart_totalPrice;
	}
	
	public int getPayment_no() {
		return payment_no;
	}
	public void setPayment_no(int payment_no) {
		this.payment_no = payment_no;
	}
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
	public String getSeat_location() {
		return seat_location;
	}
	public void setSeat_location(String seat_location) {
		this.seat_location = seat_location;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public String getDelivery_name() {
		return delivery_name;
	}
	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}
	public String getDelivery_message() {
		return delivery_message;
	}
	public void setDelivery_message(String delivery_message) {
		this.delivery_message = delivery_message;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	
	
}
