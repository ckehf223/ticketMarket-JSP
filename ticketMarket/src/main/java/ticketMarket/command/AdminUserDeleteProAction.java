package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CustomerDBBean;
import ticketMarket.process.CommandAction;

public class AdminUserDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		CustomerDBBean dbPro = CustomerDBBean.getInstance();
		dbPro.adminDeleteCustomer(userNo);
		return "/admin/adminUserDeletePro.jsp";
	}

}
