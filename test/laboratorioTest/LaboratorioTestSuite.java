package laboratorioTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ IdLabTest.class, LaboratorioManagerTestCase1.class, LaboratorioManagerTestCase2.class,
		LaboratorioRepositoryTest.class, LaboratorioSqlTest.class, ListaLabTest.class })
public class LaboratorioTestSuite {

}
