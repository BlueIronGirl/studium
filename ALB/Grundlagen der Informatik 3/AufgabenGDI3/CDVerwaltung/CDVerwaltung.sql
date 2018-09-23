create database gdi3;
use gdi3;

drop table cd;
create table cd(
	titel varchar(30) not null,
	kuenstler varchar(30), 
	anzahl int,
	erscheinungsjahr numeric(4,0), /* auch date moeglich */
	primary key(titel)
);

/* delete from cd where titel='Titel'; */
insert into cd values ('Titel', 'Alice Bedow', 1, 2016);

select * from cd;