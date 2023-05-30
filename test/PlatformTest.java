import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashMap;

public class PlatformTest {

    /**
     * Test de la méthode addTeenager
     * @return un teenager
     */
    @BeforeEach
    public Teenager ten(){
        //Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);

        //Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        
        //Criterion possedeVege = new Criterion("végétarien", CriterionName.HOST_FOOD);
        //Criterion possedeSport = new Criterion("sport", CriterionName.HOST_FOOD);
        //Criterion posseDeNonuts = new Criterion("nonuts", CriterionName.HOST_FOOD);
        Criterion posseDeTout = null;

        //Criterion mangeVege = new Criterion("végétarien", CriterionName.GUEST_FOOD);
        //Criterion mangeSport = new Criterion("sport", CriterionName.GUEST_FOOD);
        //Criterion mangeNotnuts = new Criterion("nonuts", CriterionName.GUEST_FOOD);
        Criterion mangeTout = null;

        Map<String, Criterion> reqMrBouffeTout = new HashMap<>();
        reqMrBouffeTout.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        reqMrBouffeTout.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        reqMrBouffeTout.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        reqMrBouffeTout.put(CriterionName.GUEST_FOOD.name(), mangeTout);

        Teenager teenager1 = new Teenager(1, "MrBouffeTout", "Martin", "F", LocalDate.of(2005, 6, 20), Country.FRANCE , reqMrBouffeTout);
        return teenager1;
    }

    /* Test de la méthode addTeenager */
    @Test
    public void addTeenagerTest(){
        Platform p = new Platform();
        assertTrue(p.addTeenager(ten()));
        assertTrue(p.getTeenagerList().contains(ten()));
        // on ne peut pas ajouter 2 fois le même teenager
        assertFalse(p.addTeenager(ten()));
    }

    /* Test de la méthode removeTeenager */
    @Test
    public void removeTeenagerTest(){
        Platform p = new Platform();
        assertTrue(p.addTeenager(ten()));
        assertTrue(p.getTeenagerList().contains(ten()));
        assertTrue(p.removeTeenager(ten()));
        assertFalse(p.getTeenagerList().contains(ten()));
        assertFalse(p.removeTeenager(ten()));
    }

    /* Test de la méthode getTeenagerList */
    @Test
    public void purgeInvalidRequirements(){
        Platform p = new Platform();
        p.addTeenager(ten());
        assertTrue(p.getTeenagerList().contains(ten()));
        p.purgeInvalidRequirements();
        assertFalse(p.getTeenagerList().contains(ten()));
    }


    /* Test de la méthode printCompatibleTeenagers */
    @Test
    public void printCompatibleTeenagers(){
        Platform p = new Platform();
        p.addTeenager(ten());
        assertTrue(p.getTeenagerList().contains(ten()));
        p.printCompatibleTeenagers();
        assertTrue(p.getTeenagerList().contains(ten()));   
    }

    //a faire
    // classe test import et export
}