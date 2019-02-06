import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.Repository;

import businessLogic.Postazione.PostazioneManager;
import businessLogic.Postazione.PostazioneRepository;
import businessLogic.Postazione.PostazioneSql;
import businessLogic.prenotazione.PrenotazioneManager;
import dataAccess.storage.bean.Postazione;

public class PostazioneManagerTest {

	PostazioneManager instance=PostazioneManager.getInstance();
	PostazioneRepository repository=PostazioneRepository.getInstance();
	private Postazione oracle;
	@Before
	public void setUp() throws Exception 
	{
		oracle =new Postazione("lab1", 1,true);
	}
	
	@Test
	public void testGetInstance() {
		System.out.println("Testing: get Instance");
		manager = PostazioneManager.getInstance();
		
		assertNotNull("Repository object e' nullo", manager);
	}
	
	@Test
	public void testCrea() throws SQLException
	{
		boolean flag=true;
		System.out.println("testing: crea");
		Postazione pos= new Postazione(oracle.getNumero(),oracle.getLaboratorio(),oracle.isStato());
		//-----------------
		flag=instance.creaPostazione(1, "lab1", true);
		System.out.println(flag);
	}
	
	@Test
	public void testAttiva() throws SQLException
	{
		boolean flag=true;
		System.out.println("testing: attiva");
		//-----------------
		
		flag=instance.attivaPostazione(oracle.getNumero(),oracle.getLaboratorio());
		System.out.println(oracle.isStato());
		System.out.println(flag);
	}
	
	@Test
	public void testDisattiva() throws SQLException
	{
		boolean flag=true;
		System.out.println("testing: disattiva");
		
		//-----------------
		
		flag=instance.disattivaPostazione(oracle.getNumero(),oracle.getLaboratorio());
		System.out.println(oracle.isStato());
		System.out.println(flag);
	}
	
	@Test
	public void testLibera() throws SQLException
	{
		Prenotazione pre=new Prenotazione();
		boolean flag=true;
		System.out.println("testing: libera");
		//-----------------
		
		flag=instance.liberaPostazione(pre);
		System.out.println(flag);
	}
	
	@Test
	public void testListaPos() throws SQLException
	{
		System.out.println("testing: listaPos");
		List<Postazione> postazioni=new ArrayList<>();
		
		postazioni=instance.listaPostazioni(oracle.getLaboratorio());
		assertTrue(!postazioni.isEmpty());
	}
	
	
	public void testDeletePos() 
	{
		System.out.println("testing: delete");
		repository.delete(oracle);
	}
	
	
	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
