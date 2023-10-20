package main.java.fr.univlille.entity.view;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import javafx.stage.Stage;
import main.java.fr.univlille.cell.CellEvent;
import main.java.fr.univlille.utils.Observer;

/**
 * 
 * Classe HunterView permettant d'avoir une Interface Homme Machine. Celle-ci nous permet de joeur au jeu.<br>
 * La vue du plateau du chasseur est simple. Le chasseur ne peut voir que les cases sur lesquelles il a tirer.<br>
 * Lorsqu'il tire sur une case celle-ci lui est révélée et il a accès aux données de cette case.<br>
 * Voir l'interface ICellEvent pour avoir connaissance des différentes cases.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see ICellEvent
 * @see Stage
 * @see Observer
 */
public class HunterView extends Stage implements Observer{
    
}
