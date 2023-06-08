import fr.ulille.but.sae2_02.graphes.*;
import java.io.Serializable;
import java.util.*;

public class AffectationUtil implements Serializable {
    /**
     * Méthode qui permet de savoir le niveau de compatibilité de 2 adolescent. Il commence avec 10 point, et plus ils seront compatible, plus leurs scores diminueras
     * @param host l'adolescent hôte
     * @param visitor l'adolescent invité
     * @return Le poids de leur compatibilité, plus ils est faible, plus ils sont compatible
     */
    
     public static int weight (Teenager host, Teenager guest , Affectation history) {
        int poid = 10;
        int poids = 0;
        poids -= host.nbLoisirCommun(guest);
        if(!host.compatibleWithGuest(guest)) poids += 100;
        //Pays différent ?
        if (host.getCriterion("COUNTRY").equals(guest.getCriterion("COUNTRY"))) poids += 10;
        //Age différent ?
        if (host.getDiffAge(guest).toTotalMonths()>18) poids -= 25;
        if (host.getGender().equals(guest.getGender()))

        poid = poids;
        poid = poid + history.historyTeenager(host, guest) + history.compatibleWishGender(host, guest);
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
}