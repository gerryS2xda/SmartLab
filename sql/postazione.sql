-- Autore: Antonio Cerasuolo
-- Ultima modifica: 30/01/2019

--Tabella Laboratorio

DROP TABLE IF EXISTS `postazione`;
CREATE TABLE laboratorio(
    numero INTEGER(10) PRIMARY KEY auto_increment,
   	laboratorio INTEGER(10),
   	stato boolean NOT NULL
);