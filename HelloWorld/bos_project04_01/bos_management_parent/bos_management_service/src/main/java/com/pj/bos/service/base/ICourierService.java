package com.pj.bos.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.pj.bos.domain.base.Courier;

public interface ICourierService {
	void save(Courier model);
	void deleteBatch(String courierIds);
	//Page<Courier> findByPage(Pageable pageable);
	Page<Courier> findByPage(Specification<Courier> specification, Pageable pageable);

}
