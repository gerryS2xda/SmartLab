package businessLogic.addetto;

import dataAccess.storage.SqlSpecification;

public class AddettoList implements SqlSpecification {
		
		private static AddettoList instance;

		public static AddettoList getInstance(){
		        if (instance == null){
		            instance = new AddettoList();
		        }
		        return instance;
		    }

		    public static final String TABLE_NAME = "addetto a join utente u on a.email = u.email";
		    
		    @Override
		    public String toSqlQuery() {
		        return String.format(
		                "SELECT * FROM %1$s WHERE tipo = 'false';",
		                TABLE_NAME);
		    }
}