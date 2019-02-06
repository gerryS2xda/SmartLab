package prenotazionetest;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.Postazione.ListaPos;
import businessLogic.Postazione.PostazioneRepository;
import businessLogic.Postazione.PostazioneSql;
import businessLogic.laboratorio.IdLab;
import businessLogic.laboratorio.LaboratorioRepository;
import businessLogic.laboratorio.LaboratorioSql;
import businessLogic.laboratorio.ListaLab;
import dataAccess.storage.bean.Addetto;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;

public class LaboratorioRepositoryTest {

	//instance field
	private LaboratorioRepository repository;
	private Laboratorio oracle; //oggetto che funge da oracolo per il test
	
	@Before
	public void setUp() throws Exception {
		
		repository = LaboratorioRepository.getInstance();
		
		oracle = new Laboratorio("0", "lab0", 100, true, LocalTime.parse("09:00"), LocalTime.parse("17:00"), new ArrayList<Addetto>());
		repository.add(oracle);	
		oracle = repository.findItemByQuery(new IdLab(oracle.getNome()));
	}

	@After
	public void tearDown() throws Exception {
		repository.delete(oracle);
	}

	@Test
	public void testGetInstance() {
		System.out.println("Testing: get Instance");
		repository = LaboratorioRepository.getInstance();
		
		assertNotNull("Repository object e' nullo", repository);
	}

	@Test
	public void testAdd()throws Exception {
		System.out.println("Testing: test add to DB");
		//inserisco nuovo oggetto nel DB, lo prelevo e verifica se e' stato aggiunto
		//oggetto da testare e' stato gia' inserito con setUp()
		Laboratorio actualObj = repository.findItemByQuery(new LaboratorioSql(oracle.getIDlaboratorio()));
		
		//verifica se l'oggetto ottenuto e' uguale a quello inserito
		assertEquals("Oggetto prenotazione non e' stato inserito", oracle, actualObj);
	}

	@Test
	public void testDelete()throws Exception {
		System.out.println("Testing: test remove to DB");
		
		//rimozione dell'oggetto
		repository.delete(oracle); 
		
		//verifica se e' stato cancellato
		Laboratorio actualObj = repository.findItemByQuery(new LaboratorioSql(oracle.getIDlaboratorio()));
		assertEquals("Oggetto prenotazione non e' stato rimosso", "", actualObj.getIDlaboratorio()); //in questo caso, IDLab = "" rappresenta l'oracolo del test
	}

	@Test
	public void testUpdate()throws Exception {
		System.out.println("Testing: Update");
		
		//crea oggetto simile ad oracle 
		Laboratorio actualObj = new Laboratorio(oracle.getIDlaboratorio(), oracle.getNome(), oracle.getPosti(), 
				oracle.isStato(), oracle.getApertura(), oracle.getChiusura(), new ArrayList<Addetto>());
		
		//modifica qualche attributo di actualObj
		actualObj.setChiusura(LocalTime.parse("18:00"));
		
		//invocazione di update()
		repository.update(actualObj);
		
		//prendi oggetto da DB appena modificato
		actualObj = repository.findItemByQuery(new LaboratorioSql(oracle.getIDlaboratorio()));
		
		//controlla se la modifica e' stata effettuata (controlla se cambiata ora di chiusura)
		assertNotEquals("La modifica non e' stata apportata", oracle.getChiusura().toString(), actualObj.getChiusura().toString());	//se non sono uguali --> modifica effettuata
	}

	@Test
	public void testFindItemByQuery()throws Exception {
		System.out.println("Testing: Find item by id");
		
		//trova elemento in base alla email dell'oracolo
		Laboratorio actualObj = repository.findItemByQuery(new LaboratorioSql(oracle.getIDlaboratorio()));
		
		//confronta elemento ottenuto con quello dell'oracolo
		assertEquals("query non ha prodotto il risultato atteso", oracle, actualObj);
	}

	@Test
	public void testQuery()throws Exception {
		System.out.println("Testing: Query per ottenere una lista di laboratori e verificare se e' presente oracle nella lista");
		boolean val = false; //se rimane false --> FAIL
		
		//ottieni lista dei risultati
		List<Laboratorio> actualObjs = repository.query(new ListaLab());
		
		//verifica se la postazione "oracle" e' presente nella lista.. se presente --> OK
		for(Laboratorio l : actualObjs){
			if(l.getIDlaboratorio().equals(oracle.getIDlaboratorio())){
				val = true;
				break;
			}
		}
		
		assertTrue("Oracle non e' presente nella lista", val);
	}

}
