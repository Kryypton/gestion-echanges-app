import fr.ulille.but.sae2_02.graphes.*;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class AffectationVersion1Test {
    /*public static void main(String[] args){

        //AffectationVersion1 test = new AffectationVersion1();

        //Création du graph
        GrapheNonOrienteValue<Teenager> graph= new GrapheNonOrienteValue<Teenager>();

        //Création des critéres d'allergie pour les animaux
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        Criterion posseDeTout = new Criterion("none", CriterionName.HOST_FOOD);
        Criterion mangeTout = new Criterion("none", CriterionName.GUEST_FOOD); 

        Criterion CA = new Criterion("sports,technology", CriterionName.HOBBIES);
        Criterion CB = new Criterion("culture,science", CriterionName.HOBBIES);
        Criterion CC = new Criterion("science,reading", CriterionName.HOBBIES);
        Criterion CX = new Criterion("culture,technology,", CriterionName.HOBBIES);
        Criterion CY = new Criterion("science,reading", CriterionName.HOBBIES);
        Criterion CZ = new Criterion("technology", CriterionName.HOBBIES);

        

        //Création des requirements a l'aide des critérion précedent
        Map<String, Criterion> TenA = new HashMap<>();
        TenA.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        TenA.put(CriterionName.GUEST_FOOD.name(), mangeTout);
        TenA.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        TenA.put(CriterionName.HOBBIES.name(), CA);

        Map<String, Criterion> TenB = new HashMap<>();
        TenB.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        TenB.put(CriterionName.GUEST_FOOD.name(), mangeTout);
        TenB.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estAlergique);
        TenB.put(CriterionName.HOBBIES.name(), CB);

        Map<String, Criterion> TenC = new HashMap<>();
        TenC.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        TenC.put(CriterionName.GUEST_FOOD.name(), mangeTout);
        TenC.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        TenC.put(CriterionName.HOBBIES.name(), CC);

        Map<String, Criterion> TenX = new HashMap<>();
        TenX.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        TenX.put(CriterionName.GUEST_FOOD.name(), mangeTout);
        TenX.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        TenX.put(CriterionName.HOBBIES.name(),CX);

        Map<String, Criterion> TenY = new HashMap<>();
        TenY.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        TenY.put(CriterionName.GUEST_FOOD.name(), mangeTout);
        TenY.put(CriterionName.HOST_HAS_ANIMAL.name(), aUnAnimal);
        TenY.put(CriterionName.HOBBIES.name(), CY);

        Map<String, Criterion> TenZ = new HashMap<>();
        TenZ.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        TenZ.put(CriterionName.GUEST_FOOD.name(), mangeTout);
        TenZ.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        TenZ.put(CriterionName.HOBBIES.name(), CZ);
        
        //Création des Teenager a l'aide des requirement précedent
        Teenager A = new Teenager(1, "A", "Adonia", null, null, Country.FRANCE , TenA);
        Teenager B = new Teenager(1, "B", "Bellatrix", null, null, Country.FRANCE , TenB);
        Teenager C = new Teenager(1, "C", "Callistra", null, null, Country.FRANCE , TenC);
        Teenager X = new Teenager(1, "X", "Xolag", null, null, Country.ITALY , TenX);
        Teenager Y = new Teenager(1, "Y", "Yak", null, null, Country.ITALY , TenY);
        Teenager Z = new Teenager(1, "Z", "Zander", null, null, Country.ITALY , TenZ);

        //Création de liste des étudiants hôtes et invités
        List<Teenager> guest = new ArrayList<Teenager>();
        guest.add(A);
        guest.add(B);
        guest.add(C);
        List<Teenager> host = new ArrayList<Teenager>();
        host.add(X);
        host.add(Y);
        host.add(Z);

        //Création d'une nouvelle affectation
        CalculAffectation<Teenager> calcul = new CalculAffectation<Teenager>(graph,guest,host);

        

        //Ajout de sommet et aretes au graphe
        Affectation.addSummit(guest,graph);
        Affectation.addSummit(host,graph);
        Affectation.addArete(guest,host,graph);

        // for(Teenager list : guest){
        //     for (String s : list.getCriterion("HOBBIES").getValue().split(",")) {
        //         System.out.println(list.getName() + " : " +s);
        //     }
        // }
        // for(Teenager list : host){
        //     for (String s : list.getCriterion("HOBBIES").getValue().split(",")) {
        //         System.out.println(list.getName() + " : " +s);
        //     }
        // }

        //System.out.println(listToString(graph.sommets()));
         System.out.println("Nous somme censé trouvé : \n B--X, A--Z, et C--Y");
        // // //envoie de la solution
         System.out.println(Affectation.listAreteToString(calcul.calculerAffectation()));


        public void testCompatibilityVsHobbies(){
            assertEquals(200, Affectation.compatibilityVsHobbies(A, X));
            assertEquals(200, Affectation.compatibilityVsHobbies(A, Y));
            assertEquals(200, Affectation.compatibilityVsHobbies(A, Z));
            assertEquals(100, Affectation.compatibilityVsHobbies(B, X));
        }
    }*/

    public static void main(String[] args) throws IOException{
        AffectationVersion1Test.testAffectation();
    }

    public static void testAffectation() throws IOException{
        Platform p = new Platform();
        // import un fichier 
        File csv = new File("res/AffectationTest.csv");
        p.setTeenagerList(Platform.importTeenagers(csv));
        // faire machin

        //Affectation.affectation(p.getTeenagerList(),Country.FRANCE,Country.ITALY);

        //exporter fichier

        List<String> ex = Affectation.listAreteToListTeen(Affectation.affectation(p.getTeenagerList(),Country.FRANCE,Country.ITALY));

        Platform.exportTeenagersString(ex, "res/affectationReponse.csv");
        // comparer 2 fichier

        // File csvA = new File("res/affectationReponse.csv");
        // File csvB = new File("res/affectationReponseVerifier.csv");

        //assertTrue(sameCSV(csvA,csvB));
    }

    public boolean sameCSV(File a, File b) throws FileNotFoundException{
        boolean same = true;
        Scanner scanA = new Scanner(a);
        Scanner scanB = new Scanner(b);
        while(scanA.hasNextLine() && scanB.hasNextLine() && same){
            same = scanA.next().equals(scanB.next());
        }
        scanA.close();
        scanB.close();
        return same;
    }
}