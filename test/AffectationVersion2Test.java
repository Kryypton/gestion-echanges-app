import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;


import org.junit.jupiter.api.Test;

import Criterion.Country;
import Criterion.Criterion;
import Criterion.CriterionName;
import Tennager.Teenager;
import graph.Affectation;
import graph.AffectationUtil;

import org.junit.jupiter.api.BeforeEach;



public class AffectationVersion2Test {
    Teenager teenager1 , teenager2 , teenager3 , teenager4 , teenager5 , teenager6 , teenager7 , teenager8 , teenager9 , teenager10 , teenager11 , teenager12 , teenager13 , teenager14 , teenager15, teenager16; 
    Affectation history = new Affectation();
    Affectation history2 = new Affectation();

    @BeforeEach
    public void TestInitialisation() {

        // Premiere version de l'exemple pour l'historique
        
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion historySame = new Criterion("same", CriterionName.HISTORY);
        Criterion historyOther = new Criterion("other", CriterionName.HISTORY);
        //Criterion historyVide = new Criterion(null, CriterionName.HISTORY);

        
        teenager1 = new Teenager("Adham" , "A"  , LocalDate.parse("2004-10-08") , Country.FRANCE);
        teenager2 = new Teenager("Bertrand" , "B" , LocalDate.parse("2004-02-12") , Country.FRANCE);
        teenager3 = new Teenager("Claudette" , "C" ,  LocalDate.parse("2004-12-30") , Country.FRANCE);
        teenager4 = new Teenager("Damiene" , "D" ,  LocalDate.parse("2004-09-23") , Country.FRANCE);

        teenager5 = new Teenager("Emile" , "E" ,  LocalDate.parse("2004-04-18") , Country.ITALY);     
        teenager6 = new Teenager("Fabienne" , "F" ,  LocalDate.parse("2004-11-29") , Country.ITALY);        
        teenager7 = new Teenager("Gerard" , "G" ,  LocalDate.parse("2005-08-05") , Country.ITALY);   
        teenager8 = new Teenager("Harvard" , "H" ,LocalDate.parse("2006-02-08") , Country.ITALY);    




        teenager1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager1.addCriterion(CriterionName.HISTORY.name(),historySame);

        teenager2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager2.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        //teenager3.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager4.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager4.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager5.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager5.addCriterion(CriterionName.HISTORY.name(),historyOther);

        teenager6.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager6.addCriterion(CriterionName.HISTORY.name(),historySame);

        teenager7.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        //teenager7.addCriterion(CriterionName.HISTORY.name(),historyVide);

        teenager8.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager8.addCriterion(CriterionName.HISTORY.name(),historySame);

        history.affectations(teenager1, teenager8);
        history.affectations(teenager2, teenager4);
        history.affectations(teenager3, teenager7);
        history.affectations(teenager4, teenager8);



        // Deuxieme version de l'exemple pour l'historique avec les parametre en plus demandés

        Criterion sport = new Criterion("sports", CriterionName.HOBBIES);
        Criterion technologie = new Criterion("technology", CriterionName.HOBBIES);
        Criterion science = new Criterion("science", CriterionName.HOBBIES);
        Criterion reading = new Criterion("reading", CriterionName.HOBBIES);
        Criterion musicEtScience = new Criterion("music,science", CriterionName.HOBBIES);
        Criterion sportsEtTechnologie = new Criterion("sports,technology", CriterionName.HOBBIES);
        Criterion msuciEtReading = new Criterion("music,reading", CriterionName.HOBBIES);



        teenager9 = new Teenager("Adham" , "A" ,  LocalDate.parse("2004-10-08") , Country.FRANCE);
        teenager10 = new Teenager("Bertrand" , "B" ,  LocalDate.parse("2004-02-12") , Country.FRANCE);
        teenager11 = new Teenager("Claudette" , "C" ,  LocalDate.parse("2004-12-30") , Country.FRANCE);
        teenager12 = new Teenager("Damiene" , "D" ,  LocalDate.parse("2004-09-23") , Country.FRANCE);

        teenager13 = new Teenager("Emile" , "E" ,  LocalDate.parse("2004-04-18") , Country.ITALY);     
        teenager14 = new Teenager("Fabienne" , "F" ,  LocalDate.parse("2004-11-29") , Country.ITALY);        
        teenager15 = new Teenager("Gerard" , "G" ,  LocalDate.parse("2004-08-05") , Country.ITALY);   
        teenager16 = new Teenager("Harvard" , "H" ,  LocalDate.parse("2004-02-08") , Country.ITALY);    



        teenager9.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager9.addCriterion(CriterionName.HISTORY.name(),historySame);
        teenager9.addCriterion(CriterionName.HOBBIES.name(),sport);

        teenager10.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager10.addCriterion(CriterionName.HISTORY.name(),historyOther);
        teenager10.addCriterion(CriterionName.HOBBIES.name(),technologie);

        teenager11.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        // teenager11.addCriterion(CriterionName.HISTORY.name(),historyVide);
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
        // teenager15.addCriterion(CriterionName.HISTORY.name(),historyVide);
        teenager15.addCriterion(CriterionName.HOBBIES.name(),sportsEtTechnologie);

        teenager16.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager16.addCriterion(CriterionName.HISTORY.name(),historySame);
        teenager16.addCriterion(CriterionName.HOBBIES.name(),msuciEtReading);


        history2.affectations(teenager9, teenager13);
        history2.affectations(teenager10, teenager14);
        history2.affectations(teenager11, teenager15);
        history2.affectations(teenager12, teenager16);


    }


    @Test
    public void testExemple1(){
        assertEquals(1965 , AffectationUtil.weight(teenager1, teenager5, history));
        assertEquals(1965 , AffectationUtil.weight(teenager2, teenager6, history));
        assertEquals(1865 , AffectationUtil.weight(teenager3, teenager7, history));
        assertEquals(1965 , AffectationUtil.weight(teenager4, teenager7, history));


    }



    @Test
    public void testExemple2(){
        assertEquals(1915 , AffectationUtil.weight(teenager9, teenager13, history2));
        assertEquals(1915 , AffectationUtil.weight(teenager10, teenager14, history2));
        assertEquals(1865 , AffectationUtil.weight(teenager11, teenager15, history2));
        assertEquals(914 , AffectationUtil.weight(teenager12, teenager16, history2));



    }

    @Test
    public void testWeigthAdvanced1(){
        assertEquals(1065 , AffectationUtil.weightAdvanced(teenager1, teenager5, history)); 
        assertEquals(1065 , AffectationUtil.weightAdvanced(teenager2, teenager6, history));
        assertEquals(965 , AffectationUtil.weightAdvanced(teenager3, teenager7, history));
        assertEquals(1065 , AffectationUtil.weightAdvanced(teenager4, teenager7, history));
        assertEquals(1065 , AffectationUtil.weightAdvanced(teenager1, teenager6, history));
        assertEquals(1065 , AffectationUtil.weightAdvanced(teenager2, teenager5, history));
        assertEquals(965 , AffectationUtil.weightAdvanced(teenager3, teenager6, history));
    }

    @Test
    public void testWeigthAdvanced2(){
        assertEquals(1015 , AffectationUtil.weightAdvanced(teenager9, teenager13, history2));
        assertEquals(1015 , AffectationUtil.weightAdvanced(teenager10, teenager14, history2));
        assertEquals(965 , AffectationUtil.weightAdvanced(teenager11, teenager15, history2));
        assertEquals(914 , AffectationUtil.weightAdvanced(teenager12, teenager16, history2));
        assertEquals(964 , AffectationUtil.weightAdvanced(teenager9, teenager14, history2));
        assertEquals(1065 , AffectationUtil.weightAdvanced(teenager10, teenager13, history2));
        assertEquals(965 , AffectationUtil.weightAdvanced(teenager11, teenager14, history2));
    }
}