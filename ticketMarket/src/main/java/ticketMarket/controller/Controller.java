package ticketMarket.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticketMarket.process.CommandAction;

@WebServlet(urlPatterns = { "*.do" }, initParams = {
		@WebInitParam(name = "propertyConfig", value = "commandMapping_.properties") })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();

	public Controller() {
	}

	// 서블릿 컨트롤러 생성이될때 commandMapping.properties 정보를 가지고 모든 ****Action 를 객체를 만들어서
	// Map<String,Object> commandMap 에 저장한다. 딱 한번만
	@Override
	public void init(ServletConfig config) throws ServletException {
		// initParams 에서 propertyConfig 의 값을 읽어옴 >> commandMapping.properties
		String props = config.getInitParameter("propertyConfig");
		// properties 파일이 저장된 폴더
		String realFolder = "/property";
		// 웹어플리케이션 루트 경로
		ServletContext context = config.getServletContext();
		// realFolder 를 웹어플리케이션 시스템상의 절대경로로 변경
		String realPath = context.getRealPath(realFolder) + "\\" + props;
		// 명령어와 처리클래스의 매핑정보를 저장할 Properties 객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		try {
			// command.properties 파일의 내용을 읽어옴
			f = new FileInputStream(realPath);
			// command.properties 의 내용을 Properties 객체 pr 에 저장
			pr.load(f);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException ex) {
				}
		}
		// Set 객체의 iterator()메소드를 사용해 Iterator 객체를 얻어냄
		Iterator<?> keyIter = pr.keySet().iterator();
		// Iterator 객체에 저장된 명령어와 처리클래스를 commandMap 에 저장
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String className = pr.getProperty(command);
			try {
				Class<?> commandClass = Class.forName(className);
				// Object commandInstance = commandClass.newInstance();
				Object commandInstance = commandClass.getDeclaredConstructor().newInstance();
				commandMap.put(command, commandInstance);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = null;
		CommandAction com = null;
		try {					
			String command = request.getRequestURI();
			if (command.indexOf(request.getContextPath()) == 0)
				command = command.substring(request.getContextPath().length());
			com = (CommandAction) commandMap.get(command);
			view = com.requestPro(request, response);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if(view != null) {
			request.setAttribute("cont", view);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

}
