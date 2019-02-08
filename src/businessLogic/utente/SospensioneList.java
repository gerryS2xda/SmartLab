package businessLogic.utente;

import dataAccess.storage.SqlSpecification;

public class SospensioneList implements SqlSpecification {

    public static final String TABLE_NAME = "sospensione";
    
    /** L'ADDETTO DEVE VEDERE LE PROPRIE SOSPENSIONI **/
    /** CONTROLLA IL RAD PRIMA DI QUESTE AGGIUNTE **/
    
    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT * FROM %1$s",
                TABLE_NAME);
	    }
}
