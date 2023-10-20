package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Classe DessertReceiptMinimal
 */
public class DessertReceiptMinimal implements DessertReceipt{
    private final int CARAC;
    
    /**
     * Constructeur de la classe DessertReceiptMinimal permettant de définir le nombre de caractère maximal
     * qui peuvent apparaitre sur le reçu de commande
     * @param carac Le nombre de caractère maximale
     */
    public DessertReceiptMinimal(int carac){
        this.CARAC = carac;
    }

    /**
     * Méthode permettant d'avoir le reçu de commande minimal, donc avec un nombre de caractère défninit
     * @param dessert Le dessert concerné
     * @return Le reçu de commande
     */
    @Override
    public String getReceipt(Dessert dessert) {
        double max = this.CARAC - dessert.getPrice();
        return dessert.getName().substring(0, (int) max) + dessert.getPrice();
    }
}
