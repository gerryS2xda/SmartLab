-- Autore: Gruppo N
-- Ultima modifica: 30/01/2019
-- Database name: smartlab

CREATE DATABASE  IF NOT EXISTS `smartlab`;
USE `smartlab`;

-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019
-- Tabella utente
DROP TABLE IF EXISTS `utente`;
CREATE TABLE utente(
    email varchar(50) PRIMARY KEY,
    password varchar(16) NOT NULL,
    nome varchar(20) NOT NULL,
    cognome varchar(20) NOT NULL
);

-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019
-- Tabella studente
DROP TABLE IF EXISTS `studente`;
CREATE TABLE studente(
    email VARCHAR(50) REFERENCES utente(email)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
    stato BOOLEAN NOT NULL,
	PRIMARY KEY(email),
	UNIQUE(email)
);

-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019
-- Tabella addetto
DROP TABLE IF EXISTS `addetto`;
CREATE TABLE addetto(
    email VARCHAR(50) REFERENCES utente(email)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
    tipo BOOLEAN NOT NULL,
	PRIMARY KEY(email),
	UNIQUE(email)
);


-- Autore: Gerardo Michele Laucella
-- Ultima modifica: 28/12/2018
-- Table structure for table `prenotazione`
DROP TABLE IF EXISTS `prenotazione`;
CREATE TABLE Prenotazione(
	IDprenotazione INTEGER(10) PRIMARY KEY auto_increment,
    data DATE  NOT NULL,
    ora_inizio Time NOT NULL,
    ora_fine Time NOT NULL,
    stato boolean NOT NULL,
    studente VARCHAR(50) REFERENCES studente(email)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
    postazione TINYINT(4) REFERENCES postazione(numero)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
    laboratorio INTEGER(10) REFERENCES laboratorio(IDlaboratorio)
		ON DELETE SET NULL
		ON UPDATE CASCADE
);

INSERT INTO prenotazione(data, ora_inizio, ora_fine, stato, studente, postazione, laboratorio)
	VALUES ('2018-12-28', '9:00', '11:00', true, 'g.laucella@studenti.unisa.it', 120, 2); 
INSERT INTO prenotazione(data, ora_inizio, ora_fine, stato, studente, postazione, laboratorio)
	VALUES ('2018-12-28', '11:00', '13:00', true, 'g.laucella@studenti.unisa.it', 120, 2);
	
-- Autore: Antonio Cerasuolo
-- Ultima modifica: 30/01/2019
-- Tabella postazione
DROP TABLE IF EXISTS `postazione`;
CREATE TABLE postazione(
    numero TINYINT(4) PRIMARY KEY auto_increment,
   	laboratorio INTEGER(10) REFERENCES laboratorio(IDlaboratorio)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
   	stato BOOLEAN NOT NULL
);

-- Autore: Antonio Cerasuolo
-- Ultima modifica: 30/01/2019
-- Tabella Laboratorio
DROP TABLE IF EXISTS `intervento`;
CREATE TABLE intervento(
    IDintervento INTEGER(10) PRIMARY KEY auto_increment,
   	descrizione VARCHAR(250) NOT NULL,
    data DATE NOT NULL,
   	postazione TINYINT(4) REFERENCES postazione(numero)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
   	laboratorio INTEGER(10) REFERENCES laboratorio(IDlaboratorio)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
   	addetto VARCHAR(25) NOT NULL
);


-- Autore: Giuseppe Paolisi
-- Ultima modifica: 31/12/2018
-- Tabella Laboratorio
DROP TABLE IF EXISTS `laboratorio`;
CREATE TABLE laboratorio(
    IDlaboratorio INTEGER(10) PRIMARY KEY auto_increment,
    nome VARCHAR(25) NOT NULL,
    posti INTEGER(10) NOT NULL,
    stato BOOLEAN NOT NULL,
    ora_apertura TIME NOT NULL,
    ora_chiusura TIME NOT NULL
);

-- Autore: Giuseppe Paolisi
-- Ultima modifica: 31/12/2018
-- Tabella assegnamento
DROP TABLE IF EXISTS `assegnamento`;
CREATE TABLE assegnamento(
    laboratorio INTEGER(10) REFERENCES laboratorio(IDlaboratorio)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
    responsabile VARCHAR(50) REFERENCES addetto(email)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
    PRIMARY KEY(laboratorio, responsabile),
	UNIQUE(laboratorio, responsabile)
);


-- Autore: Fulvio Somma
-- Ultima modifica: -
-- Tabella avviso
DROP TABLE IF EXISTS `avviso`;
CREATE TABLE avviso(
	idAvviso INTEGER(10) PRIMARY KEY,
	titolo VARCHAR(20) NOT NULL,
	messaggio VARCHAR(255),
	data DATE NOT NULL,
	addetto VARCHAR(25) REFERENCES addetto(email)
		ON DELETE SET NULL
		ON UPDATE CASCADE
);

-- Autore: Fulvio Somma
-- Ultima modifica: -
-- Tabella segnalazione
DROP TABLE IF EXISTS `segnalazione`;
CREATE TABLE segnalazione(
	idSegnalazione INTEGER(10) PRIMARY KEY,
	oggetto VARCHAR(50),
	descrizione VARCHAR(255),
	data DATE NOT NULL,
	postazione TINYINT(4) REFERENCES postazione(numero)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
	laboratorio INTEGER(10) REFERENCES laboratorio(IDlaboratorio)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
	studente VARCHAR(50) REFERENCES studente(email)
		ON DELETE SET NULL
		ON UPDATE CASCADE
);

-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019
-- Tabella Sospensione
DROP TABLE IF EXISTS `sospensione`;
CREATE TABLE sospensione(
    IDsospensione INTEGER(10) PRIMARY KEY auto_increment,
    durata SMALLINT(5) NOT NULL,
    data DATE NOT NULL, 
    motivazione VARCHAR(255),
    studente VARCHAR(50) REFERENCES studente(email)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
    addetto VARCHAR(50) REFERENCES addetto(email)
		ON DELETE SET NULL
		ON UPDATE CASCADE
);

