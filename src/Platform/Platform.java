package Platform;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.module.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Criterion.CriterionTypeException;
import Tennager.Teenager;

import java.util.HashMap;

/**
 * Cette classe permet de créer une plateforme contenant des adolescents et de les comparer entre eux.
 * @since 1.0
 * @version 1.0
 * @author Dorny Nathan
 * @author Berrakane Adham
 * @author Moutté Quentin
 */
public class Platform {

    private ArrayList<Teenager> teenagerList;
    private Map<Teenager, Teenager> compatibleTeenagers;

    /**
     * Constructeur de la classe Platform
     */
    public Platform() {
        this.teenagerList = new ArrayList<>();
        this.compatibleTeenagers = new HashMap<>();
    }

    /**
     * Méthode qui ajoute un adolescent à la plateforme (à la liste des adolescents)
     * @param teenager l'adolescent à ajouter
     * @return true si l'adolescent a été ajouté, false sinon
     */
    public boolean addTeenager(Teenager teenager){
        if (teenagerList.contains(teenager)) {
            return false;
        }
        return teenagerList.add(teenager);  
    }

    /**
     * Méthode qui supprime un adolescent de la plateforme (de la liste des adolescents)
     * @param teenager l'adolescent à supprimer
     * @return true si l'adolescent a été supprimé, false sinon
     */
    public boolean removeTeenager(Teenager teenager){
        return teenagerList.remove(teenager);
    }

    public boolean removeCompatibleTeenager(Teenager host,Teenager visitor){
        return compatibleTeenagers.remove(host,visitor);
    }

    /**
     * Méthode qui renvois l'indice d'un teenager dans la liste des adolescents
     * @param index index demandé
     * @return true si l'adolescent a été supprimé, false sinon
     */
    public Teenager getIndexFromTeenager(int index){
        for (Teenager teenager : teenagerList) {
            if (teenager.getId() == index) {
                return teenager;
            }
        }
        return null;
    }

    /**
     * Methode qui regarde si 2 adolescent sont compatible entre eux (Allergie, alimentation...) et les ajoute dans une map
     */
    public void findCompatibleTeenagers(){
        for (Teenager teenager : teenagerList) {
            for (Teenager guest : teenagerList) {
                System.out.println("Comparaison de " + teenager.getName() + " avec " + guest.getName());
                if (teenager.compatibleWithGuest(guest)) {
                    compatibleTeenagers.put(teenager, guest);
                } else {
                    System.out.println("Les deux adolescents ne sont pas compatibles");
                }
            }
        }
    }

    public boolean containsSame(String name, String forename) {
        for (Teenager teenager : teenagerList) {
            if (teenager.getName().equals(name) && (teenager.getForname().equals(forename))) return true;
        }
        return false;
    }

    /** 
     * Methode qui supprime les adolescents qui n'ont pas de saisies valides
     */
    public void purgeInvalidRequirements(){
        for (Teenager teenager : teenagerList) {
            teenager.purgeInvalidRequirement();
        }
    }

    public void purgeInvalidAnimalRequirements() throws CriterionTypeException{
        for (Teenager teenager : teenagerList) {
            teenager.purgeInvalidAnimalRequirement();
        }
    }

    public void purgeInvalidFoodRequirements() throws CriterionTypeException{
        for (Teenager teenager : teenagerList) {
            teenager.purgeInvalidFoodRequirement();
        }
    }

    /**
     * Methode qui affiche les adolescents compatibles entre eux (temporaire)
     */
    public void printCompatibleTeenagers(){
        System.out.println("->>>> final : ");
        for (Map.Entry<Teenager, Teenager> entry : compatibleTeenagers.entrySet()) {
            System.out.printf("[%s] peut aller chez %s\n", entry.getKey().getName(), entry.getValue().getName());
        }
    }

    /**
     * Getter qui renvoie les adolescents de la plateforme
     * @return une liste d'adolescents
     */
    public List<Teenager> getTeenagerList() {
        return teenagerList;
    }

    public ArrayList<Teenager> getTeenagerArrayList() {
        return teenagerList;
    }

    /**
     * Setter qui modifie la liste des adolescents de la plateforme
     * @param teenagerList la nouvelle liste d'adolescents
     */
    public void setTeenagerList(ArrayList<Teenager> teenagerList) {
        this.teenagerList = teenagerList;
    }

    /**
     * Methode qui retourne les adolescents compatibles entre eux sous forme d'une MAP
     * @return une map d'adolescents
     */
    public Map<Teenager, Teenager> getCompatibleTeenagers() {
        return compatibleTeenagers;
    }

    /**
     * Setter qui définit la map des adolescents compatibles entre eux
     * @param compatibleTeenagers la nouvelle map d'adolescents
     */
    public void setCompatibleTeenagers(Map<Teenager, Teenager> compatibleTeenagers) {
        this.compatibleTeenagers = compatibleTeenagers;
    }

    /**
     * Méthode qui permet d'importer des adolescents
     * @param CSV le fichier CSV contenant les adolescents
     * @return une liste de tous les adolescents
     */
    public static ArrayList<Teenager> importListTeenagers(File CSV) throws FileNotFoundException {
        Scanner scan = new Scanner(CSV);
        scan.useDelimiter("\n");
        ArrayList<Teenager> list = new ArrayList<Teenager>();
        int i = 1;
        String pattern = scan.next();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            if (!line.isEmpty()) { 
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");
                if (lineScanner.hasNext()) {
                    String csvData = lineScanner.next();
                    list.add(new Teenager(line, i, pattern));
                    i++;
                }
                lineScanner.close();
            }
        }
        scan.close();
        return list;
    }


    /**
     * Méthode qui permet d'importer des couples adolescents
     * @param CSV le fichier CSV contenant les adolescents
     * @return une liste de tous les adolescents
     */
    public static  Map<Teenager,Teenager> importCompatibleTeenagers(File CSV) throws FileNotFoundException{
        Scanner scan = new Scanner(CSV);
        scan.useDelimiter("\n");
        Map<Teenager,Teenager> compatible = new HashMap<Teenager,Teenager>();
        int i=1;
        Teenager ten1;
        Teenager ten2;
        String ten1String;
        String ten2String;
        String pattern =scan.next();
        while(scan.hasNextLine()){
            ten1String = "" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";";
            ten2String = "" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";" + scan.next() + ";";
            ten1 = new Teenager(ten1String,i,pattern);
            ten2 = new Teenager(ten2String,i,pattern);
            compatible.put(ten1, ten2);
            scan.nextLine();
            i++;
        }
        scan.close();
        return compatible;
    }

    /**
     * Méthode qui permet d'exporter des adolescents
     * @param CSV la liste des adolescents
     * @param fichier le chemin du fichier où stockée les étudiants
     */
    public static void exportTeenagers(ArrayList<Teenager> CSV, String fichier) throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))){
            for(Teenager a: CSV){
                bw.write(a.teenagerToString());
                bw.newLine(); 
            }
        }catch(IOException e) {
                System.out.println("Writing error: " + e.getMessage());
                e.printStackTrace();
        }
    }

    public static void exportTeenagersString(List<String> CSV, String fichier) throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))){
            for(String a: CSV){
                bw.write(a+"\n");
                bw.newLine();
            }
        } catch (IOException e) {
                System.out.println("Writing error: " + e.getMessage());
                e.printStackTrace();
        }
    }

    public static void exportCompatibleTeenager(Map<Teenager,Teenager> CSV, String fichier) throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))){
            for(Teenager a: CSV.keySet()){
                bw.write(a.teenagerToString()+";"+CSV.get(a).teenagerToString()+"\n");
                bw.newLine();
            }
        }catch(IOException e) {
                System.out.println("Writing error: " + e.getMessage());
                e.printStackTrace();
        }
    }

    public String toStringTeengarderList() {
        String str = "";
        for (Teenager teenager : this.teenagerList) {
            str += teenager.toString() + "\n";
        }
        return str;
    }

    public static void main(String[] args) {
        Platform platform = new Platform();
        try {
            ArrayList<Teenager> list = importListTeenagers(new File("res/teenagerList.csv"));
            platform.setTeenagerList(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (Teenager teenager : platform.getTeenagerList()) {
            System.out.println(teenager.getName());
        }
    }


    public void fichierConfig() throws FileNotFoundException, CriterionTypeException{
        File f = new File("res/Configuration.csv");
        Scanner scan = new Scanner(f);
        scan.next();
        scan.useDelimiter(";");
        if(scan.next().equals("true")){
            this.purgeInvalidRequirements();
        }
        if(scan.next().equals("true")){
            this.purgeInvalidAnimalRequirements();
        }
        if(scan.next().equals("true")){
            this.purgeInvalidFoodRequirements();
        }
        String listPath = scan.next();
        File f1,f2;
        if(!listPath.equals("false")){
            try {
                f1 = new File(listPath);
                this.importListTeenagers(f1);
            } catch (Exception e) {
                throw new CriterionTypeException("Erreur fichier.");
            }
        }
        String compatiblePath = scan.next();
        if(!compatiblePath.equals("false")){
            try {
                f2 = new File(compatiblePath);
                this.importListTeenagers(f2);
            } catch (Exception e) {
                throw new CriterionTypeException("Erreur fichier.");
            }
        }
    }

}