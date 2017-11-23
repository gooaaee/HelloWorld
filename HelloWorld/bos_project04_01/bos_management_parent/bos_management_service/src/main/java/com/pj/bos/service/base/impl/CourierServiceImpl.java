package com.pj.bos.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.bos.dao.base.ICourierDao;
import com.pj.bos.domain.base.Courier;
import com.pj.bos.service.base.ICourierService;
@Service
@Transactional
public class CourierServiceImpl implements ICourierService {
	@Autowired
	private ICourierDao dao;
	@Override
	public void save(Courier model) {
		dao.save(model);
	}
	@Override
	public void deleteBatch(String courierIds) {
		String[] ids = courierIds.split(",");
		for(String id:ids){
			//dao.delete(Integer.parseInt(id));//物理删除
			dao.updateDeltag(Integer.parseInt(id));//逻辑删除,把deltag字段置1
		}
	}
//	@Override
//	public Page<Courier> findByPage(Pageable pageable) {
//		return dao.findAll(pageable);
//	}
	@Override
	public Page<Courier> findByPage(Specification<Courier> specification, Pageable pageable) {
		return dao.findAll(specification, pageable);
	}
}
