create table product_move.customer
(
	id bigint auto_increment
		primary key,
	created_time datetime default (now()) null,
	last_updated_time datetime default (now()) null,
	name text not null,
	address text not null,
	phone text not null
);

