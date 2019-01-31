-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019

--Tabella studente

DROP TABLE IF EXISTS `studente`;
CREATE TABLE studente(
    email varchar(50) PRIMARY KEY,
    stato boolean NOT NULL,
);