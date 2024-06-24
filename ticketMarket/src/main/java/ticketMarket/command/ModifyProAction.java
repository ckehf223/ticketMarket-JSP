package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CustomerDBBean;
import ticketMarket.bean.CustomerDataBean;
import ticketMarket.process.CommandAction;

public class ModifyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = (String)request.getSession().getAttribute("loginId");
		CustomerDataBean member = new CustomerDataBean();
		CustomerDBBean dbPro = CustomerDBBean.getInstance();
		member.setCt_id(id);
		member.setCt_pw(request.getParameter("userPw"));
		member.setCt_email(request.getParameter("userEmail"));
		member.setCt_address(request.getParameter("userAddress"));
		member.setCt_phone(request.getParameter("userPhone"));
		dbPro.updateCustomer(member);
		
		return "/member/modifyPro.jsp";
	}

}
