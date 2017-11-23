package com.pj.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pj.bos.domain.base.Area;

public interface IAreaDao extends JpaRepository<Area, String> {

}
