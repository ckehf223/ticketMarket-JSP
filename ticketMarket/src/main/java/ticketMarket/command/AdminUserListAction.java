package ticketMarket.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CustomerDBBean;
import ticketMarket.bean.CustomerDataBean;
import ticketMarket.process.CommandAction;

public class AdminUserListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		CustomerDBBean dbPro = CustomerDBBean.getInstance();
		ArrayList<CustomerDataBean> list = dbPro.getUserList();
		request.setAttribute("type", Integer.valueOf(0));
		request.setAttribute("list", list);
		return "/admin/adminUserList.jsp";
	}

}
