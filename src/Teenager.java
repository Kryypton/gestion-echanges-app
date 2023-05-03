import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
//import java.util.FormatFlagsConversionMismatchException;

public class Teenager{

    private int id;
    private String name;
    private String forname; 
    private String gender;
    private String countryName;
    private LocalDate birthDate;
    private Criterion[] requirements;


    public Teenager(int id , String name , String forname , String gender , LocalDate birthDate , Criterion[] requirements){
        this.id = id ;
        this.name = name;
        this.forname = forname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.requirements = requirements;
    }

    public boolean compatibleWithGuest(Teenager guest){
        if (guest == null) {
            return false;
        }

        for (Criterion requirement : requirements) {
            for (Criterion guestCriterion : guest.getRequirements()) {
                if (requirement.getLabel().equals(guestCriterion.getLabel()) && !requirement.equals(guestCriterion)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void purgeInvalidRequirement(){
        List<Criterion> validRequirements = new ArrayList<Criterion>();

        for (Criterion requirement : requirements) {
            if (requirement.isValid()) {
                validRequirements.add(requirement);
            }
        }

        requirements = validRequirements.toArray(new Criterion[validRequirements.size()]);
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

    public Criterion[] getRequirements() {
        return requirements;
    }
}