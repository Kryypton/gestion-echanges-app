package Main;
import java.time.LocalDate;
import java.util.Scanner;

import Criterion.Country;
import Platform.Platform;
import Tennager.Teenager;

public class Main {
    private static Platform platform = new Platform();
    private static Boolean isRunning = true;

    private static String saisieClavier() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        sc.close();
        return str;
    }
    
    private static char tableauDeBord() {
        System.out.println("1 - Ajouter un Etudiant manuellement à la liste");
        System.out.println("2 - Ajouter un Enssemble d'étudiant à partir d'un fichier CSV");
        System.out.println("3 - Ajouter un Etudiant à la liste");
        System.out.println("4 - Ajouter un Etudiant à la liste");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        sc.close();
        return c;
    }

    private static Teenager createTeenagerManually() {
        String nom;
        String prenom;
        LocalDate dateNaissance;
        Country pays;
        Scanner sc = new Scanner(System.in);

        System.out.println("Nom : ");
        nom = saisieClavier();
        System.out.println("Prénom : ");
        prenom = saisieClavier();
        System.out.println("Date de naissance : (format : yyyy-mm-dd)");
        dateNaissance = LocalDate.parse(saisieClavier());
        System.out.println("Pays : ");
        pays = Country.valueOf(saisieClavier().toUpperCase());
        saisieClavier();
        sc.close();
        return new Teenager(nom, prenom, dateNaissance, pays);
    }

    private static void addToPlatform(Teenager teenager) {
        platform.addTeenager(teenager);
        System.out.println("Etudiant ajouté avec succès");
    }

    public static void main(String[] args) {
        while (isRunning) {
            if (tableauDeBord() == '1') {
                System.out.println("Vous avez choisi 1 - L'ajout manuel d'un étudiant");
                addToPlatform(createTeenagerManually());
            }
            if (tableauDeBord() == '2') System.out.println("Vous avez choisi 2");
            if (tableauDeBord() == '3') System.out.println("Vous avez choisi 3");
            if (tableauDeBord() == '3') System.out.println("Vous avez choisi 4");
            return;
        }
    }

}
