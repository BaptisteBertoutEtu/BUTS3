package main.java.fr.univlille.view.gameview;

/**
 * 
 * Enumération {@code AllParametersView} permettant de définir l'ensemble des vue de paramètre qu'il est possible d'afficher.<br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 */
public enum AllParametersView {
    /**
     * Enumération {@code SIZE_PARAMETERS} définissant le nom de l'énumération lié à la vue de changement des paramètres de taille du labyrinthe.
     */
    SIZE_PARAMETERS,

    /**
     * Enumération {@code MOVEMENT_PARAMETERS} définissant le nom de l'énumération lié à la vue de changement des paramètres de mouvement dans le labyrinthe.
     */
    MOVEMENT_PARAMETERS,

    /**
     * Enumération {@code KNOLEDGE_PARAMETERS} définissant le nom de l'énumération lié à la vue de changement des paramètres de connaissance du labyrinthe.
     */
    KNOLEDGE_PARAMETERS,

    /**
     * Enumération {@code MOVEMENT_PARAMETERS} définissant le nom de l'énumération lié à la vue de changement des paramètres des joueurs et des intelligences artificielles dans le labyrinthe.
     */
    PLAYER_PARAMETERS
}