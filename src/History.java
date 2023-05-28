import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;

public class History {
    
    private Map<Integer, List<String>> history;

    public History(Map<Integer, List<String>> history) {
        this.history = history;
    }
    /**
     * Constructeur de la classe History
     * @param history l'historique des adolescents
     * @since 1.0
     * @version 1.0
     */
    public History() {
        this.history = new HashMap<>();
    }

    /**
     * Méthode qui regarde si un adolescent a déjà été avec un autre adolescent
     * @param teen l'adolescent à regarder
     * @param year l'année à regarder
     * @return le nom de l'adolescent avec qui il a été, ou "" si il n'a pas été avec quelqu'un
     */
    public String hasBeenWith(Teenager teen, int year) {
        List<String> historyForYear = history.get(year);
        if (historyForYear != null) {
            String teenagerName = teen.getName() + " " + teen.getForname();
            if (historyForYear.contains(teenagerName)) {
                return teenagerName;
            }
        }
        return "";
    }

    /**
     * Méthode qui regarde si un adolescent veut la même chose qu'un autre adolescent
     * @param teen l'adolescent à regarder
     * @return true si il veut la même chose, false sinon
     */
    public boolean wantTheSame(Teenager teen) {
        Criterion historyCriterion = teen.getHistory();
        if (historyCriterion != null) {
            return historyCriterion.getValue().equals("same");
        }
        return false;
    }

    /**
     * Méthode qui regarde si un adolescent veut la même chose qu'un autre adolescent
     * @return l'historique des adolescents
     */
    public Map<Integer, List<String>> getHistory() {
        return this.history;
    }

/**
 * Méthode qui regarde si un adolescent veut la même chose qu'un autre adolescent
 * @param year l'année à regarder
 * @return l'historique des adolescents pour l'année donnée
 */
    public List<String> getList(int year) {
        return history.get(year);
    }

    /**
     * Méthode qui regarde si un adolescent veut la même chose qu'un autre adolescent
     * @param year l'année à regarder
     * @param teenagers la liste des adolescents à regarder
     */
    public void addToHistory(int year, List<String> teenagers) {
        history.put(year, teenagers);
    }

    /**
     * Méthode qui regarde si un adolescent veut la même chose qu'un autre adolescent
     * @param year l'année à regarder
     * @param teenagers la liste des adolescents à regarder
     */
    public void addToHistoryWithTeenager(int year, List<Teenager> teenagers) {
        List<String> historyForYear = history.get(year);
        if (historyForYear == null) {
            historyForYear = new ArrayList<>();
            history.put(year, historyForYear);
        }
        
        for (Teenager teen : teenagers) {
            String teenagerName = teen.getName() + " " + teen.getForname();
            historyForYear.add(teenagerName);
        }
    }
    

    public static void main(String[] args) {
        // Création de deux objets Teenager
        Teenager teen1 = new Teenager(1, "teen1prenom", "teen1nom", "M", LocalDate.of(2000, 1, 1), Country.FRANCE);
        Teenager teen2 = new Teenager(2, "teen2prenom", "teen2nom", "F", LocalDate.of(2001, 2, 2), Country.ITALY);
        
        // Création d'un historique vide
        History historyObject = new History();
    
        // Ajout des deux Teenagers à l'historique pour l'année 2019
        List<Teenager> history2019 = new ArrayList<>();
        history2019.add(teen1);
        history2019.add(teen2);
        historyObject.addToHistoryWithTeenager(2019, history2019);
        
        // Obtention de l'historique pour l'année 2019
        List<String> historyForYear = historyObject.getList(2019);
        System.out.println("Historique pour l'année 2019 : " + historyForYear);
    
        // Vérification si teen1 a été avec quelqu'un en 2019
        String hasBeenWith = historyObject.hasBeenWith(teen1, 2019);
        System.out.println("teen1 a été avec : " + hasBeenWith);
    
        // Vérification si teen1 veut se remettre avec la même personne
        boolean wantTheSame = historyObject.wantTheSame(teen1);
        System.out.println("teen1 veut se remettre avec la même personne : " + wantTheSame);
    }
    
    
    
    
}
