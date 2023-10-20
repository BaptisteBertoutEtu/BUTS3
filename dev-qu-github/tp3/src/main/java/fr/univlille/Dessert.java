package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Classe abstraite Dessert
 */
public abstract class Dessert{

    protected String name;
    protected double price;

    /**
     * Constructeur de la classe Dessert
     * @param name Le nom du dessert
     * @param price Le prix du dessert
     */
    protected Dessert(String name, double price){
        this.name=name;
        this.price=price;
    }

    /**
     * Méthode permettant d'obtenir le prix du dessert
     * @return Le prix du dessert
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Méthode permettant d'obtenir le nom du dessert
     * @return Le nom du dessert
     */
    public String getName(){
        return this.name;
    }

}