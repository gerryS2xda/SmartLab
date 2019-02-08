package comunicazioneTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import businessLogic.comunicazione.ListaAvvisi;

public class ListaAvvisiTest {

	@Test
	public void testToSqlQuery() {
		String oracle = "SELECT * FROM avviso";
		ListaAvvisi actual = new ListaAvvisi();
		
		assertEquals(oracle, actual.toSqlQuery());
	}

}
