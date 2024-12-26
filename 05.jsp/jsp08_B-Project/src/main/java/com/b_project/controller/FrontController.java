package com.b_project.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	
	private Map<String, Action> actionMap = new HashMap<String, Action>();
	
	@Override
	public void init() throws ServletException {
		
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);
		
		try(FileReader fis = new FileReader(configFilePath)) {
			prop.load(fis);
		} catch (IOException e) {
			throw new ServletException(e);
		}
		
		Iterator keyIter = prop.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String action = (String) keyIter.next();
			String actionClassName = prop.getProperty(action);
			
			System.out.println(action + " : " + actionClassName);
			
			
			try {
				Class<?> actionClass = Class.forName(actionClassName);
				Action actionInstance = (Action) actionClass.getDeclaredConstructor().newInstance();
				actionMap.put(action, actionInstance);
			} catch (Exception e) {
				throw new ServletException(e);
			}		
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		process(req, res);
	}

	private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String action = requestURI.substring(contextPath.length());
		Action actionInstance = actionMap.get(action);
		ActionForward forward = null;
		
		try {
			forward = actionInstance.execute(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				res.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, res);
			}
//		예를 들어 com.b_project.model.funding.action.FundingSupportAction에서 
//		Action이 끝난 후에도 res에 자바스크립트의 alert이 실행되게하기 위해, location.href를 통해 직접 리다이렉트 경로를 지정했다.
//		따라서 FundingSupportAction의 execute()가 반환하는 값은 아무런 의미가 없게되기에 null을 반환했다.
//		이 때 NullpointerException이 발생하는 것을 방지하기 위해 빈 else구문을 설정한다. 
		} else { 
			// dummy
		}
	}	
}
