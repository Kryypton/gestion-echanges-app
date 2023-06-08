


public class AffectationUtil {
    
    /**
     * Méthode qui permet de savoir le niveau de compatibilité de 2 adolescent. Il commence avec 10 point, et plus ils seront compatible, plus leurs scores diminueras
     * @param host l'adolescent hôte
     * @param visitor l'adolescent invité
     * @return Le poids de leur compatibilité, plus ils est faible, plus ils sont compatible
     */
     public static double weight (Teenager host, Teenager guest , History history) {
        double poid = 10;
        double poids = 0;
        poids -= host.nbLoisirCommun(guest);
        if(!host.compatibleWithGuest(guest)){
            poids += 100;
        }
        //Pays différent ?
        if (host.getCriterion("COUNTRY").equals(guest.getCriterion("COUNTRY"))) {
            poids += 10;
        }
        poid = poids;
        poid = poid + history.historyTeenager(host, guest);
        return poid;
    }
}
