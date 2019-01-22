package businessLogic.comunicazione;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.bean.Avviso;
import dataAccess.storage.bean.Segnalazione;

public class CommunicationManager {
	
	public void addSegnalazione(Segnalazione s){
		SegnalazioneRepository sr = new SegnalazioneRepository();
		try {
			sr.add(s);
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento della segnalazione");
			e.printStackTrace();
		}
	}
	
	public void deleteSegnalazione(Segnalazione s){
		SegnalazioneRepository sr = new SegnalazioneRepository();
		try {
			sr.delete(s);
		} catch (SQLException e) {
			System.out.println("Errore durante l'eliminazione della segnalazione");
			e.printStackTrace();
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
	
	public void addAvviso(Avviso av){
		AvvisoRepository ar = new AvvisoRepository();
		try {
			ar.add(av);
		} catch (SQLException e) {
			System.out.println("Errore durante l'inserimento dell'avviso");
			e.printStackTrace();
		}
	}
	
	public void deleteAvviso(Avviso av){
		AvvisoRepository ar = new AvvisoRepository();
		try{
			ar.delete(av);
		}catch(SQLException e){
			System.out.println("Errore durante la cancellazione dell'avviso");
			e.printStackTrace();
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
