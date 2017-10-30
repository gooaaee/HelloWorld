package com.itheima.domain;

import java.util.Date;

/*
CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,		#用户名
  `password` varchar(20) DEFAULT NULL,		#密码
  `name` varchar(20) DEFAULT NULL,			#昵称
  `email` varchar(30) DEFAULT NULL,			#电子邮箱
  `telephone` varchar(20) DEFAULT NULL,		#电话
  `birthday` date DEFAULT NULL,				#生日
  `sex` varchar(10) DEFAULT NULL,			#性别
  `state` int(11) DEFAULT 0,				#状态：0=未激活，1=已激活
  `code` varchar(64) DEFAULT NULL,			#激活码
  PRIMARY KEY (`uid`)
) ;
 */
public class User {
	private String uid;//用户编号
	private String username;//用户名
	private String password;//密码
	private String name;//昵称
	private String email;//电子邮箱
	private String telephone;//电话
	private Date birthday;//生日
	private String sex;//性别
	private int state;//状态：0=未激活，1=已激活
	private String code;//激活码
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
				+ email + ", telephone=" + telephone + ", birthday=" + birthday + ", sex=" + sex + ", state=" + state
				+ ", code=" + code + "]";
	}

}
