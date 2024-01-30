package main.java.fr.univlille.view.gameview.allview.allwinview;

/**
 * 
 * Classe {@code MonsterWinView} permettant d'afficher la page gagnate du Monstre lorsque le Monstre a gagné.<br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 * 
 * @see WinView
 */
public class MonsterWinView extends WinView{
    
    /**
     * Constructeur de la classe {@code MonsterWinView} permettant de créer l'affichage de la vue gagnant du monstre
     */
    public MonsterWinView(){
        this.label = "Le monstre a gagné !";
        this.text = "Le monstre a atteint la sortie, il a gagné !";
        this.start();
    }

}
