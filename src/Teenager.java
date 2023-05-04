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

        return compatibleFood(guest);
           
    }

    public boolean compatibleAnimal(Teenager guest){
        if (guest == null) return false;
        Criterion animalRequirement = requirements.get(CriterionName.HOST_HAS_ANIMAL.name());
        Criterion guestAnimalRequirement = guest.getRequirements().get(CriterionName.GUEST_ANIMAL_ALLERGY.name());
        if (animalRequirement.getvalue().equals("yes") && guestAnimalRequirement.getvalue().equals("yes")) {
            return false; // Incompatible car l'invité a une allergie et l'hote a un animal.
        } 
        return true;
        /*code innutile mais je garde au cas ou...
        Criterion animalRequirement = requirements.get(CriterionName.GUEST_ANIMAL_ALLERGY.name());
        if (animalRequirement == null) {
            return true; // Si aucun critére de compatibilité animal n'existe, on suppose qu'ils sont compatible.
        }
    
        Criterion guestAnimalRequirement = guest.getRequirements().get(CriterionName.HOST_HAS_ANIMAL.name());
        if (guestAnimalRequirement == null) {
            return true; // Si l'invité n'a pas de critére de compatibilité animal, on suppose qu'ils sont compatible.
        }*/
    }
    
    public boolean compatibleFood(Teenager guest) {
        if (guest == null) return false;
    
        Criterion foodRequirement = this.requirements.get(CriterionName.HOST_FOOD.name());
        Criterion guestFoodRequirement = guest.getRequirements().get(CriterionName.GUEST_FOOD.name());
    
        // Check if both food requirements are not null before comparing their values
        if (foodRequirement != null && guestFoodRequirement != null) {
            if (foodRequirement.getvalue().equals(guestFoodRequirement.getvalue())) {
                return true; // Compatible because the host has the same food preference as the guest.
            }
        } else if (foodRequirement == null || guestFoodRequirement == null) {
            return true; // If either food requirement is missing, assume compatibility.
        }
        return false;
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