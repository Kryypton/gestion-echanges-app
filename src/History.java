package src;

import fr.ulille.but.sae2_02.graphes.Arete;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;


/*Rappel de Adham : Cette classe doit permettre par rapport a l'historique des anciennes liaison de 
Teenager de créer une nouvelle regle de calcule du poids d'un arrete.*/ 

// Ce qu'il manque : 

// Une methode qui importe une liste de Teenager qui ont deja été correspondant.
// Une methode qui exporte une liste de Teenager qui ont deja été correspondant.

/* Et enfin une methode que l'on ajoutera a AffectationVersion1.java dans la methode weight.
Cette methode devra retourner un int , elle permttra de voir si un Teenager a des preferences ou 
contrainte par rapport a l'historique de l'autre Teenager 

Pour moi******
2 Teenager qui ont deja été ensemble mais que essemble  = -100 
2 Teenager n'ont jamais été ensemble = 0 
2 Teenager qui ont deja été ensemble mais aussi avec d'autre = -50
*/ 

// si vous avez des ammelioration mettez les.


public class History {
    private Map<Teenager , Teenager> affectationsHistory;
    // private Map<Integer, List<String>> history;



    public History() { // Création de la hashmap de Teenagers
        this.history = new HashMap<Teenager , Teenager>();
    }

    // Création de la hashmap de Teenagers et les arretes qui vont avec entre les Teenager.
    public History(List<fr.ulille.but.sae2_02.graphes.Arete<Teenager>> aretes){
        this();
        for(Arete<Teenager> arete : aretes){
            this.affectations(arete.getExtremite1() , arete.getExtremite2());
        }
    }


    // Enleve le teenager courant qui est avec un autre.
    public void desaffectations(Teenager t){
        this.affectationsHistory.remove(t);
    }

    // Affecte 2 Teenagers
    public void affectations(Teenager t1 , Teenager t2){
        this.affectationsHistory.put(t1 , t2);
    }

    // Retourne le Teenager associer au Teenager courant.
    public Teenager get( Teenager t){
        return this.affectation.get(t);
    }

    // renvoie true si un teenager est affecter a un teenager.
    public boolean estAffecter(Teenager t){
        if(this.affectation.containsKey(t)){
            return true;
        }
        return false;
    }

    /* 
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
     */

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
