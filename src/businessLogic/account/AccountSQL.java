package businessLogic.account;

import dataAccess.storage.SqlSpecification;

public class AccountSQL implements SqlSpecification {

	private static AccountSQL instance;
	
	public static AccountSQL getInstance(String acc){
		if (instance == null){
			instance = new AccountSQL(acc);
		}
		return instance;
	}
	
	public static final String TABLE_NAME = "account";
	
	private String account;
	
	public AccountSQL(String acc){
		this.account = acc;
	}
	
	@Override
	public String toSqlQuery(){
		return String.format(
				"SELECT * FROM %1$s WHERE email=%2$s;",
                TABLE_NAME,
                this.account);
	}
}
