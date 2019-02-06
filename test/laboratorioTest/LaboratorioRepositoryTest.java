package laboratorioTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.laboratorio.IdLab;
import businessLogic.laboratorio.LaboratorioRepository;
import businessLogic.laboratorio.ListaLab;
import dataAccess.storage.bean.Laboratorio;

public class LaboratorioRepositoryTest {
	
	private LaboratorioRepository repository;
	private Laboratorio oracle;
	
	@Before
	public void setUp() throws SQLException{
		repository=new LaboratorioRepository();
		
		oracle= new Laboratorio();
		oracle.setNome("lab4");
		oracle.setPosti(10);
		oracle.setStato(true);
		oracle.setApertura(LocalTime.parse("9:00"));
		oracle.setChiusura(LocalTime.parse("17:00"));
		System.out.println(oracle);
		repository.add(oracle);
		
		//ottengo l'oggetto completo perch� l'id � auto-increment
		Laboratorio temp=repository.findItemByQuery(new IdLab(oracle.getNome()));
		System.out.println(temp.getIDlaboratorio());
		oracle.setIDlaboratorio(temp.getIDlaboratorio());
		System.out.println(oracle);
	}
	
	@After
	public void tearDown() throws SQLException{
		System.out.println("tearDown");
		oracle=repository.findItemByQuery(new IdLab(oracle.getNome()));
		repository.delete(oracle);
	}
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LaboratorioRepository result = LaboratorioRepository.getInstance();
        assertNotNull(result);
        
	}
	@Test
	public void testAdd() throws SQLException{
		System.out.println("add");
		//l'oracolo viene inserito con setUp()
		//controllo se � stato inserito
		Laboratorio result=repository.findItemByQuery(new IdLab(oracle.getNome()));

		assertEquals(oracle,result);
	}
	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		
		repository.delete(oracle);
		
		Laboratorio result=repository.findItemByQuery(new IdLab(oracle.getNome()));
		//l'oracolo deve essere null 
		assertEquals(null,result);
	}
	
	@Test
	public void testUpdate() throws SQLException{
		
		Laboratorio result=oracle;
		
		assertSame(result,oracle);//verifico che puntano alla stessa istanza
		
		result.setStato(false);
		result.setApertura(LocalTime.parse("11:00"));
		result.setChiusura(LocalTime.parse("19:00"));
		
		repository.update(result);
		
		result=repository.findItemByQuery(new IdLab(oracle.getNome()));
		
		assertEquals(oracle,result);//verifica che la modifica sia stata apportata
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		
		Laboratorio result=repository.findItemByQuery(new IdLab(oracle.getNome()));
		
		assertEquals(oracle,result);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		
		List<Laboratorio> lista=repository.query(new ListaLab());
		
		assertEquals(lista.get(lista.size()-1),oracle);//controlla se l'ultimo elemento inserito � uguale a oracle

	}
	
}
