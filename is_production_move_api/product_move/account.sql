create table product_move.account
(
	id bigint auto_increment
		primary key,
	created_time datetime default (now()) null,
	last_updated_time datetime default (now()) null,
	username varchar(50) not null,
	password varchar(255) not null,
	email varchar(100) not null,
	role_id bigint not null,
	company_name varchar(100) not null,
	address varchar(500) not null,
	phone varchar(20) not null,
	constraint company_name
		unique (company_name),
	constraint email
		unique (email),
	constraint username
		unique (username),
	constraint user_role_fk
		foreign key (role_id) references product_move.role (id)
);

