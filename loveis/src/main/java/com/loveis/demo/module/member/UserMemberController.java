package com.loveis.demo.module.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveis.demo.module.base.BaseController;
import com.loveis.demo.module.base.Constants;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/love/user")
public class UserMemberController extends BaseController {
	
	@Autowired
	MemberService memberService;
	
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
			httpSession.setAttribute("sessSeqXdm", rtMember.getUserSeq());
			httpSession.setAttribute("sessIdXdm", rtMember.getUserId());
			httpSession.setAttribute("sessNameXdm", rtMember.getUserName());
			
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
}
