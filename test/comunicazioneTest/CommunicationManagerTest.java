package comunicazioneTest;

import businessLogic.comunicazione.CommunicationManager;
import dataAccess.storage.bean.Avviso;
import dataAccess.storage.bean.Segnalazione;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class CommunicationManagerTest {
	
	@Test
	public void testaddSegnalazione(){
		Segnalazione s = new Segnalazione();
		s.setId(23);
		s.setOggetto("Oggetto");
		s.setDescrizione("Descrizione");
		s.setData(new Data());
		s.setStudente(12);
		s.setLaboratorio("SesaLab");
		s.setPostazione(25);
		CommunicationManager cm = new CommunicationManager();
		assertTrue(cm.addSegnalazione(s));
	}
	
	@Test
	public void testDeleteSegnalazione(){
		Segnalazione s = new Segnalazione();
		s.setId(23);
		s.setOggetto("Oggetto");
		s.setDescrizione("Descrizione");
		s.setData(new Data());
		s.setStudente(12);
		s.setLaboratorio("SesaLab");
		s.setPostazione(25);
		CommunicationManager cm = new CommunicationManager();
		assertTrue(cm.deleteSegnalazione(s));
	}
	
	@Test
	public void testViewSegnalazione(){
		Segnalazione s = new Segnalazione();
		s.setId(23);
		s.setOggetto("Oggetto");
		s.setDescrizione("Descrizione");
		s.setData(new Data());
		s.setStudente(12);
		s.setLaboratorio("SesaLab");
		s.setPostazione(25);
		List<Segnalazione> lista = new ArrayList<Segnalazione>();
		CommunicationManager cm = new CommunicationManager();
		cm.addSegnalazione(s);
		lista = cm.viewSegnalazione();
		assertTrue(!lista.isEmpty());
	}
	
	@Test
	public void testAddAvviso(){
		Avviso a = new Avviso();
		a.setId(10);
		a.setTitolo("Titolo");
		a.setMessaggio("Messaggio");
		a.setData(new Data());
		a.setAddetto(34);
		CommunicationManager cm = new CommunicationManager();
		assertTrue(cm.addAvviso(a));
	}
	
	@Test
	public void testDeleteAvviso(){
		Avviso a = new Avviso();
		a.setId(10);
		a.setTitolo("Titolo");
		a.setMessaggio("Messaggio");
		a.setData(new Data());
		a.setAddetto(34);
		CommunicationManager cm = new CommunicationManager();
		assertTrue(cm.deleteAvviso(a));
	}
	
	@Test
	public void testViewAvviso(){
		Avviso a = new Avviso();
		a.setId(10);
		a.setTitolo("Titolo");
		a.setMessaggio("Messaggio");
		a.setData(new Data());
		a.setAddetto(34);
		CommunicationManager cm = new CommunicationManager();
		cm.addAvviso(a);
		List<Avviso> lista = new ArrayList<Avviso>();
		lista = cm.viewAvviso();
		assertTrue(!lista.isEmpty());
	}
}
