import java.util.ArrayList;
import java.util.List;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class AffectationVersion1Test {


        public static void main(String[] args) {
                


        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);

        Criterion sportEtTechnologie = new Criterion("sports,technology", CriterionName.HOBBIES);
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


        }



}