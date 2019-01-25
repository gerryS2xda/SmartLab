package assegnamentoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.assegnamento.ListaLabAss;
import businessLogic.laboratorio.ListaLab;


public class ListaRespAssTest {
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ListaLabAss result = ListaLabAss.getInstance();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("toSqlQuery");
		ListaLabAss lista=new ListaLabAss();
		ListaLabAss result=ListaLabAss.getInstance();
		assertEquals(lista.toSqlQuery(),result.toSqlQuery());
	}

}
