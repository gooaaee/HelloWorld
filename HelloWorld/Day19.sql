CREATE DATABASE webdb_1;
USE webdb_1;
CREATE TABLE category(
    cid INT PRIMARY KEY,
    cname VARCHAR(100)
);
#One7
/*
drop
alter
rename
*/
SHOW TABLES;
DROP TABLE category;
ALTER TABLE category ADD `desc` VARCHAR(20);
ALTER TABLE category MODIFY `desc` VARCHAR(50) NOT NULL;
ALTER TABLE category CHANGE `desc` `description` VARCHAR(30);
ALTER TABLE category DROP description;
RENAME TABLE category TO category2;
ALTER TABLE category CHARACTER SET gbk;
#One8
/*
insert
delete
update
*/
INSERT INTO category (cid,cname) values ('1','jeck');
INSERT INTO category values ('2','tom');
DELETE FROM category WHERE cid = '2';
UPDATE category SET cid = '2',cname = 'steve' WHERE cid = '1';

#One9
/*
根据下列数据完成下面的查询练习
1.查询商品名称为“花花公子”的商品所有信息：
select * from product where pname = '花花公子';
2.查询价格为800商品
select pname from product where price >= 800;
3.查询价格不是800的所有商品
select * from product where price <> 800;
4.查询商品价格大于60元的所有商品信息
select * from product where price > 60;
5.查询商品价格在200到1000之间所有商品
select pname from product where price >= 200 and price <= 1000;
6.查询商品价格是200或800的所有商品
select pname from product where price >= 200 and price <= 800;
7.查询含有'霸'字的所有商品
select pname from product where pname like '%霸%';
8.查询以'香'开头的所有商品
select pname from product where pname like '%香%';
9.查询第二个字为'想'的所有商品
select pname from product where pname like '_想%';
10.商品没有分类的商品
select pname from product where category_id is null;
11.查询有分类的商品
select pname from product where category_id is not null;
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

#Two1
/*
操作题1:
在cmd窗口,使用mysql命令,创建编码utf8的数据库webdb2,创建表users,完成相关查询
要求: users表的字段  id  name  age   birthday   salary
1:字段要求: id  int  主键 , age  int  , name 字符类型, birthday 日期类型   salary 数值类型 要求小数点保留2位.
2:录入初始化数据,薪资可以录入,也可以不录入数据. 
3:查询所有员工的薪资,年薪,以及姓名
4:查询所有员工,如果薪资没有的员工,salary列值显示:没有薪资,倒序显示.
5:将salary列为null的用户薪资,修改为0.00.
*/
create database webdb_2;
use webdb_2;
create table users(
    id int primary key,
    age int,
    name varchar(20),
    birthday date,
    salary decimal(6,2)
);
insert into users values(1,null,null,null,5.6);

#Two2
/*
操作题2:
基于用户表users完成相关查询.
1:查询员工张姓且薪资大于900的员工.
2:查询生日在1990-1-1之前的员工信息.
3:查询员工薪资小于1200 或者 大于5000的信息
4:查询姓名以明结尾的员工信息.
*/
#Three1
/*
1: cmd窗口,采用root登录,创建一个新客户 rabbit,密码 123.
2:使用root创建一个全新的数据库webdb3,编码utf8.
3:给rabbit授予webdb3所有权限.
4:root用户退出,采用rabbit登录.
5:rabbit用户登录,查询所有数据库信息.
6:在webdb3下创建一张新表demo表 
7:设计demo表的基本字段 id int 主键,  name 姓名字符类型.
8:录入初始化数据,可以随机录入一些数据.(推荐录入3条记录以上)
9:将webdb3数据库数据进行备份.
10:将webdb3数据库进行删除.
11:对webdb3数据库进行恢复.
*/
#创建新用户rabbit
create user rabbit identified by '123';
create database if not exists webdb3 character set utf8;
#对rabbit用户授予该数据库的所有权限
grant all on webdb3.* to rabbit;
mysql -urabbit -p123
create table demo(
    id int primary key,
    name varchar(200)
);
insert into demo values(1,'demo1');
insert into demo values(2,'demo2');
insert into demo values(3,'demo3');
#备份webdb3数据库到d盘的webdb3.sql文件夹中
mysqldump -urabbit -p123 webdb3 > d:/webdb3.sql
drop database webdb3;
#使用rabbit用户恢复webd3数据库,失败
mysql -urabbit -p webdb3<d:/webdb3.sql
#登录rabbit用户,新建一个webdb3数据库
mysql -urabbit -p123
create database if not exists webdb3 character set utf8;
#退出mysql,执行下列恢复命令,恢复数据库成功
mysql -urabbit -p webdb3<d:/webdb3.sql
#再次查询是否恢复成功
mysql -urabbit -p123
use webdb3
select * from demo;


