package com.loveis.demo.module.ai;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.loveis.demo.module.base.BaseController;
import com.loveis.demo.module.code.CodeDto;
import com.loveis.demo.module.member.MemberService;
import com.loveis.demo.module.member.MemberVo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/love/ai")
public class AiController extends BaseController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/AiLoveList")
	public String aiLoveList(MemberVo vo, Model model, HttpSession httpSession) {
		vo.setUserSeq(httpSession.getAttribute("sessSeqUser").toString());
		model.addAttribute("item", memberService.selectOne(vo));
		model.addAttribute("code", CodeDto.cachedCodeArrayList);
		return "/love/ai/AiLoveList";
	}
	
	@RequestMapping("/CallPythonApi")
	public ResponseEntity<Map<String, String>> callPythonApi() {
	    RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:5000/generate";
	    Map<String, String> responseMap = restTemplate.getForObject(url, Map.class);
	    String result = responseMap.get("result");

	    Map<String, String> returnMap = new HashMap<>();
	    returnMap.put("result", result);

	    return ResponseEntity.ok(returnMap);
	}
}
