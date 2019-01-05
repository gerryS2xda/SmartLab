package assegnamentoTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import businessLogic.assegnamento.AssegnamentoManager;
import businessLogic.assegnamento.AssegnamentoRepository;
import businessLogic.assegnamento.AssegnamentoSql;
import businessLogic.laboratorio.LaboratorioRepository;
import dataAccess.storage.bean.Assegnamento;

public class AssegnamentoManagerTest {

	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LaboratorioRepository result = LaboratorioRepository.getInstance();
        assertNotNull(result);
        
	}
	
	@Test
	public void testSetRespToLab() throws SQLException{
		System.out.println("setRespToLab");
		Assegnamento ass= new Assegnamento();
		ass.setLaboratorio("Lab1");
		ass.setResponsabile("resp1");
		//-----------------
		AssegnamentoManager instance= AssegnamentoManager.getInstance();
		instance.setRespToLab(ass);
		AssegnamentoSql sql=new AssegnamentoSql(ass.getLaboratorio(),ass.getResponsabile());
		AssegnamentoRepository repository=new AssegnamentoRepository();
		Assegnamento result=repository.findItemByQuery(sql);
		assertEquals(result,ass);
		repository.delete(ass);
		
	}
	
	@Test
	public void testRemoveResponsabile() throws SQLException{
		System.out.println("removeResponsabile");
		Assegnamento ass= new Assegnamento();
		ass.setLaboratorio("Lab1");
		ass.setResponsabile("resp1");
		//-----------------
		AssegnamentoManager instance= AssegnamentoManager.getInstance();
		instance.removeResponsabile(ass);
		AssegnamentoRepository repository=new AssegnamentoRepository();
		AssegnamentoSql sql=new AssegnamentoSql(ass.getLaboratorio(),ass.getResponsabile());
		Assegnamento result=repository.findItemByQuery(sql);
		assertEquals(null,result);
		repository.delete(ass);
	}
	
	@Test
	public void testShowResponsabileAndLaboratorio() throws SQLException{
		System.out.println("removeResponsabile");
		Assegnamento ass= new Assegnamento();
		ass.setLaboratorio("Lab1");
		ass.setResponsabile("resp1");
		List<Assegnamento> assegnamenti= new ArrayList<Assegnamento>();
		//-----------------
		AssegnamentoRepository repository=new AssegnamentoRepository();
		repository.add(ass);
		AssegnamentoManager instance= AssegnamentoManager.getInstance();
		assegnamenti=instance.showResponsabileAndLaboratorio();
		assertTrue(!assegnamenti.isEmpty());
		repository.delete(ass);
	}

}
