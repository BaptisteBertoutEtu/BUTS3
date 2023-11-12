package main.java.fr.univlille.model.entity;


import java.util.HashMap;
import java.util.Map;

import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import main.java.fr.univlille.model.cell.CellEvent;
import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.exception.UnsupportedMove;
import main.java.fr.univlille.model.Game;
import main.java.fr.univlille.model.board.Board;
import main.java.fr.univlille.model.parameters.AllParameters;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.gameview.AllViewEnum;

/**
 * 
 * Classe {@code Monster} peremttant l'initialisation du monstre.<br>
 * Le Monstre ne peut que se déplacer sur une case du plateau adjacente et qui est libre.<br>
 * Cette classe implémente l'interface {@code IMonsterStrategy} pour avoir accès à ses méthodes.<br>
 * Elle implémente également l'interface {@link Observer} et hérite de la classe abstraite {@link Subject}, 
 * qui lui permettent de notifier l'objet {@link Hunter} lorsque celui-ci lui demande les données de la case sur lequel il vient de tirer.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see Subject
 * @see IMonsterStrategy
 * @see Observer
 */
public class Monster extends Subject implements IMonsterStrategy, Observer{
    private static Coordinate currentCoord;
    private static Coordinate lastCoord;
    private Coordinate lastShoot;
    private static Map<Coordinate,Integer> path  = new HashMap<>();
    private boolean[][] monsterBooleanMaze;
    private final int[][] POSSIBILITES_FOR_CROSS;
    private Map<ICoordinate,CellEvent> history;
    private Coordinate exit;

    /**
     * Constructeur de la classe {@code Monster} qui permet d'instancier un Monstre.<br>
     */
    public Monster(){
        this.POSSIBILITES_FOR_CROSS =  new int[][]{
            {-1,-1},
            {-1,1},
            {1,-1},
            {1,1}
        };
        this.lastShoot = null;
        this.history = new HashMap<>();
        
    }

    /**
     * 
     */
    @Override
    public ICoordinate play() {
        return null;
    }
    
    /**
     * Méthode {@code move} permettant le déplacement du monstre avec, en paramètre, les coordonnées de la case où le joueur veut se déplacer.<br>
     * Cette méthode permet également de vérifier si le Monstre a gagné.<br>
     * Cette méthode génère une exception si le mouvement n'est pas valable.
     * 
     * @param coord Les coordonnées de la case où le joueur veut se déplacer.
     * @return Un booléen permettant de savoir si le déplacement à fonctionné ou non. Ici {@code false} signifie que la case 
     * ou l'on souhaite se déplacer est la sortie, ce qui permet de déclencher la victoire du Monstre.
     * @throws UnsupportedMove L'exception est levée lorsque le déplacement est impossible.
     */
    public void move(Coordinate coord) throws UnsupportedMove{
        if (isGoodMove(coord)) {
            Monster.lastCoord = Monster.currentCoord;
            Monster.currentCoord = coord;
            Monster.path.put(coord,Game.currentTurn+1);
            if (coord.equals(this.exit)) {
                notifyObservers(AllViewEnum.MONSTERWIN);
            }
            else{
                CellEvent temp = new CellEvent(CellInfo.EMPTY, coord, ++Game.currentTurn);
                history.put(temp.getCoord(),temp);
                notifyObservers((Coordinate)temp.getCoord());
            }
            
        }
        else{
            throw new UnsupportedMove();
        }
    }

    /**
     * Méthode {@code isGoodMove} permettant de défnir si le déplacement du joueur est valable ou non.
     * Selon les paramètres saisis par le joueur, on vérifie soit que le coup peut se jouer pour un déplacement de 4 case autour de la position actuelle
     * ( c'est à dire, soit à gauche, soit à droite, soit en haut, soit en bas ), soit que le coup peut se jouer pour un 
     * déplacement de 8 cases autour de la position actuelle.
     * 
     * @param coord Les coordonnées de la case que le joueur veut atteindre.
     * @return {@code true} si le coup est bon, {@code false} sinon.
     */
    private boolean isGoodMove(Coordinate coord){
        if (Parameters.movement.equals(AllParameters.MOVEMENT_4)) {
            return isGoodMoveFour(coord);
        }
        else{
            return isGoodMoveEight(coord);
        }
    }

    /**
     * Méthode {@code isGoodMoveFour} permettant de défnir si le déplacement du joueur est valable ou non pour un déplacement de 4 case autour de la position actuelle
     * ( c'est à dire, soit à gauche, soit à droite, soit en haut, soit en bas ).
     * Cette méthode vérifie également si la case souhaitée n'est pas un mur.
     * 
     * @param coord Les coordonnées de la case que le joueur veut atteindre.
     * @return {@code true} si le coup est bon, {@code false} sinon.
     */
    private boolean isGoodMoveFour(Coordinate coord){
        int col = Monster.currentCoord.compareCol(coord.getCol());
        int row = Monster.currentCoord.compareRow(coord.getRow());
        boolean goodCol = (col<=1 && col>=-1);
        boolean goodRow = (row<=1 && row>=-1);
        boolean isntWall = this.monsterBooleanMaze[coord.getCol()][coord.getRow()];
        if(Monster.currentCoord.getCol()!=coord.getCol() && Monster.currentCoord.getRow()!=coord.getRow()){
            return false;
        }
        return goodCol && goodRow && isntWall;
    }
    
    /**
     * Méthode {@code isGoodMoveEight} permettant de défnir si le déplacement du joueur est valable ou non pour un 
     * déplacement de 8 cases autour de la position actuelle.
     * Cette méthode vérifie également si la case souhaitée n'est pas un mur.
     * 
     * @param coord Les coordonnées de la case que le joueur veut atteindre.
     * @return {@code true} si le coup est bon, {@code false} sinon.
     */
    private boolean isGoodMoveEight(Coordinate coord){
        int col = Monster.currentCoord.compareCol(coord.getCol());
        int row = Monster.currentCoord.compareRow(coord.getRow());
        boolean goodCol = (col<=1 && col>=-1);
        boolean goodRow = (row<=1 && row>=-1);
        boolean isntWall = !this.monsterBooleanMaze[coord.getCol()][coord.getRow()] == false;
        return goodCol && goodRow && isntWall && dontCrossWall(col, row,coord);
        
    }

    /**
     * Méthode {@code dontCrossWall} permettant de vérifier que le Monstre ne puisse pas se déplacer à travers deux murs posés en diagonale comme dans l'exemple ci-dessous : <br>
     *      M | W <br>
     *      ----- <br>
     *      W | * <br>
     * Avec M étant le monstre, W un mur et * l'endroit ou il veut aller.
     * 
     * @param col La comparaison entre la colonne courante du Monstre et la colonne de la nouvelle coordonnée.
     * @param row La comparaison entre la ligne courante du Monstre et la ligne de la nouvelle coordonnée.
     * @param coord La coordonnée où le Monstre veut se rendre.
     * @return {@code true} si il ne traverse pas de mur, {@code false} sinon.
     */
    private boolean dontCrossWall(int col, int row,Coordinate coord){
        for (int[] cross : this.POSSIBILITES_FOR_CROSS) {
            if(cross[0]==col && cross[1]==row){
                return (this.monsterBooleanMaze[coord.getCol()][coord.getRow()-cross[1]] || this.monsterBooleanMaze[coord.getCol()-cross[0]][coord.getRow()]);
            }
        }
        return true;
    }

    /**
     * Méthode {@code initialize} permettant d'initialiser un labyrinthe à partir d'un tableau de booléen.
     * 
     * @param maze Le tableau de booléen peremttant d'initialiser un labyrinthe.
     */
    @Override
    public void initialize(boolean[][] maze) {
        this.monsterBooleanMaze = maze;
        Board.randomMonsterPosition(maze);
        Board.randomExitPosition(maze);
        this.exit = new Coordinate(Board.getxExit(), Board.getyExit());
    }

    /**
     * Méthode {@code update} non utilisé ici.
     * 
     * @throws UnsupportedOperationException L'exception est levé si la méthode est appelée.
     */
    @Override
    public void update(Subject subj) {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     */
    @Override
    public void update(ICellEvent arg0) {
        throw new UnsupportedOperationException();
    }

    /**
     * Méthode {@code lastShoot} permettant de définir la dernière coordonnée des tirs éffectués par le Chasseur, et de notifier le Chasseur
     * avec les informations de cette case.<br>
     * De plus, si la case est la sortie, le Chasseur n'en est pas informé. 
     * 
     * @param data Les coordonées du dernier tir du Chasseur.
     */
    @Override
    public void update(Subject subj, Object data) {
        if(data instanceof Coordinate && subj instanceof Hunter && !((Coordinate) data).equals(this.lastShoot)) {
            Coordinate temp = (Coordinate) data;
            this.lastShoot = temp;
            notifyObservers(new CellEvent(CellInfo.HUNTER, temp, -1));

            
            if(temp.equals(Monster.currentCoord)) notifyObservers(new CellEvent(CellInfo.MONSTER, temp, Game.currentTurn));
            else if(this.history.containsKey(temp)) {notifyObservers(this.history.get(temp)); System.out.println("entrer");}
            else if(this.monsterBooleanMaze[temp.getCol()][temp.getRow()]) notifyObservers(new CellEvent(CellInfo.EMPTY, temp, -1));
            else notifyObservers(new CellEvent(CellInfo.WALL, temp, -1));
        }
    }

    /**
     * Méthode {@code getCurrentCoord} permettant de retourner les coodonnées courantes du Monstre.
     * 
     * @return Les coodonnées courantes du Monstre.
     */
    public Coordinate getCurrentCoord(){
        return Monster.currentCoord;
    }

    /**
     * Méthode {@code getLastCoord} permettant de retourner les coodonnées précédentes du Monstre.
     * 
     * @return Les coodonnées précédents du Monstre.
     */
    public Coordinate getLastCoord(){
        return Monster.lastCoord;
    }

    /**
     * Méthode {@code setCurrentCoord} permettant de de définir les coordonnées principales du Monstre, de défnir les coordonnées précédentes du Monste
     * ( qui ici sont les mêmes que ls coordonnées courantes ) et d'ajouter au chemin parcouru par le Monstre, la dernière case parcourue ainsi que le tour correspondant.<br>
     * Cette méthode n'est appelée qu'une seule fois. Dans la classe {@link Board};
     * 
     * @param c Les coordonnées permettant de définir l'ensemble des opérations définit dans l'explication.
     */
    public static void setCurrentCoord(Coordinate c){
        Monster.currentCoord = c;
        Monster.lastCoord = c;
        Monster.path.put(c,Game.currentTurn);
    }


    /**
     * Méthode {@code getLastShoot} permettant de retourner les coordonées du dernier tir du chasseur.
     * 
     * @return Les coordonées du dernier tir du Chasseur.
     */
    public Coordinate getLastShoot(){
        return this.lastShoot;
    }

    /**
     * Méthode {@code getPath} permettant d'accéder au chemin parcouru par le Monstre.
     * @return La {@link Map} contenant le chemin parcouru par le Monstre.
     */
    public Map<Coordinate,Integer> getPath(){
        return Monster.path;
    }

    public Coordinate getExit(){return this.exit;}

    public boolean[][] getMaze(){
        return this.monsterBooleanMaze;
    }
}
