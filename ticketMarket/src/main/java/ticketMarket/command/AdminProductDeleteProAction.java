package ticketMarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.PerformanceDBBean;
import ticketMarket.process.CommandAction;

public class AdminProductDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int pf_no = Integer.parseInt(request.getParameter("pf_no"));
		PerformanceDBBean dbPro = PerformanceDBBean.getInstance();
		dbPro.deletePerformance(pf_no);
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/adminProductDeletePro.jsp";
	}

}
