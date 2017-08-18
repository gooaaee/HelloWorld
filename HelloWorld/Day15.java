/*
编写一个类，增加一个实例方法用于打印一条字符串。
并使用反射手段创建该类的对象， 并调用该对象中的方法
 */
public class One8 {
	public static void main(String[] args)
	throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
	SecurityException, IllegalArgumentException, InvocationTargetException {
		Class clazz = Class.forName("One8A");
		Object obj = clazz.newInstance();
		Method mtd = clazz.getMethod("eat");
		mtd.invoke(obj);
	}
}
class One8A{
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void eat(){
		System.out.println("helloworld");
	}
}

/*
定义一个标准的Person类，包含属性name，包含setget方法,包含空参构造。
使用反射的方式创建一个实例、并初始化，使用反射方式调用setName方法对名称进行设置,
设置之后 在通过反射方式执行getName()方法
 */
public class Two2 {
	public static void main(String[] args) throws Exception{
		Class clazz = Class.forName("Person");
		Object obj = clazz.newInstance();
		Method mtd = clazz.getMethod("setName",String.class);
		mtd.invoke(obj,"小王八");
		Method mtd2 = clazz.getMethod("getName");
		String name = (String)mtd2.invoke(obj);
		System.out.println(name);
	}
}
class Person{
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

/*
ArrayList<Integer> list = new ArrayList<Integer>();
这个泛型为Integer的ArrayList中存放一个String类型的对象
创建集合对象
 2：添加数字可以 但是添加字符串就报错 这时候想到反射
 3：通过对象获得字节码文件对象
 4：通过字节码文件对象得到add方法反射形式
 5: 反射方式 执行add 添加字符串
 */
 public class Three1 {
 	public static void main(String[] args) {
 		ArrayList<Integer> list = new ArrayList<>();
 		Class clazz = list.getClass();
 		Method mtd = clazz.getMethod("add",Object.class);
 		mtd.invoke(list,"hello");
 		System.out.println(list);
 	}
 }

/*
已知一个类，定义如下：
package com.itcast.demo;
public class DemoClass {
	public void run() {
		System.out.println("welcome to heima!");
	}
}
(1)写一个Properties格式的配置文件，配置类的完整名称。
(2) 写一个程序，读取这个Properties配置文件，获得类的完整名称并加载这个类，
(3)用反射 的方式运行run方法。

按要求定义一个类
2：写一个配置文件 键值对形势 className = 值为类的路径
3：创建一个MainApp类 定义main方法
4：在main方法中创建一个Properties集合类
5: 调用读取功能将文件中的路径读取出来 获取字节码对象
6: 反射获取对象
7: 反射获取run方法 并执行

个人题目修改为
className=classPath
fieldName1=fieldValue1
fieldName2=fieldValue2
fieldName3=fieldValue3
field为私有权限
Person.properties配置文件中内容:
com.itheima.homework.Person
name=heihei
age=22
 */
public class Three2 {
	public static void main(String[] args) throws Exception {
		Properties pro = new Properties();
		pro.load(new FileInputStream("Person.properties"));
		Set<String> fieldNames = pro.stringPropertyNames();
		Class clazz = null;
		Object obj = null;
		String className = null;
		for (String key : fieldNames) {
			if (pro.getProperty(key).equals("")) {
				className = key;
				clazz = Class.forName(className);
				obj = clazz.newInstance();
				break;
			}
		}
		for (String key : fieldNames) {
			if (key.equals(className)) {
				continue;
			} else {
				String fieldName = key;
				String fieldValue = pro.getProperty(key);
				String mtdName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method mtd = clazz.getMethod(mtdName, String.class);
				mtd.invoke(obj, fieldValue);
			}
		}
		for (String key : fieldNames) {
			if (key.equals(className)) {
				continue;
			} else {
				String fieldName = key;
				String fieldValue = pro.getProperty(key);
				String mtdName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				Method mtd = clazz.getMethod(mtdName);
				Object value = mtd.invoke(obj);
				System.out.println(fieldName + ":" + value);
			}
		}
	}
}//Three2
class Person {
	private String name;
	private String age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
}