package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Classe Chocolat
 */
public class Chocolat extends Garniture{
    private static final String NAME = "Chocolat";
    private static final double PRICE = 1.5;

    /**
     * Constructeur de la classe Chocolat définissant son nom, son prix et le dessert qui lui appartient
     * @param dessert Le dessert qui appartient à la décoration
     */
    public Chocolat(Dessert dessert){
        super(Chocolat.NAME, Chocolat.PRICE, dessert);
    }
}
