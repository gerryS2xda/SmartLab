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
import dataAccess.storage.bean.Utente;

public class AddettoRepositoryTest {
	
	private StudenteRepository repository;
	private Studente oracle;
	
	@Before
	public void setUp() throws Exception {
		repository=AddettoRepository.getInstance();
		
		oracle = new Addetto("test@unisa.studenti.it", "12345678", "Rocco", "Lo Conte");
		oracle.setTipo(false);
		System.out.println(oracle);
		repository.add(oracle);
		
		oracle=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
		repository.delete(oracle);
	}

	@Test
	public void testGetInstance() {
		System.out.println("getInstance");
		AddettoRepository result = AddettoRepository.getInstance();
        assertNotNull(result);
	}


	@Test
	public void testAdd() throws SQLException {
		System.out.println("add");
		
		Studente result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));

		assertEquals(oracle,result);
	}

	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		
		repository.delete(oracle);
		
		Studente result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));

		assertEquals("",result.getEmail());
	}
	
	@Test
	public void testUpdate() throws SQLException{
		
		Studente result=oracle;
		
		assertSame(result,oracle);
		
		result.setPassword("nuovaPass");
		
		repository.update(result);
		
		result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));
		
		assertEquals(oracle,result);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		
		Studente result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));
		
		assertEquals(oracle,result);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		
		List<Addetto> lista=repository.query(new AddettoList());
		
		assertEquals(lista.get(lista.size()-1),oracle);

	}

}