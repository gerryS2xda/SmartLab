package businessLogic.Postazione;

import java.sql.SQLException;
import java.util.ArrayList;
import dataAccess.storage.bean.*;
import java.util.List;

import businessLogic.laboratorio.LaboratorioManager;
import businessLogic.prenotazione.PrenByStudPost;
import businessLogic.prenotazione.PrenotazioneException;
import businessLogic.prenotazione.PrenotazioneManager;
import businessLogic.prenotazione.PrenotazioneRepository;

public class PostazioneManager {
	
	private static PostazioneManager instance;

    public static PostazioneManager getInstance() 
    {
        
        instance = new PostazioneManager();
            
        return instance;
    }
	
    PrenotazioneRepository pr=new PrenotazioneRepository();
    

    public PostazioneManager(){
    	
    }
    
/**
 * Crea una postazione con i vari parametri prescelti
 * @param "laboratorio" indica il laboratorio in cui verrà inserita la postazione, "numero" indica il numero di    
 * postazione che gli verrà assegnato, "b" è lo stato della postazione.
 * @return p ritorna una postazione
 */
	public boolean creaPostazione(int numero,Laboratorio laboratorio,boolean b)
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
 * @param p indica quale postazione va attivata
 */
	public boolean attivaPostazione(Postazione pos)
	{
		if(pos==null)
			{
				return false;
			}
		pos.setStato(true);
		return true;
	}
/**
 * Disattiva e rende quindi non prenotabile una postazione precedentemente disattivata
 * @param p indica quale postazione va disattivata
 * @pre deve esistere quella postazione che si vuole disattivare
 */
	public boolean disattivaPostazione(Postazione pos)
	{
		if(pos==null)
		{
			return false;
		}
	pos.setStato(false);
	return true;
	}
	
/**
 * Libera una postazione che era stata prenotata da uno studente ma non è fisicamente presente
 * @param p indica quale postazione va Liberata
 * @return boolean che indica se è andata a buon fine l’operazione (true)
 * @throws SQLException 
 *  
 * 
 */

	public boolean liberaPostazione(Prenotazione pre) throws SQLException
	{
		if(pre.isPrenotazioneActive())
		{
			pr.delete(pre);
			return true;
		}
		return false;
	}
	
/**
 * Crea una lista di Postazioni che possiamo
 * @param Lab E’ il laboratorio da cui andrà a ricavare la lista delle postazioni
 * @return listaPos
 * @pre la stringa non deve essere vuota ne null
 */
	public List<Postazione> listaPostazioni(String lab)
	{
		List <Postazione> lpos=new ArrayList();
		Postazione pos=new Postazione();
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
			System.err.println("la Stringa inserita e' vuota");
		}
		pos.setNumero(1);   //codice per test statico
		pos.setStato(true);
		lpos.add(pos);
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
			try 
			{
				repository.delete(pos);
			} catch (SQLException e)
			{
				System.err.println("non e' andata a buon fiine");
				e.printStackTrace();
			}
			
		}
		return false;
	}
	
	
	
	
	public List<Prenotazione> listaPrenotazioni(String orainizio, String orafine,String idlab)
	{
		List <Prenotazione> pos=null;
		
			pos= new ArrayList<>();
			
			PrenotazioneRepository preR=new PrenotazioneRepository();
			PrenByStudPost presql=new PrenByStudPost(orainizio, idlab, orafine);
			
			List<Prenotazione> lista=new ArrayList();
		
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
