package Main;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
    private static Country countryHote;
    private static Country countryVisiteur;
    private static ArrayList<Country> CountryList = new ArrayList<Country>();
    static {
        EnumSet.allOf(Country.class).forEach(country -> CountryList.add(country));
    }
    

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
                if (!platform.cleanByCountry(countryHote, countryVisiteur).contains(t)) {
                    System.out.println("Erreur de saisie, veuillez recommencer, l'étudiant n'existe pas dans les pays selectionnés.");
                    return saisieClavierTeenager();
                } else {
                    return t;
                }
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

    public boolean configExist(){
        File f = new File("res/configuration.csv");
        if(f.exists()){
            return true;
        }
        return false;
    }

    private char modification() {
        System.out.println("1 - Erreur d'entré de critére");
        System.out.println("2 - Erreur d'entrée pour les animaux");
        System.out.println("3 - Erreur d'entrée pour la nourriture");
        System.out.println("4 - Changer de fichier d'adolescent");
        System.out.println("5 - Changer de fichier d'appariemment");
        System.out.println("\t (b) - Retour au menu principal");
        System.out.println("\t (q) - Quitter l'application");
        char c = SaisieClavier.saisieClavierChar();
        return c;
    }

    private static char gestionEleve() {
        System.out.println("1 - Ajouter un Etudiant manuellement à la liste");
        System.out.println("2 - Ajouter un Enssemble d'étudiant à partir d'un fichier CSV");
        System.out.println("3 - Afficher les étudiants");
        System.out.println("4 - Sauvegarder les étudiants");
        System.out.println("\t (b) - Retour au menu principal");
        System.out.println("\t (q) - Quitter l'application");
        char c = SaisieClavier.saisieClavierChar();
        return c;
    }

    public static char gestionAppariement() {
        System.out.println("1 - Ajouter un Appariement manuellement à la liste");
        System.out.println("2 - Générer les appariements à l'aide de Graph");
        System.out.println("3 - Afficher les appariements");
        System.out.println("4 - importer des appariements");
        System.out.println("5 - Sauvegarder les appariements");
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
        System.out.println("3 - Consulter l'historique");
        System.out.println("4 - Option");
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
        requirements.put(CriterionName.HOST_FOOD.name(), new Criterion(SaisieClavier.saisieYesOrNo() == 'y' ? "yes" : "no" , CriterionName.HOST_FOOD));
        System.out.println("L'étudiant peut-il cuisiner pour des personnes allergiques aux noix ? (y/n)");
        requirements.put(CriterionName.HOST_FOOD.name(), new Criterion(SaisieClavier.saisieYesOrNo() == 'y' ? "yes" : "no" , CriterionName.HOST_FOOD));
        System.out.println("Avec quel genre l'étudiant souhaite être ? (male/female/other)");
        requirements.put(CriterionName.PAIR_GENDER.name(), new Criterion(SaisieClavier.saisieGenre(), CriterionName.PAIR_GENDER));
        System.out.println("L'étudiant souhaite-t-il être avec son ancienne affectation ? (y/n)");
        if (SaisieClavier.saisieYesOrNo() == 'y') {
            System.out.println("Quel est l'ID de son ancienne affectation ?");
            lastAffectation = SaisieClavier.saisieClavierTeenager();
        }
        return new Teenager(nom, prenom, dateNaissance, pays, requirements, lastAffectation);
    }

    private static Teenager createTeenagerManuallyByCountry(Country c) {
        System.out.println("L'ajout manuel d'un étudiant");
        String nom;
        String prenom;
        LocalDate dateNaissance;
        Country pays = c;
        Map<String, Criterion> requirements = new HashMap<String, Criterion>();
        Teenager lastAffectation = null;

        System.out.println("Nom : ");
        nom = SaisieClavier.saisieClavierStr();
        System.out.println("Prénom : ");
        prenom = SaisieClavier.saisieClavierStr();
        System.out.println("Date de naissance : (format : yyyy-mm-dd)");
        dateNaissance = SaisieClavier.saisieClavierDate();
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
        requirements.put(CriterionName.HOST_FOOD.name(), new Criterion(SaisieClavier.saisieYesOrNo() == 'y' ? "yes" : "no" , CriterionName.HOST_FOOD));
        System.out.println("L'étudiant peut-il cuisiner pour des personnes allergiques aux noix ? (y/n)");
        requirements.put(CriterionName.HOST_FOOD.name(), new Criterion(SaisieClavier.saisieYesOrNo() == 'y' ? "yes" : "no" , CriterionName.HOST_FOOD));
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
            platform.setTeenagerList(Platform.importListTeenagers(new File(path)));
        } catch (FileNotFoundException e) {
            System.out.printf("Erreur lors de l'importation du fichier [%s] n'est pas un fichier CSV ou n'existe pas.\n", path);
        }
        System.out.printf("Les étudiants du fichier [%s] ont été ajouter avec succès\n", path);
    }

    private static void addToPlatformbyCompatibleCSV(String path) {
        try {
            platform.setCompatibleTeenagers(Platform.importCompatibleTeenagers(new File(path)));
        } catch (FileNotFoundException e) {
            System.out.printf("Erreur lors de l'importation du fichier [%s] n'est pas un fichier CSV ou n'existe pas.\n", path);
        }
        System.out.printf("Les étudiants du fichier [%s] ont été ajouter avec succès\n", path);
    }

    //////////////////////////////// Gestion des appariements ////////////////////////////////

    /*private static void appariement() {
        Teenager teenager1;
        Teenager teenager2;
        do {
            System.out.println("ID de l'étudiant 1 : ");
            teenager1 = SaisieClavier.saisieClavierTeenager();
            System.out.println("ID de l'étudiant 2 :");
            teenager2 = SaisieClavier.saisieClavierTeenager();
            if (teenager1.equals(teenager2)) System.out.println("Les deux étudiants sont les mêmes, veuillez recommencer.");
        } while (teenager1.equals(teenager2));

    }

    private static void afficherAppariement() {
        System.out.println("Voici les appariements :");
        for(Teenager a: platform.getCompatibleTeenagers().keySet()){
            System.out.println(a.teenagerToString()+";"+platform.getCompatibleTeenagers().get(a).teenagerToString()+"\n");
        }
    }*/

    //////////////////////////////// PARAMETTRE ////////////////////////////////
    private static void parametre() {
        do {
            System.out.print("Veuillez choisir un Pays d'accueil : ");
            for (Country country : CountryList) System.out.print(country.name() + "/");
            System.out.println();
            
            countryHote = SaisieClavier.saisieClavierCountry();
            CountryList.remove(countryHote);
            System.out.print("Veuillez choisir un Pays d'invité : ");
            for (Country country : CountryList) System.out.print(country.name() + "/");
            System.out.println();
            countryVisiteur = SaisieClavier.saisieClavierCountry();
            if (countryHote.equals(countryVisiteur)) System.out.println("Les deux pays sont les mêmes, veuillez recommencer.");
        } while (countryHote == countryVisiteur);
    }
    
    public void runRoot() throws IOException{
        String saisie;
        parametre();
        if(configExist()){
            platform.fichierConfig();
        }else{
            platform.sansFichierConfig();
        }
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
                System.out.println("Vous avez choisi d'ajouter des eleves à la liste à partir d'un fichier CSV");
                addToPlatformbyCSV(SaisieClavier.saisieClavierStr());
                System.out.println("→ Retour au menu principal");
            }

            ////// Affichage des élèves //////
            if (saisie.equals("ge3")) {
                System.out.println("Vous avez choisi d'afficher les élèves");
                System.out.println(platform.toStringTeengarderList()); 
                System.out.println("Retour au menu principal");
            }
            ////// exporter des étudiants //////
            if (saisie.equals("ge4")) {
                System.out.println("Vous avez choisi de sauvegarder les élèves");
                System.out.println("Donner le chemin vers pour le fichier de sauvegarder");
                Platform.exportTeenagers(platform.getTeenagerArrayList(), SaisieClavier.saisieClavierStr());
                System.out.println("Retour au menu principal");
            }

            //////////////////////////// Gestion des affectations ////////////////////////////

            if (saisie.equals("2")) {
                System.out.println("Vous avez choisi La gestion des appariements");
                saisie = "ga" + gestionAppariement();
            }
            ////// ajouter appariements //////
            if (saisie.equals("ga1")) {
                System.out.println("Vous avez choisi d'affecter manullement 2 étudiants");
                System.out.println(platform.getCompatibleTeenagers().toString());
                System.out.println("→ Retour au menu principal");
            }
            ////// générer appariements //////
            if (saisie.equals("ga2")) {
                System.out.println("Vous avez choisi de générer automatiquement les appariements :");
                do {
                    if (platform.getNbCountry(countryHote) != platform.getNbCountry(countryVisiteur)) {
                        System.out.println("La génération est impossible ! Puisque le nombre d'étudiant hôte n'est pas égal au nombre d'étudiants Visteur ! " + platform.getNbCountry(countryHote)  + "(" + countryHote.getCOUNTRY_NAME() + ") /" + platform.getNbCountry(countryVisiteur) + "(" + countryVisiteur.getCOUNTRY_NAME() + ")");
                        if (platform.getNbCountry(countryHote) > platform.getNbCountry(countryVisiteur)) {
                            System.out.println("Veuillez ajouter un étudiant (nombre impair !) " + countryVisiteur.getCOUNTRY_NAME() + " : ");
                            platform.addTeenager(createTeenagerManuallyByCountry(countryVisiteur));
                        } else {
                            System.out.println("Veuillez ajouter un étudiant (nombre impair !) " + countryHote.getCOUNTRY_NAME() + " : ");
                            platform.addTeenager(createTeenagerManuallyByCountry(countryHote));
                        }
                    }
                } while (platform.getNbCountry(countryHote) == platform.getNbCountry(countryVisiteur));
                platform.setCompatibleTeenagers(AffectationUtil.listAreteToListTeen(AffectationUtil.affectation(platform.getTeenagerList(), countryVisiteur, countryHote)));
                System.out.println("→ Retour au menu principal");
            }
            ////// Affichage des appariements //////
            if (saisie.equals("ga3")) {
                System.out.println("Vous avez choisi d'afficher les appariements");
                System.out.println(platform.toStringCompatibleTeenagers());
                System.out.println("→ Retour au menu principal");
            }
            ////// importer appariements //////
            if (saisie.equals("ga4")) {
                System.out.println("Vous avez choisi d'ajouter des appariements à partir d'un fichier CSV");
                addToPlatformbyCompatibleCSV(SaisieClavier.saisieClavierStr());
                System.out.println("→ Retour au menu principal");
            }
            ////// exporter appriements //////
            if (saisie.equals("ge5")) {
                System.out.println("Vous avez choisi de sauvegarder les appariements");
                System.out.println("Donner le chemin vers pour le fichier de sauvegarder");
                Platform.exportCompatibleTeenager(platform.getCompatibleTeenagers(), SaisieClavier.saisieClavierStr());
                System.out.println("Retour au menu principal");
            }
            ////// Ajout d'une affectation à la liste //////
            if (saisie.equals("ga1")) {

            }

            //////////////////////////// Gestion de l'historique ////////////////////////////
            if (saisie.equals("3")) saisie = "gh" + gestionHistorique();

            //////////////////////////// Option ////////////////////////////
            if (saisie.equals("4")){
                System.out.println("Vous avez choisi les options");
                if(configExist()){
                    saisie = "op" + option();
                }else{
                    saisie = "so" + sansFichier();
                }
            }

            if (saisie.equals("so1")) {
                System.out.println("Vous avez choisi de créer le fichier de configuration");
                try (FileInputStream fis = new FileInputStream("res/defaultconfig.csv");
                    FileOutputStream fos = new FileOutputStream("res/configuration.csv")) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                    System.out.println("La copie du fichier a été créée avec succès.");
                    } catch (IOException e) {
                        System.out.println("Une erreur s'est produite lors de la copie du fichier : " + e.getMessage());
                    }
            }

            ////// Modifier le fichier //////
            if (saisie.equals("op1")) {
                System.out.println("Vous avez choisi de modifier le fichier");
                
                saisie = "op1" + modification();
            }

            if (saisie.equals("op11")) {
                System.out.println("Vous avez choisi de modifier le paramètre conncernant les erreurs d'entrées ");
                
                
            }

            if (saisie.equals("op12")) {
                System.out.println("Vous avez choisi de modifier le paramètre conncernant les erreurs d'entrées pour les animaux");
                
               
            }

            if (saisie.equals("op13")) {
                System.out.println("Vous avez choisi de modifier le paramètre conncernant les erreurs d'entrées pour la nourriture");
                
                
            }

            if (saisie.equals("op14")) {
                System.out.println("Vous avez choisi de changer de fichier d'adolescent");
                
               
            }

            if (saisie.equals("op15")) {
                System.out.println("Vous avez choisi de changer le fichier d'appariemment");
                
            }
            ////// Supprimer le fichier //////
            if (saisie.equals("op2")) {
                System.out.println("Vous avez choisi de supprimer le fichier");
                if (configExist()) {
                    System.out.println("Voulez-vous vraiment supprimer le fichier ? (y/n)");
                    char c = SaisieClavier.saisieClavierChar();
                    if (c == 'y') {
                        File file = new File("res/configuration.csv");
                        boolean deleted = file.delete();
                        if (deleted) {
                            System.out.println("Le fichier a été supprimé.");
                        } else {
                            System.out.println("Impossible de supprimer le fichier.");
                        }
                    } else {
                        System.out.println("Le fichier n'a pas été supprimé.");
                    }
                } else {
                    System.out.println("Le fichier n'existe pas. Retour au menu principal.");
                }
            }

            //////////////////////////// Retour au Menu principal ////////////////////////////
            if (saisie.equals("b")) {
                System.out.println("Vous avez choisi de retourner au menu principal");
            }

            if (saisie.equals("q")) {
                System.out.println("Vous avez choisi de quitter le programme");
                isRunning = false;
            }
        } 
    }

    public static void main(String[] args) throws IOException {
        TableauDeBordRoot main = new TableauDeBordRoot();
        System.out.println("Bienvenue dans le programme de gestion des appariements");
        main.runRoot();
    }


    public static void supprimerTeenagerFromCSV(Teenager teen) throws IOException{
        Platform platform = new Platform();
        try {
            platform.importListTeenagers(new File("res/Teenagers.csv"));   
        } catch (Exception e) {
            System.out.println("Erreur lors de l'importation du fichier");
        }
        platform.removeTeenager(teen);
        try {
            Platform.exportTeenagers(platform.getTeenagerArrayList(),"res/Teenagers.csv");    /////////////////////// Je sais pas comment faire pour que ça marche mais vous avez l'idée
        } catch (FileNotFoundException e) {
            System.out.println("Erreur lors de l'exportation du fichier");
        }
    }

    public static void supprimerTeenager(Platform platform) throws IOException{
        System.out.println("Voici la liste des étudiants : ");
        System.out.println(platform.toStringTeengarderList());
        System.out.println("Veuillez saisir l'id de l'étudiant à supprimer : ");
        int id = SaisieClavier.saisieClavierInt();
        Teenager teen = platform.getTeenagerFromID(id);
        supprimerTeenagerFromCSV(teen);
    }

    public char option() throws IOException{
        System.out.println("il existe un fichier de configuration, voulez vous :");
        System.out.println("1 - modifier le fichier");
        System.out.println("2 - supprimer le fichier");
        System.out.println("\t (b) - Retour au menu principal");
        System.out.println("\t (q) - Quitter l'application");
        char c = SaisieClavier.saisieClavierStr().charAt(0);
        return c;
    }

    public char sansFichier() throws IOException{
        System.out.println("Il n'existe pas de fichier de configuration. Voulez-vous crée un fichier de configuration par défault ?");
        System.out.println("1 - crée le fichier");
        System.out.println("\t (b) - Retour au menu principal");
        System.out.println("\t (q) - Quitter l'application");
        char c = SaisieClavier.saisieClavierStr().charAt(0);
        return c;
    }

    /*if(configExist()){
            do{
                        System.out.println("1 - Erreur d'entré de critère");
        System.out.println("2 - Erreur d'entré pour les animaux");
        System.out.println("3 - Erreur d'entré pour la nourriture");
        System.out.println("4 - Changer de fichier d'adolescent");
        System.out.println("5 - Changer de fichier d'appariemment");
                S
                saisie = sc.next();spain
            }while(!saisie.equals("1") && !saisie.equals("2") && !saisie.equals("3"));
            if (saisie.equals("1")){
                Platform.changeFichierConfig();
            }else if(saisie.equals("2")){
                Platform.deleteFichierConfig();
            }
        }else{
            do{
                System.out.println("Il n'existe pas de fichier de configuration. Voulez-vous crée un fichier de configuration par défault ?");
                System.out.println("[y/n]");
                saisie = sc.next();
            }while(!saisie.equals("y") || !saisie.equals("n"));
            if (saisie.equals("y")){
                Platform.createFichierConfig();
            }
        }

        System.out.println("Veuillez choisir la pondération des critères de préférence");

        try {
            AffectationUtil.multiplicateur_pref = SaisieClavier.saisieClavierInt();

        } catch (Exception e) {
            System.out.println("Erreur de saisie, veuillez recommencer");
        }

        System.out.println("Les pondération ont été fixer");
    }*/

    public void entryError(){
        String saisie,type;
        do{
            System.out.println("Si vous voulez activer le controle des entrées, appuyer sur 'y', ou sur 'n' dans le cas contraire:");
            saisie = sc.next();
        } while(!saisie.equals("y") && !saisie.equals("n"));
        if (saisie.equals("y")) {
            type = "true";
        } else {
            type = "false";
        }
    }

    public void animalError(){
        String saisie,animal;
        do{
            System.out.println("Si vous voulez activer le controle des Animaux, appuyer sur 'y', ou sur 'n' dans le cas contraire:");
            saisie = sc.next();
        }while(!saisie.equals("y") && !saisie.equals("n"));
        if (saisie.equals("y")){
            animal = "true";
        }else{
            animal = "false";
        }
    }

    public void foodError(){
        String saisie,food;
        do{
            System.out.println("Si vous voulez activer le controle de la nourriture, appuyer sur 'y', ou sur 'n' dans le cas contraire:");
            saisie = sc.next();
        }while(!saisie.equals("y") && !saisie.equals("n"));
        if (saisie.equals("y")){
            food = "true";
        }else{
            food = "false";
        }
    }

    public void listPath(){
        String saisie,liste;
        File f;
        boolean chemin;
        do{
            if(chemin == true){
                System.out.println("Erreur, le fichier donné ne peut pas être lu. ");
            }else{
                chemin = true;
            }
                        
            System.out.println("Entrer le chemin vers le fichier d'importation: ");
            saisie = sc.next();
            f = new File(saisie);
        }while(f.canRead());
            liste = saisie;
        if (saisie.equals("y")){
            liste = saisie;
        }else{
            liste = "false";
        }

    public void mapPath(){
        String saisie,map;
        File f;
        boolean chemin;
        do{
            if(chemin == true){
                System.out.println("Erreur, le fichier donné ne peut pas être lu. ");
            }else{
                chemin = true;
            }

        System.out.println("Entrer le chemin vers le fichier d'importation: ");
        saisie = sc.next();
        f = new File(saisie);
        }while(f.canRead());
        if (saisie.equals("y")){
            map = saisie;
        }else{
            map = "false";
        }
    } 

}
