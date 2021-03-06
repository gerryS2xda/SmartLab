package utenteTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import dataAccess.storage.bean.Sospensione;

public class UtenteManagerTestCase1 {
	
	private UtenteManager manager;
	private StudenteRepository repository;
	private Studente oracle;

	@Before
	public void setUp() throws Exception {
		manager=UtenteManager.getInstace();
		repository=StudenteRepository.getInstance();
		
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
    public void testGetInstance() {
        System.out.println("getInstance");
        StudenteRepository result = StudenteRepository.getInstance();
        assertNotNull(result);
        
	}
	
	@Test
	public void testRegistraStudente() throws SQLException{
		System.out.println("registraStudente");
		
		Studente result=repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		
		assertEquals(result,oracle);
	}
	
	@Test
	public void testEffettuaAutenticazione() throws SQLException{
		System.out.println("effettuaAutenticazione");
		
		Studente result = manager.effettuaAutenticazione(oracle.getEmail(), oracle.getPassword());
		assertEquals(oracle.getEmail(),result.getEmail());
	}
	
	@Test
	public void testEffettuaSospensione() throws SQLException{
		System.out.println("effettuaSospensione");
		
		Sospensione s = manager.effettuaSospensione(oracle, "pippo");
		Studente result = repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		
		assertEquals(oracle.getStato(), result.getStato());
		
	}
	
	@Test
	public void testIsStudentPresente() throws SQLException{
		System.out.println("isStudentPresente");
		
		boolean b = manager.isStudentPresente(oracle.getEmail());
		Studente result = repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		
		assertEquals(b, true);
		
	}
	
	@Test
	public void testGetStudentList() throws SQLException{
		System.out.println("getStudentList");
		
		List<Studente> studenti = manager.getStudentList();
		Studente result = repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		
		assertTrue(!studenti.isEmpty());
		
	}
	
	@Test
	public void testEditPassword()throws SQLException{
		System.out.println("getLaboratoryListForResp");
		
		manager.editPassword(oracle.getEmail(), "nuovaPassword");
		Studente result = repository.findItemByQuery(new StudenteSQL(oracle.getEmail())); 
		
		assertEquals(oracle.getPassword(), result.getPassword());
	}

}
