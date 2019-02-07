package assegnamentoTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import businessLogic.assegnamento.AssegnamentoSql;

public class AssegnamentoSqlTest {
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        AssegnamentoSql result = AssegnamentoSql.getInstance("lab1","resp1");
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("toSqlQuery");
		AssegnamentoSql ass=new AssegnamentoSql("lab1","resp1");
		AssegnamentoSql result=AssegnamentoSql.getInstance("lab1","resp1");
		assertEquals(ass.toSqlQuery(),result.toSqlQuery());
	}

}
