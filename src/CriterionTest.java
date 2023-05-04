import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CriterionTest {
    Criterion criterion1, criterion2, criterion3, criterion4, criterion5;


    @BeforeEach
    void initialization() {
        criterion1 = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        criterion2 = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        criterion3 = new Criterion("végétarien", CriterionName.HOST_FOOD);
        criterion4 = new Criterion("pasBien", CriterionName.HOST_FOOD);
        criterion5 = new Criterion("pasBien", CriterionName.HOST_FOOD);
    }

    @Test
    void testIsValid() {
        assertTrue(criterion1.isValid());
        assertTrue(criterion2.isValid());
        assertTrue(criterion3.isValid());
        assertFalse(criterion4.isValid());
    }
}
