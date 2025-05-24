package com.loveis.demo.module.ai;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public ResponseEntity<Map<String, String>> callPythonApi(@RequestParam(value = "file", required = false) MultipartFile file, 
			@RequestParam("mbti") String mbti, @RequestParam(value = "url", required = false) String awsUrl) 
			throws IllegalStateException, IOException {
		String uploadDir = "C:/uploads/";
		String filename = "";
		String text = "";
		File destFile;
	    
	    File dir = new File(uploadDir);
	    if (!dir.exists()) {
	        dir.mkdirs();  // 경로에 없는 모든 폴더를 생성
	    }
	    
	    if (awsUrl == null) {
	    	filename = file.getOriginalFilename();
	    	destFile = new File(uploadDir + filename);
	    	file.transferTo(destFile);
	    	text = "사진의 사람은 나인데 내 오늘 패션 어때?";
	    } else {
	    	 // AWS 이미지 URL 처리
	        filename = awsUrl.substring(awsUrl.lastIndexOf("/") + 1);
	        destFile = new File(uploadDir + filename);
	        text = "내 프로필 사진 어때?";

	        try (InputStream in = new URL(awsUrl).openStream();
	             FileOutputStream out = new FileOutputStream(destFile)) {
	            byte[] buffer = new byte[4096];
	            int n;
	            while ((n = in.read(buffer)) != -1) {
	                out.write(buffer, 0, n);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(Map.of("result", "AWS 이미지 다운로드 실패: " + e.getMessage()));
	        }
	    }
	    
	    text = mbti + "처럼 말해줘!" + text;
        
	    RestTemplate restTemplate = new RestTemplate();
	    String url = "http://localhost:5000/generate";
	    
	    FileSystemResource resource = new FileSystemResource(destFile);
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	    
	    MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	    body.add("file", resource);
	    body.add("text", text);
	    
	    HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
	    
	    ResponseEntity<Map> response = restTemplate.postForEntity(url, requestEntity, Map.class);
	    
	    Map<String, String> responseMap = response.getBody();
	    String result = "";
	    if (responseMap != null) {
	        result = responseMap.get("result");
	    }

	    Map<String, String> returnMap = new HashMap<>();
	    returnMap.put("result", result);
	    returnMap.put("chat", text);

	    return ResponseEntity.ok(returnMap);
	}
	
	@RequestMapping(value = "/AiLoveChat")
	public String aiLoveChat(@ModelAttribute("vo") MemberVo vo, Model model, HttpSession httpSession) {
		vo.setUserSeq(httpSession.getAttribute("sessSeqUser").toString());
		model.addAttribute("item", memberService.selectOne(vo));
		return "/love/ai/AiLoveChat";
	}
}
