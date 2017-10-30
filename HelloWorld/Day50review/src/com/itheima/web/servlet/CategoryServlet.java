package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.service.ICategoryService;
import com.itheima.service.serviceImp.CategoryServiceImp;
import com.itheima.tools.JedisUtils;
import com.itheima.tools.JsonUtil;
import com.itheima.web.base.BaseServlet;

import redis.clients.jedis.Jedis;

public class CategoryServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	//查询所有分类
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		Jedis jedis = JedisUtils.getJedis();
		String allCat = jedis.get("allCat");
		if(null == allCat){
			//jedis中不存在allCat
			ICategoryService service = new CategoryServiceImp();
			List<Category> list = service.getAllCategory();
			String jsonStr = JsonUtil.list2json(list);
			jedis.set("allCat", jsonStr);
			System.out.println(jsonStr);
			response.getWriter().print(jsonStr);
		}else{
			//jedis中存在allCat
			response.getWriter().print(allCat);
		}
		return null;
	}
}
