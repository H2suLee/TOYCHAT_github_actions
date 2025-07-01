package com.toychat.prj.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
    	System.out.println("로그인 성공 핸들러");
        // 여기에 로그인 성공 후 처리할 내용을 작성하기!
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User)authentication.getPrincipal();
        if (isUser(oAuth2User)) {
        	System.out.println("로그인 성공");
            //response.sendRedirect("/access-user");
        } else {
        	System.out.println("로그인 실패");
            //response.sendRedirect("/access-guest");
        }
    }

    private boolean isUser(DefaultOAuth2User oAuth2User) {
        return oAuth2User.getAuthorities().stream()
            .anyMatch(authority -> authority.getAuthority().equals("ROLE_USR"));
    }
}