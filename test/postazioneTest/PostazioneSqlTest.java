package postazioneTest;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import businessLogic.Postazione.PostazioneSql;

public class PostazioneSqlTest {

	@Test
    public void testGetInstance() 
	{
        System.out.println("getInstance");
        PostazioneSql result = PostazioneSql.getInstance(1,"");
        assertNotNull(result);
        
        
	}
	
	@Test
	public void testToSqlQuery() 
	{
		System.out.println("toSqlQuery");
		PostazioneSql pos=new PostazioneSql(1,"lab1");
		PostazioneSql result=PostazioneSql.getInstance(1,"lab1");
		assertEquals(pos.toSqlQuery(),result.toSqlQuery());
		
	}
}
