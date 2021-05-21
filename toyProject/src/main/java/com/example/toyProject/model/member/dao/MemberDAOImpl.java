package com.example.toyProject.model.member.dao;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.toyProject.model.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	
	@Inject
	SqlSession sqlSession;  
	
	@Override
	public MemberDTO login_Check(MemberDTO dto) {
		
		return sqlSession.selectOne("member.login_check",dto);
	}

	@Override
	public List<MemberDTO> list(int start, String searchOption, String searchKey) {
		//offset 0  은 0번째 이후의 행을 가져온다. 컨트롤러에서 0을 빼서 넘겨주었음
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", start);
		map.put("searchOption", searchOption);
		map.put("searchKey", searchKey);
		
		return sqlSession.selectList("member.list",map);
	}

	//멤버 레코드 수를 알아온다. 
	@Override
	public int count(String searchOption,String searchKey) {
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("searchOption", searchOption);
		map.put("searchKey", searchKey);
		
		return sqlSession.selectOne("member.count",map);
	}
	
	//아이디 중복 여부 확인 
	@Override
	public int idCheck(String userid)
	{
		return sqlSession.selectOne("member.idCheck",userid);
	}
	
	//회원 가입
	@Override
	public void signUp(MemberDTO dto)
	{
		sqlSession.selectOne("member.signUp",dto);
	}
	
	//수정할 회원 정보 가져오기
	@Override
	public MemberDTO getModifyInfo(String userid) {
		
		return sqlSession.selectOne("member.getModifyInfo",userid);
	}
	
	//회원 등록 처리
	@Override
	public void regist(MemberDTO dto)
	{
		sqlSession.selectOne("member.regist",dto);
	}
	
	//회원 등록 처리
	@Override
	public void modify(MemberDTO dto)
	{
		sqlSession.selectOne("member.modify",dto);
	}
	
	//회원 삭제 처리
	@Override
	public void delete(List<String> list) {
		//DAO까지 잘가나 확인 
		System.out.println("listDAO: "+list);
		sqlSession.selectOne("member.delete",list);
	}
	
	//스프링 시큐리티에서 사용할 메소드 나중에 개선
//	@Override
//	public MemberDTO getUserById(String userid) {
//		
//		return sqlSession.selectOne("member.getUserById", userid);
//	}
	
	
}
