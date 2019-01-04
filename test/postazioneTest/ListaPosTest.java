import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic_Postazione.postazione.ListaPos;

public class ListaPosTest {

	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ListaPos result = ListaPos.getInstance();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
	}
	
	@Test
	public void testToSqlQuery() {
		ListaPos lista=new ListaPos();
		ListaPos result=ListaPos.getInstance();
		assertEquals(lista.toSqlQuery(),result.toSqlQuery());
	}

}
