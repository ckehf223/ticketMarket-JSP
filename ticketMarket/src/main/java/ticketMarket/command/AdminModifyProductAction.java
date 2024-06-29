package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.PerformanceDBBean;
import ticketMarket.bean.PerformanceDataBean;
import ticketMarket.process.CommandAction;

public class AdminModifyProductAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int pf_no = Integer.parseInt(request.getParameter("pf_no"));
		PerformanceDBBean dbPro = PerformanceDBBean.getInstance();
		PerformanceDataBean pData = dbPro.getPerformance(pf_no);
		request.setAttribute("pData", pData);
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/adminModifyProduct.jsp";
	}

}
