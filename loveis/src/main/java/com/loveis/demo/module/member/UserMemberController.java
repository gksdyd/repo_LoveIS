package com.loveis.demo.module.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String loginXdmForm(MemberDto vo, Model model) {
		return "xdm/user/LoginUserForm";
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
}
