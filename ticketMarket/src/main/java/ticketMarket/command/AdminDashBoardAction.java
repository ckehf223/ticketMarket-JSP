package ticketMarket.command;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.AdminDBBean;
import ticketMarket.process.CommandAction;

public class AdminDashBoardAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		AdminDBBean manager = AdminDBBean.getInstance();
		ArrayList<HashMap<String,String>> map = manager.getTopRankPerformance();
		request.setAttribute("map", map);
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/dashBoard.jsp";
	}

}
