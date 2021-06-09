package com.example.toyProject;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.toyProject.model.member.dto.MemberDTO;
import com.example.toyProject.service.member.MemberService;

public class ClientInvoker {
	//jsp에서 import 해서 사용할때 ClientInvoker 객체를 생성하는 불편함 때문에 static으로 지정했습니다.  
	public static int getListTotal(String searchOption, String searchKey)
	{
		//ClassPathXmlApplication의 기본위치는 WEB-INF/classes 입니다. 
		//이 프로젝트에는 classes 폴더가 없으므로 상위계층으로 올라간뒤 root-context.xml을 찾습니다.
		//절대경로로는 /WEB-INF 하위에 있는내용들에 직접접근이 불가능하므로 이러한 방법을 사용합니다. 
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("../spring/root-context.xml");
		MemberService memberService = 
				(MemberService)context.getBean("memberServiceImplInvoker");
		
		System.out.println("HTTP invoker idCheck "+memberService.idCheck("gptjd345"));
		
		//회원 목록을 가져온다. 페이지블록 = 2 , 검색 옵션 = all , 검색 키워드 = ""
		List<MemberDTO> list = memberService.list(2, "all", "");
		
		System.out.println("HTTP invoker list "+list);
		
		System.out.println("HTTP invoker list "+memberService.getModifyInfo("gptjd345"));
		

		
		
		
		//검색 옵션과 검색 키워드에 맞는 레코드의 수를 반환한다. 
		return memberService.count(searchOption, searchKey);
		
		
	}
}
