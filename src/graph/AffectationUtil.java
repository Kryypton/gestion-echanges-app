package graph;
import fr.ulille.but.sae2_02.graphes.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import Criterion.Country;
import Criterion.Criterion;
import Criterion.CriterionName;
import Tennager.Teenager;

public class AffectationUtil implements Serializable {
    public static final int POIDS_INITIAL = 1000;
    public static final int POID_REDIBITOIRE = 1000;

    public static int poid_initial = POIDS_INITIAL;
    public static int poid_redibitoire = POID_REDIBITOIRE;



    /**
     * Méthode qui permet de savoir le niveau de compatibilité de 2 adolescent. Il commence avec 100 point, et plus ils seront compatible, plus leurs scores diminueras
     * @param host l'adolescent hôte
     * @param visitor l'adolescent invité
     * @return Le poids de leur compatibilité, plus ils est faible, plus ils sont compatible
     */
    
     public static int weight (Teenager host, Teenager guest , Affectation history) {
        int poid = 0;
        //int poids = 0;
        poid -= host.nbLoisirCommun(guest);
        if(!host.compatibleWithGuest(guest)) poid += poid_redibitoire;
        //Age différent ?
        if (host.getDiffAge(guest).toTotalMonths()<18) poid -= 25;
        //poid = poid + poids;
        poid = poid + history.historyTeenager(host, guest) + history.compatibleWishGender(host, guest);
        if (poid < 0) {
            poid = 0;
        }
        return poid;
    }


    public static int weightAdvanced (Teenager host, Teenager guest , Affectation history) {
        int poid = 1000;
        //int poids = 0;
        poid -= host.nbLoisirCommun(guest);
        if(!host.compatibleWithGuest(guest)) poid += 100 ;
        //Age différent ?
        if (host.getDiffAge(guest).toTotalMonths()<18) poid -= 25;
        //poid = poid + poids;
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


    public int getPoidInitial(){
        return poid_initial;
    }

    public int getPoidRedibitoire(){
        return poid_redibitoire;
    }

    public int setPoidInitial(int poid){
        return poid_initial = poid;
    }

    public int setPoidRedibitoire(int poid){
        return poid_redibitoire = poid;
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

        
        ////////////////////////// Graphe Version 1 //////////////////////////

        // Voici l'exemple 1 du sujet implementé

        System.out.println("////////////////////////// Graphe Version 1 //////////////////////////");
        System.out.println("\n");
        System.out.println("Voici l'exemple 1 du sujet implementé");
        System.out.println("\n");

        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);

        Criterion sportEtTechnologie = new Criterion("sports,technology", CriterionName.HOBBIES);
        Criterion sport = new Criterion("sports", CriterionName.HOBBIES);
        Criterion technologie = new Criterion("technology", CriterionName.HOBBIES);
        Criterion scienceEtReading = new Criterion("science,reading", CriterionName.HOBBIES);
        Criterion cultureEtTechnologie = new Criterion("culture,technology", CriterionName.HOBBIES);
        Criterion cultureEtScience = new Criterion("culture,science", CriterionName.HOBBIES);


        Teenager teenager1 = new Teenager("A", "Adonia" , Country.FRANCE);
        Teenager teenager2 = new Teenager("B", "Bellatrix" , Country.FRANCE);
        Teenager teenager3 = new Teenager("C", "Callista" , Country.FRANCE);
        Teenager teenager4 = new Teenager("X", "Xolag" , Country.ITALY);
        Teenager teenager5 = new Teenager("Y", "Yak" , Country.ITALY);
        Teenager teenager6 = new Teenager("Z", "Zander" , Country.ITALY);

        teenager1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager1.addCriterion(CriterionName.HOBBIES.name(),sportEtTechnologie);

        teenager2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estAlergique);
        teenager2.addCriterion(CriterionName.HOBBIES.name(),cultureEtScience);

        teenager3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager3.addCriterion(CriterionName.HOBBIES.name(),scienceEtReading);


        teenager4.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        teenager4.addCriterion(CriterionName.HOBBIES.name(),cultureEtTechnologie);

        teenager5.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aUnAnimal);
        teenager5.addCriterion(CriterionName.HOBBIES.name(),scienceEtReading);

        teenager6.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        teenager6.addCriterion(CriterionName.HOBBIES.name(),technologie);

        GrapheNonOrienteValue<Teenager> graphFRChezIT = new GrapheNonOrienteValue<Teenager>();

        List<Teenager> fr = new ArrayList<Teenager>();
        List<Teenager> it = new ArrayList<Teenager>();

        fr.add(teenager1);
        fr.add(teenager2);
        fr.add(teenager3);

        it.add(teenager4);
        it.add(teenager5);
        it.add(teenager6);

        for (Teenager teenager : fr) {
            graphFRChezIT.ajouterSommet(teenager);
        }

        for (Teenager teenager : it) {
            graphFRChezIT.ajouterSommet(teenager);
        }


        for (Teenager t1 : fr) {
            for (Teenager t2 : it) {
                graphFRChezIT.ajouterArete(t1, t2, AffectationUtil.weightVersion1(t1, t2));
            }
        }

        CalculAffectation<Teenager> calculfr = new CalculAffectation<Teenager>(graphFRChezIT, fr, it);
        List<Arete<Teenager>> list = calculfr.calculerAffectation();
        System.out.println("Affectation francais chez les Italiens :");
        System.out.println(list);
        System.out.println(calculfr.getCout());

        System.out.println("\n");


        // Voici l'exemple 2 du sujet qui implemente IncompatibilityVsHobbies
        System.out.println("Voici l'exemple 2 du sujet qui implemente IncompatibilityVsHobbies :");
        System.out.println("\n");

        Criterion hobbiesDeA = new Criterion("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx", CriterionName.HOBBIES);
        Criterion hobbiesDeD = new Criterion("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx", CriterionName.HOBBIES);


        Teenager teenager7 = new Teenager("A", "A" ,LocalDate.parse("2009-06-01"), Country.ITALY);
        Teenager teenager8 = new Teenager("B", "B" ,LocalDate.parse("2009-06-01"), Country.ITALY);
        Teenager teenager9 = new Teenager("C", "C" ,LocalDate.parse("2009-06-01"),Country.GERMANY);
        Teenager teenager10 = new Teenager("D", "D" , LocalDate.parse("2009-06-01"), Country.GERMANY);

        teenager7.addCriterion(CriterionName.HOBBIES.name(),hobbiesDeA);
        teenager7.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estAlergique);
        teenager7.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aUnAnimal);

        teenager8.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager8.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aUnAnimal);

        teenager9.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager9.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);

        teenager10.addCriterion(CriterionName.HOBBIES.name(),hobbiesDeD);
        teenager10.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager10.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(), aUnAnimal);


        GrapheNonOrienteValue<Teenager> graphALLChezIT = new GrapheNonOrienteValue<Teenager>();
        GrapheNonOrienteValue<Teenager> graphITChezALL = new GrapheNonOrienteValue<Teenager>();


        List<Teenager> allemandList = new ArrayList<Teenager>();
        List<Teenager> italiensList = new ArrayList<Teenager>();

        italiensList.add(teenager7);
        italiensList.add(teenager8);

        allemandList.add(teenager9);
        allemandList.add(teenager10);

        for (Teenager teenager : italiensList) {
            graphALLChezIT.ajouterSommet(teenager);
            graphITChezALL.ajouterSommet(teenager);
        }


        for (Teenager teenager : allemandList) {
            graphALLChezIT.ajouterSommet(teenager);
            graphITChezALL.ajouterSommet(teenager);
        }


        for (Teenager t1 : italiensList) {
            for (Teenager t2 : allemandList) {
                graphITChezALL.ajouterArete(t2, t1, AffectationUtil.weightVersion1(t2, t1));
            }
        }

        for (Teenager t1 : allemandList) {
            for (Teenager t2 : italiensList) {
                graphALLChezIT.ajouterArete(t1, t2, AffectationUtil.weightVersion1(t1, t2));
            }
        }


        CalculAffectation<Teenager> calculALL = new CalculAffectation<Teenager>(graphALLChezIT, allemandList, italiensList);
        List<Arete<Teenager>> listALL = calculALL.calculerAffectation();
        System.out.println("Affectation allemand chez les Italiens :");
        System.out.println(listALL);
        System.out.println(calculALL.getCout());

        System.out.println("\n");

        CalculAffectation<Teenager> calculIT = new CalculAffectation<Teenager>(graphITChezALL, italiensList, allemandList);
        List<Arete<Teenager>> listIT = calculIT.calculerAffectation();
        System.out.println("Affectation italiens chez les Allemands :");
        System.out.println(listIT);
        System.out.println(calculIT.getCout());

        System.out.println("\n");






        ////////////////////////// Graphe Version 2 //////////////////////////


        System.out.println("////////////////////////// Graphe Version 2 //////////////////////////");
        System.out.println("\n");


        // Premiere version de l'exemple pour l'historique

        System.out.println("Premiere version de l'exemple pour l'historique : ");
        System.out.println("\n");

        Criterion historySame = new Criterion("same", CriterionName.HISTORY);
        Criterion historyOther = new Criterion("other", CriterionName.HISTORY);
        // Criterion historyVide = new Criterion("", CriterionName.HISTORY);

        
        Teenager teenager11 = new Teenager("Adham" , "A" , LocalDate.parse("2004-10-08") , Country.FRANCE);
        Teenager teenager12 = new Teenager("Bertrand" , "B"  , LocalDate.parse("2004-02-12") , Country.FRANCE);
        Teenager teenager13 = new Teenager("Claudette" , "C" , LocalDate.parse("2004-12-30") , Country.FRANCE);
        Teenager teenager14 = new Teenager("Damiene" , "D" ,  LocalDate.parse("2004-09-23") , Country.FRANCE);

        Teenager teenager15 = new Teenager("Emile" , "E" , LocalDate.parse("2004-04-18") , Country.ITALY);     
        Teenager teenager16 = new Teenager("Fabienne" , "F" ,  LocalDate.parse("2004-11-29") , Country.ITALY);        
        Teenager teenager17 = new Teenager("Gerard" , "G" , LocalDate.parse("2004-08-05") , Country.ITALY);   
        Teenager teenager18 = new Teenager("Harvard" , "H" ,  LocalDate.parse("2004-02-08") , Country.ITALY);    

        Affectation history = new Affectation();


        teenager11.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager11.addCriterion(CriterionName.HISTORY.name(),historySame);

        teenager12.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager12.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager13.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        // teenager13.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager14.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager14.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager15.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager15.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager16.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager16.addCriterion(CriterionName.HISTORY.name(),historySame);

        teenager17.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        //teenager17.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager18.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager18.addCriterion(CriterionName.HISTORY.name(),historySame);

        history.affectations(teenager11, teenager16);
        history.affectations(teenager12, teenager15);
        history.affectations(teenager13, teenager17);
        history.affectations(teenager14, teenager18);


        GrapheNonOrienteValue<Teenager> graphFRChezIT2 = new GrapheNonOrienteValue<Teenager>();
        List<Teenager> fr2 = new ArrayList<Teenager>();
        List<Teenager> it2 = new ArrayList<Teenager>();

        fr2.add(teenager11);
        fr2.add(teenager12);
        fr2.add(teenager13);
        fr2.add(teenager14);

        it2.add(teenager15);
        it2.add(teenager16);
        it2.add(teenager17);
        it2.add(teenager18);

        for (Teenager teenager : fr2) {
            graphFRChezIT2.ajouterSommet(teenager);
        }

        for (Teenager teenager : it2) {
            graphFRChezIT2.ajouterSommet(teenager);
        }

        for (Teenager t1 : fr2) {
            for (Teenager t2 : it2) {
                graphFRChezIT2.ajouterArete(t1, t2, AffectationUtil.weight(t1, t2, history));
            }
        }

        CalculAffectation<Teenager> calculfr1 = new CalculAffectation<Teenager>(graphFRChezIT2, fr2, it2);
        List<Arete<Teenager>> list2 = calculfr1.calculerAffectation();
        System.out.println("Affectation FR chez IT :");
        System.out.println(list2);
        System.out.println(calculfr1.getCout());
        System.out.println("\n");


        

        // Deuxieme version de l'exemple pour l'historique avec les parametre en plus demandés

        System.out.println("Deuxieme version de l'exemple pour l'historique avec les parametre en plus demandés : ");
        System.out.println("\n");

        Criterion science = new Criterion("science", CriterionName.HOBBIES);
        Criterion reading = new Criterion("reading", CriterionName.HOBBIES);
        Criterion musicEtScience = new Criterion("music,science", CriterionName.HOBBIES);
        Criterion sportsEtTechnologie = new Criterion("sports,technology", CriterionName.HOBBIES);
        Criterion msuciEtReading = new Criterion("music,reading", CriterionName.HOBBIES);



        Teenager teenager19 = new Teenager("Adham" , "A" ,  LocalDate.parse("2004-10-08") , Country.FRANCE);
        Teenager teenager20 = new Teenager("Bertrand" , "B" ,  LocalDate.parse("2004-02-12") , Country.FRANCE);
        Teenager teenager21 = new Teenager("Claudette" , "C" ,  LocalDate.parse("2004-12-30") , Country.FRANCE);
        Teenager teenager22 = new Teenager("Damiene" , "D" ,  LocalDate.parse("2004-09-23") , Country.FRANCE);

        Teenager teenager23 = new Teenager("Emile" , "E" ,  LocalDate.parse("2004-04-18") , Country.ITALY);     
        Teenager teenager24 = new Teenager("Fabienne" , "F" ,  LocalDate.parse("2004-11-29") , Country.ITALY);        
        Teenager teenager25 = new Teenager("Gerard" , "G" ,  LocalDate.parse("2004-08-05") , Country.ITALY);   
        Teenager teenager26 = new Teenager("Harvard" , "H" ,  LocalDate.parse("2004-02-08") , Country.ITALY);    

        Affectation history2 = new Affectation();

        teenager19.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager19.addCriterion(CriterionName.HISTORY.name(),historySame);
        teenager19.addCriterion(CriterionName.HOBBIES.name(),sport);

        teenager20.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager20.addCriterion(CriterionName.HISTORY.name(),historyOther);
        teenager20.addCriterion(CriterionName.HOBBIES.name(),technologie);

        teenager21.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        // teenager21.addCriterion(CriterionName.HISTORY.name(),historyVide);
        teenager21.addCriterion(CriterionName.HOBBIES.name(),reading);

        teenager22.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager22.addCriterion(CriterionName.HISTORY.name(),historyOther);
        teenager22.addCriterion(CriterionName.HOBBIES.name(),musicEtScience);

        teenager23.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager23.addCriterion(CriterionName.HISTORY.name(),historyOther);
        teenager23.addCriterion(CriterionName.HOBBIES.name(),science);

        teenager24.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager24.addCriterion(CriterionName.HISTORY.name(),historySame);
        teenager24.addCriterion(CriterionName.HOBBIES.name(),sport);

        teenager25.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        // teenager25.addCriterion(CriterionName.HISTORY.name(),historyVide);
        teenager25.addCriterion(CriterionName.HOBBIES.name(),sportsEtTechnologie);

        teenager26.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager26.addCriterion(CriterionName.HISTORY.name(),historySame);
        teenager26.addCriterion(CriterionName.HOBBIES.name(),msuciEtReading);


        history2.affectations(teenager19, teenager23);
        history2.affectations(teenager20, teenager24);
        history2.affectations(teenager21, teenager25);
        history2.affectations(teenager22, teenager26);


        GrapheNonOrienteValue<Teenager> graphFRChezIT3 = new GrapheNonOrienteValue<Teenager>();
        List<Teenager> fr3 = new ArrayList<Teenager>();
        List<Teenager> it3 = new ArrayList<Teenager>();

        fr3.add(teenager19);
        fr3.add(teenager20);
        fr3.add(teenager21);
        fr3.add(teenager22);

        it3.add(teenager23);
        it3.add(teenager24);
        it3.add(teenager25);
        it3.add(teenager26);

        for (Teenager teenager : fr3) {
            graphFRChezIT3.ajouterSommet(teenager);
        }

        for (Teenager teenager : it3) {
            graphFRChezIT3.ajouterSommet(teenager);
        }

        for (Teenager t1 : fr3) {
            for (Teenager t2 : it3) {
                graphFRChezIT3.ajouterArete(t1, t2, AffectationUtil.weight(t1, t2, history2));
            }
        }

        CalculAffectation<Teenager> calculfr3 = new CalculAffectation<Teenager>(graphFRChezIT3, fr3, it3);
        List<Arete<Teenager>> list3 = calculfr3.calculerAffectation();
        System.out.println("Affectation FR chez IT :");
        System.out.println(list3);
        System.out.println(calculfr3.getCout());
        System.out.println("\n");   

    }
}