drop table if exists classical_period cascade;
drop table if exists composer cascade;
drop table if exists composition cascade;


create table classical_period (
id long  identity not null primary key,
name varchar(50),
year_of_beginning integer,
year_of_end integer
);

create table composer (
id long  identity not null primary key,
name varchar(100),
nationality varchar(20),
year_of_birth integer,
classical_period_id long
);

create table composition (
id long  identity not null primary key,
title varchar(150),
composer_id long
);

