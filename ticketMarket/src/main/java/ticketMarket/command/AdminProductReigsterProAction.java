package ticketMarket.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ticketMarket.bean.PerformanceDBBean;
import ticketMarket.bean.PerformanceDataBean;
import ticketMarket.process.CommandAction;

public class AdminProductReigsterProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String filename = "";
		String realFolder = "";
		String encType = "utf-8";
		int maxSize = 10 * 1024 * 1024;

		MultipartRequest imageUp = null;
		realFolder = "D:/git/ticketMarket-JSP/ticketMarket/src/main/webapp/image/BigbannerImage";
		try {
			imageUp = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());

			Enumeration<?> files = imageUp.getFileNames();
			while (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				filename = imageUp.getFilesystemName(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
		String id = "p"+sdf.format(date);
		PerformanceDBBean dbPro = PerformanceDBBean.getInstance();
		String productName = imageUp.getParameter("productName");
		String productGenre = imageUp.getParameter("productGenre");
		String productDate = imageUp.getParameter("productDate");
		String productVenue = imageUp.getParameter("productVenue");
		int productAge = Integer.parseInt(imageUp.getParameter("productAge"));
		int productSeats = Integer.parseInt(imageUp.getParameter("productSeats"));
		int productPrice = Integer.parseInt(imageUp.getParameter("productPrice"));

		PerformanceDataBean pData = new PerformanceDataBean();
		pData.setPf_id(id);
		pData.setPf_name(productName);
		pData.setPf_genre(productGenre);
		pData.setPf_date(productDate);
		pData.setPf_venue(productVenue);
		pData.setPf_limitAge(productAge);
		pData.setPf_totalSeats(productSeats);
		pData.setPf_price(productPrice);
		pData.setPf_imageUrl(filename);

		dbPro.registerPerformance(pData);
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/adminProductRegisterPro.jsp";
	}

}
