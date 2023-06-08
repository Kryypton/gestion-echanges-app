import java.io.*;
import java.time.LocalDate;
import java.util.*;

import fr.ulille.but.sae2_02.graphes.Arete;

public class Affectation implements Serializable {
    private Map<Integer, Map<Teenager , Teenager>> affectationsHistory;

    public Affectation() {
        this.affectationsHistory = new HashMap<Integer, Map<Teenager , Teenager>>();
    }

    // Création de la hashmap de Teenagers et les arretes qui vont avec entre les Teenager.
    public Affectation(Map<Integer, Arete<Teenager>> aretes){
        this.affectationsHistory = new HashMap<Integer, Map<Teenager , Teenager>>();
        for(Map.Entry<Integer, Arete<Teenager>> entry : aretes.entrySet()){
            this.affectationsHistory.put(entry.getKey() , new HashMap<Teenager, Teenager>());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Map<Teenager , Teenager>> entry : affectationsHistory.entrySet()) {
            int year = entry.getKey();
            Map<Teenager, Teenager> affectations = entry.getValue();
            StringBuilder yearStringBuilder = new StringBuilder();
            yearStringBuilder.append("Année : ").append(year).append("\n");
            for (Map.Entry<Teenager, Teenager> affectationEntry : affectations.entrySet()) {
                Teenager teenager1 = affectationEntry.getKey();
                Teenager teenager2 = affectationEntry.getValue();
                yearStringBuilder.append("\t").append(teenager1.getName()).append(" est chez ").append(teenager2.getName()).append("\n");
            }
            sb.append(yearStringBuilder);
        }
        return sb.toString();
    }
    

    /*
    // Enleve le teenager courant qui est avec un autre.
    public void desaffectations(Teenager t){
        this.affectationsHistory.remove(t);
    }*/

    // Affecte 2 Teenagers
    public void affectations(Teenager t1, Teenager t2, int year) {
        Map<Teenager, Teenager> affectation;
        if (affectationsHistory.containsKey(year)) {
            affectation = affectationsHistory.get(year);
        } else {
            affectation = new HashMap<Teenager, Teenager>();
            affectationsHistory.put(year, affectation);
        }
        affectation.put(t1, t2);
    }
    

    // Retourne le Teenager associer au Teenager courant.
    public Teenager get(Teenager t, int year){
        if (estAffecter(t, year)) {
            return this.affectationsHistory.get(year).get(t);
        }
        return null;
    }

    // renvoie true si un teenager est affecter a un teenager.
    public boolean estAffecter(Teenager t, int year){
        if(this.affectationsHistory.containsKey(year) && this.affectationsHistory.get(year).containsKey(t)){
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
            Map<Integer, Map<Teenager , Teenager>>  history = (Map<Integer, Map<Teenager , Teenager>>) objectInputStream.readObject();
            affectationsHistory.putAll(history); // Utiliser putAll pour copier les éléments dans affectationsHistory
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("historique chargé");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("erreur lors du chargement de l'historique: " + e.getMessage());
        }
    }


    // Methode qui permet de réevaluer le poids d'un arrete en fonction de l'historique des Teenagers.
    public int historyTeenager(Teenager host , Teenager visitor){
        if (estAffecter(host)) {
            if (this.get(host).equals(visitor)){
                if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("meme") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("meme")){
                    return -100;
                }
                if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("different") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("different")){
                    return 50;
                }
            }
        } else if(estAffecter(visitor)){
            if(this.get(visitor).equals(host)){
                if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("meme") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("meme")){
                    return -100;
                }
                if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("different") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("different")){
                    return 50;
                }
            }
        }
        return 0;
    }



    
    public static void main(String[] args) {
        Teenager teenager1 = new Teenager(1, "teen1", "A", "M", LocalDate.of(2000, 5, 10), Country.FRANCE);
        Teenager teenager2 = new Teenager(2, "teen2", "B", "F", LocalDate.of(2001, 8, 15), Country.GERMANY);
        Teenager teenager3 = new Teenager(3, "teen3", "C", "F", LocalDate.of(2002, 10, 20), Country.ITALY);
        Teenager teenager4 = new Teenager(4, "teen4", "D", "F", LocalDate.of(2002, 10, 14), Country.SPAIN);
        Teenager teenager5 = new Teenager(4, "teen5", "E", "M", LocalDate.of(2000, 2, 15), Country.SPAIN);
        Teenager teenager6 = new Teenager(4, "teen6", "F", "M", LocalDate.of(2002, 5, 02), Country.ITALY);
        Teenager teenager7 = new Teenager(4, "teen7", "G", "M", LocalDate.of(2002, 8, 23), Country.FRANCE);

        Affectation history = new Affectation();
        history.affectations(teenager1 , teenager2, 0);
        history.affectations(teenager3 , teenager4, 0);
        history.affectations(teenager5 , teenager6, 0);
        history.affectations(teenager7 , teenager1, 2);
        history.affectations(teenager2 , teenager3, 2);
        history.affectations(teenager4 , teenager5, 2);
        history.affectations(teenager6 , teenager7, 2);
        history.affectations(teenager1 , teenager3, 3);
        history.affectations(teenager2 , teenager4, 3);
        

        System.out.println(history);
        // sauvegarde de l'historique dans un fichier
        String filename = "./res/historique.ser";
        
        history.saveHistory(filename);

        // Chargement de l'historique à partir d'un fichier
        Affectation loadedHistory = new Affectation();
        loadedHistory.loadHistory(filename);

        // Affichage 
        System.out.println(loadedHistory);
    }
}
