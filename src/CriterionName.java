/**
 * Enumère les différents types de critères pouvant être utilisés pour faire correspondre un invité et un hôte.
 * @version 1.0
 * @since 2023-05-01
 */
public enum CriterionName {
    
    // Les différents types de critères sont :
    GUEST_ANIMAL_ALLERGY('B'),
    HOST_HAS_ANIMAL('B'),
    GUEST_FOOD('T'),
    HOST_FOOD('T'),
    HOBBIES('T'),
    GENDER('T'),
    PAIR_GENDER('T'),
    HISTORY('T');

    private final char TYPE;

    /**
     * Constructeur de CriterionName
     * @param type le type de critére
     */
    private CriterionName(char type){
        this.TYPE=type;
    }

    /**
     * Cette méthode permet de récupérer le type d'un critére
     * @return le type du critére
     */
    public char getType() {
        return TYPE;
    }

    
}
