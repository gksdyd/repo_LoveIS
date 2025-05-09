package com.loveis.demo.module.member;

import java.util.List;

public class Member4ListDto {
	
	private List<Integer> persText;
	private List<Integer> hobbText;
	
	private String user_userSeq;
//	-----

	public List<Integer> getPersText() {
		return persText;
	}

	public void setPersText(List<Integer> persText) {
		this.persText = persText;
	}

	public List<Integer> getHobbText() {
		return hobbText;
	}

	public void setHobbText(List<Integer> hobbText) {
		this.hobbText = hobbText;
	}

	public String getUser_userSeq() {
		return user_userSeq;
	}

	public void setUser_userSeq(String user_userSeq) {
		this.user_userSeq = user_userSeq;
	}

}
