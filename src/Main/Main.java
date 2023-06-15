package Main;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
        System.out.println("2 - Ajouter un Ensemble d'étudiant à partir d'un fichier CSV");
        System.out.println("3 - Supprimer un étudiants de la liste");
        System.out.println("4 - Afficher la liste d'étudiants");
        System.out.println("4 - Afficher la liste des appariements");
        System.out.println("4 - Générer les appariements");
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

    private static void afficherList(List list){
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    private static void afficherMap(Map list){
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        while (isRunning) {
            if (tableauDeBord() == '1') {
                System.out.println("Vous avez choisi 1 - L'ajout manuel d'un étudiant");
                addToPlatform(createTeenagerManually());
            }
            if (tableauDeBord() == '2') {
                System.out.println("Vous avez choisi 2 - Ajouter un Ensemble d'étudiant à partir d'un fichier CSV");
                System.out.println("Donner le chemin vers le fichier");

                Scanner saisieUtilisateur = new Scanner(System.in);

                System.out.println("Veuillez saisir le chemin vers le fichier CSV");
                String str = saisieUtilisateur.next();

                try {
                    File csv = new File(str);
                    Platform.importListTeenagers(csv);
                } catch (Exception e) {
                    System.out.println("Erreur, fichier introuvable");
                }
            }
            if (tableauDeBord() == '3') {
                System.out.println("Vous avez choisi 3 - ");
            }
            if (tableauDeBord() == '4') {
                System.out.println("Vous avez choisi 4 - ");
            }
            if (tableauDeBord() == '5') {
                System.out.println("Vous avez choisi 5 - ");
            }
            if (tableauDeBord() == '6') {
                System.out.println("Vous avez choisi 6 - ");
            }
        }
    }

}
