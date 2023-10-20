package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Classe DessertReceiptDefault
 */
public class DessertReceiptDefault implements DessertReceipt{
    
    /**
     * Méthode permettant un reçu de commande normal
     * @param dessert Le dessert concerné
     * @return Le reçu de commande
     */
    @Override
    public String getReceipt(Dessert dessert) {     
        return "Produit             Prix\n" + dessert.getName() + "        " + dessert.getPrice();
    }
}
