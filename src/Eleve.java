import java.time.LocalDate;

public class Eleve{
    
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

    public Eleve(Teenager teen){
        this.name = teen.getName();
        this.forname = teen.getForname();
        this.countryName = teen.getCountryName();
        this.birthDate = teen.getBirthDate();
        this.HostFood = teen.getCriterion("HOST_FOOD");
        this.GuestFood = teen.getCriterion("GUEST_FOOD");
        this.HostAnimal = teen.getCriterion("HOST_HAS_ANIMAL");
        this.GuestHanimal = teen.getCriterion("GUEST_ANIMAL_ALLERGY");
        this.Hobbies = teen.getCriterion("HOBBIES");
        this.PairGender = teen.getCriterion("GENDER");
        this.Gender = teen.getCriterion("PAIR_GENDER");
        this.History = teen.getCriterion("HISTORY");
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