package dataAccess.storage.bean;

import java.io.Serializable;
import java.sql.Date;

public class Segnalazione implements Serializable {
	
	private int id;
	private String titolo, messaggio;
	private Date data;
	private Addetto addetto;
	
	public Segnalazione(String tit, String msg, Date d, Addetto ad){
		titolo = tit;
		messaggio = msg;
		data = d;
		addetto = ad;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int nId){
		id = nId;
	}
	
	public String getTitolo(){
		return titolo;
	}
	
	public void setTitolo(String nTit){
		titolo = nTit;
	}
	
	public String getMessaggio(){
		return messaggio;
	}
	
	public void setMessaggio(String nMsg){
		messaggio = nMsg;
	}
	
	public Date getData(){
		return data;
	}
	
	public void setData(Date nData){
		data = nData;
	}
	
	public Addetto getAddetto(){
		return addetto;
	}
	
	public void setAddetto(Addetto nAd){
		addetto = nAd;
	}

}
