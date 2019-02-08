package postazioneTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import businessLogic.Postazione.GetPostazioneByLabSQL;

public class GetPostazioneByLabSQLTest {

	@Test
	public void testToSqlQuery() {
		System.out.println("toSqlQuery");
		String oracle = "SELECT * FROM postazione WHERE laboratorio = 0";
		GetPostazioneByLabSQL result = new GetPostazioneByLabSQL("0");
		assertEquals(oracle,result.toSqlQuery());
	}

}
