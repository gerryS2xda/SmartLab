import static org.junit.Assert.*;

import org.junit.Test;

public class AccountSQLTest {
	
	@Test
	public void testGetInstance(){
		System.out.println("getInstance");
		AccountSQL result = AccountSQL.getInstance("");
		assertNotNull(result);
	}
	
	@Test
	public void TestToSqlQuery{
		System.out.println("toSqlQuery");
		AccountSQL u = new AccountSQL("acc1");
		AccountSQL result = AccountSQL.getInstance("acc1");
		assertEquals(u.toSqlQuery(), result.toSqlQuery());
	}
}
