package assegnamentoTest;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import businessLogic.assegnamento.AssegnamentoManager;
import dataAccess.storage.bean.Assegnamento;

public class AssegnamentoManagerTest2 {
	
	private AssegnamentoManager manager;
	private Assegnamento oracle;
	
	@Before
	public void setUp() throws Exception {
		manager=AssegnamentoManager.getInstance();
		oracle=null;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetRespToLab() {
		System.out.println("setRespToLab caso null");

		assertTrue(!manager.setRespToLab(oracle));
	}

	@Test
	public void testRemoveResponsabile() {
		System.out.println("removeResponsabile caso null");

		assertTrue(!manager.removeResponsabile(oracle));
	}

}
