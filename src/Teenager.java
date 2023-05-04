import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.HashMap;

public class Teenager{

    private int id;
    private String name;
    private String forname; 
    private String gender;
    private String countryName;
    private LocalDate birthDate;
    private Map<String, Criterion> requirements;


    public Teenager(int id , String name , String forname , String gender , LocalDate birthDate , Map<String, Criterion> requirements){
        this.id = id ;
        this.name = name;
        this.forname = forname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.requirements = requirements;
    }

    /* Regarde si un hote est compatible avec un invité donné */
    public boolean compatibleWithGuest(Teenager guest){
        if (guest == null) {
            return false;
        }

        for (Criterion requirement : requirements.values()) {
            Criterion guestCriterion = guest.getRequirements().get(requirement.getLabel().name());
            if (guestCriterion != null && !requirement.equals(guestCriterion)) {
                return false;
            }
        }
        return true;
    }

    /* Supprime les critéres invalide de la Map requirements */
    public void purgeInvalidRequirement(){
        Map<String, Criterion> validRequirements = new HashMap<>();

        for (Map.Entry<String, Criterion> entry : requirements.entrySet()) {
            if (entry.getValue().isValid()) {
                validRequirements.put(entry.getKey(), entry.getValue());
            }
        }

        requirements = validRequirements;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getForname() {
        return this.forname;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public String getGender(){
        return this.gender;
    }

    public Period getAge() {
        LocalDate dateNow = LocalDate.now();
        Period p = Period.between(this.birthDate, dateNow);
        return p;
    }

    public Criterion getCriterion(String criterionName) {
        return requirements.get(criterionName);
    }

    public Map<String, Criterion> getRequirements() {
        return requirements;
    }

}