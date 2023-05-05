import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Platform {

    private ArrayList<Teenager> teenagerList;
    private Map<Teenager, Teenager> compatibleTeenagers;

    public Platform() {
        this.teenagerList = new ArrayList<>();
        this.compatibleTeenagers = new HashMap<>();
    }

    /* Ajoute des adolescent à la liste */
    public void addTeenager(Teenager teenager){
        teenagerList.add(teenager);
    }

    public void removeTeenager(Teenager teenager){
        teenagerList.remove(teenager);
    }

    /* Regarde si 2 adolescent sont compatible entre eux (Allergie, alimentation...) 
     * et les ajoute à la Map compatibleTeenagers
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

    /* Purge tous les critéres non valides des adolescent */
    public void purgeInvalidRequirements(){
        for (Teenager teenager : teenagerList) {
            teenager.purgeInvalidRequirement();
        }
    }

    /* Affiche la Map compatibleTeenagers */
    public void printCompatibleTeenagers(){
        System.out.println("->>>> final : ");
        for (Map.Entry<Teenager, Teenager> entry : compatibleTeenagers.entrySet()) {
            System.out.println("[" + entry.getKey().getName() + "] peut aller chez " + entry.getValue().getName());
        }
    }

    public List<Teenager> getTeenagerList() {
        return teenagerList;
    }

    public void setTeenagerList(ArrayList<Teenager> teenagerList) {
        this.teenagerList = teenagerList;
    }

    public Map<Teenager, Teenager> getCompatibleTeenagers() {
        return compatibleTeenagers;
    }

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