package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CustomerDBBean;
import ticketMarket.process.CommandAction;

public class LoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		CustomerDBBean manager = CustomerDBBean.getInstance();
		int check = manager.userCheck(id, pw);
		request.setAttribute("id", id);
		request.setAttribute("check", Integer.valueOf(check));
		return "/member/loginPro.jsp";
	}

}
