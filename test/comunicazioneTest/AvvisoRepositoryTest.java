package comunicazioneTest;

import businessLogic.comunicazione.AvvisoRepository;
import dataAccess.storage.bean.Avviso;
import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;

public class AvvisoRepositoryTest {
	
	@Test
	public void testAdd(){
		Avviso av = new Avviso(20, "Titolo", "Messaggio", new Data(), 12);
		AvvisoRepository ar = new AvvisoRepository();
		ar.add(av);
		AvvisoSql avviso = new AvvisoSql(av.getId());
		Avviso ris = ar.findItemByQuery(avviso);
		assertEquals(av, ris);
		ar.delete(av);
	}
	
	@Test
	public void testDelete(){
		Avviso av = new Avviso(21, "Titolo", "Messaggio", new Data(), 13);
		AvvisoRepository ar = new AvvisoRepository();
		ar.add(av);
		AvvisoSql avviso = new AvvisoSql(av.getId());
		ar.delete(av);
		Avviso ris = ar.findItemByQuery(avviso);
		assertEquals(null, ris);
	}
	
	@Test
	public void testFindItemByQuery(){
		Avviso av = new Avviso(21, "Titolo", "Messaggio", new Data(), 13);
		AvvisoRepository ar = new AvvisoRepository();
		ar.add(av);
		AvvisoSql avviso = new AvvisoSql(av.getId());
		Avviso ris = ar.findItemByQuery(avviso);
		assertEquals(av, ris);
	}
	
	@Test
	public void testQuery(){
		Avviso av = new Avviso(21, "Titolo", "Messaggio", new Data(), 13);
		AvvisoRepository ar = new AvvisoRepository();
		ar.add(av);
		List<Avviso> lista = new ArrayList<Avviso>();
		lista = ar.query(new ListaAvvisi());
		assertTrue(!lista.isEmpty());
	}
}
