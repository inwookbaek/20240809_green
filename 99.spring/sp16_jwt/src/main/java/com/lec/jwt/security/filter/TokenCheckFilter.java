package com.lec.jwt.security.filter;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lec.jwt.security.APIUserDetailsService;
import com.lec.jwt.security.exception.AccessTokenException;
import com.lec.jwt.util.JWTUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {
	
	private final APIUserDetailsService apiUserDetailsService;  // 872
	private final JWTUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

// p808~809		
//        String path = request.getRequestURI();
//
//        if (!path.startsWith("/api/")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        log.info("Token Check Filter..........................");
//        log.info("JWTUtil: " + jwtUtil);
//		
//        filterChain.doFilter(request,response);
		
// p814
        String path = request.getRequestURI();

        if (!path.startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }

        log.info("Token Check Filter..........................");
        log.info("JWTUtil: " + jwtUtil);
        
// p873 start
//        try {
//        	validateAccessToken(request);
//            filterChain.doFilter(request,response);
//        } catch(AccessTokenException accessTokenException){
//            accessTokenException.sendResponseError(response);
//        }
        try{

            Map<String, Object> payload = validateAccessToken(request);

            // JWT의 mid값을 이용해서 UserDetails를 구하고 이를 활용해서
            // UsernamePasswordAuthenticationToken객체를 구성
            String mid = (String)payload.get("mid");
            log.info("mid: " + mid);

            UserDetails userDetails = apiUserDetailsService.loadUserByUsername(mid);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request,response);
        } catch(AccessTokenException accessTokenException){
            accessTokenException.sendResponseError(response);
        }        
       
// p873 end
    }
	
	private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException {

        String headerStr = request.getHeader("Authorization");

        if(headerStr == null  || headerStr.length() < 8){
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
        }

        //Bearer 생략
        String tokenType = headerStr.substring(0,6);
        String tokenStr =  headerStr.substring(7);

        if(tokenType.equalsIgnoreCase("Bearer") == false){
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADTYPE);
        }

        try{
            Map<String, Object> values = jwtUtil.validateToken(tokenStr);

            return values;
        }catch(MalformedJwtException malformedJwtException){
            log.error("MalformedJwtException----------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.MALFORM);
        } catch(SecurityException securityException){
        	log.error("SecurityException----------------------");
        	throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADSIGN);
        } catch(ExpiredJwtException expiredJwtException){
        	log.error("ExpiredJwtException----------------------");
        	throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.EXPIRED);
        }
    }
}
