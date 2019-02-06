package assegnamentoTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.assegnamento.AssegnamentoRepository;
import businessLogic.assegnamento.AssegnamentoSql;
import businessLogic.laboratorio.ListaLab;
import dataAccess.storage.bean.Assegnamento;

public class AssegnamentoRepositoryTest {
	
	private AssegnamentoRepository repository;
	private Assegnamento oracle;
	
	@Before
	public void setUp() throws SQLException{
		repository=AssegnamentoRepository.getInstance();
		
		oracle.setLaboratorio("2");
		oracle.setResponsabile("esempio1@unisa.it");
		
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
		
		assertEquals(result,oracle);
	}
	
	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		repository.delete(oracle);
		Assegnamento result=repository.findItemByQuery(new AssegnamentoSql(oracle.getLaboratorio(),oracle.getResponsabile()));
		assertEquals(null,result);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		
		Assegnamento result=repository.findItemByQuery(new AssegnamentoSql(oracle.getLaboratorio(),oracle.getResponsabile()));
		assertEquals(oracle,result);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		
		List<Assegnamento> lista=repository.query(new ListaLab());
		assertEquals(lista.get(lista.size()-1),oracle);
	}


}
