package com.loveis.demo.module.payment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/love/payment")
public class payController {

	@RequestMapping(value = "/PayLoveList")
	public String payLoveList() {
		return "love/payment/PayLoveList";
	}
}
