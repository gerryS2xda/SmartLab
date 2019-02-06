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
	
	private AvvisoRepository ar;
	private Avviso oracle;
	
	
	@Before
	public void setUp() throws SQLException{
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		ar = new AvvisoRepository();
		oracle.setId(1);
		oracle.setMessaggio("Messaggio test");
		oracle.setTitolo("Titolo test");
		oracle.setData(data);
		oracle.setAddetto("test");
		ar.add(oracle);
	}
	
	@After
	public void tearDown() throws SQLException{
		ar.delete(oracle);
	}
	
	@Test
	public void testAdd() throws SQLException{
		Avviso res = ar.findItemByQuery(new AvvisoSql(oracle.getId()));
		assertEquals(res, oracle);
	}
	
	@Test
	public void testDelete() throws SQLException{
		ar.delete(oracle);
		Avviso res = ar.findItemByQuery(new AvvisoSql(oracle.getId()));
		assertEquals(res, null);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		Avviso res = ar.findItemByQuery(new AvvisoSql(oracle.getId()));
		assertEquals(res, oracle);
	}
	
	@Test
	public void testQuery() throws SQLException{
		List<Avviso> lista = new ArrayList<Avviso>();
		lista = ar.query(new ListaAvvisi());
		assertEquals(lista.get(lista.size() - 1), oracle);
	}

}
