package businessLogic.account;

import dataAccess.storage.SqlSpecification;

public class SospensioneList implements SqlSpecification {
	private static SospensioneList instance;

	public static SospensioneList getInstance(){
	        if (instance == null){
	            instance = new SospensioneList();
	        }
	        return instance;
	    }

	    public static final String TABLE_NAME = "sospensione";
	    
	    @Override
	    public String toSqlQuery() {
	        return String.format(
	                "SELECT * FROM %1$s ;",
	                TABLE_NAME);
	    }
}
