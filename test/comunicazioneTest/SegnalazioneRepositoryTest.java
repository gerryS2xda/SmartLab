package comunicazioneTest;

import businessLogic.comunicazione.ListaSegnalazioni;
import businessLogic.comunicazione.SegnalazioneRepository;
import businessLogic.comunicazione.SegnalazioneSql;
import dataAccess.storage.bean.Segnalazione;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SegnalazioneRepositoryTest {
	
	private SegnalazioneRepository sr;
	private Segnalazione oracle;
	
	@Before
	public void setUp() throws SQLException{
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		oracle = new Segnalazione();
		oracle.setId(15);
		oracle.setDescrizione("Descrizione test");
		oracle.setOggetto("Oggetto test");
		oracle.setData(data);
		oracle.setLaboratorio("SesaLab");
		oracle.setPostazione(16);
		oracle.setStudente("test");
	}
	
	@After
	public void tearDown() throws SQLException{
		sr.delete(oracle);
	}
	
	@Test
	public void testAdd()throws SQLException{
		Segnalazione res = sr.findItemByQuery(new SegnalazioneSql(oracle.getId()));
		assertEquals(res, oracle);
	}
	
	@Test
	public void testDelete()throws SQLException{
		sr.delete(oracle);
		Segnalazione res = sr.findItemByQuery(new SegnalazioneSql(oracle.getId()));
		assertEquals(res, null);
	}
	
	@Test
	public void testFindItemByQuery()throws SQLException{
		Segnalazione res = sr.findItemByQuery(new SegnalazioneSql(oracle.getId()));
		assertEquals(res, oracle);
	}
	
	@Test
	public void testQuery()throws SQLException{
		List<Segnalazione> lista = new ArrayList<Segnalazione>();
		lista = sr.query(new ListaSegnalazioni());
		assertEquals(lista.get(lista.size() - 1), oracle);
	}
}
