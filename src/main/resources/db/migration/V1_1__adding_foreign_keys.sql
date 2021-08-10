alter table composer add foreign key (classical_period_id) references classical_period(id);
alter table composition add foreign key (composer_id) references composer(id);