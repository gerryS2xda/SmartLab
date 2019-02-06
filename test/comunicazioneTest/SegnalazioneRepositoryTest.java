package comunicazioneTest;

import org.junit.Test;

import businessLogic.comunicazione.SegnalazioneRepository;
import dataAccess.storage.bean.Segnalazione;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

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
	public void testAdd(){
		Segnalazione res = sr.findItemByQuery(new SegnalazioneSql(oracle.getId()));
		assertEquals(res, oracle);
	}
	
	@Test
	public void testDelete(){
		sr.delete(oracle);
		Segnalazione res = sr.findItemByQuery(new SegnalazioneSql(oracle.getId()));
		assertEquals(res, null);
	}
	
	@Test
	public void testFindItemByQuery(){
		Segnalazione res = sr.findItemByQuery(new SegnalazioneSql(oracle.getId()));
		assertequals(res, oracle);
	}
	
	@Test
	public void testQuery(){
		List<Segnalazione> lista = new ArrayList<Segnalazione>();
		lista = sr.query(new ListaSegnalazioni());
		assertEquals(lista.get(lista.size() - 1), oracle);
	}
}
