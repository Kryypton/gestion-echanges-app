import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Criterion.Country;
import Criterion.Criterion;
import Criterion.CriterionName;
import Tennager.Teenager;
import graph.AffectationUtil;

public class AffectationVersion1Test {

    Teenager teenager1 , teenager2 , teenager3 , teenager4 , teenager5 , teenager6 , teenager7 , teenager8 , teenager9 , teenager10;


    @BeforeEach
    public void TestInitialization()    { 

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


        teenager1 = new Teenager("A", "Adonia" , Country.FRANCE);
        teenager2 = new Teenager("B", "Bellatrix" , Country.FRANCE);
        teenager3 = new Teenager("C", "Callista" , Country.FRANCE);
        teenager4 = new Teenager("X", "Xolag" , Country.ITALY);
        teenager5 = new Teenager("Y", "Yak" , Country.ITALY);
        teenager6 = new Teenager("Z", "Zander" , Country.ITALY);

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




        // Voici l'exemple 2 du sujet qui implemente IncompatibilityVsHobbies

        Criterion hobbiesDeA = new Criterion("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx", CriterionName.HOBBIES);
        Criterion hobbiesDeD = new Criterion("a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg,hh,ii,jj,kk,ll,mm,nn,oo,pp,qq,rr,ss,tt,uu,vv,ww,xx", CriterionName.HOBBIES);


        teenager7 = new Teenager("A", "A" ,LocalDate.parse("2009-06-01"), Country.ITALY);
        teenager8 = new Teenager("B", "B" ,LocalDate.parse("2009-06-01"), Country.ITALY);
        teenager9 = new Teenager("C", "C" , LocalDate.parse("2009-06-01"),Country.GERMANY);
        teenager10 = new Teenager("D", "D" , LocalDate.parse("2009-06-01"), Country.GERMANY);

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

    }


    @Test
    public void testWeightVersion1() {
        assertEquals(200 , AffectationUtil.weightVersion1(teenager1,teenager2));
        assertEquals(99 , AffectationUtil.weightVersion1(teenager1,teenager4));
        assertEquals(99 , AffectationUtil.weightVersion1(teenager2,teenager5));
        assertEquals(200 , AffectationUtil.weightVersion1(teenager3,teenager6));
        assertEquals(100 , AffectationUtil.weightVersion1(teenager4,teenager5));
        assertEquals(99 , AffectationUtil.weightVersion1(teenager4,teenager6));
        assertEquals(100 , AffectationUtil.weightVersion1(teenager5,teenager6));
    }



    @Test
    public void testCompatibilityVsHobbies() {
        assertEquals(100, AffectationUtil.weightVersion1(teenager7,teenager8));
        assertEquals(100 , AffectationUtil.weightVersion1(teenager7,teenager9));
        assertEquals(50 , AffectationUtil.weightVersion1(teenager7,teenager10));
        assertEquals(100 , AffectationUtil.weightVersion1(teenager8,teenager9));
        assertEquals(100 , AffectationUtil.weightVersion1(teenager8,teenager10));
        assertEquals(100 , AffectationUtil.weightVersion1(teenager9,teenager10));
    }


}