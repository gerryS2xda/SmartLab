package utenteTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import businessLogic.utente.StudentList;
import businessLogic.utente.StudenteRepository;
import businessLogic.utente.StudenteSQL;
import dataAccess.storage.bean.Studente;

public class StudenteRepositoryTest {
	
	private StudenteRepository repository;
	private Studente oracle;
	
	@Before
	public void setUp() throws Exception {
		repository=StudenteRepository.getInstance();
		
		oracle = new Studente("test@unisa.studenti.it", "12345678", "Rocco", "Lo Conte");
		oracle.setStato(false);
		System.out.println(oracle);
		repository.add(oracle);
		
		oracle=repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
		repository.delete(oracle);
	}

	@Test
	public void testGetInstance() {
		System.out.println("getInstance");
        StudenteRepository result = StudenteRepository.getInstance();
        assertNotNull(result);
	}


	@Test
	public void testAdd() throws SQLException {
		System.out.println("add");
		
		Studente result=repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));

		assertEquals(oracle,result);
	}

	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		
		repository.delete(oracle);
		
		Studente result=repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));

		assertEquals("",result.getEmail());
	}
	
	@Test
	public void testUpdate() throws SQLException{
		
		Studente result=oracle;
		
		assertSame(result,oracle);
		
		result.setPassword("nuovaPass");
		
		repository.update(result);
		
		result=repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		
		assertEquals(oracle,result);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		
		Studente result=repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		
		assertEquals(oracle,result);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		
		List<Studente> lista=repository.query(new StudentList());
		
		assertEquals(lista.get(lista.size()-1),oracle);

	}

}