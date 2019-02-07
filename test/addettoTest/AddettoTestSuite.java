package businessLogic.addetto;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddettoSQLTest.class, AddettoManagerTestCase1.class, AddettoManagerTestCase2.class,
		StudenteRepositoryTest.class, AddettoListTest.class, AddettoLoginSQLTest.class })
public class AddettoTestSuite {

}