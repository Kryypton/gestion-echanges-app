import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;


public class HistoryTest {
    private History history;
    private Teenager t1, t2, t3, t4;
    private String file = "./res/historiqueTest.ser";

    @BeforeEach
    public void setUp() {
        history = new History();
        t1 = new Teenager(1, "teen1", "A", "M", LocalDate.of(2000, 5, 10), Country.FRANCE);
        t2 = new Teenager(2, "teen2", "B", "F", LocalDate.of(2001, 8, 15), Country.GERMANY);
        t3 = new Teenager(3, "teen3", "C", "F", LocalDate.of(2002, 10, 20), Country.ITALY);
        t4 = new Teenager(4, "teen4", "C", "F", LocalDate.of(2002, 10, 20), Country.SPAIN);
    }

    @Test
    public void testAffectations() {
        history.affectations(t1, t2);
        assertTrue(history.estAffecter(t1));
        assertEquals(t2, history.get(t1));
    }

    @Test
    public void testDesaffectations() {
        history.affectations(t1, t2);
        history.desaffectations(t1);
        assertFalse(history.estAffecter(t1));
    }

    @Test
    public void testSaveAndLoadHistory() {
        history.affectations(t1, t2);
        history.affectations(t3, t4);
        history.saveHistory(file);

        History loadedHistory = new History();
        loadedHistory.loadHistory(file);

        assertEquals(t2, loadedHistory.get(t1));
        assertTrue(loadedHistory.estAffecter(t1));
        assertTrue(loadedHistory.estAffecter(t3));
        assertEquals(t4, loadedHistory.get(t3));
    }

    @Test
    public void testHistoryTeenager() {
        Criterion critere = new Criterion("meme", CriterionName.HISTORY);
        Criterion critere2 = new Criterion("different", CriterionName.HISTORY);
        
        history.affectations(t1, t2);
        t1.addCriterion(CriterionName.HISTORY.name(), critere);
        t2.addCriterion(CriterionName.HISTORY.name(), critere);
        assertEquals(0, history.historyTeenager(t1, t2));
        //TODO : Ce test n'est pas perssistant pour le moment
    }
}
