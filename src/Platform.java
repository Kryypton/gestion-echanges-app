import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
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

    /**
     * Methode qui regarde si 2 adolescent sont compatible entre eux (Allergie, alimentation...) et les ajoute dans une map
     */
    public void findCompatibleTeenagers(){
        for (Teenager teenager : teenagerList) {
            for (Teenager guest : teenagerList) {
                System.out.println("Comparaison de " + teenager.getName() + " avec " + guest.getName());
                if (!teenager.equals(guest)) {
                    if (teenager.compatibleWithGuest(guest)) {
                        compatibleTeenagers.put(teenager, guest);
                    }
                } else {
                    System.out.println("Les deux adolescents sont identiques - (findCompatibleTeenagers method)");
                }
            }
        }
    }

    /** 
     * Methode qui supprime les adolescents qui n'ont pas de saisies valides
     */
    public void purgeInvalidRequirements(){
        for (Teenager teenager : teenagerList) {
            teenager.purgeInvalidRequirement();
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
    public static ArrayList<Teenager> importTeenagers(File CSV) throws FileNotFoundException{
        Scanner scan = new Scanner(CSV);
        scan.useDelimiter("\n");
        ArrayList<Teenager> list = new ArrayList<Teenager>();
        int i=1;
        scan.next();
        while(scan.hasNextLine()){
            list.add(new Teenager(scan.next(),i));
            scan.nextLine();
            i++;
        }
        scan.close();
        return list;
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
        
        // A FAIRE exporte les donnée 
    }

    public static void exportTeenagersString(List<String> CSV, String fichier) throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))){
            for(String a: CSV){
                bw.write(a+"\n");
                bw.newLine();
            }
        }catch(IOException e) {
                System.out.println("Writing error: " + e.getMessage());
                e.printStackTrace();
        }
        
        // A FAIRE exporte les donnée 
    }

    public static void exportCompatibleTeenager(Map<Teenager,Teenager> CSV, String fichier) throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fichier))){
            for(Map<Teenager,Teenager> a: CSV){
                bw.write(a.getKey().teenagerToString()+";"+a.getKey().teenagerToString()+"\n");
                bw.newLine();
            }
        }catch(IOException e) {
                System.out.println("Writing error: " + e.getMessage());
                e.printStackTrace();
        }
    }

    // public static void main(String[] args) throws IOException{
    //     File CSV = new File("res/adosAleatoires.csv");
    //     ArrayList<Teenager> list = importTeenagers(CSV);
    //     for(Teenager a: list){
    //         System.out.println(a.teenagerToString()/*getName()+" " + a.getForname() +" " + a.getGender() + " " + a.getBirthDate()*/);
    //     }
    //     exportTeenagers(list,"res/donnéeExporter.csv");

        
    // }
}