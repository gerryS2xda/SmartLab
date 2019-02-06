import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import businessLogic.comunicazione.CommunicationManager;
import dataAccess.storage.bean.Segnalazione;

public class SegnalazioneTest {

	@Test
	public void testAddSegnalazione() {
		Segnalazione s = new Segnalazione();
		s.setId(26);
		s.setOggetto("Oggetto");
		s.setDescrizione("Descrizione");
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		s.setData(data);
		s.setStudente("g.laucella@studenti.unisa.it");
		s.setLaboratorio(1);
		s.setPostazione(25);
		CommunicationManager cm = new CommunicationManager();
		assertTrue(cm.addSegnalazione(s));
	}

	@Test
	public void testDeleteSegnalazione(){
		Segnalazione s = new Segnalazione();
		s.setId(24);
		s.setOggetto("Oggetto");
		s.setDescrizione("Descrizione");
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		s.setData(data);
		s.setStudente("g.laucella@studenti.unisa.it");
		s.setLaboratorio(1);
		s.setPostazione(25);
		CommunicationManager cm = new CommunicationManager();
		assertTrue(cm.deleteSegnalazione(s));
	}
	
	@Test
	public void testViewSegnalazione(){
		Segnalazione s = new Segnalazione();
		s.setId(25);
		s.setOggetto("Ogg");
		s.setDescrizione("Des");
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		s.setData(data);
		s.setStudente("g.laucella@studenti.unisa.it");
		s.setLaboratorio(1);
		s.setPostazione(25);
		List<Segnalazione> lista = new ArrayList<Segnalazione>();
		CommunicationManager cm = new CommunicationManager();
		cm.addSegnalazione(s);
		lista = cm.viewSegnalazione();
		assertTrue(!lista.isEmpty());
	}
}
