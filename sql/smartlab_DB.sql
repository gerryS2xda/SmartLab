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

INSERT INTO `utente` VALUES ('antonio@unisa.it','123asd','Antonio','Cerasuolo'),('f.somma@unisa.it','123asd','Fulvio','Somma'),('g.laucella@studenti.unisa.it','123asd','Gerardo','Laucella'),('g.paolisi@unisa.it','123asd','Giuseppe','Paolisi'),('r.loconte@unisa.it','123asd','Rocco','Lo Conte');


-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019
-- Tabella studente
DROP TABLE IF EXISTS `studente`;
CREATE TABLE studente(
    email VARCHAR(50) NOT NULL,
    stato BOOLEAN NOT NULL,
	PRIMARY KEY(email),
	UNIQUE(email),
	FOREIGN KEY (email) REFERENCES utente(email) ON DELETE CASCADE
);

INSERT INTO `studente` VALUES ('g.laucella@studenti.unisa.it',1);

-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019
-- Tabella addetto
DROP TABLE IF EXISTS `addetto`;
CREATE TABLE addetto(
    email VARCHAR(50) NOT NULL,
    tipo BOOLEAN NOT NULL,
	PRIMARY KEY(email),
	UNIQUE(email),
	FOREIGN KEY (email) REFERENCES utente(email) ON DELETE CASCADE
);

INSERT INTO `addetto` VALUES ('antonio@unisa.it',0),('f.somma@unisa.it',0),('g.paolisi@unisa.it',1),('r.loconte@unisa.it',1);


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
	
-- Autore: Antonio Cerasuolo
-- Ultima modifica: 30/01/2019
-- Tabella postazione
DROP TABLE IF EXISTS `postazione`;
CREATE TABLE postazione(
    numero TINYINT(4) auto_increment,
   	laboratorio INTEGER(10) REFERENCES laboratorio(IDlaboratorio)
		ON DELETE SET NULL
		ON UPDATE CASCADE,
   	stato BOOLEAN NOT NULL,
	PRIMARY KEY (numero, laboratorio)
);

INSERT INTO `postazione` VALUES (1,0,1),(1,1,1),(1,2,1),(2,1,1),(2,2,1),(3,1,1),(3,2,1),(4,1,1),(4,2,1),(5,1,1),(5,2,1),(6,1,1),(6,2,1),(7,1,1),(7,2,1),(8,1,1),(8,2,1),(9,1,1),(9,2,1),(10,1,1),(10,2,1),(11,1,1),(11,2,1),(12,1,1),(12,2,1),(13,1,1),(13,2,1),(14,1,1),(14,2,1),(15,1,1),(15,2,1),(16,1,1),(16,2,1),(17,1,1),(17,2,1),(18,1,1),(18,2,1),(19,1,1),(19,2,1),(20,1,1),(20,2,1),(21,1,1),(21,2,1),(22,1,1),(22,2,1),(23,1,1),(23,2,1),(24,1,1),(24,2,1),(25,1,1),(25,2,1),(26,1,1),(26,2,1),(27,1,1),(27,2,1),(28,1,1),(28,2,1),(29,1,1),(29,2,1),(30,1,1),(30,2,1),(31,1,1),(31,2,1),(32,1,1),(32,2,1),(33,1,1),(33,2,1),(34,1,1),(34,2,1),(35,1,1),(35,2,1),(36,1,1),(36,2,1),(37,1,1),(37,2,1),(38,1,1),(38,2,1),(39,1,1),(39,2,1),(40,1,1),(40,2,1),(41,1,1),(41,2,1),(42,1,1),(42,2,1),(43,1,1),(43,2,1),(44,1,1),(44,2,1),(45,1,1),(45,2,1),(46,1,1),(46,2,1),(47,1,1),(47,2,1),(48,1,1),(48,2,1),(49,1,1),(49,2,1),(50,1,1),(50,2,1),(51,2,1),(52,2,1),(53,2,1),(54,2,1),(55,2,1),(56,2,1),(57,2,1),(58,2,1),(59,2,1),(60,2,1);


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

INSERT INTO `laboratorio` VALUES (1,'Sistemi',50,1,'09:00:00','17:00:00'),(2,'Reti',60,1,'09:00:00','17:00:00');

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

INSERT INTO `assegnamento` VALUES (1,'antonio@unisa.it'),(2,'f.somma@unisa.it');

-- Autore: Fulvio Somma
-- Ultima modifica: -
-- Tabella avviso
DROP TABLE IF EXISTS `avviso`;
CREATE TABLE avviso(
	idAvviso INTEGER(10) PRIMARY KEY auto_increment,
	titolo VARCHAR(20) NOT NULL,
	messaggio VARCHAR(255),
	data DATE NOT NULL,
	addetto VARCHAR(25) REFERENCES addetto(email)
		ON DELETE SET NULL
		ON UPDATE CASCADE
);

INSERT INTO `avviso` VALUES (1,'Avviso chiusura','Oggi il laboratorio Sistemi e'' chiuso','2019-02-03','antonio@unisa.it'),(2,'Avviso apertura','Oggi il laboratorio Reti apre alle 13','2019-02-03','antonio@unisa.it');

-- Autore: Fulvio Somma
-- Ultima modifica: -
-- Tabella segnalazione
DROP TABLE IF EXISTS `segnalazione`;
CREATE TABLE segnalazione(
	idSegnalazione INTEGER(10) PRIMARY KEY auto_increment,
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
