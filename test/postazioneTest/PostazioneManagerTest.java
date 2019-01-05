import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.Repository;

import businessLogic.Postazione.PostazioneManager;
import businessLogic.Postazione.PostazioneRepository;
import businessLogic.Postazione.PostazioneSql;
import dataAccess.storage.bean.Postazione;

public class PostazioneManagerTest {

	@Test
	public void testCrea() throws SQLException
	{
		boolean flag=true;
		System.out.println("crea");
		Postazione pos= new Postazione();
		//-----------------
		PostazioneManager pm=PostazioneManager.getInstance();
		flag=pm.creaPostazione(1, "lab1", true);
		System.out.println(flag);
	}
	
	@Test
	public void testAttiva() throws SQLException
	{
		boolean flag=true;
		System.out.println("attiva");
		Postazione pos= new Postazione();
		//-----------------
		PostazioneManager pm=PostazioneManager.getInstance();
		flag=pm.attivaPostazione(pos);
		System.out.println(pos.isStato());
		System.out.println(flag);
	}
	
	@Test
	public void testDisattiva() throws SQLException
	{
		boolean flag=true;
		System.out.println("disattiva");
		Postazione pos= new Postazione();
		//-----------------
		PostazioneManager pm=PostazioneManager.getInstance();
		flag=pm.disattivaPostazione(pos);
		System.out.println(pos.isStato());
		System.out.println(flag);
	}
	
	@Test
	public void testLibera() throws SQLException
	{
		boolean flag=true;
		System.out.println("libera");
		Postazione pos= new Postazione();
		//-----------------
		PostazioneManager pm=PostazioneManager.getInstance();
		flag=pm.liberaPostazione(pos);
		System.out.println(flag);
	}
	
	@Test
	public void testLista() throws SQLException
	{
		System.out.println("libera");
		Postazione pos=new Postazione();
		List<Postazione> postazioni=new ArrayList<>();
		
		pos.setLaboratorio("lab1");
		pos.setNumero(1);
		pos.setStato(true);
		
		PostazioneManager instance=PostazioneManager.getInstance();
		PostazioneRepository repository=PostazioneRepository.getInstance();
		
		postazioni.add(pos);
		repository.add(postazioni);
		assertTrue(!postazioni.isEmpty());
		repository.delete(postazioni);
	}
	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
