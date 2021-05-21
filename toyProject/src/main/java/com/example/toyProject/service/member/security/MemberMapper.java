package com.example.toyProject.service.member.security;

import java.util.Map;

import com.example.toyProject.model.member.dto.MemberDTO;

public interface MemberMapper {
	
	public MemberDTO read(String userid);
}
