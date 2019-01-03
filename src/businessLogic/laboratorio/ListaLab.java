package businessLogic.laboratorio;
import dataAccess.storage.SqlSpecification;

public class ListaLab implements SqlSpecification{

    public static final String TABLE_NAME = "laboratorio";//nome della tabella su cui saranno effettuate le operazioni

    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT * FROM %1$s ;",
                TABLE_NAME
        );
    }
}