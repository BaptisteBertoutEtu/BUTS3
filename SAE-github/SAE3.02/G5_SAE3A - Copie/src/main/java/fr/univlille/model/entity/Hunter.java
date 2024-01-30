package main.java.fr.univlille.model.entity;

import fr.univlille.iutinfo.cam.player.IStrategy;
import fr.univlille.iutinfo.cam.player.hunter.IHunterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import main.java.fr.univlille.model.cell.CellEvent;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.gameview.AllViewEnum;

/**
 * 
 * Classe {@code Hunter} permettant l'initialisation du chasseur. <br>
 * Le chasseur ne peut que tirer sur une case du plateau.<br>
 * Elle implémente l'interface {@link Observer} et hérite de la classe abstraite {@link Subject}, 
 * qui lui permettent de notifier l'objet {@code Monster} lorsqu'il effectue un tir, 
 * et d'avoir accès aux données de la case sur laquelle il vient de tirer.
 * Elle hérite de Entity pour pouvoir avoir accès à un observateur, observé et aussi pour l'intelligence artificielle
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 */
public class Hunter extends Entity{

    /*
     * Variable définissant si le monstre a été trouvé ou non
     */
    private boolean isMonster;

     /**
     *  Constructeur principale de la classe {@code Hunter} définissant le Hunter comme joueur.
     */
    public Hunter(){
        this.setIsIA(false);
    }

    /**
     * Constructeur secondaire de la classe {@code Hunter} définissant le Hunter comme Intelligence Artificielle avec une strategie en paramètre 
     * qui sera utilisée pour pouvoir jouer.
     * 
     * @param ia Ce paramètre représente la stratégie utilisée par Hunter
     */
    public Hunter(IStrategy ia){
        this.setIA(ia);
        this.setIsIA(true);
    }

    /**
     * Méthode {@code play()}  permettant de jouer avec l'intelligence artificielle, elle donnera une coordonnée et 
     * elle sera rentrée dans la méthode shoot pour jouer.
     */
    public void play() {
        ICoordinate iaCoordinate = this.getIA().play();
        this.shoot(iaCoordinate);
    }

    /**
     * Méthode {@code shoot(ICoordinate)} permettant au Chasseur de tirer et ainsi de nottifier le Monstre des coodonnées de la case sur laquelle
     * il vient de tirer, et de récupérer les informations de celle-ci en conséquence.
     * 
     * @param coord Ce paramètre représente la coordonnée de la case où le Chasseur a tiré.
     */
    public void shoot(ICoordinate coord){
        notifyObservers(coord);
    }

    /**
     * Méthode {@code update(Subject)} non utilisée ici.
     */
    @Override
    public void update(Subject subj) {
        // non utilisée
    }

    /**
     * Méthode {@code update(Subject, Object)} permettant de récuperer l'information envoyée par le sujet. <br>
     * Si cette information vient du sujet {@link Monster} et que l'information est une {@link CellEvent},
     * alors le Chasseur réagit en conséquence. Soit la coodonnée correspond à la position du monstre et dans ce cas il a gagné,
     * sinon il transmet l'information à sa vue pour l'afficher.
     * 
     * @param subj Ce paramètre représente le sujet qui envoie l'information.
     * @param data Ce paramètre représente l'information qui est envoyée par le sujet.
     */
    @Override
    public void update(Subject subj, Object data) {
        if(subj instanceof Monster && data instanceof CellEvent){
            CellEvent temp = (CellEvent) data;

            if(temp.isMonster()) {
                this.isMonster = true;
                notifyObservers(AllViewEnum.HUNTERWIN);
            }
            else if(!temp.isHunter()) {
                notifyObservers(temp);
                if(this.isIA()) this.getIA().update(temp);
            }
        }
    }

    /**
     * Méthode {@code initialize(int,int)} permettant de définir la taille du labyrinthe utilisée par l'IA du chasseur.
     * @param width Ce paramètre représente la heuteur du labyrinthe.
     * @param height Ce paramètre représente la largeur du labyrinthe.
     */
    public void initialize(int width, int height) {
        if(this.getIA()!=null) ((IHunterStrategy)this.getIA()).initialize(width, height);
    }

    /**
     * Méthode {@code getIsMonster()} permettant de savoir si le monstre a été découvert.
     * @return {@code true} si le monstre a été découvert, {@code false} sinon.
     */
    public boolean getIsMonster(){
        return this.isMonster;
    }

}
