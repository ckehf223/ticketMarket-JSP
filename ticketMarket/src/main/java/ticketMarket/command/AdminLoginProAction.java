package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.AdminDBBean;
import ticketMarket.process.CommandAction;

public class AdminLoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("adminId");
		String pw = request.getParameter("adminPw");
		AdminDBBean manager = AdminDBBean.getInstance();
		int check = manager.adminCheck(id, pw);
		if(check == 1) {
			request.getSession().setAttribute("adminLoginId", id);
		}
		request.setAttribute("type", Integer.valueOf(0));
		request.setAttribute("check", Integer.valueOf(check));
		return "/admin/adminLoginPro.jsp";
	}

}
