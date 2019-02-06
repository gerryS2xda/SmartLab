package laboratorioTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import businessLogic.Postazione.ListaPos;
import businessLogic.Postazione.PostazioneRepository;
import businessLogic.laboratorio.IdLab;
import businessLogic.laboratorio.LaboratorioManager;
import businessLogic.laboratorio.LaboratorioRepository;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;

public class LaboratorioManagerTestCase1 {
	
	private LaboratorioManager manager;
	private LaboratorioRepository repository;
	private PostazioneRepository repositoryp;
	private Laboratorio oracle;
	private Postazione postazione;
	private int posti=10;

	@Before
	public void setUp() throws Exception {
		manager=LaboratorioManager.getInstance();
		repository=LaboratorioRepository.getInstance();
		repositoryp=PostazioneRepository.getInstance();
		
		oracle=new Laboratorio();
		oracle.setNome("lab10");
		oracle.setPosti(posti);
		oracle.setStato(true);
		oracle.setApertura(LocalTime.parse("9:00"));
		oracle.setChiusura(LocalTime.parse("17:00"));
		
		manager.createLaboratory(oracle);
		
		//ottego l'id 
		Laboratorio temp=repository.findItemByQuery(new IdLab(oracle.getNome()));
		oracle.setIDlaboratorio(temp.getIDlaboratorio());
	}

	@After
	public void tearDown() throws Exception {
		for(int i=0;i<oracle.getPosti();i++){
			postazione.setLaboratorio(oracle.getIDlaboratorio());
			postazione.setNumero(i+1);//le postazioni sono numerate da 1 fino a posti
			repositoryp.delete(postazione);
		}
		repository.delete(oracle);
	}

	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LaboratorioRepository result = LaboratorioRepository.getInstance();
        assertNotNull(result);
        
	}
	
	@Test
	public void testCreateLaboratory() throws SQLException{
		System.out.println("createLaboratory");
		//l'oracolo viene inserito con setUp()
		Laboratorio result=repository.findItemByQuery(new IdLab(oracle.getNome()));
		//postazioni generate dalla creazione del laboratorio
		List<Postazione> postazioni=repositoryp.query(new ListaPos(oracle.getIDlaboratorio()));
		
		assertEquals(result,oracle);
		assertTrue(!postazioni.isEmpty());
	}
	
	@Test
	public void testDeleteLaboratory() throws SQLException{
		System.out.println("remuveLaboratory");
		
		manager.removeLaboratory(oracle);
		
		Laboratorio result=repository.findItemByQuery(new IdLab(oracle.getNome()));
		//l'oracolo deve essere null 
		assertEquals(null,result);
	}
	
	@Test
	public void testGetLaboratoryList() throws SQLException{
		System.out.println("getLaboratoryList");
		
		List<Laboratorio> laboratori=manager.getLaboratoryList();
		
		assertEquals(laboratori.get(laboratori.size()-1),oracle);
	}
	
	@Test
	public void testGetLaboratoryListForResp(){
		System.out.println("getLaboratoryListForResp");
		
		List<Laboratorio> laboratori=manager.getLaboratoryListForResp("esempio1@unisa.it");
		
		assertEquals(laboratori.get(laboratori.size()-1),oracle);
	}

}
