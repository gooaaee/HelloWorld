package com.pj.bos.action.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pj.bos.domain.base.Courier;
import com.pj.bos.service.base.ICourierService;
@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class CourierAction  extends ActionSupport implements ModelDriven<Courier> {
	private Courier model = new Courier();
	@Autowired
	private ICourierService service;
	@Action("courierAction_save")
	public void save(){
		Map<String,Object> map = new HashMap<String,Object>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		try {
			service.save(model);
			map.put("success", true);
			map.put("message", "操作成功");
			String jsonStr = JSON.toJSONString(map);
			response.getWriter().write(jsonStr);
		} catch (Exception e) {
			map.put("success", false);
			map.put("message", "操作失败");
			String jsonStr = JSON.toJSONString(map);
			try {
				response.getWriter().write(jsonStr);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	private int page;//当前页码
	private int rows;//每页显示的最大条数
	//courier.xml分页显示收派标准
	//有两种动作会使用该动作方法,当点击"快递员设置/替换"
	@Action("courierAction_findByPage")
	public void findByPage(){
		//组合条件
		Specification<Courier> specification = new Specification<Courier>(){
			@Override
			public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//courierNum/company/type/standard.name
				List<Predicate> list = new ArrayList<Predicate>();
				if(StringUtils.isNotBlank(model.getCourierNum())){
					Predicate predicate = cb.like(root.get("courierNum").as(String.class),"%" + model.getCourierNum()+"%");
					list.add(predicate);
				}
				if(StringUtils.isNotBlank(model.getCompany())){
					Predicate predicate = cb.like(root.get("courierNum").as(String.class),"%" + model.getCompany()+"%");
					list.add(predicate);
				}
				if(StringUtils.isNotBlank(model.getType())){
					Predicate predicate = cb.like(root.get("courierNum").as(String.class),"%" + model.getType()+"%");
					list.add(predicate);
				}
				if(null != model.getStandard() && StringUtils.isNotBlank(model.getStandard().getName())){
					Predicate predicate = cb.like(root.get("courierNum").as(String.class),"%" + model.getStandard().getName()+"%");
					list.add(predicate);
				}
				//list-->数组-->Predicate
				if(list.size() != 0){
					Predicate[] predicates = new Predicate[list.size()];
					list.toArray(predicates);
					Predicate ands = cb.and(predicates);
					return ands;
				}
				//条件为空,或直接展示全部快递员
				return null;
			}
		};
		Pageable pageable = new PageRequest(page-1, rows);
		Page<Courier> page = service.findByPage(specification,pageable);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", page.getTotalElements());//总条数
		map.put("rows", page.getContent());//页面显示的list集合
		String jsonStr = JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		try {
			//int i = 1/0;
			response.getWriter().write(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String courierIds;
	//批量删除快递员,逻辑删除
	@Action("courierAction_deleteBatch")
	public void deleteBatch(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", false);
		map.put("message", "作废操作失败");
		String jsonStr = JSON.toJSONString(map);
		try {
			service.deleteBatch(courierIds);
			map.put("success", true);
			map.put("message", "作废操作成功");
			jsonStr = JSON.toJSONString(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setCourierIds(String courierIds) {
		this.courierIds = courierIds;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	@Override
	public Courier getModel() {
		return model;
	}

}
