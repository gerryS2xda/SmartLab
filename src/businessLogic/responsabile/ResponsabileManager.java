package businessLogic.responsabile;

import java.util.ArrayList;

import dataAccess.storage.bean.Addetto;
import dataAccess.storage.bean.Utente;

public class ResponsabileManager {
	
	private static ResponsabileManager instance;
	private ArrayList<Addetto> responsabileList = new ArrayList<Addetto>();
	
	public static ResponsabileManager getInstace(){
		if (instance == null){
			instance = new ResponsabileManager();
		}
		return instance;
	}

	public void aggiungiResp (String email, String password){
		if(isEmailRight(email) && isPasswordRight(password))
			responsabileList.add(new Addetto(email, password)); 
		//aggiungo nome e cognome ai parametri del metodo oppure metto come parametro l'oggetto addetto
	}
	
	public void rimuoviResp (Addetto resp){
		for(int i=0; i<responsabileList.size(); i++){
			if (responsabileList.get(i).equals(resp))
				responsabileList.remove(i);
			//basta fare questo oppure devo fare uno shift?
		}
	}
	
	public void visualizzaListaResp (){
		for(int i=0; i<responsabileList.size(); i++){
			System.out.println(""+responsabileList.get(i).getSurname()+" "+responsabileList.get(i).getName());
			//è corretto oppure devo semplicemente restituire la lista?
		}
	}

}
