package com.loveis.demo.module.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loveis.demo.module.member.MemberService;

@Controller
@RequestMapping(value = "/love/index")
public class IndexLoveController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/IndexLoveView")
	public String indexLoveView(Model model) {
		model.addAttribute("item", memberService.memberCount());
		return "love/index/IndexLoveView";
	}
}
