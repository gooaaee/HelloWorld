package com.pj.bos.action.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pj.bos.domain.base.Standard;
import com.pj.bos.service.base.IStandardService;
@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class StandardAction extends ActionSupport implements ModelDriven<Standard> {
	Standard model = new Standard();
	@Autowired
	private IStandardService service;
	
	@Action(value="standardAction_save",results={@Result(name="success",type="redirect",location="/pages/base/standard.html")})
	public String save(){
		service.save(model);
		return SUCCESS;
	}
	private int page;//当前页码
	private int rows;//每页显示的最大条数
	//standard.xml分页显示收派标准
	@Action("standardAction_findByPage")
	public void findByPage(){
		Pageable pageable = new PageRequest(page-1, rows);
		Page<Standard> page = service.findByPage(pageable);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", page.getTotalElements());//总条数
		map.put("rows", page.getContent());//页面显示的list集合
		String jsonStr = JSON.toJSONStringWithDateFormat(map, "yyyy-MM-dd");
		/*
		{                                                      
			"total":100,	
			"rows":[ 
				{"id":"001","name":"10-20公斤","operator":"张三","operatingTime":"2016-08-18","company":"杭州分部"}
			]
		}
		map是一个total/100,rows/对象的键值对集合
		*/
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//standard_findAll,快递员设置/替班中courier.html页面的增加按钮弹窗中的取件标准下拉菜单,ajax查询回显到下拉菜单中,查询的表是t_standard表
	@Action("standard_findAll")
	public void findAll(){
		List<Standard> list = service.findAll();
		String jsonStr = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public Standard getModel() {
		return model;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
}
