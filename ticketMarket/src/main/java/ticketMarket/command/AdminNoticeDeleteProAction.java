package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.NoticeDBBean;
import ticketMarket.process.CommandAction;

public class AdminNoticeDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		dbPro.deleteNotice(num);
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/adminNoticeDeletePro.jsp";
	}

}
