
create table role(
	id int primary key AUTO_INCREMENT,	
	created timestamp not null default now(),
	role varchar(60)  not null,
	unique key (role)
) engine innodb;

