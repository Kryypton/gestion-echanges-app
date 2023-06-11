import fr.ulille.but.sae2_02.graphes.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class AffectationUtil implements Serializable {
    /**
     * Méthode qui permet de savoir le niveau de compatibilité de 2 adolescent. Il commence avec 100 point, et plus ils seront compatible, plus leurs scores diminueras
     * @param host l'adolescent hôte
     * @param visitor l'adolescent invité
     * @return Le poids de leur compatibilité, plus ils est faible, plus ils sont compatible
     */
    
     public static int weight (Teenager host, Teenager guest , Affectation history) {
        int poid = 100;
        int poids = 0;
        poids -= host.nbLoisirCommun(guest);
        if(!host.compatibleWithGuest(guest)) poids += 100;
        //Pays différent ?
        if (host.getCountryName().getCOUNTRY_NAME().equals(guest.getCountryName().getCOUNTRY_NAME())) poids += 10;
        //Age différent ?
        if (host.getDiffAge(guest).toTotalMonths()>18) poids -= 25;
        

        poid = poid + poids;
        poid = poid + history.historyTeenager(host, guest) + history.compatibleWishGender(host, guest);
        if (poid < 0) {
            poid = 0;
        }
        return poid;
    }


    public static int weightVersion1 (Teenager host, Teenager guest) {
        int poid = 100;
        int poids = 0;
        poids -= host.nbLoisirCommun(guest);
        if(!host.compatibleWithGuest(guest)) poids += 100;
        poid = poid + poids;
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
                graph.ajouterArete(teenager1,teenager2,weight(teenager1,teenager2 , new Affectation() ));
                //System.out.println(teenager1.getName() +" avec " + teenager2.getName() +" vaut " + weight(teenager1,teenager2));
            }
        }
    }

    
    /**
     * Méthode qui permet de recuperer tout les étudiants appartenant a un pays donnée
     * @param list la liste d'étudiants
     * @param country le pays sélectionner
     * @return une liste des étudiants appartenants aux pays country
     */
    public static List<Teenager> selectPays(List<Teenager> list, Country country){
        List<Teenager> listCountry = new ArrayList<Teenager>();
        for (Teenager teenager : list){
            if (teenager.getCountryName().equals(country)){
                listCountry.add(teenager);
            }
        }
        return listCountry;
    }

    /**
     * Méthode qui permet de crée les aretes composant le graph
     * @param guest La liste des étudiants invités a ajouter
     * @param host La liste des étudiants hôtes a ajouter
     * @param graph Le graph dans lequel on ajoute les aretes
     */
    public static List<Arete<Teenager>> affectation(List<Teenager> groupe , Country guest , Country host){
        List<Teenager> hostList = AffectationUtil.selectPays(groupe, host);    
        List<Teenager> guestList = AffectationUtil.selectPays(groupe, guest);
        GrapheNonOrienteValue<Teenager> graph = new GrapheNonOrienteValue<Teenager>();
        for (Teenager teenager1 : hostList) {
            graph.ajouterSommet(teenager1);
        }
        for (Teenager teenager2 : guestList) {
            graph.ajouterSommet(teenager2);
        }
        for(Teenager teenager1 : hostList){
            for (Teenager teenager2 : guestList){
                graph.ajouterArete(teenager1,teenager2,weight(teenager1,teenager2 , new Affectation() ));
            }
        }
        CalculAffectation<Teenager> calcul = new CalculAffectation<Teenager>(graph , hostList , guestList);
        return calcul.calculerAffectation();
    }


    /**
     * Méthode qui permet de retourner sous String une liste du nom des étudiants
     * @param list
     * @return Un String listants les nom des étudiants
     */
    public static String listToString(List<Teenager> list){
        String ten = "";
        for (Teenager teenager : list){
            ten += teenager.getName() + "\n";
        }
        return ten;
    }

    /**
     * Méthode qui permet de retourner sous String le nom de deux adolescent avec le poid de leur compatibilité
     * @param list la liste d'adolescent
     * @return Un string avec le nom de deux adolescent avec le poid de leur compatibilité
     */
    public static String listAreteToString(List<Arete<Teenager>> list){
        String ten = "";
        for (Arete<Teenager> teenager : list){
            ten += teenager.getExtremite1().getName() + " and " + teenager.getExtremite2().getName()+ ": " +teenager.getPoids() + "\n";
        }
        return ten;
    }

    /**
     * Méthode qui permet de retourner sous une liste de String 2 adolescent compatible
     * @param list la liste d'adolescent
     * @return Une liste de String avec des adolescent compatible
     */
    public static Map<Teenager,Teenager> listAreteToListTeen(List<Arete<Teenager>> list){
        Map<Teenager,Teenager> newMap = new HashMap<Teenager,Teenager>();
        for (Arete<Teenager> teenager : list){
            newMap.put(teenager.getExtremite1(),teenager.getExtremite2());
        }
        return newMap;
    }


    public static void main(String[] args) {


        // Premiere version de l'exemple pour l'historique

        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion historySame = new Criterion("same", CriterionName.HISTORY);
        Criterion historyOther = new Criterion("other", CriterionName.HISTORY);
        Criterion historyVide = new Criterion("", CriterionName.HISTORY);

        
        Teenager teenager1 = new Teenager("Adham" , "A" , "male" , LocalDate.parse("08/10/2004") , Country.FRANCE);
        Teenager teenager2 = new Teenager("Bertrand" , "B" , "male" , LocalDate.parse("12/02/2004") , Country.FRANCE);
        Teenager teenager3 = new Teenager("Claudette" , "C" , "female" , LocalDate.parse("30/12/2004") , Country.FRANCE);
        Teenager teenager4 = new Teenager("Damiene" , "D" , "female" , LocalDate.parse("23/09/2004") , Country.FRANCE);

        Teenager teenager5 = new Teenager("Emile" , "E" , "male" , LocalDate.parse("18/04/2004") , Country.ITALY);     
        Teenager teenager6 = new Teenager("Fabienne" , "F" , "female" , LocalDate.parse("29/11/2004") , Country.ITALY);        
        Teenager teenager7 = new Teenager("Gerard" , "G" , "male" , LocalDate.parse("05/08/2004") , Country.ITALY);   
        Teenager teenager8 = new Teenager("Harvard" , "H" , "male" , LocalDate.parse("08/02/2004") , Country.ITALY);    

        Affectation history = new Affectation();


        teenager1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager1.addCriterion(CriterionName.HISTORY.name(),historySame);

        teenager2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager2.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager3.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager4.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager4.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager5.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager5.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager6.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager6.addCriterion(CriterionName.HISTORY.name(),historySame);

        teenager7.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager7.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager8.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager8.addCriterion(CriterionName.HISTORY.name(),historySame);

        history.affectations(teenager1, teenager8);
        history.affectations(teenager2, teenager4);
        history.affectations(teenager5, teenager6);
        history.affectations(teenager7, teenager3);


        GrapheNonOrienteValue<Teenager> graphFRChezIT = new GrapheNonOrienteValue<Teenager>();
        List<Teenager> fr = new ArrayList<Teenager>();
        List<Teenager> it = new ArrayList<Teenager>();

        fr.add(teenager1);
        fr.add(teenager2);
        fr.add(teenager3);
        fr.add(teenager4);

        it.add(teenager5);
        it.add(teenager6);
        it.add(teenager7);
        it.add(teenager8);

        for (Teenager teenager : fr) {
            graphFRChezIT.ajouterSommet(teenager);
        }

        for (Teenager teenager : it) {
            graphFRChezIT.ajouterSommet(teenager);
        }

        for (Teenager t1 : fr) {
            for (Teenager t2 : it) {
                graphFRChezIT.ajouterArete(t1, t2, AffectationUtil.weight(t1, t2, history));
            }
        }

        CalculAffectation<Teenager> calculfr = new CalculAffectation<Teenager>(graphFRChezIT, fr, it);
        List<Arete<Teenager>> list = calculfr.calculerAffectation();
        System.out.println("Affectation FR chez IT :");
        System.out.println(list);
        System.out.println(calculfr.getCout());

        System.out.println("\n");



        // Deuxieme version de l'exemple pour l'historique avec les parametre en plus demandés

        

    }
}