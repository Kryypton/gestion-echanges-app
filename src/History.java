import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History {
    
    private Map<Integer, List<String>> history;

    public History(Map<Integer, List<String>> history) {
        this.history = history;
    }

    public History(int year, List<String>Teenager) {
        Map<Integer, List<String>> history = new HashMap<Integer, List<String>>();
        this.history = history;
    }

    /* a faire: sert a determiner avec qui un etudiant a déjà été */
    public String hasBeenWith(Teenager teen, int year){
        for(String a : this.getList(year)){
            if(a.equals(teen.getName()+","+teen.getForname().split(";"))){
                return teen.getName()+","+teen.getForname().split(";");
            }
        }
        return "";
    }

    /* a faire: sert a determiner si un étudiant veut se remettre avec quelqu'un avec qui il a déjà été */
    public boolean wantTheSame(Teenager teen){
        return teen.getHistory().getValue().equals("same");
    }

    public Map<Integer, List<String>> getHistory() {
        return this.history;
    }

    public List<String> getList(int year) {
        return history.get(year);
    }

    // public List<String> getListYear() {
    //     return history.get(year);
    // }

    
}
