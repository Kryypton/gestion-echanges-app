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

        
        Map<String, Criterion> requirements1 = new HashMap<>();
        requirements1.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        requirements1.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        requirements1.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        requirements1.put(CriterionName.GUEST_FOOD.name(), mangeTout);

        Map<String, Criterion> requirements2 = new HashMap<>();
        requirements2.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        requirements2.put(CriterionName.HOST_HAS_ANIMAL.name(), aUnAnimal);
        requirements2.put(CriterionName.HOST_FOOD.name(), possedeVege);
        requirements2.put(CriterionName.GUEST_FOOD.name(), mangeVege);

        Map<String, Criterion> requirements3 = new HashMap<>();
        requirements3.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estAlergique);
        requirements3.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        requirements3.put(CriterionName.HOST_FOOD.name(), possedeSport);
        requirements3.put(CriterionName.GUEST_FOOD.name(), mangeSport);

        Map<String, Criterion> requirements4 = new HashMap<>();
        requirements4.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        requirements4.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        requirements4.put(CriterionName.HOST_FOOD.name(), posseDeNonuts);
        requirements4.put(CriterionName.GUEST_FOOD.name(), mangeNotnuts);

        Map<String, Criterion> requirements5 = new HashMap<>();
        requirements5.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estPasAlergique);
        requirements5.put(CriterionName.HOST_HAS_ANIMAL.name(), aUnAnimal);
        requirements5.put(CriterionName.HOST_FOOD.name(), posseDeTout);
        requirements5.put(CriterionName.GUEST_FOOD.name(), mangeTout);

        Map<String, Criterion> requirements6 = new HashMap<>();
        requirements6.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), estAlergique);
        requirements6.put(CriterionName.HOST_HAS_ANIMAL.name(), aPasAnimal);
        requirements6.put(CriterionName.HOST_FOOD.name(), posseDeNonuts);
        requirements6.put(CriterionName.GUEST_FOOD.name(), mangeNotnuts);

        Teenager teenager1 = new Teenager(1, "Alice", "Martin", "F", LocalDate.of(2005, 6, 20), Country.FRANCE , requirements1);
        Teenager teenager2 = new Teenager(2, "Bob", "Smith", "M", LocalDate.of(2005, 1, 15), Country.FRANCE,  requirements2);
        Teenager teenager3 = new Teenager(3, "Charlie", "Brown", "M", LocalDate.of(2005, 8, 3), Country.ALLEMAGNE , requirements3);
        Teenager teenager4 = new Teenager(4, "Diane", "Garcia", "F", LocalDate.of(2005, 2, 10), Country.ITALIE ,  requirements4);
        Teenager teenager5 = new Teenager(5, "Eve", "Wilson", "F", LocalDate.of(2005, 4, 30), Country.ALLEMAGNE , requirements5);
        Teenager teenager6 = new Teenager(6, "Frank", "Anderson", "M", LocalDate.of(2005, 7, 25), Country.ESPAGNE,requirements6);
        Teenager teenager7 = new Teenager(7, "Grace", "Taylor", "F", LocalDate.of(2005, 3, 5), Country.FRANCE, requirements1);
        Teenager teenager8 = new Teenager(8, "Henry", "Thomas", "M", LocalDate.of(2005, 9, 1), Country.ITALIE,requirements5); 

        Platform platform = new Platform();
        platform.addTeenager(teenager1);
        platform.addTeenager(teenager2);
        platform.addTeenager(teenager3);
        platform.addTeenager(teenager4);
        platform.addTeenager(teenager5);
        platform.addTeenager(teenager6);
        platform.addTeenager(teenager7);
        platform.addTeenager(teenager8);
        platform.purgeInvalidRequirements();
        platform.findCompatibleTeenagers();
        platform.printCompatibleTeenagers();

    }
}
