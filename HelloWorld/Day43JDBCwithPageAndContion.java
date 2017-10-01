/*
 *
 *
 *删除选中商品
 *
 *
 *
 */
//index.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
      <jsp:forward page="/RetrieveServlet"></jsp:forward>
</body>
</html>

//productList.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示全部商品信息</title>
</head>
<body>
<table border="1" width="80%" align="center"> 
	<tr>
		<th>
			<input type="button" name="delBtn" id="delBtn" value="删除"/>
			<input type="checkbox" name="delAllCheckbox" id="delAllCheckbox"/>
		</th>
		<th>序号</th>
		<th>商品名称</th>
		<th>市场价格</th>
		<th>商场价格</th>
		<th>图片</th>
		<th>上架时间</th>
		<th>是否热门</th>
		<th>商品描述</th>
		<th>是否上架</th>
		<th>修改</th>
		<th>删除</th>
	</tr>
	<c:forEach varStatus="status" items="${requestScope.productList}" var="item">
	<tr>
		<td><input type="checkbox" name="delCheckbox" value="${item.pid}"/></td>
		<td>${status.count}</td>
		<td>${item.pname}</td>
		<td>${item.market_price}</td>
		<td>${item.shop_price}</td>
		<td><img src="${item.pimage}" width="50px"/></td>
		<td>${item.pdate}</td>
		<!-- 是否热门,1代表热门,0代表不热门 -->
		<td>
			<c:if test="${item.is_hot == 1}">
				是
			</c:if>
			<c:if test="${item.is_hot == 0}">
				否
			</c:if>
		</td>
		<td>${item.pdesc}</td>
		<!-- 是否下架,1代表下架,0代表未下架 -->
		<td>
			<c:if test="${item.pflag==1}">
				下架
			</c:if>
			<c:if test="${item.pflag==0}">
				未下架
			</c:if>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/update?pid=${item.pid}">修改</a>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/delete?pid=${item.pid}">删除</a>
		</td>
	</tr>
	</c:forEach>
</table>
	<center>
		共10页/第1页
		<a href="#">首页</a>
		<a href="#">上一页</a>
		<a href="#">1</a>
		<a href="#">2</a>
		<a href="#">3</a>
		<a href="#">4</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=5">5</a>
		<a href="#">6</a>
		<a href="#">7</a>
		<a href="#">8</a>
		<a href="#">9</a>
		<a href="#">下一页</a>
		<a href="#">末页</a>
		<input type="text" />
		<input type="button" value="前往"/>
	</center>
<center>
	<a href="${pageContext.request.contextPath}/categoryServlet">添加商品</a>
</center>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script>
$(function(){
	
	/*
	 * 页面加载完毕,为删除链接绑定点击事件功能
	 */
	//$("#myId").click(function(){alert("TTTTT");});
	//$(".myClass").click(function(){alert("TTTTT");});
	$("a:contains('删除')").click(function(){
		//alert("QQQQQ");
		
		/*
		if(confirm("忍心删除?")){
			return true;
		}
		
		//阻止链接跳转
		return false;
		*/
		
		return confirm("忍心删除?");
	});
	
	/*
	 * 全选全消功能
	 */
	//当点击全选复选框时,其他复选框全选
	$("#delAllCheckbox").click(function(){
		$("input[type='checkbox']").prop("checked",this.checked);
	});
	$("input[name='delCheckbox']").click(function(){
		//当点击非全选复选框时,如果全部选中,则自动勾选全选复选框
		if($("input[name='delCheckbox']:checked").length==$("input[name='delCheckbox']").length){
			$("#delAllCheckbox").prop("checked",true);
		}
		//当全选时,如果勾掉其中一个复选框时,全选复选框也自动勾掉
		if($("input[name='delCheckbox']:checked").length != $("input[name='delCheckbox']").length){
			$("#delAllCheckbox").prop("checked",false);
		}
	});
	
	/*
	 * 点击删除按钮提醒"忍心删除吗?"
	 * 可以一条一条删除,使用事务,我是使用sql语句拼接
	 */
	 $("#delBtn").click(function(){
		 if(confirm("你忍心删除吗?")){
			 //拼接参数,?pid=1&pid=2...
			 var params = $("input[name='delCheckbox']:checked").serialize();
			 location.href = "${pageContext.request.contextPath}/DelSomeProductServlet?" + params;
		 }
	 });
	//加载事件完成
});
</script>
</body>
</html>
    
//RetriveveServlet.java
package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;

public class RetrieveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置请求参数的编码格式
		request.setCharacterEncoding("utf-8");
		// 设置响应的编码格式
		response.setContentType("text/html;charset=utf-8");
		// 访问service层进行业务判断,返回产品对象集合
		ProductService service = new ProductService();
		List<Product> list = service.retrieveProduct();
		// 把数据库查到的产品集合放到request对象的属性中
		request.setAttribute("productList", list);
		// 请求转发到productList.jsp页面
		request.getRequestDispatcher("/productList.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

//CategoryServlet.java
package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.service.CategoryService;

public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//请求头的编码格式没有设置用new String("".getBytes("iso-8859-1"),"utf-8")
		//设置请求体的编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应的编码格式
		response.setContentType("text/html;charset=utf-8");
		//访问sevice层,获取所有的分类
		CategoryService service = new CategoryService();
		List<Category> list = service.getAllCategory();
		//设置request属性,把该属性传递到addProduct.jsp中,可以通过jsp页面的request内置对象访问到
		request.setAttribute("categoryList", list);
		//请求转发到addProduct.jsp
		request.getRequestDispatcher("/addProduct.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

//addProduct.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加商品</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/addProduct">
<table border="1" width="50%" align="center">

<tr><td>商品名称:<input type="text" name="pname" value="${requestScope.updatingProduct.pname}"/></td></tr>
<tr><td>市场价格:<input type="text" name="market_price" value="${requestScope.updatingProduct.market_price}"/></td></tr>
<tr><td>商城价格:<input type="text" name="shop_price" value="${requestScope.updatingProduct.shop_price}"/></td></tr>
<tr><td>商品图片:<input type="text" name="pimage" value="${requestScope.updatingProduct.pimage}"/></td></tr>
<!-- 热门是1,不热门是0 -->
<tr><td>是否热门:
	<!-- 1代表热门 -->
	<c:if test="${requestScope.updatingProduct.is_hot==1}">
      <input type="radio" name="is_hot" value="1" checked="checked"/>是
      <input type="radio" name="is_hot" value="0"/>否
	</c:if>
	<!-- 0代表不热门 ,该判断语句是除了热门之外的其他所有可能都执行下列语句-->
	<c:if test="${requestScope.updatingProduct.is_hot != 1}">
		<input type="radio" name="is_hot" value="1" />是
		<input type="radio" name="is_hot" value="0" checked="checked"/>否
	</c:if>
</td></tr>
<tr><td>商品描述:<textarea cols="20" rows="10" name="pdesc">${requestScope.updatingProduct.pdesc}</textarea></td></tr>
<!-- 是否上架,未下架:0,下架:1 -->
<tr><td>是否上架:
	<!-- 1代表下架,这个是假设除了未下架之外的所有可能 -->
	<c:if test="${requestScope.updatingProduct.pflag != 0}">
		<input type="radio" name="pflag" value="0" />是
		<input type="radio" name="pflag" value="1" checked="checked"/>否
	</c:if>
	<!-- 0代表未下架 -->
	<c:if test="${requestScope.updatingProduct.pflag == 0}">
		<input type="radio" name="pflag" value="0" checked="checked"/>是
		<input type="radio" name="pflag" value="1"/>否
	</c:if>
</td></tr>
<tr>
	<td>所属分类
	   <select name="cid">
	   		<c:forEach items="${categoryList}" var="category" varStatus="status">
	   			<!-- 通过UpdateServlet获取到 -->
	   			<!-- 如果UpdateServlet中的updatingProduct属性等于所有分类中的某一个,则设置该分类的selected属性为selected -->
	   			<c:if test="${requestScope.updatingProduct.cid == category.cid}">
	   				<option value="${status.count}" selected="selected">${category.cname}</option>
	   			</c:if>
	   			<c:if test="${requestScope.updatingProduct.cid != category.cid}">
	   				<option value="${status.count}">${category.cname}</option>
	   			</c:if>
	   		</c:forEach>
	   </select>
   </td>
</tr>
<tr><td><button>添加</button></td></tr>
</table>   
</form>

</body>
</html>
			    
//AddProductServlet.java
package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.Product;
import com.itheima.service.AddProductService;

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应编码格式
		response.setContentType("text/html;charset=utf-8");
		//获取请求数据
		Map<String, String[]> parameterMap = request.getParameterMap();
		//封装数据
		Product product = new Product();
		try {
			BeanUtils.populate(product, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//添加数据到数据库,跳转到service进行逻辑判断
		AddProductService service = new AddProductService();
		service.addProduct(product);
		//设置请求属性(无)
		//重定向
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		
	}
}

//CategoryService.java
package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.CategoryDao;
import com.itheima.domain.Category;

public class CategoryService {

	public List<Category> getAllCategory() {
		CategoryDao dao = new CategoryDao();
		List<Category> list = null;
		try {
			list = dao.getAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

//AddProudctService.java
package com.itheima.service;

import java.sql.SQLException;

import com.itheima.dao.AddProductDao;
import com.itheima.domain.Product;

public class AddProductService {

	public void addProduct(Product product) {
		AddProductDao dao = new AddProductDao();
		try {
			int rows = dao.addProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

//ProductService.java
package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.ProductDao;
import com.itheima.domain.PageModel;
import com.itheima.domain.Product;

public class ProductService {
	//查询所有的库中商品
	public List<Product> retrieveProduct() {
		//转到dao层进行数据库查询
		ProductDao dao = new ProductDao();
		List<Product> list = null;
		try {
			list = dao.retrieveProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//点击修改时,找到该行对应数据库中的数据,并保存到product对象中,并返回
	public Product findOneProduct(String pid) {
		ProductDao dao = new ProductDao();
		Product product = null;
		try {
			product = dao.findOneProduct(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	//点击updateProduct.jsp中的修改按钮时,修改数据库中的数据
	public void update(Product product) {
		ProductDao dao = new ProductDao();
		try {
			dao.update(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//productList.jsp页面中的删除,删除数据库中的该条记录
	public void delete(String pid) {
		ProductDao dao = new ProductDao();
		try {
			dao.delete(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//点击productList.jsp页面中的删除按钮时,删除勾选中的复选框对应数据库中的数据,也可以使用事务
	public void delSomeRecordByPid(String[] pids) {
		ProductDao dao = new ProductDao();
		try {
			dao.delSomeRecordByPid(pids);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//在productList2.jsp中分页展示给用户商品,currentPage:用户点击的链接页,pageSize:每页展示的商品条数
	public PageModel showPage(int currentPage, int pageSize) {
		ProductDao dao = new ProductDao();
		PageModel pageModel = null; 
		List<Product> list = null;
		int totalRecords = 0;
		try {
			totalRecords = dao.getTotalRecords();
			pageModel = new PageModel(currentPage, pageSize, totalRecords);
			list = dao.showPage(pageModel);
			pageModel.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageModel;
	}
}

//CategoryDao.java
package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.domain.Category;
import com.itheima.tools.JDBCUtils;

public class CategoryDao {
	//查询category表中的所有分类,用BeanListHandler<Category>(Category),返回Category对象的list集合
	public List<Category> getAllCategory() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from category";
		List<Category> list = qr.query(sql, new BeanListHandler<Category>(Category.class));
		return list;
	}

}

//AddProductDao.java
package com.itheima.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.dbutils.QueryRunner;

import com.itheima.domain.Product;
import com.itheima.tools.JDBCUtils;

public class AddProductDao {

	public int addProduct(Product product) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		//pname,market_price,shop_price,pimage,is_hot,pdesc,pflag,cid
		String sql = "insert into product(pid,pname,market_price,shop_price,pimage,is_hot,pdesc,pflag,cid,pdate)"
				+ " value(?,?,?,?,?,?,?,?,?,?)";
		Date date = new Date();
		String id = UUID.randomUUID().toString();
		String pid = id.replaceAll("-", "").toUpperCase();
		System.out.println(id.length());
		Object[] params = {pid,product.getPname(),product.getMarket_price(),
				product.getShop_price(),product.getPimage(),product.getIs_hot(),
				product.getPdesc(),product.getPflag(),product.getCid(),date};
		int rows = qr.update(sql, params);
		return rows;
	}

}

//ProductDao.java
package com.itheima.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.domain.PageModel;
import com.itheima.domain.Product;
import com.itheima.tools.JDBCUtils;

public class ProductDao {
	//查询所以库中商品
	public List<Product> retrieveProduct() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		//查询数据库中的所有商品
		String sql = "select * from product";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}
	//点击修改时,在数据库中找到该商品并保存到product对象中,然后返回
	public Product findOneProduct(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pid = ?";
		Object[] params = {pid};
		Product product = qr.query(sql, new BeanHandler<Product>(Product.class), params);
		return product;
	}
	//点击updateProduct.jsp中的修改按钮时,修改数据库中的数据
	public void update(Product product) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update product set pname = ?,"
				+ "market_price = ? , shop_price = ? ,"
				+ "pimage = ? ,pdate = ? ,is_hot = ? ,pdesc = ? ,pflag = ? ,cid = ? where pid = ?";
		Date date = new Date();
		Object[] params = {product.getPname(),product.getMarket_price(),product.getShop_price(),
				product.getPimage(),date,product.getIs_hot(),product.getPdesc(),
				product.getPflag(),product.getCid(),product.getPid()};
		qr.update(sql, params);
	}
	public void delete(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from product where pid = ?";
		qr.update(sql, pid);
	}
	//点击productList.jsp页面中的删除按钮时,删除勾选中的复选框对应数据库中的数据
	public void delSomeRecordByPid(String[] pids) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from product where 1=1";
		//拼接sql语句,delete from product where 1=1 and pid=1 or pid=2 or pid=3...
		if(pids.length >= 1){
			sql = sql + " and pid=" + pids[0] + " ";
			for(int i = 1; i < pids.length; i++){
				sql = sql + " or pid=" + pids[i] + " ";
			}
			qr.update(sql);
		}
	}
	//在productList2.jsp中分页展示给用户商品,currentPage:用户点击的链接页,pageSize:每页展示的商品条数
	public List<Product> showPage(PageModel pageModel) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product limit ?,?";
		Object[] params = {pageModel.getStartIndex(),pageModel.getPageSize()};
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class), params);
		return list;
	}
	public int getTotalRecords() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		//
		String sql = "select count(*) from product";
		Long totalRecords = (Long)qr.query(sql, new ScalarHandler());
		return totalRecords.intValue();
	}

}

//Category.java
package com.itheima.domain;
/*
 * CREATE TABLE `category` (
  `cid` VARCHAR(32) NOT NULL,
  `cname` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

 */
public class Category {
	private String cid;
	private String cname;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + "]";
	}
	
}

//Product.java
package com.itheima.domain;

import java.util.Date;

/*
CREATE TABLE `product` (
		  `pid` VARCHAR(32) NOT NULL,
		  `pname` VARCHAR(50) DEFAULT NULL,
		  `market_price` DOUBLE DEFAULT NULL,
		  `shop_price` DOUBLE DEFAULT NULL,
		  `pimage` VARCHAR(200) DEFAULT NULL,
		  `pdate` DATE DEFAULT NULL,
		  `is_hot` INT(11) DEFAULT NULL,
		  `pdesc` VARCHAR(255) DEFAULT NULL,
		  `pflag` INT(11) DEFAULT NULL,
		  `cid` VARCHAR(32) DEFAULT NULL,
		  PRIMARY KEY (`pid`),
		  KEY `sfk_0001` (`cid`),
		  CONSTRAINT `sfk_0001` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
		) ENGINE=INNODB DEFAULT CHARSET=utf8;
*/
public class Product {
	private String pid;
	private String pname;
	private double market_price;
	private double shop_price;
	private String pimage;
	private Date pdate;
	private int is_hot;//是否热门,1代表热门,0代表不热门
	private String pdesc;
	private int pflag;//是否上架,1代表下架,0代表未下架
	private String cid;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getMarket_price() {
		return market_price;
	}
	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}
	public double getShop_price() {
		return shop_price;
	}
	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public int getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public int getPflag() {
		return pflag;
	}
	public void setPflag(int pflag) {
		this.pflag = pflag;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", market_price=" + market_price + ", shop_price="
				+ shop_price + ", pimage=" + pimage + ", pdate=" + pdate + ", is_hot=" + is_hot + ", pdesc=" + pdesc
				+ ", pflag=" + pflag + ", cid=" + cid + "]";
	}
	
}

/*
 *
 *
 * 分页查询商品
 *
 *
 */

//PageModel.java
package com.itheima.domain;

import java.util.ArrayList;
import java.util.List;

/*

  	分页
		用户点击的页为currentPage
		每页显示4条数据,pageSize=4
		显示9个页面链接
		1	 	,2,3,4,		5		,6,7,8,		9
	startPage          currentPage 	 		 endPage
		上一页,previousPage
		下一页,nextPage
		总页数,totalPage
		总记录商品条数,totalRecords
		用户选中的链接页的第一条数据的起始索引值,startIndex
 */
public class PageModel {
	private List<Product> list;
	
	private int currentPage;//当前页,由用户点击确定-----------------------------------参数1
	private int pageSize;//每页条数,暂定为4-----------------------------------------参数2
	private int startPage;//开始页, currentPage-4
	private int endPage;//结束页,currentPage+4
	private int previousPage;//前一页,currentPage-1
	private int nextPage;//下一页,currentPage+1
	private int totalPage;//总页数,(总条数+3)/4
	private int totalRecords;//总条数,select count(*) from product----------------参数3
	private int startIndex;//开始索引,(currentPage-1)*pageSize
	
	public PageModel(int currentPage,int pageSize,int totalRecords){
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalRecords = totalRecords;
		
		totalPage = (totalRecords + 3)/pageSize;
		if(currentPage > 4){
			this.startPage = currentPage - 4;
		}else{
			this.startPage = 1;
		}
		//三种情况:currentPage>总页数-4,currentPage<4,其他情况
		if(currentPage > totalPage - 4){
			this.endPage = totalPage;
		}else if(currentPage < 4){
			this.endPage = 9;
		}else{
			this.endPage = currentPage + 4;
		}
		if(currentPage > 1){
			previousPage = currentPage - 1;
		}else{
			previousPage = currentPage;
		}
		if(currentPage == totalPage){
			nextPage = totalPage;
		}else{
			nextPage = currentPage + 1;
		}
		startIndex = (currentPage - 1)*pageSize;
	}
	
	
	
	
	
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getPreviousPage() {
		return previousPage;
	}
	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
}

//index.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<jsp:forward page="/PageServlet"></jsp:forward>
	<%
		//删除商品时的操作
		/*<jsp:forward page="/RetrieveServlet"></jsp:forward>*/
	%>
</body>
</html>

//productList2.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示全部商品信息</title>
</head>
<body>
<table border="1" width="80%" align="center"> 
	<tr>
		<th>序号</th>
		<th>商品名称</th>
		<th>市场价格</th>
		<th>商场价格</th>
		<th>图片</th>
		<th>上架时间</th>
		<th>是否热门</th>
		<th>商品描述</th>
		<th>是否上架</th>
		<th>修改</th>
		<th>删除</th>
	</tr>
	<!-- showProducts:pageModel对象 -->
	<c:forEach varStatus="status" items="${requestScope.showProducts.list}" var="item">
	<tr>
		<td>${status.count}</td>
		<td>${item.pname}</td>
		<td>${item.market_price}</td>
		<td>${item.shop_price}</td>
		<td><img src="${item.pimage}" width="50px"/></td>
		<td>${item.pdate}</td>
		<!-- 是否热门,1代表热门,0代表不热门 -->
		<td>
			<c:if test="${item.is_hot == 1}">
				是
			</c:if>
			<c:if test="${item.is_hot == 0}">
				否
			</c:if>
		</td>
		<td>${item.pdesc}</td>
		<!-- 是否下架,1代表下架,0代表未下架 -->
		<td>
			<c:if test="${item.pflag==1}">
				下架
			</c:if>
			<c:if test="${item.pflag==0}">
				未下架
			</c:if>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/update?pid=${item.pid}">修改</a>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/delete?pid=${item.pid}">删除</a>
		</td>
	</tr>
	</c:forEach>
</table>
<!-- 
	分页
		用户点击的页为currentPage
		每页显示4条数据,pageSize=4
		显示9个页面链接
		1	 	,2,3,4,		5		,6,7,8,		9
	startPage          currentPage 	 		 endPage
		上一页,previousPage
		下一页,nextPage
		总页数,totalPage
		总记录商品条数,totalRecords
		用户选中的链接页的第一条数据的起始索引值,startIndex
 -->
 
 	<center>
 		共${requestScope.showProducts.totalPage}页/第${requestScope.showProducts.currentPage}页
 		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=1">首页</a>
 		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=${requestScope.showProducts.previousPage}">上一页</a>
 		<c:forEach begin="${requestScope.showProducts.startPage}" end="${requestScope.showProducts.endPage}" var="i">
 			<a href="${pageContext.request.contextPath}/PageServlet?currentPage=${i}">${i}</a>
 		</c:forEach>
 		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=${requestScope.showProducts.nextPage}">下一页</a>
 		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=${requestScope.showProducts.totalPage}">末页</a>
 	</center>
 	
<!-- 
	<center>
		共10页/第1页
		<a href="#">首页</a>
		<a href="#">上一页</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=1">1</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=2">2</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=3">3</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=4">4</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=5">5</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=6">6</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=7">7</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=8">8</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=9">9</a>
		<a href="#">下一页</a>
		<a href="#">末页</a>
		<input type="text" />
		<input type="button" value="前往"/>
	</center>
-->
<center>
	<a href="${pageContext.request.contextPath}/categoryServlet">添加商品</a>
</center>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script>
$(function(){
	
	/*
	 * 页面加载完毕,为删除链接绑定点击事件功能
	 */
	//$("#myId").click(function(){alert("TTTTT");});
	//$(".myClass").click(function(){alert("TTTTT");});
	$("a:contains('删除')").click(function(){
		//alert("QQQQQ");
		
		/*
		if(confirm("忍心删除?")){
			return true;
		}
		
		//阻止链接跳转
		return false;
		*/
		
		return confirm("忍心删除?");
	});
	//加载事件完成
});
</script>
</body>
</html>

  /*
   *
   * 对商品处理进一步优化,把分页的信息提取出来单独处理,并对PageModel.java进一步优化
   *
   *
   */

  //pageFile.jsp
    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%--分页显示的开始 --%>
    	<div style="text-align:center">
    		共${page.totalPageNum}页/第${page.currentPageNum}页
    		<a href="${pageContext.request.contextPath}/${page.url}?num=1">首页</a>
    		<a href="${pageContext.request.contextPath}/${page.url}?num=${page.prePageNum}">上一页</a>
    		<%--显示的页码，使用forEach遍历显示的页面 --%>
    		<c:forEach begin="${page.startPage}" end="${page.endPage}" var="pagenum">
    			<a href="${pageContext.request.contextPath}/${page.url}?num=${pagenum}">${pagenum}</a>
    		</c:forEach>
    		
    		<a href="${pageContext.request.contextPath}/${page.url}?num=${page.nextPageNum}">下一页</a>
    		<a href="${pageContext.request.contextPath}/${page.url}?num=${page.totalPageNum}">末页</a>
    		<input type="text" id="pagenum" name="pagenum" size="1"/><input type="button" value="前往" onclick="jump()" />
    		<script type="text/javascript">
    			function jump(){
    				var totalpage = ${page.totalPageNum};
    				var pagenum = document.getElementById("pagenum").value;
    				//判断输入的是一个数字
    				var reg =/^[1-9][0-9]{0,1}$/;
    				if(!reg.test(pagenum)){
    					//不是一个有效数字
    					alert("请输入符合规定的数字");
    					return ;
    				}
    				//判断输入的数字不能大于总页数
    				if(parseInt(pagenum)>parseInt(totalpage)){
    					//超过了总页数
    					alert("不能大于总页数");
    					return;
    				}
    				//转向分页显示的Servlet
    				window.location.href="${pageContext.request.contextPath}/${page.url}?num="+pagenum;
    			}
    		</script>
    	</div>
    	<%--分页显示的结束--%>

//PageModel.java
package com.itheima.domain;

import java.util.List;

/**
 * 存放分页相关的数据
 *
 */
public class PageModel {
	
	//1_集合
	private List list;//已经分好页的结果集,该list中只有10条记录
		
		
	//2_分页参数信息基本属性
	private int currentPageNum;//当前页数，由用户指定				*
	private int pageSize ;//每页显示的条数，固定的				*
	private int totalRecords;//总记录条数，数据库查出来的			    *
	
	
	private int startIndex;//每页开始记录的索引，计算出来的			    *
	private int totalPageNum;//总页数，计算出来的					*
	
	private int prePageNum;//上一页							    *
	private int nextPageNum;//下一页							    *
	
	//扩展属性
	//一共每页显示9个页码按钮
	private int startPage;//开始页码
	private int endPage;//结束页码
	
	//完善属性
	private String url;
	
	


	//要想使用我的分页，必须给我两个参数。一个是要看哪一页，另一个是总记录条数
	public PageModel(int currentPageNum,int totalRecords,int pageSize){
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;
		this.pageSize=pageSize;
		
		//计算查询记录的开始索引
		startIndex = (currentPageNum-1)*pageSize;
		//计算总页数
		totalPageNum = totalRecords%pageSize==0?(totalRecords/pageSize):(totalRecords/pageSize+1);
		
		prePageNum = currentPageNum-1;
		if(prePageNum<1){
			prePageNum = 1;
		}
		
		nextPageNum = currentPageNum+1;
		if(nextPageNum>totalPageNum){
			nextPageNum = totalPageNum;
		}
		
		
		startPage = currentPageNum - 4;
		endPage = currentPageNum + 4;
		//看看总页数够不够9页
		if(totalPageNum>9){
			//超过了9页
			if(startPage < 1){
				startPage = 1;
				endPage = startPage+8;
			}
			if(endPage>totalPageNum){
				endPage = totalPageNum;
				startPage = endPage-8;
			}
		}else{
			//不够9页
			startPage = 1;
			endPage = totalPageNum;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getPrePageNum() {
		return prePageNum;
	}

	public int getNextPageNum() {
		return nextPageNum;
	}

	
	
	
	public int getCurrentPageNum() {
		return currentPageNum;
	}


	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalRecords() {
		return totalRecords;
	}


	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}


	public int getTotalPageNum() {
		return totalPageNum;
	}


	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}


	public int getStartIndex() {
		return startIndex;
	}


	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}


	


	public void setPrePageNum(int prePageNum) {
		this.prePageNum = prePageNum;
	}


	

	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}

/*
 *
 *
 * 条件查询商品
 * 只需对上面的代码修改一下,请求属性中添加要回显的数据
 * 展示商品的页面相应的判断一下是否存在回显的数据
 *
 */

//productList3.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示全部商品信息</title>
</head>
<body>
<center>
<form action="${pageContext.request.contextPath}/ContionSubmitSerlvet" method="post">
	选择分类:
		<select name="productCid">
			<option value="">--选择分类--</option>
			<c:forEach items="${categoryList}" var="category">
				<c:if test="${productCid==category.cid }">
				<option value="${category.cid}" selected="selected">${category.cname}</option>
				</c:if>
				<c:if test="${productCid != category.cid }">
				<option value="${category.cid}">${category.cname}</option>
				</c:if>
			</c:forEach>
		</select>
	商品名称:
		<c:if test="${productName==''}">
			<input type="text" name="productName" />
		</c:if>
		<c:if test="${productName!=''}">
			<input type="text" name="productName" value="${productName}"/>
		</c:if>
	<input type="submit" value="查询"/>
</form>
</center>
<table border="1" width="80%" align="center"> 
	<tr>
		<th>序号</th>
		<th>商品名称</th>
		<th>市场价格</th>
		<th>商场价格</th>
		<th>图片</th>
		<th>上架时间</th>
		<th>是否热门</th>
		<th>商品描述</th>
		<th>是否上架</th>
		<th>修改</th>
		<th>删除</th>
	</tr>
	<!-- showProducts:pageModel对象 -->
	<c:forEach varStatus="status" items="${requestScope.contionResultList}" var="item">
	<tr>
		<td>${status.count}</td>
		<td>${item.pname}</td>
		<td>${item.market_price}</td>
		<td>${item.shop_price}</td>
		<td><img src="${item.pimage}" width="50px"/></td>
		<td>${item.pdate}</td>
		<!-- 是否热门,1代表热门,0代表不热门 -->
		<td>
			<c:if test="${item.is_hot == 1}">
				是
			</c:if>
			<c:if test="${item.is_hot == 0}">
				否
			</c:if>
		</td>
		<td>${item.pdesc}</td>
		<!-- 是否下架,1代表下架,0代表未下架 -->
		<td>
			<c:if test="${item.pflag==1}">
				下架
			</c:if>
			<c:if test="${item.pflag==0}">
				未下架
			</c:if>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/update?pid=${item.pid}">修改</a>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/delete?pid=${item.pid}">删除</a>
		</td>
	</tr>
	</c:forEach>
</table>

<center>
	<a href="${pageContext.request.contextPath}/categoryServlet">添加商品</a>
</center>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script>
$(function(){
	
	/*
	 * 页面加载完毕,为删除链接绑定点击事件功能
	 */
	//$("#myId").click(function(){alert("TTTTT");});
	//$(".myClass").click(function(){alert("TTTTT");});
	$("a:contains('删除')").click(function(){
		//alert("QQQQQ");
		
		/*
		if(confirm("忍心删除?")){
			return true;
		}
		
		//阻止链接跳转
		return false;
		*/
		
		return confirm("忍心删除?");
	});
	//加载事件完成
});
</script>
</body>
</html>

//ContainSubmitServlet.java
package com.itheima.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.domain.Category;
import com.itheima.domain.PageModel;
import com.itheima.domain.Product;
import com.itheima.service.CategoryService;
import com.itheima.service.ProductService;

public class ContionSubmitSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求响应的编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取用户输入的数据,如果用户没有输入任何数据,则为空字符串
		String productCid = request.getParameter("productCid");
		String productName = request.getParameter("productName");
		
		//封装数据(无)
		//service层处理,通过用户数据进行查询,返回product对象的list集合
		ProductService service = new ProductService();
		List<Product> list = service.getProductListByContion(productCid,productName);
		
		CategoryService service1 = new CategoryService();
		List<Category> list1 = service1.getAllCategory();
		//增加请求属性,查询到所有的分类对象集合
		request.setAttribute("categoryList", list1);
		
		//回显用户输入的数据
		request.setAttribute("productCid", productCid);
		request.setAttribute("productName", productName);
		request.setAttribute("contionResultList", list);
		//请求转发回productList3.jsp页面
		request.getRequestDispatcher("/productList3.jsp").forward(request, response);
	}
}
