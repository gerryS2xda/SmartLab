import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import businessLogic.comunicazione.CommunicationManager;
import dataAccess.storage.bean.Segnalazione;

public class SegnalazioneTest {

	@Test
	public void testAddSegnalazione() {
		Segnalazione s = new Segnalazione();
		s.setId(23);
		s.setOggetto("Oggetto");
		s.setDescrizione("Descrizione");
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		s.setData(data);
		s.setStudente("test");
		s.setLaboratorio("SesaLab");
		s.setPostazione(25);
		CommunicationManager cm = new CommunicationManager();
		assertTrue(cm.addSegnalazione(s));
	}

	/*@Test
	public void testDeleteSegnalazione() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewSegnalazione() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddAvviso() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAvviso() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewAvviso() {
		fail("Not yet implemented");
	}*/

}
