import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import businessLogic.comunicazione.AvvisoRepository;
import businessLogic.comunicazione.AvvisoSql;
import businessLogic.comunicazione.ListaAvvisi;
import dataAccess.storage.bean.Avviso;

public class AvvisoRepositoryTest {

	@Test
	public void testAdd() throws SQLException{
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		Avviso av = new Avviso(30, "Titolo", "Messaggio", data, "g.paolisi@unisa.it");
		AvvisoRepository ar = new AvvisoRepository();
		ar.add(av);
		AvvisoSql avviso = new AvvisoSql(av.getId());
		Avviso ris = ar.findItemByQuery(avviso);
		assertEquals(av, ris);
		ar.delete(av);
	}
	
	@Test
	public void testDelete() throws SQLException{
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		Avviso av = new Avviso(40, "Titolo", "Messaggio", data, "g.paolisi@unisa.it");
		AvvisoRepository ar = new AvvisoRepository();
		ar.add(av);
		AvvisoSql avviso = new AvvisoSql(av.getId());
		ar.delete(av);
		Avviso ris = ar.findItemByQuery(avviso);
		assertEquals(null, ris);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		Avviso av = new Avviso(35, "Titolo", "Messaggio", data, "g.paolisi@unisa.it");
		AvvisoRepository ar = new AvvisoRepository();
		ar.add(av);
		AvvisoSql avviso = new AvvisoSql(av.getId());
		Avviso ris = ar.findItemByQuery(avviso);
		assertEquals(av, ris);
	}
	
	@Test
	public void testQuery() throws SQLException{
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		Avviso av = new Avviso(21, "Titolo", "Messaggio", data, "g.paolisi@unisa.it");
		AvvisoRepository ar = new AvvisoRepository();
		ar.add(av);
		List<Avviso> lista = new ArrayList<Avviso>();
		lista = ar.query(new ListaAvvisi());
		assertTrue(!lista.isEmpty());
	}

}
