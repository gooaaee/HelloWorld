package com.itheima.domain;

import java.util.Date;

/*
uidvarchar(32) NOT NULL
usernamevarchar(20) NULL
passwordvarchar(20) NULL
namevarchar(20) NULL
emailvarchar(30) NULL
telephonevarchar(20) NULL
birthdaydate NULL
sexvarchar(10) NULL
stateint(11) NULL
codevarchar(64) NULL
 */
public class User {
	private String uid;//用户id,32位
	private String username;//用户名
	private String password;//用户密码
	private String name;//用户昵称
	private String email;//用户email
	private String telephone;//用户电话
	private Date birthday;//出生日期
	private String sex;//性别
	private int state;//用户激活状态,0:未激活,1:已激活
	private String code;//用户激活码,64位
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
