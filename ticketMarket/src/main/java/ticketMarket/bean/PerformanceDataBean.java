package ticketMarket.bean;

public class PerformanceDataBean {
	private int pf_no;					// 번호
	private String pf_id;				// 공연아이디
	private String pf_name; 			// 공연명
	private String pf_genre; 			// 장르(뮤지컬, 연극, 콘서트)
	private String pf_date; 			// 공연일
	private String pf_venue; 			// 장소
	private int pf_limitAge;			// 관람제한연령
	private int pf_totalSeats;			// 총좌석수
	private String pf_imageUrl;			// 이미지url
	private String pf_pageUrl;			// 이동할 페이지 url
	private int pf_price;				// 티켓가격,compare
	private int pf_allowcheck;			// 판매여부
	private int pf_quantity;			// 판매수량
	public int getPf_no() {
		return pf_no;
	}
	public void setPf_no(int pf_no) {
		this.pf_no = pf_no;
	}
	public String getPf_id() {
		return pf_id;
	}
	public void setPf_id(String pf_id) {
		this.pf_id = pf_id;
	}
	public String getPf_name() {
		return pf_name;
	}
	public void setPf_name(String pf_name) {
		this.pf_name = pf_name;
	}
	public String getPf_genre() {
		return pf_genre;
	}
	public void setPf_genre(String pf_genre) {
		this.pf_genre = pf_genre;
	}
	public String getPf_date() {
		return pf_date;
	}
	public void setPf_date(String pf_date) {
		this.pf_date = pf_date;
	}
	public String getPf_venue() {
		return pf_venue;
	}
	public void setPf_venue(String pf_venue) {
		this.pf_venue = pf_venue;
	}
	public int getPf_limitAge() {
		return pf_limitAge;
	}
	public void setPf_limitAge(int pf_limitAge) {
		this.pf_limitAge = pf_limitAge;
	}
	public int getPf_totalSeats() {
		return pf_totalSeats;
	}
	public void setPf_totalSeats(int pf_totalSeats) {
		this.pf_totalSeats = pf_totalSeats;
	}
	public String getPf_imageUrl() {
		return pf_imageUrl;
	}
	public void setPf_imageUrl(String pf_imageUrl) {
		this.pf_imageUrl = pf_imageUrl;
	}
	public String getPf_pageUrl() {
		return pf_pageUrl;
	}
	public void setPf_pageUrl(String pf_pageUrl) {
		this.pf_pageUrl = pf_pageUrl;
	}
	public int getPf_price() {
		return pf_price;
	}
	public void setPf_price(int pf_price) {
		this.pf_price = pf_price;
	}
	public int getPf_allowcheck() {
		return pf_allowcheck;
	}
	public void setPf_allowcheck(int pf_allowcheck) {
		this.pf_allowcheck = pf_allowcheck;
	}
	public int getPf_quantity() {
		return pf_quantity;
	}
	public void setPf_quantity(int pf_quantity) {
		this.pf_quantity = pf_quantity;
	}
	
	
}
