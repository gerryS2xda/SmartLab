package businessLogic.comunicazione;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.bean.Avviso;
import dataAccess.storage.bean.Segnalazione;

public class CommunicationManager {
	
	public void addSegnalazione(int id, String oggetto, String descrizione, Date data, int postazione, String laboratorio, int studente){
		Segnalazione s = new Segnalazione(id, oggetto, descrizione, data, studente, laboratorio, postazione);
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
	
	public void addAvviso(int id, String titolo, String messaggio, Date data, int addetto){
		Avviso av = new Avviso(id, titolo, messaggio, data, addetto);
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
