package com.example.toyProject.service.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.toyProject.controller.member.CustomHttpRequestServlet;
import com.example.toyProject.model.member.dto.MemberDTO;

public interface MemberService {
	
	//로그인 확인 
	MemberDTO login_Check(MemberDTO dto, HttpSession session);
	
	//로그아웃 
	void logout(HttpSession session);
	
	//회원 목록
	List<MemberDTO> list(int start, String searchOption, String searchKey);
	
	//검색 조건에 맞는 최대 레코드 수를 출력  
	int count(String searchOption, String searchKey);
	
	//아이디 중복확인
	int idCheck(String userid);
	
	//회원 가입
	void signUp(MemberDTO dto);
	
	//회원 수정창에 가져올 정보
	MemberDTO getModifyInfo(String userid);
	
	//회원 등록 처리
	void regist(MemberDTO dto);
	
	//회원 수정 처리
	void modify(MemberDTO dto);
	
	//회원 삭제 처리
	void delete(List<String> list);
	
	//HttpServletRequest 직렬화 가능 여부 확인 
	int CanISerialize(CustomHttpRequestServlet req);
	
}
