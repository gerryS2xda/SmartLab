package assegnamentoTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.Postazione.PostazioneRepository;
import businessLogic.assegnamento.AssegnamentoManager;
import businessLogic.assegnamento.AssegnamentoRepository;
import businessLogic.assegnamento.AssegnamentoSql;
import businessLogic.laboratorio.IdLab;
import businessLogic.laboratorio.LaboratorioManager;
import businessLogic.laboratorio.LaboratorioRepository;
import dataAccess.storage.bean.Assegnamento;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;

public class AssegnamentoManagerTest {
	private AssegnamentoManager manager;
	private AssegnamentoRepository repository;
	private Assegnamento oracle;
	
	@Before
	public void setUp() throws Exception {
		manager=AssegnamentoManager.getInstance();
		repository=AssegnamentoRepository.getInstance();
		
		oracle=new Assegnamento("esempio1@unisa.it","2");
		
	}

	@After
	public void tearDown() throws Exception {
		repository.delete(oracle);
	}

	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LaboratorioRepository result = LaboratorioRepository.getInstance();
        assertNotNull(result);
        
	}
	
	@Test
	public void testSetRespToLab() throws SQLException{
		System.out.println("setRespToLab");
		 manager.setRespToLab(oracle);
		 Assegnamento result=repository.findItemByQuery(new AssegnamentoSql(oracle.getLaboratorio(),oracle.getResponsabile()));
		 
		 assertEquals(result.getLaboratorio(),oracle.getLaboratorio());
	}
	
	@Test
	public void testRemoveResponsabile() throws SQLException{
		System.out.println("removeResponsabile");
		repository.add(oracle);
		manager.removeResponsabile(oracle);
		Assegnamento result=repository.findItemByQuery(new AssegnamentoSql(oracle.getLaboratorio(),oracle.getResponsabile()));
		assertEquals(result.getLaboratorio(),"");
	}
	
	@Test
	public void testShowResponsabileAndLaboratorio() throws SQLException{
		System.out.println("removeResponsabile");
		
	}

}
