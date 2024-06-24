package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CustomerDBBean;
import ticketMarket.process.CommandAction;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String id = (String)request.getSession().getAttribute("loginId");
		CustomerDBBean dbPro = CustomerDBBean.getInstance();
		int check = dbPro.deleteCustomer(id);
		request.setAttribute("check", check);
		return "/member/deletePro.jsp";
	}

}
