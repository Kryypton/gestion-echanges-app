import fr.ulille.but.sae2_02.graphes.*;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class AffectationVersion2Test {
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
        name4 = "Dylan";
        forname1 = "Brown";
        forname2 = "Dumont";
        forname3 = "Garnier";
        forname4 = "Lefebvre";
        gender1 = "F";
        gender2 = "M";
        gender3 = "M";
        gender4 = "M";
        countryName1 = Country.FRANCE;
        countryName2 = Country.GERMANY;
        countryName3 = Country.SPAIN;
        countryName4 = Country.ITALY;
        birthDate1 = LocalDate.parse("2000-01-01");
        birthDate2 = LocalDate.parse("2002-08-04");
        birthDate3 = LocalDate.parse("2001-11-21");
        birthDate4 = LocalDate.parse("2003-03-15");

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
        
        t1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        t1.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        t1.addCriterion(CriterionName.HOST_FOOD.name(), posseDeTout);
        t1.addCriterion(CriterionName.GUEST_FOOD.name(), mangeTout);

        t4.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        t4.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        t4.addCriterion(CriterionName.HOST_FOOD.name(), saisieIncorrect);
        t4.addCriterion(CriterionName.GUEST_FOOD.name(), saisieIncorrect);
    }

    @Test
    public void testWeight(){
        assertEquals(0, Affectation.weight(t1,t2,history/* A FAIRE History */), 0.1);
        assertEquals(0, Affectation.weight(t1,t2,history/* A FAIRE History */), 0.1);
        assertEquals(0, Affectation.weight(t1,t2,history/* A FAIRE History */), 0.1);
    }

    @Test
    public void testSelectPays(){
        
    }

    @Test
    public void testListAreteToListTeen(){}

    // public static void testAffectation() throws IOException{
    //     Platform p = new Platform();
    //     // import un fichier 
    //     File csv = new File("res/AffectationTest.csv");
    //     p.setTeenagerList(Platform.importListTeenagers(csv));
    //     // faire machin

    //     Affectation.affectation(p.getTeenagerList(),Country.FRANCE,Country.ITALY);

    //     //exporter fichier

    //     List<String> ex = Affectation.listAreteToListTeen(Affectation.affectation(p.getTeenagerList(),Country.FRANCE,Country.ITALY));

    //     Map<Teenager,Teenager> ex = exportCompatibleTeenager

    //     Platform.exportTeenagersString(ex, "res/affectationReponse.csv");
    //     // comparer 2 fichier

    //     File csvA = new File("res/affectationReponse.csv");
    //     File csvB = new File("res/affectationReponseVerifier.csv");

    //     assertTrue(sameCSV(csvA,csvB));
    // }

    // public boolean sameCSV(File a, File b) throws FileNotFoundException{
    //     boolean same = true;
    //     Scanner scanA = new Scanner(a);
    //     Scanner scanB = new Scanner(b);
    //     while(scanA.hasNextLine() && scanB.hasNextLine() && same){
    //         same = scanA.next().equals(scanB.next());
    //     }
    //     scanA.close();
    //     scanB.close();
    //     return same;
    // }
}
