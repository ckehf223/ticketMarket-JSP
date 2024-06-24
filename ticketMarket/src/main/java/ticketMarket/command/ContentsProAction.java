package ticketMarket.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.PerformanceDBBean;
import ticketMarket.bean.PerformanceDataBean;
import ticketMarket.process.CommandAction;

public class ContentsProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String num = request.getParameter("num");
		String category = null;
		switch(num) {
		case "1": category = "뮤지컬"; break;
		case "2": category = "콘서트"; break;
		case "3": category = "전시"; break;
		case "4": category = "연극"; break;
		case "5": category = "아동가족"; break;
		}
		PerformanceDBBean dbPro = PerformanceDBBean.getInstance();
		ArrayList<PerformanceDataBean> list = dbPro.getPerformanceList(category);
		request.setAttribute("pfList", list);
		request.setAttribute("category", category);
		return "/performance/contents.jsp";
	}

}
