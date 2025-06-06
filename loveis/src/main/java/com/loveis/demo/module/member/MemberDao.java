package com.loveis.demo.module.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.loveis.demo.module.base.BaseDao;
import com.loveis.demo.module.base.BaseDto;

@Repository
public interface MemberDao extends BaseDao {

	public List<MemberDto> selectList(MemberVo vo);
	public List<ActivityDto> selectActivity(MemberVo vo);
	public List<Member4ListDto> selectOneList4Hobb(MemberVo vo);
	public List<Member4ListDto> selectOneList4Pers(MemberVo vo);
	public List<BaseDto> selectOneList4Pic(ActivityDto activityDto);
	public int selectOneCount(MemberVo vo);
	public MemberDto selectOne4Pic(MemberDto memberDto);
	public MemberDto selectOne(MemberDto memberDto);
	public MemberDto selectOneLogin(MemberDto memberDto);
	public int update(MemberDto memberDto);
	public int ueleteBackgroundImage(MemberDto memberDto);
	public int ueleteProfileImage(MemberDto memberDto);
	public int updateSingle(MemberDto memberDto);
	public int updateSingleIntroduce(MemberDto memberDto);
	public int insert(MemberDto memberDto);
	public void insertActivity(ActivityDto activityDto);
	public int checkId(MemberDto memberDto);
	public int checkEmail(MemberDto memberDto);
	public int updatePassword(MemberDto memberDto);
	public int uelete(MemberDto memberDto);
	public MemberDto checkPhone(MemberDto memberDto);
	public int reviewInsert(MemberDto memberDto);
	public int insertUploaded(BaseDto dto);
	public List<MemberDto> selectMemberList(MemberVo vo);
	public int selectMemberCount(MemberVo vo);
	
	public int logging(MemberDto memberDto);
	public int logout(MemberDto memberDto);
	
	public void personalityInsert(@Param("listDto") List<MemberDto> listDto);
	public void hobbyInsert(@Param("listDto") List<MemberDto> listDto);
	public int deletePersonalityByUser(MemberDto memberDto);
	public int deleteHobbyByUser(MemberDto memberDto);
	public MemberDto selectOne(MemberVo vo);
	public MemberDto googleLogin(String userEmail);
	public MemberDto facebookLogin(MemberDto memberDto);
	public MemberDto memberCount();
}
