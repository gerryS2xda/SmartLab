package utenteTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.comunicazione.AvvisoByNameSQL;
import businessLogic.comunicazione.AvvisoSql;
import businessLogic.comunicazione.ListaAvvisi;
import businessLogic.utente.SospensioneList;
import businessLogic.utente.SospensioneRepository;
import businessLogic.utente.SospensioneSQL;
import dataAccess.storage.bean.Avviso;
import dataAccess.storage.bean.Sospensione;


public class SospensioneRepositoryTest {
	
	private SospensioneRepository repository;
	private Sospensione oracle;
	
	@Before
	public void setUp() throws Exception {
		repository=SospensioneRepository.getInstance();
		
		oracle = new Sospensione(1, "pippo", "esempio1@unisa.it");
		repository.add(oracle);
		
		oracle=repository.findItemByQuery(new SospensioneSQL(oracle.getID()));
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
		repository.delete(oracle);
	}

	@Test
	public void testGetInstance() {
		System.out.println("getInstance");
        SospensioneRepository result = SospensioneRepository.getInstance();
        assertNotNull(result);
	}


	@Test
	public void testAdd() throws SQLException {
		System.out.println("add");
		
		Sospensione result=repository.findItemByQuery(new SospensioneSQL(oracle.getID()));

		assertEquals(oracle,result);
	}

	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		
		repository.delete(oracle);
		
		Sospensione result=repository.findItemByQuery(new SospensioneSQL(oracle.getID()));

		assertEquals(-1,result.getID());
	}
	
	@Test
	public void testUpdate() throws SQLException{
		System.out.println("Testing: Update");
		
		//crea oggetto simile ad oracle 
		Sospensione actualObj = new Sospensione();
		actualObj.setID(oracle.getID());
		actualObj.setMotivazione(oracle.getMotivazione());
		actualObj.setStudente(oracle.getStudente());
		actualObj.setData(LocalDate.now().toString());
		actualObj.setAddetto("addettoTest");
		
		//modifica qualche attributo di actualObj
		int durata = 2;
		actualObj.setDurata(2); 
		
		//invocazione di update()
		repository.update(actualObj);
		
		//prendi oggetto da DB appena modificato
		actualObj = repository.findItemByQuery(new SospensioneSQL(oracle.getID()));
		
		//controlla se la modifica e' stata effettuata 
		assertEquals("La modifica non e' stata apportata", durata, actualObj.getDurata());	
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		
		Sospensione result=repository.findItemByQuery(new SospensioneSQL(oracle.getID()));
		
		assertEquals(oracle,result);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		
		boolean val = false; //se rimane false --> FAIL
		
		//ottieni lista dei risultati
		List<Sospensione> lista=repository.query(new SospensioneList());
		
		for(Sospensione s : lista){
			if(s.getID() == oracle.getID()){
				val = true;
				break;
			}
		}

	}

}
