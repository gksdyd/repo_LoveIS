package com.loveis.demo.module.ai;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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
	public ResponseEntity<Map<String, String>> callPythonApi(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		String uploadDir = "C:/uploads/";
	    String filename = file.getOriginalFilename();
	    
	    File dir = new File(uploadDir);
	    if (!dir.exists()) {
	        dir.mkdirs();  // 경로에 없는 모든 폴더를 생성
	    }
	    
	    File destFile = new File(uploadDir + filename);
        file.transferTo(destFile);
        
	    RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:5000/generate";
	    
	    FileSystemResource resource = new FileSystemResource(destFile);
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    
	    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	    body.add("file", resource);
	    
	    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
	    
	    ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);
	    
	    Map<String, String> responseMap = response.getBody();
	    String result = "";
	    if (responseMap != null) {
	        result = responseMap.get("result");
	    }

	    Map<String, String> returnMap = new HashMap<>();
	    returnMap.put("result", result);

	    return ResponseEntity.ok(returnMap);
	}
}
