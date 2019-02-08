package prenotazionetest;

import businessLogic.Postazione.GetPostazioneByLabSQL;
import businessLogic.Postazione.ListaPos;
import businessLogic.Postazione.PostazioneRepository;
import businessLogic.Postazione.PostazioneSql;
import dataAccess.storage.bean.Postazione;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PostazioneRepositoryTest {

	//instance field
	private PostazioneRepository repository;
	private Postazione oracle; //oggetto che funge da oracolo per il test
	
	@Before
	public void setUp() throws Exception {
		
		repository = PostazioneRepository.getInstance();
		
		oracle = new Postazione(0, "0", true); //0 rappresenta l'id del laboratorio usato per il test
		
		repository.add(oracle);		
		
		//ottieni il numero dopo inserimento poiche' si usa auto_increment (serve per delete)
		Postazione temp = repository.findItemByQuery(new GetPostazioneByLabSQL(oracle.getLaboratorio()));
		oracle.setNumero(temp.getNumero());
	}

	@After
	public void tearDown() throws Exception {
		repository.delete(oracle);
	}

	@Test
	public void testGetInstance() {
		System.out.println("Testing: get Instance");
		repository = PostazioneRepository.getInstance();
		
		assertNotNull("Repository object e' nullo", repository);
	}

	@Test
	public void testAdd()throws Exception {
		System.out.println("Testing: test add to DB");
		//inserisco nuovo oggetto nel DB, lo prelevo e verifica se e' stato aggiunto
		//oggetto da testare e' stato gia' inserito con setUp()
		Postazione actualObj = repository.findItemByQuery(new PostazioneSql(oracle.getNumero(), oracle.getLaboratorio()));
		
		//verifica se l'oggetto ottenuto e' uguale a quello inserito
		assertEquals("Oggetto prenotazione non e' stato inserito", oracle, actualObj);
	}

	@Test
	public void testDelete()throws Exception {
		System.out.println("Testing: test remove to DB");
		
		//rimozione dell'oggetto
		repository.delete(oracle); 
		
		//verifica se e' stato cancellato
		Postazione actualObj = repository.findItemByQuery(new PostazioneSql(oracle.getNumero(), oracle.getLaboratorio()));
		assertEquals("Oggetto prenotazione non e' stato rimosso", -1, actualObj.getNumero()); //in questo caso, post = 0 rappresenta l'oracolo del test
		
	}

	@Test
	public void testUpdate()throws Exception {
		System.out.println("Testing: Update");
		
		//crea oggetto simile ad oracle 
		Postazione actualObj = new Postazione(oracle.getNumero(), oracle.getLaboratorio(), oracle.isStato());
		
		//modifica qualche attributo di actualObj
		actualObj.setStato(false); //cambia lo stato da true (usato nell'oracle) a false
		
		//invocazione di update()
		repository.update(actualObj);
		
		//prendi oggetto da DB appena modificato
		actualObj = repository.findItemByQuery(new PostazioneSql(oracle.getNumero(), oracle.getLaboratorio()));
		
		//controlla se la modifica e' stata effettuata (controlla se lo stato e' cambiato da true a false)
		assertFalse("La modifica non e' stata apportata", actualObj.isStato());	//se lo stato e' false --> modifica effettuata
	}

	@Test
	public void testFindItemByQuery()throws Exception {
		System.out.println("Testing: Find item by id");
		
		//trova elemento in base alla email dell'oracolo
		Postazione actualObj = repository.findItemByQuery(new PostazioneSql(oracle.getNumero(), oracle.getLaboratorio()));
		
		//confronta elemento ottenuto con quello dell'oracolo
		assertEquals("query non ha prodotto il risultato atteso", oracle, actualObj);
	}

	@Test
	public void testQuery()throws Exception {
		System.out.println("Testing: Query per ottenere una lista di postazioni e verificare se e' presente oracle nella lista");
		boolean val = false; //se rimane false --> FAIL
		
		//ottieni lista dei risultati
		List<Postazione> actualObjs = repository.query(new ListaPos(oracle.getLaboratorio()));
		
		//verifica se la postazione "oracle" e' presente nella lista.. se presente --> OK
		for(Postazione p : actualObjs){
			if(p.getNumero() == oracle.getNumero() && p.getLaboratorio().equals(oracle.getLaboratorio())){
				val = true;
				break;
			}
		}
		
		assertTrue("Oracle non e' presente nella lista", val);
	}

}
