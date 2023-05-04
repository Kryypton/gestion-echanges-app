import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);

        Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);


        Criterion possedeVege = new Criterion("végétarien", CriterionName.HOST_FOOD);
        Criterion possedeSport = new Criterion("sport", CriterionName.HOST_FOOD);
        Criterion posseDeNonuts = new Criterion("nonuts", CriterionName.HOST_FOOD);
        Criterion posseDeTout = null;

        Criterion mangeVege = new Criterion("végétarien", CriterionName.GUEST_FOOD);
        Criterion mangeSport = new Criterion("sport", CriterionName.GUEST_FOOD);
        Criterion mangeNotnuts = new Criterion("nonuts", CriterionName.GUEST_FOOD);
        Criterion mangeTout = null;

        
        Map<String, Criterion> reqMrBouffeTout = new HashMap<>();
        reqMrBouffeTout.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        reqMrBouffeTout.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        reqMrBouffeTout.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        reqMrBouffeTout.put(CriterionName.GUEST_FOOD.name(), mangeTout);

        Map<String, Criterion> reqMrVert = new HashMap<>();
        reqMrVert.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        reqMrVert.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        reqMrVert.put(CriterionName.HOST_FOOD.name(), possedeVege);
        reqMrVert.put(CriterionName.GUEST_FOOD.name(), mangeVege);

        Map<String, Criterion> reqMrHulk = new HashMap<>();
        reqMrHulk.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estAlergique);
        reqMrHulk.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        reqMrHulk.put(CriterionName.HOST_FOOD.name(), possedeSport);
        reqMrHulk.put(CriterionName.GUEST_FOOD.name(), mangeSport);

        Map<String, Criterion> reqMrDiet = new HashMap<>();
        reqMrDiet.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        reqMrDiet.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        reqMrDiet.put(CriterionName.HOST_FOOD.name(), posseDeNonuts);
        reqMrDiet.put(CriterionName.GUEST_FOOD.name(), mangeNotnuts);

        Teenager teenager1 = new Teenager(1, "MrBouffeTout", "Martin", "F", LocalDate.of(2005, 6, 20), Country.FRANCE , reqMrBouffeTout);
        Teenager teenager2 = new Teenager(2, "MrVert", "Smith", "M", LocalDate.of(2005, 1, 15), Country.FRANCE,  reqMrVert);
        Teenager teenager3 = new Teenager(3, "MrHulk", "Brown", "M", LocalDate.of(2005, 8, 3), Country.ALLEMAGNE , reqMrHulk);
        Teenager teenager4 = new Teenager(4, "MrDiet", "Garcia", "F", LocalDate.of(2005, 2, 10), Country.ITALIE ,  reqMrDiet);

        Platform platform = new Platform();
        platform.addTeenager(teenager1);
        platform.addTeenager(teenager2);
        platform.addTeenager(teenager3);
        platform.addTeenager(teenager4);
        
        platform.purgeInvalidRequirements();
        platform.findCompatibleTeenagers();
        platform.printCompatibleTeenagers();

    }
}
