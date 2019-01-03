-- Autore: Gerardo Michele Laucella
-- Ultima modifica: 28/12/2018

--
-- Table structure for table `prenotazione`
--
DROP TABLE IF EXISTS `prenotazione`;
CREATE TABLE Prenotazione(
	IDprenotazione INTEGER(10) PRIMARY KEY auto_increment,
    data DATE  NOT NULL,
    fascia_oraria VARCHAR(30) NOT NULL,
    stato boolean NOT NULL,
    studente VARCHAR(50),
    postazione smallint,
    laboratorio tinyint
);

INSERT INTO prenotazione(data, fascia_oraria, stato, studente, postazione, laboratorio)
	VALUES ('2018-12-28', '9 - 11', true, 'g.laucella@studenti.unisa.it', 120, 2); 
INSERT INTO prenotazione(data, fascia_oraria, stato, studente, postazione, laboratorio)
	VALUES ('2018-12-28', '11 - 13', true, 'g.laucella@studenti.unisa.it', 120, 2);
