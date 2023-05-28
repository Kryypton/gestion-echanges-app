import fr.ulille.but.sae2_02.graphes.*;
import java.util.*;

public class AffectationVersion1{

    /**
     * Méthode qui permet de savoir le niveau de compatibilité de 2 adolescent. Il commence avec 10 point, et plus ils seront compatible, plus leurs scores diminueras
     * @param host l'adolescent hôte
     * @param visitor l'adolescent invité
     * @return Le poids de leur compatibilité, plus ils est faible, plus ils sont compatible
     */
    
     public static double weight (Teenager host, Teenager guest) {
        double poid = 10;
        poid -= host.nbLoisirCommun(guest);
        if(!host.compatibleWithGuest(guest)){
            poid += 100;
        }
        //Pays différent ?
        if (!host.getCriterion("COUNTRY").equals(guest.getCriterion("COUNTRY"))) {
            poid += 10;
        }
        return poid;
    }

    /**
     * Méthode qui permet de crée les sommets formé par les étudiants pour le graph
     * @param list La liste des étudiants a ajouter
     * @param graph Le graph dans lequel on ajoute les sommets
     */
    public static void addSummit(List<Teenager> list, GrapheNonOrienteValue<Teenager> graph){
        for (Teenager teenager : list) {
            graph.ajouterSommet(teenager);
        }
    }

    /**
     * Méthode qui permet de crée les aretes composant le graph
     * @param guest La liste des étudiants invités a ajouter
     * @param host La liste des étudiants hôtes a ajouter
     * @param graph Le graph dans lequel on ajoute les aretes
     */
    public static void addArete(List<Teenager> guest,List<Teenager> host, GrapheNonOrienteValue<Teenager> graph){
        for (Teenager teenager1 : host) {
            for (Teenager teenager2 : guest){
                graph.ajouterArete(teenager1,teenager2,weight(teenager1,teenager2));
                //System.out.println(teenager1.getName() +" avec " + teenager2.getName() +" vaut " + weight(teenager1,teenager2));
            }
        }
    }

    public static String listToString(List<Teenager> list){
        String ten = "";
        for (Teenager teenager : list){
            ten += teenager.getName() + "\n";
        }
        return ten;
    }

    public static String listAreteToString(List<Arete<Teenager>> list){
        String ten = "";
        for (Arete<Teenager> teenager : list){
            ten += teenager.getExtremite1().getName() + " and " + teenager.getExtremite2().getName()+ ": " +teenager.getPoids() + "\n";
        }
        return ten;
    }
       
/* 
    public static void main(String[] args){


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
        Teenager X = new Teenager(1, "X", "Xolag", null, null, Country.ITALIE , TenX);
        Teenager Y = new Teenager(1, "Y", "Yak", null, null, Country.ITALIE , TenY);
        Teenager Z = new Teenager(1, "Z", "Zander", null, null, Country.ITALIE , TenZ);

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
        addSummit(guest,graph);
        addSummit(host,graph);
        addArete(guest,host,graph);

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
        // System.out.println("Nous somme censé trouvé : \n B--X, A--Z, et C--Y");
        // // //envoie de la solution
        // System.out.println(listAreteToString(calcul.calculerAffectation()));
    }*/
}