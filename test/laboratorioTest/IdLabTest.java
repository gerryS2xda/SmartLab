package laboratorioTest;

import static org.junit.Assert.assertEquals;
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
	public void test() {
		System.out.println("toSqlQuery");
		
		String oracle = "SELECT * FROM laboratorio WHERE nome='lab1';";
		IdLab actualObj = new IdLab("lab1");
		assertEquals(oracle, actualObj.toSqlQuery());
	}

}
