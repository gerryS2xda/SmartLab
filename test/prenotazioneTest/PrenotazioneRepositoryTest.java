package prenotazioneTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.utils.Utils;

import businessLogic.prenotazione.PrenotazioneById;
import businessLogic.prenotazione.PrenotazioneByStudent;
import businessLogic.prenotazione.PrenotazioneRepository;
import dataAccess.storage.bean.Prenotazione;
import java.time.LocalDate;
import java.util.List;

public class PrenotazioneRepositoryTest {

	//instance field
	private PrenotazioneRepository repository;
	private Prenotazione oracle; //oggetto che funge da oracolo per il test
	
	@Before
	public void setUp() throws Exception {
	    //attiva driver manager come connessione per Junit
		Utils.enableDriverManager();
		
		//ottieni istanza di un oggetto repository
		repository = PrenotazioneRepository.getInstance();
		
		oracle = new Prenotazione();
		oracle.setData(LocalDate.now().toString());
		oracle.setFasciaOraria("9 - 11");
		oracle.setStatus(true);
		oracle.setStudente("teststud@studenti.unisa.it");
		oracle.setPostazione(100);
		oracle.setLaboratorio(1);
		
		repository.add(oracle);
		
		//ottieni l'ID dopo inserimento poiche' si usa auto_increment (serve per delete)
		Prenotazione temp = repository.findItemByQuery(new PrenotazioneByStudent(oracle.getStudente()));
		oracle.setID(temp.getId());
	}
	
	@After
	public void tearDown() throws Exception {
		repository.delete(oracle);
		Utils.disattivaDriverManager();
	}

	@Test
	public void testGetInstance()throws Exception {
		System.out.println("Testing: get Instance");
		repository = PrenotazioneRepository.getInstance();
		
		assertNotNull("Repository object e' nullo", repository);
	}

	@Test
	public void testAdd()throws Exception  {
		System.out.println("Testing: test add to DB");
		//inserisco nuovo oggetto nel DB, lo prelevo e verifica se e' stato aggiunto
		//oggetto da testare e' stato gia' inserito con setUp()
		Prenotazione actualObj = repository.findItemByQuery(new PrenotazioneById(oracle.getId()));
		
		//verifica se l'oggetto ottenuto e' uguale a quello inserito
		assertEquals("Oggetto prenotazione non e' stato inserito", oracle, actualObj);
	}

	@Test
	public void testDelete()throws Exception  {
		System.out.println("Testing: test remove to DB");
		
		//rimozione dell'oggetto
		repository.delete(oracle); 
		
		//verifica se e' stato cancellato
		Prenotazione actualObj = repository.findItemByQuery(new PrenotazioneByStudent(oracle.getStudente()));
		assertEquals("Oggetto prenotazione non e' stato rimosso", null, actualObj); //in questo caso, null rappresenta l'oracolo del test
		
	}

	@Test
	public void testUpdate()throws Exception  {
		
		System.out.println("Testing: Update");
		
		//crea oggetto simile ad oracle e verifica che i due riferimeni puntano alla stessa istanza
		Prenotazione actualObj = oracle;
		assertSame("Gli oggetti non fanno riferimento alla stessa istanza", actualObj, oracle);
		
		//modifica qualche attributo di actualObj
		actualObj.setFasciaOraria("11 - 13");
		actualObj.setStudente("teststud2@studenti.unisa.it");
		
		//invocazione di update()
		repository.update(actualObj);
		
		//prendi oggetto da DB appena modificato
		actualObj = repository.findItemByQuery(new PrenotazioneById(oracle.getId()));
		
		//controlla se la modifica e' stata effettuata
		assertEquals("La modifica non e' stata apportata", oracle, actualObj);
		
	}

	@Test
	public void testFindItemByQuery()throws Exception  {
		System.out.println("Testing: Find item by id");
		
		//trova elemento in base all'id dell'oracolo
		Prenotazione actualObj = repository.findItemByQuery(new PrenotazioneById(oracle.getId()));
		
		//confronta elemento ottenuto con quello dell'oracolo
		assertEquals("query non ha prodotto il risultato atteso", oracle, actualObj);
	}

	@Test
	public void testQuery()throws Exception  {
		System.out.println("Testing: Query per ottenere prenotazione fatta da uno studente");
		
		//ottieni lista dei risultati
		List<Prenotazione> actualObjs = repository.query(new PrenotazioneByStudent(oracle.getStudente()));
		
		//per comodita' si testa solo il primo risultato ottenuto (poiche' con ID = 0 e' sicuramente il primo)
		assertEquals("query non ha prodotto il risultato atteso", oracle, actualObjs.get(0));
	}

}