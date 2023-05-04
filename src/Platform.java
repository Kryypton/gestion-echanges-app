import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class Platform {

    private List<Teenager> teenagerList;
    private Map<Teenager, Teenager> compatibleTeenagers;

    public Platform() {
        this.teenagerList = new ArrayList<>();
        this.compatibleTeenagers = new HashMap<>();
    }

    /* Ajoute des adolescent à la liste */
    public void addTeenager(Teenager teenager){
        teenagerList.add(teenager);
    }

    /* Regarde si 2 adolescent sont compatible entre eux (Allergie, alimentation...) 
     * et les ajoute à la Map compatibleTeenagers
    */
    public void findCompatibleTeenagers(){
        for (Teenager teenager : teenagerList) {
            for (Teenager guest : teenagerList) {
                if (teenager.compatibleWithGuest(guest)) {
                    compatibleTeenagers.put(teenager, guest);
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
        for (Map.Entry<Teenager, Teenager> entry : compatibleTeenagers.entrySet()) {
            System.out.println(entry.getKey().getName() + " compatible " + entry.getValue().getName());
        }
    }


    /* A FAIRE  
     * - Classe de Test compléte (Je ne sais pas si elle est complete mais je précise)
     * - calcul des affinités (apparament compatibleWithGuest se concentre sur l'allergie des animaux)
     * - la suppression des adolescent
     * - Je crois que nous devions faire une classe country.
     * - gestion des régles spécifique par pays
     */

}