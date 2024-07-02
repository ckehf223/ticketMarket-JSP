package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.process.CommandAction;

public class AdminMoveAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.getSession().removeAttribute("adminLoginId");
		return "/main/main.jsp";
	}

}
