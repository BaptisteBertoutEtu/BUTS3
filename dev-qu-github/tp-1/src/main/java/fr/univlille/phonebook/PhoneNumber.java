package fr.univlille.phonebook;

/**
 * @author Antoine Nongaillard, Fabien Delecroix
 * Un {@literal}{PhoneNumber} contient les informations d'un numéro de téléphone en incluant son indicatif de pays.
 * Les méthodes fournies permettent son affichage au format standard ou international
 */
public class PhoneNumber{

    /** Code du pays, par exemple 33 pour la France */
    protected final int COUNTRY_CODE;
    /** Code de la zone, par exemple 3 pour les hauts de France */
    protected final int AREA_CODE;
    /** Code du secteur, par exemple 20 pour Lille et alentours*/
    protected final int SECTOR_CODE;
    /** Trois derniers duos de chiffres */
    protected final int[] LAST_ONES = new int[3];


    /** Code indicatif de pays pour la France */
    public static final int FRANCE_CODE = 33;
    private static int countryCodeReference = FRANCE_CODE;

    /**
     * Permet de modifier l'indicatif de pays duquel dépend le format d'affichage des numéros
     * @param   countryCode   indicatif de pays souhaité
     * @see     #toString()
     */
    public static void setCountryCodeReference(int countryCode) {
        PhoneNumber.countryCodeReference = countryCode;
    }

    /**
     * Crée un numéro de téléphone initialisé avec les nombres fournis.
     * Dans le cas où l'un des paramètres commence par un zéro, on pourra aussi bien l'entrer ou non (par ex. 06 ou 6)
     * @param   country l'indicatif du pays
     * @param   area l'indicatif de zone géographique
     * @param   sector l'indicatif de secteur
     * @param   one le premier des 3 duos de chiffre finaux
     * @param   two le deuxième des 3 duos de chiffre finaux
     * @param   three le dernier des 3 duos de chiffre finaux
     */
    public PhoneNumber(int country, int area, int sector, int one, int two, int three) {
        this.COUNTRY_CODE = country;
        this.AREA_CODE = area;
        this.SECTOR_CODE = sector;
        this.LAST_ONES[0] = one;
        this.LAST_ONES[1] = two;
        this.LAST_ONES[2] = three;
    }

    /**
     * Retourne le numéro de téléphone sous forme standard : {@code}{xx.xx.xx.xx.xx}
     * @return  le numéro de téléphone sous forme standard : {@code}{xx.xx.xx.xx.xx}
     */
    public String standardFormat() {
        StringBuilder res = new StringBuilder();
        res.append('0'); 
        res.append(this.AREA_CODE);
        res.append('.');
        if(this.SECTOR_CODE<10) res.append('0');
        res.append(this.SECTOR_CODE);
        for (int num : LAST_ONES){
            res.append('.');
            if(num<10) res.append('0');
            res.append(num);
        }
        return res.toString();
    }

    /**
     * Retourne le numéro sous forme international : {@code}{+xx.x.xx.xx.xx.xx}
     * @return  le numéro sous forme international : {@code}{+xx.x.xx.xx.xx.xx}
     */
    public String internationalFormat() {
        StringBuilder res = new StringBuilder();
        res.append('+');
        res.append(this.COUNTRY_CODE);
        res.append('.');
        res.append(this.standardFormat().substring(1));
        return res.toString();
    }

    /**
     * Retourne la représentation sous forme d'une String du numéro de téléphone dans son format standard ou international selon
     * que l'indicatif de pays du numéro corresponde à celui de référence ou non.
     * @return  la représentation sous forme d'une String du numéro de téléphone dans son format international.
     * @see     #internationalFormat()
     * @see     #standardFormat()
     * @see     #countryCodeReference
     */
    public String toString() {
        if (this.COUNTRY_CODE == PhoneNumber.countryCodeReference)
            return standardFormat();
        else
            return internationalFormat();
    }

}