package prenotazionetest;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.Postazione.PostazioneRepository;
import businessLogic.laboratorio.IdLab;
import businessLogic.laboratorio.LaboratorioRepository;
import businessLogic.prenotazione.ListaPrenotazioniQuery;
import businessLogic.prenotazione.PrenotazioneById;
import businessLogic.prenotazione.PrenotazioneByStudent;
import businessLogic.prenotazione.PrenotazioneException;
import businessLogic.prenotazione.PrenotazioneManager;
import businessLogic.prenotazione.PrenotazioneRepository;
import businessLogic.utente.StudenteRepository;
import dataAccess.storage.bean.Addetto;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;
import dataAccess.storage.bean.Prenotazione;
import dataAccess.storage.bean.Studente;

public class PrenotazioneManagerTestCase2 {

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
	public void testEffettuaPrenotazione()throws Exception {
		System.out.println("Testing: effettua prenotazione. Caso: oraCorrente > oraChiusura --> setta data di domani");
		
		//decrementa ora di chiusura e salva modifica nella table di laboratorio
		LocalTime oraChiusura = LocalTime.now().minusHours(1); 
		lab.setChiusura(oraChiusura);
		
		laboratorioRep.update(lab);
		
		//EffettuaPrenotazione: aggiunge la nuova prenotazione nella table di prenotazione
		Prenotazione actualObj = manager.effettuaPrenotazione("teststud@studenti.unisa.it", 100, "09:00", "11:00", oracle.getLaboratorio().getIDlaboratorio());
		
		//setta ID della prenotazione appena inserita
		actualObj.setID(1 + oracle.getId()); 
		
		//ottieni la prenotazione appena inserita e confronta con i dati di 'actualOBj' che funge da oracolo per questo test.. 
		Prenotazione newPren = repository.findItemByQuery(new PrenotazioneById(actualObj.getId()));
		
		String oracleDomani = LocalDate.now().plusDays(1).toString();	//oracolo del test
		
		//effettua il test: se newPren ha impostata la data di oggi --> prenotazione e' stata aggiunta nel DB
		assertEquals("Prenotazione non e' stata effettuata con la data di oggi", oracleDomani, newPren.getData());
		
		//rimuovi la prenotazione inserita con effettuaPrenotazione dal DB
		repository.delete(actualObj);
	}

	@Test(expected = PrenotazioneException.class)
	public void testAnnullaPrenotazione()throws Exception {
		System.out.println("Testing: annulla prenotazione. Caso: oraAttuale >= oraInizio --> lancia eccezione");
		
		//Non dobbiamo fare nulla con l'oraInizio, poiche' viene ridotta di 2 nel manager e aggiorna valore nel DB 
		//annulla la prenotazione
		manager.annullaPrenotazione(oracle);
		
		//non usiamo assertEquals poiche' viene lanciata l'eccezione PrenotazioneException, quindi ok
	}

	@Test(expected = PrenotazioneException.class)
	public void testFindPrenotazioneById()throws Exception {
		System.out.println("Testing: trova una prenotazione tramite id. Caso ID < 0");
		
		//Il test e' stato semplificato perche' non e' stato previsto un metodo che inserisce una prenotazione nel DB
		//andando a settare l'ID.. ID viene generato automaticamente con auto increment
		
		//crea una nuova prenotazione con ID = -1 
		Prenotazione pren = new Prenotazione(oracle.getData(), oracle.getOraInizio(), oracle.getOraFine(), 
				oracle.getStudente(), oracle.getPostazione(), oracle.getLaboratorio());
		pren.setID(-1);
		
		Prenotazione resultTest = manager.findPrenotazioneById(pren.getId());
		
		//se viene lanciata l'eccezione --> test ha esito PASS
	}

	@Test
	public void testIsPrenotazioneActive()throws Exception {
		System.out.println("Testing: verifica se una prenotazione e' attiva. Caso: false (oraAttuale >= oraFine)");
		
		//per ottenere che oraAttuale >= oraFine (caso false), occorre:
		String oraFine = LocalTime.now().minusHours(1).toString();	//oraAttuale - 1 
		oracle.setOraFine(LocalTime.parse(oraFine)); //setta come oraFine (in modo che oraAttuale > oraFine)
		
		assertFalse("La prenotazione e' attiva", manager.isPrenotazioneActive(oracle));
	}

	@Test
	public void testChangePrenotazioneStatus()throws Exception {
		System.out.println("Testing: verifica se una prenotazione e' attiva. Caso: setta False (scaduta)");
		
		//si fa in modo che manager.isPrenotazioneActive() sia false --> setta false lo stato di prenotazione
		String oraFine = LocalTime.now().minusHours(1).toString();	//oraAttuale + 1 
		oracle.setOraFine(LocalTime.parse(oraFine)); //setta come oraFine (in modo che oraAttuale < oraFine)
		
		//setta l'oracle object a true per il test
		oracle.setStatus(true);
		
		manager.changePrenotazioneStatus(oracle); //se tutto va bene, da true dovrebbe passare a false
		
		assertFalse("Lo stato di prenotazione non e' stato posto a false", oracle.isPrenotazioneActive());
	}

	@Test
	public void testDeleteAllPrenotazioniAfterDays()throws Exception {
		System.out.println("Testing: cancella tutte le prenotazioni effettuate nei giorni precedenti. Caso: dataCorrente == dataAttuale --> Non rimuovere");
		
		//la dataCorrente = dataAttuale della prenotazione (vedi setUp()), quindi non si deve manipolare la data 	
		manager.deleteAllPrenotazioniAfterDays(); //metodo da testare
		
		//Si testa se e' stato rimosso (se non e' stato rimosso --> Test PASS)
		Prenotazione actualObj = manager.findPrenotazioneById(oracle.getId());
		
		//confronta actualObj con oracle
		assertEquals("Prenotazione cancellata avente dataCorrente uguale a dataAttuale", actualObj, oracle);
	}

}
