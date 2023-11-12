package main.java.fr.univlille.model.board;

import main.java.fr.univlille.utils.OftenUse;

/**
 * Classe {@code GenerateBooleanMaze} permettant de générer un tableau de booléen pour les murs du labyrinthe.<br>
 * Le tableau initialisé est construit à partir d'une hauteur et d'un largeur pour créer un tableau à double dimensions de booléens.<br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez  
 * @author Mathis Decoster
 * 
 */
public class GenerateBooleanMaze {    

    /**
     * Méthode {@code generateBooleanMazeMonster} permettant de générer le tableau de booléen destiné au Monstre, qui génère des murs aléatoirement, selon certaines valeurs données: la hauteur et la largeur.
     * 
     * @param width La hauteur du tableau.
     * @param length La largeur du tableau.
     * @return Le tableau de booléen destiné au Monstre.
     */
    public static boolean[][] generateBooleanMazeMonster(int width, int length){
        boolean[][] maze = generateBooleanMazeHunter(width, length);
        GenerateBooleanMaze.setWall(maze);
        return maze;
    }

    /**
     * Méthode {@code generateBooleanMazeHunter} permettant de générer le tableau de booléen destiné au Chasseur, qui génère un tableau vide, selon certaines valeurs données: la hauteur et la largeur.
     * 
     * @param width La hauteur du tableau.
     * @param length La largeur du tableau.
     * @return Le tableau de booléen destiné au Chasseur.
     */
    public static boolean[][] generateBooleanMazeHunter(int width, int length){
        boolean[][] maze = new boolean[width][length];
        GenerateBooleanMaze.setFullTrue(maze);
        return maze;
    }
 
    /**
     * Méthode {@code setFullTrue} permettant de mettre toutes les valeurs du tableau placé en paramètre à true.
     * 
     * @param maze Le tableau à modifier.
     */
    private static void setFullTrue(boolean[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = true;
            }
        }
    }

    /**
     * Méthode {@code setWall} permettant d'indiquer où sont les murs sur le labyrinthe.
     * 
     * @param maze tableau ou modifier les valeurs
     */
    private static void setWall(boolean[][] maze){
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if((i==0 && j==0) || (maze.length-1==i && maze[0].length-1==j)) continue;
                if(OftenUse.RANDOM.nextInt(100)%3==0) maze[i][j] = false;
            }
        }
    }
}
