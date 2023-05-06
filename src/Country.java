/**
 * Enumère les différents pays pouvant être utilisés pour faire correspondre un invité et un hôte.
 * @version 1.0
 * @since 2023-05-01
 * @author Dorny Nathan
 * @author Berrakane Adham
 * @author Moutté Quentin
 */
public enum Country {

    ITALIE("Italie"),
    ESPAGNE("Espagne"),
    FRANCE("France"),
    ALLEMAGNE("Allemagne");

    private final String COUNTRY_NAME;

    private Country(String country_name) {
        COUNTRY_NAME = country_name;
    }

    public String getCOUNTRY_NAME() {
        return COUNTRY_NAME;
    }
}