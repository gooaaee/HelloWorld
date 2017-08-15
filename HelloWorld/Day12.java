/*
现有一字符串：”我爱Java”。将该字符串保存到当前项目根目录下的a.txt文件中。
要求：使用utf8编码保存。

利用转换输入流将当前项目根目录下使用utf8编码的a.txt文件的内容读取出来，并打印在控制台上。
要求：不能出现乱码的情况。
 */
public class One4 {
	public static void main(String[] args) throws IOException{
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("a.txt"),"UTF-8");
		osw.write("我爱java");
		osw.close();
		InputStreamReader isr = new InputStreamReader(new FileInputStream("a.txt"), "UTF-8");
		int len;
		char[] chs  = new char[1024];
		while((len = isr.read(chs)) != -1){
			System.out.println(new String(chs, 0, len));
		}
		isr.close();
	}
}

/*
对象的序列化
定义一个学生类，包含姓名，年龄，性别等成员变量，提供setters和getters方法以及构造方法。
在测试类中创建一个学生对象，给学生对象的三个成员变量赋值。
然后将该对象保存到当前项目根目录下的stu.txt文件中。

对象的反序列化
1.创建文件字节输入流对象关联目标文件
2.根据文件字节输入流对象创建对象输入流对象
3.调用对象输入流对象的方法从文件中获取学生对象
4.关闭流释放资源。
 */
public class One6 {
	public static void main(String[] args){
		Student stu = new Student("Tom",18,男);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stu.txt"));
		oos.writeObject(stu);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stu.txt"));
		Object obj = ois.readObject();
		System.out.println(obj);
		ois.close();
	}
}
class Student implements Serializable {
	private String name;
	private int age;
	private String sex;
	public Student() {
		super();
	}
	public Student(String name, int age, String sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
}

/*
在当前项目根目录下准备好一个test.txt 的文本文件，要求该文本文件是使用GBK编码，其内容如下：
窗前明月光
疑是地上霜
举头望明月
低头思故乡

利用字节流+桥转换读入这个文本文件，以UTF8的编码方式将读取的内容写到test2.txt文件中
 */
public class Two1 {
	public static void main(String[] args) throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test.txt"),"GBK"));
		bw.write("窗前明月光");
		bw.newLine();
		bw.write("疑是地上霜");
		bw.newLine();
		bw.write("举头望明月");
		bw.newLine();
		bw.write("低头思故乡");
		bw.newLine();
		bw.close();

		BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("test2.txt"),"UTF-8"));
		BufferedReader br2 = new BufferedReader(new FileReader("test.txt"));
		int len;
		char[] chs = new char[1024];
		while((len = br2.read(chs)) != -1){
			bw2.write(chs,0,len);
		}
		bw2.close();
	}
}


/*
有学生类包含学号，姓名，省份证号，Java成绩，数学成绩，英语成绩等成员变量，提供构造方法和setter和getter方法。
要求：
	* 学生信息及成绩保存到C盘的save.txt文件中
	* 学生身份证号码不能保存到文件中。
	* 程序运行时如果save.txt不存在，则
从键盘录入1个学生信息，信息录入格式如下：
	***** 录入学生信息 *****
	请输入学号：9527
	请输入姓名：华安
	请输入身份证号：2203919831234543
	请输入Java成绩：90
	请输入数学成绩：80
	请输入英语成绩：88

	根据录入的信息创建学生对象并将学生对象保存到C盘下的save.txt文件中。
	* 如果程序运行时，save.txt文件已经存在，则显示学生信息。格式如下：
	**** 学生基本信息 *****
	学号  姓名 省份证号  Java成绩  数学成绩  英语成绩
	9527  华安   null      90        80        88
 */
public class Third1 {
	public static void main(String[] args) throws IOException, throws ClassNotFoundException{
		Student stu = new Student("华安", "2203919831234543", "90", "80", "88");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.txt"));
		oos.writeObject(stu);
		oos.close();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.txt"));
		Object obj = ois.readObject();
		Student st = (Student)obj;
		System.out.println(st.name+st.identity+st.java+st.math+st.english);
		ois.close();
	}
}
class Student implements Serializable{
	public String name;
	public String identity;
	public String java;
	public String math;
	public String english;
	public Student(String name, String identity, String java, String math, String english){
		this.name = name;
		this.identity = identity;
		this.java = java;
		this.math = math;
		this.english = english;
	}
	public Student(){
	}
}