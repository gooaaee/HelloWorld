/*
查询所有的商品
 */

//创建数据库
CREATE DATABASE day42;
USE day42;

CREATE TABLE `category` (
  `cid` VARCHAR(32) NOT NULL,
  `cname` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO `category` VALUES ('1','手机数码'),('2','电脑办公'),('3','家具家居'),('4','鞋靴箱包'),('5','图书音像'),('6','母婴孕婴');

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
1
INSERT INTO `product` VALUES ('1','小米 4c 标准版',1399,1299,'products/1/c_0001.jpg','2015-11-02',1,'小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待',0,'1')
    ,('10','华为 Ascend Mate7',2699,2599,'products/1/c_0010.jpg','2015-11-02',1,'华为 Ascend Mate7 月光银 移动4G手机 双卡双待双通6英寸高清大屏，纤薄机身，智能超八核，按压式指纹识别！!选择下方“移动老用户4G飞享合约”，无需换号，还有话费每月返还！',0,'1')
    ,('11','vivo X5Pro',2399,2298,'products/1/c_0014.jpg','2015-11-02',1,'移动联通双4G手机 3G运存版 极光白【购机送蓝牙耳机+蓝牙自拍杆】新升级3G运行内存·双2.5D弧面玻璃·眼球识别技术',0,'1')
    ,('12','努比亚（nubia）My 布拉格',1899,1799,'products/1/c_0013.jpg','2015-11-02',0,'努比亚（nubia）My 布拉格 银白 移动联通4G手机 双卡双待【嗨11，下单立减100】金属机身，快速充电！布拉格相机全新体验！',0,'1')
    ,('13','华为 麦芒4',2599,2499,'products/1/c_0012.jpg','2015-11-02',1,'华为 麦芒4 晨曦金 全网通版4G手机 双卡双待金属机身 2.5D弧面屏 指纹解锁 光学防抖',0,'1')
    ,('14','vivo X5M',1899,1799,'products/1/c_0011.jpg','2015-11-02',0,'vivo X5M 移动4G手机 双卡双待 香槟金【购机送蓝牙耳机+蓝牙自拍杆】5.0英寸大屏显示·八核双卡双待·Hi-Fi移动KTV',0,'1')
    ,('15','Apple iPhone 6 (A1586)',4399,4288,'products/1/c_0015.jpg','2015-11-02',1,'Apple iPhone 6 (A1586) 16GB 金色 移动联通电信4G手机长期省才是真的省！点击购机送费版，月月送话费，月月享优惠，畅享4G网络，就在联通4G！',0,'1')
    ,('16','华为 HUAWEI Mate S 臻享版',4200,4087,'products/1/c_0016.jpg','2015-11-03',0,'华为 HUAWEI Mate S 臻享版 手机 极昼金 移动联通双4G(高配)满星评价即返30元话费啦；买就送电源+清水套+创意手机支架；优雅弧屏，mate7升级版',0,'1')
    ,('17','索尼(SONY) E6533 Z3+',4099,3999,'products/1/c_0017.jpg','2015-11-02',0,'索尼(SONY) E6533 Z3+ 双卡双4G手机 防水防尘 涧湖绿索尼z3专业防水 2070万像素 移动联通双4G',0,'1')
    ,('18','HTC One M9+',3599,3499,'products/1/c_0018.jpg','2015-11-02',0,'HTC One M9+（M9pw） 金银汇 移动联通双4G手机5.2英寸，8核CPU，指纹识别，UltraPixel超像素前置相机+2000万/200万后置双镜头相机！降价特卖，惊喜不断！',0,'1')
    ,('19','HTC Desire 826d 32G 臻珠白',1599,1469,'products/1/c_0020.jpg','2015-11-02',1,'后置1300万+UltraPixel超像素前置摄像头+【双】前置扬声器+5.5英寸【1080p】大屏！',0,'1'),('2','中兴 AXON',2899,2699,'products/1/c_0002.jpg','2015-11-05',1,'中兴 AXON 天机 mini 压力屏版 B2015 华尔金 移动联通电信4G 双卡双待',0,'1')
    ,('20','小米 红米2A 增强版 白色',649,549,'products/1/c_0019.jpg','2015-11-02',0,'新增至2GB 内存+16GB容量！4G双卡双待，联芯 4 核 1.5GHz 处理器！',0,'1')
    ,('21','魅族 魅蓝note2 16GB 白色',1099,999,'products/1/c_0021.jpg','2015-11-02',0,'现货速抢，抢完即止！5.5英寸1080P分辨率屏幕，64位八核1.3GHz处理器，1300万像素摄像头，双色温双闪光灯！',0,'1')
    ,('22','三星 Galaxy S5 (G9008W) 闪耀白',2099,1999,'products/1/c_0022.jpg','2015-11-02',1,'5.1英寸全高清炫丽屏，2.5GHz四核处理器，1600万像素',0,'1')
    ,('23','sonim XP7700 4G手机',1799,1699,'products/1/c_0023.jpg','2015-11-09',1,'三防智能手机 移动/联通双4G 安全 黑黄色 双4G美国军工IP69 30天长待机 3米防水防摔 北斗',0,'1')
    ,('24','努比亚（nubia）Z9精英版 金色',3988,3888,'products/1/c_0024.jpg','2015-11-02',1,'移动联通电信4G手机 双卡双待真正的无边框！金色尊贵版！4GB+64GB大内存！',0,'1')
    ,('25','Apple iPhone 6 Plus (A1524) 16GB 金色',5188,4988,'products/1/c_0025.jpg','2015-11-02',0,'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力',0,'1')
    ,('26','Apple iPhone 6s (A1700) 64G 玫瑰金色',6388,6088,'products/1/c_0026.jpg','2015-11-02',0,'Apple iPhone 6 Plus (A1524) 16GB 金色 移动联通电信4G手机 硬货 硬实力',0,'1')
    ,('27','三星 Galaxy Note5（N9200）32G版',5588,5388,'products/1/c_0027.jpg','2015-11-02',0,'旗舰机型！5.7英寸大屏，4+32G内存！不一样的SPen更优化的浮窗指令！赠无线充电板！',0,'1')
    ,('28','三星 Galaxy S6 Edge+（G9280）32G版 铂光金',5999,5888,'products/1/c_0028.jpg','2015-11-02',0,'赠移动电源+自拍杆+三星OTG金属U盘+无线充电器+透明保护壳',0,'1'),('29','LG G4（H818）陶瓷白 国际版',3018,2978,'products/1/c_0029.jpg','2015-11-02',0,'李敏镐代言，F1.8大光圈1600万后置摄像头，5.5英寸2K屏，3G+32G内存，LG年度旗舰机！',0,'1'),('3','华为荣耀6',1599,1499,'products/1/c_0003.jpg','2015-11-02',0,'荣耀 6 (H60-L01) 3GB内存标准版 黑色 移动4G手机',0,'1')
    ,('30','微软(Microsoft) Lumia 640 LTE DS (RM-1113)',1099,999,'products/1/c_0030.jpg','2015-11-02',0,'微软首款双网双卡双4G手机，5.0英寸高清大屏，双网双卡双4G！',0,'1'),('31','宏碁（acer）ATC705-N50 台式电脑',2399,2299,'products/1/c_0031.jpg','2015-11-02',0,'爆款直降，满千减百，品质宏碁，特惠来袭，何必苦等11.11，早买早便宜！',0,'2'),('32','Apple MacBook Air MJVE2CH/A 13.3英寸',6788,6688,'products/1/c_0032.jpg','2015-11-02',0,'宽屏笔记本电脑 128GB 闪存',0,'2')
    ,('33','联想（ThinkPad） 轻薄系列E450C(20EH0001CD)',4399,4199,'products/1/c_0033.jpg','2015-11-02',0,'联想（ThinkPad） 轻薄系列E450C(20EH0001CD)14英寸笔记本电脑(i5-4210U 4G 500G 2G独显 Win8.1)',0,'2'),('34','联想（Lenovo）小新V3000经典版',4599,4499,'products/1/c_0034.jpg','2015-11-02',0,'14英寸超薄笔记本电脑（i7-5500U 4G 500G+8G SSHD 2G独显 全高清屏）黑色满1000減100，狂减！火力全开，横扫3天！',0,'2'),('35','华硕（ASUS）经典系列R557LI',3799,3699,'products/1/c_0035.jpg','2015-11-02',0,'15.6英寸笔记本电脑（i5-5200U 4G 7200转500G 2G独显 D刻 蓝牙 Win8.1 黑色）',0,'2')
    ,('36','华硕（ASUS）X450J',4599,4399,'products/1/c_0036.jpg','2015-11-02',0,'14英寸笔记本电脑 （i5-4200H 4G 1TB GT940M 2G独显 蓝牙4.0 D刻 Win8.1 黑色）',0,'2'),('37','戴尔（DELL）灵越 飞匣3000系列',3399,3299,'products/1/c_0037.jpg','2015-11-03',0,' Ins14C-4528B 14英寸笔记本（i5-5200U 4G 500G GT820M 2G独显 Win8）黑',0,'2')
    ,('38','惠普(HP)WASD 暗影精灵',5699,5499,'products/1/c_0038.jpg','2015-11-02',0,'15.6英寸游戏笔记本电脑(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G独显 Win10)',0,'2')
    ,('39','Apple 配备 Retina 显示屏的 MacBook',11299,10288,'products/1/c_0039.jpg','2015-11-02',0,'Pro MF840CH/A 13.3英寸宽屏笔记本电脑 256GB 闪存',0,'2')
    ,('4','联想 P1',2199,1999,'products/1/c_0004.jpg','2015-11-02',0,'联想 P1 16G 伯爵金 移动联通4G手机充电5分钟，通话3小时！科技源于超越！品质源于沉淀！5000mAh大电池！高端商务佳配！',0,'1')
    ,('40','机械革命（MECHREVO）MR X6S-M',6799,6599,'products/1/c_0040.jpg','2015-11-02',0,'15.6英寸游戏本(I7-4710MQ 8G 64GSSD+1T GTX960M 2G独显 IPS屏 WIN7)黑色',0,'2')
    ,('41','神舟（HASEE） 战神K660D-i7D2',5699,5499,'products/1/c_0041.jpg','2015-11-02',0,'15.6英寸游戏本(i7-4710MQ 8G 1TB GTX960M 2G独显 1080P)黑色',0,'2')
    ,('42','微星（MSI）GE62 2QC-264XCN',6199,5999,'products/1/c_0042.jpg','2015-11-02',0,'15.6英寸游戏笔记本电脑（i5-4210H 8G 1T GTX960MG DDR5 2G 背光键盘）黑色',0,'2')
    ,('43','雷神（ThundeRobot）G150S',5699,5499,'products/1/c_0043.jpg','2015-11-02',0,'15.6英寸游戏本 ( i7-4710MQ 4G 500G GTX950M 2G独显 包无亮点全高清屏) 金',0,'2'),('44','惠普（HP）轻薄系列 HP',3199,3099,'products/1/c_0044.jpg','2015-11-02',0,'15-r239TX 15.6英寸笔记本电脑（i5-5200U 4G 500G GT820M 2G独显 win8.1）金属灰',0,'2'),('45','未来人类（Terrans Force）T5',10999,9899,'products/1/c_0045.jpg','2015-11-02',0,'15.6英寸游戏本（i7-5700HQ 16G 120G固态+1TB GTX970M 3G GDDR5独显）黑',0,'2'),('46','戴尔（DELL）Vostro 3800-R6308 台式电脑',3299,3199,'products/1/c_0046.jpg','2015-11-02',0,'（i3-4170 4G 500G DVD 三年上门服务 Win7）黑',0,'2')
    ,('47','联想（Lenovo）H3050 台式电脑',5099,4899,'products/1/c_0047.jpg','2015-11-11',0,'（i5-4460 4G 500G GT720 1G独显 DVD 千兆网卡 Win10）23英寸',0,'2'),('48','Apple iPad mini 2 ME279CH/A',2088,1888,'products/1/c_0048.jpg','2015-11-02',0,'（配备 Retina 显示屏 7.9英寸 16G WLAN 机型 银色）',0,'2')
    ,('49','小米（MI）7.9英寸平板',1399,1299,'products/1/c_0049.jpg','2015-11-02',0,'WIFI 64GB（NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536视网膜屏 800W）白色',0,'2')
    ,('5','摩托罗拉 moto x（x+1）',1799,1699,'products/1/c_0005.jpg','2015-11-01',0,'摩托罗拉 moto x（x+1）(XT1085) 32GB 天然竹 全网通4G手机11月11天！MOTO X震撼特惠来袭！1699元！带你玩转黑科技！天然材质，原生流畅系统！',0,'1')
    ,('50','Apple iPad Air 2 MGLW2CH/A',2399,2299,'products/1/c_0050.jpg','2015-11-12',0,'（9.7英寸 16G WLAN 机型 银色）',0,'2'),('6','魅族 MX5 16GB 银黑色',1899,1799,'products/1/c_0006.jpg','2015-11-02',0,'魅族 MX5 16GB 银黑色 移动联通双4G手机 双卡双待送原厂钢化膜+保护壳+耳机！5.5英寸大屏幕，3G运行内存，2070万+500万像素摄像头！长期省才是真的省！',0,'1'),('7','三星 Galaxy On7',1499,1398,'products/1/c_0007.jpg','2015-11-14',0,'三星 Galaxy On7（G6000）昂小七 金色 全网通4G手机 双卡双待新品火爆抢购中！京东尊享千元良机！5.5英寸高清大屏！1300+500W像素！评价赢30元话费券！',0,'1')
    ,('8','NUU NU5',1288,1190,'products/1/c_0008.jpg','2015-11-02',0,'NUU NU5 16GB 移动联通双4G智能手机 双卡双待 晒单有礼 晨光金香港品牌 2.5D弧度前后钢化玻璃 随机附赠手机套+钢化贴膜 晒单送移动电源+蓝牙耳机',0,'1')
    ,('9','乐视（Letv）乐1pro（X800）',2399,2299,'products/1/c_0009.jpg','2015-11-02',0,'乐视（Letv）乐1pro（X800）64GB 金色 移动联通4G手机 双卡双待乐视生态UI+5.5英寸2K屏+高通8核处理器+4GB运行内存+64GB存储+1300万摄像头！',0,'1');

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
		<th>序号</th>
		<th>商品名称</th>
		<th>市场价格</th>
		<th>商场价格</th>
		<th>图片</th>
		<th>上架时间</th>
		<th>是否热门</th>
		<th>商品描述</th>
		<th>是否上架</th>
	</tr>
	<c:forEach varStatus="status" items="${requestScope.productList}" var="item">
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
		<!-- 是否下架,1代表下架,0代表上架 -->
		<td>
			<c:if test="${item.pflag==1}">
				下架
			</c:if>
			<c:if test="${item.pflag==0}">
				未下架
			</c:if>
		</td>
	</tr>
	</c:forEach>
</table>
      <center>
	      <a href="${pageContext.request.contextPath}/categoryServlet">添加商品</a>
      </center>	   
</body>
</html>

 //RetrieveServlet.java
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
		System.out.println("1111111111");
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

//ProductService.java
package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.ProductDao;
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
}

//ProductDao.java
package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
	<jsp:forward page="/RetrieveServlet"></jsp:forward>
</body>
</html>

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
	private int pflag;//是否上架,1代表下架,0代表下架
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
 *
 *
查询全部商品的分类
*
*
*
*
*/

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

/*
 *
 *
 *
修改一件商品
第一步,当点击修改时,会显示商品的所有信息
*
*
*
 */

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

//AddProductService.java
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

/*
 *
 *
 *
修改商品
*
*
*
*/

//updateProduct.jsp
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
<form method="post" action="${pageContext.request.contextPath}/update">
<table border="1" width="50%" align="center">

<tr><td>商品名称:<input type="text" name="pname" value="${requestScope.updatingProduct.pname}"/></td></tr>
<tr><td>市场价格:<input type="text" name="market_price" value="${requestScope.updatingProduct.market_price}"/></td></tr>
<tr><td>商城价格:<input type="text" name="shop_price" value="${requestScope.updatingProduct.shop_price}"/></td></tr>
<tr><td>商品图片:<input type="text" name="pimage" value="${requestScope.updatingProduct.pimage}"/></td></tr>
<!-- 热门是1,不热门是0 -->
<tr><td>是否热门:
	<!-- 隐藏pid -->
	<input type="hidden" name="pid" value="${requestScope.updatingProduct.pid}"/>
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
<tr><td><button>修改</button></td></tr>
</table>   
</form>

</body>
</html>

//UpdateServlet.java
package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.CategoryService;
import com.itheima.service.ProductService;

public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//点击productList.jsp中的修改链接时请求转发到该方法中
		//设置请求参数的编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应的编码格式
		response.setContentType("text/html;charset=utf-8");
		//获取用户输入的数据
		String pid = request.getParameter("pid");
		//封装数据(无)
		//在数据库中查询,跳转到业务层处理
		ProductService service = new ProductService();
		Product product = service.findOneProduct(pid);
		//设置请求参数的属性,updatingProduct代表将要修改的产品对象,首先是在addProduct.jsp中展示该商品的信息
		request.setAttribute("updatingProduct", product);
		
		//设置addProduct.jsp中的所属分类下拉菜单,同CategoryServlet中的代码一样
		CategoryService service1 = new CategoryService();
		List<Category> list = service1.getAllCategory();
		//设置request属性,把该属性传递到addProduct.jsp中,可以通过jsp页面的request内置对象访问到
		request.setAttribute("categoryList", list);
		
		//请求转发
		request.getRequestDispatcher("/updateProduct.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//点击updateProduct.jsp中的修改按钮,请求转发到该servlet中
		//设置请求的编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应的编码格式
		response.setContentType("text/html;charset=utf-8");
		//获取用户参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		//封装数据
		Product product = new Product();
		try {
			BeanUtils.populate(product, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//转到service层,修改数据库中的数据
		ProductService service = new ProductService();
		service.update(product);
		//重定向到index.jsp
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
}

//ProductService.java
package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.ProductDao;
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
}

//ProductDao.java
package com.itheima.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

}

/*
 *
 *
删除商品
*
*
*/

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
	//页面加载完毕,为删除链接绑定点击事件
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
	
	
});
</script>
</body>
</html>

//DeleteServlet.java
package com.itheima.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.ProductService;

public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求编码格式
		request.setCharacterEncoding("utf-8");
		//设置响应的编码格式
		response.setContentType("text/html;charset=utf-8");
		//接收用户的数据
		String pid = request.getParameter("pid");
		//封装数据(无)
		//转到service层处理,删除该商品记录
		ProductService service = new ProductService();
		service.delete(pid);
		//重定向到首页,index.jsp
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

//ProductService.java
package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.ProductDao;
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
}

//ProductDao.java
package com.itheima.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

}
