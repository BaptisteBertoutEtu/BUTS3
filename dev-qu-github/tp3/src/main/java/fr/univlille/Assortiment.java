package main.java.fr.univlille;

import java.util.ArrayList;

/**
 * @author Baptiste Bertout
 * Classe Assortiment pouvant contenir plusieurs desserts.
 */
public class Assortiment extends Dessert{
    private static final String NAME = "a";
    private static final double DEFAULTPRICE = 0;
    private ArrayList<Dessert> desserts;

    /**
     * Contructeur principal de la classe Assortiment
     * @param desserts Une liste de desserts qui construisent l'assortiment
     */
    public Assortiment(ArrayList<Dessert> desserts){
        super(Assortiment.NAME, Assortiment.DEFAULTPRICE);
        this.desserts=desserts;
    }

    /**
     * Constructeurs secondaire de la classe Assortiment
     */
    public Assortiment(){
        this(new ArrayList<Dessert>());
    }

    /**
     * Méthode permettant d'ajouter un dessert à la liste des desserts de l'assortiement
     * @param dessert Le dessert à ajouter
     */
    public void addDessert(Dessert dessert){
        this.desserts.add(dessert);
        this.price += dessert.getPrice();
    }

    /**
     * Méthode permettant de supprimer un dessert de la liste des desserts de l'assortiment
     * @param dessert Le dessert à supprimer
     */
    public void removeDesseert(Dessert dessert){
        this.price -= dessert.getPrice();
        this.desserts.remove(dessert);
    }

    /**
     * Méthode permettant d'avoir le prix total de l'assortiment avec 10% de rabais
     * @return Retourne le prix total de l'assortiment
     */
    public double getPrice(){
        return this.price - ((this.price*10.0)/100.0);
    }

    //getChild()
}
