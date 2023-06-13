import java.time.LocalDate;

public class Eleve extends Teenager{
    
    private String name;
    private String forname;
    private Country countryName;
    private LocalDate birthDate;
    private Criterion HostFood;
    private Criterion GuestFood;
    private Criterion HostAnimal;
    private Criterion GuestHanimal;
    private Criterion Hobbies;
    private Criterion PairGender;
    private Criterion Gender;
    private Criterion History;

    public Eleve(){
        super();
        
    }

    public String getName() {
        return name;
    }

    public String getForname() {
        return forname;
    }

    public Country getCountryName() {
        return countryName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Criterion getHostFood() {
        return HostFood;
    }

    public Criterion getGuestFood() {
        return GuestFood;
    }

    public Criterion getHostAnimal() {
        return HostAnimal;
    }

    public Criterion getGuestHanimal() {
        return GuestHanimal;
    }

    public Criterion getHobbies() {
        return Hobbies;
    }

    public Criterion getPairGender() {
        return PairGender;
    }

    public Criterion getGender() {
        return Gender;
    }

    public Criterion getHistory() {
        return History;
    }
}