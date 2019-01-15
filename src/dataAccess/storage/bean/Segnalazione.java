package dataAccess.storage.bean;

import java.io.Serializable;
import java.sql.Date;

public class Segnalazione implements Serializable {
	
	private int id;
	private String titolo, messaggio;
	private Date data;
	private int addetto;
	
	public Segnalazione(int i, String tit, String msg, Date d, int ad){
		id = i;
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
	
	public int getAddetto(){
		return addetto;
	}
	
	public void setAddetto(int nAd){
		addetto = nAd;
	}

}
