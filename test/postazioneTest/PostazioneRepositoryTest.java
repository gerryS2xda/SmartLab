package postazioneTest;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import businessLogic.laboratorio.LaboratorioRepository;
import businessLogic.laboratorio.LaboratorioSql;
import businessLogic_Postazione.postazione.PostazioneRepository;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;

public class PostazioneRepositoryTest {

	@Test
    public void testGetInstance()
	{
        System.out.println("getInstance");
        PostazioneRepository result = PostazioneRepository.getInstance();
        assertNotNull(result);
        // TODO review the generated test code and remove the default call to fail.
        
	}
		}
		@Test
		public void testAdd() throws SQLException{
			System.out.println("add");
			Postazione pos= new Postazione();
			pos.setNumero("1");
			pos.setLaboratorio("lab1");
			pos.setStato(true);
			//-----------------
			PostazioneRepository instance= PostazioneRepository.getInstance();
			instance.add(pos);
			PostazioneSql sql=new PostazioneSql(pos.getNumero(),pos.getLaboratorio());
			Postazione result=instance.findItemByQuery(sql);
			assertEquals(pos,result);
			instance.delete(pos);
		}
	
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}  
