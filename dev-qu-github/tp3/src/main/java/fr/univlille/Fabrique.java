package main.java.fr.univlille;

/**
 * @author Baptiste Bertout
 * Classe Fabrique
 */
public class Fabrique {
    
    /**
     * Méthode permettant de créer un dessert avec le dessert souhaité et la ou les garniture(s) souhaitée(s)
     * @param commande L'ensemble de la commande
     * @return Un dessert totalement créer
     */
    public Dessert creerDessert(String commande){
        String[] c = commande.split(" ");
        Dessert d = null;

        if(c[0].equals("gaufre")){
            d = new Gaufre();
        }
        else if(c[0].equals("crepe")){
            d = new Crepe();
        }
        else{
            return new DessertNull();
        }
        
        for (String string : c) {
            if(string.equals("chocolat")){
                d = new Chocolat(d);
            }
            else if(string.equals("chantilly")){
                d = new Chantilly(d);
            }
            else if(string.equals("coulis de fraise")){
                d = new CoulisFraise(d);
            }
            else{
                return new DessertNull();
            }
        }

        return d;
    }
}
