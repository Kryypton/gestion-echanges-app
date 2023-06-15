import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Criterion.Country;
import Criterion.Criterion;
import Criterion.CriterionName;
import Tennager.Teenager;
import graph.Affectation;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AffectationTest {
    private Affectation affectation;
    private Teenager t1, t2, t3, t4;
    private String file = "./res/affectationTest.ser";

    @BeforeEach
    public void setUp() {
        affectation = new Affectation();
        t1 = new Teenager("teen1", "A", LocalDate.of(2000, 5, 10), Country.FRANCE);
        t2 = new Teenager("teen2", "B",  LocalDate.of(2001, 8, 15), Country.GERMANY);
        t3 = new Teenager("teen3", "C",  LocalDate.of(2002, 10, 20), Country.ITALY);
        t4 = new Teenager("teen4", "C",  LocalDate.of(2002, 10, 20), Country.SPAIN);
    }

    @Test
    public void testAffectations() {
        affectation.affectations(t1, t2);
        assertTrue(affectation.estAffecter(t1));
        assertEquals(t2, affectation.getLast(t1));
    }

    @Test
    public void testDesaffectations() {
        affectation.affectations(t1, t2);
        affectation.desaffectations(t1);
        assertFalse(affectation.estAffecter(t1));
        assertNotEquals(t2, affectation.getLast(t1));
    }

    @Test
    public void testSaveAndLoadHistory() {
        affectation.affectations(t1, t2);
        affectation.affectations(t3, t4);
        affectation.saveHistory(file);

        Affectation loadedAffectation = new Affectation();
        loadedAffectation.loadHistory(file);

        assertEquals(t2, loadedAffectation.getLast(t1));
        assertTrue(loadedAffectation.estAffecter(t1));
        assertTrue(loadedAffectation.estAffecter(t3));
        assertEquals(t4, loadedAffectation.getLast(t3));
    }

    @Test
    public void testHistoryTeenager() {
        Criterion critere = new Criterion("same", CriterionName.HISTORY);
        
        affectation.affectations(t1, t2);
        t1.addCriterion(CriterionName.HISTORY.name(), critere);
        t2.addCriterion(CriterionName.HISTORY.name(), critere);
        assertEquals(0, affectation.historyTeenager(t1, t2));
    }
}
