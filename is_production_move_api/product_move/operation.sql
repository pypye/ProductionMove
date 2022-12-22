create table product_move.operation
(
	id bigint auto_increment
		primary key,
	created_time datetime default (now()) null,
	last_updated_time datetime default (now()) null,
	product_id bigint not null,
	status_id bigint not null,
	company_id bigint not null,
	constraint operation1
		foreign key (product_id) references product_move.product (id)
			on update cascade on delete cascade,
	constraint operation2
		foreign key (status_id) references product_move.status (id)
			on update cascade on delete cascade,
	constraint operation3
		foreign key (company_id) references product_move.account (id)
			on update cascade on delete cascade
);

