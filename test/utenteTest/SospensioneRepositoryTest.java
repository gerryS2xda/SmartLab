package utenteTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.utente.*;
import dataAccess.storage.bean.*;

public class SospensioneRepositoryTest {
	
	private SospensioneRepository repository;
	private Sospensione oracle;
	
	@Before
	public void setUp() throws Exception {
		repository=SospensioneRepository.getInstance();
		
		oracle = new Sospensione(1, "pippo", "esempio1@unisa.it");

		System.out.println(oracle);
		repository.add(oracle);
		
		oracle=repository.findItemByQuery(new SospensioneSQL(oracle.getID()));
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
		repository.delete(oracle);
	}

	@Test
	public void testGetInstance() {
		System.out.println("getInstance");
        SospensioneRepository result = SospensioneRepository.getInstance();
        assertNotNull(result);
	}


	@Test
	public void testAdd() throws SQLException {
		System.out.println("add");
		
		Studente result=repository.findItemByQuery(new SospensioneSQL(oracle.getID()));

		assertEquals(oracle,result);
	}

	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		
		repository.delete(oracle);
		
		Sospensione result=repository.findItemByQuery(new SospensioneSQL(oracle.getID()));

		assertEquals(null,result.getID());
	}
	
	@Test
	public void testUpdate() throws SQLException{
		
		Sospensione result=oracle;
		
		assertSame(result,oracle);
		
		result.setStudente(oracle.getStudente());
		
		repository.update(result);
		
		result=repository.findItemByQuery(new SospensioneSQL(oracle.getID()));
		
		assertEquals(oracle,result);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		
		Studente result=repository.findItemByQuery(new SospensioneSQL(oracle.getID()));
		
		assertEquals(oracle,result);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		
		List<Sospensione> lista=repository.query(new SospensioneList());
		
		assertEquals(lista.get(lista.size()-1),oracle);

	}

}
