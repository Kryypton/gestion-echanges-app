import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

public class HistoryTest {
    private History history;
    private Teenager teenager1;
    private Teenager teenager2;
    private Teenager teenager3;
    private Teenager teenager4;

    @BeforeEach
    public void setUp() {
        history = new History();
        teenager1 = new Teenager(1, "teen1", "A", "M", LocalDate.of(2000, 5, 10), Country.FRANCE);
        teenager2 = new Teenager(2, "teen2", "B", "F", LocalDate.of(2001, 8, 15), Country.GERMANY);
        teenager3 = new Teenager(3, "teen3", "C", "F", LocalDate.of(2002, 10, 20), Country.ITALY);
        teenager4 = new Teenager(4, "teen4", "C", "F", LocalDate.of(2002, 10, 20), Country.SPAIN);
    }

    @Test
    public void testAffectations() {
        history.affectations(teenager1 , teenager2);
        history.affectations(teenager3 , teenager4);
        assertEquals(teenager2, history.get(teenager1));
        assertEquals(teenager4, history.get(teenager3));
    }

    @Test
    public void testDesaffectations() {
        history.affectations(teenager1 , teenager2);
        history.affectations(teenager3 , teenager4);
        history.desaffectations(teenager1);
        history.desaffectations(teenager3);
        assertNull(history.get(teenager1));
        assertNull(history.get(teenager3));
    }

    @Test
    public void testEstAffecter() {
        assertFalse(history.estAffecter(teenager1));
        history.affectations(teenager1 , teenager2);
        assertTrue(history.estAffecter(teenager1));
    }

    @Test
    public void testEstAffecterPair() {
        assertFalse(history.estAffecter(teenager1, teenager2));
        history.affectations(teenager1 , teenager2);
        assertTrue(history.estAffecter(teenager1, teenager2));
    }

    @Test
    public void testSaveAndLoadHistory() {
        history.affectations(teenager1 , teenager2);
        history.affectations(teenager3 , teenager4);
        String hist = "history.ser";
        history.saveHistory(hist);
        
        History newHistory = new History();
        newHistory.loadHistory(hist);

        assertEquals(history.get(teenager3), newHistory.get(teenager3));
        assertEquals(history.get(teenager1), newHistory.get(teenager1));
    }

}
