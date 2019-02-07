package utenteTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import businessLogic.utente.StudenteRepository;
import businessLogic.utente.StudenteSQL;
import businessLogic.utente.UtenteManager;
import dataAccess.storage.bean.Studente;

public class UtenteManagerTestCase2 {
	
	private UtenteManager manager;
	private StudenteRepository repository;
	private Studente oracle;


	@Before
	public void setUp() throws Exception {
		oracle=new Studente();
		oracle.setEmail("esempio1@unisa.studenti.it");
		oracle.setName("Rocco");
		oracle.setStato(false);
		oracle.setPassword("password");
		oracle.setSurname("Lo Conte");
		
		manager.registraStudente(oracle);
	}

	@After
	public void tearDown() throws Exception {
		oracle=repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		repository.delete(oracle);
	}

	@Test
	public void testRegistraStudente() throws SQLException {
		System.out.println("Test registraStudente con parametro null");
		
		Studente result=repository.findItemByQuery(new StudenteSQL(""));
		assertEquals(result.getEmail(),"");
	}

	@Test
	public void testEffettuaAutenticazione() throws SQLException {
		System.out.println("Test effettuaAutenticazione con parametro null");
		Studente result = manager.effettuaAutenticazione(oracle.getEmail(), oracle.getPassword());
		assertEquals(result.getEmail(),"");
	}

	@Test
	public void testIsStudentPresente() throws SQLException{
		System.out.println("Test isStudentPresente con parametro null");
		
		boolean b = manager.isStudentPresente(oracle.getEmail());
		Studente result = repository.findItemByQuery(new StudenteSQL(""));
		
		assertEquals(b, false);
		
	}
	
	@Test
	public void testGetStudentList() throws SQLException{
		System.out.println("Test getStudentList con parametro null");
		
		List<Studente> studenti = manager.getStudentList();
		Studente result = repository.findItemByQuery(new StudenteSQL(""));
		
		assertTrue(studenti.isEmpty());
		
	}
	
	@Test
	public void testEditPassword()throws SQLException{
		System.out.println("Test getLaboratoryListForResp con parametro null");
		
		manager.editPassword(oracle.getEmail(), "");
		Studente result = repository.findItemByQuery(new StudenteSQL(""));
		
		assertEquals(oracle.getPassword(), result.getPassword());
	}
}