package com.itheima.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.itheima.domain.PageModel;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import com.itheima.service.serviceImp.ProductServiceImp;
import com.itheima.tools.DateJsonValueProcessor;
import com.itheima.tools.UUIDUtils;
import com.itheima.web.base.BaseServlet;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		IProductService service = new ProductServiceImp();
		Product product = service.findProductByPid(pid);
		request.setAttribute("product", product);
		return "/jsp/product_info.jsp";
	}
	//header.jsp跳转,findCategoryProduct
	public String findCategoryProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		IProductService service = new ProductServiceImp();
	/*
		//显示所有商品
		//List<Product> list = service.findCategoryProduct(cid);
		//request.setAttribute("productList", list);
	*/
		//接下来代码的目的都是为了分页初始化
		int pageSize = 12;
		//获取num的值
		int num = 1;
		String nm = request.getParameter("num");
		if(null != nm && "".equals(nm)){
			num = Integer.parseInt(nm);
		}
		//获取pageModel对象
		PageModel pageModel = service.getPageModel(num,pageSize,cid);
		//page属性名为了衔接pageFile.jsp模版中使用的属性名
		request.setAttribute("page", pageModel);
		
		return "/jsp/product_list.jsp";
	}
	public String getAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		IProductService service = new ProductServiceImp();
		List<Product> list = service.getAllProduct();
		int total = list.size();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", list);
		map.put("total", total);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());
		String jsonStr = JSONObject.fromObject(map,jsonConfig).toString();
		System.out.println(jsonStr);
		response.getWriter().print(jsonStr);
		return null;
	}
	//getAllProductWithPage
	public String getAllProductWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		IProductService service = new ProductServiceImp();
		//初始化pageModel,easyui,的currentPageNum等价于easyui的page
		//new PageModel(currentPageNum, totalRecords, pageSize)
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		int page = Integer.parseInt(pageStr);
		int pageSize = Integer.parseInt(pageSizeStr);
		PageModel pageModel = service.getPageModel(page,pageSize);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", pageModel.getTotalRecords());
		map.put("rows", pageModel.getRecords());
		//由于MAP中的集合中的商品上时间日期类型转换有问题. 用定义好的工具类DateJsonValueProcessor处理
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());
		String jsonStr = JSONObject.fromObject(map,jsonConfig).toString();
		
		response.getWriter().print(jsonStr);
		return null;
	}
	public String addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//<form id="ff" style="paddind-top:30px;" method="post" enctype="multipart/form-data">
		//form表单是enctype="multipart/form-data"
		Map<String,String> map = new HashMap<String,String>();
		Product pro = new Product();
		
		try {
			DiskFileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(fac);
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem fileItem:list){
				if(fileItem.isFormField()){
					//FileItem是普通项
					//System.out.println(fileItem.getFieldName());//FieldName:表单中的<input name="FieldName"/>name属性
					//System.out.println(fileItem.getName());//null
					//System.out.println(fileItem.getString());//用户在文本框中输入的数据
					String fieldName = fileItem.getFieldName();
					String fieldValue = fileItem.getString("utf-8");
					map.put(fieldName, fieldValue);
				}else{
					//FileItem是上传项
					//System.out.println(fileItem.getFieldName());//FieldName:表单中的<input name="FieldName"/>name属性
					//System.out.println(fileItem.getName());//getName是获取上传文件的文件名
					//System.out.println(fileItem.getString());//是获取到上传文件的二进制编码内容
					String fieldName = fileItem.getFieldName();
					String fileName = fileItem.getName();
					//创建一个与上传文件同名的新文件
					String realPath = getServletContext().getRealPath("/products/3/");
					File file = new File(realPath, fileName);
					if(!file.exists()){
						file.createNewFile();
					}
					fileItem.write(file);
					map.put(fieldName, "/products/3/"+fileName);
				}
			}
			//封装map中的数据到product对象中
			BeanUtils.populate(pro, map);
			pro.setPid(UUIDUtils.getId());
			pro.setPdate(new Date());
			System.out.println(pro);
			
			IProductService service = new ProductServiceImp();
			service.addProduct(pro);
			response.getWriter().print("addProductOK");
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return null;
	}
}