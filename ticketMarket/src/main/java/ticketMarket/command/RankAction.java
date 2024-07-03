package ticketMarket.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.PerformanceDBBean;
import ticketMarket.process.CommandAction;

public class RankAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		PerformanceDBBean dbPro = PerformanceDBBean.getInstance();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");
		String today = sdf.format(date);
		ArrayList<HashMap<String,String>> list = dbPro.getRankTopList();
		
		request.setAttribute("today", today);
		request.setAttribute("list", list);
		
		return "/performance/rankPro.jsp";
	}

}
