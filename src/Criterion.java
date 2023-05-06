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
     * @return true si le critére est valide, false sinon
     */
    public boolean isValid() {
        if (label == null) {
            return false;
        }
    
        if (label.getType() == 'B') {
            return value.equals("yes") || value.equals("no");
        } else if (label.getType() == 'N') {
            try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else if (label.getType() == 'D') {
            try {
                LocalDate.parse(value);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        } else if (label.getType() == 'T') {
            if (label.name().equals("GUEST_FOOD") || label.name().equals("HOST_FOOD")) {
                return (value.equals("végétarien") || value.equals("sport") || value.equals("nonuts") || value.equals("none"));
            } else if (label.name().equals("GENDER")) {
                return (value.equals("M") || value.equals("F") || value.equals("O"));
            } else {
                return false;
            }
        }
        return false;
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