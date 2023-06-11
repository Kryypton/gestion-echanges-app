import fr.ulille.but.sae2_02.graphes.*;


import java.time.LocalDate;
import java.util.*;


public class AffectationVersion2Test {

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

        Criterion sport = new Criterion("sports", CriterionName.HOBBIES);
        Criterion technologie = new Criterion("technology", CriterionName.HOBBIES);
        Criterion science = new Criterion("science", CriterionName.HOBBIES);
        Criterion reading = new Criterion("reading", CriterionName.HOBBIES);
        Criterion musicEtScience = new Criterion("music,science", CriterionName.HOBBIES);
        Criterion sportsEtTechnologie = new Criterion("sports,technology", CriterionName.HOBBIES);
        Criterion msuciEtReading = new Criterion("music,reading", CriterionName.HOBBIES);



        Teenager teenager9 = new Teenager("Adham" , "A" , "male" , LocalDate.parse("08/10/2004") , Country.FRANCE);
        Teenager teenager10 = new Teenager("Bertrand" , "B" , "male" , LocalDate.parse("12/02/2004") , Country.FRANCE);
        Teenager teenager11 = new Teenager("Claudette" , "C" , "female" , LocalDate.parse("30/12/2004") , Country.FRANCE);
        Teenager teenager12 = new Teenager("Damiene" , "D" , "female" , LocalDate.parse("23/09/2004") , Country.FRANCE);

        Teenager teenager13 = new Teenager("Emile" , "E" , "male" , LocalDate.parse("18/04/2004") , Country.ITALY);     
        Teenager teenager14 = new Teenager("Fabienne" , "F" , "female" , LocalDate.parse("29/11/2004") , Country.ITALY);        
        Teenager teenager15 = new Teenager("Gerard" , "G" , "male" , LocalDate.parse("05/08/2004") , Country.ITALY);   
        Teenager teenager16 = new Teenager("Harvard" , "H" , "male" , LocalDate.parse("08/02/2004") , Country.ITALY);    

        Affectation history2 = new Affectation();

        teenager9.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager9.addCriterion(CriterionName.HISTORY.name(),historySame);
        teenager9.addCriterion(CriterionName.HOBBIES.name(),sport);

        teenager10.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager10.addCriterion(CriterionName.HISTORY.name(),historyOther);
        teenager10.addCriterion(CriterionName.HOBBIES.name(),technologie);

        teenager11.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager11.addCriterion(CriterionName.HISTORY.name(),historyVide);
        teenager11.addCriterion(CriterionName.HOBBIES.name(),reading);

        teenager12.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager12.addCriterion(CriterionName.HISTORY.name(),historyOther);
        teenager12.addCriterion(CriterionName.HOBBIES.name(),musicEtScience);

        teenager13.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager13.addCriterion(CriterionName.HISTORY.name(),historyOther);
        teenager13.addCriterion(CriterionName.HOBBIES.name(),science);

        teenager14.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager14.addCriterion(CriterionName.HISTORY.name(),historySame);
        teenager14.addCriterion(CriterionName.HOBBIES.name(),sport);

        teenager15.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager15.addCriterion(CriterionName.HISTORY.name(),historyVide);
        teenager15.addCriterion(CriterionName.HOBBIES.name(),sportsEtTechnologie);

        teenager16.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager16.addCriterion(CriterionName.HISTORY.name(),historySame);
        teenager16.addCriterion(CriterionName.HOBBIES.name(),msuciEtReading);


        history2.affectations(teenager9, teenager13);
        history2.affectations(teenager10, teenager14);
        history2.affectations(teenager11, teenager15);
        history2.affectations(teenager12, teenager16);


        GrapheNonOrienteValue<Teenager> graphFRChezIT2 = new GrapheNonOrienteValue<Teenager>();
        List<Teenager> fr2 = new ArrayList<Teenager>();
        List<Teenager> it2 = new ArrayList<Teenager>();

        fr2.add(teenager9);
        fr2.add(teenager10);
        fr2.add(teenager11);
        fr2.add(teenager12);

        it2.add(teenager13);
        it2.add(teenager14);
        it2.add(teenager15);
        it2.add(teenager16);

        for (Teenager teenager : fr2) {
            graphFRChezIT2.ajouterSommet(teenager);
        }

        for (Teenager teenager : it2) {
            graphFRChezIT2.ajouterSommet(teenager);
        }

        for (Teenager t1 : fr2) {
            for (Teenager t2 : it2) {
                graphFRChezIT2.ajouterArete(t1, t2, AffectationUtil.weight(t1, t2, history2));
            }
        }

        CalculAffectation<Teenager> calculfr2 = new CalculAffectation<Teenager>(graphFRChezIT2, fr2, it2);
        List<Arete<Teenager>> list2 = calculfr2.calculerAffectation();
        System.out.println("Affectation FR chez IT :");
        System.out.println(list2);
        System.out.println(calculfr2.getCout());


    }
}
