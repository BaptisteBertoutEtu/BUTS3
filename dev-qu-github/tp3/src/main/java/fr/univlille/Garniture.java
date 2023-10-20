package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Casse abstraite Garniture 
 */
public abstract class Garniture extends Dessert {
    
    protected Dessert dessert;

    /**
     * Constructeur de la classe Garniture
     * @param name Le nom de la garniture
     * @param price Le prix de la garniture
     * @param dessert Le dessert associé à la garniture
     */
    public Garniture(String name, double price, Dessert dessert){
        super(name, price);
        this.dessert=dessert;
    }

    /**
     * Méthode permettant d'obtenir le prix de l'ensemble de la configuration, le dessert + les garnitures dessus
     * @return Le prix de l'ensemble du dessert
     */
    public double getPrice(){
        return this.price + this.dessert.getPrice();
    }

    /**
     * Méthode permettant d'obtenir le nom de l'ensemble de la configuration, le dessert + les garnitures dessus
     * @return Le nom de l'ensemble du dessert
     */
    public String getName(){
        return this.dessert.getName() + " "+ this.name;
    }
}
