package businessLogic.account;

import dataAccess.storage.SqlSpecification;

public class AccountList implements SqlSpecification {
	
	private static AccountList instance;

	public static AccountList getInstance(){
	        if (instance == null){
	            instance = new AccountList();
	        }
	        return instance;
	    }

	    public static final String TABLE_NAME = "account";
	    
	    @Override
	    public String toSqlQuery() {
	        return String.format(
	                "SELECT * FROM %1$s ;",
	                TABLE_NAME);
	    }
}
