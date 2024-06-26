package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CommentDBBean;
import ticketMarket.bean.CommentDataBean;
import ticketMarket.process.CommandAction;

public class AddCommentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		int num = Integer.parseInt(request.getParameter("num"));
		CommentDBBean dbPro = CommentDBBean.getInstance();
		CommentDataBean comment = new CommentDataBean();
		comment.setN_num(num);
		comment.setId(writer);
		comment.setContent(content);
		CommentDataBean nComment = dbPro.registerComment(comment);
		StringBuilder jsonResponse = new StringBuilder();
	   
		jsonResponse.append("{");
	    jsonResponse.append("\"no\":").append(nComment.getNo()).append(",");
	    jsonResponse.append("\"id\":\"").append(nComment.getId()).append("\",");
	    jsonResponse.append("\"content\":\"").append(nComment.getContent()).append("\",");
	    jsonResponse.append("\"regdate\":\"").append(nComment.getRegdate()).append("\"");
	    jsonResponse.append("}");
	  
	    response.setStatus(HttpServletResponse.SC_OK);
	    response.setContentType("application/json");
	    response.getWriter().write(jsonResponse.toString());
		return null;
	}

}
