create database chapter5;

use chapter5;

create table t_role (
id int(12) auto_increment,
role_name varchar(60) not null,
note varchar(256) null,
primary key(id)
);

insert into t_role(role_name, note) values('role_name_1', 'note_1');
insert into t_role(role_name, note) values('role_name_2', 'note_2');
insert into t_role(role_name, note) values('role_name_3', 'note_3');

create table t_role2 (
id int(12),
role_name varchar(60) not null,
note varchar(256) null,
primary key(id)
);

create table t_user (
id int(12) auto_increment, 
user_name varchar(60) not null, 
real_name VARCHAR(60) NOT NULL, 
sex int(3), 
moble VARCHAR(20) NOT NULL, 
email VARCHAR(60) NOT NULL, 
note VARCHAR(256) NOT NULL,
primary key(id)
);

create table t_user_role (
id INT(12) AUTO_INCREMENT, 
user_id int(12) not null,
role_id int(12) not null,
 PRIMARY KEY(id),
 unique(user_id, role_id)
) ;

DROP TABLE IF EXISTS t_female_health_form;
DROP TABLE IF EXISTS t_male_health_form;
DROP TABLE IF EXISTS t_task;
DROP TABLE IF EXISTS t_work_card;
DROP TABLE IF EXISTS t_employee_task;
DROP TABLE IF EXISTS t_employee;

/*==============================================================*/
/* Table: t_employee                                            */
/*==============================================================*/
CREATE TABLE t_employee
(
   id                   INT(12) NOT NULL AUTO_INCREMENT,
   real_name            VARCHAR(60) NOT NULL,
   sex                  INT(2) NOT NULL COMMENT '1 - ÄÐ 
            0 -Å®',
   birthday             DATE NOT NULL,
   mobile               VARCHAR(20) NOT NULL,
   email                VARCHAR(60) NOT NULL,
   POSITION             VARCHAR(20) NOT NULL,
   note                 VARCHAR(256),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_employee_task                                       */
/*==============================================================*/
CREATE TABLE t_employee_task
(
   id                   INT(12) NOT NULL auto_increment,
   emp_id               INT(12) NOT NULL,
   task_id              INT(12) NOT NULL,
   task_name            VARCHAR(60) NOT NULL,
   note                 VARCHAR(256),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_female_health_form                                  */
/*==============================================================*/
CREATE TABLE t_female_health_form
(
   id                   INT(12) NOT NULL AUTO_INCREMENT,
   emp_id               INT(12) NOT NULL,
   heart                VARCHAR(64) NOT NULL,
   liver                VARCHAR(64) NOT NULL,
   spleen               VARCHAR(64) NOT NULL,
   lung                 VARCHAR(64) NOT NULL,
   kidney               VARCHAR(64) NOT NULL,
   uterus               VARCHAR(64) NOT NULL,
   note                 VARCHAR(256),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_male_health_form                                    */
/*==============================================================*/
CREATE TABLE t_male_health_form
(
   id                   INT(12) NOT NULL AUTO_INCREMENT,
   emp_id               INT(12) NOT NULL,
   heart                VARCHAR(64) NOT NULL,
   liver                VARCHAR(64) NOT NULL,
   spleen               VARCHAR(64) NOT NULL,
   lung                 VARCHAR(64) NOT NULL,
   kidney               VARCHAR(64) NOT NULL,
   prostate             VARCHAR(64) NOT NULL,
   note                 VARCHAR(256),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_task                                                */
/*==============================================================*/
CREATE TABLE t_task
(
   id                   INT(12) NOT NULL auto_increment,
   title                VARCHAR(60) NOT NULL,
   context              VARCHAR(256) NOT NULL,
   note                 VARCHAR(256),
   PRIMARY KEY (id)
);

/*==============================================================*/
/* Table: t_work_card                                           */
/*==============================================================*/
CREATE TABLE t_work_card
(
   id                   INT(12) NOT NULL AUTO_INCREMENT,
   emp_id               INT(12) NOT NULL,
   real_name            VARCHAR(60) NOT NULL,
   department           VARCHAR(20) NOT NULL,
   mobile               VARCHAR(20) NOT NULL,
   POSITION             VARCHAR(30) NOT NULL,
   note                 VARCHAR(256),
   PRIMARY KEY (id)
);

ALTER TABLE t_employee_task ADD CONSTRAINT FK_Reference_4 FOREIGN KEY (emp_id)
      REFERENCES t_employee (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE t_employee_task ADD CONSTRAINT FK_Reference_8 FOREIGN KEY (task_id)
      REFERENCES t_task (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE t_female_health_form ADD CONSTRAINT FK_Reference_5 FOREIGN KEY (emp_id)
      REFERENCES t_employee (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE t_male_health_form ADD CONSTRAINT FK_Reference_6 FOREIGN KEY (emp_id)
      REFERENCES t_employee (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE t_work_card ADD CONSTRAINT FK_Reference_7 FOREIGN KEY (emp_id)
      REFERENCES t_employee (id) ON DELETE RESTRICT ON UPDATE RESTRICT;
