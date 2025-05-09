package com.loveis.demo.module.member;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.loveis.demo.module.base.BaseService;

@Service
public class MemberService extends BaseService {

	@Autowired
	MemberDao memberDao;
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	public List<MemberDto> selectList(MemberVo vo) {
		return memberDao.selectList(vo);
	}
	
	public int selectOneCount(MemberVo vo) {
		return memberDao.selectOneCount(vo);
	}
	
	public MemberDto selectOne(MemberDto memberDto) {
		return memberDao.selectOne(memberDto);
	}
	
	public MemberDto selectOneLogin(MemberDto memberDto) {
		return memberDao.selectOneLogin(memberDto);
	}
	
	public int update(MemberDto memberDto) {
		return memberDao.update(memberDto);
	}
	
	public int updateSingle(MemberDto memberDto) {
		return memberDao.updateSingle(memberDto);
	}
	
	public int insert(MemberDto memberDto) throws Exception {
		memberDao.insert(memberDto);
		uploadFilesToS3(memberDto.getUploadImg1(), memberDto, "image", memberDto.getUploadImg1Type(), memberDto.getUploadImg1MaxNumber()
    			, memberDto.getUserSeq(), memberDao, amazonS3Client);
		return 1;
	}
	
	public int checkId(MemberDto memberDto) {
		return memberDao.checkId(memberDto);
	}
	
	public int checkEmail(MemberDto memberDto) {
		return memberDao.checkEmail(memberDto);
	}
	
	public int updatePassword(MemberDto memberDto) {
		return memberDao.updatePassword(memberDto);
	}
	
	public int uelete(MemberDto memberDto) {
		return memberDao.uelete(memberDto);
	}
	
	public MemberDto checkPhone(MemberDto memberDto) {
		return memberDao.checkPhone(memberDto);
	}
	
	public int reviewInsert(MemberDto memberDto) {
		return memberDao.reviewInsert(memberDto);
	}
	
	public List<MemberDto> selectMemberList(MemberVo vo) {
		return memberDao.selectMemberList(vo);
	}
	
	public int selectMemberCount(MemberVo vo) {
		return memberDao.selectMemberCount(vo);
	}
	
	public Integer calculateAge(String userBirth) {
		LocalDate now = LocalDate.now();
		
		int currYear = now.getYear();
		
		int birth = Integer.parseInt(userBirth.substring(0, 4));
		Integer age = (Integer)(currYear - birth + 1);
		return age;
	}
	
	
	public int deletePersonalityByUser(MemberDto memberDto) {
		return memberDao.deletePersonalityByUser(memberDto);
	}
	public int deleteHobbyByUser(MemberDto memberDto) {
		return memberDao.deleteHobbyByUser(memberDto);
	}
	
	public void personalityInsert(List<MemberDto> listDto, MemberDto memberDto) {
		memberDao.deletePersonalityByUser(memberDto);
		if (listDto != null && !listDto.isEmpty()) {
			memberDao.personalityInsert(listDto);
		}
	}
	public void hobbyInsert(List<MemberDto> listDto, MemberDto memberDto) {
		memberDao.deleteHobbyByUser(memberDto);
		if (listDto != null && !listDto.isEmpty()) {
			memberDao.hobbyInsert(listDto);
		}
	}
	
	
	
}
