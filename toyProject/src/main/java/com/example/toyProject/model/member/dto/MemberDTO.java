package com.example.toyProject.model.member.dto;

import java.io.Serializable;
import java.util.Date;

public class MemberDTO implements Serializable{
	
	/**
	 * 직렬화 역직렬화 과정에서 값이 서로 맞는지 확인 한 후에 처리를 함
	 * 이 값이 맞지 않다면 InvalidClassException 예외가 발생한다.
	 */
	private static final long serialVersionUID = 1L;
	
	private int membernum;
	private String userid;
	private String password;
	private String name;
	private String phonenum;
	private Date joindate;
	
	private String nickname;
	
	public String getNickname()
	{
		return nickname;
	}
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	//사용자의 권한
	private String authority;

	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public int getMembernum() {
		return membernum;
	}
	public void setMembernum(int membernum) {
		this.membernum = membernum;
	}
	public Date getJoindate() {
		return joindate;
	}
	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [membernum=" + membernum + ", userid=" + userid + ", password=" + password + ", name=" + name
				+ ", phonenum=" + phonenum + ", joindate=" + joindate + ", authority=" + authority + " nickname="+ nickname+ "]";
	}
	
	
	
	
}
