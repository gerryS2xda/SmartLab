package businessLogic.Postazione;

import java.sql.SQLException;

import dataAccess.storage.bean.*;

public class PostazioneManager {
	
/**
 * Crea una postazione con i vari parametri prescelti
 * @param l indica il laboratorio in cui verr� inserita la postazione, i indica il numero di    
 * postazione che gli verr� assegnato, b � lo stato della postazione.
 * @return p ritorna una postazione
 * @post viene creata una Postazione p
 */
	public boolean creaPostazione(int numero,String laboratorio,boolean b)
	{
		boolean flag=true;
		Postazione pos=null;
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
 * Libera una postazione che era stata prenotata da uno studente ma non � fisicamente presente
 * @param p indica quale postazione va Liberata
 * @return boolean che indica se � andata a buon fine l�operazione (true)
 * @pre la postazione da liberare deve essere occupata
 */

	public boolean liberaPostazione(Postazione pos)
	{
		if(pos==null)
		{
			return false;
		}
		pos.setStato(true);
		return true;
	}
	
/**
 * Crea una lista di Postazioni che possiamo
 * @param L E� il laboratorio da cui andr� a ricavare la lista delle postazioni
 * @return PostazioniList pl
 * @pre L deve esistere
 */
	public boolean listaPostazioni(Laboratorio lab)
	{
		return false;
	}
	
/**
 * Elimina una postazione p
 * @param p � la postazione che verr� eliminata
 * @return boolean che indica se l�operazione � andata a buon fine (true)
 * @pre p deve esistere
 * @post viene eliminata una Postazione p
 */
	public boolean deletePostazione(Postazione pos)
	{
		
		PostazioneRepository repository=new PostazioneRepository();
		
		return false;
	}

}
