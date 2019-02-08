package postazioneTest;

import businessLogic.Postazione.PostazioneManager;
import businessLogic.Postazione.PostazioneRepository;
import businessLogic.Postazione.PostazioneSql;
import dataAccess.storage.bean.Intervento;
import dataAccess.storage.bean.Postazione;
import dataAccess.storage.bean.Prenotazione;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;


public class PostazioneManagerTest {

	private PostazioneManager instance;
	private PostazioneRepository repository;
	private Postazione oracle;
	
	@Before
	public void setUp() throws Exception {
		instance = PostazioneManager.getInstance();
		repository = PostazioneRepository.getInstance();
		oracle =new Postazione(0, "0",true);
	}

	@Test
	public void testGetInstance() {
		System.out.println("Testing: get Instance");
		
		PostazioneManager manager=PostazioneManager.getInstance();
		assertNotNull("Repository object e' nullo", manager);
	}


	@Test
	public void testCreaPostazione() throws SQLException{

		boolean flag=true;
		System.out.println("testing: crea");
		
		Postazione actualObj = new Postazione(0, "1", true);
		//-----------------
		flag=instance.creaPostazione(actualObj.getNumero(), actualObj.getLaboratorio(), actualObj.isStato());
		System.out.println(flag);
		assertTrue(flag);
		repository.delete(actualObj);
	}

	@Test
	public void testAttivaPostazione() throws SQLException {
		boolean flag=true;
		System.out.println("testing: attiva");
		//-----------------
		int num=oracle.getNumero();
		String s=""+num;
		flag=instance.attivaPostazione(s,oracle.getLaboratorio());
		System.out.println(oracle.isStato());
		System.out.println(flag);
		assertTrue(flag);
	}

	@Test
	public void testDisattivaPostazione() throws SQLException {
		boolean flag=true;
		System.out.println("testing: disattiva");
		
		//-----------------
		int num=oracle.getNumero();
		String s=""+num;
		Intervento in=new Intervento();
		flag=instance.disattivaPostazione(s,oracle.getLaboratorio(),in);
		System.out.println(oracle.isStato());
		System.out.println(flag);
		assertTrue(flag);
	}

	@Test
	public void testLiberaPostazione() throws SQLException {
		Prenotazione pre=new Prenotazione();
		boolean flag=true;
		System.out.println("testing: libera");
		//-----------------
		
		flag=instance.liberaPostazione(pre);
		System.out.println(flag);
		assertTrue(flag);
	}

	@Test
	public void testListaPostazioni() throws SQLException {
		System.out.println("testing: listaPos");
		List<Postazione> postazioni=new ArrayList<>();
		
		postazioni=instance.listaPostazioni(oracle.getLaboratorio());
		assertTrue(!postazioni.isEmpty());
	}

	public void testDeletePos() throws SQLException { 
		
		System.out.println("testing: delete");
		PostazioneSql sql=new PostazioneSql(oracle.getNumero(),oracle.getLaboratorio());
			repository.delete(oracle);
			Postazione pos=repository.findItemByQuery(sql);
			assertEquals(pos,oracle);
	}
}
