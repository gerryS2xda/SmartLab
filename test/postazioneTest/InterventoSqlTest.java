package postazioneTest;

import businessLogic.Postazione.InterventoSql;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class InterventoSqlTest {

	@Test
	public void testToSqlQuery() {
		System.out.println("testing: toSqlQuery");
		
		String oracle = "SELECT * FROM intervento WHERE idIntervento = 1";
		
		InterventoSql result=new InterventoSql(1);
		assertEquals(oracle,result.toSqlQuery());
	}

}
