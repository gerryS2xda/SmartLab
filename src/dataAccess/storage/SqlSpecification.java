package dataAccess.storage;

public interface SqlSpecification extends Specification {
	
	String toSqlQuery();
}
