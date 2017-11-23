package com.pj.bos.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pj.bos.domain.base.Standard;

public interface IStandardDao extends JpaRepository<Standard, Integer> {

}
