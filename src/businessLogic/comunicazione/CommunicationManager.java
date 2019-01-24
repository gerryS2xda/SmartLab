package businessLogic.comunicazione;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.bean.Avviso;
import dataAccess.storage.bean.Segnalazione;

public class CommunicationManager {
	
	public boolean addSegnalazione(Segnalazione s){
		if(s != null){
			SegnalazioneRepository sr = new SegnalazioneRepository();
			try {
				sr.add(s);
				return true;
			} catch (SQLException e) {
				System.out.println("Errore durante l'inserimento della segnalazione");
				e.printStackTrace();
				return false;
			}
		}else{
			System.out.println("Segnalazione vuota");
			return false;
		}
	}
	
	public boolean deleteSegnalazione(Segnalazione s){
		if(s != null){
			SegnalazioneRepository sr = new SegnalazioneRepository();
			try {
				sr.delete(s);
				return true;
			} catch (SQLException e) {
				System.out.println("Errore durante l'eliminazione della segnalazione");
				e.printStackTrace();
				return false;
			}
		}else{
			System.out.println("Segnalazione vuota");
			return false;
		}
	}
	
	public List<Segnalazione> viewSegnalazione(){
		SegnalazioneRepository sr = new SegnalazioneRepository();
		List<Segnalazione> lista = new ArrayList<Segnalazione>();
		ListaSegnalazioni lab = new ListaSegnalazioni();
		try{
			lista = sr.query(lab);
		}catch(SQLException e){
			System.out.println("Errore durante la ricerca delle segnalazioni");
		}
		return lista;
	}
	
	public boolean addAvviso(Avviso av){
		if(av != null){
			AvvisoRepository ar = new AvvisoRepository();
			try {
				ar.add(av);
				return true;
			} catch (SQLException e) {
				System.out.println("Errore durante l'inserimento dell'avviso");
				e.printStackTrace();
				return false;
			}
		}else{
			System.out.println("Avviso vuoto");
			return false;
		}
	}
	
	public boolean deleteAvviso(Avviso av){
		if(av != null){
			AvvisoRepository ar = new AvvisoRepository();
			try{
				ar.delete(av);
				return true;
			}catch(SQLException e){
				System.out.println("Errore durante la cancellazione dell'avviso");
				e.printStackTrace();
				return false;
			}
		}else{
			System.out.println("Avviso vuoto");
			return false;
		}
	}
	
	public List<Avviso> viewAvviso(){
		AvvisoRepository ar = new AvvisoRepository();
		List<Avviso> list = new ArrayList<Avviso>();
		ListaAvvisi avviso = new ListaAvvisi();
		try{
			list = ar.query(avviso);
		}catch(SQLException e){
			System.out.println("Errore durante la ricerca degli avvisi");
			e.printStackTrace();
		}
		return list;
	}

}
