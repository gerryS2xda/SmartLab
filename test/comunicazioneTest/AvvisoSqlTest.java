package comunicazioneTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import businessLogic.comunicazione.AvvisoSql;

public class AvvisoSqlTest {
	
	@Test
	public void testToSqlQuery(){
		AvvisoSql avSql = new AvvisoSql(15);
		AvvisoSql ris = new AvvisoSql(15);
		assertEquals(avSql.toSqlQuery(), ris.toSqlQuery());
	}
}
