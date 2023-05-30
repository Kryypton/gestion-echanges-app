
/*Rappel de Adham : Cette classe doit permettre par rapport a l'historique des anciennes liaison de 
Teenager de créer une nouvelle regle de calcule du poids d'un arrete.*/ 

// Ce qu'il manque : 

// Une methode qui importe une liste de Teenager qui ont deja été correspondant.
// Une methode qui exporte une liste de Teenager qui ont deja été correspondant.

/* Et enfin une methode que l'on ajoutera a AffectationVersion1.java dans la methode weight.
Cette methode devra retourner un int , elle permttra de voir si un Teenager a des preferences ou 
contrainte par rapport a l'historique de l'autre Teenager 

Pour moi******
2 Teenager qui ont deja été ensemble mais que essemble  = -100 
2 Teenager n'ont jamais été ensemble = 0 
2 Teenager qui ont deja été ensemble mais aussi avec d'autre = -50
*/ 

// si vous avez des ammelioration mettez les.

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import fr.ulille.but.sae2_02.graphes.Arete;

public class History implements Serializable {
private Map<Teenager , Teenager> affectationsHistory;

public History() { // Création de la hashmap de Teenagers
    this.affectationsHistory = new HashMap<Teenager , Teenager>();
}

    // Création de la hashmap de Teenagers et les arretes qui vont avec entre les Teenager.
    public History(List<fr.ulille.but.sae2_02.graphes.Arete<Teenager>> aretes){
        this();
        for(Arete<Teenager> arete : aretes){
            this.affectations(arete.getExtremite1() , arete.getExtremite2());
        }
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
    public Teenager get( Teenager t){
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


    // Methode qui permet de réevaluer le poids d'un arrete en fonction de l'historique des Teenagers.
    public int historyTeenager(Teenager host , Teenager visitor){
        if(estAffecter(host)){
            if(this.get(host).equals(visitor)){
                if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("meme") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("meme")){
                    return -100;
                }
                if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("different") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("different")){
                    return 50;
                }
            }
        }else if(estAffecter(visitor)){
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
        Teenager teenager4 = new Teenager(4, "teen4", "C", "F", LocalDate.of(2002, 10, 20), Country.SPAIN);

        History history = new History();
        history.affectations(teenager1 , teenager2);
        history.affectations(teenager3 , teenager4);
        // sauvegarde de l'historique dans un fichier
        String filename = "./res/historique.ser";
        history.saveHistory(filename);

        // Chargement de l'historique à partir d'un fichier
        History loadedHistory = new History();
        loadedHistory.loadHistory(filename);

        // Affichage 
        System.out.println("historique chargé :");
        for (Teenager t : loadedHistory.getAssociations().keySet()) {
            System.out.println(t.getName() + " -> " + loadedHistory.getAssociations().get(t).getName());
        }
    }
}
