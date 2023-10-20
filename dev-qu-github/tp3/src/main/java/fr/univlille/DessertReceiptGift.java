package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Classe DessertReceiptGift
 */
public class DessertReceiptGift implements DessertReceipt{
    
    /**
     * Méthode permettant d'avoir le reçu de commande pour un cadeau, donc seul le nom est indiqué
     * @param dessert Le dessert concerné
     * @return Le reçu de commande
     */
    @Override
    public String getReceipt(Dessert dessert) {
        return "Produit\n" + dessert.getName();
    }
}
