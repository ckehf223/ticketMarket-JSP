package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ticketMarket.bean.CustomerDBBean;
import ticketMarket.bean.CustomerDataBean;
import ticketMarket.process.CommandAction;

public class ModifyFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("loginId");
		CustomerDBBean dbPro = CustomerDBBean.getInstance();
		CustomerDataBean member = dbPro.getCustomerInfo(id);
		String fullBirth = member.getCt_birth();
		String[] address = member.getCt_address().split("/");
		String[] email = member.getCt_email().split("@");
		member.setCt_birth(fullBirth.substring(0,4)+"년"+fullBirth.substring(4,6)+"월"+fullBirth.substring(6,8)+"일");
		request.setAttribute("address1", address[0]);
		request.setAttribute("address2", address[1]);
		request.setAttribute("postal", address[2]);
		request.setAttribute("member", member);
		request.setAttribute("email", email[0]);
		request.setAttribute("domain", email[1]);
		return "/member/modifyForm.jsp";
	}

}
