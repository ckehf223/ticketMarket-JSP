package ticketMarket.command;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CartDBBean;
import ticketMarket.bean.CartDataBean;
import ticketMarket.bean.CustomerDBBean;
import ticketMarket.bean.CustomerDataBean;
import ticketMarket.bean.PaymentDBBean;
import ticketMarket.bean.PaymentDataBean;
import ticketMarket.process.CommandAction;

public class PaymentCartProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = (String)request.getSession().getAttribute("loginId");
		String name = request.getParameter("name");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String address = addr1 +" "+addr2;
		String message = request.getParameter("message");
		String date = request.getParameter("date");
		int savePoint = Integer.parseInt(request.getParameter("savePoint"));
		int usePoint = 0;
		if(request.getParameter("usePoint") == null || request.getParameter("usePoint").equals("0")) {
			usePoint = 0;
		}else {
			usePoint = Integer.parseInt(request.getParameter("usePoint"));			
		}
		int finalPrice = Integer.parseInt(request.getParameter("changePrice"));
		String totalPrice = request.getParameter("totalPrice");
		String salesPrice = request.getParameter("salesPrice");
		
		CartDBBean cartPro = CartDBBean.getInstance();
		ArrayList<HashMap<String,String>> cartMap = cartPro.getCartList(id);			
		ArrayList<CartDataBean> cartList = cartPro.getNoPaymentList(id);
		ArrayList<PaymentDataBean> payList = new ArrayList<PaymentDataBean>();
		for(CartDataBean data : cartList) {
			PaymentDataBean pay = new PaymentDataBean();
			pay.setCart_no(data.getCart_no());
			pay.setCt_id(data.getCt_id());
			pay.setPf_id(data.getPf_id());
			pay.setCart_quantity(data.getCart_quantity());
			pay.setSeat_location(data.getSeat_location());
			pay.setCart_totalPrice(data.getCart_totalPrice());
			pay.setDelivery_name(name);
			pay.setDelivery_address(address);
			pay.setDelivery_message(message);
			pay.setDelivery_date(date);
			payList.add(pay);
		}
		CustomerDBBean cusPro = CustomerDBBean.getInstance();
		CustomerDataBean member = cusPro.getCustomerInfo(id);
		member.setCt_mileage(member.getCt_mileage() + (savePoint - usePoint));
		member.setCt_totalamount(member.getCt_totalamount() + finalPrice);
		if(member.getCt_totalamount() >= 2_000_000) {
			member.setCt_grade("VIP");
			member.setCt_mileageSale(0.03);
			member.setCt_saleRatio(0.09);
		}else if(member.getCt_totalamount() > 1_000_000 && member.getCt_totalamount() < 2_000_000) {
			member.setCt_grade("GOLD");
			member.setCt_mileageSale(0.02);
			member.setCt_saleRatio(0.06);
		}
		cusPro.updateCustomrAccount(member);
		cartPro.updatePayment_check(id);
		PaymentDBBean payPro = PaymentDBBean.getInstance();
		payPro.registerPaymentList(payList);
		int count = cartMap.size();
		
		request.setAttribute("totalPrice", totalPrice);
		request.setAttribute("salesPrice", salesPrice);
		request.setAttribute("count", count);
		request.setAttribute("name", name);
		request.setAttribute("address", address);
		request.setAttribute("message", message);
		request.setAttribute("date", date);
		request.setAttribute("savePoint", savePoint);
		request.setAttribute("usePoint", usePoint);
		request.setAttribute("finalPrice", finalPrice);
		request.setAttribute("cartMap", cartMap);
		
		return "/member/completion.jsp";
	}

}
