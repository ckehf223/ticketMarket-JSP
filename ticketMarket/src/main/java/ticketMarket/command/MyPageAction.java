package ticketMarket.command;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ticketMarket.bean.CartDBBean;
import ticketMarket.bean.CustomerDBBean;
import ticketMarket.bean.CustomerDataBean;
import ticketMarket.bean.PaymentDBBean;
import ticketMarket.process.CommandAction;

public class MyPageAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginId");
		CustomerDBBean dbPro = CustomerDBBean.getInstance();
		CartDBBean  cartPro = CartDBBean.getInstance();
		PaymentDBBean payPro = PaymentDBBean.getInstance();
		CustomerDataBean member = dbPro.getCustomerInfo(id);
		int count = cartPro.checkCountCart(id);
		ArrayList<HashMap<String,String>> cartMap = cartPro.getCartList(id);			
		request.setAttribute("cartMap", cartMap);
		ArrayList<HashMap<String,String>> payMap = payPro.getPaymentList(id);
		request.setAttribute("payMap", payMap);
		request.setAttribute("member", member);
		request.setAttribute("count", count);
		return "/member/myPage.jsp";
	}

}
