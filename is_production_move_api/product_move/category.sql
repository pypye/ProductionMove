create table product_move.category
(
	id bigint auto_increment
		primary key,
	created_time datetime default (now()) null,
	last_updated_time datetime default (now()) null,
	name varchar(50) not null,
	constraint name
		unique (name)
);

