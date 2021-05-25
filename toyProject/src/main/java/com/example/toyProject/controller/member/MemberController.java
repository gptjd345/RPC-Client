package com.example.toyProject.controller.member;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.toyProject.model.member.dto.MemberDTO;
import com.example.toyProject.model.member.dto.PageDTO;
import com.example.toyProject.service.member.MemberService;
import com.example.toyProject.service.member.MemberServiceImpl2;

@Controller
@RequestMapping("/member/*")
public class MemberController 
{
	
	@Inject
	MemberServiceImpl2 memberService;
	
	@RequestMapping("login")
	public String login()
	{
		return "member/login";
	}
	
	@RequestMapping(value="login.do", method= RequestMethod.GET)
	public String preventLoginDo()
	{
		return "redirect:/member/login";
	}
	
	@RequestMapping(value="login.do" ,method = RequestMethod.POST)
	public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session)
	{
		System.out.println("dto: "+dto);
		MemberDTO result = memberService.login_Check(dto, session);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("alert");
		
		//로그인 성공시 만들어진 객체가 null 이 아닌 경우 
		if(result != null)
		{	
			mav.addObject("msg", "로그인에 성공하였습니다.");
			mav.addObject("url", "/");
			return mav;
		}	
		else
		{	
			mav.addObject("msg", "아이디 또는 비밀번호를 확인해주세요. ");
			mav.addObject("url", "/member/login");
			return mav;
		}
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session,ModelAndView mav)
	{
		memberService.logout(session);
		
		return "redirect:/member/login";
	}
	

	@RequestMapping("list")
	public ModelAndView list(@ModelAttribute PageDTO pageDTO)
	{	
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/list");
		mav.addObject("pageDTO",pageDTO);
		
		return mav;		
	}
	
	@RequestMapping("tableInit.do")
	public @ResponseBody Map<String,Object> tableInit(@RequestBody PageDTO pageDTO)
	{
		
		//전체 레코드 수 알아옴
		int totalPage = memberService.count(pageDTO.getSearchOption(),pageDTO.getSearchKey());
		
		//찾아올 시작 레코드 = 현재 블록 * 10 - 10 이다. 10은 한블록에 있는 레코드 수 
		//offset은 쓰여진 숫자 다음 레코드를 가져오니 9가 아닌 10을 빼준다.
		int start = (pageDTO.getCurBlock() * 10) - 10;
		
		//해당 블록에 레코드가 없다면 없는 페이지를 보여준다.   
		
		List<MemberDTO> list = memberService.list(start,pageDTO.getSearchOption(),pageDTO.getSearchKey());
		
		System.out.println("totalPage = "+totalPage);
		System.out.println("curBlock:"+pageDTO.getCurBlock());
		System.out.println("searchOption:"+pageDTO.getSearchOption());
		System.out.println("searchKey:"+pageDTO.getSearchKey());
		System.out.println("start:"+start);
		System.out.println("list:"+list);
		
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("list", list);
		map.put("totalPage", totalPage);
		map.put("pageDTO", pageDTO);
		
		
		return map;
		
	}
	
	//회원가입시 아이디 중복 확인 
	@RequestMapping("/idCheck.do")
	public @ResponseBody int idCheck(@RequestParam String userid)
	{
		System.out.println("Controller: userid = "+userid); 
		int result = memberService.idCheck(userid);
		System.out.println("result : " + result);
		
		return result;
	}
	
	//회원 가입 창 
	@RequestMapping("signUp")
	public String signUp()
	{
		return "member/signUp";
	}
	
	//회원가입 처리
	@RequestMapping(value="/signUp.do", method = RequestMethod.POST)
	public ModelAndView signUp(@ModelAttribute MemberDTO dto)
	{
		System.out.println("회원 가입 전:"+dto.toString());
		
		memberService.signUp(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("alert");
		mav.addObject("msg", "회원 가입 되었습니다. ");
		mav.addObject("url", "/member/login");
		
		return mav;
		
	}
	
	//회원 등록 창 요청 처리
	@RequestMapping(value="/regist", method = RequestMethod.GET)
	public ModelAndView manage(@ModelAttribute PageDTO pageDTO)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/regist");
		mav.addObject("pageDTO",pageDTO);
		
		return mav;
	}
	
	//회원 등록 처리
	@RequestMapping("/regist.do")
	public ModelAndView regist(@ModelAttribute MemberDTO dto , @ModelAttribute PageDTO pageDTO) 
	{
		System.out.print("회원 등록 전: "+ dto.toString()); 
		System.out.println("회원 등록 --> PageDTO : "+pageDTO);
		
		memberService.regist(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("alert");
		mav.addObject("msg", "등록 되었습니다. ");
		
		mav.addObject("url", "/member/list.do?curBlock="+pageDTO.getCurBlock()+ 
				"&searchOption="+pageDTO.getSearchOption()+"&searchKey="+pageDTO.getSearchKey());
		
		return mav;
		
	}
	
	//회원 수정 창 가져오기 GET
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public ModelAndView modify(@RequestParam String userid, @ModelAttribute PageDTO pageDTO)
	{
		//수정할 회원 정보 가져오기
		MemberDTO dto = memberService.getModifyInfo(userid);
		ModelAndView mav = new ModelAndView();
			
		mav.setViewName("/member/modify");
		//수정창은 해당 아이디와 수정창으로 넘어오기전 블록 번호를 가지고있다.
		mav.addObject("dto",dto);
		mav.addObject("pageDTO",pageDTO);
		
		
		return mav;
	}
	
	//회원 수정 처리
	@RequestMapping("/modify.do")
	public ModelAndView modify(@ModelAttribute MemberDTO dto , @ModelAttribute PageDTO pageDTO)
	{
		System.out.print("회원 수정 전: "+ dto.toString()); 
		System.out.println("회원 수정 --> PageDTO : "+pageDTO);
		
		memberService.modify(dto);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("alert");
		mav.addObject("msg", "수정되었습니다. ");
		mav.addObject("url", "/member/list.do?curBlock="+pageDTO.getCurBlock()+
				"&searchOption="+pageDTO.getSearchOption()+"&searchKey="+pageDTO.getSearchKey());
		
		return mav;
		
	}
	
	//회원 삭제 수행
	@RequestMapping("/delete.do")
	public ModelAndView delete(@RequestParam List<String> list, @ModelAttribute PageDTO pageDTO)
	{
		System.out.println("회원 삭제할 리스트(아이디) : "+list);
		memberService.delete(list);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("alert");
		mav.addObject("msg", "삭제되었습니다. ");
		mav.addObject("url", "/member/list.do?curBlock="+pageDTO.getCurBlock()+
				"&searchOption="+pageDTO.getSearchOption()+"&searchKey="+pageDTO.getSearchKey());
		
		
		return mav;
	}
	
	
}
