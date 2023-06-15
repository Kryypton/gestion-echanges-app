package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Criterion.Country;
import Platform.Platform;
import Tennager.Teenager;

public class TableauDeBordRoot {
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

        public static File saisieClavierFile() {
            try {
                String str = sc.nextLine();
                File f = new File(str);
                return f;
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
                return saisieClavierFile();
            }
        }
    }
    
    private static char gestionEleve() {
        System.out.println("1 - Ajouter un Etudiant manuellement à la liste");
        System.out.println("2 - Ajouter un Enssemble d'étudiant à partir d'un fichier CSV");
        System.out.println("3 - Afficher les étudiants");
        char c = SaisieClavier.saisieClavierStr().charAt(0);
        return c;
    }

    public static char gestionAppariement() {
        System.out.println("1 - Ajouter un Appariement manuellement à la liste");
        System.out.println("2 - Générer les appariements à l'aide de Graph");
        System.out.println("3 - Afficher les appariements");
        char c = SaisieClavier.saisieClavierStr().charAt(0);
        return c;
    }

    public static char gestionHistorique() {
        char c = SaisieClavier.saisieClavierStr().charAt(0);
        return c;
    }

    public static char tableauDeBord() {
        System.out.println("1 - Gestion élèves");
        System.out.println("2 - Gestion des appariements");
        System.out.println("2 - Consulter l'historique");
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

    private static void addToPlatformbyCSV(String path) {
        try {
            ArrayList<Teenager> list = Platform.importListTeenagers(new File(path));
            for (Teenager teenager : list) {
                platform.addTeenager(teenager);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("Erreur lors de l'importation du fichier [%s] n'est pas un fichier CSV ou n'existe pas.\n", path);
        }
        System.out.printf("Les étudiants du fichier [%s] ont été ajouter avec succès\n", path);
    }

    private static void  afficherAppariement() {
        System.out.println("Les appariements sont : ");
        for (Appariement appariement : platform.getAppariements()) {
            System.out.println(appariement);
        }
    }
    
    public void runRoot() {
        String saisie;
        while (isRunning) {
            saisie = "" + tableauDeBord();
            //////////////////////////////// Gestion des élèves ////////////////////////////////

            if (saisie.equals("1")) {
                System.out.println("Vous avez choisi La gestion des élèves");
                saisie = "ge" + gestionEleve(); 
            } 
            ////// Ajout d'un élève à la liste //////
            if (saisie.equals("ge1")) {
                System.out.println("Vous avez choisi d'ajouter un eleve à la liste");
                addToPlatform(createTeenagerManually());
                System.out.println("→ Retour au menu principal");
            }

            ////// Ajout d'élèves à la liste à partir d'un fichier CSV //////
            if (saisie.equals("ge2")) {
                System.out.println("Vous avez choisi d'ajouter des eleves à la liste à parit d'un fichier CSV");
                addToPlatformbyCSV(SaisieClavier.saisieClavierStr());
                System.out.println("→ Retour au menu principal");
            }

            ////// Affichage des élèves //////
            if (saisie.equals("ge3")) {
                System.out.println("Vous avez choisi d'afficher les élèves");
                System.out.println(platform.toStringTeengarderList()); 
                System.out.println("Retour au menu principal");
            }

            //////////////////////////// Gestion des appariements ////////////////////////////

            if (saisie.equals("2")) {
                System.out.println("Vous avez choisi La gestion des appariements");
                saisie = "ga" + gestionAppariement();
            }

            if (saisie.equals("ga3")) {
                System.out.println("Vous avez choisi d'afficher les appariements");
                platform.findCompatibleTeenagers();
                System.out.println(platform.getCompatibleTeenagers().toString());
                System.out.println("→ Retour au menu principal");
            }

            ////// Ajout d'un appariement à la liste //////
            if (saisie.equals("ga1")) {

            }


            //////////////////////////// Gestion de l'historique ////////////////////////////
            if (saisie.equals("3")) saisie = "gh" + gestionHistorique();
            
        } 
    }

    public static void main(String[] args) {
        TableauDeBordRoot main = new TableauDeBordRoot();
        main.runRoot();
    }
}
