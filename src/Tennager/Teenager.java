package Tennager;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.Scanner;

import Criterion.Country;
import Criterion.Criterion;
import Criterion.CriterionName;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Cette classe permet de gérer les informations relatives à un adolescent et de déterminer sa compatibilité avec d'autres adolescents en fonction de différents critères.
 * @since 1.0
 * @version 1.0
 * @author Dorny Nathan
 * @author Berrakane Adham
 * @author Moutté Quentin
 */
public class Teenager implements Serializable{
    
    private int id;
    private String name;
    private String forname; 
    private Country countryName;
    private LocalDate birthDate;
    private Map<String, Criterion> requirements;
    private Teenager lastAffectation;
    private static int ctp = 0;

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
    public Teenager(String name , String forname ,  LocalDate birthDate , Country countryName, Map<String, Criterion> requirements){
        this.id = Teenager.ctp++ ;
        this.name = name;
        this.forname = forname;
        this.birthDate = birthDate;
        this.requirements = requirements;
        this.countryName = countryName;
    }

    public Teenager(String name , String forname , LocalDate birthDate , String countryName, Map<String, Criterion> requirements){
        this(name,forname,birthDate,isCountry(countryName),requirements);
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
    public Teenager(String name , String forname  , LocalDate birthDate , Country countryName){
        this.id = Teenager.ctp++ ;
        this.name = name;
        this.forname = forname;
        this.birthDate = birthDate;
        this.requirements = new HashMap<String, Criterion>();
        this.countryName = countryName;
    }


    public Teenager(String forname , String name , Country countryName ){
        this.id = Teenager.ctp++ ;
        this.name = name;
        this.forname = forname;
        this.countryName = countryName;
        this.requirements = new HashMap<String, Criterion>();
    }

    /**
     * Constructeur de la classe Teenager en paramètre un String, qui correspond à un teengager qui vient de l'importation d'un fichier, charger en fonction du patern
     * @param CSV le nom d'un adolescent
     * @param id l'identifiant unique d'un adolescent
     * @param patern l'ordre des paramétre
     */
    public Teenager(String CSV, int id, String pattern) {
        Scanner scan = new Scanner(CSV);
        scan.useDelimiter(";");

        int i = 0;
        String forename = "";
        String name = "";
        Country country = null;
        LocalDate birthDate = null;
        String hobbies = "";
        Criterion guestAnimalAllergy = null;
        Criterion hostHasAnimal = null;
        Criterion guestFood = null;
        Criterion hostFood = null;
        Criterion gender = null;
        Criterion pairGender = null;
        Criterion history = null;

        while (scan.hasNext()) {
            String value = scan.next();

            switch (i) {
                case 0:
                    break;
                case 1:
                    forename = value;
                    break;
                case 2:
                    name = value;
                    break;
                case 3:
                    country = Country.valueOf(value.toUpperCase());
                    break;
                case 4:
                    birthDate = LocalDate.parse(value);
                    break;
                case 5:
                    hobbies = value;
                    break;
                case 6:
                    guestAnimalAllergy = new Criterion(value, CriterionName.GUEST_ANIMAL_ALLERGY);
                    break;
                case 7:
                    hostHasAnimal = new Criterion(value, CriterionName.HOST_HAS_ANIMAL);
                    break;
                case 8:
                    guestFood = new Criterion(value, CriterionName.GUEST_FOOD);
                    break;
                case 9:
                    hostFood = new Criterion(value, CriterionName.HOST_FOOD);
                    break;
                case 10:
                    gender = new Criterion(value, CriterionName.GENDER);
                    break;
                case 11:
                    pairGender = new Criterion(value, CriterionName.PAIR_GENDER);
                    break;
                case 12:
                    history = new Criterion(value, CriterionName.HISTORY);
                    break;
            }

            i++;
        }
        this.id = id;
        this.forname = forename;
        this.name = name;
        this.countryName = country;
        this.birthDate = birthDate;
        this.requirements = new HashMap<>();
        this.requirements.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), guestAnimalAllergy);
        this.requirements.put(CriterionName.HOST_HAS_ANIMAL.name(), hostHasAnimal);
        this.requirements.put(CriterionName.GUEST_FOOD.name(), guestFood);
        this.requirements.put(CriterionName.HOST_FOOD.name(), hostFood);
        this.requirements.put(CriterionName.GENDER.name(), gender);
        this.requirements.put(CriterionName.PAIR_GENDER.name(), pairGender);
        this.requirements.put(CriterionName.HISTORY.name(), history);

        scan.close();
    }

    /**
     * Méthode qui permet de savoir si un adolescent est compatible avec un autre adolescent en terme de critères animal
     * @param guest l'invité à comparer avec l'adolescent courant
     * @return [true] si l'adolescent est compatible avec l'invité, [false] sinon
     */
    public boolean compatibleAnimal(Teenager guest){
        if (guest == null) return false;
        if(!guest.requirements.containsKey("GUEST_ANIMAL_ALLERGY") || !this.requirements.containsKey("HOST_HAS_ANIMAL")){
            return true;
        }
        Criterion animalRequirement = requirements.get(CriterionName.HOST_HAS_ANIMAL.name());
        Criterion guestAnimalRequirement = guest.getRequirements().get(CriterionName.GUEST_ANIMAL_ALLERGY.name());
        if (animalRequirement.getValue().equals("yes") && guestAnimalRequirement.getValue().equals("yes")) {
            return false; // Incompatible car l'invité a une allergie et l'hote a un animal.
        } 
        return true;
    }
    
    /**
     * Méthode qui permet de savoir si un adolescent est compatible avec un autre adolescent en terme de critères nourriture
     * @param guest l'invité à comparer avec l'adolescent courant
     * @return [true] si l'adolescent est compatible avec l'invité, [false] sinon
     */
    public boolean compatibleFood(Teenager guest) {
        if(!guest.requirements.containsKey("GUEST_FOOD") || !this.requirements.containsKey("HOST_FOOD")){
            return true;
        }
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
        if (this == guest) return false; // Incompatible car c'est la même personne.
        if (guest == null) return false;
        if (!compatibleAnimal(guest)) return false;
        if (!compatibleFood(guest)) return false;
        if(this.countryName == Country.FRANCE || guest.getCountryName() == Country.FRANCE){
            if(this.nbLoisirCommun(guest) == 0){
                return false;
            }
        }
        return true;
    }

    /*public boolean compatibleWishGender(Teenager other) {
        try {
            if (this.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue().equals(other.getGender())) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }*/


    /**
     * Méthode qui permet de connaitre l'age d'un adolescent de type Period
     * @return l'age d'un adolescent
     */
    public Period getAge() {
        LocalDate dateNow = LocalDate.now();
        Period p = Period.between(this.birthDate, dateNow);
        return p;
    }

    public Period getDiffAge(Teenager guest){
        return Period.between(this.birthDate, guest.getBirthDate());
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
            try {
                entry.getValue().isValid();
                validRequirements.put(entry.getKey(), entry.getValue());
            } catch (Exception e) {
                e.printStackTrace();
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
        
        List<String> teenHobbies = Arrays.asList(teen.requirements.get("HOBBIES").getValue().split(","));
        List<String> thisHobbies = Arrays.asList(this.requirements.get("HOBBIES").getValue().split(","));
        int nbLoisir = 0;
        for (String hobby : teenHobbies) {
            if (thisHobbies.contains(hobby)) {
                nbLoisir++;
            }
        }
        return nbLoisir;
    }


    /**
     * Renvoie true si les 2 Teenager ont au moins un loisir en commun.
     * @param first un Teenager 
     * @param other un Teenager 
     * @return
     */
    public boolean loisirCommun(String first, String other){
        return first.equals(other);
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
     * @param string le nom du critère à retourner
     * @return le critère de l'adolescent
     */
    public Criterion getCriterion(String string) {
        return requirements.get(string);
    }

    /**
     * Méthode qui permet de retourner un critère d'un adolescent
     * @param criterionName le nom du critère à retourner
     * @return le critère de l'adolescent
     */
    public String getCriterion(CriterionName criterionName) {
        Criterion c = requirements.get(criterionName.name());
        if(c != null){
            return c.getValue();
        }
        return null;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((forname == null) ? 0 : forname.hashCode());
        result = prime * result + ((countryName == null) ? 0 : countryName.hashCode());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        result = prime * result + ((requirements == null) ? 0 : requirements.hashCode());
        result = prime * result + ((lastAffectation == null) ? 0 : lastAffectation.hashCode());
        return result;
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
        if (lastAffectation == null) {
            if (other.lastAffectation != null)
                return false;
        } else if (!lastAffectation.equals(other.lastAffectation))
            return false;
        return true;
    }

    public String toString() {
        return this.getForname() + " " +  this.getName();
    }

    /**
     * Méthode qui permet de retrouver l'enumération du pays grace à un String du pays. Utiliser lors de l'importation
     * @param countryName Le nom du pays de l'étudiant
     * @return l'enum correspondant aux pays en paramètre
     */
    public static Country isCountry(String countryName){
        countryName.toUpperCase();
        if(countryName.equals("FRANCE")){
            return Country.FRANCE;
        }
        if(countryName.equals("ITALY")){
            return Country.ITALY;
        }
        if(countryName.equals("SPAIN")){
            return Country.SPAIN;
        }
        //if(countryName.equals("GERMANY")){
        return Country.GERMANY;
        //}
    }

    /**
     * Méthode qui permet d'avoir un adolescent en chaine de caractére, pour un format en CSV
     * @return un String avec les informations de l'adolescent
     */
    public String teenagerToString(){
        return this.getId() + ";" + this.forname+";"+this.name+";"+this.countryName+";"+this.birthDate.toString()+";"+this.requirements.get(CriterionName.HOBBIES.name())+
        ";"+this.requirements.get(CriterionName.GUEST_ANIMAL_ALLERGY.name())+";"+this.requirements.get(CriterionName.HOST_HAS_ANIMAL.name())+";"+
        this.requirements.get(CriterionName.GUEST_FOOD.name())+";"+this.requirements.get(CriterionName.HOST_FOOD.name())+";"+this.requirements.get(CriterionName.GENDER.name())+
        ";"+this.requirements.get(CriterionName.PAIR_GENDER.name())+";"+this.requirements.get(CriterionName.HISTORY.name());
    }

    public Criterion getHistory() {
        return requirements.get(CriterionName.HISTORY.name());
    }

    public void setLastAffectation(Teenager t){
        this.lastAffectation = t;
    }

    public Teenager getLastAffectation(){
        return this.lastAffectation;
    }
}
