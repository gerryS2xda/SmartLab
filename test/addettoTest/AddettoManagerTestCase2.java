package addettoTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.addetto.*;
import dataAccess.storage.bean.Addetto;
import dataAccess.storage.bean.Studente;
import dataAccess.storage.bean.Utente;

public class AddettoManagerTestCase2 {
	
	private AddettoManager manager;
	private AddettoRepository repository;
	private Addetto oracle;

	@Before
	public void setUp() throws Exception {
		manager=AddettoManager.getInstance();
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
		
		Studente result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));
		
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

