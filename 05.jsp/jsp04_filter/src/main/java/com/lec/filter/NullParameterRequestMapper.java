package com.lec.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class NullParameterRequestMapper extends HttpServletRequestWrapper{

	private Map<String, String[]> parameterMap = null;
	
	public NullParameterRequestMapper(HttpServletRequest req) {
		super(req);
		parameterMap = new HashMap<String, String[]>(req.getParameterMap());
	}

	@Override
	public String getParameter(String name) {
		String[] values = getParameterValues(name);
		if(values != null && values.length > 0) return values[0];
		return null;
	}
	
	@Override
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}
	
	@Override
	public String[] getParameterValues(String name) {
		return (String[]) parameterMap.get(name);
	}

	public void checkNull(String[] parameterNames) {
		for(int i=0;i<parameterNames.length;i++) {
			if(!parameterMap.containsKey(parameterNames[i])) {
				String[] values = new String[] {""};
				parameterMap.put(parameterNames[i], values);
			}
		}
		
	}
}
