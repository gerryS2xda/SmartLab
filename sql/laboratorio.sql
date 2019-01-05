-- Autore: Giuseppe Paolisi
-- Ultima modifica: 31/12/2018

--Tabella Laboratorio

DROP TABLE IF EXISTS `laboratorio`;
CREATE TABLE laboratorio(
    IDlaboratorio INTEGER(10) PRIMARY KEY auto_increment,
    nome varchar(25) NOT NULL,
    posti INTEGER(10) NOT NULL,
    stato boolean NOT NULL,
    ora_apertura TIME NOT NULL,
    ora_chiusura TIME NOT NULL
);