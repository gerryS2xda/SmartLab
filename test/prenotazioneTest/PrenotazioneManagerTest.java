package prenotazioneTest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.project.utils.Utils;

import businessLogic.prenotazione.PrenotazioneById;
import businessLogic.prenotazione.PrenotazioneByStudent;
import businessLogic.prenotazione.PrenotazioneManager;
import businessLogic.prenotazione.PrenotazioneRepository;
import dataAccess.storage.bean.Prenotazione;

public class PrenotazioneManagerTest {

	//instance field
	private PrenotazioneManager manager;
	private PrenotazioneRepository repository;
	private Prenotazione oracle; //oggetto che funge da oracolo per il test
	
	
	@Before
	public void setUp() throws Exception {
		//attiva driver manager come connessione per Junit
		Utils.enableDriverManager();
		manager = PrenotazioneManager.getInstance();
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
	public void testGetInstance() {
		System.out.println("Testing: get Instance");
		manager = PrenotazioneManager.getInstance();
		
		assertNotNull("Repository object e' nullo", manager);
	}

	@Test
	public void testEffettuaPrenotazione() throws Exception {
		System.out.println("Testing: effettua prenotazione");
		
		//crea oggetto prenotazione e confronta con oracle
		Prenotazione actualObj = manager.effettuaPrenotazione("teststud@studenti.unisa.it", 100, "9 - 11");
		
		//rimuovi la prenotazione appena inserita con effettuaPrenotazione dal DB
		actualObj.setID(1 + oracle.getId()); 
		repository.delete(actualObj);
		
		//effettua il confronto
		assertEquals("Prenotazione non e' stata effettuata", oracle, actualObj);
		
	}

	@Test
	public void testAnnullaPrenotazione() throws Exception{
		System.out.println("Testing: annulla prenotazione");
		
		//annulla la prenotazione
		manager.annullaPrenotazione(oracle);
		
		//verifica se e' stata annullata andando a cercarla nel DB (Per annullare si intende rimozione)
		Prenotazione actualObj = manager.findPrenotazioneById(oracle.getId());
		
		//confronta actualObj con null (In questo caso, null e' l'oracolo)
		assertEquals("Prenotazione non annullata", null, actualObj);
	}

	@Test
	public void testFindPrenotazioneById() {
		System.out.println("Testing: trova una prenotazione tramite id");
		
		//la prenotazione da trovare e' gia' nel DB tramite setUp()
		Prenotazione actualObj = manager.findPrenotazioneById(oracle.getId());
		
		//confronta la prenotazione ottenuta con oracle
		assertEquals("La prenotazione non e' stata trovata (FAIL)", oracle, actualObj);
	}

	@Test
	public void testUpdatePrenotazione() {
		System.out.println("Testing: aggiorna i dati di una prenotazione");
		
		//crea oggetto simile ad oracle e verifica che i due riferimeni puntano alla stessa istanza
		Prenotazione actualObj = oracle;
		assertSame("Gli oggetti non fanno riferimento alla stessa istanza", actualObj, oracle);
				
		//modifica qualche attributo di actualObj
		actualObj.setFasciaOraria("11 - 13");
		actualObj.setStudente("teststud2@studenti.unisa.it");
				
		//invocazione di update()
		manager.updatePrenotazione(actualObj);
				
		//prendi oggetto da DB appena modificato
		actualObj = manager.findPrenotazioneById(oracle.getId());
				
		//controlla se la modifica e' stata effettuata
		assertEquals("La modifica non e' stata apportata", oracle, actualObj);
	}

	@Test
	public void testGetListPrenotazioniByStudent() {
		System.out.println("Testing: ottieni lista di prenotazioni fatte da uno studente");
		
		//il testing verra' fatta soltanto sul primo risultato ottenuto
		List<Prenotazione> actualObjs = manager.getListPrenotazioniByStudent(oracle.getStudente());
		
		//confronta il primo risultato con quello dell'oracolo
		assertEquals("Il risultato ottenuto e' diverso da quello atteso", oracle, actualObjs.get(0));
		
		
	}

	@Test
	public void testIsPrenotazioneActive() {
		System.out.println("Testing: verifica se la prenotazione e' attiva");
		
		//si assume che la prenotazione sia attiva (nell'oracolo e' attiva)
		assertTrue("La prenotazione non e' attiva", manager.isPrenotazioneActive(oracle));
	}

	@Test
	public void testGetNumPrenotazioniEffettuateOggi() {
		System.out.println("Testing: ottieni numero di prenotazione fatte oggi da uno studente");
		
		int oracleValue = 1;
		int actualValue = manager.getNumPrenotazioniEffettuateOggi(oracle.getStudente());
		
		assertEquals("Il numero di prenotazioni effettuate non e' 1", oracleValue, actualValue);
	}

	@Test
	public void testGetNumPrenotazioniEffettuate() {
		System.out.println("Testing: ottieni numero di prenotazione fatte in totale da uno studente");
		
		int oracleValue = 1;
		int actualValue = manager.getNumPrenotazioniEffettuate(oracle.getStudente());
		
		assertEquals("Il numero di prenotazioni effettuate non e' 1", oracleValue, actualValue);
	}

}
