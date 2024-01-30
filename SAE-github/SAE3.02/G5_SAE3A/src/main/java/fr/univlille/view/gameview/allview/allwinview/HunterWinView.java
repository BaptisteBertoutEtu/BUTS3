package main.java.fr.univlille.view.gameview.allview.allwinview;

/**
 * 
 * Classe {@code HunterWinView} permettant d'afficher la page gagnate du Chasseur lorsque le Chasseur a gagné.<br>
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
public class HunterWinView extends WinView{

    /**
     * Constructeur de la classe {@code HunterWinView} permettant de créer l'affichage de la vue gagnant du chasseur
     */
    public HunterWinView(){
        this.label = "Le chasseur a gagné !";
        this.text = "Le chasseur a attrapé le Monstre, il a gagné !";
        this.start();
    }
}
