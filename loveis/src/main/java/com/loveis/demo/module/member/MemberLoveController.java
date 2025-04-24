package com.loveis.demo.module.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/love/member")
public class MemberLoveController {

	@RequestMapping(value = "/MemberLoveList")
	public String memberLoveList() {
		return "love/member/MemberLoveList";
	}
}
