package postazioneTest;

import businessLogic.Postazione.ListaPos;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class ListaPosTest {

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
