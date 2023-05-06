import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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


    /* A FAIRE  
     * - Classe de Test compléte (Je ne sais pas si elle est complete mais je précise)
     * - calcul des affinités (apparament compatibleWithGuest se concentre sur l'allergie des animaux)
     * - la suppression des adolescent
     * - Je crois que nous devions faire une classe country.
     * - gestion des régles spécifique par pays
     */
}