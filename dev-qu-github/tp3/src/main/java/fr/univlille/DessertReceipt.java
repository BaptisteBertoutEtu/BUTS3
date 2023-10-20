package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Interface DessertReceipt 
 */
public interface DessertReceipt {

    /**
     * Méthode permettant d'avoir le reçu de commande du dessert avec le prix et le nom du dessert
     * @param dessert Le dessert concerné
     * @return Le reçu de commande
     */
    String getReceipt(Dessert dessert);
}