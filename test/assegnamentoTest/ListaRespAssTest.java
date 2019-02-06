package assegnamentoTest;

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.assegnamento.ListaRespAss;


public class ListaRespAssTest {
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ListaRespAss result = ListaRespAss.getInstance();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("toSqlQuery");
		ListaRespAss lista=new ListaRespAss("lab1");
		ListaRespAss result=ListaRespAss.getInstance();
		assertEquals(lista.toSqlQuery(),result.toSqlQuery());
	}

}
