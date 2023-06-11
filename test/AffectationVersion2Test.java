import fr.ulille.but.sae2_02.graphes.*;


import java.time.LocalDate;
import java.util.*;


public class AffectationVersion2Test {

    public static void main(String[] args) {
        
    
        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        
        Criterion sport = new Criterion("sports", CriterionName.HOBBIES);
        Criterion technologie = new Criterion("technology", CriterionName.HOBBIES);
        Criterion science = new Criterion("science", CriterionName.HOBBIES);
        Criterion reading = new Criterion("reading", CriterionName.HOBBIES);
        Criterion music = new Criterion("music", CriterionName.HOBBIES);
        Criterion hobbiesVide = new Criterion("", CriterionName.HOBBIES);
        Criterion GuestFoodVege = new Criterion("vegetarian", CriterionName.GUEST_FOOD);
        Criterion GuestFoodNonuts = new Criterion("nonuts", CriterionName.GUEST_FOOD);
        Criterion HostFoodVege = new Criterion("vegetarian", CriterionName.HOST_FOOD);
        Criterion HostFoodNonuts = new Criterion("nonuts", CriterionName.HOST_FOOD);
        Criterion HostFoodVide = new Criterion("", CriterionName.HOST_FOOD);
        Criterion GuestfoodVide = new Criterion("", CriterionName.GUEST_FOOD);
        Criterion femme = new Criterion("female", CriterionName.GENDER);
        Criterion homme = new Criterion("homme", CriterionName.GENDER);
        Criterion other = new Criterion("other", CriterionName.GENDER);
        Criterion veutFemme = new Criterion("female", CriterionName.PAIR_GENDER);
        Criterion veutHomme = new Criterion("homme", CriterionName.PAIR_GENDER);
        Criterion veutOther = new Criterion("other", CriterionName.PAIR_GENDER);
        Criterion veutvidePair = new Criterion("", CriterionName.PAIR_GENDER);
        Criterion historySame = new Criterion("same", CriterionName.HISTORY);
        Criterion historyOther = new Criterion("other", CriterionName.HISTORY);
        Criterion historyVide = new Criterion("", CriterionName.HISTORY);

        Teenager teenager1 = new Teenager(1, "teen1", "A", "male", LocalDate.of(2000, 5, 10), Country.FRANCE);
        Teenager teenager2 = new Teenager(2, "teen2", "B", "female", LocalDate.of(2001, 8, 15), Country.ITALY);
        Teenager teenager3 = new Teenager(3, "teen3", "C", "male", LocalDate.of(2002, 10, 20), Country.ITALY);
        Teenager teenager4 = new Teenager(4, "teen4", "D", "male", LocalDate.of(2002, 8, 20), Country.FRANCE);
        Teenager teenager5 = new Teenager(5, "teen5", "E", "other", LocalDate.of(1999, 12, 20), Country.FRANCE);
        Teenager teenager6 = new Teenager(6, "teen6", "F", "female", LocalDate.of(2000, 5, 20), Country.FRANCE);
        Teenager teenager7 = new Teenager(7, "teen7", "G", "female", LocalDate.of(2002, 10, 20), Country.FRANCE);
        Teenager teenager8 = new Teenager(8, "teen8", "H", "male", LocalDate.of(2001, 10, 20), Country.ITALY);
        Teenager teenager9 = new Teenager(9, "teen9", "I", "other", LocalDate.of(1999, 10, 20), Country.ITALY);
        Teenager teenager10 = new Teenager(10, "teen10", "J", "female", LocalDate.of(2002, 10, 01), Country.ITALY);
        Teenager teenager11 = new Teenager(11, "teen11", "K", "female", LocalDate.of(2002, 10, 10), Country.ITALY);
        Teenager teenager12 = new Teenager(12, "teen12", "L", "male", LocalDate.of(2003, 2, 21), Country.FRANCE);
        Teenager teenager13 = new Teenager(13, "teen13", "M", "male", LocalDate.of(2002, 10, 30), Country.FRANCE);
        Teenager teenager14 = new Teenager(14, "teen14", "N", "male", LocalDate.of(2000, 3, 20), Country.FRANCE);
        Teenager teenager15 = new Teenager(15, "teen15", "O", "male", LocalDate.of(2003, 10, 20), Country.ITALY);
        Teenager teenager16 = new Teenager(16, "teen16", "M", "male", LocalDate.of(2001, 1, 25), Country.ITALY);

        Affectation history = new Affectation();
        
        teenager1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager1.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager1.addCriterion(CriterionName.HOBBIES.name(),sport);
        teenager1.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager1.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVege);
        teenager1.addCriterion(CriterionName.GENDER.name(),homme);
        teenager1.addCriterion(CriterionName.PAIR_GENDER.name(),veutHomme);
        teenager1.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager2.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aUnAnimal);
        teenager2.addCriterion(CriterionName.HOBBIES.name(),technologie);
        teenager2.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodVege);
        teenager2.addCriterion(CriterionName.HOST_FOOD.name(), HostFoodVide);
        teenager2.addCriterion(CriterionName.GENDER.name(),femme);
        teenager2.addCriterion(CriterionName.PAIR_GENDER.name(),veutvidePair);
        teenager2.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager3.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager3.addCriterion(CriterionName.HOBBIES.name(),music);
        teenager3.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager3.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);
        teenager3.addCriterion(CriterionName.GENDER.name(),homme);
        teenager3.addCriterion(CriterionName.PAIR_GENDER.name(),veutOther);
        teenager3.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager4.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager4.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager4.addCriterion(CriterionName.HOBBIES.name(),reading);
        teenager4.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager4.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);
        teenager4.addCriterion(CriterionName.GENDER.name(),homme);
        teenager4.addCriterion(CriterionName.PAIR_GENDER.name(),veutHomme);
        teenager4.addCriterion(CriterionName.HISTORY.name(),historySame);

        teenager5.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager5.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aUnAnimal);
        teenager5.addCriterion(CriterionName.HOBBIES.name(),sport);
        teenager5.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager5.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVege);
        teenager5.addCriterion(CriterionName.GENDER.name(),other);
        teenager5.addCriterion(CriterionName.PAIR_GENDER.name(),veutHomme);
        teenager5.addCriterion(CriterionName.HISTORY.name(),historySame);

        teenager6.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estAlergique);
        teenager6.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager6.addCriterion(CriterionName.HOBBIES.name(),technologie);
        teenager6.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodNonuts);
        teenager6.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);
        teenager6.addCriterion(CriterionName.GENDER.name(),femme);
        teenager6.addCriterion(CriterionName.PAIR_GENDER.name(),veutOther);
        teenager6.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager7.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager7.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager7.addCriterion(CriterionName.HOBBIES.name(),science);
        teenager7.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager7.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);
        teenager7.addCriterion(CriterionName.GENDER.name(),femme);
        teenager7.addCriterion(CriterionName.PAIR_GENDER.name(),veutFemme);
        teenager7.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager8.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estAlergique);
        teenager8.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager8.addCriterion(CriterionName.HOBBIES.name(),reading);
        teenager8.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager8.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);
        teenager8.addCriterion(CriterionName.GENDER.name(),homme);
        teenager8.addCriterion(CriterionName.PAIR_GENDER.name(),veutvidePair);
        teenager8.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager9.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager9.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aUnAnimal);
        teenager9.addCriterion(CriterionName.HOBBIES.name(),hobbiesVide);
        teenager9.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodNonuts);
        teenager9.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVege);
        teenager9.addCriterion(CriterionName.GENDER.name(),other);
        teenager9.addCriterion(CriterionName.PAIR_GENDER.name(),veutvidePair);
        teenager9.addCriterion(CriterionName.HISTORY.name(),historySame);

        teenager10.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager10.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager10.addCriterion(CriterionName.HOBBIES.name(),technologie);
        teenager10.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager10.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);
        teenager10.addCriterion(CriterionName.GENDER.name(),femme);
        teenager10.addCriterion(CriterionName.PAIR_GENDER.name(),veutvidePair);
        teenager10.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager11.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager11.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aUnAnimal);
        teenager11.addCriterion(CriterionName.HOBBIES.name(),science);
        teenager11.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager11.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVege);
        teenager11.addCriterion(CriterionName.GENDER.name(),femme);
        teenager11.addCriterion(CriterionName.PAIR_GENDER.name(),veutFemme);
        teenager11.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager12.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estAlergique);
        teenager12.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager12.addCriterion(CriterionName.HOBBIES.name(),reading);
        teenager12.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodNonuts);
        teenager12.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);
        teenager12.addCriterion(CriterionName.GENDER.name(),homme);
        teenager12.addCriterion(CriterionName.PAIR_GENDER.name(),veutFemme);
        teenager12.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager13.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager13.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager13.addCriterion(CriterionName.HOBBIES.name(),sport);
        teenager13.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodVege);
        teenager13.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodNonuts);
        teenager13.addCriterion(CriterionName.GENDER.name(),homme);
        teenager13.addCriterion(CriterionName.PAIR_GENDER.name(),veutHomme);
        teenager13.addCriterion(CriterionName.HISTORY.name(),historySame);

        teenager14.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estAlergique);
        teenager14.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager14.addCriterion(CriterionName.HOBBIES.name(),technologie);
        teenager14.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager14.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodNonuts);
        teenager14.addCriterion(CriterionName.GENDER.name(),homme);
        teenager14.addCriterion(CriterionName.PAIR_GENDER.name(),veutHomme);
        teenager14.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager15.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager15.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aUnAnimal);
        teenager15.addCriterion(CriterionName.HOBBIES.name(),science);
        teenager15.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager15.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);
        teenager15.addCriterion(CriterionName.GENDER.name(),homme);
        teenager15.addCriterion(CriterionName.PAIR_GENDER.name(),veutvidePair);
        teenager15.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager16.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager16.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager16.addCriterion(CriterionName.HOBBIES.name(),reading);
        teenager16.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodNonuts);
        teenager16.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVege);
        teenager16.addCriterion(CriterionName.GENDER.name(),homme);
        teenager16.addCriterion(CriterionName.PAIR_GENDER.name(),veutvidePair);
        teenager16.addCriterion(CriterionName.HISTORY.name(),historyOther);


        history.affectations(teenager1, teenager2);
        history.affectations(teenager3, teenager4);
        history.affectations(teenager5, teenager6);
        history.affectations(teenager7, teenager8);
        history.affectations(teenager9, teenager10);
        history.affectations(teenager11, teenager12);
        history.affectations(teenager13, teenager14);
        history.affectations(teenager15, teenager16);



        GrapheNonOrienteValue<Teenager> graphFRChezIT = new GrapheNonOrienteValue<Teenager>();
        GrapheNonOrienteValue<Teenager> graphITChezFR = new GrapheNonOrienteValue<Teenager>();
        
        List<Teenager> fr = new ArrayList<Teenager>();
        List<Teenager> it = new ArrayList<Teenager>();

        fr.add(teenager1);
        fr.add(teenager4);
        fr.add(teenager5);
        fr.add(teenager6);
        fr.add(teenager7);
        fr.add(teenager12);
        fr.add(teenager13);
        fr.add(teenager14);

        it.add(teenager2);
        it.add(teenager3);
        it.add(teenager8);
        it.add(teenager9);
        it.add(teenager10);
        it.add(teenager11);
        it.add(teenager15);
        it.add(teenager16);

        for (Teenager teenager : fr) {
            graphFRChezIT.ajouterSommet(teenager);
            graphITChezFR.ajouterSommet(teenager);
        }

        for (Teenager teenager : it) {
            graphFRChezIT.ajouterSommet(teenager);
            graphITChezFR.ajouterSommet(teenager);
        }

        for (Teenager t1 : fr) {
            for (Teenager t2 : it) {
                graphFRChezIT.ajouterArete(t1, t2, AffectationUtil.weight(t1, t2, history));
            }
        }

        for (Teenager t1 : it) {
            for (Teenager t2 : fr) {
                graphITChezFR.ajouterArete(t2, t1, AffectationUtil.weight(t1, t2, history));
            }
        }



        CalculAffectation<Teenager> calculfr = new CalculAffectation<Teenager>(graphFRChezIT, fr, it);
        List<Arete<Teenager>> list = calculfr.calculerAffectation();
        System.out.println("Affectation FR chez IT :");
        System.out.println(list);
        System.out.println(calculfr.getCout());


        CalculAffectation<Teenager> calculit = new CalculAffectation<Teenager>(graphITChezFR, it, fr);
        List<Arete<Teenager>> list2 = calculit.calculerAffectation();
        System.out.println("Affectation IT chez FR :");
        System.out.println(list2);
        System.out.println(calculit.getCout());
    }
}
