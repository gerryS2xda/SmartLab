-- Autore: Antonio Cerasuolo
-- Ultima modifica: 30/01/2019

--Tabella Laboratorio

DROP TABLE IF EXISTS `laboratorio`;
CREATE TABLE laboratorio(
    idIntervento INTEGER(10) PRIMARY KEY auto_increment,
   	descrizione varchar(250) NOT NULL,
    data DATE NOT NULL,
   	postazione INTEGER(10),
   	laboratorio INTEGER(10),
   	addetto varchar(25) NOT NULL
);