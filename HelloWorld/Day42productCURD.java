/*
��ѯ���е���Ʒ
 */

//�������ݿ�
CREATE DATABASE day42;
USE day42;

CREATE TABLE `category` (
  `cid` VARCHAR(32) NOT NULL,
  `cname` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


INSERT INTO `category` VALUES ('1','�ֻ�����'),('2','���԰칫'),('3','�Ҿ߼Ҿ�'),('4','Ьѥ���'),('5','ͼ������'),('6','ĸӤ��Ӥ');

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
INSERT INTO `product` VALUES ('1','С�� 4c ��׼��',1399,1299,'products/1/c_0001.jpg','2015-11-02',1,'С�� 4c ��׼�� ȫ��ͨ ��ɫ �ƶ���ͨ����4G�ֻ� ˫��˫��',0,'1')
    ,('10','��Ϊ Ascend Mate7',2699,2599,'products/1/c_0010.jpg','2015-11-02',1,'��Ϊ Ascend Mate7 �¹��� �ƶ�4G�ֻ� ˫��˫��˫ͨ6Ӣ�����������˱��������ܳ��˺ˣ���ѹʽָ��ʶ��!ѡ���·����ƶ����û�4G�����Լ�������軻�ţ����л���ÿ�·�����',0,'1')
    ,('11','vivo X5Pro',2399,2298,'products/1/c_0014.jpg','2015-11-02',1,'�ƶ���ͨ˫4G�ֻ� 3G�˴�� ����ס���������������+�������ĸˡ�������3G�����ڴ桤˫2.5D���沣��������ʶ����',0,'1')
    ,('12','Ŭ���ǣ�nubia��My ������',1899,1799,'products/1/c_0013.jpg','2015-11-02',0,'Ŭ���ǣ�nubia��My ������ ���� �ƶ���ͨ4G�ֻ� ˫��˫������11���µ�����100�������������ٳ�磡���������ȫ�����飡',0,'1')
    ,('13','��Ϊ ��â4',2599,2499,'products/1/c_0012.jpg','2015-11-02',1,'��Ϊ ��â4 ���ؽ� ȫ��ͨ��4G�ֻ� ˫��˫���������� 2.5D������ ָ�ƽ��� ��ѧ����',0,'1')
    ,('14','vivo X5M',1899,1799,'products/1/c_0011.jpg','2015-11-02',0,'vivo X5M �ƶ�4G�ֻ� ˫��˫�� ���Ľ𡾹�������������+�������ĸˡ�5.0Ӣ�������ʾ���˺�˫��˫����Hi-Fi�ƶ�KTV',0,'1')
    ,('15','Apple iPhone 6 (A1586)',4399,4288,'products/1/c_0015.jpg','2015-11-02',1,'Apple iPhone 6 (A1586) 16GB ��ɫ �ƶ���ͨ����4G�ֻ�����ʡ�������ʡ����������ͷѰ棬�����ͻ��ѣ��������Żݣ�����4G���磬������ͨ4G��',0,'1')
    ,('16','��Ϊ HUAWEI Mate S �����',4200,4087,'products/1/c_0016.jpg','2015-11-03',0,'��Ϊ HUAWEI Mate S ����� �ֻ� ����� �ƶ���ͨ˫4G(����)�������ۼ���30Ԫ������������͵�Դ+��ˮ��+�����ֻ�֧�ܣ����Ż�����mate7������',0,'1')
    ,('17','����(SONY) E6533 Z3+',4099,3999,'products/1/c_0017.jpg','2015-11-02',0,'����(SONY) E6533 Z3+ ˫��˫4G�ֻ� ��ˮ���� ����������z3רҵ��ˮ 2070������ �ƶ���ͨ˫4G',0,'1')
    ,('18','HTC One M9+',3599,3499,'products/1/c_0018.jpg','2015-11-02',0,'HTC One M9+��M9pw�� ������ �ƶ���ͨ˫4G�ֻ�5.2Ӣ�磬8��CPU��ָ��ʶ��UltraPixel������ǰ�����+2000��/200�����˫��ͷ�����������������ϲ���ϣ�',0,'1')
    ,('19','HTC Desire 826d 32G �����',1599,1469,'products/1/c_0020.jpg','2015-11-02',1,'����1300��+UltraPixel������ǰ������ͷ+��˫��ǰ��������+5.5Ӣ�硾1080p��������',0,'1'),('2','���� AXON',2899,2699,'products/1/c_0002.jpg','2015-11-05',1,'���� AXON ��� mini ѹ������ B2015 ������ �ƶ���ͨ����4G ˫��˫��',0,'1')
    ,('20','С�� ����2A ��ǿ�� ��ɫ',649,549,'products/1/c_0019.jpg','2015-11-02',0,'������2GB �ڴ�+16GB������4G˫��˫������о 4 �� 1.5GHz ��������',0,'1')
    ,('21','���� ����note2 16GB ��ɫ',1099,999,'products/1/c_0021.jpg','2015-11-02',0,'�ֻ����������꼴ֹ��5.5Ӣ��1080P�ֱ�����Ļ��64λ�˺�1.3GHz��������1300����������ͷ��˫ɫ��˫����ƣ�',0,'1')
    ,('22','���� Galaxy S5 (G9008W) ��ҫ��',2099,1999,'products/1/c_0022.jpg','2015-11-02',1,'5.1Ӣ��ȫ������������2.5GHz�ĺ˴�������1600������',0,'1')
    ,('23','sonim XP7700 4G�ֻ�',1799,1699,'products/1/c_0023.jpg','2015-11-09',1,'���������ֻ� �ƶ�/��ͨ˫4G ��ȫ �ڻ�ɫ ˫4G��������IP69 30�쳤���� 3�׷�ˮ��ˤ ����',0,'1')
    ,('24','Ŭ���ǣ�nubia��Z9��Ӣ�� ��ɫ',3988,3888,'products/1/c_0024.jpg','2015-11-02',1,'�ƶ���ͨ����4G�ֻ� ˫��˫���������ޱ߿򣡽�ɫ���棡4GB+64GB���ڴ棡',0,'1')
    ,('25','Apple iPhone 6 Plus (A1524) 16GB ��ɫ',5188,4988,'products/1/c_0025.jpg','2015-11-02',0,'Apple iPhone 6 Plus (A1524) 16GB ��ɫ �ƶ���ͨ����4G�ֻ� Ӳ�� Ӳʵ��',0,'1')
    ,('26','Apple iPhone 6s (A1700) 64G õ���ɫ',6388,6088,'products/1/c_0026.jpg','2015-11-02',0,'Apple iPhone 6 Plus (A1524) 16GB ��ɫ �ƶ���ͨ����4G�ֻ� Ӳ�� Ӳʵ��',0,'1')
    ,('27','���� Galaxy Note5��N9200��32G��',5588,5388,'products/1/c_0027.jpg','2015-11-02',0,'�콢���ͣ�5.7Ӣ�������4+32G�ڴ棡��һ����SPen���Ż��ĸ���ָ������߳��壡',0,'1')
    ,('28','���� Galaxy S6 Edge+��G9280��32G�� �����',5999,5888,'products/1/c_0028.jpg','2015-11-02',0,'���ƶ���Դ+���ĸ�+����OTG����U��+���߳����+͸��������',0,'1'),('29','LG G4��H818���մɰ� ���ʰ�',3018,2978,'products/1/c_0029.jpg','2015-11-02',0,'��������ԣ�F1.8���Ȧ1600���������ͷ��5.5Ӣ��2K����3G+32G�ڴ棬LG����콢����',0,'1'),('3','��Ϊ��ҫ6',1599,1499,'products/1/c_0003.jpg','2015-11-02',0,'��ҫ 6 (H60-L01) 3GB�ڴ��׼�� ��ɫ �ƶ�4G�ֻ�',0,'1')
    ,('30','΢��(Microsoft) Lumia 640 LTE DS (RM-1113)',1099,999,'products/1/c_0030.jpg','2015-11-02',0,'΢���׿�˫��˫��˫4G�ֻ���5.0Ӣ����������˫��˫��˫4G��',0,'1'),('31','�곞��acer��ATC705-N50 ̨ʽ����',2399,2299,'products/1/c_0031.jpg','2015-11-02',0,'����ֱ������ǧ���٣�Ʒ�ʺ곞���ػ���Ϯ���αؿ��11.11����������ˣ�',0,'2'),('32','Apple MacBook Air MJVE2CH/A 13.3Ӣ��',6788,6688,'products/1/c_0032.jpg','2015-11-02',0,'�����ʼǱ����� 128GB ����',0,'2')
    ,('33','���루ThinkPad�� �ᱡϵ��E450C(20EH0001CD)',4399,4199,'products/1/c_0033.jpg','2015-11-02',0,'���루ThinkPad�� �ᱡϵ��E450C(20EH0001CD)14Ӣ��ʼǱ�����(i5-4210U 4G 500G 2G���� Win8.1)',0,'2'),('34','���루Lenovo��С��V3000�����',4599,4499,'products/1/c_0034.jpg','2015-11-02',0,'14Ӣ�糬���ʼǱ����ԣ�i7-5500U 4G 500G+8G SSHD 2G���� ȫ����������ɫ��1000�p100�����������ȫ������ɨ3�죡',0,'2'),('35','��˶��ASUS������ϵ��R557LI',3799,3699,'products/1/c_0035.jpg','2015-11-02',0,'15.6Ӣ��ʼǱ����ԣ�i5-5200U 4G 7200ת500G 2G���� D�� ���� Win8.1 ��ɫ��',0,'2')
    ,('36','��˶��ASUS��X450J',4599,4399,'products/1/c_0036.jpg','2015-11-02',0,'14Ӣ��ʼǱ����� ��i5-4200H 4G 1TB GT940M 2G���� ����4.0 D�� Win8.1 ��ɫ��',0,'2'),('37','������DELL����Խ ��ϻ3000ϵ��',3399,3299,'products/1/c_0037.jpg','2015-11-03',0,' Ins14C-4528B 14Ӣ��ʼǱ���i5-5200U 4G 500G GT820M 2G���� Win8����',0,'2')
    ,('38','����(HP)WASD ��Ӱ����',5699,5499,'products/1/c_0038.jpg','2015-11-02',0,'15.6Ӣ����Ϸ�ʼǱ�����(i5-6300HQ 4G 1TB+128G SSD GTX950M 4G���� Win10)',0,'2')
    ,('39','Apple �䱸 Retina ��ʾ���� MacBook',11299,10288,'products/1/c_0039.jpg','2015-11-02',0,'Pro MF840CH/A 13.3Ӣ������ʼǱ����� 256GB ����',0,'2')
    ,('4','���� P1',2199,1999,'products/1/c_0004.jpg','2015-11-02',0,'���� P1 16G ������ �ƶ���ͨ4G�ֻ����5���ӣ�ͨ��3Сʱ���Ƽ�Դ�ڳ�Խ��Ʒ��Դ�ڳ���5000mAh���أ��߶�������䣡',0,'1')
    ,('40','��е������MECHREVO��MR X6S-M',6799,6599,'products/1/c_0040.jpg','2015-11-02',0,'15.6Ӣ����Ϸ��(I7-4710MQ 8G 64GSSD+1T GTX960M 2G���� IPS�� WIN7)��ɫ',0,'2')
    ,('41','���ۣ�HASEE�� ս��K660D-i7D2',5699,5499,'products/1/c_0041.jpg','2015-11-02',0,'15.6Ӣ����Ϸ��(i7-4710MQ 8G 1TB GTX960M 2G���� 1080P)��ɫ',0,'2')
    ,('42','΢�ǣ�MSI��GE62 2QC-264XCN',6199,5999,'products/1/c_0042.jpg','2015-11-02',0,'15.6Ӣ����Ϸ�ʼǱ����ԣ�i5-4210H 8G 1T GTX960MG DDR5 2G ������̣���ɫ',0,'2')
    ,('43','����ThundeRobot��G150S',5699,5499,'products/1/c_0043.jpg','2015-11-02',0,'15.6Ӣ����Ϸ�� ( i7-4710MQ 4G 500G GTX950M 2G���� ��������ȫ������) ��',0,'2'),('44','���գ�HP���ᱡϵ�� HP',3199,3099,'products/1/c_0044.jpg','2015-11-02',0,'15-r239TX 15.6Ӣ��ʼǱ����ԣ�i5-5200U 4G 500G GT820M 2G���� win8.1��������',0,'2'),('45','δ�����ࣨTerrans Force��T5',10999,9899,'products/1/c_0045.jpg','2015-11-02',0,'15.6Ӣ����Ϸ����i7-5700HQ 16G 120G��̬+1TB GTX970M 3G GDDR5���ԣ���',0,'2'),('46','������DELL��Vostro 3800-R6308 ̨ʽ����',3299,3199,'products/1/c_0046.jpg','2015-11-02',0,'��i3-4170 4G 500G DVD �������ŷ��� Win7����',0,'2')
    ,('47','���루Lenovo��H3050 ̨ʽ����',5099,4899,'products/1/c_0047.jpg','2015-11-11',0,'��i5-4460 4G 500G GT720 1G���� DVD ǧ������ Win10��23Ӣ��',0,'2'),('48','Apple iPad mini 2 ME279CH/A',2088,1888,'products/1/c_0048.jpg','2015-11-02',0,'���䱸 Retina ��ʾ�� 7.9Ӣ�� 16G WLAN ���� ��ɫ��',0,'2')
    ,('49','С�ף�MI��7.9Ӣ��ƽ��',1399,1299,'products/1/c_0049.jpg','2015-11-02',0,'WIFI 64GB��NVIDIA Tegra K1 2.2GHz 2G 64G 2048*1536����Ĥ�� 800W����ɫ',0,'2')
    ,('5','Ħ������ moto x��x+1��',1799,1699,'products/1/c_0005.jpg','2015-11-01',0,'Ħ������ moto x��x+1��(XT1085) 32GB ��Ȼ�� ȫ��ͨ4G�ֻ�11��11�죡MOTO X���ػ���Ϯ��1699Ԫ��������ת�ڿƼ�����Ȼ���ʣ�ԭ������ϵͳ��',0,'1')
    ,('50','Apple iPad Air 2 MGLW2CH/A',2399,2299,'products/1/c_0050.jpg','2015-11-12',0,'��9.7Ӣ�� 16G WLAN ���� ��ɫ��',0,'2'),('6','���� MX5 16GB ����ɫ',1899,1799,'products/1/c_0006.jpg','2015-11-02',0,'���� MX5 16GB ����ɫ �ƶ���ͨ˫4G�ֻ� ˫��˫����ԭ���ֻ�Ĥ+������+������5.5Ӣ�����Ļ��3G�����ڴ棬2070��+500����������ͷ������ʡ�������ʡ��',0,'1'),('7','���� Galaxy On7',1499,1398,'products/1/c_0007.jpg','2015-11-14',0,'���� Galaxy On7��G6000����С�� ��ɫ ȫ��ͨ4G�ֻ� ˫��˫����Ʒ�������У���������ǧԪ������5.5Ӣ����������1300+500W���أ�����Ӯ30Ԫ����ȯ��',0,'1')
    ,('8','NUU NU5',1288,1190,'products/1/c_0008.jpg','2015-11-02',0,'NUU NU5 16GB �ƶ���ͨ˫4G�����ֻ� ˫��˫�� ɹ������ ��������Ʒ�� 2.5D����ǰ��ֻ����� ��������ֻ���+�ֻ���Ĥ ɹ�����ƶ���Դ+��������',0,'1')
    ,('9','���ӣ�Letv����1pro��X800��',2399,2299,'products/1/c_0009.jpg','2015-11-02',0,'���ӣ�Letv����1pro��X800��64GB ��ɫ �ƶ���ͨ4G�ֻ� ˫��˫��������̬UI+5.5Ӣ��2K��+��ͨ8�˴�����+4GB�����ڴ�+64GB�洢+1300������ͷ��',0,'1');

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
		<th>���</th>
		<th>��Ʒ����</th>
		<th>�г��۸�</th>
		<th>�̳��۸�</th>
		<th>ͼƬ</th>
		<th>�ϼ�ʱ��</th>
		<th>�Ƿ�����</th>
		<th>��Ʒ����</th>
		<th>�Ƿ��ϼ�</th>
	</tr>
	<c:forEach varStatus="status" items="${requestScope.productList}" var="item">
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
		<!-- �Ƿ��¼�,1�����¼�,0�����ϼ� -->
		<td>
			<c:if test="${item.pflag==1}">
				�¼�
			</c:if>
			<c:if test="${item.pflag==0}">
				δ�¼�
			</c:if>
		</td>
	</tr>
	</c:forEach>
</table>
      <center>
	      <a href="${pageContext.request.contextPath}/categoryServlet">�����Ʒ</a>
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

//ProductService.java
package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.dao.ProductDao;
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
	//��ѯ���Կ�����Ʒ
	public List<Product> retrieveProduct() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		//��ѯ���ݿ��е�������Ʒ
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
	private int is_hot;//�Ƿ�����,1��������,0��������
	private String pdesc;
	private int pflag;//�Ƿ��ϼ�,1�����¼�,0�����¼�
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
��ѯȫ����Ʒ�ķ���
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
	//��ѯcategory���е����з���,��BeanListHandler<Category>(Category),����Category�����list����
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
�޸�һ����Ʒ
��һ��,������޸�ʱ,����ʾ��Ʒ��������Ϣ
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
�޸���Ʒ
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
<title>������Ʒ</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/update">
<table border="1" width="50%" align="center">

<tr><td>��Ʒ����:<input type="text" name="pname" value="${requestScope.updatingProduct.pname}"/></td></tr>
<tr><td>�г��۸�:<input type="text" name="market_price" value="${requestScope.updatingProduct.market_price}"/></td></tr>
<tr><td>�̳Ǽ۸�:<input type="text" name="shop_price" value="${requestScope.updatingProduct.shop_price}"/></td></tr>
<tr><td>��ƷͼƬ:<input type="text" name="pimage" value="${requestScope.updatingProduct.pimage}"/></td></tr>
<!-- ������1,��������0 -->
<tr><td>�Ƿ�����:
	<!-- ����pid -->
	<input type="hidden" name="pid" value="${requestScope.updatingProduct.pid}"/>
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
<tr><td><button>�޸�</button></td></tr>
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
		//���productList.jsp�е��޸�����ʱ����ת�����÷�����
		//������������ı����ʽ
		request.setCharacterEncoding("utf-8");
		//������Ӧ�ı����ʽ
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�û����������
		String pid = request.getParameter("pid");
		//��װ����(��)
		//�����ݿ��в�ѯ,��ת��ҵ��㴦��
		ProductService service = new ProductService();
		Product product = service.findOneProduct(pid);
		//�����������������,updatingProduct����Ҫ�޸ĵĲ�Ʒ����,��������addProduct.jsp��չʾ����Ʒ����Ϣ
		request.setAttribute("updatingProduct", product);
		
		//����addProduct.jsp�е��������������˵�,ͬCategoryServlet�еĴ���һ��
		CategoryService service1 = new CategoryService();
		List<Category> list = service1.getAllCategory();
		//����request����,�Ѹ����Դ��ݵ�addProduct.jsp��,����ͨ��jspҳ���request���ö�����ʵ�
		request.setAttribute("categoryList", list);
		
		//����ת��
		request.getRequestDispatcher("/updateProduct.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���updateProduct.jsp�е��޸İ�ť,����ת������servlet��
		//��������ı����ʽ
		request.setCharacterEncoding("utf-8");
		//������Ӧ�ı����ʽ
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�û�����
		Map<String, String[]> parameterMap = request.getParameterMap();
		//��װ����
		Product product = new Product();
		try {
			BeanUtils.populate(product, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		//ת��service��,�޸����ݿ��е�����
		ProductService service = new ProductService();
		service.update(product);
		//�ض���index.jsp
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

}

/*
 *
 *
ɾ����Ʒ
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
	<c:forEach varStatus="status" items="${requestScope.productList}" var="item">
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
	//ҳ��������,Ϊɾ�����Ӱ󶨵���¼�
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
		//������������ʽ
		request.setCharacterEncoding("utf-8");
		//������Ӧ�ı����ʽ
		response.setContentType("text/html;charset=utf-8");
		//�����û�������
		String pid = request.getParameter("pid");
		//��װ����(��)
		//ת��service�㴦��,ɾ������Ʒ��¼
		ProductService service = new ProductService();
		service.delete(pid);
		//�ض�����ҳ,index.jsp
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

}
