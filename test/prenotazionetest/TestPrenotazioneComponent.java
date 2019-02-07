package prenotazionetest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllTestsPrenotazioneQuerySQL.class, StudenteRepositoryTest.class, PostazioneRepositoryTest.class, 
	LaboratorioRepositoryTest.class, PrenotazioneRepositoryTest.class, AllTestsPrenotazioneManager.class })
public class TestPrenotazioneComponent {

}
