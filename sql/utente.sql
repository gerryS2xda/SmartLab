-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019

--Tabella utente

DROP TABLE IF EXISTS `utente`;
CREATE TABLE utente(
    email varchar(50) PRIMARY KEY,
    password varchar(16) NOT NULL,
    nome varchar(20) NOT NULL,
    cognome varchar(20) NOT NULL,
);