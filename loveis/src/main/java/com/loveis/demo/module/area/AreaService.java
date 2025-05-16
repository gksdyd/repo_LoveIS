package com.loveis.demo.module.area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveis.demo.module.base.BaseService;

@Service
public class AreaService extends BaseService {

	@Autowired
	AreaDao areaDao;
	
	public int update(AreaDto dto) {
		return areaDao.update(dto); 
	}
	
	public List<AreaDto> selectList() {
		return areaDao.selectList();
	}
	
	public List<AreaDto> selectListLocal(AreaVo vo) {
		return areaDao.selectListLocal(vo);
	}
}
