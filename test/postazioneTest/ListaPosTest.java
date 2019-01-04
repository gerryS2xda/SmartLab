package postazioneTest;

import static org.junit.Assert.*;
import org.junit.Test;
import businessLogic_Postazione.postazione.ListaPos;
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
		ListaPos lista=new ListaPos();
		ListaPos result=ListaPos.getInstance();
		assertEquals(lista.toSqlQuery(),result.toSqlQuery());
	}

}
