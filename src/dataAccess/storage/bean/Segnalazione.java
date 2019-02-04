package dataAccess.storage.bean;

import java.io.Serializable;
import java.sql.Date;

public class Segnalazione implements Serializable {
	
	private int id;
	private String oggetto, descrizione;
	private Date data;
	private String studente;
	private int postazione, lab;
	
	public Segnalazione(){
	}
	
	public Segnalazione(int i, String og, String des, Date d, String s, int l, int pos){
		id = i;
		oggetto = og;
		descrizione = des;
		data = d;
		studente = s;
		lab = l;
		postazione = pos;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int nId){
		id = nId;
	}
	
	public String getOggetto(){
		return oggetto;
	}
	
	public void setOggetto(String nSubj){
		oggetto = nSubj;
	}
	
	public String getDescrizione(){
		return descrizione;
	}
	
	public void setDescrizione(String nDes){
		descrizione = nDes;
	}
	
	public Date getData(){
		return data;
	}
	
	public void setData(Date nData){
		data = nData;
	}
	
	public String getStudente(){
		return studente;
	}
	
	public void setStudente(String nStud){
		studente = nStud;
	}
	
	public int getLaboratorio(){
		return lab;
	}
	
	public void setLaboratorio(int nLab){
		lab = nLab;
	}
	
	public int getPostazione(){
		return postazione;
	}
	
	public void setPostazione(int nPos){
		postazione = nPos;
	}
}
