package main.java.fr.univlille.model.board;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.entity.Monster;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;

/**
 * Classe {@code Board} permettant d'initialiser un plateau de jeu pour le monstre.<br>
 * Le tableau du monstre peut être intialisé de deux manières différentes. <br>
 * Soit par des dimentsions données, soit par un tableau de boolean donné. En effet cette classe ne crée pas les tableaux de booléens. <br>
 * Elle s'occupe majoritairement d'offrir au joueur un labyrinthe jouable en tout point. <br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez  
 * @author Mathis Decoster
 */
public class Board {

    /**
     * Position X du Monstre dans le labyrinthe.
     */
    private static int xMonstre;
    /**
     * Position Y du Monstre dans le labyrinthe.
     */
    private static int yMonstre;
    /**
     * Position X de la sortie dans le labyrinthe.
     */
    private static int xExit;
    /**
     * Position X de la sortie dans le labyrinthe.
     */
    private static int yExit;

    /**
     * Un tableau d'entier représentant les positions possibles pour le Monstre et la sortie. <br>
     * Représente les quatres coin du labyrinthe
     */
    private static int[][] postitions;
    /**
     * Tableau de booléen représentant le labyrinthe.
     */
    private static boolean[][] tab;

    /**
     * Méthode {@code initialize(int, int)} permettant d'initialiser un tableau 2D représentant le terrain de jeu du monstre en fonction de la taille donnée (la hauteur et la largeur pouvant être différente). <br>
     * Ce tableau est rempli de booléen. Ici {@code false} représente les mûrs et {@code true} représente les cases vides. <br>
     * Un nouveu labyrinthe est généré tant que le labyrinthe n'est pas franchissable, c'est à dire que le Monstre ne peut pas atteindre la sortie. <br>
     * 
     * @param height L'entier représentant la hauteur souhaitée du labyrinthe.
     * @param width L'entier réprésentant la largeur souhaitée du labyrinthe.
     * @return Le tableau à double dimension de booléen. (Le labyrinthe.)
     * 
     * @see GenerateBooleanMaze
     */
   public static boolean[][] initialize(int height, int width){
        Parameters.setZoneForSpanwn();
        do {
            Board.tab = GenerateBooleanMaze.generateBooleanMazeMonster(height, width);
            GenerateBooleanMaze.setMaze(tab);
            Board.postitions = GenerateBooleanMaze.getPositions();
            Board.randomExitPosition();
            Board.randomMonsterPosition();
        } while (!isMazeSolvable());
        return Board.tab;
    }

    /**
     * Méthode {@code initializeLabyPredef(boolean)} permettant d'initialiser un tableau 2D représentant le terrain de jeu du monstre de la taille donnée (la hauteur et la largeur pouvant être différente). <br>
     * Ce tableau est rempli de booléen. Ici {@code false} représente les mûrs et {@code true} représente les cases vides. <br>
     * Le labyrinthe n'est pas généré aléatoirement, ici il est donné en paramètre et est donc déjà définit. <br>
     * Le Monstre et la sortie sont positionné aléatoirement dans le labyrinthe.
     * 
     * @param tab Le tableau à double dimension de booléen.
     * @return Le tbleau à double dimension de booléen.
     */
    public static boolean[][] initializeLabyPredef(boolean[][] tab){
        Board.tab = tab;
        GenerateBooleanMaze.setMaze(tab);
        Board.postitions = GenerateBooleanMaze.getPositions();
        Parameters.setZoneForSpanwn();
        do {
            Board.randomMonsterPosition();
            Board.randomExitPosition();
        } while (!isMazeSolvable());
        return Board.tab;
    }

    /**
    * Méthode {@code randomExitPosition()} positionne la sortie de manière aléatoire dans un coin du tableau donné, 
    * puis vérifie qu'elle n'est pas au même endroit que le monstre. 
    * 
    * @see Monster
    * @see OftenUse
    */
    public static void randomExitPosition(){
        Random r = OftenUse.RANDOM;

        int i = r.nextInt(4);
        
        Board.xExit = Board.postitions[i][0];
        Board.yExit = Board.postitions[i][1];

        Monster.setExit(new Coordinate(xExit, yExit));
    }
    

    /**
     * Méthode {@code randomMonsterPosition()} positionne le monstre de manière aléatoire dans un coin du tableau donné. 
     * 
     * @see Monster
     * @see OftenUse
     */
    public static void randomMonsterPosition(){

        Random r = OftenUse.RANDOM;
        
        do {
            Board.xMonstre = r.nextInt(Parameters.lengthLabyrinth[0]);
            Board.yMonstre = r.nextInt(Parameters.lengthLabyrinth[1]);
        } while ((Board.xExit == Board.xMonstre && Board.yExit == Board.yMonstre) || !tab[Board.yMonstre][Board.xMonstre] || isInZone());

        Monster.setCurrentCoord(new Coordinate(xMonstre, yMonstre));
    }

    /**
     * Méthode privée {@code isInZone()} permettant de savoir si la position donnée est dans la zone autour de la sortie.
     * 
     * @return Un booléen indiquant si la position est dans la zone.
     * 
     * @see Board
     */
    private static boolean isInZone(){
        int[][] temp = getZone();

        for (int[] is : temp) {
            if(is[0]==Board.xMonstre && is[1]==Board.yMonstre) return true;
        }

        return false;

    }

    /**
     * Méthode privée static {@code getZone()} permettant d'obtenir les différentes cases adjacentes à la position donnée en paramètre. 
     * 
     * @return Un tableau à double dimension d'entier qui correspond au coordonnée x et y des cases adjacentes.
     * 
     * @see Parameters
     * @see Board
     */

    private static int[][] getZone(){
        int zoneInt = Parameters.zoneForSpawnAroundExit;
        
        int debutX = Math.max(0, Board.xExit - zoneInt);
        int finX = Math.min(Parameters.lengthLabyrinth[0] - 1, Board.xExit + zoneInt);
        
        int debutY = Math.max(0, Board.yExit - zoneInt);
        int finY = Math.min(Parameters.lengthLabyrinth[1] - 1, Board.yExit + zoneInt);
        
        int[][] temp = new int[(zoneInt+1)*(zoneInt+1)][2];
        int z = 0;
        for (int i = debutX; i <= finX; i++) {
            for (int j = debutY; j <= finY; j++) {
                temp[z] = new int[]{i,j};
                z++;
            }
        }
        return temp;
    }


    /**
     * Méthode {@code getxMonstre()} retournant la coordonnée X du monstre.
     * 
     * @return La coordonnée X du monstre.
     */
    public static int getxMonstre(){
        return Board.xMonstre;
    }

    /**
     * Méthode {@code getyMonstre()} retournant la coordonnée Y du monstre.
     * 
     * @return La coordonnée Y du monstre.
     */
    public static int getyMonstre(){
        return Board.yMonstre;
    }

    /**
     * Méthode {@code getxExit()} retournant la coordonnée X de la sortie.
     * 
     * @return La coordonnée X de la sortie.
     */
    public static int getxExit(){
        return Board.xExit;
    }

    /**
     * Méthode {@code getyExit()} retournant la coordonnée Y de la sortie.
     * 
     * @return La coordonnée Y de la sortie.
     */
    public static int getyExit(){
        return Board.yExit;
    }
    
    /**
     * Méthode privée {@code getValidNeighbors(int, int)} permettant d'obtenir les différentes cases adjacentes à la position donnée en paramètre. <br>
     * Elle permet d'obtenir les cases adjacentes et de définir si un chemin existe ou non.
     * 
     * @param x Position X.
     * @param y Postion Y.
     * @return Un tableau à double dimension d'entier qui correspond au coordonnée x et y des cases adjacentes.
     * 
     * @see Arrays
     */
    private static int[][] getValidNeighbors(int x, int y) {
        int[][] neighbors = new int[4][2];
        int count = 0;

        int[][] directions = {
            {1, 0},
            {-1, 0},
            {0, 1}, 
            {0, -1} 
        };

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];


            if (nx >= 0 && ny >= 0 && nx < tab[0].length && ny < tab.length && tab[ny][nx]) {
                neighbors[count] = new int[] { nx, ny };
                count++;
            }
        }
        return Arrays.copyOf(neighbors, count);
    }

    /**
     * Méthode privée {@code isMazeSolvable()} permettant de savoir si le labyrinthe est jouable, c'est à dire, si il existe un chemin 
     * entre la position initiale du Monstre et le position de la sortie.
     * 
     * @return {@code true} si le labyrinthe est jouable, {@code false} sinon.
     */
    private static boolean isMazeSolvable() {
        int startX = Board.xMonstre; 
        int startY = Board.yMonstre; 
        int endX = Board.xExit;
        int endY = Board.yExit;

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[] { startX, startY });
        boolean[][] visited = new boolean[tab.length][tab[0].length];
    
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int x = current[0];
            int y = current[1];
    
            if (x == endX && y == endY) {
                return true;
            }
    
            if (visited[y][x]) {
                continue; // Skip already visited cells
            }
            visited[y][x] = true;
    
            int[][] neighbors = getValidNeighbors(x, y);
            for (int[] neighbor : neighbors) {
                int nx = neighbor[0];
                int ny = neighbor[1];
                stack.push(new int[] { nx, ny });
            }
        }
        return false;
    }

}
