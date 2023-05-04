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