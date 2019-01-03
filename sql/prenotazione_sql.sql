-- Autore: Gerardo Michele Laucella
-- Ultima modifica: 28/12/2018

--
-- Table structure for table `prenotazione`
--
DROP TABLE IF EXISTS `prenotazione`;
CREATE TABLE Prenotazione(
	IDprenotazione INTEGER(10) PRIMARY KEY auto_increment,
    data DATE  NOT NULL,
    fascia_oraria TIME NOT NULL,
    stato boolean NOT NULL,
    studente VARCHAR(50),
    postazione smallint,
    laboratorio tinyint
);

/* 
   studente e postazioni sono chiavi esterne 
   studente: foreign key (studente) references Studente(studente) on update cascade on delete set null
   postazione: foreign key (postazione) references Postazione(numero) on update cascade on delete set null
   laboratorio: foreign key (laboratorio) references Laboratorio(IDlaboratorio) on update cascade on delete set null
   funzione TIME(7) errore, poiche' 7 troppo grande --> rimuovere 7
   postazione numero --> INT troppo grande, sostituire con Numeric(4)
   incrementare la lunghezza della stinga di studente (almeno 100 caratteri)
   BIT(1) come tipo di dato deve avere il numero di bit tra () --> usiamo boolean?
   Rif: https://dev.mysql.com/doc/refman/8.0/en/bit-type.html
*/

INSERT INTO prenotazione(data, fascia_oraria, stato, studente, postazione, laboratorio)
	VALUES ('2018-12-28', '09:00', true, 'g.laucella@studenti.unisa.it', 120, 2); 
INSERT INTO prenotazione(data, fascia_oraria, stato, studente, postazione, laboratorio)
	VALUES ('2018-12-28', '11:00', true, 'g.laucella@studenti.unisa.it', 120, 2);
