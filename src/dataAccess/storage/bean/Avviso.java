package dataAccess.storage.bean;
import java.io.Serializable;
import java.util.Date;

public class Avviso implements Serializable {
	
	private int id;
	private String oggetto, descrizione;
	private Date data;
	private int studente;
	private String lab;
	private int postazione;
	
	public Avviso(int i, String og, String des, Date d, int s, String l, int pos){
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
	
	public int getStudente(){
		return studente;
	}
	
	public void setStudente(int nStud){
		studente = nStud;
	}
	
	public String getLaboratorio(){
		return lab;
	}
	
	public void setLaboratorio(String nLab){
		lab = nLab;
	}
	
	public int getPostazione(){
		return postazione;
	}
	
	public void setPostazione(int nPos){
		postazione = nPos;
	}
}
