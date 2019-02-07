package addettoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

public class AddettoManagerTestCase1 {

	private AddettoManager manager;
	private AddettoRepository repository;
	private Addetto oracle;

	@Before
	public void setUp() throws Exception {
		manager = AddettoManager.getInstace();
		repository=AddettoRepository.getInstance();

		oracle=new Addetto();
		oracle.setEmail("esempio1@unisa.studenti.it");
		oracle.setName("Rocco");
		oracle.setTipo(false);
		oracle.setPassword("password");
		oracle.setSurname("Lo Conte");

		boolean b = manager.addResp(oracle);
	}

	@After
	public void tearDown() throws Exception {
		oracle=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));
		repository.delete(oracle);
	}

	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        AddettoRepository result = AddettoRepository.getInstance();
        assertNotNull(result);

	}

	@Test
	public void testAddResp() throws SQLException{
		System.out.println("addResp");

		Addetto result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));

		assertEquals(result,oracle);
	}

	@Test
	public void testEffettuaAutenticazione() throws SQLException{
		System.out.println("effettuaAutenticazione");

		Addetto result = manager.effettuaAutenticazione(oracle.getEmail(), oracle.getPassword());

		assertEquals(oracle.getEmail(),result.getEmail());
	}

	@Test
	public void testRimuoviResp() throws SQLException{
		System.out.println("rimuoviResp");

		boolean b = manager.rimuoviResp(oracle);
		Addetto result = repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));

		assertEquals(b, true);

	}

	@Test
	public void testGetListaResp() throws SQLException{
		System.out.println("getListaResp");

		List<Addetto> responsabili = manager.getListaResp();
		Addetto result = repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));

		assertTrue(!responsabili.isEmpty());

	}
}
