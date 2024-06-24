package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CustomerDBBean;
import ticketMarket.process.CommandAction;

public class ConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("userId");
		// 주어진 id 의 중복여부를 체크해 값을 반환.
		CustomerDBBean manager = CustomerDBBean.getInstance();
		int check = manager.confirmId(id);
		request.setAttribute("check", Integer.valueOf(check));
		return "/member/confirmId.jsp";
	}

}
