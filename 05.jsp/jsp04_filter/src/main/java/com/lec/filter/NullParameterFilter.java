package com.lec.filter;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/* 
	필터를 구현하는데 핵심 클래스는 다음의 3개 타입이다.
	
	1. javax.servlet.Filter 인터페이스 : 클라이언트와 최종자원사이에 위치하는 필터를 구현하는 객체가
	   상속해야할 인터페이스이다.
	2. javax.servlet.ServletRequestWrapper 클래스 : 필터가 요청한 결과를 저장하는 래퍼클래스
	3. javax.servlet.ServletResponseWrapper 클래스 : 필터가 응답 결과를 사용하기 위한 래퍼클래스
	
	[Filter인터페이스]
	1. init() : 필터를 초기화할 때 호출
	2. doFilter() : 필터기능을 수행, chain을 이용해서 체인다음의 필터로 처리를 전달
	3. destory() :  필터가 웹컨테이너에서 삭제될 때 호출
	
	[필터의 구현절차]
	1. init()를 호출해서 초기화 작업
	2. doFilter() 
	   1) request파라미터를 이용해서 요청의 필터작업을 수행
	   2) chain.doFilter(req, res)를 이용하여 체인의 다음필터를 수행
	   3) response를 이용하여 응답의 필터링 작업을 수행
	3. destory() : 필터가 사용한 자원을 반납
	
*/
public class NullParameterFilter implements Filter {

	private String[] parameterNames = null;
	@Override
	public void init(FilterConfig config) throws ServletException {
		String names = config.getInitParameter("parameterNames");
		StringTokenizer st = new StringTokenizer(names, ",");
		parameterNames = new String[st.countTokens()];
		for(int i=0;st.hasMoreTokens();i++) {
			parameterNames[i] = st.nextToken();
			System.out.println(parameterNames[i]);
		}
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res
			, FilterChain chain)
			throws IOException, ServletException {
		NullParameterRequestMapper reqWrapper
		   = new NullParameterRequestMapper((HttpServletRequest) req);
		reqWrapper.checkNull(parameterNames);
		chain.doFilter(reqWrapper, res);
	}

	@Override
	public void destroy() {
		// dummy
	}
}
