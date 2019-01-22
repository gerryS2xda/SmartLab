drop table if exists 'avviso'

create table avviso(
	idAvviso int(10),
	titolo varchar(40),
	messaggio varchar(200),
	data date,
	addetto int(25),
	primary key(idAvviso),
	foreign key(addetto) references(addetto.idAddetto)
	);