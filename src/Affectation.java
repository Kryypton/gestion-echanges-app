import java.io.*;
import java.time.LocalDate;
import java.util.*;

import fr.ulille.but.sae2_02.graphes.Arete;

public class Affectation implements Serializable {
    private Map<Teenager , Teenager> affectationsHistory;
    private Country Host;
    private Country Visitor;

    public Affectation() { // Création de la hashmap de Teenagers
        this.affectationsHistory = new HashMap<Teenager , Teenager>();
    }

    // Création de la hashmap de Teenagers et les arretes qui vont avec entre les Teenager.
    public Affectation(List<Arete<Teenager>> aretes){
        this(aretes , null, null);

    }

    public Affectation(List<Arete<Teenager>> aretes, Country Host, Country Visitor){
        this();
        for(Arete<Teenager> arete : aretes){
            this.affectations(arete.getExtremite1() , arete.getExtremite2());
        }
        this.Host = Host;
        this.Visitor = Visitor;

    }

    public Country getHost(){
        return this.Host;
    }

    public Country getVisitor(){
        return this.Visitor;
    }

    // Enleve le teenager courant qui est avec un autre.
    public void desaffectations(Teenager t){
        this.affectationsHistory.remove(t);
    }

    // Affecte 2 Teenagers
    public void affectations(Teenager t1 , Teenager t2){
        this.affectationsHistory.put(t1 , t2);
    }

    // Retourne le Teenager associer au Teenager courant.
    public Teenager getLast( Teenager t){
        return this.affectationsHistory.get(t);
    }

    // renvoie true si un teenager est affecter a un teenager.
    public boolean estAffecter(Teenager t){
        if(this.affectationsHistory.containsKey(t)){
            return true;
        }
        return false;
    }

    
    public boolean estAffecter(Teenager t1 , Teenager t2){
        if(this.affectationsHistory.containsKey(t1) && this.affectationsHistory.get(t1) == t2){
            return true;
        }
        return false;
    }


    // retourne tout les associations 
    public Map<Teenager, Teenager> getAssociations() {
        return this.affectationsHistory ;
    }


    /**
     * Sauvegarde l'historique dans un fichier en utilisant la sérialisation binaire
     * @param filename Le nom du fichier de sauvegarde
     */
    public void saveHistory(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(affectationsHistory);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Historique sauvegardé ");
        } catch (IOException e) {
            System.out.println("erreur lors de la sauvegarde de l'historiqu" + e.getMessage());
        }
    }
    

    /**
     * Charge l'historique à partir d'un fichier de sauvegarde en utilisant la désérialisation binaire
     * @param filename Le nom du fichier de sauvegarde
     */

    public void loadHistory(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Map<Teenager, Teenager> history = (Map<Teenager, Teenager>) objectInputStream.readObject();
            affectationsHistory.putAll(history); // Utiliser putAll pour copier les éléments dans affectationsHistory
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("historique chargé");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("erreur lors du chargement de l'historique: " + e.getMessage());
        }
    }

    public int compatibleWishGender(Teenager host, Teenager other) {
        try {
            if (host.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue().equals(other.getGender()) ||  other.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue().equals(host.getGender())) {
                if(host.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue().equals(other.getGender()) &&  other.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue().equals(host.getGender())) {
                    return -10;
                } else return -5; 
            }
            return 0;
        } catch (NullPointerException e) {
            return -10;
        }
    }


    // Methode qui permet de réevaluer le poids d'un arrete en fonction de l'historique des Teenagers.
    public int historyTeenager(Teenager host , Teenager visitor){
        if(estAffecter(host)){
            if(this.getLast(host).equals(visitor)){
                if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same")){
                    return -100;
                }
                if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("other") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("other")){
                    return 50;
                }
            }
        }else if(estAffecter(visitor)){
            if(this.getLast(visitor).equals(host)){
                if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same")){
                    return -100;
                }
                if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("other") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("other")){
                    return 50;
                }
            }
        }
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!(this.getHost() == this.getVisitor() && this.getHost()==null)) sb.append(this.getHost() + " - " + this.getVisitor() + "\n");
        for (Map.Entry<Teenager, Teenager> entry : affectationsHistory.entrySet()) {
            sb.append(entry.getKey().getName().toString() + " -> " + entry.getValue().getName().toString() + "\n");
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        Criterion estAlergique = new Criterion("yes", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion estPasAlergique = new Criterion("no", CriterionName.GUEST_ANIMAL_ALLERGY);
        Criterion aUnAnimal = new Criterion("yes", CriterionName.HOST_HAS_ANIMAL);
        Criterion aPasAnimal = new Criterion("no", CriterionName.HOST_HAS_ANIMAL);
        
        Criterion sport = new Criterion("sport", CriterionName.HOBBIES);
        Criterion technologie = new Criterion("technologie", CriterionName.HOBBIES);
        Criterion science = new Criterion("science", CriterionName.HOBBIES);
        Criterion reading = new Criterion("reading", CriterionName.HOBBIES);
        Criterion music = new Criterion("music", CriterionName.HOBBIES);
        Criterion hobbiesVide = new Criterion("", CriterionName.HOBBIES);
        Criterion GuestFoodVege = new Criterion("vegetarian", CriterionName.GUEST_FOOD);
        Criterion GuestFoodNonuts = new Criterion("nonuts", CriterionName.GUEST_FOOD);
        Criterion HostFoodVege = new Criterion("vegetarian", CriterionName.HOST_FOOD);
        Criterion HostFoodNonuts = new Criterion("nonuts", CriterionName.HOST_FOOD);
        Criterion HostFoodVide = new Criterion("", CriterionName.HOST_FOOD);
        Criterion foodVide = new Criterion("", CriterionName.GUEST_FOOD);
        Criterion GuestfoodVide = new Criterion("", CriterionName.GUEST_FOOD);
        Criterion femme = new Criterion("female", CriterionName.GENDER);
        Criterion homme = new Criterion("homme", CriterionName.GENDER);
        Criterion other = new Criterion("other", CriterionName.GENDER);
        Criterion veutFemme = new Criterion("female", CriterionName.PAIR_GENDER);
        Criterion veutHomme = new Criterion("homme", CriterionName.PAIR_GENDER);
        Criterion veutOther = new Criterion("other", CriterionName.PAIR_GENDER);
        Criterion historySame = new Criterion("same", CriterionName.HISTORY);
        Criterion historyOther = new Criterion("other", CriterionName.HISTORY);
        Criterion historyVide = new Criterion("", CriterionName.HISTORY);

        Teenager teenager1 = new Teenager(1, "teen1", "A", "male", LocalDate.of(2000, 5, 10), Country.FRANCE);
        Teenager teenager2 = new Teenager(2, "teen2", "B", "female", LocalDate.of(2001, 8, 15), Country.GERMANY);
        Teenager teenager3 = new Teenager(3, "teen3", "C", "male", LocalDate.of(2002, 10, 20), Country.ITALY);
        Teenager teenager4 = new Teenager(4, "teen4", "D", "male", LocalDate.of(2002, 10, 20), Country.SPAIN);
        Teenager teenager5 = new Teenager(5, "teen5", "E", "other", LocalDate.of(2002, 10, 20), Country.SPAIN);
        Teenager teenager6 = new Teenager(6, "teen6", "F", "female", LocalDate.of(2002, 10, 20), Country.FRANCE);
        Teenager teenager7 = new Teenager(7, "teen7", "G", "female", LocalDate.of(2002, 10, 20), Country.FRANCE);
        Teenager teenager8 = new Teenager(8, "teen8", "H", "male", LocalDate.of(2002, 10, 20), Country.ITALY);
        Teenager teenager9 = new Teenager(9, "teen9", "I", "other", LocalDate.of(2002, 10, 20), Country.GERMANY);
        Teenager teenager10 = new Teenager(10, "teen10", "J", "female", LocalDate.of(2002, 10, 20), Country.ITALY);
        Teenager teenager11 = new Teenager(11, "teen11", "K", "female", LocalDate.of(2002, 10, 20), Country.SPAIN);
        Teenager teenager12 = new Teenager(12, "teen12", "L", "male", LocalDate.of(2002, 10, 20), Country.SPAIN);
        Teenager teenager13 = new Teenager(13, "teen13", "M", "male", LocalDate.of(2002, 10, 20), Country.GERMANY);
        Teenager teenager14 = new Teenager(14, "teen14", "N", "male", LocalDate.of(2002, 10, 20), Country.FRANCE);
        Teenager teenager15 = new Teenager(15, "teen15", "O", "male", LocalDate.of(2002, 10, 20), Country.GERMANY);

        teenager1.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager1.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager1.addCriterion(CriterionName.HOBBIES.name(),sport);
        teenager1.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager1.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVege);
        teenager1.addCriterion(CriterionName.GENDER.name(),homme);

        teenager2.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager2.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aUnAnimal);
        teenager2.addCriterion(CriterionName.HOBBIES.name(),technologie);
        teenager2.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodVege);
        teenager2.addCriterion(CriterionName.HOST_FOOD.name(), HostFoodVide);

        teenager3.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager3.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager3.addCriterion(CriterionName.HOBBIES.name(),science);
        teenager3.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager3.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);

        teenager4.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager4.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager4.addCriterion(CriterionName.HOBBIES.name(),reading);
        teenager4.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager4.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);

        teenager5.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager5.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aUnAnimal);
        teenager5.addCriterion(CriterionName.HOBBIES.name(),sport);
        teenager5.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager5.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVege);

        teenager6.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estAlergique);
        teenager6.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager6.addCriterion(CriterionName.HOBBIES.name(),technologie);
        teenager6.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodNonuts);
        teenager6.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);

        teenager7.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager7.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager7.addCriterion(CriterionName.HOBBIES.name(),science);
        teenager7.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager7.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);

        teenager8.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estAlergique);
        teenager8.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager8.addCriterion(CriterionName.HOBBIES.name(),reading);
        teenager8.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager8.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);

        teenager9.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager9.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aUnAnimal);
        teenager9.addCriterion(CriterionName.HOBBIES.name(),sport);
        teenager9.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodNonuts);
        teenager9.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVege);

        teenager10.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager10.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager10.addCriterion(CriterionName.HOBBIES.name(),technologie);
        teenager10.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager10.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);

        teenager11.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager11.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aUnAnimal);
        teenager11.addCriterion(CriterionName.HOBBIES.name(),science);
        teenager11.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager11.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVege);

        teenager12.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estAlergique);
        teenager12.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager12.addCriterion(CriterionName.HOBBIES.name(),reading);
        teenager12.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodNonuts);
        teenager12.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);

        teenager13.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager13.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager13.addCriterion(CriterionName.HOBBIES.name(),sport);
        teenager13.addCriterion(CriterionName.GUEST_FOOD.name(),GuestFoodVege);
        teenager13.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodNonuts);

        teenager14.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estAlergique);
        teenager14.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aPasAnimal);
        teenager14.addCriterion(CriterionName.HOBBIES.name(),technologie);
        teenager14.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager14.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodNonuts);

        teenager15.addCriterion(CriterionName.GUEST_ANIMAL_ALLERGY.name(),estPasAlergique);
        teenager15.addCriterion(CriterionName.HOST_HAS_ANIMAL.name(),aUnAnimal);
        teenager15.addCriterion(CriterionName.HOBBIES.name(),science);
        teenager15.addCriterion(CriterionName.GUEST_FOOD.name(),GuestfoodVide);
        teenager15.addCriterion(CriterionName.HOST_FOOD.name(),HostFoodVide);

        



        // sauvegarde de l'historique dans un fichier
        String filename = "./res/historique.ser";
        history.saveHistory(filename);

        // Chargement de l'historique à partir d'un fichier
        Affectation loadedHistory = new Affectation();
        loadedHistory.loadHistory(filename);

        // Affichage 
        System.out.println("historique chargé :\n" + loadedHistory.toString());
        System.out.println(history.getLast(teenager1).getName());
        
    }
}
