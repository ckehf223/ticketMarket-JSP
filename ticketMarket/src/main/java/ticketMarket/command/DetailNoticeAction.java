package ticketMarket.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CommentDBBean;
import ticketMarket.bean.CommentDataBean;
import ticketMarket.bean.NoticeDBBean;
import ticketMarket.bean.NoticeDataBean;
import ticketMarket.process.CommandAction;

public class DetailNoticeAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		CommentDBBean dbProC = CommentDBBean.getInstance();
		NoticeDataBean notice = dbPro.getNotice(num);
		ArrayList<CommentDataBean> list = dbProC.getCommentList(num);
		
		request.setAttribute("commentList", list);
		request.setAttribute("notice", notice);
		request.setAttribute("pageNum", pageNum);
		
		return "/notice/detailNotice.jsp";
	}

}
