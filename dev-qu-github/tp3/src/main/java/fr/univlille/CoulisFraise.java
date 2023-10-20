package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Classe CoulisFraise
 */
public class CoulisFraise extends Garniture{
    private static final String NAME = "Coulis de fraise";
    private static final double PRICE = 2;

    /**
     * Constructeur de la classe CoulisFraise définissant son nom, son prix et le dessert qui lui appartient
     * @param dessert Le dessert qui appartient à la décoration
     */
    public CoulisFraise(Dessert dessert){
        super(CoulisFraise.NAME, CoulisFraise.PRICE, dessert);
    }
}
