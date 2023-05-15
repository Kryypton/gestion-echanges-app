import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Cette classe permet de gérer les informations relatives à un adolescent et de déterminer sa compatibilité avec d'autres adolescents en fonction de différents critères.
 * @since 1.0
 * @version 1.0
 * @author Dorny Nathan
 * @author Berrakane Adham
 * @author Moutté Quentin
 */
public class Teenager{
    
    private int id;
    private String name;
    private String forname; 
    private String gender;
    private Country countryName;
    private LocalDate birthDate;
    private Map<String, Criterion> requirements;

    /**
     * Constructeur de la classe Teenager avec le paramètre (requirements) qui est un Map de (Criterion) Critères
     * @param id l'identifiant unique d'un adolescent
     * @param name le nom d'un adolescent
     * @param forname le prénom d'un adolescent
     * @param gender le genre d'un adolescent
     * @param birthDate la date de naissance d'un adolescent
     * @param countryName le pays d'un adolescent
     * @param requirements les critères de compatibilité d'un adolescent
     */
    public Teenager(int id , String name , String forname , String gender , LocalDate birthDate , Country countryName, Map<String, Criterion> requirements){
        this.id = id ;
        this.name = name;
        this.forname = forname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.requirements = requirements;
        this.countryName = countryName;
    }

    /**
     * Constructeur de la classe Teenager sans le paramètre (requirements) qui est un Map de (Criterion) Critères
     * @param id l'identifiant unique d'un adolescent
     * @param name le nom d'un adolescent
     * @param forname le prénom d'un adolescent
     * @param gender le genre d'un adolescent
     * @param birthDate la date de naissance d'un adolescent
     * @param countryName le pays d'un adolescent
     */
    public Teenager(int id , String name , String forname , String gender , LocalDate birthDate , Country countryName){
        this.id = id ;
        this.name = name;
        this.forname = forname;
        this.gender = gender;
        this.birthDate = birthDate;
        this.requirements = new HashMap<String, Criterion>();
        this.countryName = countryName;
    }

    /**
     * Méthode qui permet de savoir si un adolescent est compatible avec un autre adolescent en terme de critères animal
     * @param guest l'invité à comparer avec l'adolescent courant
     * @return [true] si l'adolescent est compatible avec l'invité, [false] sinon
     */
    public boolean compatibleAnimal(Teenager guest){
        if (guest == null) return false;
        Criterion animalRequirement = requirements.get(CriterionName.HOST_HAS_ANIMAL.name());
        Criterion guestAnimalRequirement = guest.getRequirements().get(CriterionName.GUEST_ANIMAL_ALLERGY.name());
        if (animalRequirement.getValue().equals("yes") && guestAnimalRequirement.getValue().equals("yes")) {
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
    
    /**
     * Méthode qui permet de savoir si un adolescent est compatible avec un autre adolescent en terme de critères nourriture
     * @param guest l'invité à comparer avec l'adolescent courant
     * @return [true] si l'adolescent est compatible avec l'invité, [false] sinon
     */
    public boolean compatibleFood(Teenager guest) {
        Criterion hostFood = this.getRequirements().get(CriterionName.HOST_FOOD.name());
        Criterion guestFood = guest.getRequirements().get(CriterionName.GUEST_FOOD.name());
    
        boolean hostNone = hostFood.getValue().equals("none");
        boolean guestNone = guestFood.getValue().equals("none");

        if (hostNone && guestNone) return true;
        if (hostNone || guestNone) return true;
        if (hostFood.getValue().equals(guestFood.getValue())) return true;
        return false;
    }
    
    /**
     * Méthode qui permet de savoir si un adolescent est compatible avec un autre adolescent combinant tout les critères
     * @param guest l'invité à comparer avec l'adolescent courant
     * @return [true] si l'adolescent est compatible avec l'invité, [false] sinon
     */
    public boolean compatibleWithGuest(Teenager guest) {
        if (guest == null) return false;
        if (compatibleAnimal(guest) && compatibleFood(guest)){
            return true;
        } else if(this.countryName == Country.FRANCE || guest.getCountryName() == Country.FRANCE){
                if(this.nbLoisirCommun(guest) == 0){
            return false;
            }
        }
        return true;
    }

    /**
     * Méthode qui permet de connaitre l'age d'un adolescent de type Period
     * @return l'age d'un adolescent
     */
    public Period getAge() {
        LocalDate dateNow = LocalDate.now();
        Period p = Period.between(this.birthDate, dateNow);
        return p;
    }

    /**
     * Méthode qui permet de connaitre l'age d'un adolescent en année
     * @return l'age d'un adolescent en année
     */
    public int getAgeYear(){
        return getAge().getYears();
    }
    /**
     * retire les critères invalides de la liste des critères
     */
    public void purgeInvalidRequirement(){
        Map<String, Criterion> validRequirements = new HashMap<>();

        for (Map.Entry<String, Criterion> entry : requirements.entrySet()) {
            if (entry.getValue()!=null){
                if (entry.getValue().isValid()) {
                    validRequirements.put(entry.getKey(), entry.getValue());
                }
            }
        }
        this.requirements = validRequirements;
    }



    /**
    * Renvoie le nombre de loisirs communs entre les 2 Teenager.
    * @param teen un Teenager
    * @return int nombre de loisirs communs
    */

    public int nbLoisirCommun(Teenager teen){
        if(!teen.requirements.containsKey("HOBBIES") || !this.requirements.containsKey("HOBBIES")){
            return 0;
        }

        ArrayList<String> ask = new ArrayList<>();
        ArrayList<String> give = new ArrayList<>();

        if(!teen.requirements.get("HOBBIES").equals("")){
            for (String s : teen.requirements.get("HOBBIES").getValue().split(",")) {
            ask.add(s);
            }
        }
        if(!this.requirements.get("HOBBIES").equals("")){
            for (String s : this.requirements.get("HOBBIES").getValue().split(",")) {
                give.add(s);
            }
        }
        int nombreLoisirs = 0;
        for (String s : ask) {
            if (give.indexOf(s) >= 0) {
            nombreLoisirs = nombreLoisirs ++ ;
            }
        }
        return nombreLoisirs;
    }
    

    /**
     * Getter qui permet retourne l'identifiant d'un adolescent
     * @return l'identifiant d'un adolescent
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter qui permet retourne le nom d'un adolescent
     * @return le nom d'un adolescent
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter qui permet retourne le prénom d'un adolescent
     * @return le prénom d'un adolescent
     */
    public String getForname() {
        return this.forname;
    }

    /**
     * Getter qui permet retourne le pays d'un adolescent
     * @return le pays d'un adolescent
     */
    public String getCountryNameString() {
        return this.countryName.getCOUNTRY_NAME();
    }

    public Country getCountryName(){
        return this.countryName;
    }
    /**
     * Getter qui permet retourne la date de naissance d'un adolescent
     * @return la date de naissance d'un adolescent
     */
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    /**
     * Getter qui permet retourne le genre d'un adolescent
     * @return le genre de l'adolescent [M] pour male [F] pour femme [O] pour autre
     */
    public String getGender(){
        return this.gender;
    }

    /**
     * Setter qui permet de modifier l'identifiant d'un adolescent
     * @param id le nouvel identifiant de l'adolescent
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter qui permet de modifier le nom d'un adolescent
     * @param name le nouveau nom de l'adolescent
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter qui permet de modifier le prénom d'un adolescent
     * @param forname le nouveau prénom de l'adolescent
     */
    public void setForname(String forname) {
        this.forname = forname;
    }

    /**
     * Setter qui permet de modifier le genre d'un adolescent
     * @param gender le nouveau genre de l'adolescent
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Setter qui permet de modifier le pays d'un adolescent
     * @param countryName le nouveau pays de l'adolescent
     */
    public void setCountryName(Country countryName) {
        this.countryName = countryName;
    }

    /**
     * Setter qui permet de modifier la date de naissance d'un adolescent
     * @param birthDate la nouvelle date de naissance de l'adolescent
     */
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Setter qui permet de modifier les critères d'un adolescent
     * @param requirements les nouveaux critères de l'adolescent
     */
    public void setRequirements(Map<String, Criterion> requirements) {
        this.requirements = requirements;
    }

    /**
     * Méthode qui permet de retourner un critère d'un adolescent
     * @param criterionName le nom du critère à retourner
     * @return le critère de l'adolescent
     */
    public Criterion getCriterion(String criterionName) {
        return requirements.get(criterionName);
    }

    /**
     * Méthode qui permet d'ajouter un critère à un adolescent
     * @param key la clé du critère à ajouter
     * @param criterion le critère à ajouter
     * @return [true] si le critère a été ajouté, [false] sinon
     */
    public boolean addCriterion(String key, Criterion criterion) {
        try {
            if (!requirements.containsKey(key)) {
                requirements.put(key, criterion);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Méthode qui permet de modifier un critère d'un adolescent
     * @param criterionName le nom du critère à modifier
     * @param criterion le nouveau critère
     */
    public void editCriterion(String criterionName, Criterion criterion) {
        requirements.replace(criterionName, criterion);
    }
    
    /**
     * Méthode qui permet d'obtenir la liste des critères d'un adolescent
     * @return la liste des critères de l'adolescent
     */
    public Map<String, Criterion> getRequirements() {
        return requirements;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Teenager guest = (Teenager) obj;
        if (id != guest.id)
            return false;
        if (name == null) {
            if (guest.name != null)
                return false;
        } else if (!name.equals(guest.name))
            return false;
        if (forname == null) {
            if (guest.forname != null)
                return false;
        } else if (!forname.equals(guest.forname))
            return false;
        if (gender == null) {
            if (guest.gender != null)
                return false;
        } else if (!gender.equals(guest.gender))
            return false;
        if (countryName != guest.countryName)
            return false;
        if (birthDate == null) {
            if (guest.birthDate != null)
                return false;
        } else if (!birthDate.equals(guest.birthDate))
            return false;
        if (requirements == null) {
            if (guest.requirements != null)
                return false;
        } else if (!requirements.equals(guest.requirements))
            return false;
        return true;
    }
}