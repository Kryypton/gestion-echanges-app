import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.HashMap;

public class Teenager{

    private int id;
    private String name;
    private String forname; 
    private String gender;
    private Country countryName;
    private LocalDate birthDate;
    private Map<String, Criterion> requirements;


    public Teenager(int id , String name , String forname , String gender , LocalDate birthDate , Country countryName, Map<String, Criterion> requirements){
        this.id = id ;
        this.name = name;
        this.forname = forname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.requirements = requirements;
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teenager other = (Teenager) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (forname == null) {
            if (other.forname != null)
                return false;
        } else if (!forname.equals(other.forname))
            return false;
        if (gender == null) {
            if (other.gender != null)
                return false;
        } else if (!gender.equals(other.gender))
            return false;
        if (countryName != other.countryName)
            return false;
        if (birthDate == null) {
            if (other.birthDate != null)
                return false;
        } else if (!birthDate.equals(other.birthDate))
            return false;
        if (requirements == null) {
            if (other.requirements != null)
                return false;
        } else if (!requirements.equals(other.requirements))
            return false;
        return true;
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

    public boolean compatibleAnimal(Teenager guest){
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
            if (entry.getValue()!=null){
                if (entry.getValue().isValid()) {
                    validRequirements.put(entry.getKey(), entry.getValue());
                }
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
        return this.countryName.getCOUNTRY_NAME();
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