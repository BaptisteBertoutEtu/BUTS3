package main.java.fr.univlille.entity.view;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import javafx.stage.Stage;
import main.java.fr.univlille.utils.Observer;

/**
 * 
 * Classe MonsterView permettant d'avoir une Interface Homme Machine. Celle-ci nous permet de joeur au jeu.<br>
 * Le monstre connait toujours l'emplacement de la sortie et connait la taille du labyrinthe.<br>
 * <br>
 * Pour la vue des mûrs, deux options seront possibles :<br>
 * Le monstre sait où sont les murs du labyrinthe (connaissance complète du labyrinthe).<br><br>
 * ou<br><br>
 * Le monstre a une visibilité limitée à une portée de x cases autour de l’emplacement où il se trouve (connaissance partielle du labyrinthe).<br><br>
 * Pour les déplacements seront soit droite, gauche, haut, bas (4 cases) ou avec les diagonales en suplément (8 cases).<br>
 * Tous ces paramètres seront définir par le joueur dans le menu.
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
public class MonsterView extends Stage implements Observer{
    
}
