package com.loveis.demo.module.google;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.loveis.demo.module.base.Constants;
import com.loveis.demo.module.member.MemberDto;
import com.loveis.demo.module.member.MemberService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/love/google")
public class GoogleController {

	@Value("${google.client.id}")
    private String googleClientId;
    
    @Value("${google.client.pw}")
    private String googleClientPw;
    
    @Autowired
    MemberService memberService;

    @RequestMapping(value="/Login", method = RequestMethod.GET)
    public String redirectToGoogleLogin() {
        String reqUrl = "redirect:" + "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=" + googleClientId
                + "&redirect_uri=http://localhost:8070/love/google/google"
                + "&response_type=code"
                + "&scope=email%20profile%20openid"
                + "&access_type=offline";
        return reqUrl;
    }
    
    @RequestMapping(value="/google", method = RequestMethod.GET)
    public String loginGoogle(@RequestParam(value = "code") String authCode, HttpSession httpSession){
        RestTemplate restTemplate = new RestTemplate();
        GoogleRequest googleOAuthRequestParam = new GoogleRequest();
        googleOAuthRequestParam.setClientId(googleClientId);
        googleOAuthRequestParam.setClientSecret(googleClientPw);
        googleOAuthRequestParam.setCode(authCode);
        googleOAuthRequestParam.setRedirectUri("http://localhost:8070/love/google/google");
        googleOAuthRequestParam.setGrantType("authorization_code");
        
        ResponseEntity<GoogleResponse> resultEntity = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleResponse.class);
        String jwtToken=resultEntity.getBody().getId_token();
        Map<String, String> map=new HashMap<>();
        map.put("id_token",jwtToken);
        ResponseEntity<GoogleInResponse> resultEntity2 = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo",
                map, GoogleInResponse.class);
        String email=resultEntity2.getBody().getEmail();
        
        MemberDto dto = memberService.googleLogin(email);
        if (dto != null) {
        	httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
			httpSession.setAttribute("sessSeqUser", dto.getUserSeq());
			httpSession.setAttribute("sessIdUser", dto.getUserId());
			httpSession.setAttribute("sessNameUser", dto.getUserName());
			httpSession.setAttribute("sessLocalUser", dto.getUserLocal());
			memberService.logging(dto);
        	return "redirect:/love/index/IndexLoveView";
        }
        return "redirect:/love/member/LoginLoveForm";
    }
    
    @ResponseBody
    @RequestMapping(value = "/FacebookLoveLogin")
    public Map<String, Object> facebookLoveLogin(MemberDto memberDto, HttpSession httpSession) {
    	Map<String, Object> rtMap = new HashMap<>();
    	
    	MemberDto dto = memberService.facebookLogin(memberDto);
    	if (dto != null) {
        	httpSession.setMaxInactiveInterval(60 * Constants.SESSION_MINUTE_XDM); // 60second * 30 = 30minute
			httpSession.setAttribute("sessSeqUser", dto.getUserSeq());
			httpSession.setAttribute("sessIdUser", dto.getUserId());
			httpSession.setAttribute("sessNameUser", dto.getUserName());
			httpSession.setAttribute("sessLocalUser", dto.getUserLocal());
			memberService.logging(dto);
        	rtMap.put("result", "success");
        } else {
        	rtMap.put("result", "fail");
        }
    	
    	return rtMap;
    }
}
