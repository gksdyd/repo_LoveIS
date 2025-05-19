package com.loveis.demo.module.member;

import com.loveis.demo.module.base.BaseDto;
import com.loveis.demo.module.time.TimeAgoUtil;

public class MemberDto extends BaseDto {

	private String userSeq;	
	private int userGender;	
	private String userName;	
	private String userNickname;	
	private String userId;
	private String userPassword;
	private String userBirth;	
	private String userEmail;	
	private String userPhone;
	private String userCity;
	private String userLocal;
	private int userDelFlag;	
	private int userPeanut;
	private String userRegDate;
	private String userModDate;
	private Double userHeight;
	private Double userWeight;
	private String userIntroduce;
	private Integer userEducation;
	private Integer userMBTI;
	private Integer userSmoking;
	private Integer userPetNy;
	private Integer userDatingStyle;
	private String userJob;
	private Integer userStyle;
	private Integer userFirstFace;
	private Integer userChurch;
	private Integer userAlcohol;
	private Integer userbodyShape;
	private String userLogTime;
	
	// 경과시간 계산
	private String timeAgo;
	
	private String persSeq;
	private Integer persText;
	private Integer personalCount;
	private String persnalText;
	private int persnalDelNy;
	
	private String hobbSeq;
	private Integer hobbText;
	private Integer hobbyCount;
	private String hobbyText;
	private int hobbyDelNy;
	
	private String newPassword;
	
	private String imagePath;
	
	private Integer userAge;
//	-----
	public String getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(String userSeq) {
		this.userSeq = userSeq;
	}
	public int getUserGender() {
		return userGender;
	}
	public void setUserGender(int userGender) {
		this.userGender = userGender;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public int getUserDelFlag() {
		return userDelFlag;
	}
	public void setUserDelFlag(int userDelFlag) {
		this.userDelFlag = userDelFlag;
	}
	public String getUserRegDate() {
		return userRegDate;
	}
	public void setUserRegDate(String userRegDate) {
		this.userRegDate = userRegDate;
	}
	public String getUserModDate() {
		return userModDate;
	}
	public void setUserModDate(String userModDate) {
		this.userModDate = userModDate;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getUserLocal() {
		return userLocal;
	}
	public void setUserLocal(String userLocal) {
		this.userLocal = userLocal;
	}
	public int getUserPeanut() {
		return userPeanut;
	}
	public void setUserPeanut(int userPeanut) {
		this.userPeanut = userPeanut;
	}
	public Double getUserHeight() {
		return userHeight;
	}
	public void setUserHeight(Double userHeight) {
		this.userHeight = userHeight;
	}
	public Double getUserWeight() {
		return userWeight;
	}
	public void setUserWeight(Double userWeight) {
		this.userWeight = userWeight;
	}
	public String getUserIntroduce() {
		return userIntroduce;
	}
	public void setUserIntroduce(String userIntroduce) {
		this.userIntroduce = userIntroduce;
	}
	public Integer getUserEducation() {
		return userEducation;
	}
	public void setUserEducation(Integer userEducation) {
		this.userEducation = userEducation;
	}
	public Integer getUserMBTI() {
		return userMBTI;
	}
	public void setUserMBTI(Integer userMBTI) {
		this.userMBTI = userMBTI;
	}
	public Integer getUserSmoking() {
		return userSmoking;
	}
	public void setUserSmoking(Integer userSmoking) {
		this.userSmoking = userSmoking;
	}
	public Integer getUserPetNy() {
		return userPetNy;
	}
	public void setUserPetNy(Integer userPetNy) {
		this.userPetNy = userPetNy;
	}
	public Integer getUserDatingStyle() {
		return userDatingStyle;
	}
	public void setUserDatingStyle(Integer userDatingStyle) {
		this.userDatingStyle = userDatingStyle;
	}
	public String getUserJob() {
		return userJob;
	}
	public void setUserJob(String userJob) {
		this.userJob = userJob;
	}
	public Integer getUserStyle() {
		return userStyle;
	}
	public void setUserStyle(Integer userStyle) {
		this.userStyle = userStyle;
	}
	public Integer getUserFirstFace() {
		return userFirstFace;
	}
	public void setUserFirstFace(Integer userFirstFace) {
		this.userFirstFace = userFirstFace;
	}
	public Integer getUserChurch() {
		return userChurch;
	}
	public void setUserChurch(Integer userChurch) {
		this.userChurch = userChurch;
	}
	public Integer getUserAlcohol() {
		return userAlcohol;
	}
	public void setUserAlcohol(Integer userAlcohol) {
		this.userAlcohol = userAlcohol;
	}
	public String getPersSeq() {
		return persSeq;
	}
	public void setPersSeq(String persSeq) {
		this.persSeq = persSeq;
	}
	public Integer getPersText() {
		return persText;
	}
	public void setPersText(Integer persText) {
		this.persText = persText;
	}
	public String getHobbSeq() {
		return hobbSeq;
	}
	public void setHobbSeq(String hobbSeq) {
		this.hobbSeq = hobbSeq;
	}
	public Integer getHobbText() {
		return hobbText;
	}
	public void setHobbText(Integer hobbText) {
		this.hobbText = hobbText;
	}
	public Integer getPersonalCount() {
		return personalCount;
	}
	public void setPersonalCount(Integer personalCount) {
		this.personalCount = personalCount;
	}
	public Integer getHobbyCount() {
		return hobbyCount;
	}
	public void setHobbyCount(Integer hobbyCount) {
		this.hobbyCount = hobbyCount;
	}
	public String getPersnalText() {
		return persnalText;
	}
	public void setPersnalText(String persnalText) {
		this.persnalText = persnalText;
	}
	public String getHobbyText() {
		return hobbyText;
	}
	public void setHobbyText(String hobbyText) {
		this.hobbyText = hobbyText;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Integer getUserbodyShape() {
		return userbodyShape;
	}
	public void setUserbodyShape(Integer userbodyShape) {
		this.userbodyShape = userbodyShape;
	}
	public Integer getUserAge() {
		return userAge;
	}
	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}
	public int getPersnalDelNy() {
		return persnalDelNy;
	}
	public void setPersnalDelNy(int persnalDelNy) {
		this.persnalDelNy = persnalDelNy;
	}
	public int getHobbyDelNy() {
		return hobbyDelNy;
	}
	public void setHobbyDelNy(int hobbyDelNy) {
		this.hobbyDelNy = hobbyDelNy;
	}
	public String getUserLogTime() {
		return userLogTime;
	}
	public void setUserLogTime(String userLogTime) {
		this.userLogTime = userLogTime;
		
		if(userLogTime != null) {
			this.timeAgo = TimeAgoUtil.getTimeAgo(userLogTime);
		}
	}
	public String getTimeAgo() {
		return timeAgo;
	}
	public void setTimeAgo(String timeAgo) {
		this.timeAgo = timeAgo;
	}
	
}
