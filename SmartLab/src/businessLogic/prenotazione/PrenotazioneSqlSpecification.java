package businessLogic.prenotazione;

import dataAccess.storage.Specification;

public interface PrenotazioneSqlSpecification extends Specification{

	String toSqlQuery();
}
