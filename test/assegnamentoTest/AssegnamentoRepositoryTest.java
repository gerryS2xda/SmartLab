package assegnamentoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import businessLogic.assegnamento.AssegnamentoRepository;
import businessLogic.assegnamento.AssegnamentoSql;
import businessLogic.assegnamento.ListaLabAss;
import dataAccess.storage.bean.Assegnamento;

public class AssegnamentoRepositoryTest {
	
	private AssegnamentoRepository repository;
	private Assegnamento oracle;
	
	@Before
	public void setUp() throws SQLException{
		repository=AssegnamentoRepository.getInstance();
		
		oracle=new Assegnamento("esempio1@unisa.it","2");
		
		repository.add(oracle);
	}
	
	@After
	public void tearDown() throws SQLException{
		repository.delete(oracle);
	}
	
	@Test
	public void testGetInstance() {
        System.out.println("getInstance");
        AssegnamentoRepository result = AssegnamentoRepository.getInstance();
        assertNotNull(result);
        
	}
	@Test
	public void testAdd() throws SQLException{
		System.out.println("add");
		//viene inserito con setUp()
		Assegnamento result=repository.findItemByQuery(new AssegnamentoSql(oracle.getLaboratorio(),oracle.getResponsabile()));
		System.out.println(result);
		assertEquals(result.getLaboratorio(),oracle.getLaboratorio());
	}
	
	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		repository.delete(oracle);
		Assegnamento result=repository.findItemByQuery(new AssegnamentoSql(oracle.getLaboratorio(),oracle.getResponsabile()));
		assertEquals("",result.getLaboratorio());
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		
		Assegnamento result=repository.findItemByQuery(new AssegnamentoSql(oracle.getLaboratorio(),oracle.getResponsabile()));
		assertEquals(oracle.getLaboratorio(),result.getLaboratorio());
	}
	
	
	@Test
	public void testQuery(){
		assertNull(repository.query(new ListaLabAss("")));
	}


}
