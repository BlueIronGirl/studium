create table artikel(
	artikelnr int not null primary key,
	artikelbezeichnung varchar(30),
	betrag int,
	nks int,
	wkz varchar(5),
	mbest int,
	best int);

create table kunde(
	kdnr int not null primary key,
	kanrede varchar(30),
	kname varchar(30),
	kvorname varchar(30),
	ort varchar(30),
	plz varchar(6),
	strasse varchar(30),
	debnr varchar(10));

create table auftrag(
	aufnr int not null,
/* automatische genierung mittels:
 *  aufnr int generated always as identity 
 *	(start with 1, increment by 1),
 * siehe auch nachfolgender text.
*/
	aufdatum date,
	liefdatum date,
	aufkdnr int,
	aufcurr varchar(10),
	primary key(aufnr));

create table auftragsposition(
	aufnr int,
	posnr int,
	artnr int,
	menge int,
	betrag int,
	nks int,
	wkz varchar(10),
	tldat date,
	primary key(aufnr, posnr));
}

create table benutzer {
	benutzerid int not null primary key,
	id varchar(30) not null,
	passwort int
}