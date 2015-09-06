
create table user_attributes (
	id int primary key AUTO_INCREMENT,	
	user_id int  not null,
	created timestamp not null default now(),
	locale varchar(3) ,
	language varchar(3) ,
	foreign key (user_id) references user(id) on delete cascade,
	unique key (user_id)
) engine innodb;


