insert into classical_period (name, year_of_beginning, year_of_end) values ('Renaissance', 1400, 1600);
insert into classical_period (name, year_of_beginning, year_of_end) values ('Baroque', 1600, 1750);
insert into classical_period (name, year_of_beginning, year_of_end) values ('Classical', 1750, 1830);
insert into classical_period (name, year_of_beginning, year_of_end) values ('Romantic', 1830, 1900);

insert into composer (name, nationality, year_of_birth, classical_period_id)
values ('Johann Sebastian Bach', 'german', 1685, 2);
insert into composer (name, nationality, year_of_birth, classical_period_id)
values ('Georg Philipp Telemann', 'german', 1681, 2);
insert into composer (name, nationality, year_of_birth, classical_period_id)
values ('Giovanni Pierluigi da Palestrina', 'italian', 1525, 1);
insert into composer (name, nationality, year_of_birth, classical_period_id)
values ('Josquin des Prez', 'french',1450, 1);
insert into composer (name, nationality, year_of_birth, classical_period_id)
values ('Wolfgang Amadeus Mozart','austrian', 1756, 3);
insert into composer (name, nationality, year_of_birth, classical_period_id)
values ('Gustav Mahler', 'german',1860, 4);

insert into composition (title, composer_id) values ('Eine kleine Nachtmusik', 5);
insert into composition (title, composer_id) values ('Symphony No. 5', 6);
insert into composition (title, composer_id) values ('Domine exaudi orationem meam', 4);
insert into composition (title, composer_id) values ('Vestiva i colli', 3);
insert into composition (title, composer_id) values ('Brandenburg Concerto No.3', 1);
insert into composition (title, composer_id) values ('Concert Suite in D major', 2);
