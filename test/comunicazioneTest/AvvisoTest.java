package comunicazioneTest;

import businessLogic.comunicazione.CommunicationManager;
import dataAccess.storage.bean.Avviso;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AvvisoTest {

	@Test
	public void testAddAvviso(){
		Avviso a = new Avviso();
		a.setId(10);
		a.setTitolo("Titolo");
		a.setMessaggio("Messaggio");
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		a.setData(data);
		a.setAddetto("g.paolisi@unisa.it");
		CommunicationManager cm = new CommunicationManager();
		assertTrue(cm.addAvviso(a));
	}
	
	@Test
	public void testDeleteAvviso(){
		Avviso a = new Avviso();
		a.setId(10);
		a.setTitolo("Titolo");
		a.setMessaggio("Messaggio");
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		a.setData(data);
		a.setAddetto("g.paolisi@unisa.it");
		CommunicationManager cm = new CommunicationManager();
		assertTrue(cm.deleteAvviso(a));
	}
	
	@Test
	public void testViewAvviso(){
		Avviso a = new Avviso();
		a.setId(20);
		a.setTitolo("Tit");
		a.setMessaggio("Mes");
		java.util.Date d = new java.util.Date();
		Date data = new Date(d.getTime());
		a.setData(data);
		a.setAddetto("g.paolisi@unisa.it");
		CommunicationManager cm = new CommunicationManager();
		cm.addAvviso(a);
		List<Avviso> lista = new ArrayList<Avviso>();
		lista = cm.viewAvviso();
		assertTrue(!lista.isEmpty());
	}
}
