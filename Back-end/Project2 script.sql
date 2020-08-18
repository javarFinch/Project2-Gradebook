create table if not exists admin (
	id integer primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	password varchar(50) not null
);


create table if not exists assignment(
	id serial primary key,
	assignment_name varchar(50) not null,
	assignment_type varchar(50) not null,
	actual_points integer,
	total_points integer not null,
	due_date varchar(50) not null,
	pair_id integer references class_student(id) on delete cascade
);

create table if not exists teacher(
	id integer primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	password varchar(50) not null
);

create table if not exists class(
	id serial primary key,
	class_name varchar(50) not null,
	class_subject varchar(50) not null,
	teacher_id integer references teacher(id) on delete cascade
);

create table if not exists student(
	id integer primary key,
	first_name varchar(50) not null,
	last_name varchar(50) not null,
	password varchar(50) not null
);

create table if not exists class_student(
	id serial primary key,
	class_id integer references class(id) on delete cascade,
	student_id integer references student(id) on delete cascade
);


drop table admin;
drop table assignment;
drop table teacher;
drop table class;
drop table student;
drop table class_student;
