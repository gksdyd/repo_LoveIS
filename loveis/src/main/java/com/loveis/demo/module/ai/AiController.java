package com.loveis.demo.module.ai;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "love/ai")
public class AiController {
	
	@RequestMapping(value = "/AiLoveList")
	public String codeXdmList() {

		return "/love/ai/AiLoveList";
	}
}
