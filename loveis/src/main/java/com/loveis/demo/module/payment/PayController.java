package com.loveis.demo.module.payment;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
	
   @PostMapping("/PayLoveConfirm")
   @ResponseBody
   public ResponseEntity<String> confirmPayment(@RequestBody PayDto payDto) throws IOException, InterruptedException{
	   String requestBody = String.format(
		        "{\"paymentKey\":\"%s\",\"orderId\":\"%s\",\"amount\":%d}",
		        payDto.getPaymentKey(), payDto.getOrderId(), payDto.getAmount()
		    );
	   
	   HttpRequest request = HttpRequest.newBuilder()
			    .uri(URI.create("https://api.tosspayments.com/v1/payments/confirm"))
			    .header("Authorization", "Basic dGVzdF9za196WExrS0V5cE5BcldtbzUwblgzbG1lYXhZRzVSOg==")
			    .header("Content-Type", "application/json")
			    .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
			    .build();
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			
			System.out.println(response.body());
			
			return ResponseEntity.ok(response.body());
	 }   
}
