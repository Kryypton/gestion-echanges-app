package Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.BlockElement;

import Criterion.Country;
import Criterion.Criterion;
import Criterion.CriterionName;
import Platform.Platform;
import Tennager.Teenager;
import graph.Affectation;
import graph.AffectationUtil;

public class TableauDeBordRoot {
    private static Platform platform = new Platform();
    private static Boolean isRunning = true;
    private static Scanner sc = new Scanner(System.in);

    public static class SaisieClavier {

        public static void fermerScanner() {
            sc.close();
        }

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

        public static String saisieClavierRegime() {
            try {
                String str = sc.nextLine();
                if (str.equals("vegetarian") || str.equals("nonuts")) {
                    return str;
                } else {
                    System.out.println("Erreur de saisie, veuillez recommencer");
                    return saisieClavierRegime();
                }
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
                return saisieClavierRegime();
            }
        }

        public static String saisieGenre() {
            try {
                String str = sc.nextLine();
                if (str.equals("female") || str.equals("male") || str.equals("other")) {
                    return str;
                } else {
                    System.out.println("Erreur de saisie, veuillez recommencer");
                    return saisieGenre();
                }
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
                return saisieGenre();
            }
        }

        public static Teenager saisieClavierTeenager() {
            try {
                int id = Integer.parseInt(sc.nextLine());
                Teenager t = platform.getTeenagerById(id);
                return t;
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer, l'étudiant n'existe pas.");
                return saisieClavierTeenager();
            }
        }

        public static char saisieYesOrNo() {
            try {
                String str = sc.nextLine();
                char c = str.charAt(0);
                if (c == 'y' || c == 'n') {
                    return c;
                } else {
                    System.out.println("Erreur de saisie, veuillez recommencer");
                    return saisieYesOrNo();
                }
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
                return saisieYesOrNo();
            }
        }
    }

    private static char gestionEleve() {
        System.out.println("1 - Ajouter un Etudiant manuellement à la liste");
        System.out.println("2 - Ajouter un Enssemble d'étudiant à partir d'un fichier CSV");
        System.out.println("3 - Afficher les étudiants");
        System.out.println("\t (b) - Retour au menu principal");
        System.out.println("\t (q) - Quitter l'application");
        char c = SaisieClavier.saisieClavierStr().charAt(0);
        return c;
    }

    public static char gestionAppariement() {
        System.out.println("1 - Ajouter un Appariement manuellement à la liste");
        System.out.println("2 - Générer les appariements à l'aide de Graph");
        System.out.println("3 - Afficher les appariements");
        System.out.println("\t (b) - Retour au menu principal");
        System.out.println("\t (q) - Quitter l'application");
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
        System.out.println("\t (b) - Retour au menu principal");
        System.out.println("\t (q) - Quitter l'application");
        char c = SaisieClavier.saisieClavierStr().charAt(0);
        return c;
    }

    private static Teenager createTeenagerManually() {
        System.out.println("L'ajout manuel d'un étudiant");
        String nom;
        String prenom;
        LocalDate dateNaissance;
        Country pays;
        Map<String, Criterion> requirements = new HashMap<String, Criterion>();
        Teenager lastAffectation = null;

        System.out.println("Nom : ");
        nom = SaisieClavier.saisieClavierStr();
        System.out.println("Prénom : ");
        prenom = SaisieClavier.saisieClavierStr();
        System.out.println("Date de naissance : (format : yyyy-mm-dd)");
        dateNaissance = SaisieClavier.saisieClavierDate();
        System.out.println("Pays : ");
        pays = SaisieClavier.saisieClavierCountry();
        System.out.println("Genre : (female/male/other)");
        requirements.put(CriterionName.GENDER.name(), new Criterion(SaisieClavier.saisieGenre(), CriterionName.GENDER));
        System.out.println("L'étudiant a-t-il des allargie aux animaux ? (y/n)");
        requirements.put(CriterionName.GUEST_ANIMAL_ALLERGY.name(), new Criterion(SaisieClavier.saisieYesOrNo() == 'y' ? "yes" : "no", CriterionName.GUEST_ANIMAL_ALLERGY));
        System.out.println("L'étudiant possède-t-il un animal chez lui ? (y/n)");
        requirements.put(CriterionName.HOST_HAS_ANIMAL.name(), new Criterion(SaisieClavier.saisieYesOrNo() == 'y' ? "yes" : "no", CriterionName.HOST_HAS_ANIMAL));
        System.out.println("L'étudiant suis-t-il un régime spécial ? (y/n)");
        if (SaisieClavier.saisieYesOrNo() == 'y') {
            System.out.println("Quel régime ?");
            requirements.put(CriterionName.GUEST_FOOD.name(), new Criterion(SaisieClavier.saisieClavierRegime(), CriterionName.GUEST_FOOD));
        } else requirements.put(CriterionName.GUEST_FOOD.name(), new Criterion("no", CriterionName.GUEST_FOOD));
        System.out.println("L'étudiant peut-il cuisiner pour des Végétariens ? (y/n)");
        requirements.put(CriterionName.HOST_FOOD.name(), new Criterion(SaisieClavier.saisieYesOrNo() == 'y' ? "vegetarian" : null , CriterionName.HOST_FOOD));
        System.out.println("L'étudiant peut-il cuisiner pour des personnes allergiques aux noix ? (y/n)");
        requirements.put(CriterionName.HOST_FOOD.name(), new Criterion(SaisieClavier.saisieYesOrNo() == 'y' ? "nonuts" : null , CriterionName.HOST_FOOD));
        System.out.println("Avec quel genre l'étudiant souhaite être ? (male/female/other)");
        requirements.put(CriterionName.PAIR_GENDER.name(), new Criterion(SaisieClavier.saisieGenre(), CriterionName.PAIR_GENDER));
        System.out.println("L'étudiant souhaite-t-il être avec son ancienne affectation ? (y/n)");
        if (SaisieClavier.saisieYesOrNo() == 'y') {
            System.out.println("Quel est l'ID de son ancienne affectation ?");
            lastAffectation = SaisieClavier.saisieClavierTeenager();
        }
        return new Teenager(nom, prenom, dateNaissance, pays, requirements, lastAffectation);
    }

    private static void addToPlatform(Teenager teenager) {
        platform.addTeenager(teenager);
        System.out.printf("Etudiant [%s]-[%s]-[%s] a été ajouter avec succès\n", teenager.toString(), teenager.getAgeYear(), teenager.getId());
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

    private static void afficherAppariement() {
        /*System.out.println("Voici les appariements :");
        for (Pair pair : platform.getPairs()) {
            System.out.printf("Pair [%s]-[%s] :\n", pair.getTeenager1().getName(), pair.getTeenager2().getName());
            System.out.printf("    - [%s]-[%s]-[%s] avec [%s]-[%s]-[%s]\n", pair.getTeenager1().getName(), pair.getTeenager1().getAgeYear(), pair.getTeenager1().getId(), pair.getTeenager2().getName(), pair.getTeenager2().getAgeYear(), pair.getTeenager2().getId());
        }*/
    }
    
    public void runRoot() {
        String saisie;
        while (isRunning) {
            System.out.println("-------------------- Menu principal --------------------");
            saisie = "" + tableauDeBord();
            //////////////////////////////// Gestion des élèves ////////////////////////////////

            if (saisie.equals("1")) {
                System.out.println("-------------------- Gestion des élèves --------------------");
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

            //////////////////////////// Gestion des affectations ////////////////////////////

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

            ////// Ajout d'une affectation à la liste //////
            if (saisie.equals("ga1")) {

            }


            //////////////////////////// Gestion de l'historique ////////////////////////////
            if (saisie.equals("3")) saisie = "gh" + gestionHistorique();
            
            //////////////////////////// Retour au Menu principal ////////////////////////////
            if (saisie.equals("b")) {
                System.out.println("Vous avez choisi de retourner au menu principal");
            }

            if (saisie.equals("q")) {
                System.out.println("Vous avez choisi de quitter le programme");
                isRunning = false;
            }

            ///
        } 
    }

    public static void main(String[] args) {
        TableauDeBordRoot main = new TableauDeBordRoot();
        main.runRoot();
    }


        public static boolean idPresent(int id , Collection<Teenager> list){
        for (Teenager teenager : list) {
            if(teenager.getId() == id){
                return true;
            }
        }
        return false;
    }

    ////// Permet de créer un appariement manuellement avec les ID //////
    public static void créerAppariement(Affectation affectation , Platform platform){
        Set<Teenager> hote = affectation.getAssociations().keySet();
        Collection<Teenager> visiteur = affectation.getAssociations().values();
        int idHost = -1;
        int idVisitor = -1; ////// pas a 0 car l'id peut etre de 0 ;

        System.out.println("Bienvenue dans le menu de création d'appariement");

        System.out.println("Voici la liste des hôtes : ");
        for (Teenager teenager : hote) {
            System.out.println(teenager);
        }

        System.out.println("Voici la liste des visiteurs : ");
        for (Teenager teenager : visiteur) {
            System.out.println(teenager);
        }

        while (!idPresent(idVisitor, visiteur)) {
            System.out.println("Veuillez saisir l'id du visiteur : ");
            try {
                idVisitor = SaisieClavier.saisieClavierInt(); 
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
            }
        }
            
        while (!idPresent(idHost, hote)) {
            System.out.println("Veuillez saisir l'id de l'hôte : ");
            try {
                idHost = SaisieClavier.saisieClavierInt(); 
            } catch (Exception e) {
                System.out.println("Erreur de saisie, veuillez recommencer");
            }

        }
        ///// Les affectations sont faites ici /////
        affectation.clear();
        affectation.affectations(platform.getTeenagerFromID(idHost), platform.getTeenagerFromID(idVisitor));

        ////// On supprime les étudiants de la liste des étudiants comme il sont affecter //////
        platform.removeTeenager(platform.getTeenagerFromID(idHost));
        platform.removeTeenager(platform.getTeenagerFromID(idVisitor));

        affectation.add(AffectationUtil.affectation(platform.getTeenagerArrayList(), affectation.getVisitor(), affectation.getHost()));
    }



    ////// Permet de créer un appariement automatique//////

    public static void gestionDesAppariement(Country host , Country visitor){
        Platform platform = new Platform();
        boolean isRunning = true;

        try {
            platform.importListTeenagers(new File("res/Teenagers.csv"));   
        } catch (Exception e) {
            System.out.println("Erreur lors de l'importation du fichier");
        }


        if(platform.getNbCountry(host) > 1 && platform.getNbCountry(visitor) >1 ){
            System.out.println("Il y a plus d'un étudiant de chaque pays");

            while (isRunning) {
            Affectation affectation = new Affectation(AffectationUtil.affectation(platform.getTeenagerList(), visitor, host),  host , visitor);
            System.out.println("La touche <a> vous permet de generer les appariements");
            System.out.println("La touche <z> vous permet de créer un appariement manuellement");
            System.out.println("La touche <q> vous permet de quitter");
            char c = SaisieClavier.saisieClavierChar();

            if(c == 'a'){
                try {
                    platform.exporterAffectationAutomatique("res/Appariement.csv" , host , visitor);
                } catch (FileNotFoundException e) {
                    System.err.println("Erreur lors de l'exportation du fichier");
                }
            }else if(c == 'z'){
                créerAppariement(affectation, platform);
            }else if(c == 'q'){
                isRunning = false;
            } 
            }

        }
    }
}
