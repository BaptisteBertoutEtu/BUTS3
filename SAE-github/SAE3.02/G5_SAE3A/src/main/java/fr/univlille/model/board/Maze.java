package main.java.fr.univlille.model.board;
import java.util.Random;

public class Maze {
    
    private int longueur;
    private int largeur;
    private final int HORIZONTAL = 1;
    private final int VERTICAL = 2;
    private final int S = 1;
    private final int E = 2;
    private Random rand = new Random();

    public Maze(int lo, int la){
        this.largeur = la;
        this.longueur = lo;
    }

    public int[][] generateMaze(){
        int[][] maze = new int[this.longueur][this.largeur];
        for (int i=0; i<maze.length; i++) {
            for (int j=0; j<maze[i].length; j++){
                maze[i][0] = 1;
                maze[0][j] = 1;
                maze[this.longueur-1][j] = 1;
                maze[i][this.largeur-1] = 1;
            }
        }
        for (int i = 1; i < maze.length-1; i++) {
            for (int j = 1; j < maze[0].length-1; j++) {
                maze[i][j] = 0;
            }
        }
        return maze;
    }

    public int orientation(int longueur, int largeur){
        int c;
        if (longueur<largeur) c = HORIZONTAL;
        else if (largeur<longueur) c = VERTICAL;
        else if (rand.nextInt(2)==0) c = HORIZONTAL;
        else c = VERTICAL;
        return c;
    }

    public void divide(int[][] maze, int x, int y, int longueur, int largeur, int orientation){
        if (longueur<=2 || largeur<=2) return;

        System.out.println();
        // System.out.println("\033[H");
        // System.out.println("\033[2J");;
        toString(maze);
        System.out.println();
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.getMessage();
        }

        boolean horizontal = (orientation==HORIZONTAL);
        
        //d'où le mur va partir
        int wallX;
        int wallY;
        //où sera le passage dans le mur 
        int px;
        int py;
        //dans quelle direction le mur ira
        int dx;
        int dy;
        //la taille du mur
        int taille;
        //la direction qui est perpendiculaire au mur
        int direction;

        if (horizontal){
            wallX = x + 0;
            wallY = y + rand.nextInt(largeur-2);
            px = wallX + rand.nextInt(longueur);
            py = wallY + 0;
            dx = 1;
            dy = 0;
            taille = longueur;
            direction = S;
        }else{
            wallX = x + rand.nextInt(longueur-2);
            wallY = y + 0;
            px = wallX + 0;
            py = wallY + rand.nextInt(largeur);
            dx = 0;
            dy = 1;
            taille = largeur;
            direction = E;
        }

        for (int i=0; i<taille; i++){
            if (wallX != px || wallY != py){
                maze[wallX][wallY] |= direction;
            }
            wallX += dx;
            wallY += dy;
        }

        int nx = x;
        int ny = y;
        int width;
        int height;
        if (horizontal){
            width = longueur;
            height = wallY-y+1;
        }else{
            width = wallX-x+1;
            height = largeur;
        }

        divide(maze, nx, ny, width, height, orientation(width, height));

        if (horizontal){
            nx = x;
            ny = wallY+1;
            width = longueur;
            height = y+largeur-wallY-1;
        }else{
            nx = wallX+1;
            ny = y;
            width = x+longueur-wallX-1;
            height = largeur;
        }

        divide(maze, nx, ny, width, height, orientation(width, height));
    }

    public void toString(int[][] maze){
        boolean bottom;
        boolean south;
        boolean south2;
        boolean east;
        
        int lineLength = maze[0].length*2-1;
        String line = " "+"_".repeat(lineLength);
        System.out.println(line);

        for(int y=0; y<maze.length; y++){
            int[] row = maze[y];
            System.out.print("|");
            for (int x=0; x<row.length; x++){
                int value = row[x];
                bottom = y+1 >= maze.length;
                south = ((value & S) != 0 || bottom);
                south2 = ((x+1 < maze[y].length) && (maze[y][x+1] & S) != 0 || bottom);
                east = ((value & E) != 0 || x+1 >= maze[y].length);

                System.out.print(south ? "_" : " ");
                System.out.print(east ? "|" : ((south && south2) ? "_" : " "));
            }
            System.out.println();
        }
        for (int[] bs : maze) {
            for (int bs2 : bs) {
                System.out.print(bs2==0 ? "   " : " @ ");
            }
            System.out.println();
        }
    }

    public boolean[][] mazeToBoolean(int[][] maze){
        boolean[][] booleanMaze = new boolean[maze.length][maze[0].length];
        for (int i=0; i<maze.length; i++) {
            for (int j=0; j<maze[0].length; j++) {
                booleanMaze[i][j] = maze[i][j]==0; 
            }
        }
        return booleanMaze;
    }

    public static void main(String[] args) {
        Maze maze = new Maze(30, 20);
        int[][] laby = maze.generateMaze();
        maze.divide(laby, 0, 0, maze.longueur, maze.largeur, maze.orientation(maze.longueur, maze.largeur));
        maze.toString(laby);
    }

}
