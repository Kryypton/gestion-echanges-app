import java.time.LocalDate;
import java.time.Period;
import java.util.FormatFlagsConversionMismatchException;

class Teenager{

    private int id;
    private String name;
    private String forname; 
    private String gender;
    private String countryName;
    private LocalDate birthDate;
    private Criterion[] requirements;

    public boolean compatibleWithGuest(Teenager guest){}

    public void purgeInvalidRequirement(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getForname() {
        return forname;
    }

    public String getCountryName() {
        return countryName;
    }

    public Date getBirthDate() {
        return birthDate;
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