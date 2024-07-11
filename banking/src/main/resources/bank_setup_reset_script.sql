drop table if exists "user";
drop table if exists checking;
drop table if exists saving;
drop table if exists investment;

create table "user"(
	user_id integer PRIMARY KEY autoincrement,
	user_name text,
	password text
);

create table checking(
	account_id integer PRIMARY KEY autoincrement,
	gold float,
	silver float,
	copper float,
	user_id integer NOT null REFERENCES "user"(user_id)
);

create table saving(
	account_id integer PRIMARY KEY autoincrement,
	gold float,
	silver float,
	copper float,
	user_id integer NOT null REFERENCES "user"(user_id)
);

create table investment(
	account_id integer PRIMARY KEY autoincrement,
	gold float,
	silver float,
	copper float,
	user_id integer NOT null REFERENCES "user"(user_id)
);

insert into "user" (user_name, password) values ('admin', '1234');
insert into checking (gold, silver, copper, user_id) values (5, 0, 0, 1);
insert into saving (gold, silver, copper, user_id) values (5, 0, 0, 1);
insert into investment (gold, silver, copper, user_id) values (5, 0, 0, 1);