/*
请按以下要求编写代码
1.定义一个Cat类，要求
属性：名称、年龄、颜色、性别
方法：无参构造方法、全参构造方法，get/set方法

2.定义一个MainApp类，包含main()方法，先创建一个Cat对象，使用BeanUtils工具为这个对象的各个属性赋值

请按以下要求编写代码：
1.定义一个Phone类，要求：
属性：品牌、颜色、价格、屏幕尺寸
方法：无参构造方法、全参构造方法，get/set方法

2.定义一个MainApp类，包含main()方法，先创建一个Phone对象，使用构造方法或者set方法为各属性赋值
3.使用BeanUtils工具获取这个对象的各个属性的值。
 */
public class One3 {}
public class Cat{
	private String name;
	private int age;
	private String color;
	private String sex;
	public Cat() {
	}
	public Cat(String name, int age, String color, String sex) {
		this.name = name;
		this.age = age;
		this.color = color;
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Cat [name=" + name + ", age=" + age + ", color=" + color + ", sex=" + sex + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
public class mainApp{
	public static void main(String[] args) {
		Cat cat = new Cat();
		BeanUtils.setProperty(cat,name,"9527");
		BeanUtils.setProperty(cat,age,3);
		BeanUtils.setProperty(cat,color,"奶白色");
		BeanUtils.setProperty(cat,sex,"雌");
	}
}
public class Phone{
	private String brand;
	private String color;
	private double price;
	private double size;
	public Phone() {
	}
	public Phone(String brand, String color, double price, double size) {
		this.brand = brand;
		this.color = color;
		this.price = price;
		this.size = size;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Phone [brand=" + brand + ", color=" + color + ", price=" + price + ", size=" + size + "]";
	}
}
public class mainApp{
	public static void main(String[] args) {
		Phone p = new Phone();
		BeanUtils.setProperty(p,"brand","喵喵");
		BeanUtils.setProperty(p,"color","奶的色");
		BeanUtils.setProperty(p,"price",10.2);
		BeanUtils.setProperty(p,"size",6.0);
		String brand = BeanUtils.getProperty(p,"brand");
		String color = BeanUtils.getProperty(p,"color");
		String price = BeanUtils.getProperty(p,"price");
		String size = BeanUtils.getProperty(p,"size");
		System.out.println(brand + ", " + color + ", " + price + ", " + size);
	}
}

/*
请按以下要求编写代码：
1.定义一个User类，要求：
属性：登陆名、登陆密码、性别、学历、爱好(String数组)
方法：无参构造方法、全参构造方法，get/set方法。
2.定义一个MainApp类，包含main()方法：
1)先定义一个Map<String,String[]>类型的集合，将以下数据填充到集合中：
	键(所有名称要跟User类的属性名一致)		值
登录名									“zhangsan”
登陆密码									“888888”
性别										“男”
学历										“本科”
爱好										{"读书”,”唱歌”,”旅游”}

2)创建一个User对象；
3)使用BeanUtils工具类将Map中的值填充到User对象中
4)打印User对象的所有属性值。
 */
public class One5 {
}
public class User{
	private String userName;
	private String password;
	private String sex;
	private String education;
	private String[] hobbies;
	public User() {
	}
	public User(String userName, String password, String sex, String education, String[] hobbies) {
		this.userName = userName;
		this.password = password;
		this.sex = sex;
		this.education = education;
		this.hobbies = hobbies;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", sex=" + sex + ", education=" + education
		+ ", hobbies=" + Arrays.toString(hobbies) + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}
}
public class mainApp{
	public static void main(String[] args) {
		Map<String,String[]> map = HashMap<>();
		map.put("userName",new String[]{"zhangsan"});
		map.put("password",new String[]{"888888"});
		map.put("sex",new String[]{"男"});
		map.put("education",new String[]{"本科"});
		map.put("hobbies",new String[]{"读书","唱歌","旅游"});
		User user = new User();
		BeanUtils.populate(user,map);
		System.out.println(user);
	}
}

/*
一、请自定义工具类：MyBeanUtils，定义方法：
populate(Object obj,Map<String,String[])，
实现功能：可以根据Map中的键值对信息，为obj的各个属性赋值。此方法要被修饰为公有、静态的。
二、定义一个User类，要求：
属性：登陆名、登陆密码、性别、学历、爱好
方法：无参构造方法、全参构造方法，get/set方法。
三、定义一个Demo类，包含方法demo01()，方法内创建一个Map<String,String[]>对象，并填充一个用户信息；
创建一个User对象，调用MyBeanUtils类的populate()方法为User对象的成员赋值。
 */
public class One6 {
	public static void main(String[] args) {
		new Demo().demo01();
	}
}
public class MyBeanUtils{
	public static void populate(Object obj, Map<String, String[]> map) throws Exception {
		Class<?> clazz = obj.getClass();
		Field[] flds = clazz.getDeclaredFields();
		for (Field fld : flds) {
			String fieldName = fld.getName();
			Class<?> fieldModifier = fld.getType();
			String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method mtd = clazz.getMethod(methodName, fieldModifier);
			for (String key : map.keySet()) {
				if (key.equals(fieldName)) {
					if(fieldModifier.isArray()){
						//Array.newInstance(Class,int)
						String[] args =  (String[]) Array.newInstance(map.get(key).getClass().getComponentType(),map.get(key).length);
						System.arraycopy(map.get(key), 0, args, 0, args.length);
						mtd.invoke(obj, (Object)args);
					}
					else{
						mtd.invoke(obj, map.get(key));
					}
				}
			}//内层for
		}//外层for
	}//populate
}
public class User{
	private String userName;
	private String password;
	private String sex;
	private String education;
	private String[] hobbies;
}
public class Demo{
	public void demo01(){
		Map<String,String[]> map = new HashMap<>();
		User user = new User();
		map.put("userName",new String[]{"小黑"});
		map.put("password",new String[]{"888888"});
		map.put("sex",new String[]{"女"});
		map.put("education",new String[]{"本科"});
		map.put("hobbies",new String[]{"看书","听歌","旅游"});
		MyBeanUtils.populate(user,map);
		System.out.println(user);
	}
}

/*
 * 一、请自定义工具类：MyBeanUtils，定义方法：
populate(Class c,Map<String,String[])，
实现功能：可以根据Map中的键值对信息，为c所代表的对象的各个属性赋值。此方法要被修饰为公有、静态的。
二、定义一个Book类，要求：
	属性：书名、作者、价格、出版日期
	方法：无参构造方法、全参构造方法，get/set方法。
三、定义一个Demo类，包含方法demo01()，方法内创建一个Map<String,String[]>对象，并填充一个图书信息；
调用MyBeanUtils类的populate()方法为Book对象的成员赋值。
 */
public class One7 {
//同上
}

/*
 一、有以下XML文件：
<beans>
	<bean className = "cn.itcast.domain.Car">
		<property name = "brand" value = "宝马"/>
		<property name = "color" value = "红色"/>
		<property name = "price" value = "280000"/>
		<property name = "type" value = "CX200"/>
		<property name = "productDate" value = "2016-10-30"/>
	</bean>
	<bean className = "cn.itcast.domain.Book">
		<property name = "title" value = "Oracle管理员手册"/>
		<property name = "publishDate" value = "2017-01-28"/>
		<property name = "price" value = "35/">
		<property name = "publishingHouse" value = "传智播客出版社"/>
		<property name = "author" value = "传智人"/>
	</bean>
</beans>
二、请按XML文件中的属性定义两个类。
并使用dom4j解析这个xml文件，先获取每个<bean>元素的className属性，通过反射创建对象。
获取每个bean数据，使用BeanUtils工具分别填充两个Bean对象。

1.创建项目day18作业_Test1_8
2.创建XML文件；
3.创建两个Bean类；
4.创建MainApp类，包含main()方法，分别创建两个对象，
使用dom4j解析xml文件，获取数据并使用BeanUtils工具填充两个对象。
 */
public class One8 {
	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read("bean.xml");
		Element beansElement = document.getRootElement();
		List<Element> beanElements = beansElement.elements();
		for(Element beanElement : beanElements){
			String className = beanElement.attributeValue("className");
			Class clazz = Class.forName(className);
			Object obj = clazz.newInstance();
			List<Element> propertyElements = beanElement.elements();
			for(Element propertyElement : propertyElements){
				String fieldName = propertyElement.attributeValue("name");
				String fieldValue = propertyElement.attributeValue("value");
				BeanUtils.setProperty(obj,fieldName,fieldValue);
			}
			System.out.println(obj);
		}
	}
}
public class Car{
	private String brand;
	private String color;
	private String price;
	private String type;
	private String productDate;
	...
}
public class Book{
	private String title;
	private String publishDate;
	private String price;
	private String publishingHouse;
	private String author;
	...
}

/*
一、有以下XML文件：
student.xml
<?xml version = 1.0 encoding = "UTF-8"?>
<students>
	<student stuNo = "it001">
		<name>章子怡</name>
		<age>20</age>
		<sex>女</sex>
		<score>99</score>
	</student>
	<student stuNo = "it002">
		<name>汪峰</name>
		<age>22</age>
		<sex>男</sex>
		<score>100</score>
	</student>
	<student stuNo = "it003">
		<name>高圆圆</name>
		<age>18</age>
		<sex>女</sex>
		<score>98</score>
	</student>
</students>
二、请按以下要求编写代码：
1.按XML文档描述创建Student类；
2.创建类MainApp，并包含main()方法，
使用dom4j检索出所有学员信息，每个学员信息封装一个Student对象，将所有Student对象封装到一个ArrayList中；
3.遍历ArrayList，打印所有学员信息。
 */
public class Two1 {
}
public class Student{
	private String stuNo;
	private String name;
	private String age;
	private String sex;
	private String score;
	...
}
public class MainApp{
	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read("student.xml");
		Element studentsElement = document.getRootElement();
		List<Element> studentElements = studentsElement.elements();
		for(Element studentElement : studentElements){
			Class clazz = Two1Student.class;
			Object obj = clazz.newInstance();
			String stuNoValue = studentElement.attributeValue("stuNo");
			BeanUtils.setProperty(obj,"stuNo",stuNoValue);
			List<Element> studentChilds = studentElement.elements();
			for(Element studentChild : studentChilds){
				String name = studentChild.getName();
				String value = studentChild.getText();
				BeanUtils.setProperty(obj,name,value);
			}
			System.out.println(obj);
		}
	}
}

/*
一、有以下XML文件：
product.xml
<?xml version = "1.0" encoding = "UTF-8"?>
<products>
	<category name = "电视机">
		<product id = "p001">
			<brand>SONY</brand>
			<type>XN65</type>
			<price>12000</price>
			<size>65寸</size>
		</product>
		<product id = "p002">
			<brand>LG</brand>
			<type>LY2201</type>
			<price>4320</price>
			<size>45寸</size>
		</product>
	</category>

	<category name = "电冰箱">
		<product id = "p003">
			<brand>海尔</brand>
			<type>H330</type>
			<price>3800</price>
			<size> 173L</size>
		</product>
		<product id = "p004">
			<brand>西门子</brand>
			<type>X220</type>
			<price>4999</price>
			<size>188L</size>
		</product>
	</category>
</products>
二、请按以下要求编写代码：
1.按XML文档描述创建Product类(注意:包含"分类"属性)；
2.使用dom4j检索出所有"电视机"类别的所有商品信息,每个信息封装一个Product对象,将所有Product对象封装到一个ArrayList中
3.使用dom4j检索出所有"电冰箱"类别的所有商品信息,每个信息封装一个Product对象,将所有Product对象封装到另一个ArrayList中
4.遍历两个ArrayList,打印所有商品信息
 */
public class Two2 {
	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read("product.xml");
		Element productsElement = document.getRootElement();
		List<Element> categoryElements = productsElement.elements();
		ArrayList<ArrayList<Product>> list = new ArrayList<>();
		//遍历category元素
		for(Element categoryElement : categoryElements){
			ArrayList<Product> list1 = new ArrayList<Product>();
			List<Element> productElements = categoryElement.elements();
			String nameValue = categoryElement.attributeValue("name");
			//遍历product元素
			for(Element productElement : productElements){
				Product pro = new Product();
				BeanUtils.setProperty(pro,"name",nameValue);
				String idValue = productElement.attributeValue("id");
				BeanUtils.setProperty(pro,"id",idValue);
				List<Element> productChilds = productElement.elements();
				//遍历product子元素
				for(Element productChild : productChilds){
					String name = productChild.getName();
					String value = productChild.getText();
					BeanUtils.setProperty(pro,name,value);
				}
				list1.add(pro);
			}
			list.add(list1);
		}
		printList(list);
	}
	public static void printList(List<ArrayList<Product>> list){
		for(ArrayList<Product> l : list){
			for(Product t : l){
				System.out.println(t);
			}
		}
	}
}
public class Product{
	private String name;
	private String id;
	private String brand;
	private String type;
	private String price;
	private String size;
	...
}

/*
一、有以下XML文件：
phones.xml
<?xml version = "1.0" encoding = "UTF-8"?>
<phones>
	<product>
		<name>荣耀37</name>
		<brand>华为</brand>
		<size>15.5</size>
		<price>4388</price>
		<color>白色</color>
	</product>
	<product>
		<name>IPhone7Plus</name>
		<brand>苹果</brand>
		<size>14</size>
		<price>7200</price>
		<color>黑色</color>
	</product>
</phones>
二、请按以下要求编写代码：
1.按XML文档描述创建phone类
2.为用户列出菜单:
【查询手机】
1.黑色   2.白色   3.银色   4.金色    5.退出
3.根据用户输入,在XML文件中检索出相应颜色的手机,并使用Phone对象封装,多个Phone对象封装到一个ArrayList中
4.遍历ArrayList,为用户显示查询结果
 */
public class Three1 {
	public static void main(String[] args) throws Exception {
		menu: while (true) {
			System.out.println("=============菜单面板=============");
			System.out.println("查询手机:");
			System.out.println("1.黑色");
			System.out.println("2.白色");
			System.out.println("3.银色");
			System.out.println("4.金色");
			System.out.println("5.退出");
			System.out.println("请输入要查询的颜色编号");
			Scanner sc = new Scanner(System.in);
			String choose = sc.nextLine();
			switch (choose) {
				case "1":
				checkBlack();
				break;
				case "2":
				checkWhite();
				break;
				case "3":
				checkSilvery();
				break;
				case "4":
				checkGlod();
				break;
				case "5":
				System.out.println("退出成功,欢迎下次使用!");
				break menu;
				default:
				break;
			}
		}
	}
	//查询黑色的手机
	public static void checkBlack() throws Exception {
		ArrayList<Product> list = getProduct("黑色");
		printProduct(list);
	}
	//查询白色的手机
	public static void checkWhite() throws Exception {
		ArrayList<Product> list = getProduct("白色");
		printProduct(list);
	}
	//查询银色的手机
	public static void checkSilvery() throws Exception {
		ArrayList<Product> list = getProduct("银色");
		printProduct(list);
	}
	//查询金色的手机
	public static void checkGlod() throws Exception {
		ArrayList<Product> list = getProduct("金色");
		printProduct(list);
	}

	// 遍历xml,返回满足条件的product对象的集合
	public static ArrayList<Product> getProduct(String c) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read("phones.xml");
		Element phonesElement = document.getRootElement();
		List<Element> productElements = phonesElement.elements();
		ArrayList<Product> list = new ArrayList<>();
		boolean isExist = false;
		// 遍历product元素
		for (Element productElement : productElements) {
			String color = productElement.elementText("color");
			// 判断是否存在这种颜色
			if (color.equals(c)) {
				isExist = true;
				Product product = new Product();
				List<Element> productChilds = productElement.elements();
				// product对象初始化
				for (Element productChild : productChilds) {
					String name = productChild.getName();
					String value = productChild.getText();
					BeanUtils.setProperty(product, name, value);
				}
				list.add(product);
			}
		}
		//如果查询的手机存在就返回product对象的list集合
		if (isExist)
			return list;
		//如果查询的手机不存在就返回null
		else
			return null;
	}// isNeedColor

	// 打印product对象的集合
	public static void printProduct(ArrayList<Product> list) {
		//如过list为null,说明用户查询的手机不存在,就返回给用户提示信息:
		查询的手机不存在
		if(list == null){
			System.out.println("您所查询的手机不存在");
		}else{
			for (Product p : list) {
				System.out.println(p);
			}
		}
	}// printProduct
}
public class Phone{
	private String name;
	private String brand;
	private String size;
	private String price;
	private String color;
	...
}

/*
一、有以下XML文件：
students.xml
<?xml version = "1.0" encoding = "UTF-8"?>
<students>
	<course name = "平面设计">
		<student id = "it001">
			<name>章子怡</name>
			<age>20</age>
			<sex>女</sex>
			<score>99</score>
		</student>
		<student id = "it002">
			<name>杨颖</name>
			<age>21</age>
			<sex>女</sex>
			<score>100</score>
		</student>
	</course>

	<course name = "JavaEE">
		<student id = "it003">
			<name>汪峰</name>
			<age>22</age>
			<sex>男</sex>
			<score>89</score>
		</student>
		<student id = "it004">
			<name>撒贝宁</name>
			<age>23</age>
			<sex>男</sex>
			<score>90</score>
		</student>
	</course>
</students>
二、请按以下要求编写代码：
1.按XML文档描述创建Student类(注意：包含id字段、科目字段)；
2.为用户列出菜单：
【查询学员】
1.按学科   2.按性别   3.按年龄   4.按分数    5.退出
3.用户选择：
1.按学科：检索出XML中所有的”学科”名称，并继续列出菜单：
		 例如：1.平面设计   2.JavaEE
2.按性别：列出性别选择二级菜单：
		 例如：1.男	  2.女
3.按年龄：请用户输入年龄范围，格式：小值-大值。
		 例如：20-25
4.按分数：请用户输入分数范围，格式：小值-大值。
		 例如：80-100
4.根据用户输入，在XML文件中检索出相应的学员信息，创建Student对象，所有属性使用BeanUtils封装，多个Student对象封装到一个ArrayList中；
5.遍历ArrayList，为用户显示查询结果；
 */
public class Three2 {
	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read("students.xml");
		Element studentsElement = document.getRootElement();
		menu: while (true) {
			System.out.println("=============菜单面板=============");
			System.out.println("请按照如下菜单输入要查询的学生:");
			System.out.println("1.按学科");
			System.out.println("2.按性别");
			System.out.println("3.按年龄");
			System.out.println("4.按分数");
			System.out.println("5.退出");
			System.out.println("请输入要查询的类别");
			Scanner sc = new Scanner(System.in);
			String choose = sc.nextLine();
			switch (choose) {
			case "1":
				subjectQuery();
				break;
			case "2":
				genderQuery();
				break;
			case "3":
				ageQuery();
				break;
			case "4":
				scoreQuery();
				break;
			case "5":
				System.out.println("退出成功,欢迎下次使用!");
				break menu;
			default:
				break;
			}
		}
	}// main

	// 按照学科进行检索
	public static void subjectQuery() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read("students.xml");
		Element studentsElement = document.getRootElement();
		List<Element> courseElements = studentsElement.elements();
		ArrayList<String> list = new ArrayList<>();
		// 把course元素的属性,即科目存储在list集合中
		for (Element courseElement : courseElements) {
			String courseName = courseElement.attributeValue("name");
			list.add(courseName);
		}
		System.out.println("请输入要查询的类别");
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + "." + list.get(i));
		}
		Scanner sc = new Scanner(System.in);
		String no = sc.nextLine();
		// 根据集合的索引确定选择的是哪个科目
		int index = Integer.parseInt(no) - 1;
		String selectedCourseName = list.get(index);
		// 遍历course元素
		for (Element courseElement : courseElements) {
			// 根据选择的科目与course元素的属性字符串比较,找到要遍历的course元素
			String courseName = courseElement.attributeValue("name");
			// 循环到满足条件的学科,继续往下查询,查询完毕后跳出该层循环,结束该方法
			if (selectedCourseName.equals(courseName)) {
				System.out.println("请输入你要查询的类别");
				System.out.println("1.按id");
				System.out.println("2.按姓名");
				System.out.println("3.按年龄");
				System.out.println("4.按性别");
				System.out.println("5.按分数");
				String thirdChoose = sc.nextLine();
				switch (thirdChoose) {
				case "1": {
					boolean isExist = false;
					System.out.println("请输入id");
					String id = sc.nextLine();
					List<Element> studentElements = courseElement.elements();
					// 遍历student元素
					for (Element studentElement : studentElements) {
						String idAttr = studentElement.attributeValue("id");
						if (idAttr.equals(id)) {
							isExist = true;
							Three2Student stu = new Three2Student();
							BeanUtils.setProperty(stu, "course", courseElement.attributeValue("name"));
							BeanUtils.setProperty(stu, "id", studentElement.attributeValue("id"));
							List<Element> studentChilds = studentElement.elements();
							// 遍历student元素的子元素
							for (Element studentChild : studentChilds) {
								String name = studentChild.getName();
								String value = studentChild.getText();
								BeanUtils.setProperty(stu, name, value);
							}
							System.out.println(stu);
						}
					}
					if (!isExist)
						System.out.println("你要查询的学生不存在!");
				}
					break;
				case "2": {
					boolean isExist = false;
					System.out.println("请输入姓名");
					String inputName = sc.nextLine();
					List<Element> studentElements = courseElement.elements();
					// 遍历student元素
					for (Element studentElement : studentElements) {
						String nameText = studentElement.elementText("name");
						if (inputName.equals(nameText)) {
							isExist = true;
							Three2Student stu = new Three2Student();
							BeanUtils.setProperty(stu, "course", courseElement.attributeValue("name"));
							BeanUtils.setProperty(stu, "id", studentElement.attributeValue("id"));
							List<Element> studentChilds = studentElement.elements();
							// 遍历student元素的子元素
							for (Element studentChild : studentChilds) {
								String name = studentChild.getName();
								String value = studentChild.getText();
								BeanUtils.setProperty(stu, name, value);
							}
							System.out.println(stu);
						}
					}
					if (!isExist)
						System.out.println("你要查询的学生不存在!");
				}
					break;
				case "3": {
					boolean isExist = false;
					System.out.println("请输入年龄范围");
					String ageRange = sc.nextLine();
					String[] age = ageRange.split("-");
					int minAge = Integer.parseInt(age[0]);
					int maxAge = Integer.parseInt(age[1]);
					List<Element> studentElements = courseElement.elements();
					// 遍历student元素
					for (Element studentElement : studentElements) {
						String ageText = studentElement.elementText("age");
						int ageInt = Integer.parseInt(ageText);
						if (ageInt >= minAge && ageInt <= maxAge) {
							isExist = true;
							Three2Student stu = new Three2Student();
							BeanUtils.setProperty(stu, "course", courseElement.attributeValue("name"));
							BeanUtils.setProperty(stu, "id", studentElement.attributeValue("id"));
							List<Element> studentChilds = studentElement.elements();
							for (Element studentChild : studentChilds) {
								String name = studentChild.getName();
								String value = studentChild.getText();
								BeanUtils.setProperty(stu, name, value);
							}
							System.out.println(stu);
						}
					}
					if (!isExist)
						System.out.println("你要查询的学生不存在!");
				}
					break;
				case "4": {
					boolean isExist = false;
					System.out.println("1.男");
					System.out.println("2.女");
					System.out.println("请输入你要查询的类别");
					String chooseSex = sc.nextLine();
					// 如果用户输入1,即打印所有的男性学生
					if (chooseSex.equals("1")) {
						List<Element> studentElements = courseElement.elements();
						// 遍历student元素
						for (Element studentElement : studentElements) {
							String sexText = studentElement.elementText("sex");
							if ("男".equals(sexText)) {
								isExist = true;
								Three2Student stu = new Three2Student();
								BeanUtils.setProperty(stu, "course", courseElement.attributeValue("name"));
								BeanUtils.setProperty(stu, "id", studentElement.attributeValue("id"));
								List<Element> studentChilds = studentElement.elements();
								for (Element studentChild : studentChilds) {
									String name = studentChild.getName();
									String value = studentChild.getText();
									BeanUtils.setProperty(stu, name, value);
								}
								System.out.println(stu);
							}
						}
					} else {
						List<Element> studentElements = courseElement.elements();
						// 遍历student元素
						for (Element studentElement : studentElements) {
							String sexText = studentElement.elementText("sex");
							if ("女".equals(sexText)) {
								isExist = true;
								Three2Student stu = new Three2Student();
								BeanUtils.setProperty(stu, "course", courseElement.attributeValue("name"));
								BeanUtils.setProperty(stu, "id", studentElement.attributeValue("id"));
								List<Element> studentChilds = studentElement.elements();
								for (Element studentChild : studentChilds) {
									String name = studentChild.getName();
									String value = studentChild.getText();
									BeanUtils.setProperty(stu, name, value);
								}
								System.out.println(stu);
							}
						}
					}
					if (!isExist)
						System.out.println("你要查询的学生不存在!");
				}
					break;
				case "5": {
					boolean isExist = false;
					System.out.println("请用户输入分数范围");
					String scoreRange = sc.nextLine();
					String[] score = scoreRange.split("-");
					int minScore = Integer.parseInt(score[0]);
					int maxScore = Integer.parseInt(score[1]);
					List<Element> studentElements = courseElement.elements();
					// 遍历student元素
					for (Element studentElement : studentElements) {
						String scoreText = studentElement.elementText("score");
						int scoreTextInt = Integer.parseInt(scoreText);
						if (scoreTextInt >= minScore && scoreTextInt <= maxScore) {
							isExist = true;
							Three2Student stu = new Three2Student();
							BeanUtils.setProperty(stu, "course", courseElement.attributeValue("name"));
							BeanUtils.setProperty(stu, "id", studentElement.attributeValue("id"));
							List<Element> studentChilds = studentElement.elements();
							// 遍历student元素的子元素
							for (Element studentChild : studentChilds) {
								String name = studentChild.getName();
								String value = studentChild.getText();
								BeanUtils.setProperty(stu, name, value);
							}
							System.out.println(stu);
						}
					}
					if (!isExist)
						System.out.println("你要查询的学生不存在!");
				}
					break;
				default:
					break;
				}
				break;
			}
		}
	}// subjectQuery

	// 按照性别查询
	public static void genderQuery() throws Exception {
		System.out.println("请输入你要查询的类别");
		System.out.println("1.男");
		System.out.println("2.女");
		Scanner sc = new Scanner(System.in);
		String chooseSex = sc.nextLine();
		// 如果用户输入1,即打印所有的男性学生
		if (chooseSex.equals("1")) {
			SAXReader reader = new SAXReader();
			Document document = reader.read("students.xml");
			Element studentsElement = document.getRootElement();
			List<Element> courseElements = studentsElement.elements();
			// 遍历course元素
			for (Element courseElement : courseElements) {
				List<Element> studentElements = courseElement.elements();
				// 遍历student元素
				for (Element studentElement : studentElements) {
					if (studentElement.elementText("sex").equals("男")) {
						Three2Student stu = new Three2Student();
						BeanUtils.setProperty(stu, "course", courseElement.attributeValue("name"));
						BeanUtils.setProperty(stu, "id", studentElement.attributeValue("id"));
						List<Element> studentChilds = studentElement.elements();
						// 遍历student子元素
						for (Element studentChild : studentChilds) {
							String name = studentChild.getName();
							String value = studentChild.getText();
							BeanUtils.setProperty(stu, name, value);
						}
						System.out.println(stu);
					}
				}
			}
		} else {
			// 用户选择2则打印所有的女性学生
			SAXReader reader = new SAXReader();
			Document document = reader.read("students.xml");
			Element studentsElement = document.getRootElement();
			List<Element> courseElements = studentsElement.elements();
			for (Element courseElement : courseElements) {
				List<Element> studentElements = courseElement.elements();
				for (Element studentElement : studentElements) {
					if (studentElement.elementText("sex").equals("女")) {
						Three2Student stu = new Three2Student();
						BeanUtils.setProperty(stu, "course", courseElement.attributeValue("name"));
						BeanUtils.setProperty(stu, "id", studentElement.attributeValue("id"));
						List<Element> studentChilds = studentElement.elements();
						for (Element studentChild : studentChilds) {
							String name = studentChild.getName();
							String value = studentChild.getText();
							BeanUtils.setProperty(stu, name, value);
						}
						System.out.println(stu);
					}
				}
			}
		}
	}

	public static void ageQuery() throws Exception {
		// isExist用来判断用户键入的数据对应的学生是否存在
		boolean isExist = false;
		System.out.println("请用户输入年龄范围");
		Scanner sc = new Scanner(System.in);
		String ageRange = sc.nextLine();
		String[] age = ageRange.split("-");
		int minAge = Integer.parseInt(age[0]);
		int maxAge = Integer.parseInt(age[1]);
		SAXReader reader = new SAXReader();
		Document document = reader.read("students.xml");
		Element studentsElement = document.getRootElement();
		List<Element> courseElements = studentsElement.elements();
		// 遍历course元素
		for (Element courseElement : courseElements) {
			List<Element> studentElements = courseElement.elements();
			// 遍历student元素
			for (Element studentElement : studentElements) {
				String ageText = studentElement.elementText("age");
				int ageInt = Integer.parseInt(ageText);
				if (ageInt >= minAge && ageInt <= maxAge) {
					isExist = true;
					Three2Student stu = new Three2Student();
					BeanUtils.setProperty(stu, "course", courseElement.attributeValue("name"));
					BeanUtils.setProperty(stu, "id", studentElement.attributeValue("id"));
					List<Element> studentChilds = studentElement.elements();
					for (Element studentChild : studentChilds) {
						String name = studentChild.getName();
						String value = studentChild.getText();
						BeanUtils.setProperty(stu, name, value);
					}
					System.out.println(stu);
				}
			}
		}
		if (!isExist)
			System.out.println("你要查询的学生不存在!");
	}

	// 按照分数查询
	public static void scoreQuery() throws Exception {
		boolean isExist = false;
		System.out.println("请用户输入分数范围");
		Scanner sc = new Scanner(System.in);
		String scoreRange = sc.nextLine();
		String[] score = scoreRange.split("-");
		int minScore = Integer.parseInt(score[0]);
		int maxScore = Integer.parseInt(score[1]);
		SAXReader reader = new SAXReader();
		Document document = reader.read("students.xml");
		Element studentsElement = document.getRootElement();
		List<Element> courseElements = studentsElement.elements();
		// 遍历course元素
		for (Element courseElement : courseElements) {
			List<Element> studentElements = courseElement.elements();
			// 遍历student元素
			for (Element studentElement : studentElements) {
				String scoreText = studentElement.elementText("score");
				int scoreTextInt = Integer.parseInt(scoreText);
				if (scoreTextInt >= minScore && scoreTextInt <= maxScore) {
					isExist = true;
					Three2Student stu = new Three2Student();
					BeanUtils.setProperty(stu, "course", courseElement.attributeValue("name"));
					BeanUtils.setProperty(stu, "id", studentElement.attributeValue("id"));
					List<Element> studentChilds = studentElement.elements();
					// 遍历student元素的子元素
					for (Element studentChild : studentChilds) {
						String name = studentChild.getName();
						String value = studentChild.getText();
						BeanUtils.setProperty(stu, name, value);
					}
					System.out.println(stu);
				}
			}
		}
		// 如果查询的学生不存在则提示用户查询的学生不存在
		if (!isExist)
			System.out.println("查询的学生不存在!");
	}
}
public class Three2Student{
	private String course;
	private String id;
	private String name;
	private int age;
	private String sex;
	private String score;
	...
}