
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CriterionTest {
    Criterion criterion1, criterion2, criterion3, criterion4, criterion5, criterion6, criterion7, criterion8, criterion9;

    @BeforeEach
    void initialization() {
        criterion1 = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        criterion2 = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        criterion3 = new Criterion("végétarien", CriterionName.HOST_FOOD);
        criterion4 = new Criterion("pasBien", CriterionName.HOST_FOOD);
        criterion5 = new Criterion(null, CriterionName.HOST_HAS_ANIMAL);
        criterion6 = new Criterion("pasBien", CriterionName.NUMERIC);
        criterion7 = new Criterion("1", CriterionName.NUMERIC);
        criterion8 = new Criterion("01/01/2020", CriterionName.HISTORY);
        criterion9 = new Criterion("1/1", CriterionName.HISTORY);

    }

    @Test
    void testIsValid() {
        try{
            criterion1.isValid();
            criterion2.isValid();
            criterion3.isValid();
            criterion7.isValid();
            criterion8.isValid();
        }catch(CriterionTypeException e){
            assertTrue(false);
        }
        try{
            criterion4.isValid();
        }catch(CriterionTypeException e){
            assertTrue(true);
        }
        try{
            criterion5.isValid();
        }catch(CriterionTypeException e){
            assertTrue(true);
        }
        try{
            criterion6.isValid();
        }catch(CriterionTypeException e){
            assertTrue(true);
        }try{
            criterion9.isValid();
        }catch(CriterionTypeException e){
            assertTrue(true);
        }
    }
}
