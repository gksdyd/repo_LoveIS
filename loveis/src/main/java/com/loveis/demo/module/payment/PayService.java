package com.loveis.demo.module.payment;

import org.springframework.beans.factory.annotation.Autowired;

import com.loveis.demo.module.base.BaseService;

public class PayService extends BaseService {
	
	@Autowired
	PayDao payDao;
	
	public int insert(PayDto payDto) {
		return payDao.insert(payDto);
	}
}
