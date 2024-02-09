drop table if exists users cascade;
drop table if exists roles cascade;
drop table if exists user_role_x cascade;

create table users (
	id 					serial			primary key,
	created_at 			timestamp 		with time zone default current_timestamp,
	username 			varchar(50) 	unique not null,
	password 			varchar(50) 	not null,
	email 				varchar(255) 	unique not null
);

create table roles (
    id                  serial           primary key,
    authority           varchar(25)      unique not null
);

create table user_role_x (
	user_id 			integer 		not null,
	role_id 			integer 		not null,
	primary key (user_id, role_id)
);

create index index_users_id on users (id);
create index index_users_username on users (username);

alter table user_role_x
	add constraint fk_user_relation_id foreign key (user_id)
		references users(id);

alter table user_role_x
	add constraint fk_role_relation_id foreign key (role_id)
		references roles(id);