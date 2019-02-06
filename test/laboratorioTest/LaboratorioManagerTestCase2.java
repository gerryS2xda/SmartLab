package laboratorioTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.laboratorio.IdLab;
import businessLogic.laboratorio.LaboratorioManager;
import businessLogic.laboratorio.LaboratorioRepository;
import dataAccess.storage.bean.Laboratorio;

public class LaboratorioManagerTestCase2 {
	
	private LaboratorioManager manager;
	private LaboratorioRepository repository;
	private Laboratorio oracle;


	@Before
	public void setUp() throws Exception {
		manager=LaboratorioManager.getInstance();
		repository=LaboratorioRepository.getInstance();
		oracle=null;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateLaboratory() throws SQLException {
		System.out.println("Test createLaboratory caso parametro null");
		manager.createLaboratory(oracle);
		Laboratorio result=repository.findItemByQuery(new IdLab(oracle.getNome()));
		assertEquals(result,oracle);
	}

	@Test
	public void testRemoveLaboratory() throws SQLException {
		System.out.println("Test removeLaboratory caso parametro null");
		Laboratorio result=new Laboratorio();
		result.setNome("lab10");
		result.setPosti(0);
		result.setStato(true);
		result.setApertura(LocalTime.parse("9:00"));
		result.setChiusura(LocalTime.parse("17:00"));
		
		repository.add(result);
		
		//ottego l'id 
		Laboratorio temp=repository.findItemByQuery(new IdLab(oracle.getNome()));
		result.setIDlaboratorio(temp.getIDlaboratorio());
		
		manager.removeLaboratory(result);
		
		assertNotEquals(result,oracle);
		repository.delete(result);
	}


	@Test
	public void testGetLaboratoryListForResp() {
		System.out.println("test getLaboratoryForResp paramentro null");
		List<Laboratorio> laboratori=manager.getLaboratoryListForResp(null);
		assertEquals(laboratori,oracle);
	}

}
