package prenotazionetest;

import businessLogic.prenotazione.PrenotazioneById;
import businessLogic.prenotazione.PrenotazioneByStudent;
import businessLogic.prenotazione.PrenotazioneRepository;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;
import dataAccess.storage.bean.Prenotazione;
import dataAccess.storage.bean.Studente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrenotazioneRepositoryTest {

	//instance field
	private PrenotazioneRepository repository;
	private Prenotazione oracle; //oggetto che funge da oracolo per il test
	
	@Before
	public void setUp() throws Exception {
	  
		//ottieni istanza di un oggetto repository
		repository = PrenotazioneRepository.getInstance();
		
		//oggetti usati al posto dei tipi primitivi
		Studente s = new Studente();
		s.setEmail("teststud@studenti.unisa.it");
		Postazione post = new Postazione(100, "0", true);
		Laboratorio lab = new Laboratorio();
		lab.setIDlaboratorio("0");
		
		oracle = new Prenotazione();
		oracle.setData(LocalDate.now().toString());
		oracle.setOraInizio(LocalTime.parse("09:00"));
		oracle.setOraFine(LocalTime.parse("11:00"));
		oracle.setStatus(true);
		oracle.setStudente(s);
		oracle.setPostazione(post);
		oracle.setLaboratorio(lab);	
		repository.add(oracle);
		
		//ottieni l'ID dopo inserimento poiche' si usa auto_increment (serve per delete)
		Prenotazione temp = repository.findItemByQuery(new PrenotazioneByStudent(oracle.getStudente().getEmail()));
		oracle.setID(temp.getId());
	}
	
	@After
	public void tearDown() throws Exception {
		repository.delete(oracle);
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
		Prenotazione actualObj = repository.findItemByQuery(new PrenotazioneByStudent(oracle.getStudente().getEmail()));
		assertEquals("Oggetto prenotazione non e' stato rimosso", null, actualObj); //in questo caso, null rappresenta l'oracolo del test
		
	}

	@Test
	public void testUpdate()throws Exception  {
		System.out.println("Testing: Update");
		
		//crea oggetto simile ad oracle 
		Prenotazione actualObj = new Prenotazione(oracle.getData().toString(), oracle.getOraInizio(), oracle.getOraFine(), 
				oracle.getStudente(), oracle.getPostazione(), oracle.getLaboratorio());
		actualObj.setID(oracle.getId());
		
		//modifica qualche attributo di actualObj
		actualObj.setOraInizio(LocalTime.parse("11:00"));
		actualObj.setOraFine(LocalTime.parse("13:00"));
		
		//invocazione di update()
		repository.update(actualObj);
		
		//prendi oggetto da DB appena modificato
		actualObj = repository.findItemByQuery(new PrenotazioneById(oracle.getId()));
		
		//controlla se la modifica e' stata effettuata (controlla se cambiata ora di inizio)
		assertNotEquals("La modifica non e' stata apportata", oracle.getOraInizio().toString(), actualObj.getOraInizio().toString());	//se non sono uguali --> modifica effettuata
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
		List<Prenotazione> actualObjs = repository.query(new PrenotazioneByStudent(oracle.getStudente().getEmail()));
		
		//per comodita' si testa solo il primo risultato ottenuto (poiche' con ID = 0 e' sicuramente il primo)
		assertEquals("query non ha prodotto il risultato atteso", oracle, actualObjs.get(0));
	}

}
