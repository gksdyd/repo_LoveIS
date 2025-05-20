package com.loveis.demo.module.payment;

import com.loveis.demo.module.base.BaseDao;

public interface PayDao extends BaseDao {
	
	public int insert(PayDto payDto);
}
