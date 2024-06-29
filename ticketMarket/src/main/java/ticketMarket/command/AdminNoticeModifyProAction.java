package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.NoticeDBBean;
import ticketMarket.process.CommandAction;

public class AdminNoticeModifyProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		dbPro.modifyNotice(num, title, content);
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/adminModifyNoticePro.jsp";
	}

}
