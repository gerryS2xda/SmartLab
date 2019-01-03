-- Autore: Giuseppe Paolisi
-- Ultima modifica: 31/12/2018

--Tabella assegnamento

DROP TABLE IF EXISTS `assegnamento`;
CREATE TABLE assegnamento(
    laboratorio integer(10),
    responsabile varchar(50),

    primary key(laboratorio,responsabile),
    FOREIGN KEY (laboratorio) REFERENCES(laboratorio.IDlaboratorio),
    FOREIGN KEY (responsabile) REFERENCES(addetto.addetto)
);