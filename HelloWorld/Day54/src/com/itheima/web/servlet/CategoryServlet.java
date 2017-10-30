package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

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
	//查询所有分类,传回json格式字符串
	public String findAllCategory(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		resp.setContentType("application/json;charset=utf-8");
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("jsonStr");
		if(null != jsonStr){//说明redis中有jsonStr字符串
//			System.out.println("redis中有json");
			resp.getWriter().print(jsonStr);
		}else{//说明redis中没有jsonStr字符串
//			System.out.println("redis中没有json");
			ICategoryService service = new CategoryServiceImp();
			List<Category> list = service.findAllCategory();
			jsonStr = JsonUtil.list2json(list);
			jedis.set("jsonStr", jsonStr);
			resp.getWriter().print(jsonStr);
		}
		return null;
	}
}