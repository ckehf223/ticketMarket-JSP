package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CartDBBean;
import ticketMarket.bean.CartDataBean;
import ticketMarket.process.CommandAction;

public class ReservationProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = (String)request.getSession().getAttribute("loginId");
		String seats = request.getParameter("values");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int price = Integer.parseInt(request.getParameter("pf_price")) * quantity;
		CartDBBean dbPro = CartDBBean.getInstance();
		CartDataBean cdb = new CartDataBean();
		cdb.setCt_id(id);
		cdb.setPf_id(request.getParameter("pf_id"));
		cdb.setCart_quantity(quantity);
		cdb.setCart_totalPrice(price);
		cdb.setSeat_location(seats);
		dbPro.registerCart(cdb);
		
		return "/performance/reservationPro.jsp";
	}

}
