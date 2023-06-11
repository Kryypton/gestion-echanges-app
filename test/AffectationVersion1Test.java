import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class AffectationVersion1Test {

    @BeforeEach
    void initialization() {


        
    }
        public static void main(String[] args) {
                

        // Voici l'exemple 1 du sujet implemente

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

        System.out.println("\n");
        System.out.println("\n");



        // Voici l'exemple 2 du sujet qui implemente IncompatibilityVsHobbies

        Criterion hobbiesDeA = new Criterion("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx", CriterionName.HOBBIES);
        Criterion hobbiesDeD = new Criterion("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx", CriterionName.HOBBIES);


        Teenager teenager7 = new Teenager("A", "A" , "female",LocalDate.parse("2009-06-01"), Country.ITALY);
        Teenager teenager8 = new Teenager("B", "B" ,"male",LocalDate.parse("2009-06-01"), Country.ITALY);
        Teenager teenager9 = new Teenager("C", "C" , "female",LocalDate.parse("2009-06-01"),Country.GERMANY);
        Teenager teenager10 = new Teenager("D", "D" ,"male",LocalDate.parse("2009-06-01"), Country.GERMANY);

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

        CalculAffectation<Teenager> calculIT = new CalculAffectation<Teenager>(graphITChezALL, italiensList, allemandList);
        List<Arete<Teenager>> listIT = calculIT.calculerAffectation();
        System.out.println("Affectation italiens chez les Allemands :");
        System.out.println(listIT);
        System.out.println(calculIT.getCout());

        System.out.println("\n");
    }
}