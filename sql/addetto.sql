-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019

--Tabella addetto

DROP TABLE IF EXISTS `addetto`;
CREATE TABLE addetto(
    email varchar(50) PRIMARY KEY,
    tipo boolean NOT NULL,
);