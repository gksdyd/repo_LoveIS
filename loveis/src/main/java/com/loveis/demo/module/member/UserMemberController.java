package com.loveis.demo.module.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loveis.demo.module.base.BaseController;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/love/signup")
public class UserMemberController extends BaseController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/SignupUserForm")
	public String signupUserForm(MemberVo vo, HttpSession httpSession) throws Exception {
		
	    return "love/signup/SignupUserForm";
	}
}
