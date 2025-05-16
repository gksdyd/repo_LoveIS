package com.loveis.demo.module.area;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.loveis.demo.module.base.BaseDao;

@Repository
public interface AreaDao extends BaseDao {

	public int update(AreaDto dto);
	public List<AreaDto> selectList();
	public List<AreaDto> selectListLocal(AreaVo vo);
}
