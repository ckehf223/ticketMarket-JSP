package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.process.CommandAction;

public class AdminLoginAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/adminLogin.jsp";
	}

}
