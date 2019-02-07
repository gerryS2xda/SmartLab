package utenteTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ StudenteSQLTest.class, UtenteManagerTestCase1.class, UtenteManagerTestCase2.class,
		StudenteRepositoryTest.class, StudentListTest.class, StudentLoginSQLTest.class })
public class UtenteTestSuite {

}
