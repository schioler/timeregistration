create table user (
	id int primary key AUTO_INCREMENT,
	created timestamp not null default now(),
	login varchar(80) not null,
	name varchar(140),
	locale varchar(3) ,
	language varchar(3) ,
	is_login_enabled enum ('Y','N') not null default 'n',
	enabled TINYINT NOT NULL DEFAULT 1 ,
	unique key (login)
) engine innodb;



