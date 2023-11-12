package main.java.fr.univlille.model.entity;

import java.util.HashMap;
import java.util.Map;

import fr.univlille.iutinfo.cam.player.hunter.IHunterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import main.java.fr.univlille.model.cell.CellEvent;
import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.gameview.AllViewEnum;

/**
 * 
 * Classe Hunter peremttant l'initialisation du chasseur.<br>
 * Le chasseur ne peut que tirer sur une case du plateau.<br>
 * Cette classe implémente l'interface IHunterStrategy pour avoir accès à ses méthodes.<br>
 * Elle implémente également l'interface Observer et hérite de la classe abstraite Subject, 
 * qui lui permettent de notifier l'objet Monster lorsqu'il effectue un tir, 
 * et d'avoir accès aux données de la case sur laquelle il vient de tirer
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see Subject
 * @see IHunterStrategy
 * @see Observer
 */
public class Hunter extends Subject implements IHunterStrategy, Observer {

    private Map<Coordinate,Integer> pathDiscover  = new HashMap<>();

    /**
     * 
     */
    @Override
    public ICoordinate play() {
        return null;
    }

    /**
     * Méthode {@code shoot} permettant au Chasseur de tirer et ainsi de nottifier le Monstre des coodonnées de la case sur laquelle
     * il vient de tirer, et de récupérer les informations de celle-ci en conséquence.
     * 
     * @param coord La coordonnée de la case où le Chasseur a tiré.
     */
    public void shoot(Coordinate coord){
        notifyObservers(coord);
    }

    /**
     * Méthode {@code update} qui retourne un Exception qui signifie que la méthode 
     * n'a pas été implémentée, donc pas utilisée.
     * 
     * @throws UnsupportedOperationException L'exception est levé si la méthode est appelée.
     */
    @Override
    public void update(Subject subj) {
        
    }

    /**
     * Méthode {@code update} permettant de récuperer l'information demandée par le Chasseur en fonction de la coordonnée passée en paramètre,
     * et de lui renvoyer.
     * 
     * @param data La coordonnée que le Chasseur envoie pour avoir l'information lié à cette coodonnée.
     */
    @Override
    public void update(Subject subj, Object data) {
        if(subj instanceof Monster && data instanceof CellEvent){
            CellEvent temp = (CellEvent) data;
            if(temp.isMonster()) notifyObservers(AllViewEnum.HUNTERWIN);
            else if(!temp.isHunter()) notifyObservers(temp);
        }
    }

    /**
     * Méthode {@code initialize} qui initialise le tableau du hunter 
     * avec un tableau de cell vide selon certaines dimension
     * 
     * @param arg0 Le nombre de lignes du tableau.
     * @param arg1 Le nombre de colonnes du tableau.
     */
    @Override
    public void initialize(int arg0, int arg1) {
        // this.hu = Board.initialize(arg0, arg1);
    }
    
    /**
     * Méthode {@code update} non utilisé ici.
     * 
     * @throws UnsupportedOperationException L'exception est levé si la méthode est appelée.
     */
    @Override
    public void update(ICellEvent arg0) {
        throw new UnsupportedOperationException();
    }

    /**
     * Méthode {@code getPathDiscover} qui retourne l'ensemble du chemin parcouru par le monstre découvert par le chasseur.
     * 
     * @return Le chemin parcouru et décourvert par le chasseur.
     */
    public Map<Coordinate,Integer> getPathDiscover(){
        return this.pathDiscover;
    }
    
}
