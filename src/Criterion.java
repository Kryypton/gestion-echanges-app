import org.w3c.dom.css.CSSValueList;

public class Criterion{

    private String value;
    private CriterionName label;
    
    public Criterion(String value , CriterionName label){
        this.value = value;
        this.label = label;
    }


    public boolean isValid(){

        if(this.label.getType() == 'B'){
            return this.value.equals("yes") || this.value.equals("no");
        }else if(this.label.getType() == 'T'){
            return //faire equals avec obj string
        }else if(this.label.getType() == 'N'){
            return //faire equals avec obj string
        }else if(this.label.getType() == 'D'){
            return //faire equals avec obj string
        }
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
}