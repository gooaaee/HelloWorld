package com.pj.bos.service.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pj.bos.domain.base.Standard;
//ctrl+t展示实现类
public interface IStandardService {
	public void save(Standard standard);
	//standard.xml分页查询收派标准
	public Page<Standard> findByPage(Pageable pageable);
	public List<Standard> findAll();
}
