package ticketMarket.bean;

public class CustomerDataBean {
	private int ct_no;					// 번호
	private String ct_id; 				// 아이디
	private String ct_pw; 				// 비밀번호
	private String ct_name; 			// 이름
	private int ct_age;					// 나이
	private String ct_birth;			// 생년월일
	private String ct_email;
	private String ct_phone; 			// 전화번호
	private String ct_address; 			// 주소
	private String ct_grade; 			// 등급
	private double ct_saleRatio;	    // 할인율
	private int ct_totalamount; 		// 누적금액
	private int ct_mileage;				// 적립마일리지
	private double ct_mileageSale;  	// 마일리지 할인율
	
	public int getCt_no() {
		return ct_no;
	}
	public void setCt_no(int ct_no) {
		this.ct_no = ct_no;
	}
	public String getCt_id() {
		return ct_id;
	}
	public void setCt_id(String ct_id) {
		this.ct_id = ct_id;
	}
	public String getCt_pw() {
		return ct_pw;
	}
	public void setCt_pw(String ct_pw) {
		this.ct_pw = ct_pw;
	}
	public String getCt_name() {
		return ct_name;
	}
	public void setCt_name(String ct_name) {
		this.ct_name = ct_name;
	}
	public int getCt_age() {
		return ct_age;
	}
	public void setCt_age(int ct_age) {
		this.ct_age = ct_age;
	}
	public String getCt_phone() {
		return ct_phone;
	}
	public void setCt_phone(String ct_phone) {
		this.ct_phone = ct_phone;
	}
	public String getCt_address() {
		return ct_address;
	}
	public void setCt_address(String ct_address) {
		this.ct_address = ct_address;
	}
	public String getCt_grade() {
		return ct_grade;
	}
	public void setCt_grade(String ct_grade) {
		this.ct_grade = ct_grade;
	}
	public double getCt_saleRatio() {
		return ct_saleRatio;
	}
	public void setCt_saleRatio(double ct_saleRatio) {
		this.ct_saleRatio = ct_saleRatio;
	}
	public int getCt_totalamount() {
		return ct_totalamount;
	}
	public void setCt_totalamount(int ct_totalamount) {
		this.ct_totalamount = ct_totalamount;
	}
	public int getCt_mileage() {
		return ct_mileage;
	}
	public void setCt_mileage(int ct_mileage) {
		this.ct_mileage = ct_mileage;
	}
	public double getCt_mileageSale() {
		return ct_mileageSale;
	}
	public void setCt_mileageSale(double ct_mileageSale) {
		this.ct_mileageSale = ct_mileageSale;
	}
	public String getCt_birth() {
		return ct_birth;
	}
	public void setCt_birth(String ct_birth) {
		this.ct_birth = ct_birth;
	}
	public String getCt_email() {
		return ct_email;
	}
	public void setCt_email(String ct_email) {
		this.ct_email = ct_email;
	}
	
}
