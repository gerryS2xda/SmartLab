-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019

--Tabella Responsabile

DROP TABLE IF EXISTS `responsabile`;
CREATE TABLE responsabile(
    email varchar(25) PRIMARY KEY,
    password varchar(25) NOT NULL,
    nome varchar(25) NOT NULL,
    cognome varchar(25) NOT NULL,
);