package ticketMarket.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.PerformanceDBBean;
import ticketMarket.bean.PerformanceDataBean;
import ticketMarket.process.CommandAction;

public class SearchAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String text = request.getParameter("search");
		PerformanceDBBean dbPro = PerformanceDBBean.getInstance();
		ArrayList<PerformanceDataBean> list = dbPro.getSearchPerformanceList(text);
		int count = 0;
		if(list != null) {
			count = list.size();
		}
		request.setAttribute("text", text);
		request.setAttribute("pfList", list);
		request.setAttribute("count", count);
		return "/performance/searchPro.jsp";
	}

}
