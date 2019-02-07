package comunicazioneTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import businessLogic.comunicazione.SegnalazioneSql;

public class SegnalazioneSqlTest {
	
	@Test
	public void testToSqlQuery(){
		SegnalazioneSql sgSql = new SegnalazioneSql(20);
		SegnalazioneSql ris = new SegnalazioneSql(20);
		assertEquals(sgSql.toSqlQuery(), ris.toSqlQuery());
	}
}
