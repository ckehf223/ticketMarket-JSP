package ticketMarket.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.NoticeDBBean;
import ticketMarket.bean.NoticeDataBean;
import ticketMarket.process.CommandAction;

public class AdminNoticeListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		NoticeDBBean dbPro = NoticeDBBean.getInstance();
		int pageSize = 10;
		int pageNum = 1;
		int pageBlock = 5;
		if(request.getParameter("pageNum") == null || request.getParameter("pageNum").equals("")) {
			pageNum = 1;
		}else {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		int startRow = (pageNum - 1) * pageSize + 1;
		int endRow = pageNum * pageSize;
		ArrayList<NoticeDataBean> noList = dbPro.getNoticeList(startRow, endRow);
		int count = noList.size();
		int number = count-(pageNum -1 )*pageSize;
		int pageCount = count/pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = (int)((pageNum-1) / pageBlock) * pageBlock +1;
		int endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		request.setAttribute("number", number);;
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("count", count);
		request.setAttribute("noList", noList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/adminNoticeList.jsp";
	}

}
