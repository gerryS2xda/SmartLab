package addettoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import businessLogic.addetto.AddettoList;
import businessLogic.addetto.AddettoRepository;
import businessLogic.addetto.AddettoSQL;
import dataAccess.storage.bean.Addetto;

public class AddettoRepositoryTest {

	private AddettoRepository repository;
	private Addetto oracle;

	@Before
	public void setUp() throws Exception {
		repository=AddettoRepository.getInstance();

		oracle = new Addetto("test@unisa.studenti.it", "12345678", "Rocco", "Lo Conte");
		oracle.setTipo(false);
		System.out.println(oracle);
		repository.add(oracle);

		oracle=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown");
		repository.delete(oracle);
	}

	@Test
	public void testGetInstance() {
		System.out.println("getInstance");
		AddettoRepository result = AddettoRepository.getInstance();
        assertNotNull(result);
	}


	@Test
	public void testAdd() throws SQLException {
		System.out.println("add");

		Addetto result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));

		assertEquals(oracle,result);
	}

	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");

		repository.delete(oracle);

		Addetto result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));

		assertEquals("",result.getEmail());
	}

	@Test
	public void testUpdate() throws SQLException{

		Addetto result=oracle;

		assertSame(result,oracle);

		result.setPassword("nuovaPass");

		repository.update(result);

		result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));

		assertEquals(oracle,result);
	}

	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");

		Addetto result=repository.findItemByQuery(new AddettoSQL(oracle.getEmail()));

		assertEquals(oracle,result);
	}

	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");

		List<Addetto> lista=repository.query(new AddettoList());

		assertEquals(lista.get(lista.size()-1),oracle);

	}

}