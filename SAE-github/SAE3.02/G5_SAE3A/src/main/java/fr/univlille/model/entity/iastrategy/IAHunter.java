package main.java.fr.univlille.model.entity.iastrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import fr.univlille.iutinfo.cam.player.hunter.IHunterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;
import main.java.fr.univlille.model.cell.Coordinate;

/**
 * Classe {@code IAHunter} permettant l'implémentation de l'interface {@code IHunterStrategy} et permettant de créer une "Intelligence Artificielle" 
 * qui jouera contre le Joueur humain ou contre l'IA du Monstre. <br>
 * Cette IA est une IA aléatoire pour le moment, c'est à dire qu'elle tire dans un case aléatoire du labyrinthe.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 */
public class IAHunter implements IHunterStrategy{

    private static final Random r = new Random();
    private int currentTurn;
    private InnerIAHunterCellEvent lastCoordDiscovered;
    private int turnOfDiscovered;
    private int range;
    private InnerIAHunterCellEvent[][] tabHunter;

    private Set<ICoordinate> alreadyDiscovered;

    /**
     * Constructeur de la classe {@code IAHunter} permettant d'instancier l'IA du chasseur.
     */
    public IAHunter(){
        this.currentTurn = 0;
        this.lastCoordDiscovered = null;
        this.turnOfDiscovered = 0;
        this.range = 0;
        this.tabHunter = null;
        this.alreadyDiscovered = new HashSet<>();
    }

    /**
     * Méthode {@code play()} hérité de {@code IHunterStrategy} qui permet à l'IA du Chasseur de tirer aléatoirement dans le labyrinthe. <br>
     * 
     * @return Cette méthode retourne une instance de {@code InnerIAHunterCoordinate}. Autrement dit une coordonnée.
     */
    @Override
    public ICoordinate play() {
        ICoordinate cell;
        
        if(lastCoordDiscovered == null){
            cell = new InnerIAHunterCoordinate( r.nextInt(tabHunter[0].length),  r.nextInt(tabHunter.length));
            if(!this.alreadyDiscovered.add(cell)) cell = this.play();
        }
        else {
            this.range = turnOfDiscovered - lastCoordDiscovered.getTurn();
            this.range = this.range==1 || this.range==0 ? 1 : this.range/2;

            List<InnerIAHunterCoordinate> liste = aroundCell();

            cell = liste.get(r.nextInt(liste.size()));
        }

        return cell;
    }

    /**
     * Méthode {@code update()} hérité de l'interface {@code IHunterStrategy} qui permet de mettre à jour 
     * les cases connues par le Chasseur.
     */
    @Override
    public void update(ICellEvent arg0) {
        InnerIAHunterCellEvent temp = new InnerIAHunterCellEvent(arg0.getState(), arg0.getCoord(), arg0.getTurn());

        this.tabHunter[temp.getCoord().getRow()][temp.getCoord().getCol()] = temp;

        if(temp.isMonsterPassed() && lastCoordDiscovered == null){
            this.alreadyDiscovered.clear();
            this.lastCoordDiscovered = temp;
            this.turnOfDiscovered = currentTurn;
        }
        else if(temp.isMonsterPassed() && temp.getTurn()>lastCoordDiscovered.getTurn()){
            this.lastCoordDiscovered = temp;
            this.turnOfDiscovered = currentTurn;
        }

        currentTurn++;
    }

    /**
     * Méthode {@code initialise()} hérité de l'interface {@code IHunterStrategy} permettant d'initiliser l'ensemble des éléments 
     * utilisés par l'IA pour jouer.
     * 
     * @param arg0 Ce paramètre représente le nombre de ligne que compte le labyrinthe.
     * @param arg1 Ce paramètre représente le nombre de colonne que compte le labyrinth".
     * 
     */
    @Override
    public void initialize(int arg0, int arg1) {
        currentTurn = 1;
        this.tabHunter = new InnerIAHunterCellEvent[arg0][arg1];

        for (int y = 0; y < tabHunter.length; y++) {
            for (int x = 0; x < tabHunter[0].length; x++) {
                tabHunter[y][x] = new InnerIAHunterCellEvent(CellInfo.EMPTY, new InnerIAHunterCoordinate(x, y), -1);
            }
        }
    }
    
    /**
     * Méthode privée {@code aroundCell()} permet de donner une liste de coordonnées autour de la
     * coordonée du chemin maximum du hunter trouvée, pour que le hunter tire.
     * 
     * @return la liste de coordonées où l'on peut tirer
     */
    private List<InnerIAHunterCoordinate> aroundCell(){
        List<InnerIAHunterCoordinate> liste = new ArrayList<>();
        ICoordinate tempCoord = lastCoordDiscovered.getCoord();

        for(int y=tempCoord.getRow()-this.range; y<=tempCoord.getRow()+this.range; y++){
            for(int x=tempCoord.getCol()-this.range; x<=tempCoord.getCol()+this.range; x++){     
                if(isValid(y,x) && !(y == tempCoord.getRow() && x == tempCoord.getCol())){
                    liste.add(new InnerIAHunterCoordinate(x, y));
                }
            }
        }
        return liste;
    }

    /**
     * Méthode {@code isValid()} permettant de définir si les entier passés en paramètre, représentant une coodonnée, sont valides. <br>
     * C'est à dire que les coordonnées ne sortent pas du labyrinthe.
     * 
     * @param x Ce paramètre représente la coordonée x à vérifiée.
     * @param y Ce paramètre représente la coordonée y à vérifiée.
     * 
     * @return Cette méthode retourne un {@code booléen} selon si les attributs passés en paramètre sont valides ou non.
     */
    private boolean isValid(int y, int x){
        return y >= 0 && x >= 0 && y < tabHunter.length && x < tabHunter[0].length && !tabHunter[y][x].isWall();
    }

/**
 * 
 * Classe {@code InnerIAHunterCoordinate} permettant l'implémentation de linterface {@code ICoordinate}.<br>
 * Cette implémentation permet d'avoir accès au méthodes de {@code ICoordinate}. <br>
 * Cette classes est une classe interne à la classe {@code IAHunter} ce qui permet la portabilité de la classe {@code IAHunter}
 * dans le jeu des autres équipes.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
  */
public class InnerIAHunterCoordinate implements ICoordinate{


    /**
     * Valeur correspondant au nombre de ligne dans le terrain de jeu.
     */
    private int row;
    /**
     * Valeur correspondant au nombre de colonne dans le terrain de jeu.
     */
    private int col;


    /**
     * Constructeur de la classe {@link InnerIAHunterCoordinate} permettant de créer une coordonnée avec les numéros de colone et de ligne spécifiés.
     * 
     * @param col Le numéro de colone ( x ).
     * @param row Le numéro de ligne ( y ).
     */
    public InnerIAHunterCoordinate(int col, int row){
        this.col = col;
        this.row = row;
    }


    /**
     * Méthode {@code getCol} qui retourne le numéro de colonne de la coordonnée.
     * 
     * @return Le numéro de la colonne.
     */
    @Override
    public int getCol() {
        return this.col;
    }


    /**
     * Méthode {@code getRow} qui  retourne le numéro de ligne de la coordonnée.
     * 
     * @return Le numéro de la ligne.
     */
    @Override
    public int getRow() {
        return this.row;
    }

    /**
     * Méthode {@code hashCode} permettant de générer le hashCode.
     * @return Le hashCode.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + col;
        return result;
    }


    /**
     * Méthode {@code equals} permettant de comparer deux coordonées et de retourner
     * un booléen en fonction de si ils sont égaux ou non.
     * 
     * @param obj La deuxième coordonée pour la comparaison.
     * @return Le booléen qui définit si ils sont égaux ou non.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ICoordinate other = (ICoordinate) obj;
        if (row != other.getRow())
            return false;
        return col == other.getCol();
    }

    @Override
    public String toString() {
        return ""+this.col+";"+this.row;
    }
}

/**
 * Classe {@code InnerIAHunterCellEvent} permettant l'implémentation de linterface {@code ICellEvent} dans IAHunter.<br>
 * Cette implémentation permet d'avoir accès au méthodes de  {@code ICellEvent}.<br>
 * Il y a différentes cases définit par la classe interne {@code CellInfo}.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see CellInfo
 * @see Coordinate
 */
public class InnerIAHunterCellEvent implements ICellEvent{
    
    /**
     * L'information liée à cet événement.
     */
    private CellInfo info;
    /**
     * Les coodonnées liées à cet événement.
     */
    private ICoordinate coord;
    /**
     * Le tour lié à cet événement.
     */
    private int turn;

    /**
     * Constructeur principal de la classe {@link InnerIAHunterCellEvent} permettant de créer et d'initialiser un évènement qui sera associé à une cellule.
     * Permet de définir le type de cellule {@link CellInfo} (pour savoir si elle représente une cellule vide, une sortie, un mur,
     * l'emplacement du monstre ou celui du tir du chasseur), ainsi qu'un numéro de tour qui indique le tour pendant lequel l'évènement s'est passé.
     * 
     * @param info Le type de la cellule.
     * @param coord La coordonnée de la cellule.
     * @param turn Le tour pendant lequel le monstre est passé sur la cellule.
     */
    public InnerIAHunterCellEvent(CellInfo info, ICoordinate coord, int turn){
        this.info = info;
        this.coord = coord;
        this.turn = turn;
    }

    /**
     * Méthode {@code getState} retournant le type de la cellule (vide, sortie, mur, monstre ou tir du chasseur).
     * 
     * @return Le type de la cellule.
     */
    @Override
    public CellInfo getState() {
        return this.info;
    }

    /**
     * Méthode {@code getCoord} permettant d'obtenir les coordonnées liées aux événements.
     * 
     * @return La coordonnées liée à l'événement sur lequel cett méthode a été appelée.
     */
    @Override
    public ICoordinate getCoord() {
        return this.coord;
    }

    /**
     * Méthode {@code getTurn} permettant d'obtenir les tours liées aux événements.
     * 
     * @return Le tour lié à l'événement sur lequel cett méthode a été appelée.
     */
    @Override
    public int getTurn() {
        return this.turn;
    }

    /**
     * Méthode {@code isMonsterPassed} retourne {@code true} ou {@code false} selon si le monstre est déjà
     * passé au moins une fois sur la case.
     * 
     * @return {@code true} si le monstre est déjà passé par là, {@code false} sinon.
     */
    public boolean isMonsterPassed(){
        return this.turn > -1;
    }

    /**
     * Méthode {@code isMonster} vérifiant si le monstre est sur la case au tour demandé.
     * 
     * @return {@code true} si le monstre est sur la case au tour passé en paramètre, {@code false} sinon.
     */
    public boolean isMonster(){
        return this.info.equals(CellInfo.MONSTER);
    }

    /**
     * Méthode {@code isEmpty} vérifiant si la case est vide au tour demandé.
     * 
     * @return {@code true} si la case est vide au tour passé en paramètre, {@code false} sinon.
     */
    public boolean isEmpty(){
        return this.info.equals(CellInfo.EMPTY);
    }

    /**
     * Méthode {@code isWall} vérifiant si la case est un mur au tour demandé.
     * 
     * @return {@code true} si la case est un mur au tour passé en paramètre, {@code false} sinon.
     */
    public boolean isWall(){
        return this.info.equals(CellInfo.WALL);
    }

    /**
     * Méthode {@code isHunter} vérifiant si le chasseur a tiré sur la case au tour demandé.
     * 
     * @return {@code true} si le chasseur a tiré sur la case au tour passé en paramètre, {@code false} sinon.
     */
    public boolean isHunter(){
        return this.info.equals(CellInfo.HUNTER);
    }

    @Override
        public String toString() {
            return ""+this.coord.getCol()+","+this.coord.getRow();
        }
}


}
