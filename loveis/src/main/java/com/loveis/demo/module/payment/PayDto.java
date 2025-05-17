package com.loveis.demo.module.payment;

//import com.fasterxml.jackson.annotation.JsonProperty;

public class PayDto {

//	@JsonProperty("paymentKey")
    private String paymentKey;
    
//    @JsonProperty("orderId")
    private String orderId;
    
//    @JsonProperty("amount")
    private Integer amount;
//    -----
	public String getPaymentKey() {
		return paymentKey;
	}
	public void setPaymentKey(String paymentKey) {
		this.paymentKey = paymentKey;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}
