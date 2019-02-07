package laboratorioTest;

import static org.junit.Assert.assertEquals;
import java.sql.SQLException;
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
		Laboratorio result=repository.findItemByQuery(new IdLab(""));
		assertEquals(result.getIDlaboratorio(),"");
	}

	@Test
	public void testRemoveLaboratory() throws SQLException {
		System.out.println("Test removeLaboratory caso parametro null");
		Laboratorio result=new Laboratorio();
		manager.removeLaboratory(oracle);
		result=repository.findItemByQuery(new IdLab(result.getNome()));
		assertNotEquals(result.getIDlaboratorio(),"");
	}


	@Test
	public void testGetLaboratoryListForResp() {
		System.out.println("test getLaboratoryForResp paramentro null");
		List<Laboratorio> laboratori=manager.getLaboratoryListForResp(null);
		assertEquals(laboratori,oracle);
	}

}
