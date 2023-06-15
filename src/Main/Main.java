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
    private static Scanner sc = new Scanner(System.in);

    public static class SaisieClavier {

        public static String saisieClavierStr() {
            String str = sc.nextLine();
            return str;
        }

        public static char saisieClavierChar() {
            try {
                String str = sc.nextLine();
                char c = str.charAt(0);
                return c;
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
                return saisieClavierChar();
            }
        }

        public static int saisieClavierInt() {
            try {
                int i = sc.nextInt();
                return i;
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
                return saisieClavierInt();
            }
        }

        public static double saisieClavierDouble() {
            try {
                double d = sc.nextDouble();
                return d;
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
                return saisieClavierDouble();
            }
        }

        public static Country saisieClavierCountry() {
            try {
                String str = sc.nextLine();
                Country c = Country.valueOf(str.toUpperCase());
                return c;
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
                return saisieClavierCountry();
            }
        }

        public static LocalDate saisieClavierDate() {
            try {
                String str = sc.nextLine();
                LocalDate d = LocalDate.parse(str);
                return d;
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
                return saisieClavierDate();
            }
        }
    }
    
    private static char tableauDeBordArrive() {
        System.out.println("1 - Ajouter un Etudiant manuellement à la liste");
        System.out.println("2 - Ajouter un Enssemble d'étudiant à partir d'un fichier CSV");
        System.out.println("3 - Ajouter un Etudiant à la liste");
        System.out.println("4 - Ajouter un Etudiant à la liste");
        char c = SaisieClavier.saisieClavierStr().charAt(0);
        return c;
    }

    private static Teenager createTeenagerManually() {
        System.out.println("L'ajout manuel d'un étudiant");
        String nom;
        String prenom;
        LocalDate dateNaissance;
        Country pays;

        System.out.println("Nom : ");
        nom = SaisieClavier.saisieClavierStr();
        System.out.println("Prénom : ");
        prenom = SaisieClavier.saisieClavierStr();
        System.out.println("Date de naissance : (format : yyyy-mm-dd)");
        dateNaissance = SaisieClavier.saisieClavierDate();
        System.out.println("Pays : ");
        pays = SaisieClavier.saisieClavierCountry();
        return new Teenager(nom, prenom, dateNaissance, pays);
    }

    private static void addToPlatform(Teenager teenager) {
        platform.addTeenager(teenager);
        System.out.printf("Etudiant [%s]-[%s]-[%s] a été ajouter avec succès\n", teenager.getName(), teenager.getAgeYear(), teenager.getId());
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

    public void run() {
        char saisie;
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

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
