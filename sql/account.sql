-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019

--Tabella Account

DROP TABLE IF EXISTS `account`;
CREATE TABLE account(
    email varchar(25) PRIMARY KEY,
    password varchar(25) NOT NULL,
    nome varchar(25) NOT NULL,
    cognome varchar(25) NOT NULL,
    /** devo aggiungere stato? **/
);