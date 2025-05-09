package com.loveis.demo.module.member;

import com.loveis.demo.module.base.BaseVo;

public class MemberVo extends BaseVo {

	private Integer shSecession;
	
	private Integer shGender;
	private Integer shAge;
	private Integer shDistance;
	private Integer shWeight;
	private Integer shStyle;
	private Integer shFirstFace;
	private String[] shPersonality;
	private Integer shDatingStyle;
	private Integer shMbti;
	private Integer shSmoking;
	private String[] shHobby;
	private Integer shPet;
	private Integer shReligion;
	private Integer shDrink;
	
	private String userSeq;
	private Integer userLocal;

	public Integer getShSecession() {
		return shSecession;
	}

	public void setShSecession(Integer shSecession) {
		this.shSecession = shSecession;
	}
	
	public Integer getShGender() {
		return shGender;
	}

	public void setShGender(Integer shGender) {
		this.shGender = shGender;
	}

	public Integer getShAge() {
		return shAge;
	}

	public void setShAge(Integer shAge) {
		this.shAge = shAge;
	}

	public Integer getShDistance() {
		return shDistance;
	}

	public void setShDistance(Integer shDistance) {
		this.shDistance = shDistance;
	}

	public Integer getShWeight() {
		return shWeight;
	}

	public void setShWeight(Integer shWeight) {
		this.shWeight = shWeight;
	}

	public Integer getShStyle() {
		return shStyle;
	}

	public void setShStyle(Integer shStyle) {
		this.shStyle = shStyle;
	}

	public Integer getShFirstFace() {
		return shFirstFace;
	}

	public void setShFirstFace(Integer shFirstFace) {
		this.shFirstFace = shFirstFace;
	}

	public String[] getShPersonality() {
		return shPersonality;
	}

	public void setShPersonality(String[] shPersonality) {
		this.shPersonality = shPersonality;
	}

	public Integer getShDatingStyle() {
		return shDatingStyle;
	}

	public void setShDatingStyle(Integer shDatingStyle) {
		this.shDatingStyle = shDatingStyle;
	}

	public Integer getShMbti() {
		return shMbti;
	}

	public void setShMbti(Integer shMbti) {
		this.shMbti = shMbti;
	}

	public Integer getShSmoking() {
		return shSmoking;
	}

	public void setShSmoking(Integer shSmoking) {
		this.shSmoking = shSmoking;
	}

	public String[] getShHobby() {
		return shHobby;
	}

	public void setShHobby(String[] shHobby) {
		this.shHobby = shHobby;
	}

	public Integer getShPet() {
		return shPet;
	}

	public void setShPet(Integer shPet) {
		this.shPet = shPet;
	}

	public Integer getShReligion() {
		return shReligion;
	}

	public void setShReligion(Integer shReligion) {
		this.shReligion = shReligion;
	}

	public Integer getShDrink() {
		return shDrink;
	}

	public void setShDrink(Integer shDrink) {
		this.shDrink = shDrink;
	}

	public Integer getUserLocal() {
		return userLocal;
	}

	public void setUserLocal(Integer userLocal) {
		this.userLocal = userLocal;
	}

	public String getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}

	public String ranCertification() {
		String rt = "";
		for (int i = 0; i < 6; i++) {
			rt += (int)(Math.random() * 10);			
		}
		return rt;
	}
}
