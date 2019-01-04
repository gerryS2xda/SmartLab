package laboratorioTest;

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.laboratorio.LaboratorioRepository;

public class LaboratorioRepositoriTest {
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LaboratorioRepository result = LaboratorioRepository.getInstance();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
