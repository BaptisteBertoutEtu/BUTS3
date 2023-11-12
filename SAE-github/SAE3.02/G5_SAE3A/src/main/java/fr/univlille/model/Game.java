package main.java.fr.univlille.model;

import main.java.fr.univlille.model.entity.Hunter;
import main.java.fr.univlille.model.entity.Monster;
import main.java.fr.univlille.model.board.GenerateBooleanMaze;
import main.java.fr.univlille.model.parameters.Parameters;

/**
 * 
 * Classe {@code Game} qui nous permet de créer le jeu.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 */
public class Game {
    private Monster monster;
    private Hunter hunter;
    /**
     * Attribut {@code currentTurn} qui permet d'avoir accès au tour courant depuis toutes les classes.
     */
    public static int currentTurn;

    /**
     * Constructeur de la classe {@link Game} permettant de définir un nouveau Monstre et un nouveau Chasseur.
     * 
     * @see Hunter
     * @see Monster
     */
    public Game(){
        Game.currentTurn = 1;
        this.monster = new Monster();
        this.hunter = new Hunter();

        this.hunter.attach(monster);
        this.monster.attach(hunter);
        
        

        this.monster.initialize(GenerateBooleanMaze.generateBooleanMazeMonster(Parameters.lengthLabyrinth[0],Parameters.lengthLabyrinth[1]));
        // this.hunter.initialize(Parameters.lengthLabyrinth[0],Parameters.lengthLabyrinth[1]);
    }

    /**
     * Méthode {@code getMonster} permettant d'obtenir le Monstre du jeu.
     * @return Le Monstre.
     */
    public Monster getMonster(){
        return this.monster;
    }

    /**
     * Méthode {@code getHunter} permettant d'obtenir le Chasseur du jeu.
     * @return Le Chasseur.
     */
    public Hunter getHunter(){
        return this.hunter;
    }
}
