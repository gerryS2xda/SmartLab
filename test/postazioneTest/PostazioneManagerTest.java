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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class PostazioneManagerTest {

	PostazioneManager instance=PostazioneManager.getInstance();
	PostazioneRepository repository=PostazioneRepository.getInstance();
	private Postazione oracle;
	
	@Before
	public void setUp() throws Exception 
	{
		oracle =new Postazione(1, "lab1",true);
	}

	@Test
	public void testGetInstance() {
		System.out.println("Testing: get Instance");
		
		PostazioneManager manager=PostazioneManager.getInstance();
		assertNotNull("Repository object e' nullo", manager);
	}

	@Test
	public void testPostazioneManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreaPostazione() throws SQLException{

		boolean flag=true;
		System.out.println("testing: crea");
		//-----------------
		flag=instance.creaPostazione(1, "lab1", true);
		System.out.println(flag);
		assertTrue(flag);
	}

	@Test
	public void testAttivaPostazione() throws SQLException
	{
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
	public void testDisattivaPostazione() throws SQLException
	{
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
	public void testLiberaPostazione() throws SQLException
	{
		Prenotazione pre=new Prenotazione();
		boolean flag=true;
		System.out.println("testing: libera");
		//-----------------
		
		flag=instance.liberaPostazione(pre);
		System.out.println(flag);
		assertTrue(flag);
	}

	@Test
	public void testListaPostazioni() throws SQLException
	{
		System.out.println("testing: listaPos");
		List<Postazione> postazioni=new ArrayList<>();
		
		postazioni=instance.listaPostazioni(oracle.getLaboratorio());
		assertTrue(!postazioni.isEmpty());
	}

	public void testDeletePos() throws SQLException 

	{
		System.out.println("testing: delete");
		PostazioneSql sql=new PostazioneSql(oracle.getNumero(),oracle.getLaboratorio());
			repository.delete(oracle);
			Postazione pos=repository.findItemByQuery(sql);
			assertEquals(pos,oracle);
		
	}


}
