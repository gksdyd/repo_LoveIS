package com.loveis.demo.module.payment;

public class PayDto {
	// 결제 승인 요청 DTO
//    public static class PaymentConfirmRequest {
//        private String paymentKey;
//        private String orderId;
//        private Integer amount;
//        
//        // getters and setters
//        public String getPaymentKey() { return paymentKey; }
//        public void setPaymentKey(String paymentKey) { this.paymentKey = paymentKey; }
//        public String getOrderId() { return orderId; }
//        public void setOrderId(String orderId) { this.orderId = orderId; }
//        public Integer getAmount() { return amount; }
//        public void setAmount(Integer amount) { this.amount = amount; }
//    }
	private String paymentKey;
    private String orderId;
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
