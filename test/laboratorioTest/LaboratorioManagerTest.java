package laboratorioTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import businessLogic.laboratorio.LaboratorioManager;
import businessLogic.laboratorio.LaboratorioRepository;
import businessLogic.laboratorio.LaboratorioSql;
import dataAccess.storage.bean.Laboratorio;

public class LaboratorioManagerTest {

	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LaboratorioRepository result = LaboratorioRepository.getInstance();
        assertNotNull(result);
        
	}
	
	@Test
	public void testCreateLaboratory(){
		System.out.println("createLaboratory");
		Laboratorio lab= new Laboratorio();
		lab.setNome("Lab1");
		lab.setPosti(100);
		lab.setIDlaboratorio("12345678");
		lab.setStato(true);
		//---------
		LaboratorioManager instance=LaboratorioManager.getInstance();
		assertTrue(instance.createLaboratory(lab));
		instance.removeLaboratory(lab);
	}

	@Test
	public void testDeleteLaboratory(){
		System.out.println("remuveLaboratory");
		Laboratorio lab= new Laboratorio();
		lab.setNome("Lab1");
		lab.setPosti(100);
		lab.setIDlaboratorio("12345678");
		lab.setStato(true);
		//---------
		LaboratorioManager instance=LaboratorioManager.getInstance();
		assertTrue(instance.removeLaboratory(lab));
		
	}
	
	@Test
	public void testGetLaboratoryList() throws SQLException{
		System.out.println("getLaboratoryList");
		Laboratorio lab= new Laboratorio();
		lab.setNome("Lab1");
		lab.setPosti(100);
		lab.setIDlaboratorio("12345678");
		lab.setStato(true);
		List<Laboratorio> laboratori =new ArrayList<Laboratorio>();
		//---------
		LaboratorioManager instance=LaboratorioManager.getInstance();
		instance.createLaboratory(lab);
		laboratori=instance.getLaboratoryList();
		assertTrue(!laboratori.isEmpty());
	}
}
