package com.pj.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pj.bos.dao.base.IStandardDao;
import com.pj.bos.domain.base.Standard;
import com.pj.bos.service.base.IStandardService;
@Service
@Transactional
public class StandardServiceImpl implements IStandardService {
	@Autowired
	private IStandardDao dao;
	@Override
	public void save(Standard standard) {
		dao.save(standard);
	}
	@Override
	public List<Standard> findAll() {
		return dao.findAll();
	}
	@Override
	public Page<Standard> findByPage(Pageable pageable) {
		return dao.findAll(pageable);
	}

}
