package comunicazioneTest;

import businessLogic.comunicazione.AvvisoRepository;
import businessLogic.comunicazione.AvvisoSql;
import businessLogic.comunicazione.CommunicationManager;
import businessLogic.comunicazione.SegnalazioneRepository;
import businessLogic.comunicazione.SegnalazioneSql;
import dataAccess.storage.bean.Avviso;
import dataAccess.storage.bean.Segnalazione;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CommunicationManagerTest1 {
	
	private CommunicationManager cm;
	private AvvisoRepository ar;
	private SegnalazioneRepository sr;
	private Avviso oracle;
	private Segnalazione orcl;
	
	@Before
	public void setUp() throws SQLException{
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		cm = new CommunicationManager();
		ar = new AvvisoRepository();
		sr = new SegnalazioneRepository();
		oracle = new Avviso();
		orcl = new Segnalazione();
		oracle.setId(17);
		oracle.setMessaggio("Messaggio avviso");
		oracle.setTitolo("Titolo avviso");
		oracle.setData(data);
		oracle.setAddetto("test");
		orcl.setId(23);
		orcl.setData(data);
		orcl.setDescrizione("Descrizione segnalazione");
		orcl.setOggetto("Oggetto segnalazione");
		orcl.setLaboratorio("SesaLab");
		orcl.setPostazione(12);
		orcl.setStudente("test");
		cm.addAvviso(oracle);
		cm.addSegnalazione(orcl);
	}
	
	@After
	public void tearDown() throws SQLException{
		ar.delete(oracle);
		sr.delete(orcl);
	}
	
	@Test
	public void testaddSegnalazione()throws SQLException{
		Segnalazione res = sr.findItemByQuery(new SegnalazioneSql(orcl.getId()));
		assertEquals(res, orcl);
	}
	
	@Test
	public void testDeleteSegnalazione()throws SQLException{
		cm.deleteSegnalazione(orcl);
		Segnalazione res = sr.findItemByQuery(new SegnalazioneSql(orcl.getId()));
		assertEquals(res, null);
	}
	
	@Test
	public void testViewSegnalazione()throws SQLException{
		List<Segnalazione> lista = new ArrayList<Segnalazione>();
		lista = cm.viewSegnalazione();
		assertEquals(lista.get(lista.size() - 1), orcl);
	}
	
	@Test
	public void testAddAvviso()throws SQLException{
		Avviso rs = ar.findItemByQuery(new AvvisoSql(oracle.getId()));
		assertEquals(rs, oracle);
	}
	
	@Test
	public void testDeleteAvviso()throws SQLException{
		cm.deleteAvviso(oracle);
		Avviso rs = ar.findItemByQuery(new AvvisoSql(oracle.getId()));
		assertEquals(rs, null);
	}
	
	@Test
	public void testViewAvviso()throws SQLException{
		List<Avviso> lista = new ArrayList<Avviso>();
		lista = cm.viewAvviso();
		assertEquals(lista.get(lista.size() - 1), oracle);
	}
}
