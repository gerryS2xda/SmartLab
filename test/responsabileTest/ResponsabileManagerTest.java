import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

public class AccountManagerTest {
	
	@Test
	public void testGetInstance(){
		System.out.println("getInstance");
		AccountRepository result = AccountRepository.getInstance();
		assertNotNull(result);
	}
	
	@Test
	public void testSignUp(){
		System.out.println("effettuaRegistrazione");
		Utente u = new Utente();
		u.setEmail("email1@studenti.unisa.it");
		u.setPassword("password1");
		u.setName("Name1");
		u.setSurname("Surname1");
		
		AccountManager instance = AccountManager.getInstace();
		assertTrue(instance.effettuaRegistrazione
				(u.getEmail(), u.getPassword(), u.getName(), u.getSurname()));
	}
	
	@Test
	public void testLogin(){
		System.out.println("effettuaAutenticazione");
		Utente u = new Utente();
		u.setEmail("email1@studenti.unisa.it");
		u.setPassword("password1");
		u.setName("Name1");
		u.setSurname("Surname1");
		
		AccountManager instance = AccountManager.getInstace();
		assertTrue(instance.effettuaAutenticazione(u.getEmail(), u.getPassword()));
	}
	
	@Test
	public void testSospensione(){
		System.out.println("effettuaSospensione");
		Utente u = new Utente();
		u.setEmail("email1@studenti.unisa.it");
		u.setPassword("password1");
		u.setName("Name1");
		u.setSurname("Surname1");	
		
		AccountManager instance = AccountManager.getInstace();
		assertTrue(instance.effettuaSospensione((Studente) u)); //è giusto fare il cast?
	}
	
	@Test
	public void testAccountList() throws SQLException{
		System.out.println("getAccountList");
		Utente u = new Utente();
		u.setEmail("email1@studenti.unisa.it");
		u.setPassword("password1");
		u.setName("Name1");
		u.setSurname("Surname1");
		ArrayList<Utente> utenti = new ArrayList<Utente>();
		
		AccountManager instance = AccountManager.getInstace();
		instance.effettuaRegistrazione(u.getEmail(), u.getPassword(), u.getName(), u.getSurname());
		utenti = instance.getAccountList();
		assertTrue(!utenti.isEmpty());
	}
}
