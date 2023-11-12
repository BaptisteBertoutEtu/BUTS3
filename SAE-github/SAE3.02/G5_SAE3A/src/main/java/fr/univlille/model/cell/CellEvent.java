package main.java.fr.univlille.model.cell;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;


/**
 * 
 * Classe {@code CellEvent} permettant l'implémentation de linterface {@link ICellEvent}.<br>
 * Cette implémentation permet d'avoir accès au méthodes de  {@link ICellEvent}.<br>
 * Il y a différente case définit par la classe interne {@link CellInfo}.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see ICellEvent
 */


public class CellEvent implements ICellEvent{
    
    private CellInfo info;
    private Coordinate coord;
    private int turn;

    /**
     * Constructeur principal de la classe {@link CellEvent} permettant de créer et d'initialiser un évènement qui sera associé à une cellule.
     * Permet de définir le type de cellule {@link CellInfo} (pour savoir si elle représente une cellule vide, une sortie, un mur,
     * l'emplacement du monstre ou celui du tir du chasseur), ainsi qu'un numéro de tour qui indique le tour pendant lequel l'évènement s'est passé.
     * 
     * @param info Le type de la cellule.
     * @param coord La coordonnée de la cellule.
     * @param turn Le tour pendant lequel le monstre est passé sur la cellule.
     */
    public CellEvent(CellInfo info, Coordinate coord, int turn){
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
     * Méthode {@code isMonsterPassed} retourne {{@code true}} ou {{@code false}} selon si le monstre est déjà
     * passé au moins une fois sur la case.
     * 
     * @return {{@code true}} si le monstre est déjà passé par là, {{@code false}} sinon.
     */
    public boolean isMonsterPassed(){
        return this.turn > -1;
    }

    /**
     * Méthode {@code isMonster} vérifiant si le monstre est sur la case au tour demandé.
     * 
     * @return {{@code true}} si le monstre est sur la case au tour passé en paramètre, {{@code false}} sinon.
     */
    public boolean isMonster(){
        return this.info.equals(CellInfo.MONSTER);
    }

    /**
     * Méthode {@code isEmpty} vérifiant si la case est vide au tour demandé.
     * 
     * @return {{@code true}} si la case est vide au tour passé en paramètre, {{@code false}} sinon.
     */
    public boolean isEmpty(){
        return this.info.equals(CellInfo.EMPTY);
    }

    /**
     * Méthode {@code isWall} vérifiant si la case est un mur au tour demandé.
     * 
     * @return {{@code true}} si la case est un mur au tour passé en paramètre, {{@code false}} sinon.
     */
    public boolean isWall(){
        return this.info.equals(CellInfo.WALL);
    }

    /**
     * Méthode {@code isHunter} vérifiant si le chasseur a tiré sur la case au tour demandé.
     * 
     * @return {{@code true}} si le chasseur a tiré sur la case au tour passé en paramètre, {{@code false}} sinon.
     */
    public boolean isHunter(){
        return this.info.equals(CellInfo.HUNTER);
    }
}
