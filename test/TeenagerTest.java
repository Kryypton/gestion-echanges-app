import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeenagerTest {
    public Teenager t1, t2, t3, t4;
    public int id1, id2, id3, id4;
    public String name1, name2, name3, name4;
    public String forname1, forname2, forname3, forname4;
    public String gender1, gender2, gender3, gender4;
    public Country countryName1, countryName2, countryName3, countryName4;
    public LocalDate birthDate1, birthDate2, birthDate3, birthDate4;
    public Map<String, Criterion> requirements1, requirements2, requirements3, requirements4;
  

    @BeforeEach
    void initialization() {
        id1 = 1;
        id2 = 2;
        id3 = 3;
        id4 = 4;
        name1 = "Alice";
        name2 = "Bruno";
        name3 = "Clément";
        name4 = "mauvaisEtudiant";
        forname1 = "Brown";
        forname2 = "Dumont";
        forname3 = "Garnier";
        forname4 = "mauvaisEtudiant";
        gender1 = "F";
        gender2 = "M";
        gender3 = "M";
        gender4 = "F";
        countryName1 = Country.FRANCE;
        countryName2 = Country.ALLEMAGNE;
        countryName3 = Country.ESPAGNE;
        countryName4 = Country.ITALIE;
        birthDate1 = LocalDate.parse("2000-01-01");
        birthDate2 = LocalDate.parse("2002-08-04");
        birthDate3 = LocalDate.parse("2001-11-21");
        birthDate4 = LocalDate.parse("2000-11-18");

        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        Criterion possedeVege = new Criterion("végétarien", CriterionName.HOST_FOOD);
        Criterion possedeSport = new Criterion("sport", CriterionName.HOST_FOOD);
        Criterion posseDeTout = new Criterion("all", CriterionName.HOST_FOOD);
        Criterion mangeVege = new Criterion("végétarien", CriterionName.GUEST_FOOD);
        Criterion mangeSport = new Criterion("sport", CriterionName.GUEST_FOOD); 
        Criterion mangeTout = new Criterion("all", CriterionName.GUEST_FOOD);
        Criterion uselessAlergie = new Criterion("useless", CriterionName.GUEST_ANIMAL_ALLERGY);

        requirements1 = new HashMap<String, Criterion>();
        requirements1.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        requirements1.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        requirements1.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        requirements1.put(CriterionName.GUEST_FOOD.name(), mangeTout);

        requirements2 = new HashMap<String, Criterion>();
        requirements2.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        requirements2.put(CriterionName.HOST_HAS_ANIMAL.name(), aUnAnimal);
        requirements2.put(CriterionName.HOST_FOOD.name(), possedeVege);
        requirements2.put(CriterionName.GUEST_FOOD.name(), mangeVege);

        requirements3 = new HashMap<String, Criterion>();
        requirements3.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estAlergique);
        requirements3.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        requirements3.put(CriterionName.HOST_FOOD.name(), possedeSport);
        requirements3.put(CriterionName.GUEST_FOOD.name(), mangeSport);

        requirements4 = new HashMap<String, Criterion>();
        requirements4.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), uselessAlergie);
        requirements4.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        requirements4.put(CriterionName.HOST_FOOD.name(), possedeSport);
        requirements4.put(CriterionName.GUEST_FOOD.name(), mangeSport);

        t1 = new Teenager(id1, name1, forname1, gender1, birthDate1, countryName1, requirements1);
        t2 = new Teenager(id2, name2, forname2, gender2, birthDate2, countryName2, requirements2);
        t3 = new Teenager(id3, name3, forname3, gender3, birthDate3, countryName3, requirements3);
        t4 = new Teenager(id4, name4, forname4, gender4, birthDate4, countryName4, requirements4);
    }

    @Test
    void testCompatibleAnimal() {
        assertTrue(t1.compatibleAnimal(t2));
        assertTrue(t1.compatibleAnimal(t3));
        assertTrue(t2.compatibleAnimal(t1));
        assertFalse(t2.compatibleAnimal(t3));
        assertTrue(t3.compatibleAnimal(t2));
        assertTrue(t3.compatibleAnimal(t1));
    }

    @Test
    void testCompatibleFood() {
        assertTrue(t1.compatibleFood(t2));
        assertTrue(t1.compatibleFood(t3));
        assertTrue(t2.compatibleFood(t1));
        assertFalse(t2.compatibleFood(t3));
        assertFalse(t3.compatibleFood(t2));
        assertTrue(t3.compatibleFood(t1));
    }

    @Test
    void testEquals() {
        assertTrue(t1.equals(t1));
        assertFalse(t1.equals(t2));
        assertFalse(t1.equals(t3));
        assertFalse(t2.equals(t1));
        assertTrue(t2.equals(t2));
        assertFalse(t2.equals(t3));
        assertFalse(t3.equals(t1));
        assertFalse(t3.equals(t2));
        assertTrue(t3.equals(t3));
    }

    @Test
    void testPurgeInvalidRequirement() {
        t1.purgeInvalidRequirement();
        assertEquals(t1.getRequirements().size(), 4);
        t2.purgeInvalidRequirement();
        assertEquals(t2.getRequirements().size(), 4);
        t3.purgeInvalidRequirement();
        assertEquals(t3.getRequirements().size(), 4);
        t4.purgeInvalidRequirement();
        assertEquals(t4.getRequirements().size(), 3);
    }

}
