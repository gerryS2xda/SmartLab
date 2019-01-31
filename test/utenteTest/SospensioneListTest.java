package accountTest;

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.account.SospensioneList;

public class SospensioneListTest {
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        SospensioneList result = SospensioneList.getInstance();
        assertNotNull(result);        
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("toSqlQuery");
		SospensioneList list = new SospensioneList();
		SospensioneList result = SospensioneList.getInstance();
		assertEquals(lista.toSqlQuery(),result.toSqlQuery());
	}

}