import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Map;

public class HistoryTest {

    @Test
    public void testAffectations() {
        Teenager teenager1 = new Teenager(1, "teen1", "A", "M", LocalDate.of(2000, 5, 10), Country.FRANCE);
        Teenager teenager2 = new Teenager(2, "teen2", "B", "F", LocalDate.of(2001, 8, 15), Country.GERMANY);
        Teenager teenager3 = new Teenager(3, "teen3", "C", "F", LocalDate.of(2002, 10, 20), Country.ITALY);

        History history = new History();
        history.affectations(teenager1, teenager2, 0);
        history.affectations(teenager2, teenager3, 1);

        assertTrue(history.estAffecter(teenager1, 0));
        assertTrue(history.estAffecter(teenager2, 0));
        assertTrue(history.estAffecter(teenager2, 1));
        assertTrue(history.estAffecter(teenager3, 1));

        assertFalse(history.estAffecter(teenager1, 1));
        assertFalse(history.estAffecter(teenager3, 0));
    }

    @Test
    public void testGet() {
        Teenager teenager1 = new Teenager(1, "teen1", "A", "M", LocalDate.of(2000, 5, 10), Country.FRANCE);
        Teenager teenager2 = new Teenager(2, "teen2", "B", "F", LocalDate.of(2001, 8, 15), Country.GERMANY);
        Teenager teenager3 = new Teenager(3, "teen3", "C", "F", LocalDate.of(2002, 10, 20), Country.ITALY);

        History history = new History();
        history.affectations(teenager1, teenager2, 0);
        history.affectations(teenager2, teenager3, 1);

        assertEquals(teenager2, history.get(teenager1, 0));
        assertEquals(teenager3, history.get(teenager2, 1));
        assertNull(history.get(teenager1, 1));
        assertNull(history.get(teenager3, 0));
    }

    @Test
    public void testToString() {
        Teenager teenager1 = new Teenager(1, "teen1", "A", "M", LocalDate.of(2000, 5, 10), Country.FRANCE);
        Teenager teenager2 = new Teenager(2, "teen2", "B", "F", LocalDate.of(2001, 8, 15), Country.GERMANY);
        Teenager teenager3 = new Teenager(3, "teen3", "C", "F", LocalDate.of(2002, 10, 20), Country.ITALY);

        History history = new History();
        history.affectations(teenager1, teenager2, 0);
        history.affectations(teenager2, teenager3, 1);

        String expectedString = "Année : 0\n" +
                "\tteen1 est chez teen2\n" +
                "Année : 1\n" +
                "\tteen2 est chez teen3\n";
        assertEquals(expectedString, history.toString());
    }

    @Test
    public void testSaveAndLoadHistory() {
        Teenager teenager1 = new Teenager(1, "teen1", "A", "M", LocalDate.of(2000, 5, 10), Country.FRANCE);
        Teenager teenager2 = new Teenager(2, "teen2", "B", "F", LocalDate.of(2001, 8, 15), Country.GERMANY);
        Teenager teenager3 = new Teenager(3, "teen3", "C", "F", LocalDate.of(2002, 10, 20), Country.ITALY);

        History history = new History();
        history.affectations(teenager1, teenager2, 0);
        history.affectations(teenager2, teenager3, 1);

        String filename = "./res/historique.ser";

        history.saveHistory(filename);

        History loadedHistory = new History();
        loadedHistory.loadHistory(filename);

        Map<Integer, Map<Teenager, Teenager>> loadedAffectations = loadedHistory.getAffectationsHistory();
        assertTrue(loadedAffectations.containsKey(0));
        assertTrue(loadedAffectations.containsKey(1));

        Map<Teenager, Teenager> affectations0 = loadedAffectations.get(0);
        assertEquals(teenager2, affectations0.get(teenager1));

        Map<Teenager, Teenager> affectations1 = loadedAffectations.get(1);
        assertEquals(teenager3, affectations1.get(teenager2));
    }

    @Test
    public void testHistoryTeenager() {
        Teenager teenager1 = new Teenager(1, "teen1", "A", "M", LocalDate.of(2000, 5, 10), Country.FRANCE);
        Teenager teenager2 = new Teenager(2, "teen2", "B", "F", LocalDate.of(2001, 8, 15), Country.GERMANY);
        Teenager teenager3 = new Teenager(3, "teen3", "C", "F", LocalDate.of(2002, 10, 20), Country.ITALY);

        History history = new History();
        history.affectations(teenager1, teenager2, 0);
        history.affectations(teenager2, teenager3, 1);

        int weight1 = history.historyTeenager(teenager1, teenager2, 0);
        int weight2 = history.historyTeenager(teenager2, teenager3, 1);

        assertEquals(-100, weight1);
        assertEquals(0, weight2);
    }
}
