//package com.example.toyProject.service.member.security;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
//import javax.inject.Inject;
//
//import org.apache.ibatis.session.SqlSession;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.example.toyProject.model.member.dto.CustomUser;
//import com.example.toyProject.model.member.dto.MemberDTO;
//
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private MemberMapper memberMapper;
//	
//	 
//	    @Override
//	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	   
//	    	
//	    	System.out.println("userid: "+username);
////	    	memberMapper.getUserById(userid);
//	    	MemberDTO user = memberMapper.read(username);
//	    	
//	    //	memberDAO.getUserById(userid);
//	       
//	        if(user == null) {
//	        	System.out.println("시큐리티 매퍼에서 null 반환 받음"+user);
//	            throw new UsernameNotFoundException(username);
//	        }
//	         
//	        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
//			authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
//	        UserDetails userDetails = new CustomUser(user.getUserid(), user.getPassword(), true, true, true, true, authorities);
//	        return userDetails;
//	    }
//	 
//	
//	
//
//}
