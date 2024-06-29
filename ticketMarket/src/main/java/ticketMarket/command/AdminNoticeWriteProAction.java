package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.NoticeDBBean;
import ticketMarket.process.CommandAction;

public class AdminNoticeWriteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		dbPro.registerNotice(title, content);
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/adminNoticeWriterPro.jsp";
	}

}
