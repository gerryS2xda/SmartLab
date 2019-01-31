package responsabileTest;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import businessLogic.addetto.AddettoManager;
import dataAccess.storage.bean.Addetto;

public class AddettoManagerTest{
	
	@Test
	public void testGetInstance(){
		System.out.println("getInstance");
		AddettoRepository result = AddettoRepository.getInstance();
		assertNotNull(result);
	}
	
	@Test
	public void testRimuoviResp(){
		System.out.println("rimuoviResp");
		Addetto a = new Addetto();
		a.setEmail("email1@unisa.it");
		a.setTipo(false);
		
		AddettoManager instance = AddettoManager.getInstace();
		assertTrue(instance.rimuoviResp(a));
	}
	
	@Test
	public void testAddResp(){
		System.out.println("addResp");
		Addetto a = new Addetto();
		a.setEmail("email1@unisa.it");
		a.setTipo(false);
		
		AddettoManager instance = AddettoManager.getInstace();
		assertTrue(instance.addResp(a));
	}
	
	@Test
	public void testGetListaResp(){
		System.out.println("getListaResp");
		Addetto a = new Addetto();
		a.setEmail("email1@unisa.it");
		a.setTipo(false);
		List<Addetto> list = new ArrayList<Addetto>();
		
		AddettoManager instance = AddettoManager.getInstace();
		instance.addResp(a);
		list = instance.getListaResp();
		assertTrue(!list.isEmpty());
	}
}