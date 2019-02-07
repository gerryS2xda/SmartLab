package businessLogic.Postazione;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

import dataAccess.storage.bean.*;
import java.util.List;



import businessLogic.prenotazione.PrenotazioneByOra;
import businessLogic.prenotazione.PrenotazioneRepository;

public class PostazioneManager {
	
	private static PostazioneManager instance;

    public static PostazioneManager getInstance() 
    {
        
        instance = new PostazioneManager();
            
        return instance;
    }
	
    PrenotazioneRepository prere=new PrenotazioneRepository();
    PostazioneRepository posr=new PostazioneRepository();

    public PostazioneManager(){
    	
    }
     
/**
 * Crea una postazione con i vari parametri prescelti
 * @param "laboratorio" indica il laboratorio in cui verrà inserita la postazione, "numero" indica il numero di    
 * postazione che gli verrà assegnato, "b" è lo stato della postazione.
 * @return p ritorna una postazione
 */
	public boolean creaPostazione(int numero,String laboratorio,boolean b)
	{
		boolean flag=true;
		Postazione pos=new Postazione();
		pos.setNumero(numero);
		pos.setLaboratorio(laboratorio);
		pos.setStato(b);
		PostazioneRepository repository=new PostazioneRepository();
		
		try
		{
			repository.add(pos);
		}
		catch (SQLException e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}
	
/**
 * Riattiva una postazione precedentemente disattivata
 * @param id indica quale postazione va attivata
 * @param idlab serve per modificare la postazione dato che ha una chiave composta
 *  
 */
	public boolean attivaPostazione(String id, String idlab) 
	{
		boolean flag=true;
		int id1=Integer.parseInt(id); //converto la stringa in intero 
		Postazione pos=new Postazione(); 
		
		pos.setNumero(id1);
		pos.setLaboratorio(idlab);
		pos.setStato(true);
		try {
			posr.update(pos);
		} catch (SQLException e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
		
	}
/**
 * Disattiva e rende quindi non prenotabile una postazione precedentemente disattivata
 * @param p indica quale postazione va disattivata
 * @param idlab serve per modificare la postazione dato che ha una chiave composta
 * @param inter è l'intervento che viene salvato nel DB per tenere traccia del motivo della disattivazione
 * @pre deve esistere quella postazione che si vuole disattivare
 */
	public boolean disattivaPostazione(String id, String idlab,Intervento inter) 
	{
		boolean flag=true;
		int id1=Integer.parseInt(id); //converto la stringa in intero 
		Postazione pos=new Postazione(); 
		InterventoRepository intRe=new InterventoRepository();
		
		
		
		//mi ricavo la data di oggi
		//Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
		 Date data = new Date(Calendar.getInstance().getTime().getTime());
		 //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		 //String oggi=sdf.format(data);

		inter.setData(data.toLocalDate());			//setto la data di oggi da mettere nella tabella intervento
		System.out.println(inter.getData());
		
		System.out.println(id1);
		System.out.println(idlab);
		
		//setto l'oggetto pos per aggiornarlo
		pos.setNumero(id1);
		pos.setLaboratorio(idlab);
		pos.setStato(false);
		//aggiungo entrambi al DB
		try {
			intRe.add(inter);
			posr.update(pos);
		} catch (SQLException e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}
	
/**
 * Libera una postazione che era stata prenotata da uno studente ma non è fisicamente presente
 * @param p indica quale postazione va Liberata
 * @return boolean che indica se è andata a buon fine l’operazione (true)
 * @throws SQLException 
 *  
 * 
 */

	public boolean liberaPostazione(Prenotazione pre)
	{
		if(pre.isPrenotazioneActive())
		{
			try {
				prere.delete(pre);
			} catch (SQLException e) {
				
				e.printStackTrace();
				return false;
			}
			
		}
		return true;
	}
	
/**
 * Crea una lista di Postazioni che possiamo
 * @param Lab E’ il laboratorio da cui andrà a ricavare la lista delle postazioni
 * @return listaPos
 * @pre la stringa non deve essere vuota ne null
 */
	public List<Postazione> listaPostazioni(String lab)
	{
		List <Postazione> lpos=new ArrayList<Postazione>();
		//Postazione pos=new Postazione();
		if(lab!=null && !lab.equals(""))
		{
			PostazioneRepository repository=new PostazioneRepository();
			ListaPos lista=new ListaPos(lab);//query che prende il laboratrio
		
				try 
				{
					lpos=repository.query(lista);
				}
				catch (SQLException e) 
				{
					System.err.println("errore");
					e.printStackTrace();
				}
		} else
		{
			System.out.println("la Stringa inserita e' vuota lista vuota");
		}
//		pos.setNumero(1);   //codice per test statico
//		pos.setLaboratorio("lab1");
//		pos.setStato(false);
//		lpos.add(pos);
//		pos.setNumero(2);   //codice per test statico
//		pos.setLaboratorio("lab2");
//		pos.setStato(true);
//		lpos.add(pos);
		return lpos;
	}
	
/**
 * Elimina una postazione p
 * @param p è la postazione che verrà eliminata
 * @return boolean che indica se l’operazione è andata a buon fine (true)
 * @pre p deve esistere
 * @post viene eliminata una Postazione p
 */
	public boolean deletePostazione(Postazione pos)
	{
		if(pos!=null)
		{
			PostazioneRepository repository=new PostazioneRepository();
			
				repository.delete(pos);
			
				System.err.println("non e' andata a buon fine");
			
		}
		return false;
	}
	
	
	
	
	public List<Prenotazione> listaPrenotazioni(String orainizio, String orafine,String idlab)
	{
		//List <Prenotazione> pos= new ArrayList<>();
			
			PrenotazioneRepository preR=new PrenotazioneRepository();
			PrenotazioneByOra presql=new PrenotazioneByOra(orainizio, orafine, idlab);
			
			List<Prenotazione> lista=new ArrayList<Prenotazione>();
		
				try 
				{
					lista=preR.query(presql);
				}
				catch (SQLException e) 
				{
					System.err.println("errore");
					e.printStackTrace();
				}
		
		{
			System.err.println("la Stringa inserita e' vuota");
		}
		return lista;
	}
	

}
