package comunicazioneTest;

import businessLogic.comunicazione.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class AvvisoSqlTest {
	
	@Test
	public void testToSqlQuery(){
		AvvisoSql avSql = new AvvisoSql(15);
		AvvisoSql ris = new AvvisoSql(15);
		assertEquals(avSql.toSqlQuery(), ris.toSqlQuery());
	}
}
