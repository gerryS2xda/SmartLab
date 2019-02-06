package postazioneTest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import businessLogic.Postazione.InterventoRepository;
import businessLogic.Postazione.PostazioneManager;
import businessLogic.Postazione.PostazioneRepository;
import businessLogic.Postazione.PostazioneSql;
import dataAccess.storage.bean.Intervento;
import dataAccess.storage.bean.Postazione;

public class InterventoRepositoryTest {


	@Test
	public void testGetInstance() {
		System.out.println("Testing: get Instance");
		
		InterventoRepository repo=InterventoRepository.getInstance();
		assertNotNull("Repository object e' nullo", repo);
	}

	@Test
	public void testAdd() {
		
		System.out.println("testing: add");
		Intervento inter= new Intervento();
		inter.setAddetto("nicola");
		inter.setDescrizione("asdasd");
		inter.setIdIntervento(2);
		inter.setPostazione(1);
		inter.setLaboratorio("lab1");
		//-----------------
		interventoRepository instance= .getInstance();
		instance.add(inter);
		InterventoSql sql=new InterventoSql(inter.getIdIntervento());
		Intervento result=instance.findItemByQuery(sql);
		assertEquals(inter,result);
	}

	@Test
	public void testDelete() 
	{
		Intervento inter=new Intervento();
		inter.setIdIntervento(1);
		InterventoRepository instance= InterventoRepository.getInstance();
		instance.delete(inter);
		InterventoSql sql=new InterventoSql(inter.getIdIntervento());
		Intervento result=instance.findItemByQuery(sql);
		
		assertEquals(null,inter);
	}

}
