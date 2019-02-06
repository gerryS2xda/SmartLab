package laboratorioTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.laboratorio.IdLab;

public class IdLabTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        IdLab result = IdLab.getInstance();
        assertNotNull(result);
        
	}

	@Test
	public void test() {
		System.out.println("toSqlQuery");
		IdLab oracle=new IdLab("lab1");
		IdLab result=IdLab.getInstance();
		assertEquals(oracle.toSqlQuery(),result.toSqlQuery());
	}

}
