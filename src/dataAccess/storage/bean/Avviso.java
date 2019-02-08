package dataAccess.storage.bean;

import java.io.Serializable;
import java.util.Date;

public class Avviso implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String titolo, messaggio;
	private Date data;
	private String addetto;
	
	public Avviso(){	
		id = 0;
		titolo = "";
		messaggio = "";
		data = null;
		addetto = "";
	}
	
	public Avviso(int i, String tit, String msg, Date d, String ad) {
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
	
	public String getAddetto(){
		return addetto;
	}
	
	public void setAddetto(String nAd){
		addetto = nAd;
	}
	
	//other method
	public boolean equals(Object otherObject){
		boolean val = false;
		if(otherObject instanceof Avviso){
			Avviso avv = (Avviso) otherObject;
			if(avv.getTitolo().equals(titolo) && avv.getMessaggio().equals(messaggio)){
				val = true;
			}
		}
		return val;
	}
}
