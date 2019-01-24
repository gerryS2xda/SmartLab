DROP table if exists 'segnalazione'

create table segnalazione(
	idSegnalazione int(20),
	oggetto varchar(40),
	descrizione varchar(200),
	data date,
	studente int(20),
	laboratorio varchar(20),
	postazione int(20),
	primary key (idSegnalazione),
	foreign key(laboratorio) references(laboratorio.IDlaboratorio),
	foreign key(postazione) references(postazione.numero)
	);