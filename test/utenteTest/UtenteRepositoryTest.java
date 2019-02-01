package accountTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

public class UtenteRepositoryTest {
	
	@Test
	public void testGetInstance(){
		System.out.println("getInstance");
		UtenteRepository result = UtenteRepository.getInstance();
		assertNotNull(result);
	}
	
	@Test
	public void testAdd() throws SQLException{
		System.out.println("add");
		Utente u = new Utente();
		u.setEmail("email1@studenti.unisa.it");
		u.setPassword("password1");
		u.setName("Name1");
		u.setSurname("Surname1");
		
		UtenteRepository instance = UtenteRepository.getInstance();
		instance.add(u);
		AccountSQL sql = new AccountSQL(u.getEmail());
		Utente result = instance.findItemByQuery(sql);
		asserEquals(u, result);
		instance.delete(u);
	}
	
	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		Utente u = new Utente();
		u.setEmail("email1@studenti.unisa.it");
		u.setPassword("password1");
		u.setName("Name1");
		u.setSurname("Surname1");
		
		UtenteRepository instance = UtenteRepository.getInstance();
		instance.add(u);
		UtenteSQL sql = new UtenteSQL(u.getEmail());
		instance.delete(u);
		Utente result = instance.findItemByQuery(sql);
		assertEquals(null, result);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		Utente u = new Utente();
		u.setEmail("email1@studenti.unisa.it");
		u.setPassword("password1");
		u.setName("Name1");
		u.setSurname("Surname1");
		
		UtenteRepository instance = UtenteRepository.getInstance();
		instance.add(u);
		UtenteSQL sql = new UtenteSQL(u.getEmail());
		Utente result = instance.findItemByQuery(sql);
		assertEquals(u, result);
		instance.delete(u);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		Utente u = new Utente();
		u.setEmail("email1@studenti.unisa.it");
		u.setPassword("password1");
		u.setName("Name1");
		u.setSurname("Surname1");
		List<Utente> utenti = new ArrayList<Utente>();
		
		UtenteRepository.getInstance().add(u);
		utenti = UtenteRepository.getInstance().query(new AccountList());
		assertTrue(!utenti.isEmpty());
	}
}
