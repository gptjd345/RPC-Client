package com.example.toyProject.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.GrpcClient;

@Controller
public class MainController {
	
	@Resource
	private GrpcClient grpcClient;
	
	
	@RequestMapping("/")
	public String Main()
	{
		grpcClient.helloWorldPrint();
		return "main";
	}
}
