//import org.w3c.dom.css.CSSValueList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Criterion{

    private String value;
    private CriterionName label;
    
    public Criterion(String value , CriterionName label){
        this.value = value;
        this.label = label;
    }

    /* Regarde si des critéres donné sont valides ou non */
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
    


    public CriterionName getLabel() {
        return label;
    }

    public String getvalue(){
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