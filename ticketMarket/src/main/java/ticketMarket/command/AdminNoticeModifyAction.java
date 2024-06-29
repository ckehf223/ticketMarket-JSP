package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.NoticeDBBean;
import ticketMarket.bean.NoticeDataBean;
import ticketMarket.process.CommandAction;

public class AdminNoticeModifyAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		NoticeDataBean notice = dbPro.adminGetNotice(num);

		request.setAttribute("num", num);
		request.setAttribute("notice", notice);
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/adminModifyNotice.jsp";
	}

}
