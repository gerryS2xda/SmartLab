-- Autore: Lo Conte Rocco
-- Ultima modifica: 23/01/2019

--Tabella Sospensione

DROP TABLE IF EXISTS `sospensione`;
CREATE TABLE sospensione(
    IDlaboratorio INTEGER(10) PRIMARY KEY auto_increment,
    durata INTEGER(10) NOT NULL,
    data DATE() NOT NULL, /** devo mettere un numero tra parantesi? Non so come funziona **/
    motivazione varchar(100),
    studente varchar(25) NOT NULL,
    addetto varchar(25) NOT NULL
);