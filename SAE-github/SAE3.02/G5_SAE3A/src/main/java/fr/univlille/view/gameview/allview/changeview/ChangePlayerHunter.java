package main.java.fr.univlille.view.gameview.allview.changeview;

import main.java.fr.univlille.model.Game;
import main.java.fr.univlille.view.gameview.AllViewEnum;

/**
 * Classe {@code ChangePlayerHunter} permettant un affichage bloquant la vue à l'adversaire tant que le Chasseur n'a pas décidé de changer de joueur.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 * 
 * @see ChangeView
 */
public class ChangePlayerHunter extends ChangeView{

    /**
     * Constructeur principal de la classe {@code ChangePlayerHunter} permettant de construire la vue.
     */
    public ChangePlayerHunter(){
        this.text = Game.nameHunter + " a joué, à votre tour "+Game.nameMonster+" .";
        this.view = AllViewEnum.CHANGEPLAYERHUNTER;
        this.start();
    }
}
