import java.util.FormatFlagsConversionMismatchException;

class Teenager extends Criterion{

    private int id;
    private String name;
    private String forname; 
    private String gender;
    private String countryName;
    private Date birthDate;
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

    public Criterion[] getRequirements() {
        return requirements;
    }
}