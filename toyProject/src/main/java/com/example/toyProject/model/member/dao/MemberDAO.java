package com.example.toyProject.model.member.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.toyProject.model.member.dto.MemberDTO;

public interface MemberDAO {
	 
	 MemberDTO login_Check(MemberDTO dto);
	
	 List<MemberDTO> list(int start, String searchOption, String searchKey);
	
	 //멤버 레코드 수를 받아옴 
	 int count(String searchOption,String searchKey);
	 
	 //아이디 중복여부 확인 
	 int idCheck(String userid);
	 
	 //회원가입 절차
	 void signUp(MemberDTO dto);
	 
	 //회원 정보 수정창에 가져올 정보
	 MemberDTO getModifyInfo(String userid);
	 
	 //회원정보 등록 
	 void regist(MemberDTO dto);
	 
	 //회원정보 수정
	 void modify(MemberDTO dto);
	 
	 //회원 정보 삭제
	 void delete(List<String> list);
	 
	 //스프링 시큐리티에서 사용할 메소드 나중에 개선
//	 MemberDTO getUserById(String userid);
}
