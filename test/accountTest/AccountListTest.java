package accountTest;

import static org.junit.Assert.*;

import org.junit.Test;

import businessLogic.account.AccountList;

public class AccountListTest{
	
	@Test
	public void testGetInstance(){
		System.out.println("getInstance");
        AccountList result = AccountList.getInstance();
        assertNotNull(result);
	}
	
	@Test
	public void testToSqlQuery(){
		System.out.println("toSqlQuery");
		AccountList list = new AccountList();
		AccountList result = AccountList.getInstance();
		assertEquals(list.toSqlQuery(), result.toSqlQuery());
	}
}