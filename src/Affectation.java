import java.io.*;
import java.util.*;

import fr.ulille.but.sae2_02.graphes.Arete;

public class Affectation implements Serializable {
    private Map<Teenager , Teenager> affectationsHistory;
    private Country Host;
    private Country Visitor;

    public Affectation() { // Création de la hashmap de Teenagers
        this.affectationsHistory = new HashMap<Teenager , Teenager>();
    }

    // Création de la hashmap de Teenagers et les arretes qui vont avec entre les Teenager.
    public Affectation(List<Arete<Teenager>> aretes){
        this(aretes , null, null);

    }

    public Affectation(List<Arete<Teenager>> aretes, Country Host, Country Visitor){
        this();
        for(Arete<Teenager> arete : aretes){
            this.affectations(arete.getExtremite1() , arete.getExtremite2());
        }
        this.Host = Host;
        this.Visitor = Visitor;

    }

    public Country getHost(){
        return this.Host;
    }

    public Country getVisitor(){
        return this.Visitor;
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
    public Teenager getLast( Teenager t){
        return this.affectationsHistory.get(t);
    }

    // renvoie true si un teenager est affecter a un teenager.
    public boolean estAffecter(Teenager t){
        if(this.affectationsHistory.containsKey(t)){
            return true;
        }
        return false;
    }

    
    public boolean estAffecter(Teenager t1 , Teenager t2){
        if(this.affectationsHistory.containsKey(t1) && this.affectationsHistory.get(t1) == t2){
            return true;
        }
        return false;
    }

    // retourne tout les associations 
    public Map<Teenager, Teenager> getAssociations() {
        return this.affectationsHistory ;
    }

    /**
     * Sauvegarde l'historique dans un fichier en utilisant la sérialisation binaire
     * @param filename Le nom du fichier de sauvegarde
     */
    public void saveHistory(String filename) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(affectationsHistory);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Historique sauvegardé ");
        } catch (IOException e) {
            System.out.println("erreur lors de la sauvegarde de l'historiqu" + e.getMessage());
        }
    }
    

    /**
     * Charge l'historique à partir d'un fichier de sauvegarde en utilisant la désérialisation binaire
     * @param filename Le nom du fichier de sauvegarde
     */

    public void loadHistory(String filename) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Map<Teenager, Teenager> history = (Map<Teenager, Teenager>) objectInputStream.readObject();
            affectationsHistory.putAll(history); // Utiliser putAll pour copier les éléments dans affectationsHistory
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("historique chargé");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("erreur lors du chargement de l'historique: " + e.getMessage());
        }
    }

    public int compatibleWishGender(Teenager host, Teenager other) {
        try {
            if (host.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue().equals(other.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue()) ||  other.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue().equals(host.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue())) {
                if(host.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue().equals(other.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue()) && other.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue().equals(host.getRequirements().get(CriterionName.PAIR_GENDER.name()).getValue())) {
                    return -10;
                } else return -5; 
            }
            return 0;
        } catch (NullPointerException e) {
            return -10;
        }
    }


    // Methode qui permet de réevaluer le poids d'un arrete en fonction de l'historique des Teenagers.
    public int historyTeenager(Teenager host , Teenager visitor){
        if(estAffecter(host)){
            if(host.getRequirements().containsKey("HISTORY")){
                if(this.getLast(host).equals(visitor)){
                    if(host.getCriterion(CriterionName.HISTORY).equals(null) || visitor.getCriterion(CriterionName.HISTORY).equals(null) ){
                        return -100;
                    }
                    if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same") && visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same") ){
                        return -100;
                    }
                    if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same")){
                        return -50;
                    }
                    if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("other") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("other")){
                        return 50;
                    }
                }
            }else{
                return -100;
            }
        }else if(estAffecter(visitor)){
            if(visitor.getRequirements().containsKey("HISTORY")){
                if(this.getLast(visitor).equals(host)){
                    if(host.getCriterion(CriterionName.HISTORY).equals(null) || visitor.getCriterion(CriterionName.HISTORY).equals(null) ){
                        return -100;
                    }
                    if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same") && visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same")){
                        return -100;
                    }
                    if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("same")){
                        return -50;
                    }
                    if(host.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("other") || visitor.getCriterion(CriterionName.HISTORY).equalsIgnoreCase("other")){
                        return 50;
                    }
                }
            }else{
                return -100;
            }
        }
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!(this.getHost() == this.getVisitor() && this.getHost()==null)) sb.append(this.getHost() + " - " + this.getVisitor() + "\n");
        for (Map.Entry<Teenager, Teenager> entry : affectationsHistory.entrySet()) {
            sb.append(entry.getKey().getName().toString() + " -> " + entry.getValue().getName().toString() + "\n");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((affectationsHistory == null) ? 0 : affectationsHistory.hashCode());
        result = prime * result + ((Host == null) ? 0 : Host.hashCode());
        result = prime * result + ((Visitor == null) ? 0 : Visitor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Affectation other = (Affectation) obj;
        if (affectationsHistory == null) {
            if (other.affectationsHistory != null)
                return false;
        } else if (!affectationsHistory.equals(other.affectationsHistory))
            return false;
        if (Host != other.Host)
            return false;
        if (Visitor != other.Visitor)
            return false;
        return true;
    }

    

}