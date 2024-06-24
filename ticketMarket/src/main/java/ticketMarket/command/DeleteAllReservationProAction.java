package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CartDBBean;
import ticketMarket.process.CommandAction;

public class DeleteAllReservationProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = (String)request.getSession().getAttribute("loginId");
		CartDBBean dbPro = CartDBBean.getInstance();
		dbPro.deleteAllCart(id);
		return "/performance/deleteReservationPro.jsp";
	}

}
