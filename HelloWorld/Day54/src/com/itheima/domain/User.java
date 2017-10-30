package com.itheima.domain;

import java.util.Date;

/*
CREATE TABLE `user` (
  `uid` varchar(32) NOT NULL,
  `username` varchar(20) DEFAULT NULL,		#�û���
  `password` varchar(20) DEFAULT NULL,		#����
  `name` varchar(20) DEFAULT NULL,			#�ǳ�
  `email` varchar(30) DEFAULT NULL,			#��������
  `telephone` varchar(20) DEFAULT NULL,		#�绰
  `birthday` date DEFAULT NULL,				#����
  `sex` varchar(10) DEFAULT NULL,			#�Ա�
  `state` int(11) DEFAULT 0,				#״̬��0=δ���1=�Ѽ���
  `code` varchar(64) DEFAULT NULL,			#������
  PRIMARY KEY (`uid`)
) ;
 */
public class User {
	private String uid;//�û����
	private String username;//�û���
	private String password;//����
	private String name;//�ǳ�
	private String email;//��������
	private String telephone;//�绰
	private Date birthday;//����
	private String sex;//�Ա�
	private int state;//״̬��0=δ���1=�Ѽ���
	private String code;//������
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
