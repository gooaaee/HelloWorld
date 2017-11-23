package com.pj.bos.service.base;

import java.io.File;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pj.bos.domain.base.Area;

public interface IAreaService {
	void importXls(File areaFile);
	Page<Area> findByPage(Pageable pageable);
}
