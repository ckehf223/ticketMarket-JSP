package ticketMarket.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CartDBBean;
import ticketMarket.bean.CustomerDBBean;
import ticketMarket.bean.CustomerDataBean;
import ticketMarket.process.CommandAction;

public class PaymentCartAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = (String)request.getSession().getAttribute("loginId");
		//배송 날짜 만들기
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
		String[] arr = sdf.format(date).split("/");
		int dayIndex = Integer.parseInt(arr[2]) + ((int)(Math.random()*4)+1);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(arr[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(arr[1])-1);		
		cal.set(Calendar.DAY_OF_MONTH, dayIndex);
		int dayNameIndex = cal.get(Calendar.DAY_OF_WEEK);
		String[] day_name = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};
		String day = arr[1]+"/"+String.valueOf(dayIndex) +" "+day_name[dayNameIndex-1];
		
		CustomerDBBean dbPro = CustomerDBBean.getInstance();
		CartDBBean cartPro = CartDBBean.getInstance();
		CustomerDataBean member = dbPro.getCustomerInfo(id);
		ArrayList<HashMap<String,String>> cartMap = cartPro.getCartList(id);
		int count = cartPro.checkCountCart(id);
		int totalPrice = cartPro.getTotalPriceCart(id);
		
		int sale = (int) (totalPrice * member.getCt_saleRatio());
		int point = (int) (totalPrice * member.getCt_mileageSale());
		int realPrice = totalPrice - sale;
		request.setAttribute("member", member);
		request.setAttribute("cartMap", cartMap);
		request.setAttribute("count", count);
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("sale", sale);
		request.setAttribute("point", point);
		request.setAttribute("realPrice", realPrice);
		request.setAttribute("date", day);
		return "/member/paymentCart.jsp";
	}

}
