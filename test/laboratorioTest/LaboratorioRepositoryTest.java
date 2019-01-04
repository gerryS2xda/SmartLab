package laboratorioTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import businessLogic.laboratorio.LaboratorioRepository;
import businessLogic.laboratorio.LaboratorioSql;
import businessLogic.laboratorio.ListaLab;
import dataAccess.storage.bean.Laboratorio;

public class LaboratorioRepositoryTest {
	
	@Test
    public void testGetInstance() {
        System.out.println("getInstance");
        LaboratorioRepository result = LaboratorioRepository.getInstance();
        assertNotNull(result);
        
	}
	@Test
	public void testAdd() throws SQLException{
		System.out.println("add");
		Laboratorio lab= new Laboratorio();
		lab.setNome("Lab1");
		lab.setPosti(100);
		lab.setIDlaboratorio("12345678");
		lab.setStato(true);
		//-----------------
		LaboratorioRepository instance= LaboratorioRepository.getInstance();
		instance.add(lab);
		LaboratorioSql sql=new LaboratorioSql(lab.getIDlaboratorio());
		Laboratorio result=instance.findItemByQuery(sql);
		assertEquals(lab,result);
		instance.delete(lab);
	}
	
	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		Laboratorio lab= new Laboratorio();
		lab.setNome("Lab1");
		lab.setPosti(100);
		lab.setIDlaboratorio("12345678");
		lab.setStato(true);
		//-----------------
		LaboratorioRepository instance= LaboratorioRepository.getInstance();
		instance.add(lab);
		LaboratorioSql sql=new LaboratorioSql(lab.getIDlaboratorio());
		instance.delete(lab);
		Laboratorio result=instance.findItemByQuery(sql);
		assertEquals(null,result);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		Laboratorio lab= new Laboratorio();
		lab.setNome("Lab1");
		lab.setPosti(100);
		lab.setIDlaboratorio("12345678");
		lab.setStato(true);
		//-----------------
		LaboratorioRepository instance= LaboratorioRepository.getInstance();
		instance.add(lab);
		LaboratorioSql sql=new LaboratorioSql(lab.getIDlaboratorio());
		Laboratorio result=instance.findItemByQuery(sql);
		assertEquals(lab,result);
		instance.delete(lab);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		Laboratorio lab= new Laboratorio();
		lab.setNome("Lab1");
		lab.setPosti(100);
		lab.setIDlaboratorio("12345678");
		lab.setStato(true);
		List<Laboratorio> laboratori =new ArrayList<Laboratorio>();
		//-----------------

		LaboratorioRepository.getInstance().add(lab);
		laboratori=LaboratorioRepository.getInstance().query(new ListaLab());
		assertTrue(!laboratori.isEmpty());

	}

}
