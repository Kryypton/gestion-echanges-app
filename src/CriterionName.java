/**
 * Enumère les différents types de critères pouvant être utilisés pour faire correspondre un invité et un hôte.
 * @version 1.0
 * @since 2023-05-01
 */
public enum CriterionName {
    
    /**
     * est allergique aux annimaux [true, false]
     */
    GUEST_ANIMAL_ALLERGY('B'),
    /**
     * héberge un animal [true, false]
     */
    HOST_HAS_ANIMAL('B'), 
    /**
     * nourriture de l'invité[végétarien, sportif, no nuts, tout]
     */
    GUEST_FOOD('T'), 
    /**
     * les hobbies de l'hôte [sport, musique, lecture, tout]
     */
    HOST_FOOD('T'), 
    /**
     * les hobbies de l'adolescent [sport, musique, lecture, tout]
     */
    HOBBIES('T'),
    /**
     * le genre de l'adolescent [F, M, T]
     */
    GENDER('T'),
    /**
     * le genre de l'hôte [F, M, T]
     */
    PAIR_GENDER('T'),
    /**
     * historique de l'adolescent
     */
    HISTORY('D'),

    /**
     * le type du critére
     */
    NUMERIC('N');

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
