package comunicazioneTest;

import static org.junit.Assert.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.comunicazione.ListaSegnalazioni;
import businessLogic.comunicazione.SegnalazioneByOggetto;
import businessLogic.comunicazione.SegnalazioneRepository;
import businessLogic.comunicazione.SegnalazioneSql;
import dataAccess.storage.bean.Segnalazione;

public class SegnalazioneRepositoryTest {

	//instance field
	private SegnalazioneRepository repository;
	private Segnalazione inp; //oggetto che funge da oracolo per il test
		
	@Before
	public void setUp() throws Exception {
		
		//ottieni istanza di un oggetto repository
		repository = new SegnalazioneRepository();
				
		inp = new Segnalazione(0, "Oggetto test", "Descrizione test", Date.valueOf(LocalDate.now()), "test", "0", 16);
		repository.add(inp);
		
		//ottieni l'ID dopo inserimento poiche' si usa auto_increment (serve per delete)
		Segnalazione temp = repository.findItemByQuery(new SegnalazioneByOggetto(inp.getOggetto()));
		inp.setId(temp.getId());
	}

	@After
	public void tearDown() throws Exception {
		repository.delete(inp);
	}

	@Test
	public void testAdd()throws Exception {
		System.out.println("Testing: test add to DB");
		//inserisco nuovo oggetto nel DB, lo prelevo e verifica se e' stato aggiunto
		//oggetto da testare e' stato gia' inserito con setUp()
		Segnalazione actualObj = repository.findItemByQuery(new SegnalazioneSql(inp.getId()));
		
		//verifica se l'oggetto ottenuto e' uguale a quello inserito
		assertEquals("Oggetto Segnalazione non e' stato inserito", inp, actualObj);
	}

	@Test
	public void testDelete()throws Exception {
		System.out.println("Testing: test remove to DB");
		
		//rimozione dell'oggetto
		repository.delete(inp); 
		
		//verifica se e' stato cancellato
		Segnalazione actualObj = repository.findItemByQuery(new SegnalazioneSql(inp.getId()));
		assertEquals("Oggetto Segnalazione non e' stato rimosso", 0, actualObj.getId()); //in questo caso, null rappresenta l'oracolo del test
	}

	@Test
	public void testUpdate()throws Exception {
		System.out.println("Testing: Update");
		
		//crea oggetto simile ad oracle 
		Segnalazione actualObj = new Segnalazione(inp.getId(), inp.getOggetto(), inp.getDescrizione(), inp.getData(), inp.getStudente(), inp.getLaboratorio(), inp.getPostazione());
		
		//modifica qualche attributo di actualObj
		Date oracle = Date.valueOf(LocalDate.now().plusDays(1));
		actualObj.setData(oracle); //cambia la data
		
		//invocazione di update()
		repository.update(actualObj);
		
		//prendi oggetto da DB appena modificato
		actualObj = repository.findItemByQuery(new SegnalazioneSql(inp.getId()));
		
		//controlla se la modifica e' stata effettuata 
		assertEquals("La modifica non e' stata apportata", oracle.toString(), actualObj.getData().toString());
	}

	@Test
	public void testFindItemByQuery()throws Exception {
		System.out.println("Testing: Find item by id");
		
		//trova elemento in base alla email dell'oracolo
		Segnalazione actualObj = repository.findItemByQuery(new SegnalazioneSql(inp.getId()));
		
		//confronta elemento ottenuto con quello dell'oracolo
		assertEquals("query non ha prodotto il risultato atteso", inp, actualObj);
	}

	@Test
	public void testQuery()throws Exception {
		System.out.println("Testing: Query per ottenere una lista di postazioni e verificare se e' presente oracle nella lista");
		boolean val = false; //se rimane false --> FAIL
		
		//ottieni lista dei risultati
		List<Segnalazione> actualObjs = repository.query(new ListaSegnalazioni());
		
		//verifica se la postazione "oracle" e' presente nella lista.. se presente --> OK
		for(Segnalazione s : actualObjs){
			if(s.getId() == inp.getId()){
				val = true;
				break;
			}
		}
		
		assertTrue("Oracle non e' presente nella lista", val);
	}

}
