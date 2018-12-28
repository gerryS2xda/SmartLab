package dataAccess.storage;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

	void add(T item)throws SQLException;
	
	void delete(T item)throws SQLException;
	
	void update(T item)throws SQLException;
	
	T findItemByQuery(Specification spec)throws SQLException;
	
	List<T> query(Specification spec)throws SQLException;
}

