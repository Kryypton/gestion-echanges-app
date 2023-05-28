
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

public class History implements Serializable {
    private List<Association> associations;

    public History() {
        associations = new ArrayList<>();
    }

    /**
     * Ajoute une association à l'historique
     * @param association L'association à ajouter
     */
    public void addAssociation(Association association) {
        associations.add(association);
    }

    /**
     * Sauvegarde l'historique dans un fichier en utilisant la sérialisation binaire
     * @param filename Le nom du fichier de sauvegarde
     */
    public void saveHistory(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(associations);
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
            associations = (List<Association>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("historique chargé");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("erreur lordu chargement de l'historique :" + e.getMessage());
        }
    }
    

    public List<Association> getAssociations() {
        return associations;
    }

    public static void main(String[] args) {
        Teenager teenager1 = new Teenager(1, "teen1", "A", "M", LocalDate.of(2000, 5, 10), Country.FRANCE);
        Teenager teenager2 = new Teenager(2, "teen2", "B", "F", LocalDate.of(2001, 8, 15), Country.GERMANY);
        Teenager teenager3 = new Teenager(3, "teen3", "C", "F", LocalDate.of(2002, 10, 20), Country.ITALY);

        History history = new History();

        Association association = new Association(teenager1, teenager2);
        history.addAssociation(association);

        // sauvegarde de l'historique dans un fichier
        String filename = "historique.ser";
        history.saveHistory(filename);

        // Chargement de l'historique à partir d'un fichier
        History loadedHistory = new History();
        loadedHistory.loadHistory(filename);

        // Affichage 
        System.out.println("historique chargé :");
        for (Association assoc : loadedHistory.getAssociations()) {
            System.out.println(assoc.getTeenager1().getName() + " - " + assoc.getTeenager2().getName());
        }
    }
}
