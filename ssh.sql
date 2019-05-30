CREATE TABLE t_male_health (
	id INT ( 12 ) NOT NULL auto_increment,
	user_id INT ( 12 ) NOT NULL,
	heart VARCHAR ( 64 ) NOT NULL,
	liver VARCHAR ( 64 ) NOT NULL,
	spleen VARCHAR ( 64 ) NOT NULL,
	lung VARCHAR ( 64 ) NOT NULL,
	kidney VARCHAR ( 64 ) NOT NULL,
	prostate VARCHAR ( 64 ) NOT NULL,
	check_date date NOT NULL,
	note VARCHAR ( 512 ),
PRIMARY KEY ( id ) 
);

CREATE TABLE t_female_health (
	id INT ( 12 ) NOT NULL auto_increment,
	user_id INT ( 12 ) NOT NULL,
	heart VARCHAR ( 64 ) NOT NULL,
	liver VARCHAR ( 64 ) NOT NULL,
	spleen VARCHAR ( 64 ) NOT NULL,
	lung VARCHAR ( 64 ) NOT NULL,
	kidney VARCHAR ( 64 ) NOT NULL,
	uterus VARCHAR ( 64 ) NOT NULL,
	check_date date NOT NULL,
	note VARCHAR ( 512 ),
PRIMARY KEY ( id ) 
);

CREATE TABLE t_role ( 
	id INT ( 12 ) NOT NULL auto_increment, 
	role_name VARCHAR ( 64 ) NOT NULL, 
	note VARCHAR ( 512 ), 
	PRIMARY KEY ( id ) 
);


CREATE TABLE t_user (
	id INT ( 12 ) NOT NULL,
	user_name VARCHAR ( 60 ) NOT NULL,
	PASSWORD VARCHAR ( 60 ) NOT NULL,
	sex CHAR ( 1 ) NOT NULL,
	mobile VARCHAR ( 20 ) NOT NULL,
	tel VARCHAR ( 20 ),
	email VARCHAR ( 60 ),
	note VARCHAR ( 512 ),
PRIMARY KEY ( id ) 
);

CREATE TABLE t_user_info (
	id INT ( 12 ) NOT NULL,
	user_id INT ( 12 ) NOT NULL,
	head_image BLOB NOT NULL,
	note VARCHAR ( 1024 ),
	PRIMARY KEY ( id ) 
);


CREATE TABLE t_user_role (
	id INT ( 12 ) NOT NULL auto_increment,
	role_id INT ( 12 ) NOT NULL,
	user_id INT ( 12 ) NOT NULL,
PRIMARY KEY ( id ) 
);

CREATE UNIQUE INDEX role_user_idx ON t_user_role ( user_id, role_id );
