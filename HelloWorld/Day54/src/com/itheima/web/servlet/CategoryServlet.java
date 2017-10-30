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
	//��ѯ���з���,����json��ʽ�ַ���
	public String findAllCategory(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		resp.setContentType("application/json;charset=utf-8");
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("jsonStr");
		if(null != jsonStr){//˵��redis����jsonStr�ַ���
//			System.out.println("redis����json");
			resp.getWriter().print(jsonStr);
		}else{//˵��redis��û��jsonStr�ַ���
//			System.out.println("redis��û��json");
			ICategoryService service = new CategoryServiceImp();
			List<Category> list = service.findAllCategory();
			jsonStr = JsonUtil.list2json(list);
			jedis.set("jsonStr", jsonStr);
			resp.getWriter().print(jsonStr);
		}
		return null;
	}
}