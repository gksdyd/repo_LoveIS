package com.loveis.demo.module.member;

import com.loveis.demo.module.base.BaseDto;

public class ActivityDto extends BaseDto {
	private String actiSeq;
	private String actiText;
	private String actiRegDate;
	private String actiModDate;
	private int actiDelNy;
	private int actiLike;
	private String user_userSeq;
	private String userSeq;
//-----
	public String getActiSeq() {
		return actiSeq;
	}
	public void setActiSeq(String actiSeq) {
		this.actiSeq = actiSeq;
	}
	public String getActiText() {
		return actiText;
	}
	public void setActiText(String actiText) {
		this.actiText = actiText;
	}
	public String getActiRegDate() {
		return actiRegDate;
	}
	public void setActiRegDate(String actiRegDate) {
		this.actiRegDate = actiRegDate;
	}
	public String getActiModDate() {
		return actiModDate;
	}
	public void setActiModDate(String actiModDate) {
		this.actiModDate = actiModDate;
	}
	public int getActiDelNy() {
		return actiDelNy;
	}
	public void setActiDelNy(int actiDelNy) {
		this.actiDelNy = actiDelNy;
	}
	public int getActiLike() {
		return actiLike;
	}
	public void setActiLike(int actiLike) {
		this.actiLike = actiLike;
	}
	public String getUser_userSeq() {
		return user_userSeq;
	}
	public void setUser_userSeq(String user_userSeq) {
		this.user_userSeq = user_userSeq;
	}
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	
}
