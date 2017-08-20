/*
请定义类ArrayTools，并定义以下方法：
1.方法printArray，此方法可以接收一个int[]数组，并打印这个数组中的所有元素（多个元素之间用逗号隔开）。
2.方法：check，在方法内打印：”之前执行......”
3.方法：log，在方法内打印：”之后执行......”
4.方法test()，并使用JUnit测试printArray方法。要求在执行前先执行check()方法，在执行后执行log()方法。
 */
public class One4 {}
public class One4ArrayTools{
	public void printArray(int[] arr){
		System.out.println(Arrays.toString(arr));
	}
	@Before
	public void check(){
		System.out.println("之前执行......");
	}
	@After
	public void log(){
		System.out.println("之后执行......");
	}
	@Test
	public void test(){
		int[] arr = {1,2,3,4,5};
		printArray(arr);
	}
}

/*
创建新项目，按以下要求定义，并使用注解：
1.请定义一个最简单的注解@MyAnno1(参考讲义2.3)：
1)不需要任何属性。
2)此注解只能修饰“类”和接口(参考讲义2.6)。
3)此注解要出现在源码和字节码中(参考讲义2.6)
4)定义测试类：Test1，并使用此注解修饰(参考讲义2.4)
2.请定义注解@MyAnno2：
1)包含一个String类型的属性“type”，并且定义默认值“java”。
2)此注解只能修饰“字段”。
3)此注解只需要能够在源码中使用。
4)定义测试类：Test2，随意定义一个成员属性，并使用此注解；
3.请定义注解@MyAnno3：
1)包含一个String类型的属性“type”，不定义默认值。
2)包含一个int[]数组类型的属性“intArr”，不定义默认值。
3)此注解只能修饰“方法”。
4)此注解要出现在源码和字节码中。
5)定义测试类：Test3，随意定义一个成员方法，并使用此注解；
 */
public class One7 {}
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface One7MyAnno1{
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface One7MyAnno2{
	String type() default "java";
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface One7MyAnno3{
	String type();
}

/*
自定义注解@MyTest，模拟JUnit的@Test注解，可以执行一个无参，无返回值的方法
 */
public class One8 {
	@MyTest(name="hello")
	public void show(){
		System.out.println("这是show方法...");
	}
	@MyTest(name="world")
	public void run(){
		System.out.println("这是run方法");
	}
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest{
	String name();
}
public class One8Test{
	public static void main(String[] args) throws Exception{
		Class clazz = Class.forName("One8");
		Object obj = clazz.newInstance();
		Method[] mtds = clazz.getMethods();
		for (Method mtd : mtds) {
			boolean flag = mtd.isAnnotationPresent(MyTest.class);
			if (flag) {
				mtd.invoke(obj);
				MyTest anno = mtd.getAnnotation(MyTest.class);
				System.out.println(mtd.getName()+"的注解值为:"+anno.name());
			}
		}
	}
}

/*
按以下要求编写代码：
1.定义一个接口：Person，包含以下抽象方法：work()
1)定义一个类Student，实现Person接口，实现work()方法，打印输出：”我做Java项目”；
2.定义一个MyHandler，实现InvocationHandler接口，有如下要求：
1)定义成员属性--被代理对象：
2)定义构造方法，为被代理对象赋值；
3)定义一个方法before()，打印输出：”项目设计”；
4)定义一个方法after()，打印输出：”项目总结”；
5)重写invoke()方法，要求在调用方法前执行before()方法，在调用方法后执行after()方法。
定义一个测试类：Test，包含main()方法。要求用动态代理获取Student类的代理对象，并执行coding()方法。
 */
public class One9 {
}
public interface One9Person{
	void work();
}
public class One9Student implements One9Person{
	public void work(){
		System.out.println("我做Java项目");
	}
}
public class MyHandler implements InvocationHandler{
	public One9Student stu;
	public MyHandler(One9Student stu){
		this.stu = stu;
	}
	public Object invoke(Object proxy, Method mtd, Object[] args){
		before();
		mtd.invoke(stu);
		after();
	}
	public void before(){
		System.out.println("项目设计");
	}
	public void after(){
		System.out.println("项目总结");
	}
}
public class Oner9Test{
	public static void main(String[] args) {
		One9Student stu = new One9Student();
		InvocationHandler handler = new MyHandler(stu);
		One9Person stu2 = (One9Person)Proxy.newProxyInstance(One9Student.class.getClassLoader(),stu.getClass().getInterfaces(),handler);
		stu2.work();
	}
}

/*
自定义三个注解：
@MyTest，@MyBefore，@MyAfter，
分别模拟JUnit中的@Test，@Before，@After三个注解的作用，并测试使用。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest{
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBefore{
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAfter{
}
public class Two1 {
	@MyTest
	public void show(){
		System.out.println("我是show");
	}
	@MyBefore
	public void showBefore(){
		System.out.println("我是showBefore");
	}
	@MyAfter
	public void showAfter(){
		System.out.println("我是showAfter");
	}
}
public class Test{
	@Test
	public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
	ClassNotFoundException, InstantiationException {
		Class clazz = Class.forName("com.itheima.homework.Two1");
		Object obj = clazz.newInstance();
		Method[] mtds = clazz.getMethods();
		for (Method mtd : mtds) {
			boolean flag = mtd.isAnnotationPresent(MyBefore.class);
			if (flag) {
				mtd.invoke(obj);
			}
		}
		for (Method mtd : mtds) {
			boolean flag = mtd.isAnnotationPresent(MyTest.class);
			if (flag) {
				mtd.invoke(obj);
			}
		}
		for (Method mtd : mtds) {
			boolean flag = mtd.isAnnotationPresent(MyAfter.class);
			if (flag) {
				mtd.invoke(obj);
			}
		}
	}
}

/*
需求说明：我们来做一个“图书信息管理系统”，有以下要求：
1.图书要求记录以下信息：
	图书编号,作者,出版社,出版日期,单价,数量
2.图书信息记录到文件：Test3_1.txt中，每个图书信息存储一行，各属性值之间用逗号隔开。
3.程序要求提供以下功能(不需要列菜单)：
添加图书，修改图书，删除图书，查询所有图书
4.各功能具体要求：
1)添加图书：要求验证图书编号不能重复。将新图书信息追加写入到文件：Test3_1.txt中；
2)修改图书：根据图书编号查找图书，允许修改图书的除图书编号外的所有信息。修改完成后，将修改后的数据再次写入到Test3_1.txt中。
3)删除图书：根据图书编号查找图书，找到后先提示是否删除，如果用户选择”是”，执行删除。同时文件中的数据也要被删除；
4)查询所有图书：根据图书编号查询商品；
5.代码要求：
1)定义类BookDAO，此类中包含了上述所有的方法。
2)为测试每个功能模块，不从main()方法启动，使用我们自定义的@MyTest注解分别测试每个方法。
 */
public class Three1 {

}
public class BookDAO {
	// 添加功能
	@Test
	public void bookAdd() throws IOException {
		ArrayList<String> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要添加的图书名");
		String book = sc.nextLine();
		list.add(book);
		System.out.println("请输入你要添加的作者");
		book = sc.nextLine();
		list.add(book);
		System.out.println("请输入你要添加的出版社");
		book = sc.nextLine();
		list.add(book);
		System.out.println("请输入你要添加的出版日期");
		book = sc.nextLine();
		list.add(book);
		System.out.println("请输入你要添加的单价");
		book = sc.nextLine();
		list.add(book);
		System.out.println("请输入你要添加的数量");
		book = sc.nextLine();
		list.add(book);

		BufferedWriter bw = new BufferedWriter(new FileWriter("Test3_1.txt", true));
		BufferedReader br = new BufferedReader(new FileReader("Test3_1.txt"));
		boolean isRepeat = false;
		String info = "";
		for (int i = 0; i < list.size(); i++) {
			if (i == (list.size() - 1))
				info += list.get(i);
			else
				info += list.get(i) + ",";
		}
		String line;
		while ((line = br.readLine()) != null) {
			if (line.split(",")[0].equals(list.get(0))) {
				isRepeat = true;
				break;
			}
		}
		if (!isRepeat) {
			bw.write(info);
			bw.newLine();
		}
		bw.close();
		br.close();
		sc.close();
	}// add

	// 删除功能
	@Test
	public void bookDelete() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要删除的图书");
		String book = sc.nextLine();
		System.out.println("您确定要删除图书吗?是/否");
		String isDelete = sc.nextLine();
		if (isDelete.equals("是")) {
			BufferedReader br = new BufferedReader(new FileReader("Test3_1.txt"));
			// list为暂时缓冲区,缓存Test3_1.txt中的内容
			ArrayList<String> list = new ArrayList<>();
			String line;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			boolean isExist = false;
			for (String str : list) {
				if (str.split(",")[0].equals(book)) {
					list.remove(str);
					isExist = true;
					break;
				}
			}
			if (isExist) {
				BufferedWriter bw = new BufferedWriter(new FileWriter("Test3_1.txt"));
				for (String str : list) {
					if(str.equals(book))
						continue;
					bw.write(str);
					bw.newLine();
				}
				System.out.println("删除成功!");
				bw.close();
			} else {
				System.out.println("图书不存在,删除失败");
			}
			br.close();
		}
		sc.close();
	}// delete

	// 查看功能
	@Test
	public void bookLook() throws IOException {
		System.out.println("请输入你要查询的书");
		Scanner sc = new Scanner(System.in);
		String book = sc.nextLine();
		BufferedReader br = new BufferedReader(new FileReader("Test3_1.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			if (line.split(",")[0].equals(book)) {
				System.out.println(line);
				break;
			}
		}
		br.close();
		sc.close();
	}// look

	// 修改功能
	@Test
	public void bookChange() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Test3_1.txt"));
		System.out.println("请输入要修改的图书编号");
		Scanner sc = new Scanner(System.in);
		String book = sc.nextLine();
		ArrayList<String> list = new ArrayList<String>();
		String line;
		int index = 0;
		boolean isExist = false;
		// 把txt文件中的内容按行存储到list中,并判断要修改的图书是否存在
		while ((line = br.readLine()) != null) {
			list.add(line);
			if (line.split(",")[0].equals(book)) {
				isExist = true;
			}
		}
		if (isExist) {
			// 获得要修改的图书在list中的索引
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).split(",")[0].equals(book)) {
					index = i;
					break;
				}
			}
			String info = book;
			System.out.println("请输入您要修改的作者");
			info = info + "," + sc.nextLine();
			System.out.println("请输入您要修改的出版社");
			info = info + "," + sc.nextLine();
			System.out.println("请输入您要修改的出版日期");
			info = info + "," + sc.nextLine();
			System.out.println("请输入您要修改的单价");
			info = info + "," + sc.nextLine();
			System.out.println("请输入您要修改的数量");
			info = info + "," + sc.nextLine();
			list.set(index, info);
			BufferedWriter bw = new BufferedWriter(new FileWriter("Test3_1.txt"));
			for (String str : list) {
				bw.write(str);
				bw.newLine();
			}
			bw.close();
			br.close();
			sc.close();
		} else {
			System.out.println("您要修改的图书不存在!");
			br.close();
			sc.close();
			return;
		}
	}// set
}//BookDAO

/*
需求说明：接上例，本例中，我们为BookDAO添加动态代理。
1.要求为BookDAO中的每个方法进行增强，
要求在执行每个方法前要进行”权限检查”，在执行每个方法后要进行”写日志”操作。（这两个操作只需要打印文字即可）

1.创建新项目：day16作业_Test3_2
2.将关卡3的案例1中的所有类和文件复制到本项目中；
3.为BookDAO增加一个接口IBook，包含所有方法的声明；
4.定义Test类，包含main()方法，获取BookDAO的动态代理对象，并调用每个方法测试。
 */
public class Three2 {
	public static void main(String[] args) {
		BookDAO admin = new BookDAO();
		ClassLoader loader = admin.getClass().getClassLoader();
		Class<?>[] interfaces = admin.getClass().getInterfaces();
		InvocationHandler handler = new MyInvocationHandler(admin);
		IBook proxy = (IBook)Proxy.newProxyInstance(loader,interfaces,handler);
		proxy.bookLook();
	}
}
public interface IBook{
	public void bookLook();
	public void bookChange();
	public void bookDelete();
	public void bookAdd();
}
public class BookDAO implements IBook{
	// 添加功能
	@Test
	public void bookAdd() {
		ArrayList<String> list = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入你要添加的图书名");
		String book = sc.nextLine();
		list.add(book);
		System.out.println("请输入你要添加的作者");
		book = sc.nextLine();
		list.add(book);
		System.out.println("请输入你要添加的出版社");
		book = sc.nextLine();
		list.add(book);
		System.out.println("请输入你要添加的出版日期");
		book = sc.nextLine();
		list.add(book);
		System.out.println("请输入你要添加的单价");
		book = sc.nextLine();
		list.add(book);
		System.out.println("请输入你要添加的数量");
		book = sc.nextLine();
		list.add(book);

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("Test3_1.txt", true));
			BufferedReader br = new BufferedReader(new FileReader("Test3_1.txt"));
			boolean isRepeat = false;
			String info = "";
			for (int i = 0; i < list.size(); i++) {
				if (i == (list.size() - 1))
					info += list.get(i);
				else
					info += list.get(i) + ",";
			}
			String line;
			while ((line = br.readLine()) != null) {
				if (line.split(",")[0].equals(list.get(0))) {
					isRepeat = true;
					break;
				}
			}
			if (!isRepeat) {
				bw.write(info);
				bw.newLine();
			}
			bw.close();
			br.close();
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// add

	// 删除功能
	@Test
	public void bookDelete() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("请输入你要删除的图书");
			String book = sc.nextLine();
			System.out.println("您确定要删除图书吗?是/否");
			String isDelete = sc.nextLine();
			if (isDelete.equals("是")) {
				BufferedReader br = new BufferedReader(new FileReader("Test3_1.txt"));
				// list为暂时缓冲区,缓存Test3_1.txt中的内容
				ArrayList<String> list = new ArrayList<>();
				String line;
				while ((line = br.readLine()) != null) {
					list.add(line);
				}
				boolean isExist = false;
				for (String str : list) {
					if (str.split(",")[0].equals(book)) {
						list.remove(str);
						isExist = true;
						break;
					}
				}
				if (isExist) {
					BufferedWriter bw = new BufferedWriter(new FileWriter("Test3_1.txt"));
					for (String str : list) {
						if (str.equals(book))
							continue;
						bw.write(str);
						bw.newLine();
					}
					System.out.println("删除成功!");
					bw.close();
				} else {
					System.out.println("图书不存在,删除失败");
				}
				br.close();
			}
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// delete

	// 查看功能
	@Test
	public void bookLook() {
		try {
			System.out.println("请输入你要查询的书");
			Scanner sc = new Scanner(System.in);
			String book = sc.nextLine();
			BufferedReader br = new BufferedReader(new FileReader("Test3_1.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.split(",")[0].equals(book)) {
					System.out.println(line);
					break;
				}
			}
			br.close();
			sc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// look

	// 修改功能
	@Test
	public void bookChange() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Test3_1.txt"));
			System.out.println("请输入要修改的图书编号");
			Scanner sc = new Scanner(System.in);
			String book = sc.nextLine();
			ArrayList<String> list = new ArrayList<String>();
			String line;
			int index = 0;
			boolean isExist = false;
			// 把txt文件中的内容按行存储到list中,并判断要修改的图书是否存在
			while ((line = br.readLine()) != null) {
				list.add(line);
				if (line.split(",")[0].equals(book)) {
					isExist = true;
				}
			}
			if (isExist) {
				// 获得要修改的图书在list中的索引
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).split(",")[0].equals(book)) {
						index = i;
						break;
					}
				}
				String info = book;
				System.out.println("请输入您要修改的作者");
				info = info + "," + sc.nextLine();
				System.out.println("请输入您要修改的出版社");
				info = info + "," + sc.nextLine();
				System.out.println("请输入您要修改的出版日期");
				info = info + "," + sc.nextLine();
				System.out.println("请输入您要修改的单价");
				info = info + "," + sc.nextLine();
				System.out.println("请输入您要修改的数量");
				info = info + "," + sc.nextLine();
				list.set(index, info);
				BufferedWriter bw = new BufferedWriter(new FileWriter("Test3_1.txt"));
				for (String str : list) {
					bw.write(str);
					bw.newLine();
				}
				bw.close();
				br.close();
				sc.close();
			} else {
				System.out.println("您要修改的图书不存在!");
				br.close();
				sc.close();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// bookChange
}
public class MyInvocationHandler implements InvocationHandler{
	private BookDAO obj;
	public MyInvocationHandler(BookDAO obj){
		this.obj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args){
		checkBookDAOAccess();
		method.invoke(obj,args);
		writeLog();
		return null;
	}
	private void writeLog(){
		System.out.println("写日志");
	}
	private void checkBookDAOAccess(){
		System.out.println("权限检查");
	}
}