package com.example.toyProject.service.member.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;


/* 로그인 성공 대응 로직  */
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler
{
	//로그인 인증후 디폴트 Provider에서 실행하는 메소드이다. 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException 
	{
		
		//Security가 요청을 가로챈경우 사용자가 맨처음에 요청했던 URL 정보를 저장한 객체(세션)를 가져온다. 
		RequestCache requestCache = new HttpSessionRequestCache();
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		String URL="/";
		//원래 요청한 url 정보가 세션에 존재하는 경우
		if(savedRequest != null)
		{
			URL = savedRequest.getRedirectUrl();
			
			//세션에 저장된 객체를 다 사용한 뒤에는 지워서 메모리 낭비 방지
			requestCache.removeRequest(request, response);
			
			System.out.println("스프링 시큐리티에서 가로챈 URL: "+URL);
			
			//원래 있었던 요청으로 리다이렉트 진행
			response.sendRedirect(URL);
		}
		//로그인 버튼 눌러서 로그인 수행한 경우 메인페이지로 리다이렉트 
		else {
			response.sendRedirect(URL);
		}
		
		
		
	}

	
}
	
	

