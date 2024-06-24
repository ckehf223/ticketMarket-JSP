package ticketMarket.command;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.bean.CustomerDBBean;
import ticketMarket.bean.CustomerDataBean;
import ticketMarket.process.CommandAction;

public class RegisterProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		CustomerDataBean member = new CustomerDataBean();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int age = Integer.parseInt(sdf.format(date));
		age = age - Integer.parseInt(request.getParameter("userYear"));
		member.setCt_id(request.getParameter("userId"));
		member.setCt_pw(request.getParameter("userPw"));
		member.setCt_name(request.getParameter("userName"));
		member.setCt_email(request.getParameter("userEmail"));
		member.setCt_age(age);
		member.setCt_birth(request.getParameter("userBirth"));
		
		member.setCt_address(request.getParameter("userAddress"));
		member.setCt_phone(request.getParameter("userPhone"));
		
		// 회원가입처리
		CustomerDBBean dbPro = CustomerDBBean.getInstance();
		dbPro.insertMember(member);
		return "/member/joinPro.jsp";
	}

}
