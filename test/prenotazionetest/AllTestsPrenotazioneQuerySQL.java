package prenotazionetest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ListaPrenotazioniQueryTest.class, PrenByStudPostTest.class, PrenotazioneByIdTest.class,
		PrenotazioneByOraTest.class, PrenotazioneByStudentTest.class, PrenotazioneGetSQLTestSuite.class })
public class AllTestsPrenotazioneQuerySQL {

}
