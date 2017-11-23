package com.pj.bos.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pj.bos.dao.base.IStandardDao;
import com.pj.bos.domain.base.Standard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class JPATest {
	@Autowired
	private IStandardDao dao;
	@Test
	public void testAdd(){
		Standard standard = new Standard();
		standard.setMaxLength(10);
		standard.setMaxWeight(10);
		standard.setMinLength(1);
		standard.setMinWeight(1);
		standard.setOperatingCompany("北京分公司");
		standard.setOperatingTime(new Date());
		standard.setOperator("admin");
		dao.save(standard);
	}
}
