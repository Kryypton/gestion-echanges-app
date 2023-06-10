import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeenagerTest {
    public Teenager t1, t2, t3, t4, t5;
    public int id1, id2, id3, id4 ,id5;
    public String name1, name2, name3, name4 ,name5;
    public String forname1, forname2, forname3, forname4, forname5;
    public String gender1, gender2, gender3, gender4, gender5;
    public Country countryName1, countryName2, countryName3, countryName4, countryName5;
    public LocalDate birthDate1, birthDate2, birthDate3, birthDate4, birthDate5;
    public Map<String, Criterion> requirements1, requirements2, requirements3, requirements4, requirements5;

    @BeforeEach
    void initialization() {
        id1 = 1;
        id2 = 2;
        id3 = 3;
        id4 = 4;
        id5 = 5;
        name1 = "Alice";
        name2 = "Bruno";
        name3 = "Clément";
        name4 = "Dylan";
        name5 = "Eva";
        forname1 = "Brown";
        forname2 = "Dumont";
        forname3 = "Garnier";
        forname4 = "Lefebvre";
        forname5 = "Martin";
        gender1 = "F";
        gender2 = "M";
        gender3 = "M";
        gender4 = "M";
        gender5 = "F";
        countryName1 = Country.FRANCE;
        countryName2 = Country.GERMANY;
        countryName3 = Country.SPAIN;
        countryName4 = Country.ITALY;
        countryName5 = Country.FRANCE;
        birthDate1 = LocalDate.parse("2000-01-01");
        birthDate2 = LocalDate.parse("2002-08-04");
        birthDate3 = LocalDate.parse("2001-11-21");
        birthDate4 = LocalDate.parse("2003-03-15");
        birthDate5 = LocalDate.parse("2001-12-03");

        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        Criterion possedeVege = new Criterion("végétarien", CriterionName.HOST_FOOD);
        Criterion possedeSport = new Criterion("sport", CriterionName.HOST_FOOD);
        Criterion posseDeTout = new Criterion("none", CriterionName.HOST_FOOD);
        Criterion mangeTout = new Criterion("none", CriterionName.GUEST_FOOD);  
        Criterion mangeVege = new Criterion("végétarien", CriterionName.GUEST_FOOD);
        Criterion mangeSport = new Criterion("sport", CriterionName.GUEST_FOOD); 
        Criterion saisieIncorrect = new Criterion("pasBien", CriterionName.NUMERIC);
        Criterion biologie = new Criterion("biologie", CriterionName.HOBBIES);

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
        
        t1 = new Teenager(id1, name1, forname1, gender1, birthDate1, countryName1);
        t2 = new Teenager(id2, name2, forname2, gender2, birthDate2, countryName2, requirements2);
        t3 = new Teenager(id3, name3, forname3, gender3, birthDate3, countryName3, requirements3);
        t4 = new Teenager(id4, forname4, forname4, gender4, birthDate4, countryName4);
        t5 = new Teenager(id5, name5, forname5, gender5, birthDate5, countryName5);

        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        t1.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        t1.addCriterion(CriterionName.HOST_FOOD.name(), posseDeTout);
        t1.addCriterion(CriterionName.GUEST_FOOD.name(), mangeTout);
        t1.addCriterion(CriterionName.GUEST_FOOD.name(), biologie);

        t4.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        t4.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        t4.addCriterion(CriterionName.HOST_FOOD.name(), saisieIncorrect);
        t4.addCriterion(CriterionName.GUEST_FOOD.name(), saisieIncorrect);

        t5.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        t5.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        t5.addCriterion(CriterionName.HOST_FOOD.name(), saisieIncorrect);
        t5.addCriterion(CriterionName.GUEST_FOOD.name(), saisieIncorrect);
        t5.addCriterion(CriterionName.HOBBIES.name(), biologie);
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
        assertEquals(t4.getRequirements().size(), 2);
    }

    @Test
    void testCompatibleWithGuest(){
        assertTrue(t1.compatibleWithGuest(t2));
        assertTrue(t1.compatibleWithGuest(t3));
        assertTrue(t2.compatibleWithGuest(t1));
        assertFalse(t2.compatibleWithGuest(t3));
        assertFalse(t3.compatibleWithGuest(t2));
        assertTrue(t3.compatibleWithGuest(t1));
        assertFalse(t1.compatibleWithGuest(t5));
    }

    @Test
    void testGetAgeYear(){
        assertEquals(t1.getAgeYear(), 23);
        assertEquals(t2.getAgeYear(), 20);
        assertEquals(t3.getAgeYear(), 21);
        assertEquals(t4.getAgeYear(), 20);
    }

    @Test
    void testAddCriterion(){
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Teenager t5 = new Teenager(id1, forname2, forname1, gender1, birthDate1, countryName1);
        assertTrue(t5.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique));
        assertEquals(t5.getRequirements().size(), 1);
        Teenager t6 = new Teenager(id1, forname2, forname1, gender1, birthDate1, countryName1);
        assertTrue(t6.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique));
        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        assertFalse(t6.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estAlergique));
        // on ne peut pas ajouter deux fois le même critère
        assertEquals(t6.getRequirements().size(), 1);
        assertEquals(t6.getRequirements().get(CriterionName.GUEST_ANIMAL_ALLERGY.name()).getValue(), "no");
    }

    @Test
    void testEditCriterion(){
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Teenager t5 = new Teenager(id1, forname2, forname1, gender1, birthDate1, countryName1);
        t5.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        t5.editCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estAlergique);
        assertEquals(t5.getRequirements().size(), 1);
        assertEquals(t5.getRequirements().get(CriterionName.GUEST_ANIMAL_ALLERGY.name()).getValue(), "yes");
    }

    @Test
    void testNbLoisirCommun() {
        assertEquals(t1.nbLoisirCommun(t5), 0);
    }
}
