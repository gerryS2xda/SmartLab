package comunicazioneTest;

import businessLogic.comunicazione.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SegnalazioneSqlTest {
	
	@Test
	public void TestToSqlQuery(){
		SegnalazioneSql sgSql = new SegnalazioneSql(20);
		SegnalazioneSql ris = new SegnalazioneSql(20);
		assertEquals(sgSql.toSqlQuery(), ris.toSqlQuery());
	}
}
