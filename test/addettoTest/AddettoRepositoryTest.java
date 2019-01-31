package responsabileTest;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import businessLogic.addetto.AddettoRepository;
import dataAccess.storage.bean.Addetto;

public class AddettoRepositoryTest{
	
	@Test
	public void testGetInstance(){
		System.out.println("getInstance");
		AddettoRepository result = AddettoRepository.getInstance();
		assertNotNull(result);
	}
	
	@Test
	public void testAdd() throws SQLException{
		System.out.println("add");
		Addetto a = new Addetto();
		a.setEmail("email1@unisa.it");
		a.setTipo(false);
		
		AddettoRepository instance = AddettoRepository.getInstance();
		instance.add(a);
		AddettoSQL sql = new AddettoSQL(a.getEmail());
		Addetto result = instance.findItemByQuery(sql);
		assertEquals(a, result);
		instance.delete(a);
	}
	
	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		Addetto a = new Addetto();
		a.setEmail("email1@unisa.it");
		a.setTipo(false);
		
		AddettoRepository instance = AddettoRepository.getInstance();
		instance.add(a);
		AddettoSQL sql = new AddettoSQL(a.getEmail());
		instance.delete(a);
		Addetto result = instance.findItemByQuery(sql);
		assertEquals(null, result);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		Addetto a = new Addetto();
		a.setEmail("email1@unisa.it");
		a.setTipo(false);
		
		AddettoRepository instance = AddettoRepository.getInstance();
		instance.add(a);
		AddettoSQL sql = new AddettoSQL(a.getEmail());
		Addetto result = instance.findItemByQuery(sql);
		assertEquals(a, result);
		instance.delete(a);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		Addetto a = new Addetto();
		a.setEmail("email1@unisa.it");
		a.setTipo(false);
		List<Addetto> addetti = new ArrayList<Addetto>();
		
		AddettoRepository.getInstance().add(a);
		addetti = AddettoRepository.getInstance().query(new AddettoList());
		assertTrue(!addetti.isEmpty());
}
