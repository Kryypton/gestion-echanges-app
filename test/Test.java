import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Criterion criterion1 = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion criterion2 = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion criterion3 = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion criterion4 = new Criterion("yes", CriterionName.HOST_FOOD);

        Map<String, Criterion> requirements1 = new HashMap<>();
        requirements1.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), criterion1);
        requirements1.put(CriterionName.HOST_HAS_ANIMAL.name(), criterion3);

        Map<String, Criterion> requirements2 = new HashMap<>();
        requirements2.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), criterion2);
        requirements2.put(CriterionName.HOST_FOOD.name(), criterion4);

        Teenager teenager1 = new Teenager(1, "Alice", "Martin", "F", LocalDate.of(2005, 6, 20), requirements1);
        Teenager teenager2 = new Teenager(2, "Bob", "Smith", "M", LocalDate.of(2005, 1, 15), requirements2);

        System.out.println("Teenager 1 compatible with Teenager 2: " + teenager1.compatibleWithGuest(teenager2));
        System.out.println("Teenager 2 compatible with Teenager 1: " + teenager2.compatibleWithGuest(teenager1));

        teenager1.purgeInvalidRequirement();
        teenager2.purgeInvalidRequirement();
        
        System.out.println("Purged requirements for Teenager 1: " + teenager1.getRequirements());
        System.out.println("Purged requirements for Teenager 2: " + teenager2.getRequirements());
    }
}
