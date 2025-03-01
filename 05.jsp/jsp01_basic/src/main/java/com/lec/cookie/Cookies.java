package com.lec.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Cookies {

	private Map<String, Cookie> cookiMap = new HashMap<>();
	
	public static Cookie createCookie(String name, String value) throws Exception {
		return new Cookie(name, URLEncoder.encode(value, "utf-8"));
	}
	
	public static Cookie createCookie(String name, String value
			, String path, int maxAge) throws Exception {
		
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	public static Cookie createCookie(String name, String value
			, String path, int maxAge, String domain) throws Exception {
		
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	
	
	public Cookies(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(int i=0;i<cookies.length;i++) {
				cookiMap.put(cookies[i].getName(), cookies[i]);
			}
		}
	}
	
	public Cookie getCookie(String name) {
		return cookiMap.get(name);
	}
	
	public String getValue(String name) throws Exception {
		Cookie cookie = cookiMap.get(name);
		if(cookie == null) return null;
		return URLDecoder.decode(cookie.getValue(), "utf-8");
	}
	
	public boolean exitst(String name) {
		return cookiMap.get(name) != null;
	}
	
	
}
















