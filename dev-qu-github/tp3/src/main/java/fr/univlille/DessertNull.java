package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Classe DessertNull qui permet d'instancier un dessert null nous permettant de gerer les erreurs de la classe Fabrique si l'utilisateur
 * saisit un dessert ou une garniture inexistante ou si le premier mot de commande n'est pas un dessert
 */
public class DessertNull extends Dessert{
    private static final String NAME = "";
    private static final double PRICE = 0;

    /**
     * Contructeur de la classe DessertNull créant un dessert null sans nom et de prix 0
     */
    public DessertNull(){
        super(DessertNull.NAME,DessertNull.PRICE);
    }
}