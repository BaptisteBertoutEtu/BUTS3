package main.java.fr.univlille.model.board;

import java.util.Random;
import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.entity.Monster;
import main.java.fr.univlille.utils.OftenUse;

/**
 * Classe {@code Board} permettant d'initialiser un plateau de jeu pour le monstre et le chasseur.<br>
 * Le tableau initialisé pour le monstre est construit à partir d'un tableau à double dimension de booléen permettant de savoir où se trouvent les mûrs.<br>
 * Le tableau du chasseur quant à lui est initialisé par des cases vides qui seront mises à jour au fur et à mesure du jeu.<br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez  
 * @author Mathis Decoster
 * 
 */
public class Board {

    private static int xMonstre;
    private static int yMonstre;
    private static int xExit;
    private static int yExit;

    /**
     * Méthode {@code randomMonsterPosition} positionne le monstre de manière aléatoire dans un coin du tableau donné. 
     * 
     * @param tab Le tableau de jeu dans lequel le monstre doit être positionné.
     * 
     * @see Cell
     */
    public static void randomMonsterPosition(boolean[][] tab){

        Random r = OftenUse.RANDOM;
        do {
            switch (r.nextInt(4)) {
                case 1:
                    Board.xMonstre = 0;
                    Board.yMonstre = tab[0].length-1;
                    break;
                case 2:
                    Board.xMonstre = tab.length-1;
                    Board.yMonstre = 0;
                    break;
                case 3:
                    Board.xMonstre = tab.length-1;
                    Board.yMonstre = tab[0].length-1;
                    break;
                default : 
                    Board.xMonstre = 0;
                    Board.yMonstre = 0;
                    break;
            }
        } while (!tab[Board.xMonstre][Board.yMonstre]);

        Monster.setCurrentCoord(new Coordinate(xMonstre, yMonstre));
    }

     /**
     * Méthode {@code randomExitPosition} positionne la sortie de manière aléatoire dans un coin du tableau donné, 
     * puis vérifie qu'elle n'est pas au même endroit que le monstre. 
     * 
     * @param tab Le tableau de jeu dans lequel la sortie doit être positionné.
     * 
     * @see Cell
     */
    public static void randomExitPosition(boolean[][] tab){
        Random r = OftenUse.RANDOM;

        do {
            switch (r.nextInt(4)) {
                case 1:
                    Board.xExit = 0;
                    Board.yExit = tab[0].length-1;
                    break;
                case 2:
                    Board.xExit = tab.length-1;
                    Board.yExit = 0;
                    break;
                case 3:
                    Board.xExit = tab.length-1;
                    Board.yExit = tab[0].length-1;
                    break;
                default : 
                    Board.xExit = 0;
                    Board.yExit = 0;
                    break;
            }
        } while ((Board.xExit == Board.xMonstre && Board.yExit == Board.yMonstre) || !tab[Board.xExit][Board.yExit]);
    }

    /**
     * Méthode {@code getxMonstre} retournant la coordonnée X du monstre
     * 
     * @return La coordonnée X du monstre
     */
    public static int getxMonstre(){
        return Board.xMonstre;
    }

    /**
     * Méthode {@code getyMonstre} retournant la coordonnée Y du monstre
     * 
     * @return La coordonnée Y du monstre
     */
    public static int getyMonstre(){
        return Board.yMonstre;
    }

    /**
     * Méthode {@code getxExit} retournant la coordonnée X de la sortie
     * 
     * @return La coordonnée X de la sortie
     */
    public static int getxExit(){
        return Board.xExit;
    }

    /**
     * Méthode {@code getyExit} retournant la coordonnée Y de la sortie
     * 
     * @return La coordonnée Y de la sortie
     */
    public static int getyExit(){
        return Board.yExit;
    }
    
    // /**
    //  * Méthode privée {@code getValidNeighbors} 
    //  * @param maze
    //  * @param x
    //  * @param y
    //  * @return
    //  */
    // private static int[][] getValidNeighbors(Cell[][] maze, int x, int y) {
    //     int[][] neighbors = new int[4][2];
    //     int count = 0;

    //     int[][] directions = {
    //         {1, 0},
    //         {-1, 0},
    //         {0, 1}, 
    //         {0, -1} 
    //     };

    //     for (int[] dir : directions) {
    //         int nx = x + dir[0];
    //         int ny = y + dir[1];


    //         if (nx >= 0 && ny >= 0 && nx < maze.length && ny < maze[0].length && maze[nx][ny].isEmpty()) {
    //             neighbors[count] = new int[] { nx, ny };
    //             count++;
    //         }
    //     }
    //     return Arrays.copyOf(neighbors, count);
    // }

    // /**
    //  * Méthode privée {@code isMazeSolvable} permettant de savoir si le labyrinthe passé en paramètre est jouable, c'est à dire, si il existe un chemin 
    //  * entre la position initiale du Monstre et le position de la sortie.
    //  * 
    //  * @param maze Le tableau sur lequel nous voulons vérifier la jouabilité.
    //  * @param statX La position X du Monstre.
    //  * @param statY La position Y du Monstre.
    //  * @param endX La position X de la sortie.
    //  * @param endY La position Y de la sortie.
    //  * @return {{@code true}} si le labyrinthe est jouable, {{@code false}} sinon.
    //  */
    // private static boolean isMazeSolvable(Cell[][] maze) {
    //     int startX = Board.xMonstre; 
    //     int startY = Board.yMonstre; 
    //     int endX = Board.xExit;
    //     int endY = Board.yExit;

    //     Deque<int[]> stack = new ArrayDeque<>();
    //     stack.push(new int[] { startX, startY });
    //     boolean[][] visited = new boolean[maze.length][maze[0].length];
    
    //     while (!stack.isEmpty()) {
    //         int[] current = stack.pop();
    //         int x = current[0];
    //         int y = current[1];
    
    //         if (x == endX && y == endY) {
    //             return true;
    //         }
    
    //         if (visited[x][y]) {
    //             continue; // Skip already visited cells
    //         }
    //         visited[x][y] = true;
    
    //         int[][] neighbors = getValidNeighbors(maze, x, y);
    //         for (int[] neighbor : neighbors) {
    //             int nx = neighbor[0];
    //             int ny = neighbor[1];
    //             stack.push(new int[] { nx, ny });
    //         }
    //     }
    //     return false;
    // }    
}
