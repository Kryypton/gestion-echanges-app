

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

           }
               return true;
            }


    public CriterionName getLabel() {
        return label;
    }

    public String getvalue(){
        return value;
    }

    
                
        

}

