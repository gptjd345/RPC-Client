package com.example.toyProject.model.member.dto;

//페이지의 현재 블록, 페이지 검색 옵션, 페이지 검색 키워드를 저장하는 DTO
public class PageDTO {
	
	//기본값 세팅
	private int curBlock=1;
	private String searchOption="none";
	private String searchKey="";
	
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public String getSearchOption() {
		return searchOption;
	}
	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	@Override
	public String toString() {
		return "PageDTO [curBlock=" + curBlock + ", searchOption=" + searchOption + ", searchKey=" + searchKey + "]";
	}
	
	
	
}
