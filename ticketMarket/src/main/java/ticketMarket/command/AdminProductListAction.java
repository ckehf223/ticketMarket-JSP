package ticketMarket.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.PerformanceDBBean;
import ticketMarket.bean.PerformanceDataBean;
import ticketMarket.process.CommandAction;

public class AdminProductListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		PerformanceDBBean dbPro = PerformanceDBBean.getInstance();
		ArrayList<PerformanceDataBean> list = dbPro.adminPerformanceList();
		request.setAttribute("list", list);
		request.setAttribute("type", Integer.valueOf(0));
		
		return "/admin/adminProductList.jsp";
	}

}
