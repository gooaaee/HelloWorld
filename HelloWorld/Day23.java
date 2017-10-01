/*
使用C3P0获得10个连接对象
要求：不使用配置文件

1.创建连接池对象ComboPooledDataSource对象
2.设置连接参数（jdbcUrl，user，password，driverClass）
3.设置连接池参数
4.初始连接数initialPoolSize
a)最大连接数maxPoolSize
b)最大等待时间checkoutTimeout
c)最大空闲回收时间maxIdleTime
获取连接对象getConnection() 方法
 */
public class One1 {
	public static void main(String[] args) throws PropertyVetoException, SQLException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setUser("root");
		cpds.setPassword("root");
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/day22One5");
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setInitialPoolSize(5);
		cpds.setMaxPoolSize(10);
//		cpds.setCheckoutTimeout(2000);
		cpds.setMaxIdleTime(1000);
		for(int i = 0;i < 20;i++){
			Connection conn = cpds.getConnection();
			System.out.println("第"+(i+1)+"个"+conn);
		}
	}
}

/*
C3P0连接池的使用
要求：连接池参数和初始化连接数通过配置文件配置

使用C3P0连接池获得10个连接对象。
要求：分别使用默认配置和命名配置创建连接池对象。再通过连接池对象获得连接对象

1.创建配置文件c3p0-config.xml，配置连接池参数和初始化连接数。
2.分别使用默认配置和命名配置创建连接池对象。
3.通过连接池对象获得连接对象。
 */
public class One2 {
	public static void main(String[] args) throws Exception {
//		test01();
		test02();
	}

	// 使用默认配置(default-config)
	public static void test01() throws Exception {
		// 获得连接池对象
		ComboPooledDataSource ds = new ComboPooledDataSource();
		// 获得连接对象
		for (int i = 0; i < 10; i++) {
			Connection conn = ds.getConnection();
			System.out.println("第" + (i + 1) + "个:" + conn);
			if (i == 3) {
				// 把连接对象放回连接池
				conn.close();
			}
		}
	}

	// 使用命名配置(named-config)
	public static void test02() throws Exception {
		// 获得连接池对象
		ComboPooledDataSource ds = new ComboPooledDataSource("pkxing-config");
		// 获得连接对象
		for (int i = 0; i < 10; i++) {
			Connection conn = ds.getConnection();
			System.out.println("第" + (i + 1) + "个:" + conn);
		}
	}
}

/*
连接池工具类的创建

自定义C3P0工具类，要求如下：
1.创建私有静态数据源(连接池对象)成员变量
2.创建公有的得到数据源(连接池对象)的方法
3.创建共有的得到连接对象的方法
 */
public class One3 {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	public static DataSource getDataSource(){
		return ds;
	}
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}

/*
DBCP连接池的基本使用

使用DBCP连接池获得10个连接对象，要求如下：
1.纯代码编写配置信息(不使用配置文件)
2.设置初始为5个，最大10个，最大等待时间3秒，最大空闲个数3个

1）导入dbcp的jar包
2）创建连接池对象BasicDataSource对象
3）设置连接参数（url，user，password，driverClass）
4）设置连接池参数（初始连接数，最大连接数，最大等待时间，最大空闲个数）
5）获取连接对象（调用getConnection() 方法）
 */
public class One4 {
	public static void main(String[] args) throws SQLException {
		BasicDataSource ds = new BasicDataSource();
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setUrl("jdbc:mysql://localhost:3306/day22One5");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setInitialSize(5);
		ds.setMaxActive(10);
		ds.setMaxWait(1000);
		ds.setMaxIdle(5);
		for(int i = 0;i < 10;i++){
			System.out.println("第"+(i+1)+"个"+ds.getConnection());
		}
	}
}

/*
DBCP配置文件的使用

使用DBCP连接池获得10个连接对象，要求如下：
使用配置文件配置数据库参数和连接池参数

1) 使用Properties类加载属性文件
2) 通过类对象的getResourceAsStream("/dbcp.properties")方法，从类路径下加载文件，以字节流的方式加载。
3) 通过properties.load(InputStream in) 加载属性文件
4) 通过BasicDataSourceFactory.createDataSource(Properties prop)，得到DataSource连接池对象
5) 通过BasicDataSource类得到连接对象
 */
public class One5 {
	public static void main(String[] args) throws Exception {
		Properties p = new Properties();
		p.load(One5.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
		DataSource ds = BasicDataSourceFactory.createDataSource(p);
		for(int i = 0;i < 50;i++){
			Connection conn = ds.getConnection();
			System.out.println("第" + (i+1) + "个" + conn.hashCode());
			if(i == 9 || i == 3){
				conn.close();
			}
		}
	}
}

dbcpconfig.properties配置如下:
```
#连接设置
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/day23
username=root
password=root

#<!-- 初始化连接 -->
initialSize=5

#最大连接数量
maxActive=10

#<!-- 最大空闲连接 -->
maxIdle=5

#<!-- 最小空闲连接 -->
minIdle=2

#<!-- 超时等待时间以毫秒为单位 60000毫秒/1000等于60秒 -->
maxWait=60000


#JDBC驱动建立连接时附带的连接属性属性的格式必须为这样：[属性名=property;]
#注意："user" 与 "password" 两个属性会被明确地传递，因此这里不需要包含他们。
connectionProperties=useUnicode=true;characterEncoding=utf8

#指定由连接池所创建的连接的自动提交（auto-commit）状态。
defaultAutoCommit=true

#driver default 指定由连接池所创建的连接的只读（read-only）状态。
#如果没有设置该值，则“setReadOnly”方法将不被调用。（某些驱动并不支持只读模式，如：Informix）
defaultReadOnly=

#driver default 指定由连接池所创建的连接的事务级别（TransactionIsolation）。
#可用值为下列之一：（详情可见javadoc。）NONE,READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE
defaultTransactionIsolation=REPEATABLE_READ
```

/*
 DBCP工具类的定义

 自定义DBCP工具类，要求如下：
1)声明静态连接对池成员变量
2)编写静态语句块：加载类路径下的属性文件，并且使用工厂类创建数据源(连接池对象)
3)编写两个公共静态方法，返回数据源(连接池对象)和返回连接对象。
4)编写测试类测试该工具类的相关方法。
 */
public class One6 {
	private static DataSource ds;
	static{
		Properties p = new Properties();
		try {
			p.load(One6.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
			ds = BasicDataSourceFactory.createDataSource(p);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DataSource getDataSource(){
		return ds;
	}
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}

/*
1.DbUtils工具类的使用
2.C3P0工具类的使用
3.数据库的插入操作

1.创建一个用户表(包含字段：用户编号，用户名，性别，地址)
2.向用户表添加多条用户记录

1.通过C3P0工具类获得数据源对象
2.根据数据源对象创建QueryRunner对象
3.编写添加数据的SQL语句
4.调用QueryRunner对象的update方法执行SQL语句。
 */
public class One7 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
		String sql = "insert into user values(null,'天天天才郭','男','飘荡')";
		int rows = qr.update(sql);
		System.out.println("影响了" + rows + "行");
	}
}
/*
 *C3P0工具类
 */
public class C3P0Utils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();

	public static DataSource getDs() {
		return ds;
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void closeAll(Connection conn,PreparedStatement ps,ResultSet rs) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
/*
 *c3p0-config.xml
 *c3p0配置文件,置于src文件夹下
 */
```
<c3p0-config>
	<default-config>
		<property name="driverClass">com.mysql.jdbc.Driver</property>
		<property name="jdbcUrl">jdbc:mysql://127.0.0.1:3306/day23</property>
		<property name="user">root</property>
		<property name="password">root</property>
	</default-config>
</c3p0-config>
```

/*
1.DbUtils工具类的使用
2.DBCP工具类的使用
3.数据库的更新操作

更新用户表中id为2的记录，修改性别和居住地。

1.通过DBCP工具类获得数据源对象
2.根据数据源创建QueryRunner对象
3.编写更新记录的SQL语句。
4.调用QueryRunner对象的upate方法执行SQL语句。
 */
 public class One8 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(DBCPUtils.getDs());
		String sql = "update user set address = '阳光可以照到的地方' where id = 4";
		int rows = qr.update(sql);
		System.out.println("影响了" + rows + "行");

	}
}
/*
 *DBCP工具类
 */
public class DBCPUtils {
	private static DataSource ds;
	static{
		Properties p = new Properties();
		try {
			p.load(DBCPUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
			ds = BasicDataSourceFactory.createDataSource(p);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DataSource getDs(){
		return ds;
	}
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
}

/*
1.DbUtils工具类的使用
2.数据库的删除操作

删除用户表中id为3的记录

1.通过DBCP工具类获得数据源对象
2.根据数据源创建QueryRunner对象
3.编写更新记录的SQL语句。
4.调用QueryRunner对象的upate方法执行SQL语句。
 */
 public class One9 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		String sql = "delete from user where id = 4";
		int rows = qr.update(sql);
		System.out.println("影响了" + rows + "行");
	}
}

/*
1.DbUtils工具类查询方法使用
2.数据库的查询操作
3.自定义ResultSetHandler接口实现类

查询用户表的所有用户数据，要求如下：
1.只查询用户名和性别两个字段信息
2.查询结果是一个集合，集合中存放所有的用户对象

1.通过C3P0Utils工具类获得数据源对象
2.根据数据源对象创建QueryRunner对象
3.编写查询的SQL语句
4.调用QueryRunner的对象的query方法进行查询
5.获得查询结果。
 */
/*
 *方法一,使用匿名对象方式,局限性较强
 */
public class One10 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
		String sql = "select uname,sex from user";
		List<User> list = qr.query(sql, new ResultSetHandler<List<User>>(){

			@Override
			public List<User> handle(ResultSet rs) throws SQLException {
				ArrayList<User> list = new ArrayList<User>();
				while(rs.next()){
					String name = rs.getString("uname");
					String sex = rs.getString("sex");
					list.add(new User(0,name,sex,null));
				}
				return list;
			}
		});
		for(User user: list){
			System.out.println(user);
		}
	}
}
public class User {
	private int id;
	private String uname;
	private String sex;
	private String address;

	public User(int id, String uname, String sex, String address) {
		this.id = id;
		this.uname = uname;
		this.sex = sex;
		this.address = address;
	}
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", uname=" + uname + ", sex=" + sex + ", address=" + address + "]";
	}
}
/*
 *第二种方式,使用反射,实用性略好一点点
 */
public class One10 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
		String sql = "select * from user";
		List<User> list = qr.query(sql, new ResultSetHandlerImpl<User>(User.class));
		for (User user : list) {
			System.out.println(user);
		}
	}
}
public class ResultSetHandlerImpl<T> implements ResultSetHandler<List<T>>{
	private Class<T> clazz;
	private T obj;
	private Field field;
	public ResultSetHandlerImpl(Class<T> clazz) {
		this.clazz = clazz;
	}
	@Override
	public List<T> handle(ResultSet rs) throws SQLException {
		ArrayList<T> list = new ArrayList<>();
		while(rs.next()){
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			try {
				obj = clazz.newInstance();
			} catch (InstantiationException e1) {
				System.out.println("创建对象异常");
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				System.out.println("创建对象时非法访问异常");
				e1.printStackTrace();
			}
			for(int i = 1;i <= columnCount;i++){
				String columnName = metaData.getColumnName(i);
				try {
					field = clazz.getDeclaredField(columnName);
					Class<?> type = field.getType();
					if(type == int.class){
						int value = rs.getInt(i);
						String setMethodName = "set"+ columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
						Method mtd = clazz.getMethod(setMethodName,type);
						mtd.invoke(obj, value);
					}else{
						String value = rs.getString(i);
						String setMethodName = "set"  + columnName.substring(0,1).toUpperCase() + columnName.substring(1);
						Method mtd = clazz.getMethod(setMethodName,type);
						mtd.invoke(obj, value);
					}
				} catch (NoSuchFieldException e) {
					System.out.println("获取成员变量对象异常");
					e.printStackTrace();
				} catch (SecurityException e) {
					System.out.println("安全异常");
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					System.out.println("不存在这样的方法异常");
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					System.out.println("非法访问权限不足异常");
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					System.out.println("参数非法异常");
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					System.out.println("invoke调用异常");
					e.printStackTrace();
				}
			}
			list.add(obj);
		}
		return list;
	}
}

/*
1.DbUtils工具类的使用
2.ArrayHandler的使用
3.数据查询和数据封装

查询用户表中的第一条数据。并将数据封装成对象数组。

1.通过C3P0Utils工具类获得数据源对象
2.创建QueryRunner对象
3.编写SQL语句
4.调用QueryRunner对象的query方法传入SQL语句和ArrayHandlder对象
5.接收方法返回值即对象数组。
 */
public class Two1 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
		String sql = "select * from user";
		Object[] objs = qr.query(sql,new ArrayHandler());
		System.out.println(Arrays.toString(objs));
	}
}

/*
1.ArrayListHandler类的使用
2.数据的查询和封装

查询用户表中的所有用户记录，将每一条记录都封装成一个Object[]数组中，返回集合对象。

1.通过C3P0Utils工具类获得数据源对象
2.创建QueryRunner对象
3.编写SQL语句
4.创建ArrayListHandler对象
5.调用查询方法传递SQL和ArrayListHandler对象并接收返回值。
 */
public class Two2 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
		String sql = "select * from user";
		List<Object[]> list = qr.query(sql, new ArrayListHandler());
		for(Object[] objs : list){
			System.out.println(Arrays.toString(objs));
		}
	}
}

/*
1.JavaBean的使用
2.BeanHandler类的使用

查询用户表中的第一条用户记录并将该记录封装成一个JavaBean对象。
注意事项：JavaBean属性名和用户表的列名要相同
 */
public class Two3 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
		String sql = "select * from user where id = 2";
		//将一条记录封装到User对象中
		User user = qr.query(sql, new BeanHandler<>(User.class));
		System.out.println(user);
	}
}

/*
4.1．训练知识点
1.BeanListHandler类的使用
2.ScalarHandler类的使用
4.2．训练描述
1.定义一个方法：查询用户表的所有用户记录并将每一条记录封装成JavaBean对象存放到集合中。
2.定义一个方法：查询用户表中用户记录的数量。
 */
public class Two4 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
		//查询用户表的所有用户记录并将每一条记录封装成JavaBean对象存放到集合中
		String sql = "select * from user";
		List<User> list = qr.query(sql, new BeanListHandler<>(User.class));
		for(User user : list){
			System.out.println(user);
		}
		//查询用户表中用户记录的数量
		sql = "select count(*) from user";
		Object obj = qr.query(sql, new ScalarHandler());
		System.out.println(obj);
	}
}

/*
5.1．训练知识点
1.ColumnListHandler类的使用
2.KeyedHandler类的使用
5.2．训练描述
1.定义一个方法，查询用户表，获得所有用户的名字存放到集合中。
2.定义一个方法，查询用户表，获得所有用户的所有信息，返回一个Map集合(Map集合的key是用户id，value是每一个用户的信息，也是一个Map集合。)
 */
public class Two5 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
		//查询用户表，获得所有用户的名字存放到集合中
		String sql = "select uname from user";
		sql = "select * from user";
		List<Object> query = qr.query(sql, new ColumnListHandler("uname"));
		System.out.println(query);
		//查询用户表，获得所有用户的所有信息，返回一个Map集合
		sql = "select * from user";
		Map<Object, Map<String, Object>> map = qr.query(sql, new KeyedHandler("id"));
		System.out.println(map);
	}
}

/*
6.1．训练知识点
1.MapHandler类的使用
2.MapListHandler类的使用
6.2．训练描述
1.定义一个方法：查询用户表获取第一条用户记录并封装成Map集合(key是字段名称，value是字段值)。
2.定义一个方法：查询用户表获取所有用户记录并返回一个集合，集合中存放的都是Map对象，一个Map对象封装对应一个用户记录。
 */
public class Two6 {
	public static void main(String[] args) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
		String sql = "select * from user";
		Map<String, Object> query = qr.query(sql, new MapHandler());
		System.out.println(query);
		List<Map<String,Object>> query2 = qr.query(sql, new MapListHandler());
		System.out.println(query2);
	}
}

/*
1.1．训练知识点
1.C3P0Utils工具类使用
2.DbUtils工具类的使用
3.QueryRunner类的使用
1.2．训练描述
1.在数据库创建部门表和员工表，包含字段如下：
	部门表包含字段有：部门编号，部门名称(唯一且不能为空)
	员工表包含字段有：员工编号，员工姓名(唯一且不能为空)，员工性别，员工职位，员工工资，入职日期。部门编号(外键)
2.先添加多个部门数据，再添加多条员工数据
3.编写方法接收一个员工编号和工资两个参数，方法内将指定编号的员工工资修改为新的工资。
4.编写方法查询指定职位所有员工的信息，返回List<Employee>集合。
5.编写方法查询指定姓名的员工信息，返回Employee对象。
6.编写方法根据员工姓名删除指定的员工信息。
7.编写方法查询所有姓张员工的工资并输出在控制台，输出格式如下：
		张三=10000
		张飞=20000
		…………………
8.编写方法接收一个工资参数，方法内查询工资大于等于传入的工资的员工，返回符合条件所有员工信息List<Employee>集合。
9.编写方法查询指定部门的所有员工信息，返回List<Employee>集合
要求：以上所有的增删改查操作都必须使用DbUtils工具类型相关API完成。


数据库中插入的数据如下:
USE day23;
#部门表包含字段有：部门编号，部门名称(唯一且不能为空)
CREATE TABLE dept(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(50) UNIQUE NOT NULL
);
#day23员工表包含字段有：员工编号，员工姓名(唯一且不能为空)，员工性别，员工职位，员工工资，入职日期。部门编号(外键)
CREATE TABLE employee(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(50) UNIQUE NOT NULL,
	gender VARCHAR(10),
	job VARCHAR(50),
	salary DOUBLE ,
	join_date DATE,
	dept_id INT,
	FOREIGN KEY (dept_id) REFERENCES dept(id)
);

#dept表中添加数据,"研发部","销售部","产品部"
INSERT INTO dept(NAME) VALUES('研发部'),('销售部'),('产品部');
#employee表中插入数据
INSERT INTO employee(NAME,gender,job,salary,join_date,dept_id)
VALUES('张三','男','Java研发工程师',9000,'2016-10-10',1),
('林青霞','女','销售经理',15000,'2015-06-15',2),
('李四','男','产品经理',10000,'2014-12-11',3),
('张三丰','男','Android研发工程师',8000,'2017-05-26',1);
 */
public class Three1 {
	@Test
	public void foo() throws SQLException{
//		queryAll();
//		queryEmployee("张三丰");
//		querySome();
//		querySalary(5000);
//		queryDept("研发部");
//		salaryChange(6,2333);
		deleteEmployee("张三丰");
	}

	private QueryRunner qr = new QueryRunner(C3P0Utils.getDs());
	private String sql;
	// 3.编写方法接收一个员工编号和工资两个参数，方法内将指定编号的员工工资修改为新的工资
	public void salaryChange(int id, double salary) throws SQLException {
		sql = "update employee set salary = ? where id = ?";
		Object[] params = {salary,id};
		int rows = qr.update(sql,params);
		System.out.println("共影响了" + rows + "行");
	}

	// 4.编写方法查询指定职位所有员工的信息，返回List<Employee>集合
	public List<Employee> queryAll() throws SQLException {
		sql = "select * from employee";
		List<Employee> list = qr.query(sql, new BeanListHandler<>(Employee.class));
		for (Employee e : list) {
			System.out.println(e);
		}
		return list;
	}

	// 5.编写方法查询指定姓名的员工信息，返回Employee对象
	public Employee queryEmployee(String name) throws SQLException {
		sql = "select * from employee where name = ?";
		Object[] params = {name};
		Employee e = qr.query(sql, new BeanHandler<>(Employee.class),params);
		System.out.println(e);
		return e;
	}

	// 6.编写方法根据员工姓名删除指定的员工信息
	public void deleteEmployee(String name) throws SQLException {
		sql = "delete from employee where name = ?";
		Object[] params = {name};
		int rows = qr.update(sql,params);
		System.out.println("影响了"+rows+"行");
	}

	// 7.编写方法查询所有姓张员工的工资并输出在控制台
	public void querySome() throws SQLException {
		sql = "select salary from employee where name like '张%'";
		List<Object> list = qr.query(sql, new ColumnListHandler());
		System.out.println(list);
	}

	// 8.编写方法接收一个工资参数，方法内查询工资大于等于传入的工资的员工，返回符合条件所有员工信息List<Employee>集合
	public List<Employee> querySalary(double salary) throws SQLException {
		sql = "select * from employee where salary >= ?";
		Object[] params = {salary};
		List<Employee> list = qr.query(sql, new BeanListHandler<>(Employee.class),params);
		System.out.println(list);
		return list;
	}

	// 9.编写方法查询指定部门的所有员工信息，返回List<Employee>集合
	public List<Employee> queryDept(String deptName) throws SQLException {
		sql = "select * from employee e,(select * from dept where name = ?) temp where e.dept_id = temp.id";
		Object[] params = {deptName};
		List<Employee> list = qr.query(sql, new BeanListHandler<>(Employee.class),params);
		System.out.println(list);
		return list;
	}
}
