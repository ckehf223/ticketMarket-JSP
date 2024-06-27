package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.process.CommandAction;

public class AdminLogoutProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		return "/admin/adminLogoutPro.jsp";
	}

}
