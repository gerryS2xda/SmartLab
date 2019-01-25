package responsabileTest

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.responsabile.ResponsabileList;

public class ResponsabileListTest {
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ResponsabileList result = ResponsabileList.getInstance();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("toSqlQuery");
		ResponsabileList list = new ResponsabileList();
		ResponsabileList result = ResponsabileList.getInstance();
		assertEquals(list.toSqlQuery(), result.toSqlQuery());
	}

}