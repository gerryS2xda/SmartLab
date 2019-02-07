package utenteTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ StudentSQLTest.class, StudenteRepositoryTest.class, StudentListTest.class, 
	StudentLoginSQLTest.class, UtenteManagerTestCase1.class, UtenteManagerTestCase2.class, })
public class UtenteTestSuite {

}
