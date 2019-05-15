create database chapter15;
use chapter15;

create table t_role(
id int(12) not null auto_increment,
role_name varchar(60) not null,
note varchar(256) null,
primary key(id)
);

truncate t_role;

insert into t_role (role_name, note) value('role_name_1', 'note_1');
insert into t_role (role_name, note) value('role_name_2', 'note_2');
insert into t_role (role_name, note) value('role_name_3', 'note_3');
insert into t_role (role_name, note) value('role_name_4', 'note_4');
insert into t_role (role_name, note) value('role_name_5', 'note_5');

select * from t_role;
