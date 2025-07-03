package com.toychat.prj.handler;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.toychat.prj.common.jwt.JwtUtil;
import com.toychat.prj.common.util.Util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Value("${spring.security.oauth2.client.registration.kakao.callback-path}")
    private String callbackPath;
	
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        String uri = Util.getBaseUrl(request);
        System.out.println("로그인 성공 핸들러 : " + uri);
        uri = "http://localhost:9091"; // 로컬용
        
        String fullUrl = "";
    	DefaultOAuth2User oauth2User = (DefaultOAuth2User)authentication.getPrincipal();
        if (isUser(oauth2User)) {
        	System.out.println("로그인 성공");
        	
        	Map<String, Object> attributes = oauth2User.getAttributes();
        	String id = String.valueOf(attributes.get("id"));
        	Map<String, Object> properties =  (Map<String, Object>) attributes.get("properties");
        	String nick = (String) properties.get("nickname");
        	// jwtKey 부여
        	String jwtToken = jwtUtil.generateToken(id);
        	fullUrl = UriComponentsBuilder.fromUriString(uri + callbackPath)
                    .queryParam("id",id)
                    .queryParam("nick",nick)
                    .queryParam("jwt",jwtToken)
                    .build()
                    .encode()
                    .toUriString();
        	
            response.sendRedirect(fullUrl);
            
        } else {
        	System.out.println("로그인 실패");
        	fullUrl = UriComponentsBuilder.fromUriString(uri + callbackPath)
                    .queryParam("error","Not user")
                    .build()
                    .encode()
                    .toUriString();
        	
            response.sendRedirect(fullUrl);
        }
    }

    private boolean isUser(DefaultOAuth2User oAuth2User) {
        return oAuth2User.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_USR"));
    }
}