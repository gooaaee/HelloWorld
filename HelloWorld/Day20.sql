#One1
/*
1.基于product表的数据,进行聚合函数查询.

2.商品汇总,总价等查询。
#1 查询商品的总条数
#2 查询价格大于200商品的总条数
#3 查询分类为'c001'的所有商品的总和
#4 查询分类为'c002'所有商品的平均价格
#5 查询商品的最大价格和最小价格
*/
#创建商品表：
create table product(
	pid int primary key,
	pname varchar(20),
	price double,
	category_id varchar(32)
);
INSERT INTO product(pid,pname,price,category_id) VALUES(1,'联想',5000,'c001');
INSERT INTO product(pid,pname,price,category_id) VALUES(2,'海尔',3000,'c001');
INSERT INTO product(pid,pname,price,category_id) VALUES(3,'雷神',5000,'c001');
INSERT INTO product(pid,pname,price,category_id) VALUES(4,'JACK JONES',800,'c002');
INSERT INTO product(pid,pname,price,category_id) VALUES(5,'真维斯',200,'c002');
INSERT INTO product(pid,pname,price,category_id) VALUES(6,'花花公子',440,'c002');
INSERT INTO product(pid,pname,price,category_id) VALUES(7,'劲霸',2000,'c002');
INSERT INTO product(pid,pname,price,category_id) VALUES(8,'香奈儿',800,'c003');
INSERT INTO product(pid,pname,price,category_id) VALUES(9,'相宜本草',200,'c003');
INSERT INTO product(pid,pname,price,category_id) VALUES(10,'面霸',5,'c003');
INSERT INTO product(pid,pname,price,category_id) VALUES(11,'好想你枣',56,'c004');
INSERT INTO product(pid,pname,price,category_id) VALUES(12,'香飘飘奶茶',1,'c005');
INSERT INTO product(pid,pname,price,category_id) VALUES(13,'果9',1,NULL);

select count(*) from product;
select count(*) from product where price >= 200;
select sum(price) from product where category_id = 'c001';
select avg(price) from product where category_id = 'c002';
select max(price) from product;
select min(price) from product;

#One3
/*
#1 统计各个分类商品的个数
select category_id,count(*) from product group by category_id;
#2 统计各个分类商品的个数,且只显示个数大于1的信息
select category_id,count(*) from product group by category_id having count(*)>1;
*/
#Two1
/*
1:设计员工表emp和部门表dept
  要求: emp表的基本字段 empno int 主键 自动自增长,ename 字符类型 salary 数值类型  bonus 奖金数值型……deptno int 员工在的部门 (外键)
       dept表的基本字段 deptno int 主键 自动增长 , dname 部门名称 字符类型  dlocation 地理位置 字符类型
2: 设计表结构 在emp表中设计deptno外键.录入相关数据.
*/
create table emp(
    empno int primary key auto_increment,
    ename varchar(20),
    salary decimal(9,2),
    bonus decimal(9,2)
);
create table dept(
    deptno int primary key auto_increment,
    dname varchar(32),
    dlocation varchar(50)
);
alter table emp add column deptno int;
alter table emp add constraint emp_fk foreign key (deptno) references dept (deptno);
insert into dept (dname,dlocation) values('d001','d地方1');
insert into emp (ename,salary,bonus,deptno) values('e001','1000','10',1);

#Two2 
/*
1: 完成学员和老师,课程以及中间表设计
2: 使用sql脚本完成中间表设计以及联合主键,外键的引入.
3: 录入相关数据.
*/
create table student(
    sno int primary key auto_increment,
    sname varchar(32),
    sage int
);
create table teacher(
    tno int primary key auto_increment,
    tname varchar(32),
    tage int
);
create table STitem(
    sno int,
    tno int
);
alter table STitem add constraint STitem_student_fk foreign key (sno) references student(sno);
alter table STitem add constraint STitem_teacher_fk foreign key (tno) references teacher(tno);
insert into student (sname,sage) values('小明','18');
insert into student (sname,sage) values('小花','18');
insert into teacher (tname,tage) values('黄老师','50');
insert into STitem values(1,1);

#Three3
/*
基于设计和学员表,老师表,课程表以及中间表的数据,完成相关查询
1.查询平均成绩大于70分的同学的学号和平均成绩
select * from student where id = (select student_id from student_course group by student_id having avg(score) > 70);
2.查询所有同学的学号、姓名、选课数、总成绩
3.查询学过赵云老师所教课的同学的学号、姓名
select student.name,student.id from student,(select student_course.student_id from student_course,(select course.id from course,(select id from teacher where name = '赵云') temp where course.teacher_id = temp.id) temp2 where temp2.id = student_course.course_id) temp3 where temp3.student_id = student.id;
4.查询没学过关羽老师课的同学的学号、姓名
5.查询没有学三门课以上的同学的学号、姓名
6.查询各科成绩最高和最低的分
select max(score) from student_course group by course_id;
*/
create table student(
	id int primary key auto_increment,
	name varchar(20),
	city varchar(10),
	age int
);
create table teacher(
	id int primary key auto_increment,
	name varchar(20)
);
create table course(
	id int primary key auto_increment,
	name varchar(20),
	teacher_id int,
	foreign key (teacher_id) references teacher(id)
);
create table student_course(
	student_id int,
	course_id int,
	score int,
	foreign key (student_id) references student(id),
	foreign key (course_id) references course(id)
);
insert into teacher values(null,'关羽');
insert into teacher values(null,'张飞');
insert into teacher values(null,'赵云');

insert into student values(null,'小王','北京',20);
insert into student values(null,'小李','上海',18);
insert into student values(null,'小周','北京',22);
insert into student values(null,'小刘','北京',21);
insert into student values(null,'小张','上海',22);
insert into student values(null,'小赵','北京',17);
insert into student values(null,'小蒋','上海',23);
insert into student values(null,'小韩','北京',25);
insert into student values(null,'小魏','上海',25);
insert into student values(null,'小明','北京',20);

insert into course values(null,'语文',1);
insert into course values(null,'数学',1);
insert into course values(null,'生物',2);
insert into course values(null,'化学',2);
insert into course values(null,'物理',2);
insert into course values(null,'英语',3);

insert into student_course values(1,1,80);
insert into student_course values(1,2,90);
insert into student_course values(1,3,85);
insert into student_course values(1,4,78);
insert into student_course values(2,2,53);
insert into student_course values(2,3,77);
insert into student_course values(2,5,80);
insert into student_course values(3,1,71);
insert into student_course values(3,2,70);
insert into student_course values(3,4,80);
insert into student_course values(3,5,65);
insert into student_course values(3,6,75);
insert into student_course values(4,2,90);
insert into student_course values(4,3,80);
insert into student_course values(4,4,70);
insert into student_course values(4,6,95);
insert into student_course values(5,1,60);
insert into student_course values(5,2,70);
insert into student_course values(5,5,80);
insert into student_course values(5,6,69);
insert into student_course values(6,1,76);
insert into student_course values(6,2,88);
insert into student_course values(6,3,87);
insert into student_course values(7,4,80);
insert into student_course values(8,2,71);
insert into student_course values(8,3,58);
insert into student_course values(8,5,68);
insert into student_course values(9,2,88);
insert into student_course values(10,1,77);
insert into student_course values(10,2,76);
insert into student_course values(10,3,80);
insert into student_course values(10,4,85);
insert into student_course values(10,5,83);
