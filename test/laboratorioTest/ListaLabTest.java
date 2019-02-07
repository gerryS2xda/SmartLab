package laboratorioTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

import businessLogic.laboratorio.ListaLab;

public class ListaLabTest {
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ListaLab result = ListaLab.getInstance();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("toSqlQuery");
		ListaLab lista=new ListaLab();
		ListaLab result=ListaLab.getInstance();
		assertEquals(lista.toSqlQuery(),result.toSqlQuery());
	}

}
