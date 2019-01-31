package accountTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import businessLogic.account.SospensioneRepository;
import businessLogic.account.AccountSQL;
import businessLogic.account.SospensioneList;
import dataAccess.storage.bean.Sospensione;

public class SospensioneRepositoryTest {
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        SospensioneRepository result = SospensioneRepository.getInstance();
        assertNotNull(result);
        
	}
	@Test
	public void testAdd() throws SQLException{
		System.out.println("add");
		Sospensione s = new Sospensione();
		s.setID("1");
		s.setDurata("10");
		s.setData(new Date());
		s.setMotivazione("ppp");
		s.setStudente(new Studente());
		s.setAddetto(new Addetto());
		
		SospensioneRepository instance= SospensioneRepository.getInstance();
		instance.add(s);
		AccountSQL sql = new AccountSQL(s.getID());
		Sospensione result = instance.findItemByQuery(sql);
		assertEquals(s,result);
		instance.delete(s);
	}
	
	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		Sospensione s= new Sospensione();
		s.setID("1");
		s.setDurata("10");
		s.setData(new Date());
		s.setMotivazione("ppp");
		s.setStudente(new Studente());
		s.setAddetto(new Addetto());
		
		SospensioneRepository instance= SospensioneRepository.getInstance();
		instance.add(s);
		AccountSQL sql = new AccountSQL(s.getID());
		instance.delete(s);
		Sospensione result = instance.findItemByQuery(sql);
		assertEquals(null,result);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		Sospensione s= new Sospensione();
		s.setID("1");
		s.setDurata("10");
		s.setData(new Date());
		s.setMotivazione("ppp");
		s.setStudente(new Studente());
		s.setAddetto(new Addetto());
		
		SospensioneRepository instance= SospensioneRepository.getInstance();
		instance.add(s);
		AccountSQL sql = new AccountSQL(s.getID());
		Sospensione result = instance.findItemByQuery(sql);
		assertEquals(s,result);
		instance.delete(s);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		Sospensione s= new Sospensione();
		s.setID("1");
		s.setDurata("10");
		s.setData(new Date());
		s.setMotivazione("ppp");
		s.setStudente(new Studente());
		s.setAddetto(new Addetto());
		List<Sospensione> sospesi = new ArrayList<Sospensione>();

		SospensioneRepository.getInstance().add(s);
		sospesi = SospensioneRepository.getInstance().query(new SospensioneList());
		assertTrue(!sospesi.isEmpty());

	}

}