

public class Criterion{

    private String value;
    private CriterionName label;
    
    public Criterion(String value , CriterionName label){
        this.value = value;
        this.label = label;
    }

    public boolean isValid(){

        if(label.getType().equals() ){
            return true;
        }
        
        return false;
    }


}

