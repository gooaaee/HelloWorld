package com.pj.bos.action.base;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pj.bos.domain.base.Area;
import com.pj.bos.service.base.IAreaService;
@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class AreaAction extends ActionSupport implements ModelDriven<Area>{
	private Area model = new Area();
	@Autowired
	private IAreaService service;
	private File areaFile;
	private String areaFileFileName;
	private String areaFileContentType;
	private int page;
	private int rows;
	@Action("areaFileImportXls")
	public void areaFileImportXls(){
		service.importXls(areaFile);
	}
	@Action("AreaAction_findByPage")
	public void findByPage(){
		Pageable pageable = new PageRequest(page-1,rows);
		Page<Area> page = service.findByPage(pageable);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", page.getTotalElements());
		map.put("rows", page.getContent());
		String jsonStr = JSON.toJSONString(map);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setAreaFile(File areaFile) {
		this.areaFile = areaFile;
	}
	public void setAreaFileFileName(String areaFileFileName) {
		this.areaFileFileName = areaFileFileName;
	}
	public void setAreaFileContentType(String areaFileContentType) {
		this.areaFileContentType = areaFileContentType;
	}
	@Override
	public Area getModel() {
		return model;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
}
