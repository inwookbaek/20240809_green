package com.lec.jwt.security.filter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class APILoginFilter extends AbstractAuthenticationProcessingFilter {

    public APILoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) 
    		throws AuthenticationException, IOException, ServletException {

    	log.info("APILoginFilter-----------------------------------");

// p788~789 - start ----------------------------------------------------------
    	
        if(request.getMethod().equalsIgnoreCase("GET")){
            log.info("GET METHOD NOT SUPPORT");
            return null;
        }

        Map<String, String> jsonData = parseRequestJSON(request);

        log.info("jsonData: "+jsonData);  
        
        
// p788~789 - end ----------------------------------------------------------
        
        //p791
        // return null;
        UsernamePasswordAuthenticationToken authenticationToken 
        	= new UsernamePasswordAuthenticationToken(
        			jsonData.get("mid"),
        			jsonData.get("mpw"));

        return getAuthenticationManager().authenticate(authenticationToken);        
    }   
    
    private Map<String,String> parseRequestJSON(HttpServletRequest request) {

        //JSON 데이터를 분석해서 mid, mpw 전달 값을 Map으로 처리
        try(Reader reader = new InputStreamReader(request.getInputStream())){
       	
            Gson gson = new Gson();
            return gson.fromJson(reader, Map.class);

        } catch(Exception e){
            log.error(e.getMessage());
        }
        
        return null;
    }    
}
