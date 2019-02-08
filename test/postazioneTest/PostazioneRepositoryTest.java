package postazioneTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import businessLogic.Postazione.GetPostazioneByLabSQL;
import businessLogic.Postazione.ListaPos;
import businessLogic.Postazione.PostazioneRepository;
import businessLogic.Postazione.PostazioneSql;
import dataAccess.storage.bean.Postazione;

public class PostazioneRepositoryTest {

	//instance field
	private PostazioneRepository repository;
	private Postazione pos;
		
	@Before
	public void setUp() throws Exception {
			
		repository = PostazioneRepository.getInstance();
		
		pos= new Postazione();
		pos.setNumero(0);
		pos.setLaboratorio("0");
		pos.setStato(true);
		repository.add(pos);
		
		//ottieni il numero dopo inserimento poiche' si usa auto_increment (serve per delete)
		Postazione temp = repository.findItemByQuery(new GetPostazioneByLabSQL(pos.getLaboratorio()));
		pos.setNumero(temp.getNumero());
	}

	@After
	public void tearDown() throws Exception {
		repository.delete(pos);
	}
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        PostazioneRepository result = PostazioneRepository.getInstance();
        assertNotNull(result);
	}

	@Test
	public void testAdd() throws SQLException {
		System.out.println("add");
		
		
		PostazioneSql sql=new PostazioneSql(pos.getNumero(),pos.getLaboratorio());
		Postazione result=repository.findItemByQuery(sql);
		System.out.println("RESULT: " + result.getNumero());
		assertEquals(pos,result);
	}


	@Test
	public void testUpdate() throws SQLException {
		System.out.println("testing: update");
		
		pos.setStato(false);
		//-----------------
		
		repository.update(pos);
		PostazioneSql sql=new PostazioneSql(pos.getNumero(),pos.getLaboratorio());
		Postazione result=repository.findItemByQuery(sql);
		assertFalse(result.isStato());
	}
	
	@Test
	public void testDelete() throws SQLException {
		System.out.println("delete");
		
		PostazioneSql sql=new PostazioneSql(pos.getNumero(),pos.getLaboratorio());
		repository.delete(pos);
		Postazione test=repository.findItemByQuery(sql);
		assertEquals(-1,test.getNumero());
	}

	@Test
	public void testFindItemByQuery() throws SQLException {
		System.out.println("findItemByQuery");
		
		PostazioneSql sql=new PostazioneSql(pos.getNumero(),pos.getLaboratorio());
		
		Postazione test=repository.findItemByQuery(sql);
		assertEquals(pos,test);
		
	}
	
	@Test
	public void testQuery() throws SQLException {
		System.out.println("Testing: Query per ottenere una lista di postazioni e verificare se e' presente oracle nella lista");
		boolean val = false; //se rimane false --> FAIL
		
		//ottieni lista dei risultati
		List<Postazione> actualObjs = repository.query(new ListaPos(pos.getLaboratorio()));
		
		//verifica se la postazione "oracle" e' presente nella lista.. se presente --> OK
		for(Postazione p : actualObjs){
			if(p.getNumero() == pos.getNumero() && p.getLaboratorio().equals(pos.getLaboratorio())){
				val = true;
				break;
			}
		}
		
		assertTrue("Oracle non e' presente nella lista", val);
	}
}
