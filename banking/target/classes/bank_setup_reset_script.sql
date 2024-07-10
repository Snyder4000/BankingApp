drop table if exists "user";

create table "user"(
	userid integer PRIMARY KEY autoincrement,
	username text,
	password text
);

insert into "user" values ('admin', '1234');