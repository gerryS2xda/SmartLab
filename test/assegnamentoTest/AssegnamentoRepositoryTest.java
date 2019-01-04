package assegnamentoTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import businessLogic.assegnamento.AssegnamentoRepository;
import businessLogic.assegnamento.AssegnamentoSql;
import businessLogic.laboratorio.ListaLab;
import dataAccess.storage.bean.Assegnamento;

public class AssegnamentoRepositoryTest {

	public void testGetInstance() {
        System.out.println("getInstance");
        AssegnamentoRepository result = AssegnamentoRepository.getInstance();
        assertNotNull(result);
        
	}
	@Test
	public void testAdd() throws SQLException{
		System.out.println("add");
		Assegnamento ass= new Assegnamento();
		ass.setLaboratorio("Lab1");
		ass.setResponsabile("resp1");;
		//-----------------
		AssegnamentoRepository instance= AssegnamentoRepository.getInstance();
		instance.add(ass);
		AssegnamentoSql sql=new AssegnamentoSql(ass.getLaboratorio(),ass.getResponsabile());
		Assegnamento result=instance.findItemByQuery(sql);
		assertEquals(ass,result);
		instance.delete(ass);
	}
	
	@Test
	public void testDelete() throws SQLException{
		System.out.println("delete");
		Assegnamento ass= new Assegnamento();
		ass.setLaboratorio("Lab1");
		ass.setResponsabile("resp1");;
		//-----------------
		AssegnamentoRepository instance= AssegnamentoRepository.getInstance();
		instance.add(ass);
		AssegnamentoSql sql=new AssegnamentoSql(ass.getLaboratorio(),ass.getResponsabile());
		instance.delete(ass);
		Assegnamento result=instance.findItemByQuery(sql);
		assertEquals(null,result);
	}
	
	@Test
	public void testFindItemByQuery() throws SQLException{
		System.out.println("findItemByQuery");
		Assegnamento ass= new Assegnamento();
		ass.setLaboratorio("Lab1");
		ass.setResponsabile("resp1");;
		//-----------------
		AssegnamentoRepository instance= AssegnamentoRepository.getInstance();
		instance.add(ass);
		AssegnamentoSql sql=new AssegnamentoSql(ass.getLaboratorio(),ass.getResponsabile());
		Assegnamento result=instance.findItemByQuery(sql);
		assertEquals(ass,result);
		instance.delete(ass);
	}
	
	@Test
	public void testQuery() throws SQLException{
		System.out.println("query");
		Assegnamento ass= new Assegnamento();
		ass.setLaboratorio("Lab1");
		ass.setResponsabile("resp1");;
		List<Assegnamento> assegnamenti =new ArrayList<Assegnamento>();
		//-----------------
		AssegnamentoRepository.getInstance().add(ass);
		assegnamenti=AssegnamentoRepository.getInstance().query(new ListaLab());
		assertTrue(!assegnamenti.isEmpty());
	}


}
