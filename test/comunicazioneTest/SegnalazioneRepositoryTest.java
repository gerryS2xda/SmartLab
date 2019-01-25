package comunicazioneTest;

import org.junit.Test;

import businessLogic.comunicazione.SegnalazioneRepository;
import dataAccess.storage.bean.Segnalazione;

import static org.junit.Assert.*;

import java.util.List;

public class SegnalazioneRepositoryTest {
	
	@Test
	public void testAdd(){
		Segnalazione sg = new Segnalazione(20, "Oggetto", "Descrizione", new Data(), 12, "SesaLab", 23);
		SegnalazioneRepository sr = new SegnalazioneRepository();
		sr.add(sg);
		SegnalazioneSql segnalazione = new SegnalazioneSql(sg.getId());
		Segnalazione ris = sr.findItemByQuery(segnalazione);
		assertEquals(sg, ris);
		ar.delete(sg);
	}
	
	@Test
	public void testDelete(){
		Segnalazione sg = new Segnalazione(20, "Oggetto", "Descrizione", new Data(), 12, "SesaLab", 23);
		SegnalazioneRepository sr = new SegnalazioneRepository();
		sr.add(sg);
		SegnalazioneSql segnalazione = new SegnalazioneSql(sg.getId());
		sr.delete(sg);
		Segnalazione ris = sr.findItemByQuery(segnalazione);
		assertEquals(null, ris);
	}
	
	@Test
	public void testFindItemByQuery(){
		Segnalazione sg = new Segnalazione(20, "Oggetto", "Descrizione", new Data(), 12, "SesaLab", 23);
		SegnalazioneRepository sr = new SegnalazioneRepository();
		sr.add(sg);
		SegnalazioneSql segnalazione = new SegnalazioneSql(sg.getId());
		Segnalazione ris = sr.findItemByQuery(segnalazione);
		assertEquals(sg, ris);
	}
	
	@Test
	public void testQuery(){
		Segnalazione sg = new Segnalazione(20, "Oggetto", "Descrizione", new Data(), 12, "SesaLab", 23);
		SegnalazioneRepository sr = new SegnalazioneRepository();
		sr.add(sg);
		List<Segnalazione> lista = new ArrayList<Segnalazione>();
		lista = sr.query(new ListaSegnalazioni());
		assertTrue(!lista.isEmpty());
	}
}
