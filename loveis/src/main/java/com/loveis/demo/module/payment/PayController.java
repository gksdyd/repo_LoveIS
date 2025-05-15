package com.loveis.demo.module.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/love/payment")
public class PayController {

	@RequestMapping(value = "/PayLoveList")
	public String payLoveList() {
		return "love/payment/PayLoveList";
	}
	
	@RequestMapping(value = "/PayLoveSuccess")
	public String paysuccess() {
		return "love/payment/TossPaymentsSucess";
	}
	
	@RequestMapping(value = "/PayLoveFail")
	public String payfail() {
		return "love/payment/TossPaymentsFail";
	}
	
//	결제 승인 처리 메서드
    @PostMapping("/confirm")
    @ResponseBody
    public ResponseEntity<String> confirmPayment(@RequestBody PayDto payDto) {
        
        System.out.println("결제 승인 요청 받음:");
        System.out.println("paymentKey: " + payDto.getPaymentKey());
        System.out.println("orderId: " + payDto.getOrderId());
        System.out.println("amount: " + payDto.getAmount());
        
        // 2. 실제로는 여기서 Toss Payments API를 호출해야 하지만,
        //    테스트를 위해 일단 성공 응답만 반환
        
        // 3. 성공 응답 반환
        return ResponseEntity.ok("결제가 성공적으로 완료되었습니다");
    }
}
