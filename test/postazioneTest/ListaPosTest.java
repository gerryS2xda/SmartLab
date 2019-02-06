import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import businessLogic.Postazione.ListaPos;

public class ListaPosTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ListaPos result = ListaPos.getInstance();
        assertNotNull(result);
        
	}
	@Test
	public void testToSqlQuery() {
		System.out.println("toSqlQuery");
		ListaPos lista=new ListaPos("");
		ListaPos result=ListaPos.getInstance();
		assertEquals(lista.toSqlQuery(),result.toSqlQuery());
	}
}
