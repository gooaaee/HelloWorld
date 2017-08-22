/*
针对以下约束描述，请为其写出相应的schema约束文件：
1.根元素为：books，无属性；
2.根元素下可以包含book子元素，可以出现0次或多次；
3.book子元素必须有一个属性：bookid；
4.book元素下可以有以下子元素：title，author，price。
这些子元素必须按顺序出现，可以出现0次或多次；
 */
<?xml version="1.0" encoding="UTF-8"?>
<!--
	模拟spring规范，如果开发人员需要在xml使用当前Schema约束，必须包括指定命名空间。
	格式如下：
	<books xmlns="http://www.xxx.com/book"
	   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	   xsi:schemaLocation="http://www.xxx.com/bean bean-schema.xsd"
	>
-->
<schema xmlns="http://www.w3.org/2001/XMLSchema"
		targetNamespace="http://www.xxx.com/book"
		xmlns:xsd="http://www.w3.org/2001/XMLSchema"
		xmlns:tns="http://www.xxx.com/book"
		elementFormDefault="qualified">
	<!-- 声明根标签 -->
	<element name="books">
		<complexType>
			<choice minOccurs="0" maxOccurs="unbounded">
				<element name="book">
					<complexType>
						<sequence minOccurs="0" maxOccurs="unbounded">
							<attribute name="bookid" use="required"></attribute>
							<element name="tittle">
								<complexType>
									<sequence minOccurs="0" maxOccurs="unbounded">
									<\sequence>
								<\complexType>
							</element>
							<element name="author">
									<complexType>
									<sequence minOccurs="0" maxOccurs="unbounded">
									<\sequence>
								<\complexType>
							</element>
							<element name="price">
								<complexType>
									<sequence minOccurs="0" maxOccurs="unbounded">
									<\sequence>
								<\complexType>
							</element>
							</element>
						</sequence>
					</complexType>
				</element>
			</choice>
		</complexType>
	</element>
</schema>

/*
dom4j解析xml文件
有以下XML文档，请使用dom4j解析出所有的商品信息，并打印商品的所有属性值：
product.xml
<?xml version = “1.0” encoding=”UTF-8”?>
<products>
	<product id = “p001”>
		<name>华为X80</name>
		<color>白</color>
		<price>2800</price>
	</product>
	<product id = “p002”>
		<name>华为X88</name>
		<color>黑</color>
		<price>2900</price>
	</product>
</products>
 */
public class One12 {
	public static void main(String[] args) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read("One12.xml");
		Element root = document.getRootElement();
		List<Element> productElements = root.elements();
		for (Element productElement : productElements) {
			List<Element> productChilds = productElement.elements();
			String id = productElement.attributeValue("id");
			String name = productElement.elementText("name");
			String color = productElement.elementText("color");
			String price = productElement.elementText("price");
			System.out.println("id:" + id + ", name:" + name + ", color:" + color + ", price:" + price);
		}
	}
}

/*
针对以下约束描述，请为其写出相应的DTD约束文件：以及相应的xml文件：
1.根元素为：students
2.根元素有个属性：schoolName;
3.根元素中只能包含student元素，可以不出现，也可以出现多次。
4.student元素有一个属性：id，必须使用
5.student元素下只能有：name，sex，age三个子元素；这三个子元素必须按顺序出现，而且每个子元素只能出现一次；
6.name，sex，age三个子元素没有属性，而且只能包含文本内容；
 */
public class Two1 {}
<?xml version="1.0" encoding="UTF-8"?>
<!ELEMENT students (student*)>
<!ELEMENT student (name,sex,age)>
<!ELEMENT name (#PCDATA)>
<!ELEMENT sex (#PCDATA)>
<!ELEMENT age (#PCDATA)>
<!ATTLIST students schoolName CDATA>
<!ATTLIST student id CDATA #REQUIRED>

/*
针对以下约束描述，请为其写出相应的DTD约束文件以及xml文件：
1.根元素为：servlets
2.根元素有个属性：version，可以不出现。
3.根元素中只能包含servlet元素和servlet-mapping元素，必须按顺序出现，每个元素可以出现0次或多次；
4.servlet元素只能包含servlet-name元素和servlet-class元素，必须出现，只能出现一次，而且要按照顺序出现。
5.servlet-mapping元素只能包含servlet-name元素和url-mapping元素，必须出现，只能出现一次，而且要按照顺序出现。
servlet元素和servlet-mapping元素的所有子元素都只能包含文本内容。
 */
public class Two2 {}
<?xml version="1.0" encoding="UTF-8"?>
<!ELEMENT servlets (servlet*,servlet-mapping*)>
<!ELEMENT servlet (servlet-name,servlet-class)>
<!ELEMENT servlet-mapping (servlet-name,url-mapping)>
<!ELEMENT servlet-name (#PCDATA)>
<!ELEMENT servlet-class (#PCDATA)>
<!ELEMENT url-mapping (#PCDATA)>

/*
有以下XML文件：
book.xml
<?xml version="1.0" encoding="UTF-8"?>
<books>
	<book id = "1">
		<title>JavaEE从入门到精通</title>
		<publishDate>2017-01-20</publishDate>
		<price>88</price>
	</book>
	<book id = "2">
		<title>Oracle管理员手册</title>
		<publishDate>2017-05-01</publishDate>
		<price>65</price>
	</book>
	<book id = "3">
		<title>JavaSE开发手册</title>
		<publishDate>2016-12-10</publishDate>
		<price>35</price>
	</book>
</books>

请按以下要求解析XML文件：
1.查询出所有id大于等于2的所有图书信息，并打印；
2.查询出所有书名中包含Java的所有图书信息，并打印；
3.查询出所有价格大于50元的所有图书信息，并打印；


1.创建项目day17作业_Test2_3
2.在项目目录下，按照已给出的XML内容创建book.xml文件。
3.创建类ParseXML，针对上述三个需求，分别编写三个方法实现功能；
4.使用JUnit测试每个方法。
 */
public class Two3 {
	//查询出所有id大于等于2的所有图书信息，并打印
	@Test
	public void idFilter() throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read("book.xml");
		Element rootElement = document.getRootElement();
		List<Element> bookElements = rootElement.elements();

		for (Element bookElement : bookElements) {
			String id = bookElement.attributeValue("id");
			if (Integer.parseInt(id) >= 2) {
				List<Element> bookChilds = bookElement.elements();
				for (Element bookChild : bookChilds) {
					System.out.println(bookChild.getText());
				}
			}
		}
	}

	//查询出所有书名中包含Java的所有图书信息，并打印
	@Test
	public void printAll() throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read("book.xml");
		Element rootElement = document.getRootElement();
		List<Element> bookElements = rootElement.elements();
		for(Element bookElement : bookElements){
			List<Element> bookChilds = bookElement.elements();
			for(Element bookChild : bookChilds){
				System.out.println(bookChild.getText());
			}
		}
	}

	//查询出所有价格大于50元的所有图书信息，并打印
	@Test
	public void priceFilter() throws DocumentException{
		SAXReader reader = new SAXReader();
		Document document = reader.read("book.xml");
		Element rootElement = document.getRootElement();
		List<Element> bookElements = rootElement.elements();
		for(Element bookElement : bookElements){
			String price = bookElement.elementText("price");
			if(Integer.parseInt(price) > 50){
				List<Element> bookChilds = bookElement.elements();
				for(Element bookChild : bookChilds){
					System.out.println(bookChild.getText());
				}
			}
		}
	}
}

/*
有以下XML文件：
web.xml
<?xml version="1.0" encoding="UTF-8"?>
<servlets>
	<servlet>
		<servlet-name>servlet1</servlet-name>
		<servlet-class>cn.itheima.servlets.MyServlet1</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>servlet1</servlet-name>
		<url-pattern>/s1</url-pattern>
	</servlet-mapping>
	<servlet>
		<servlet-name>servlet2</servlet-name>
		<servlet-class>cn.itheima.servlets.MyServlet2</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>servlet2</servlet-name>
		<url-pattern>/s2</url-pattern>
	</servlet-mapping>
</servlets>

编写程序，请按以下要求解析XML文件：
1.接收用户输入一个字符串，例如：”/s1”，要求程序可以通过检索<servlet-mapping>元素下的<url-pattern>元素进匹配，匹配后获取同级的<servlet-name>元素的值；通过这个值再检索<servlet>元素下的<servlet-name>元素的值进行匹配，匹配成功后，获取同级下的<url-pattern>的值，并打印<url-pattern>的值。
例如：用户输入：/s1，控制台打印：cn.itheima.servlets.MyServlet1
      用户输入：/s2，控制台打印：cn.itheima.servlets.MyServlet2

1.创建项目day17作业_Test3_1
2.在项目目录下，按照已给出的XML内容创建web.xml文件。
3.创建类ParseXML，针对上述需求，编写方法findClass()实现功能；
4.使用JUnit测试这个个方法。
 */
public class Three1 {
	@Test
	public void findClass() throws DocumentException{
		System.out.println("请输入一个web路径");
		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();

		SAXReader reader = new SAXReader();
		Document document = reader.read("web.xml");
		Element rootElement = document.getRootElement();
		List<Element> servletMappingElements = rootElement.elements("servlet-mapping");
		String servletName = "";
		for(Element servletMappingElement : servletMappingElements){
			String url = servletMappingElement.elementText("url-pattern");
			if(url.equals(path)){
				servletName = servletMappingElement.elementText("servlet-name");
				break;
			}
		}
		List<Element> servletElements = rootElement.elements("servlet");
		for(Element servletElement : servletElements){
			String ele = servletElement.elementText("servlet-name");
			if(ele.equals(servletName)){
				System.out.println(servletElement.elementText("servlet-class"));
			}
		}
		sc.close();
	}
}