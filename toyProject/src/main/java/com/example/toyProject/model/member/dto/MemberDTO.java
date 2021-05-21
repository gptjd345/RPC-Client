package com.example.toyProject.model.member.dto;

import java.util.Date;

public class MemberDTO {
	
	private int membernum;
	private String userid;
	private String password;
	private String name;
	private String phonenum;
	private Date joindate;
	
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
				+ ", phonenum=" + phonenum + ", joindate=" + joindate + ", authority=" + authority + "]";
	}
	
	
	
	
}
