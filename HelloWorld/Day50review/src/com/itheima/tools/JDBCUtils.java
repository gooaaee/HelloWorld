package com.itheima.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl=new ThreadLocal<>();
	
	/**
	 * 从线程中获取连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		//从线程中获取conneciton
		Connection conn = tl.get();
		if(conn==null){
			conn=ds.getConnection();
			//和当前线程绑�?
			tl.set(conn);
		}
		return conn;
	}

	// 获取数据�?
	public static DataSource getDataSource() {
		return ds;
	}

	// 释放资源
	public static void closeResource( Statement st, ResultSet rs) {
		closeResultSet(rs);
		closeStatement(st);
	}
	
	// 释放资源
	public static void closeResource(Connection conn, Statement st, ResultSet rs) {
		closeResource(st, rs);
		closeConn(conn);
	}

	// 释放 connection
	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				//和线程解�?
				tl.remove();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	// 释放 statement ctrl + shift + f 格式化代�?
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			st = null;
		}
	}

	// 释放结果�?
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}
	
	
	//�?��事务
	public static void startTransaction() throws SQLException{
		getConnection().setAutoCommit(false);
	}
	
	/**
	 * 事务提交且释放连�?
	 */
	public static void commitAndClose(){
		Connection conn = null;
		try {
			conn=getConnection();
			//事务提交
			conn.commit();
			//关闭资源
			conn.close();
			//解除版定
			tl.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 事务回滚且释放资�?
	 */
	public static void rollbackAndClose(){
		Connection conn = null;
		try {
			conn=getConnection();
			//事务回滚
			conn.rollback();
			//关闭资源
			conn.close();
			//解除版定
			tl.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
