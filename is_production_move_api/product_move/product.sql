create table product_move.product
(
	id bigint auto_increment
		primary key,
	created_time datetime default (now()) null,
	last_updated_time datetime default (now()) null,
	product_code varchar(50) not null,
	product_name varchar(500) not null,
	category_id bigint not null,
	price varchar(500) not null,
	description text null,
	status_id bigint not null,
	customer_id bigint null,
	constraint product_category_fk
		foreign key (category_id) references product_move.category (id)
			on update cascade on delete cascade,
	constraint product_customer_fk
		foreign key (customer_id) references product_move.customer (id)
			on update cascade on delete cascade
);

