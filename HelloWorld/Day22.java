/*
通过代码获得Statement对象并执行一条创表语句：创建一张学生表(id，姓名，性别)
1.注册数据库驱动
2.获得连接对象
3.获得Statement对象
4.定义要执行的创表语句
5.调用Statement对象的方法执行SQL语句。
6.释放资源。
 */
public class One5 {
	public static void main(String[] args) throws Exception {
		// 1
		Class.forName("com.mysql.jdbc.Driver");
		// 2
		String url = "jdbc:mysql://localhost:3306/day22One5";
		String user = "root";
		String pwd = "root";
		Connection conn = DriverManager.getConnection(url, user, pwd);
		// 3
		Statement state = conn.createStatement();
		// 4
		String sql = "create table student(id int primary key auto_increment,"
						+  "sname varchar(50),"
						+ "sex varchar(50));";
		int row = state.executeUpdate(sql);
		// 5
		System.out.println("影响了" + row + "行");
		// 6
		state.close();
		conn.close();
	}
}

