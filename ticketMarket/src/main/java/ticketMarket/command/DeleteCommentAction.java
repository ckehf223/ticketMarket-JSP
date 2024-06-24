package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CommentDBBean;
import ticketMarket.process.CommandAction;

public class DeleteCommentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		CommentDBBean dbPro = CommentDBBean.getInstance();
		dbPro.deleteComment(num);
		
		return "/notice/detailNotice.jsp";
	}

}
