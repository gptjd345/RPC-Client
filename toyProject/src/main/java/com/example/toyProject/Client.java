package com.example.toyProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.example.toyProject.service.member.MemberService;

public class Client {
	public static void main(String[] args)
	{
		ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/spring/root-context.xml");

		MemberService memberService = (MemberService)context.getBean("memberServiceImplInvoker");
		System.out.println( memberService.count("none", "gptjd"));
	}
}
