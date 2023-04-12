import java.time.LocalDate;
import java.time.Period;
import java.util.FormatFlagsConversionMismatchException;

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

    }

    public void purgeInvalidRequirement(){

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