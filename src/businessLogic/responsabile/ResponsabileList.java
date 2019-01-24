package businessLogic.responsabile;

import dataAccess.storage.SqlSpecification;

public class ResponsabileList implements SqlSpecification {
		
		private static ResponsabileList instance;

		public static ResponsabileList getInstance(){
		        if (instance == null){
		            instance = new ResponsabileList();
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