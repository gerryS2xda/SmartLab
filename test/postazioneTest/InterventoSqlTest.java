package postazioneTest;
import static org.junit.Assert.*;

import org.junit.Test;
import businessLogic.Postazione.InterventoSql;

public class InterventoSqlTest {

	@Test
	public void testInterventoSql() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInstance() {
		System.out.println("test: getInstance");
		InterventoSql result = InterventoSql.getInstance(0);
        assertNotNull(result);
	}

	@Test
	public void testToSqlQuery() {
		System.out.println("testing: toSqlQuery");
		InterventoSql lista=new InterventoSql(1);
		InterventoSql result=InterventoSql.getInstance(0);
		assertEquals(lista.toSqlQuery(),result.toSqlQuery());
	}

}
