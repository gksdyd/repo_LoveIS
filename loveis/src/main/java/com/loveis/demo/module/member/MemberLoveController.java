package com.loveis.demo.module.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveis.demo.module.base.BaseController;
import com.loveis.demo.module.base.Constants;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/love/member")
public class MemberLoveController extends BaseController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/MemberLoveList")
	public String memberLoveList(@ModelAttribute("vo") MemberVo vo, Model model, HttpSession httpSession) {
		vo.setUserSeq(httpSession.getAttribute("sessSeqUser").toString());
		vo.setRowNumToShow(6);
		vo.setParamsPaging(memberService.selectMemberCount(vo));
		model.addAttribute("list", memberService.selectMemberList(vo));
		return "love/member/MemberLoveList";
	}
	@RequestMapping(value = "/MemberLoveMypage")
	public String MemberLoveMypage(Model model, MemberDto dto, HttpSession httpSession, @ModelAttribute Member4ListDto listDto) {
		dto.setUserSeq(httpSession.getAttribute("sessSeqXdm").toString());
		
		model.addAttribute("item", memberService.selectOne(dto));
		return "love/member/MemberLoveSingle";
	}
	@RequestMapping(value = "/MemberLoveMypageUpdt")
	public String MemberLoveMypageUpdt(MemberDto dto, @ModelAttribute Member4ListDto listDto) {
		List<MemberDto> dtoListP = new ArrayList<>();
		List<MemberDto> dtoListH = new ArrayList<>();
		
		if (listDto.getPersText() != null && !listDto.getPersText().isEmpty()) {
	        for (Integer code : listDto.getPersText()) {
	        	MemberDto memberDto = new MemberDto();
	        	memberDto.setUserSeq(listDto.getUser_userSeq());
	        	memberDto.setPersText(code);
	            dtoListP.add(memberDto);
	        }
	    }
		if (listDto.getHobbText() != null && !listDto.getHobbText().isEmpty()) {
			for (Integer code : listDto.getPersText()) {
				MemberDto memberDto = new MemberDto();
				memberDto.setUserSeq(listDto.getUser_userSeq());
				memberDto.setHobbText(code);
				dtoListH.add(memberDto);
			}
		}
		
		if (!dtoListP.isEmpty()) {
			memberService.personalityInsert(dtoListH, dto);
		} else {
//			memberService.deletePersonalityByUser(dto);
			
		}
		if (!dtoListH.isEmpty()) {
			memberService.hobbyInsert(dtoListH, dto);
		} else {
//			memberService.deleteHobbyByUser(dto);
		}
		memberService.updateSingle(dto);
		return "redirect:/love/member/MemberLoveMypage";
	}
	
	@RequestMapping(value = "/SignupUserForm")
	public String signupUserForm(MemberVo vo, HttpSession httpSession) throws Exception {
	    return "love/user/SignupUserForm";
	}
	
	@RequestMapping(value = "/LoginUserForm")
	public String loginUserForm(MemberDto vo, Model model) {
		return "love/user/LoginUserForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "/LoginUserProc")
	public Map<String, Object> loginUserProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		MemberDto rtMember = memberService.selectOneLogin(dto);
		
		if (rtMember != null && matchesBcrypt(dto.getUserPassword(), rtMember.getUserPassword(), 10)) {
			httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
			httpSession.setAttribute("sessSeqUser", rtMember.getUserSeq());
			httpSession.setAttribute("sessIdUser", rtMember.getUserId());
			httpSession.setAttribute("sessNameUser", rtMember.getUserName());
			httpSession.setAttribute("sessLocalUser", rtMember.getUserLocal());
			
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fali");
		}
		
		return returnMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/LogoutXdmProc")
	public Map<String, Object> logoutXdmProc(MemberDto dto, HttpSession httpSession) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		httpSession.setAttribute("sessSeqXdm", null);
		httpSession.setAttribute("sessIdXdm", null);
		httpSession.setAttribute("sessNameXdm", null);
		returnMap.put("rt", "success");
		return returnMap;
	}
	
	@RequestMapping(value = "/MemberLoveProc")
	public String memberLoveProc(@ModelAttribute("vo") MemberVo vo, Model model) {
		vo.setRowNumToShow(6);
		vo.setParamsPaging(memberService.selectMemberCount(vo));
		model.addAttribute("list", memberService.selectMemberList(vo));
		return "love/include/memberList :: memberList";
	}
	
	// 1. 비밀번호 찾기 화면 띄우기
    @GetMapping("/findPassword")
    public String findPasswordForm() {
        return "love/user/ForgetPasswordForm"; // findPassword 페이지로 이동
    }

    // 2. 비밀번호 찾기 처리
    @PostMapping("/findPassword")
    public String findPasswordSubmit(@RequestParam("username") String username,
                                      @RequestParam("email") String email,
                                      Model model) {
        // 여기서 DB 조회해서 username과 email이 일치하는지 확인
        boolean userExists = checkUser(username, email); // (가정) DB 조회 함수

        if (userExists) {
            model.addAttribute("message", "비밀번호 재설정 링크를 이메일로 보냈습니다.");
        } else {
            model.addAttribute("message", "일치하는 계정이 없습니다.");
        }

        return "findPasswordResult"; // 결과 보여주는 페이지로 이동
    }

    // 임시로 만든 유저 체크 함수 (DB 조회로 교체해야 함)
    private boolean checkUser(String username, String email) {
        // DB 조회 로직 필요 (예시: memberService.findByUsernameAndEmail(username, email))
        return "testuser".equals(username) && "test@email.com".equals(email);
    }
    
    @ResponseBody
    @RequestMapping(value = "/SignUpLoveProc")
    public void signUpLoveProc(MemberDto dto) throws Exception {
    	dto.setUserPassword(encodeBcrypt(dto.getUserPassword(), 10));
    	memberService.insert(dto);
    }
}
