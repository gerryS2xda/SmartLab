package prenotazionetest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllTestsPrenotazioneQuerySQL.class, PrenotazioneRepositoryTest.class, PostazioneRepositoryTest.class, 
	StudenteRepositoryTest.class, LaboratorioRepositoryTest.class, AllTestsPrenotazioneManager.class })
public class TestPrenotazioneComponent {

}
