/**
 *
 *
 *
 * 题目:记录用户上次访问的时间
 *
 *
 *
 */ 

/*
 * servlet代码 
 */
package com.review.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewVisitTime extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求参数的编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应的编码格式
		response.setContentType("text/html; charset=utf-8");
		//查询cookie中是否有visitTime的参数
		Cookie[] cookies = request.getCookies();
		//isExist为false表示请求头上cookie中没有visitTime的参数
		boolean isExist = false;
		if(cookies != null){
			for(Cookie cookie:cookies){
				if("visitTime".equals(cookie.getName())){
					isExist = true;
					break;
				}
			}
		}
		//如果没有则进行第一次增加cookie
		if(!isExist){
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dateStr = sdf.format(date);
			Cookie cookie = new Cookie("visitTime",dateStr);
			cookie.setMaxAge(60*60*1);
			cookie.setPath("/Day37");
			response.addCookie(cookie);
			request.setAttribute("visitTime", "你是第一次访问我们的网站,没有时间记录");
		}else{//如果有则重新设置新的访问时间,并把该时间设置为request属性
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dateStr = sdf.format(date);
			Cookie cookie = new Cookie("visitTime",dateStr);
			cookie.setMaxAge(60*60*1);
			cookie.setPath("/Day37");
			response.addCookie(cookie);
			request.setAttribute("visitTime", "你上次访问我们的网站的时间是:"+dateStr);
		}
		//通过请求转发到下一个页面
		request.getRequestDispatcher("/reviewShowVisitTime2.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

/*
 * reviewShowVisitTime.jsp
 */
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="/visitTime"></jsp:forward>
</body>
</html>

/*
 * reviewShowVisitTime2.jsp
 */
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<input type="button" value="点我查看上次的访问时间" onclick="show()"/>
	</div>
	<div id="div1" style="display: none;">
		<% 
				out.print(request.getAttribute("visitTime"));
		%>
	</div>
	<script type="text/javascript">
		function show(){
	        var div1 = document.getElementById("div1");
	        if(div1.style.display == "none"){
	        	div1.style.display = "";
	        }else{
	        	div1.style.display = "none";
	        }
		}
	</script>
</body>
</html>

/*
 * web.xml配置
 */
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">
  <display-name>Day37</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
    <servlet>
    <description></description>
    <display-name>ReviewVisitTime</display-name>
    <servlet-name>ReviewVisitTime</servlet-name>
    <servlet-class>com.review.web.ReviewVisitTime</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>ReviewVisitTime</servlet-name>
    <url-pattern>/visitTime</url-pattern>
  </servlet-mapping>
</web-app>


/**
 *
 * 
 * 题目:验证码校验
 *
 *
 */
 
/*
 * ReviewCheckCodeServlet代码
 */
package com.review.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReviewCheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求参数的编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应参数的编码格式
		response.setContentType("text/html; charset=utf-8");
		//获取用户输入的验证码
		String fromUserCheckCode = request.getParameter("checkCode");
		//获取会话对象
		HttpSession session = request.getSession();
		//获取会话对象中的验证码
		String checkCode = (String)session.getAttribute("checkcode_session");
		//校验用户输入的验证码是否正确
		Boolean isRight = false;
		if(checkCode.equals(fromUserCheckCode)){
			isRight = true;
		}
		if(isRight){//用户输入的验证码正确
			//重定向到首页
			response.sendRedirect(request.getContextPath()+"/index.html");
		}else{//用户输入验证码错误
			//在页面中显示验证码错误提示,设置请求对象的参数,请求转发
			request.setAttribute("checkCodeErrorMsg", "你输入的验证码有误,请重新输入");
			request.getRequestDispatcher("/reviewCheckImg.jsp").forward(request, response);
		}
	}
}

/*
 * reviewCheckImg.jsp 
 */
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/Day37/reviewCheckCode" method="post">
		<div style="color:red;"><%=request.getAttribute("checkCodeErrorMsg")==null?"":request.getAttribute("checkCodeErrorMsg") %></div>
		验证码:<input type="text" name="checkCode" placeholder="请输入验证码"/>
		<img src="/Day37/checkImg" onclick="changeImg(this)"/><br/>
		<input type="submit" value="提交"/>
	</form>
	<script type="text/javascript">
		function changeImg(obj){
			obj.src="/Day37/checkImg?random="+Math.random();
		}
	</script>
</body>
</html>

/*
 * CheckImgServlet
 * 生成验证码图片的功能
 */
package com.review.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码生成程序
 */
public class CheckImgServlet extends HttpServlet {

	// 集合中保存所有成语
	private List<String> words = new ArrayList<String>();

	@Override
	public void init() throws ServletException {
		// 初始化阶段，读取new_words.txt
		// web工程中读取 文件，必须使用绝对磁盘路径
		String path = getServletContext().getRealPath("/WEB-INF/new_words.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line;
			while ((line = reader.readLine()) != null) {
				words.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 禁止缓存
		// response.setHeader("Cache-Control", "no-cache");
		// response.setHeader("Pragma", "no-cache");
		// response.setDateHeader("Expires", -1);
		int width = 120;
		int height = 30;
		// 步骤一 绘制一张内存中图片
		BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		// 步骤二 图片绘制背景颜色 ---通过绘图对象
		Graphics graphics = bufferedImage.getGraphics();// 得到画图对象 --- 画笔
		// 绘制任何图形之前 都必须指定一个颜色
		graphics.setColor(getRandColor(200, 250));
		graphics.fillRect(0, 0, width, height);
		// 步骤三 绘制边框
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width - 1, height - 1);
		// 步骤四 四个随机数字
		Graphics2D graphics2d = (Graphics2D) graphics;
		// 设置输出字体
		graphics2d.setFont(new Font("宋体", Font.BOLD, 18));
		Random random = new Random();// 生成随机数
		int index = random.nextInt(words.size());
		String word = words.get(index);// 获得成语
		// 定义x坐标
		int x = 10;
		for (int i = 0; i < word.length(); i++) {
			// 随机颜色
			graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			// 旋转 -30 --- 30度
			int jiaodu = random.nextInt(60) - 30;
			// 换算弧度
			double theta = jiaodu * Math.PI / 180;

			// 获得字母数字
			char c = word.charAt(i);

			// 将c 输出到图片
			graphics2d.rotate(theta, x, 20);
			graphics2d.drawString(String.valueOf(c), x, 20);
			graphics2d.rotate(-theta, x, 20);
			x += 30;
		}
		// 将验证码内容保存session
		request.getSession().setAttribute("checkcode_session", word);
		// 步骤五 绘制干扰线
		graphics.setColor(getRandColor(160, 200));
		int x1;
		int x2;
		int y1;
		int y2;
		for (int i = 0; i < 30; i++) {
			x1 = random.nextInt(width);
			x2 = random.nextInt(12);
			y1 = random.nextInt(height);
			y2 = random.nextInt(12);
			graphics.drawLine(x1, y1, x1 + x2, x2 + y2);
		}
		// 将上面图片输出到浏览器 ImageIO
		graphics.dispose();// 释放资源
		//将图片写到response.getOutputStream()中
		ImageIO.write(bufferedImage, "jpg", response.getOutputStream());

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	/**
	 * 取其某一范围的color
	 * 
	 * @param fc
	 *            int 范围参数1
	 * @param bc
	 *            int 范围参数2
	 * @return Color
	 */
	private Color getRandColor(int fc, int bc) {
		// 取其随机颜色
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}

/*
 * web.xml配置
 */
  </servlet-mapping>
  <servlet>
    <description></description>
    <display-name>CheckCodeImg</display-name>
    <servlet-name>CheckCodeImg</servlet-name>
    <servlet-class>com.review.web.CheckImgServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>CheckCodeImg</servlet-name>
    <url-pattern>/checkImg</url-pattern>
  </servlet-mapping>
  <servlet>
    <description></description>
    <display-name>ReviewCheckCodeServlet</display-name>
    <servlet-name>ReviewCheckCodeServlet</servlet-name>
    <servlet-class>com.review.web.ReviewCheckCodeServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>ReviewCheckCodeServlet</servlet-name>
    <url-pattern>/reviewCheckCode</url-pattern>
  </servlet-mapping>
