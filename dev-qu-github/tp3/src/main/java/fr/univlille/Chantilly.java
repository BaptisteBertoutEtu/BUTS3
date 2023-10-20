package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Classe Chantilly
 */
public class Chantilly extends Garniture{
    private static final String NAME = "Chantilly";
    private static final double PRICE = 1;

    /**
     * Constructeur de la classe Chantilly définissant son nom, son prix et le dessert qui lui appartient
     * @param dessert Le dessert qui appartient à la décoration
     */
    public Chantilly(Dessert dessert){
        super(Chantilly.NAME, Chantilly.PRICE, dessert);
    }
}
