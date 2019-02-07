package addettoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.addetto.AddettoManager;
import businessLogic.addetto.AddettoRepository;
import businessLogic.addetto.AddettoSQL;
import dataAccess.storage.bean.Addetto;

public class AddettoManagerTestCase2 {

	private AddettoManager manager;
	private AddettoRepository repository;
	private Addetto oracle;

	@Before
	public void setUp() throws Exception {
		manager=AddettoManager.getInstace();
		repository=AddettoRepository.getInstance();
		oracle=null;
		oracle.setEmail("");
		boolean b = manager.addResp(oracle);
	}

	@After
	public void tearDown() throws Exception {
		oracle=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));
		repository.delete(oracle);
	}

	@Test
	public void testAddResp() throws SQLException{
		System.out.println("addResp null");

		Addetto result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));

		assertEquals(result.getEmail(),"");
	}

	@Test
	public void testEffettuaAutenticazione() throws SQLException{
		System.out.println("effettuaAutenticazione null");
		Addetto result=new Addetto();
		result = manager.effettuaAutenticazione(oracle.getEmail(), oracle.getPassword());
		assertEquals("",result.getEmail());
	}

	@Test
	public void testRimuoviResp() throws SQLException{
		System.out.println("rimuoviResp null");

		boolean b = manager.rimuoviResp(oracle);
		Addetto result = repository.findItemByQuery(new AddettoSQL(""));

		assertEquals(b, false);

	}

	@Test
	public void testGetListaResp() throws SQLException{
		System.out.println("getListaResp null");

		List<Addetto> responsabili = manager.getListaResp();
		Addetto result = repository.findItemByQuery(new AddettoSQL(""));

		assertTrue(responsabili.isEmpty());

	}
}

