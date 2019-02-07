import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.comunicazione.AvvisoRepository;
import businessLogic.comunicazione.CommunicationManager;
import businessLogic.comunicazione.SegnalazioneRepository;
import dataAccess.storage.bean.Avviso;
import dataAccess.storage.bean.Segnalazione;

public class CommunicationManagerTest2 {
	
	private CommunicationManager cm;
	private AvvisoRepository ar;
	private SegnalazioneRepository sr;
	private Avviso oracle;
	private Segnalazione orcl;

	@Before
	public void setUp() throws Exception {
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		cm = new CommunicationManager();
		ar = new AvvisoRepository();
		sr = new SegnalazioneRepository();
		oracle = null;
		orcl = null;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddSegnalazione() {
		cm.addSegnalazione(orcl);
		Segnalazione res = sr.findItemByQuery(new SegnalazioneSql(0));
		assertEquals(res.getId(), null);
	}

	@Test
	public void testDeleteSegnalazione() {
		Segnalazione res = new Segnalazione();
		cm.deleteSegnalazione(orcl);
		res = sr.findItemByQuery(new SegnalazioneSql(res.getId()));
		assertNotEquals(res.getId(), null);
	}

	@Test
	public void testViewSegnalazione() {
		List<Segnalazione> res = cm.viewSegnalazione();
		assertEquals(res, orcl);
	}

	@Test
	public void testAddAvviso() {
		cm.addAvviso(oracle);
		Avviso av = ar.findItemByQuery(new AvvisoSql(0));
		assertEquals(av, null);
	}

	@Test
	public void testDeleteAvviso() {
		Avviso av = new Avviso();
		cm.deleteAvviso(oracle);
		av = ar.findItemByQuery(new AvvisoSql(av.getId()));
		assertEquals(av, null);
	}

	@Test
	public void testViewAvviso() {
		List<Avviso> lista = cm.viewAvviso();
		assertEquals(lista, oracle);
	}

}
