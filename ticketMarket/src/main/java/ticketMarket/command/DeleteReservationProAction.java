package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CartDBBean;
import ticketMarket.process.CommandAction;

public class DeleteReservationProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int orderNum = Integer.parseInt(request.getParameter("orderNum"));
		CartDBBean dbPro = CartDBBean.getInstance();
		dbPro.deleteCart(orderNum);
		return "/performance/deleteReservationPro.jsp";
	}

}
