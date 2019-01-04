package laboratorioTest;

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.laboratorio.LaboratorioSql;
import businessLogic.laboratorio.ListaLab;

public class LaboratorioSqlTest {
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LaboratorioSql result = LaboratorioSql.getInstance("");
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
	}
	
	@Test
	public void testToSqlQuery() {
		System.out.println("toSqlQuery");
		LaboratorioSql lab=new LaboratorioSql("lab1");
		LaboratorioSql result=LaboratorioSql.getInstance("lab1");
		assertEquals(lab.toSqlQuery(),result.toSqlQuery());
	}

}
