package prenotazionetest;

import businessLogic.Postazione.PostazioneRepository;
import businessLogic.laboratorio.IdLab;
import businessLogic.laboratorio.LaboratorioRepository;
import businessLogic.laboratorio.LaboratorioSql;
import businessLogic.prenotazione.ListaPrenotazioniQuery;
import businessLogic.prenotazione.PrenotazioneById;
import businessLogic.prenotazione.PrenotazioneByStudent;
import businessLogic.prenotazione.PrenotazioneGetSQL;
import businessLogic.prenotazione.PrenotazioneManager;
import businessLogic.prenotazione.PrenotazioneRepository;
import businessLogic.utente.StudenteRepository;
import dataAccess.storage.bean.Addetto;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;
import dataAccess.storage.bean.Prenotazione;
import dataAccess.storage.bean.Studente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrenotazioneManagerTestCase1 {

	//instance field
	private PrenotazioneManager manager;
	private PrenotazioneRepository repository;
	private PostazioneRepository postazioneRep;
	private LaboratorioRepository laboratorioRep;
	private StudenteRepository studenteRep;
	private Prenotazione oracle; //oggetto che funge da oracolo per il test
	//oggetti usati come campi in Prenotazione per ottenere informazione generale
	private Studente s;
	private Postazione post;
	private Laboratorio lab;
		
	@Before
	public void setUp() throws Exception {
		
		manager = PrenotazioneManager.getInstance();
		repository = PrenotazioneRepository.getInstance();
		
		//altre 
		postazioneRep = PostazioneRepository.getInstance();
		laboratorioRep = LaboratorioRepository.getInstance();
		studenteRep = StudenteRepository.getInstance();
		
		//oggetti usati al posto dei tipi primitivi
		lab = new Laboratorio("0", "lab0", 100, true, LocalTime.parse("09:00"), LocalTime.parse("17:00"));
		laboratorioRep.add(lab);
		lab = laboratorioRep.findItemByQuery(new IdLab(lab.getNome()));
		System.out.println("IDLab: " + lab.getIDlaboratorio() + " Nome: " + lab.getNome());
		
		s = new Studente("teststud@studenti.unisa.it", "1234asd", "TestNome", "TestCognome");
		studenteRep.add(s);
		post = new Postazione(100, lab.getIDlaboratorio(), true);
		postazioneRep.add(post);
		
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
		postazioneRep.delete(post);
		laboratorioRep.delete(lab);
		studenteRep.delete(s);
	}

	@Test
	public void testGetInstance() {
		System.out.println("Testing: get Instance");
		manager = PrenotazioneManager.getInstance();
		
		assertNotNull("Repository object e' nullo", manager);
	}

	@Test
	public void testEffettuaPrenotazione()throws Exception {
		System.out.println("Testing: effettua prenotazione. Caso: oraCorrente < oraChiusura --> setta data di oggi");
		
		//incremento ora di chiusura e salva modifica nella table di laboratorio
		LocalTime oraChiusura = LocalTime.now().plusHours(1); 
		lab.setChiusura(oraChiusura);
		
		laboratorioRep.update(lab);
		
		//EffettuaPrenotazione: aggiunge la nuova prenotazione nella table di prenotazione
		Prenotazione actualObj = manager.effettuaPrenotazione("teststud@studenti.unisa.it", 100, "09:00", "11:00", oracle.getLaboratorio().getIDlaboratorio());
		
		//setta ID della prenotazione appena inserita
		actualObj.setID(1 + oracle.getId()); 
		
		//ottieni la prenotazione appena inserita e confronta la sua data con quella di oggi
		Prenotazione newPren = repository.findItemByQuery(new PrenotazioneById(actualObj.getId()));
		
		String oracleToday = LocalDate.now().toString();	//oracolo del test
		
		//effettua il test: se newPren ha impostata la data di oggi --> prenotazione e' stata aggiunta nel DB
		assertEquals("Prenotazione non e' stata effettuata con la data di oggi", oracleToday, newPren.getData());
		
		//rimuovi la prenotazione inserita con effettuaPrenotazione dal DB
		repository.delete(actualObj);
	}

	@Test
	public void testAnnullaPrenotazione()throws Exception {
		System.out.println("Testing: annulla prenotazione. Caso: oraAttuale < oraInizio --> Rimozione");
		
		//incrementa oraInizio di 3, poiche' viene ridotta di 2 nel manager e aggiorna valore nel DB 
		LocalTime oraInizio = LocalTime.now().plusHours(3); 
		oracle.setOraInizio(oraInizio);
		repository.update(oracle);
		
		//annulla la prenotazione
		manager.annullaPrenotazione(oracle);
		
		//verifica se e' stata annullata andando a cercarla nel DB (Per annullare si intende rimozione)
		Prenotazione actualObj = manager.findPrenotazioneById(oracle.getId());
		
		//confronta actualObj con null (In questo caso, null e' l'oracolo)
		assertEquals("Prenotazione non annullata", null, actualObj);
	}

	@Test
	public void testFindPrenotazioneById()throws Exception  {
		System.out.println("Testing: trova una prenotazione tramite id. Caso ID > 0");
		
		//la prenotazione da trovare e' gia' nel DB tramite setUp()
		Prenotazione actualObj = manager.findPrenotazioneById(oracle.getId());
		
		//confronta la prenotazione ottenuta con oracle
		assertEquals("La prenotazione non e' stata trovata (FAIL)", oracle, actualObj);
	}

	@Test
	public void testGetListPrenotazioniByStudent()throws Exception  {
		System.out.println("Testing: ottieni lista di prenotazioni fatte da uno studente. Caso: lista piena");
		
		//il testing verra' fatta soltanto sul primo risultato ottenuto
		List<Prenotazione> actualObjs = manager.getListPrenotazioniByStudent(oracle.getStudente().getEmail());
		
		//confronta il primo risultato con quello dell'oracolo
		assertEquals("Il risultato ottenuto e' diverso da quello atteso", oracle, actualObjs.get(0));
	}

	@Test
	public void testIsPrenotazioneActive()throws Exception  {
		System.out.println("Testing: verifica se una prenotazione e' attiva. Caso: active (oraAttuale < oraFine)");
		
		//per ottenere che oraAttuale < oraFine (caso true), occorre:
		String oraFine = LocalTime.now().plusHours(1).toString();	//oraAttuale + 1 
		oracle.setOraFine(LocalTime.parse(oraFine)); //setta come oraFine (in modo che oraAttuale < oraFine)
		
		assertTrue("La prenotazione non e' attiva", manager.isPrenotazioneActive(oracle));
		
	}

	@Test
	public void testChangePrenotazioneStatus()throws Exception  {
		System.out.println("Testing: verifica se una prenotazione e' attiva. Caso: setta Active");
		
		//si fa in modo che manager.isPrenotazioneActive() sia True --> setta True lo stato di prenotazione
		String oraFine = LocalTime.now().plusHours(1).toString();	//oraAttuale + 1 
		oracle.setOraFine(LocalTime.parse(oraFine)); //setta come oraFine (in modo che oraAttuale < oraFine)
		
		//setta l'oracle object a false per il test
		oracle.setStatus(false);
		
		manager.changePrenotazioneStatus(oracle); //se tutto va bene, da false dovrebbe passare a true
		
		assertTrue("Lo stato di prenotazione non e' stato posto a true", oracle.isPrenotazioneActive());
	}

	@Test
	public void testGetNumPrenotazioniEffettuateOggi()throws Exception  {
		System.out.println("Testing: ottieni numero di prenotazione fatte oggi da uno studente. Caso: studente ha fatto almeno una prenotazione");

		int oracleValue = 1;
		int actualValue = manager.getNumPrenotazioniEffettuateOggi(oracle.getStudente().getEmail());

		assertEquals("Il numero di prenotazioni effettuate non e' 1", oracleValue, actualValue);
	}

	@Test
	public void testGetNumeroPostazioniPrenotate()throws Exception {
		System.out.println("Testing: ottieni numero di postazioni prenotate (Caso: postazione presente --> > 0");
		
		//si assume che lab0 con ID = 0 sia presente nella table 'laboratorio'
		int or = 1; //una postazione prenotata nel lab0
		int actualValue = manager.getNumeroPostazioniPrenotate(oracle.getOraInizio().toString(), oracle.getLaboratorio().getIDlaboratorio());
		
		assertEquals("Postazione" + oracle.getPostazione().getNumero() +" non e' stata prenotata", or, actualValue);
	}

	@Test
	public void testGetPrenotazioniByQuery()throws Exception  {
		System.out.println("Testing: ottieni una prenotazione by una query. Caso: prenotazione trovata");
		
		//inseriamo come valori per la clausola where, quelli riportati nell'oracle object
		List<Prenotazione> prenotazioni = manager.getPrenotazioniByQuery(oracle.getOraInizio().toString(), 
				oracle.getOraFine().toString(), oracle.getPostazione().getNumero(), oracle.getLaboratorio().getIDlaboratorio());
		
		//se viene ritornata una lista con almeno un elemento --> prenotazione trovata
		int or = 1;	//oracolo
		int actualValue = prenotazioni.size(); 
		
		assertEquals("Prenotazione non trovata", or, actualValue);
	}
		
	@Test
	public void testDeleteAllPrenotazioni()throws Exception  {
		System.out.println("Testing: cancella tutte le prenotazioni");
		
		//cancella tutte le prenotazioni presenti nel DB
		manager.deleteAllPrenotazioni();
		
		//effettuando una query che ritorna una lista di prenotazioni, se la lista e' vuota --> = 0
		int oracle = 0;
		List<Prenotazione> actualObj = repository.query(new ListaPrenotazioniQuery());
		
		assertEquals("Non tutte le prenotazioni sono state cancellate", oracle, actualObj.size());
	}

	@Test
	public void testDeleteAllPrenotazioniAfterDays()throws Exception  {
		System.out.println("Testing: cancella tutte le prenotazioni effettuate nei giorni precedenti. Caso: dataCorrente != dataAttuale --> Rimuovi");
		
		//imposta la dataCorrente come dataAttuale nella prenotazione e aggiorna il suo valore presente nel DB
		String dataAttuale = LocalDate.now().minusDays(1).toString();	
		oracle.setData(dataAttuale);
		repository.update(oracle);
		
		manager.deleteAllPrenotazioniAfterDays(); //metodo da testare
		
		//una volta rimosso, si testa se e' stato rimosso
		Prenotazione actualObj = manager.findPrenotazioneById(oracle.getId());
		
		//confronta actualObj con null (In questo caso, null e' l'oracolo)
		assertEquals("Prenotazione non cancellata", null, actualObj);
	}

	@Test
	public void testGetAllPrenotazioni()throws Exception  {
		System.out.println("Testing: ottieni tutte le prenotazioni (Caso: true se oracle e' presente)");
		boolean val = false; 
		
		//per semplicita' verifichiamo se la prenotazione "oracle" si trova nella lista
		List<Prenotazione> prenotazioni = manager.getAllPrenotazioni();
		
		for(Prenotazione p : prenotazioni){
			if(p.getId() == oracle.getId()){
				val = true;
				break;
			}
		}
		
		assertTrue("La prenotazione oracle non e' presente nella lista", val); 
	}

}
