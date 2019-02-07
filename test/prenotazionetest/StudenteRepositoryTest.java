package prenotazionetest;

import businessLogic.utente.StudentList;
import businessLogic.utente.StudenteRepository;
import businessLogic.utente.StudenteSQL;
import dataAccess.storage.bean.Studente;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudenteRepositoryTest {

	//instance field
	private StudenteRepository repository;
	private Studente oracle; //oggetto che funge da oracolo per il test
	
	@Before
	public void setUp() throws Exception {
		repository = StudenteRepository.getInstance();
		
		oracle = new Studente("teststud@studenti.unisa.it", "1234asd", "TestNome", "TestCognome");
		oracle.setStato(true); //account e' attivo
		
		repository.add(oracle);
	}

	@After
	public void tearDown() throws Exception {
		repository.delete(oracle);
	}

	@Test
	public void testGetInstance() {
		System.out.println("Testing: get Instance");
		repository = StudenteRepository.getInstance();
		
		assertNotNull("Repository object e' nullo", repository);
	}

	@Test
	public void testAdd()throws Exception {
		System.out.println("Testing: test add to DB");
		//inserisco nuovo oggetto nel DB, lo prelevo e verifica se e' stato aggiunto
		//oggetto da testare e' stato gia' inserito con setUp()
		Studente actualObj = repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		
		//verifica se l'oggetto ottenuto e' uguale a quello inserito
		assertEquals("Oggetto prenotazione non e' stato inserito", oracle, actualObj);
	}

	@Test
	public void testDelete()throws Exception {
		System.out.println("Testing: test remove to DB (email = '' rappresenta il caso 'null' ");
		
		//rimozione dell'oggetto
		repository.delete(oracle); 
		
		//verifica se e' stato cancellato
		Studente actualObj = repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		assertEquals("Oggetto prenotazione non e' stato rimosso", "", actualObj.getEmail()); //in questo caso, "" rappresenta l'oracolo del test
	}

	@Test
	public void testUpdate()throws Exception {
		System.out.println("Testing: Update");
		
		//crea oggetto simile ad oracle 
		Studente actualObj = new Studente(oracle.getEmail(), oracle.getPassword(), oracle.getName(), oracle.getSurname());
		
		//modifica qualche attributo di actualObj
		actualObj.setName("TestNomeUpdate");
		actualObj.setStato(false); //cambia lo stato da true (usato nell'oracle) a false
		
		//invocazione di update()
		repository.update(actualObj);
		
		//prendi oggetto da DB appena modificato
		actualObj = repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		
		//controlla se la modifica e' stata effettuata (controlla se lo stato e' cambiato da true a false)
		assertFalse("La modifica non e' stata apportata", actualObj.getStato());	//se lo stato e' false --> modifica effettuata
	}

	@Test
	public void testFindItemByQuery()throws Exception {
		System.out.println("Testing: Find item by id");
		
		//trova elemento in base alla email dell'oracolo
		Studente actualObj = repository.findItemByQuery(new StudenteSQL(oracle.getEmail()));
		
		//confronta elemento ottenuto con quello dell'oracolo
		assertEquals("query non ha prodotto il risultato atteso", oracle, actualObj);
	}

	@Test
	public void testQuery()throws Exception {
		System.out.println("Testing: Query per ottenere una lista di studenti e verificare se e' presente oracle nella lista");
		boolean val = false; //se rimane false --> FAIL
		
		//ottieni lista dei risultati
		List<Studente> actualObjs = repository.query(new StudentList());
		
		//verifica se lo studente e' presente nella lista.. se presente --> OK
		for(Studente s : actualObjs){
			if(s.getEmail().equals(oracle.getEmail())){
				val = true;
				break;
			}
		}
		
		assertTrue("Oracle non e' presente nella lista", val);
	}
}
