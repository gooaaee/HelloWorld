#One1
/*
1)查询工资最高的员工是谁？
2)查询工资小于平均工资的员工有哪些？
3)查询大于5000的员工，来至于哪些部门，输出部门的名字
4)查询开发部与财务部所有的员工信息，分别使用子查询和表连接实现
5)查询2011年以后入职的员工信息和部门信息，分别使用子查询和表连接实现
*/
create database day21;
use day21;
CREATE TABLE dept(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20)
);
INSERT INTO dept (NAME) VALUES ('开发部'),('市场部'),('财务部');
CREATE TABLE employee (
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(10),
	gender CHAR(1),   -- 性别
	salary DOUBLE,   -- 工资
	join_date DATE,  -- 入职日期
	dept_id INT,
	FOREIGN KEY (dept_id) REFERENCES dept(id) -- 外键，关联部门表(部门表的主键)
);
INSERT INTO employee(NAME,gender,salary,join_date,dept_id) VALUES('孙悟空','男',7200,'2013-02-24',1);
INSERT INTO employee(NAME,gender,salary,join_date,dept_id) VALUES('猪八戒','男',3600,'2010-12-02',2);
INSERT INTO employee(NAME,gender,salary,join_date,dept_id) VALUES('唐僧','男',9000,'2008-08-08',2);
INSERT INTO employee(NAME,gender,salary,join_date,dept_id) VALUES('白骨精','女',5000,'2015-10-07',3);
INSERT INTO employee(NAME,gender,salary,join_date,dept_id) VALUES('蜘蛛精','女',4500,'2011-03-14',1);
#查询工资最高的员工是谁？
SELECT NAME from employee WHERE salary = (SELECT MAX(salary) FROM employee);
#查询工资小于平均工资的员工有哪些？
select NAME from employee where salary < (select avg(salary) from employee);
#查询大于5000的员工，来至于哪些部门，输出部门的名字
select temp.NAME,dept.NAME from dept,(select dept_id,NAME from employee where salary > 5000) temp where dept.id = temp.dept_id;
#查询开发部与财务部所有的员工信息，分别使用子查询和表连接实现
select employee.id,employee.NAME,employee.gender,employee.salary,employee.join_date,employee.dept_id from employee,(select id from dept where NAME in('财务部','开发部'))temp where employee.dept_id=temp.id;
#查询2011年以后入职的员工信息和部门信息，分别使用子查询和表连接实现
select temp.id,temp.NAME,temp.gender,temp.salary,temp.join_date,temp.dept_id,dept.NAME from dept,(select * from employee where join_date >= '2011-01-01')temp where temp.dept_id = dept.id;

#Two1
/*
某管理系统需要开发一套权限管理系统。众所周知，权限系统是每个系统里面必备的最基本的系统。每个用户有不同的角色，每个角色有不同的权限。请设计一套权限系统，使用多表连接查询，通过不同的用户名得到这个用户有哪些权限。
用到５张表：用户表（user）、角色表（role）、权限表（privilege）、用户角色表（user_role）、角色权限表（role_privilege）。各表的大体表结构如下：
1)用户表（user）：id、name、pwd
2)角色表（role）：id、name
3)权限表（privilege）：id、name
4)用户角色表（user_role）：user_id、role_id
角色权限表（role_privilege）：role_id
要求：
  1) 创建5张表，添加三个用户，三种角色(1总经理，2经理，3员工)
  2) 添加三种权限：员工：1请假；经理：1请假、2审批请假；总经理：1请假、2审批请假、3辞退员工
  3) 用户分配角色：1号员工是3号角色；2号员工是2、3号角色；3号员工是1，2号角色
  4) 权限与角色的关系，1号角色有3种权限，2号角色有2种权限，3号角色有1种权限
  5) 查询Jack、Rose、NewBoy登录以后有哪些权限，查询后要去掉重复的项。、privilege_id
*/
#创建5张表，添加三个用户，三种角色(1总经理，2经理，3员工)
#添加三种权限：员工：1请假；经理：1请假、2审批请假；总经理：1请假、2审批请假、3辞退员工
create table usr(
    id int primary key auto_increment,
    name varchar(50),
    pwd varchar(50)
);
insert into usr(name,pwd) values('Jack','111'),('Rose','222'),('NewBoy','333');

create table role(
    id int primary key auto_increment,
    name varchar(50)
);
insert into role(name) values('总经理'),('经理'),('员工');

create table privilege(
    id int primary key auto_increment,
    name varchar(50)
);
insert into privilege (name) values('请假'),('审批请假'),('辞退员工');

create table usr_role(
    usr_id int,
    role_id int,
    primary key(usr_id,role_id),
    foreign key (usr_id) references usr(id),
    foreign key (role_id) references role(id)
);
insert into usr_role (usr_id,role_id) values(1,3),(2,2),(2,3),(3,1),(3,2);

create table role_privilege(
    role_id int,
    privilege_id int,
    primary key(role_id,privilege_id),
    foreign key(role_id) references role(id),
    foreign key (privilege_id) references privilege(id)
);
insert into role_privilege(role_id,privilege_id) values(1,1),(1,2),(1,3),(2,1),(2,2),(3,1);
#用户分配角色：1号员工是3号角色；2号员工是2、3号角色；3号员工是1，2号角色
#权限与角色的关系，1号角色有3种权限，2号角色有2种权限，3号角色有1种权限
#查询Jack、Rose、NewBoy登录以后有哪些权限，查询后要去掉重复的项
select privilege.name from privilege where privilege.id in (select privilege_id from role_privilege where role_privilege.role_id in (select role_id from usr_role,(select id from usr where name = 'Jack') temp where usr_role.usr_id = temp.id));

#Three1
/*
假设某建筑公司要设计一个数据库。公司的业务规则概括说明如下：
公司承担多个工程项目，每一项工程有：工程号、工程名称、施工人员等
公司有多名职工，每一名职工有：职工号、姓名、职务
公司按照工时和小时工资率支付工资，小时工资率由职工的职务决定（例如，技术员的小时工资率与工程师不同）
 
查询要求：
1)查询职工号是1001的职工职务信息，要求输出职工号，姓名及其职务，并使用别名(内连接)
2)查询职务为“技术员”的职工薪水，要求输出姓名，职务，工时率(内连接)
3)查询所有的工程号，工程名称，职工号，工时(右连接)
4)查询“班建斌”职工的工作情况，要求输出姓名，参与的工程名称，工时(3表连接)
5)使用四表连接查询，查询出如下图的结果

1.2．操作步骤描述
1)创建工程表： 工程号(主键,字符串),工程名称解答问题的部门，使用p标签
2)职务表:   职务编号(主键)，职务,小时工资率
3)员工表:   职工号(主键，从1001开始),姓名,职务编号(外键)
4)工时表:  工时编号(主键)，工程号(外键),职工号(外键),工时
5)插入工程数据、职务表、员工表、工时表 
*/
create table project(
    id char(2) primary key,
    name varchar(50)
);
create table job(
    id int primary key auto_increment,
    name varchar(50),
    rate double
);
create table emp(
    id int primary key auto_increment,
    name varchar(50),
    job_id int,
    foreign key (job_id) references job(id)
);
alter table emp auto_increment = 1001;

create table work_time (
	prj_id char(2),
	emp_id int,
	hours int,
	primary key(prj_id, emp_id),
	foreign key(prj_id) references project(id),
	foreign key(emp_id) references emp(id)
);
insert into project values ('A1','花园大厦'),('A2','立交桥'),('A3','临江饭店');
INSERT INTO job VALUES (1,'工程师',65),(2,'技术员',60),(3,'律师',100),(4,'工人',55);
INSERT INTO emp (NAME,job_id) VALUES ('杨国明',1),('班建斌',2),('伍岳林',3),('鞠明亮',4);
INSERT INTO work_time VALUES ('A1',1001,13),('A1',1002,16),('A1',1003,19),('A2',1001,13),('A2',1004,17),('A3',1002,18),('A3',1003,14);

#1.查询职工号是1001的职工职务信息，要求输出职工号，姓名及其职务，并使用别名(内连接)
select temp.name,temp.id,job.name from job,(select * from emp where id = '1001') temp where job.id = temp.job_id;

#2.查询职务为“技术员”的职工薪水，要求输出姓名，职务，工时率(内连接)
select emp.name,temp.name,temp.rate from emp,(select * from job where name = '技术员') temp where emp.job_id = temp.id

#3.查询所有的工程号，工程名称，职工号，工时(右连接)
select p.id,p.name,w.emp_id,w.hours from project p right join work_time w on p.id = w.prj_id;

#4.查询"班建斌"职工的工作情况，要求输出姓名，参与的工程名称，工时(3表连接)
select temp1.n,temp1.h,p.name from project p,(select temp.name n,w.hours h,w.prj_id id from work_time w,(select * from emp where name = '班建斌') temp where temp.id = w.emp_id) temp1 where temp1.id = p.id;

#5.使用四表连接查询，查询出如下图的结果
/*
工程号  工程名称    职工号  姓名    职务    小时工资率  工时
A1      花园大厦    1001    杨国明  工程师      65      13
A1      花园大厦    1002    班建斌  技术员      60      16
A1      花园大厦    1004    伍岳林  律师        100     19
A2      立交桥      1001    杨国明  工程师      65      13
A2      立交桥      1003    鞠明亮  工人        55      17
A3      临江饭店    1002    班建斌  技术员      60      18
A3      临江饭店    1004    伍岳林  律师        100     14
*/
select 
