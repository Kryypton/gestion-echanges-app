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

    public History() {
        this.history = new HashMap<>();
    }

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

    public boolean wantTheSame(Teenager teen) {
        Criterion historyCriterion = teen.getHistory();
        if (historyCriterion != null) {
            return historyCriterion.getValue().equals("same");
        }
        return false;
    }

    public Map<Integer, List<String>> getHistory() {
        return this.history;
    }

    public List<String> getList(int year) {
        return history.get(year);
    }

    public void addToHistory(int year, List<String> teenagers) {
        history.put(year, teenagers);
    }

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
