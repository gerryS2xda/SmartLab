package dataAccess.storage.bean;


public class Utente {
	
	private String email;
	private String password;
	private String name;
	private String surname;
	
	public Utente (String email, String password, String name, String surname){
		this.email = email;
		this.password = password;
		this.name = name;
		this.surname = surname;
	}
	
	public Utente(){
		this.email = "";
		this.password = "";
		this.name = "";
		this.surname = "";
	}
	
	public void setEmail(String x){
		this.email = x;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setPassword(String x){
		this.password = x;
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setName(String x){
		this.name = x;
	}
	
	public String getName(){
		return name;
	}
	
	public void setSurname(String x){
		this.surname = x;
	}
	
	public String getSurname(){
		return surname;
	}
	
	//other method
	//adattato per stringa JSON
	public String toString(){
		String str = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"nome\": \"" + name + "\", "
				+ "\"cognome\": \"" + surname + "\", ";
		return str;
	}
	
	public boolean equals(Object o){
		boolean val = false;
		if(o instanceof Utente){
			Utente u = (Utente) o;
			if(getEmail().compareTo(u.getEmail()) == 0 && getPassword().compareTo(u.getPassword()) == 0 && 
					getName().compareTo(u.getName()) == 0 && getSurname().compareTo(u.getSurname()) == 0){
				val = true;
			}
		}
		return val;
	}
}
