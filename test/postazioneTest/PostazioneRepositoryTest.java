package postazioneTest;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import businessLogic_Postazione.postazione.PostazioneRepository;
import dataAccess.storage.Specification;
import dataAccess.storage.bean.Postazione;

public class PostazioneRepositoryTest 
{

	@Test
    public void testGetInstance()
	{
        System.out.println("getInstance");
        PostazioneRepository result = PostazioneRepository.getInstance();
        assertNotNull(result);

	}
}
	@Test
	public void testAdd() throws SQLException
	{
		System.out.println("add");
		Postazione pos= new Postazione();
		pos.setNumero(1);
		pos.setLaboratorio("lab1");
		pos.setStato(true);
		//-----------------
		PostazioneRepository instance= PostazioneRepository.getInstance();
		instance.add(pos);
		PostazioneSql sql=new PostazioneSql(pos.getNumero(),pos.getLaboratorio());
		Postazione result=instance.findItemByQuery(sql);
		assertEquals(pos,result);
		instance.delete(pos);
	}
	
	public void delete(Postazione pos) throws SQLException
	{
		System.out.println("delete");
		pos.setNumero(1);
		pos.setLaboratorio("lab1");
		PostazioneRepository instance = PostazioneRepository.getInstance();
		PostazioneSql sql=new PostazioneSql(pos.getNumero(),pos.getLaboratorio());
		instance.add(pos);
		instance.delete(pos);
		Postazione test=instance.findItemByQuery(sql);
		assertEquals(null,test);
	}
	
	public void findItemByQuery(Specification specification) throws SQLException
	{
		System.out.println("findItemByQuery");
		Postazione pos=new Postazione();
		PostazioneRepository instance = PostazioneRepository.getInstance();
		
		pos.setNumero(1);
		pos.setLaboratorio("lab1");
		PostazioneSql sql=new PostazioneSql(pos.getNumero(),pos.getLaboratorio());
		
		instance.add(pos);
		Postazione test=instance.findItemByQuery(sql);
		assertEquals(pos,test);
		instance.delete(pos);
	}
	
	public void query() throws SQLException 
	{
		System.out.println("query");
		Postazione pos=new Postazione();
		
		pos.setNumero(1);
		pos.setLaboratorio("lab1");
		
		PostazioneSql sql=new PostazioneSql(pos.getNumero(),pos.getLaboratorio());
		instance.add(pos);
		
		PostazioneRepository instance = PostazioneRepository.getInstance();
		Postazione test=instance.findItemByQuery(sql);
		assertEquals(pos,test);
		instance.delete(pos);
		
	}
	
	
	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}  
