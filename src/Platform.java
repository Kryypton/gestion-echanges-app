import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;

public class Platform {

    private List<Teenager> teenagerList;
    private Map<Teenager, Teenager> compatibleTeenagers;

    public Platform() {
        this.teenagerList = new ArrayList<>();
        this.compatibleTeenagers = new HashMap<>();
    }

    public void addTeenager(Teenager teenager){
        teenagerList.add(teenager);
    }

    public void findCompatibleTeenagers(){
        for (Teenager teenager : teenagerList) {
            for (Teenager guest : teenagerList) {
                if (teenager.compatibleWithGuest(guest)) {
                    compatibleTeenagers.put(teenager, guest);
                }
            }
        }
    }

    public void purgeInvalidRequirements(){
        for (Teenager teenager : teenagerList) {
            teenager.purgeInvalidRequirement();
        }
    }

    public void printCompatibleTeenagers(){
        for (Map.Entry<Teenager, Teenager> entry : compatibleTeenagers.entrySet()) {
            System.out.println(entry.getKey().getName() + " compatible " + entry.getValue().getName());
        }
    }

    public static void main(String[] args) {

        
        Criterion criterion1 = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion criterion2 = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion criterion3 = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion criterion4 = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        Criterion criterion5 = new Criterion("végétarien", CriterionName.HOST_FOOD);
        Criterion criterion6 = new Criterion("sport", CriterionName.HOST_FOOD);
        Criterion criterion7 = new Criterion("nonuts", CriterionName.HOST_FOOD);
        Criterion criterion8 = new Criterion("yes", CriterionName.HOBBIES);
        
        Map<String, Criterion> requirements1 = new HashMap<>();
        requirements1.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), criterion1);
        requirements1.put(CriterionName.HOST_HAS_ANIMAL.name(), criterion3);
        requirements1.put(CriterionName.HOST_FOOD.name(), criterion5);
        requirements1.put(CriterionName.HOBBIES.name(), criterion8);

        Map<String, Criterion> requirements2 = new HashMap<>();
        requirements2.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), criterion2);
        requirements2.put(CriterionName.HOST_HAS_ANIMAL.name(), criterion4);
        requirements2.put(CriterionName.HOST_FOOD.name(), criterion6);
        requirements2.put(CriterionName.HOBBIES.name(), criterion8);

        Map<String, Criterion> requirements3 = new HashMap<>();
        requirements3.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), criterion2);
        requirements3.put(CriterionName.HOST_HAS_ANIMAL.name(), criterion4);
        requirements3.put(CriterionName.HOST_FOOD.name(), criterion7);
        requirements3.put(CriterionName.HOBBIES.name(), criterion8);

        Map<String, Criterion> requirements4 = new HashMap<>();
        requirements4.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), criterion1);
        requirements4.put(CriterionName.HOST_HAS_ANIMAL.name(), criterion3);
        requirements4.put(CriterionName.HOST_FOOD.name(), criterion5);
        requirements4.put(CriterionName.HOBBIES.name(), criterion8);

        Teenager teenager1 = new Teenager(1, "Alice", "Martin", "F", LocalDate.of(2005, 6, 20), requirements1);
        Teenager teenager2 = new Teenager(2, "Bob", "Smith", "M", LocalDate.of(2005, 1, 15), requirements2);
        Teenager teenager3 = new Teenager(3, "Charlie", "Brown", "M", LocalDate.of(2005, 8, 3), requirements3);
        Teenager teenager4 = new Teenager(4, "Diane", "Garcia", "F", LocalDate.of(2005, 2, 10), requirements4);
        Teenager teenager5 = new Teenager(5, "Eve", "Wilson", "F", LocalDate.of(2005, 4, 30), requirements1);
        Teenager teenager6 = new Teenager(6, "Frank", "Anderson", "M", LocalDate.of(2005, 7, 25), requirements2);
        Teenager teenager7 = new Teenager(7, "Grace", "Taylor", "F", LocalDate.of(2005, 3, 5), requirements3);
        Teenager teenager8 = new Teenager(8, "Henry", "Thomas", "M", LocalDate.of(2005, 9, 1), requirements4);



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