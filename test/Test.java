import java.time.LocalDate;

public class Test {

    public static void main(String[] args) {
        Criterion criterion1 = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion criterion2 = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        Criterion criterion3 = new Criterion("vegetarien", CriterionName.GUEST_FOOD);
        Criterion criterion4 = new Criterion("1987-06-15", CriterionName.HISTORY);
        System.out.println("isValid crit1 " + criterion1.isValid()); // true
        System.out.println("isValid crit2: " + criterion2.isValid()); // true
        System.out.println("isValid cri3: " + criterion3.isValid()); // true
        System.out.println("isValid crit4 " + criterion4.isValid()); // true
        LocalDate birthDate1 = LocalDate.of(2005, 3, 12);
        LocalDate birthDate2 = LocalDate.of(2004, 7, 25);

        Criterion[] requirements1 = {criterion1, criterion3, criterion4};
        Criterion[] requirements2 = {criterion2, criterion3};

        Teenager teenager1 = new Teenager(1, "test1", "nomtest1", "M", birthDate1, requirements1);
        Teenager teenager2 = new Teenager(2, "test2", "nomtest2", "F", birthDate2, requirements2);

        System.out.println("T1 compatible avecT2: " + teenager1.compatibleWithGuest(teenager2));

        teenager1.purgeInvalidRequirement();
        teenager2.purgeInvalidRequirement();

        System.out.println("T1 requirements après purge: -> ");
        for (Criterion requirement : teenager1.getRequirements()) {
            System.out.println(requirement.getLabel());
        }

        System.out.println("T2 requirements après purge -> ");
        for (Criterion requirement : teenager2.getRequirements()) {
            System.out.println(requirement.getLabel());
        }
    }
}
