/*
 *
 *
 *ɾ��ѡ����Ʒ
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
<title>��ʾȫ����Ʒ��Ϣ</title>
</head>
<body>
<table border="1" width="80%" align="center"> 
	<tr>
		<th>
			<input type="button" name="delBtn" id="delBtn" value="ɾ��"/>
			<input type="checkbox" name="delAllCheckbox" id="delAllCheckbox"/>
		</th>
		<th>���</th>
		<th>��Ʒ����</th>
		<th>�г��۸�</th>
		<th>�̳��۸�</th>
		<th>ͼƬ</th>
		<th>�ϼ�ʱ��</th>
		<th>�Ƿ�����</th>
		<th>��Ʒ����</th>
		<th>�Ƿ��ϼ�</th>
		<th>�޸�</th>
		<th>ɾ��</th>
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
		<!-- �Ƿ�����,1��������,0�������� -->
		<td>
			<c:if test="${item.is_hot == 1}">
				��
			</c:if>
			<c:if test="${item.is_hot == 0}">
				��
			</c:if>
		</td>
		<td>${item.pdesc}</td>
		<!-- �Ƿ��¼�,1�����¼�,0����δ�¼� -->
		<td>
			<c:if test="${item.pflag==1}">
				�¼�
			</c:if>
			<c:if test="${item.pflag==0}">
				δ�¼�
			</c:if>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/update?pid=${item.pid}">�޸�</a>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/delete?pid=${item.pid}">ɾ��</a>
		</td>
	</tr>
	</c:forEach>
</table>
	<center>
		��10ҳ/��1ҳ
		<a href="#">��ҳ</a>
		<a href="#">��һҳ</a>
		<a href="#">1</a>
		<a href="#">2</a>
		<a href="#">3</a>
		<a href="#">4</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=5">5</a>
		<a href="#">6</a>
		<a href="#">7</a>
		<a href="#">8</a>
		<a href="#">9</a>
		<a href="#">��һҳ</a>
		<a href="#">ĩҳ</a>
		<input type="text" />
		<input type="button" value="ǰ��"/>
	</center>
<center>
	<a href="${pageContext.request.contextPath}/categoryServlet">�����Ʒ</a>
</center>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script>
$(function(){
	
	/*
	 * ҳ��������,Ϊɾ�����Ӱ󶨵���¼�����
	 */
	//$("#myId").click(function(){alert("TTTTT");});
	//$(".myClass").click(function(){alert("TTTTT");});
	$("a:contains('ɾ��')").click(function(){
		//alert("QQQQQ");
		
		/*
		if(confirm("����ɾ��?")){
			return true;
		}
		
		//��ֹ������ת
		return false;
		*/
		
		return confirm("����ɾ��?");
	});
	
	/*
	 * ȫѡȫ������
	 */
	//�����ȫѡ��ѡ��ʱ,������ѡ��ȫѡ
	$("#delAllCheckbox").click(function(){
		$("input[type='checkbox']").prop("checked",this.checked);
	});
	$("input[name='delCheckbox']").click(function(){
		//�������ȫѡ��ѡ��ʱ,���ȫ��ѡ��,���Զ���ѡȫѡ��ѡ��
		if($("input[name='delCheckbox']:checked").length==$("input[name='delCheckbox']").length){
			$("#delAllCheckbox").prop("checked",true);
		}
		//��ȫѡʱ,�����������һ����ѡ��ʱ,ȫѡ��ѡ��Ҳ�Զ�����
		if($("input[name='delCheckbox']:checked").length != $("input[name='delCheckbox']").length){
			$("#delAllCheckbox").prop("checked",false);
		}
	});
	
	/*
	 * ���ɾ����ť����"����ɾ����?"
	 * ����һ��һ��ɾ��,ʹ������,����ʹ��sql���ƴ��
	 */
	 $("#delBtn").click(function(){
		 if(confirm("������ɾ����?")){
			 //ƴ�Ӳ���,?pid=1&pid=2...
			 var params = $("input[name='delCheckbox']:checked").serialize();
			 location.href = "${pageContext.request.contextPath}/DelSomeProductServlet?" + params;
		 }
	 });
	//�����¼����
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
		// ������������ı����ʽ
		request.setCharacterEncoding("utf-8");
		// ������Ӧ�ı����ʽ
		response.setContentType("text/html;charset=utf-8");
		// ����service�����ҵ���ж�,���ز�Ʒ���󼯺�
		ProductService service = new ProductService();
		List<Product> list = service.retrieveProduct();
		// �����ݿ�鵽�Ĳ�Ʒ���Ϸŵ�request�����������
		request.setAttribute("productList", list);
		// ����ת����productList.jspҳ��
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
		//����ͷ�ı����ʽû��������new String("".getBytes("iso-8859-1"),"utf-8")
		//����������ı����ʽ
		request.setCharacterEncoding("utf-8");
		//������Ӧ�ı����ʽ
		response.setContentType("text/html;charset=utf-8");
		//����sevice��,��ȡ���еķ���
		CategoryService service = new CategoryService();
		List<Category> list = service.getAllCategory();
		//����request����,�Ѹ����Դ��ݵ�addProduct.jsp��,����ͨ��jspҳ���request���ö�����ʵ�
		request.setAttribute("categoryList", list);
		//����ת����addProduct.jsp
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
<title>������Ʒ</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/addProduct">
<table border="1" width="50%" align="center">

<tr><td>��Ʒ����:<input type="text" name="pname" value="${requestScope.updatingProduct.pname}"/></td></tr>
<tr><td>�г��۸�:<input type="text" name="market_price" value="${requestScope.updatingProduct.market_price}"/></td></tr>
<tr><td>�̳Ǽ۸�:<input type="text" name="shop_price" value="${requestScope.updatingProduct.shop_price}"/></td></tr>
<tr><td>��ƷͼƬ:<input type="text" name="pimage" value="${requestScope.updatingProduct.pimage}"/></td></tr>
<!-- ������1,��������0 -->
<tr><td>�Ƿ�����:
	<!-- 1�������� -->
	<c:if test="${requestScope.updatingProduct.is_hot==1}">
      <input type="radio" name="is_hot" value="1" checked="checked"/>��
      <input type="radio" name="is_hot" value="0"/>��
	</c:if>
	<!-- 0�������� ,���ж�����ǳ�������֮����������п��ܶ�ִ���������-->
	<c:if test="${requestScope.updatingProduct.is_hot != 1}">
		<input type="radio" name="is_hot" value="1" />��
		<input type="radio" name="is_hot" value="0" checked="checked"/>��
	</c:if>
</td></tr>
<tr><td>��Ʒ����:<textarea cols="20" rows="10" name="pdesc">${requestScope.updatingProduct.pdesc}</textarea></td></tr>
<!-- �Ƿ��ϼ�,δ�¼�:0,�¼�:1 -->
<tr><td>�Ƿ��ϼ�:
	<!-- 1�����¼�,����Ǽ������δ�¼�֮������п��� -->
	<c:if test="${requestScope.updatingProduct.pflag != 0}">
		<input type="radio" name="pflag" value="0" />��
		<input type="radio" name="pflag" value="1" checked="checked"/>��
	</c:if>
	<!-- 0����δ�¼� -->
	<c:if test="${requestScope.updatingProduct.pflag == 0}">
		<input type="radio" name="pflag" value="0" checked="checked"/>��
		<input type="radio" name="pflag" value="1"/>��
	</c:if>
</td></tr>
<tr>
	<td>��������
	   <select name="cid">
	   		<c:forEach items="${categoryList}" var="category" varStatus="status">
	   			<!-- ͨ��UpdateServlet��ȡ�� -->
	   			<!-- ���UpdateServlet�е�updatingProduct���Ե������з����е�ĳһ��,�����ø÷����selected����Ϊselected -->
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
<tr><td><button>���</button></td></tr>
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
		//������������ʽ
		request.setCharacterEncoding("utf-8");
		//������Ӧ�����ʽ
		response.setContentType("text/html;charset=utf-8");
		//��ȡ��������
		Map<String, String[]> parameterMap = request.getParameterMap();
		//��װ����
		Product product = new Product();
		try {
			BeanUtils.populate(product, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//������ݵ����ݿ�,��ת��service�����߼��ж�
		AddProductService service = new AddProductService();
		service.addProduct(product);
		//������������(��)
		//�ض���
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
	//��ѯ���еĿ�����Ʒ
	public List<Product> retrieveProduct() {
		//ת��dao��������ݿ��ѯ
		ProductDao dao = new ProductDao();
		List<Product> list = null;
		try {
			list = dao.retrieveProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//����޸�ʱ,�ҵ����ж�Ӧ���ݿ��е�����,�����浽product������,������
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
	//���updateProduct.jsp�е��޸İ�ťʱ,�޸����ݿ��е�����
	public void update(Product product) {
		ProductDao dao = new ProductDao();
		try {
			dao.update(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//productList.jspҳ���е�ɾ��,ɾ�����ݿ��еĸ�����¼
	public void delete(String pid) {
		ProductDao dao = new ProductDao();
		try {
			dao.delete(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//���productList.jspҳ���е�ɾ����ťʱ,ɾ����ѡ�еĸ�ѡ���Ӧ���ݿ��е�����,Ҳ����ʹ������
	public void delSomeRecordByPid(String[] pids) {
		ProductDao dao = new ProductDao();
		try {
			dao.delSomeRecordByPid(pids);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//��productList2.jsp�з�ҳչʾ���û���Ʒ,currentPage:�û����������ҳ,pageSize:ÿҳչʾ����Ʒ����
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
	//��ѯcategory���е����з���,��BeanListHandler<Category>(Category),����Category�����list����
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
	//��ѯ���Կ�����Ʒ
	public List<Product> retrieveProduct() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		//��ѯ���ݿ��е�������Ʒ
		String sql = "select * from product";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}
	//����޸�ʱ,�����ݿ����ҵ�����Ʒ�����浽product������,Ȼ�󷵻�
	public Product findOneProduct(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from product where pid = ?";
		Object[] params = {pid};
		Product product = qr.query(sql, new BeanHandler<Product>(Product.class), params);
		return product;
	}
	//���updateProduct.jsp�е��޸İ�ťʱ,�޸����ݿ��е�����
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
	//���productList.jspҳ���е�ɾ����ťʱ,ɾ����ѡ�еĸ�ѡ���Ӧ���ݿ��е�����
	public void delSomeRecordByPid(String[] pids) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "delete from product where 1=1";
		//ƴ��sql���,delete from product where 1=1 and pid=1 or pid=2 or pid=3...
		if(pids.length >= 1){
			sql = sql + " and pid=" + pids[0] + " ";
			for(int i = 1; i < pids.length; i++){
				sql = sql + " or pid=" + pids[i] + " ";
			}
			qr.update(sql);
		}
	}
	//��productList2.jsp�з�ҳչʾ���û���Ʒ,currentPage:�û����������ҳ,pageSize:ÿҳչʾ����Ʒ����
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
	private int is_hot;//�Ƿ�����,1��������,0��������
	private String pdesc;
	private int pflag;//�Ƿ��ϼ�,1�����¼�,0����δ�¼�
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
 * ��ҳ��ѯ��Ʒ
 *
 *
 */

//PageModel.java
package com.itheima.domain;

import java.util.ArrayList;
import java.util.List;

/*

  	��ҳ
		�û������ҳΪcurrentPage
		ÿҳ��ʾ4������,pageSize=4
		��ʾ9��ҳ������
		1	 	,2,3,4,		5		,6,7,8,		9
	startPage          currentPage 	 		 endPage
		��һҳ,previousPage
		��һҳ,nextPage
		��ҳ��,totalPage
		�ܼ�¼��Ʒ����,totalRecords
		�û�ѡ�е�����ҳ�ĵ�һ�����ݵ���ʼ����ֵ,startIndex
 */
public class PageModel {
	private List<Product> list;
	
	private int currentPage;//��ǰҳ,���û����ȷ��-----------------------------------����1
	private int pageSize;//ÿҳ����,�ݶ�Ϊ4-----------------------------------------����2
	private int startPage;//��ʼҳ, currentPage-4
	private int endPage;//����ҳ,currentPage+4
	private int previousPage;//ǰһҳ,currentPage-1
	private int nextPage;//��һҳ,currentPage+1
	private int totalPage;//��ҳ��,(������+3)/4
	private int totalRecords;//������,select count(*) from product----------------����3
	private int startIndex;//��ʼ����,(currentPage-1)*pageSize
	
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
		//�������:currentPage>��ҳ��-4,currentPage<4,�������
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
		//ɾ����Ʒʱ�Ĳ���
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
<title>��ʾȫ����Ʒ��Ϣ</title>
</head>
<body>
<table border="1" width="80%" align="center"> 
	<tr>
		<th>���</th>
		<th>��Ʒ����</th>
		<th>�г��۸�</th>
		<th>�̳��۸�</th>
		<th>ͼƬ</th>
		<th>�ϼ�ʱ��</th>
		<th>�Ƿ�����</th>
		<th>��Ʒ����</th>
		<th>�Ƿ��ϼ�</th>
		<th>�޸�</th>
		<th>ɾ��</th>
	</tr>
	<!-- showProducts:pageModel���� -->
	<c:forEach varStatus="status" items="${requestScope.showProducts.list}" var="item">
	<tr>
		<td>${status.count}</td>
		<td>${item.pname}</td>
		<td>${item.market_price}</td>
		<td>${item.shop_price}</td>
		<td><img src="${item.pimage}" width="50px"/></td>
		<td>${item.pdate}</td>
		<!-- �Ƿ�����,1��������,0�������� -->
		<td>
			<c:if test="${item.is_hot == 1}">
				��
			</c:if>
			<c:if test="${item.is_hot == 0}">
				��
			</c:if>
		</td>
		<td>${item.pdesc}</td>
		<!-- �Ƿ��¼�,1�����¼�,0����δ�¼� -->
		<td>
			<c:if test="${item.pflag==1}">
				�¼�
			</c:if>
			<c:if test="${item.pflag==0}">
				δ�¼�
			</c:if>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/update?pid=${item.pid}">�޸�</a>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/delete?pid=${item.pid}">ɾ��</a>
		</td>
	</tr>
	</c:forEach>
</table>
<!-- 
	��ҳ
		�û������ҳΪcurrentPage
		ÿҳ��ʾ4������,pageSize=4
		��ʾ9��ҳ������
		1	 	,2,3,4,		5		,6,7,8,		9
	startPage          currentPage 	 		 endPage
		��һҳ,previousPage
		��һҳ,nextPage
		��ҳ��,totalPage
		�ܼ�¼��Ʒ����,totalRecords
		�û�ѡ�е�����ҳ�ĵ�һ�����ݵ���ʼ����ֵ,startIndex
 -->
 
 	<center>
 		��${requestScope.showProducts.totalPage}ҳ/��${requestScope.showProducts.currentPage}ҳ
 		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=1">��ҳ</a>
 		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=${requestScope.showProducts.previousPage}">��һҳ</a>
 		<c:forEach begin="${requestScope.showProducts.startPage}" end="${requestScope.showProducts.endPage}" var="i">
 			<a href="${pageContext.request.contextPath}/PageServlet?currentPage=${i}">${i}</a>
 		</c:forEach>
 		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=${requestScope.showProducts.nextPage}">��һҳ</a>
 		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=${requestScope.showProducts.totalPage}">ĩҳ</a>
 	</center>
 	
<!-- 
	<center>
		��10ҳ/��1ҳ
		<a href="#">��ҳ</a>
		<a href="#">��һҳ</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=1">1</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=2">2</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=3">3</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=4">4</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=5">5</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=6">6</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=7">7</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=8">8</a>
		<a href="${pageContext.request.contextPath}/PageServlet?currentPage=9">9</a>
		<a href="#">��һҳ</a>
		<a href="#">ĩҳ</a>
		<input type="text" />
		<input type="button" value="ǰ��"/>
	</center>
-->
<center>
	<a href="${pageContext.request.contextPath}/categoryServlet">�����Ʒ</a>
</center>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script>
$(function(){
	
	/*
	 * ҳ��������,Ϊɾ�����Ӱ󶨵���¼�����
	 */
	//$("#myId").click(function(){alert("TTTTT");});
	//$(".myClass").click(function(){alert("TTTTT");});
	$("a:contains('ɾ��')").click(function(){
		//alert("QQQQQ");
		
		/*
		if(confirm("����ɾ��?")){
			return true;
		}
		
		//��ֹ������ת
		return false;
		*/
		
		return confirm("����ɾ��?");
	});
	//�����¼����
});
</script>
</body>
</html>

  /*
   *
   * ����Ʒ�����һ���Ż�,�ѷ�ҳ����Ϣ��ȡ������������,����PageModel.java��һ���Ż�
   *
   *
   */

  //pageFile.jsp
    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%--��ҳ��ʾ�Ŀ�ʼ --%>
    	<div style="text-align:center">
    		��${page.totalPageNum}ҳ/��${page.currentPageNum}ҳ
    		<a href="${pageContext.request.contextPath}/${page.url}?num=1">��ҳ</a>
    		<a href="${pageContext.request.contextPath}/${page.url}?num=${page.prePageNum}">��һҳ</a>
    		<%--��ʾ��ҳ�룬ʹ��forEach������ʾ��ҳ�� --%>
    		<c:forEach begin="${page.startPage}" end="${page.endPage}" var="pagenum">
    			<a href="${pageContext.request.contextPath}/${page.url}?num=${pagenum}">${pagenum}</a>
    		</c:forEach>
    		
    		<a href="${pageContext.request.contextPath}/${page.url}?num=${page.nextPageNum}">��һҳ</a>
    		<a href="${pageContext.request.contextPath}/${page.url}?num=${page.totalPageNum}">ĩҳ</a>
    		<input type="text" id="pagenum" name="pagenum" size="1"/><input type="button" value="ǰ��" onclick="jump()" />
    		<script type="text/javascript">
    			function jump(){
    				var totalpage = ${page.totalPageNum};
    				var pagenum = document.getElementById("pagenum").value;
    				//�ж��������һ������
    				var reg =/^[1-9][0-9]{0,1}$/;
    				if(!reg.test(pagenum)){
    					//����һ����Ч����
    					alert("��������Ϲ涨������");
    					return ;
    				}
    				//�ж���������ֲ��ܴ�����ҳ��
    				if(parseInt(pagenum)>parseInt(totalpage)){
    					//��������ҳ��
    					alert("���ܴ�����ҳ��");
    					return;
    				}
    				//ת���ҳ��ʾ��Servlet
    				window.location.href="${pageContext.request.contextPath}/${page.url}?num="+pagenum;
    			}
    		</script>
    	</div>
    	<%--��ҳ��ʾ�Ľ���--%>

//PageModel.java
package com.itheima.domain;

import java.util.List;

/**
 * ��ŷ�ҳ��ص�����
 *
 */
public class PageModel {
	
	//1_����
	private List list;//�Ѿ��ֺ�ҳ�Ľ����,��list��ֻ��10����¼
		
		
	//2_��ҳ������Ϣ��������
	private int currentPageNum;//��ǰҳ�������û�ָ��				*
	private int pageSize ;//ÿҳ��ʾ���������̶���				*
	private int totalRecords;//�ܼ�¼���������ݿ�������			    *
	
	
	private int startIndex;//ÿҳ��ʼ��¼�����������������			    *
	private int totalPageNum;//��ҳ�������������					*
	
	private int prePageNum;//��һҳ							    *
	private int nextPageNum;//��һҳ							    *
	
	//��չ����
	//һ��ÿҳ��ʾ9��ҳ�밴ť
	private int startPage;//��ʼҳ��
	private int endPage;//����ҳ��
	
	//��������
	private String url;
	
	


	//Ҫ��ʹ���ҵķ�ҳ�������������������һ����Ҫ����һҳ����һ�����ܼ�¼����
	public PageModel(int currentPageNum,int totalRecords,int pageSize){
		this.currentPageNum = currentPageNum;
		this.totalRecords = totalRecords;
		this.pageSize=pageSize;
		
		//�����ѯ��¼�Ŀ�ʼ����
		startIndex = (currentPageNum-1)*pageSize;
		//������ҳ��
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
		//������ҳ��������9ҳ
		if(totalPageNum>9){
			//������9ҳ
			if(startPage < 1){
				startPage = 1;
				endPage = startPage+8;
			}
			if(endPage>totalPageNum){
				endPage = totalPageNum;
				startPage = endPage-8;
			}
		}else{
			//����9ҳ
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
 * ������ѯ��Ʒ
 * ֻ�������Ĵ����޸�һ��,�������������Ҫ���Ե�����
 * չʾ��Ʒ��ҳ����Ӧ���ж�һ���Ƿ���ڻ��Ե�����
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
<title>��ʾȫ����Ʒ��Ϣ</title>
</head>
<body>
<center>
<form action="${pageContext.request.contextPath}/ContionSubmitSerlvet" method="post">
	ѡ�����:
		<select name="productCid">
			<option value="">--ѡ�����--</option>
			<c:forEach items="${categoryList}" var="category">
				<c:if test="${productCid==category.cid }">
				<option value="${category.cid}" selected="selected">${category.cname}</option>
				</c:if>
				<c:if test="${productCid != category.cid }">
				<option value="${category.cid}">${category.cname}</option>
				</c:if>
			</c:forEach>
		</select>
	��Ʒ����:
		<c:if test="${productName==''}">
			<input type="text" name="productName" />
		</c:if>
		<c:if test="${productName!=''}">
			<input type="text" name="productName" value="${productName}"/>
		</c:if>
	<input type="submit" value="��ѯ"/>
</form>
</center>
<table border="1" width="80%" align="center"> 
	<tr>
		<th>���</th>
		<th>��Ʒ����</th>
		<th>�г��۸�</th>
		<th>�̳��۸�</th>
		<th>ͼƬ</th>
		<th>�ϼ�ʱ��</th>
		<th>�Ƿ�����</th>
		<th>��Ʒ����</th>
		<th>�Ƿ��ϼ�</th>
		<th>�޸�</th>
		<th>ɾ��</th>
	</tr>
	<!-- showProducts:pageModel���� -->
	<c:forEach varStatus="status" items="${requestScope.contionResultList}" var="item">
	<tr>
		<td>${status.count}</td>
		<td>${item.pname}</td>
		<td>${item.market_price}</td>
		<td>${item.shop_price}</td>
		<td><img src="${item.pimage}" width="50px"/></td>
		<td>${item.pdate}</td>
		<!-- �Ƿ�����,1��������,0�������� -->
		<td>
			<c:if test="${item.is_hot == 1}">
				��
			</c:if>
			<c:if test="${item.is_hot == 0}">
				��
			</c:if>
		</td>
		<td>${item.pdesc}</td>
		<!-- �Ƿ��¼�,1�����¼�,0����δ�¼� -->
		<td>
			<c:if test="${item.pflag==1}">
				�¼�
			</c:if>
			<c:if test="${item.pflag==0}">
				δ�¼�
			</c:if>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/update?pid=${item.pid}">�޸�</a>
		</td>
		<td>
			<a href="${pageContext.request.contextPath}/delete?pid=${item.pid}">ɾ��</a>
		</td>
	</tr>
	</c:forEach>
</table>

<center>
	<a href="${pageContext.request.contextPath}/categoryServlet">�����Ʒ</a>
</center>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
<script>
$(function(){
	
	/*
	 * ҳ��������,Ϊɾ�����Ӱ󶨵���¼�����
	 */
	//$("#myId").click(function(){alert("TTTTT");});
	//$(".myClass").click(function(){alert("TTTTT");});
	$("a:contains('ɾ��')").click(function(){
		//alert("QQQQQ");
		
		/*
		if(confirm("����ɾ��?")){
			return true;
		}
		
		//��ֹ������ת
		return false;
		*/
		
		return confirm("����ɾ��?");
	});
	//�����¼����
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
		//����������Ӧ�ı����ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�û����������,����û�û�������κ�����,��Ϊ���ַ���
		String productCid = request.getParameter("productCid");
		String productName = request.getParameter("productName");
		
		//��װ����(��)
		//service�㴦��,ͨ���û����ݽ��в�ѯ,����product�����list����
		ProductService service = new ProductService();
		List<Product> list = service.getProductListByContion(productCid,productName);
		
		CategoryService service1 = new CategoryService();
		List<Category> list1 = service1.getAllCategory();
		//������������,��ѯ�����еķ�����󼯺�
		request.setAttribute("categoryList", list1);
		
		//�����û����������
		request.setAttribute("productCid", productCid);
		request.setAttribute("productName", productName);
		request.setAttribute("contionResultList", list);
		//����ת����productList3.jspҳ��
		request.getRequestDispatcher("/productList3.jsp").forward(request, response);
	}
}
