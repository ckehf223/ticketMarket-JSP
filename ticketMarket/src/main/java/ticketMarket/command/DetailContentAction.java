package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CartDBBean;
import ticketMarket.bean.CustomerDBBean;
import ticketMarket.bean.PerformanceDBBean;
import ticketMarket.bean.PerformanceDataBean;
import ticketMarket.bean.SeatDBBean;
import ticketMarket.process.CommandAction;

public class DetailContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = (String)request.getSession().getAttribute("loginId");
		PerformanceDBBean dbProP = PerformanceDBBean.getInstance();
		SeatDBBean dbProS = SeatDBBean.getInstance();
		CustomerDBBean dbProC = CustomerDBBean.getInstance();
		int age = dbProC.getCustomerAge(id);
		String[] row = {"A","B","C","D","E","F","G","H","I","J"};
		int no = Integer.parseInt(request.getParameter("no"));
		PerformanceDataBean pd = dbProP.getPerformance(no);
		String[] list = dbProS.getSeats(pd.getPf_id()).split(",");
		request.setAttribute("age", age);
		request.setAttribute("rowList", row);
		request.setAttribute("pd", pd);
		request.setAttribute("list", list);
		return "/performance/detailContent.jsp";
	}

}
