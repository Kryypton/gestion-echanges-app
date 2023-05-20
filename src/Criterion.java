//import org.w3c.dom.css.CSSValueList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Cette classe représente un critére, elle est composé d'une valeur et d'un label
 * @since 1.0
 * @version 1.0
 * @author Dorny Nathan
 * @author Berrakane Adham
 * @author Moutté Quentin
 */
public class Criterion{

    private String value;
    private CriterionName label;
    /**
     * Constructeur de la classe Criterion
     * @param value la valeur du critére
     * @param label le label du critére / le nom du critére
     */
    public Criterion(String value , CriterionName label){
        this.value = value;
        this.label = label;
    }

    /**
     * Cette méthode permet de savoir si un critére est valide ou non
     * @return Si le critere n'est pas valide renvoie une exception
     */

    public boolean isValid() throws CriterionTypeException {

        if (label == null) {
            throw new CriterionTypeException("Le label du critère est null");
        }
        if (label.getType() == 'B' && !this.equals("yes") && !this.equals("no")) {
            throw new CriterionTypeException("Le critère qui représente un booléen doit être égal à yes ou no");
        } else if (label.getType() == 'N' && !isNumeric(value)) {
            throw new CriterionTypeException("Le critère qui représente un nombre doit être un nombre");
        } else if (label.getType() == 'D') {
                String[] date = this.value.split("/");
                if(!Criterion.isNumeric(date[0]) || !Criterion.isNumeric(date[1]) || !Criterion.isNumeric(date[2])){
                    throw new CriterionTypeException("Le critère qui représente une date doit être une date");
                }
        } else if (label.getType() == 'T' && !isText(value)) {
            throw new CriterionTypeException("Le critère qui représente du texte doit être du texte");
            } else {
            return true;
            }
        return false;
        }

    
    private static boolean isNumeric(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static boolean isText(String string){
        try {
            Integer.parseInt(string);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Cette méthode permet de récupérer le label du critére
     * @return le label du critére
     */
    public CriterionName getLabel() {
        return label;
    }

    /**
     * Cette méthode permet de récupérer la valeur du critére
     * @return la valeur du critére
     */
    public String getValue(){
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Criterion other = (Criterion) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        if (label != other.label)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Criterion [value=" + value + ", label=" + label + "]";
    }
}