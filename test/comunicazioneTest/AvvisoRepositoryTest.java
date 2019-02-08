package comunicazioneTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import businessLogic.comunicazione.AvvisoByNameSQL;
import businessLogic.comunicazione.AvvisoRepository;
import businessLogic.comunicazione.AvvisoSql;
import businessLogic.comunicazione.ListaAvvisi;
import dataAccess.storage.bean.Avviso;

public class AvvisoRepositoryTest {

	//instance field
	private AvvisoRepository repository;
	private Avviso inp; //oggetto che funge da oracolo per il test
		
	@Before
	public void setUp() throws Exception {
		
		//ottieni istanza di un oggetto repository
		repository = AvvisoRepository.getInstance();
		
		inp = new Avviso(0, "Messaggio test", "Titolo test", Date.valueOf(LocalDate.now()), "test");
		repository.add(inp);
		
		//ottieni l'ID dopo inserimento poiche' si usa auto_increment (serve per delete)
		Avviso temp = repository.findItemByQuery(new AvvisoByNameSQL(inp.getTitolo()));
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
		Avviso actualObj = repository.findItemByQuery(new AvvisoSql(inp.getId()));
		
		//verifica se l'oggetto ottenuto e' uguale a quello inserito
		assertEquals("Oggetto Avviso non e' stato inserito", inp, actualObj);
	}

	@Test
	public void testGetInstance()throws Exception {
		System.out.println("Testing: get Instance");
		repository = AvvisoRepository.getInstance();
		
		assertNotNull("Repository object e' nullo", repository);
	}
	
	@Test
	public void testDelete()throws Exception {
		System.out.println("Testing: test remove to DB");
		
		//rimozione dell'oggetto
		repository.delete(inp); 
		
		//verifica se e' stato cancellato
		Avviso actualObj = repository.findItemByQuery(new AvvisoSql(inp.getId()));
		assertEquals("Oggetto Avviso non e' stato rimosso", 0, actualObj.getId()); //in questo caso, 0 rappresenta l'oracolo del test
	}

	@Test
	public void testUpdate()throws Exception {
		System.out.println("Testing: Update");
		
		//crea oggetto simile ad oracle 
		Avviso actualObj = new Avviso(inp.getId(), inp.getTitolo(), inp.getMessaggio(), inp.getData(), inp.getAddetto());
		
		//modifica qualche attributo di actualObj
		Date oracle = Date.valueOf(LocalDate.now().plusDays(1));
		actualObj.setData(oracle); //cambia la data
		
		//invocazione di update()
		repository.update(actualObj);
		
		//prendi oggetto da DB appena modificato
		actualObj = repository.findItemByQuery(new AvvisoSql(inp.getId()));
		
		//controlla se la modifica e' stata effettuata 
		assertEquals("La modifica non e' stata apportata", oracle.toString(), actualObj.getData().toString());	
	}

	@Test
	public void testFindItemByQuery()throws Exception {
		System.out.println("Testing: Find item by id");
		
		//trova elemento in base alla email dell'oracolo
		Avviso actualObj = repository.findItemByQuery(new AvvisoSql(inp.getId()));
		
		//confronta elemento ottenuto con quello dell'oracolo
		assertEquals("query non ha prodotto il risultato atteso", inp, actualObj);
	}

	@Test
	public void testQuery()throws Exception {
		System.out.println("Testing: Query per ottenere una lista di postazioni e verificare se e' presente oracle nella lista");
		boolean val = false; //se rimane false --> FAIL
		
		//ottieni lista dei risultati
		List<Avviso> actualObjs = repository.query(new ListaAvvisi());
		
		//verifica se la postazione "oracle" e' presente nella lista.. se presente --> OK
		for(Avviso a : actualObjs){
			if(a.getId() == inp.getId()){
				val = true;
				break;
			}
		}
		
		assertTrue("Oracle non e' presente nella lista", val);
	}

}
