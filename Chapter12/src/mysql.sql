create database chapter12;
use chapter12;
create table t_role(
id int(12) not null auto_increment,
role_name varchar(60) not null,
note varchar(256) null,
primary key(id)
);

insert into t_role (role_name, note) values('role_name_1', 'note');
