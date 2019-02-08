package comunicazioneTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import businessLogic.comunicazione.AvvisoByNameSQL;
import businessLogic.comunicazione.AvvisoRepository;
import businessLogic.comunicazione.AvvisoSql;
import businessLogic.comunicazione.CommunicationManager;
import businessLogic.comunicazione.SegnalazioneByOggetto;
import businessLogic.comunicazione.SegnalazioneRepository;
import businessLogic.comunicazione.SegnalazioneSql;
import businessLogic.laboratorio.IdLab;
import businessLogic.laboratorio.LaboratorioRepository;
import dataAccess.storage.bean.Avviso;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Segnalazione;

public class CommunicationManagerTest {

	//instance field
	private CommunicationManager manager;
	private AvvisoRepository repositoryAvviso;
	private SegnalazioneRepository repositorySegn;
	private LaboratorioRepository laboratorioRep;
	private Avviso avvIn;
	private Segnalazione segnIn;
	private Laboratorio lab;
	
	@Before
	public void setUp() throws Exception {
		
		manager = new CommunicationManager();
		repositoryAvviso = new AvvisoRepository();
		repositorySegn = new SegnalazioneRepository();
		
		//crea e salva i dati nella table di laboratorio per il test
		laboratorioRep = LaboratorioRepository.getInstance();
		lab = new Laboratorio("0", "lab0", 100, true, LocalTime.parse("09:00"), LocalTime.parse("17:00"));
		laboratorioRep.add(lab);
		lab = laboratorioRep.findItemByQuery(new IdLab(lab.getNome())); //ottieni ID per delete
		
		//aggiungi avviso nel DB
		avvIn = new Avviso(0, "Messaggio test", "Titolo test", Date.valueOf(LocalDate.now()), "test");
		repositoryAvviso.add(avvIn);
		
		//ottieni l'ID dopo inserimento poiche' si usa auto_increment (serve per delete)
		Avviso temp = repositoryAvviso.findItemByQuery(new AvvisoByNameSQL(avvIn.getTitolo()));
		avvIn.setId(temp.getId());
		
		//aggiungi segnalazione nel DB
		segnIn = new Segnalazione(0, "Oggetto test", "Descrizione test", Date.valueOf(LocalDate.now()), "test", "0", 16);
		repositorySegn.add(segnIn);
		
		//ottieni l'ID dopo inserimento poiche' si usa auto_increment (serve per delete)
		Segnalazione tempSegn = repositorySegn.findItemByQuery(new SegnalazioneByOggetto(segnIn.getOggetto()));
		segnIn.setId(tempSegn.getId());
		
	}

	@After
	public void tearDown() throws Exception {
		repositoryAvviso.delete(avvIn);
		repositorySegn.delete(segnIn);
		laboratorioRep.delete(lab);
	}

	@Test
	public void testAddSegnalazione()throws Exception {
		System.out.println("Testing: test add to DB. Caso: true");
		
		//crea oggetto simile ad oracle 
		Segnalazione actualObj = new Segnalazione(segnIn.getId(), segnIn.getOggetto(), segnIn.getDescrizione(), segnIn.getData(), segnIn.getStudente(), segnIn.getLaboratorio(), segnIn.getPostazione());
		actualObj.setLaboratorio(lab.getNome()); //setta il nome del lab al posto di ID per testare il metodo
		
		boolean esito = manager.addSegnalazione(actualObj);
		
		//setta ID della Segnalazione appena inserita
		actualObj.setId(1 + segnIn.getId()); 
		
		assertTrue("Oggetto Segnalazione non e' stato inserito", esito);
	}

	@Test
	public void testDeleteSegnalazione()throws Exception {
		System.out.println("Testing: test remove to DB");
		
		//rimozione dell'oggetto
		manager.deleteSegnalazione(segnIn);
		
		//verifica se e' stato cancellato
		Segnalazione actualObj = repositorySegn.findItemByQuery(new SegnalazioneSql(segnIn.getId()));
		assertEquals("Oggetto Segnalazione non e' stato rimosso", 0, actualObj.getId()); //in questo caso, null rappresenta l'oracolo del test
	}

	@Test
	public void testViewSegnalazione()throws Exception {
		System.out.println("Testing: Query per ottenere una lista di postazioni e verificare se e' presente oracle nella lista");
		boolean val = false; //se rimane false --> FAIL
		
		//ottieni lista dei risultati
		List<Segnalazione> actualObjs = manager.viewSegnalazione();
		
		//verifica se la postazione "oracle" e' presente nella lista.. se presente --> OK
		for(Segnalazione s : actualObjs){
			if(s.getId() == segnIn.getId()){
				val = true;
				break;
			}
		}
		
		assertTrue("Oracle non e' presente nella lista", val);
	}

	@Test
	public void testAddAvviso()throws Exception {
		System.out.println("Testing: test add to DB. Caso: true");
		
		Avviso actualObj = new Avviso(avvIn.getId(), avvIn.getTitolo(), avvIn.getMessaggio(), avvIn.getData(), avvIn.getAddetto());
		
		boolean esito = manager.addAvviso(avvIn);
		
		//setta ID della Avviso appena inserita
		actualObj.setId(1 + avvIn.getId()); 
		
		assertTrue("Oggetto Avviso non e' stato inserito", esito);

	}

	@Test
	public void testDeleteAvviso()throws Exception {
		System.out.println("Testing: test remove to DB");
		
		//rimozione dell'oggetto
		manager.deleteAvviso(avvIn);
		
		//verifica se e' stato cancellato
		Avviso actualObj = repositoryAvviso.findItemByQuery(new AvvisoSql(avvIn.getId()));
		assertEquals("Oggetto Avviso non e' stato rimosso", 0, actualObj.getId()); //in questo caso, 0 rappresenta l'oracolo del test
	}

	@Test
	public void testViewAvviso()throws Exception {
		System.out.println("Testing: Query per ottenere una lista di postazioni e verificare se e' presente oracle nella lista");
		boolean val = false; //se rimane false --> FAIL
		
		//ottieni lista dei risultati
		List<Avviso> actualObjs = manager.viewAvviso();
		
		//verifica se la postazione "oracle" e' presente nella lista.. se presente --> OK
		for(Avviso a : actualObjs){
			if(a.getId() == avvIn.getId()){
				val = true;
				break;
			}
		}
		
		assertTrue("Oracle non e' presente nella lista", val);
	}

}
