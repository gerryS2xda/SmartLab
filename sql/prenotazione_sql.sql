-- Autore: Gerardo Michele Laucella
-- Ultima modifica: 28/12/2018

--
-- Table structure for table `prenotazione`
--
DROP TABLE IF EXISTS `prenotazione`;
CREATE TABLE Prenotazione(
	IDprenotazione INTEGER(10) PRIMARY KEY auto_increment,
    data DATE  NOT NULL,
    ora_inizio Time NOT NULL,
    ora_fine Time NOT NULL,
    stato boolean NOT NULL,
    studente VARCHAR(50),
    postazione smallint,
    laboratorio tinyint
);

INSERT INTO prenotazione(data, ora_inizio, ora_fine, stato, studente, postazione, laboratorio)
	VALUES ('2018-12-28', '9:00', '11:00', true, 'g.laucella@studenti.unisa.it', 120, 2); 
INSERT INTO prenotazione(data, ora_inizio, ora_fine, stato, studente, postazione, laboratorio)
	VALUES ('2018-12-28', '11:00', '13:00', true, 'g.laucella@studenti.unisa.it', 120, 2)
