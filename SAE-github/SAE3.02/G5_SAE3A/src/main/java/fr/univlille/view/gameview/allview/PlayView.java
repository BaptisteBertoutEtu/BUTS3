package main.java.fr.univlille.view.gameview.allview;

import javafx.scene.Scene;
import main.java.fr.univlille.model.Game;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.entity.HunterView;
import main.java.fr.univlille.view.entity.MonsterView;
import main.java.fr.univlille.view.gameview.AllViewEnum;



/**
 * 
 * Classe {@code PlayView} permettant d'afficher en interface graphique le jeu en lui même (les différentes vues du jeu).<br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 * 
 */
public class PlayView extends View implements Observer{
    private Scene s ;
    private static final String TITLE = "Jouer";
    private HunterView hunterView;
    private MonsterView monsterView;
    private View currentView;

    /**
     * Méthode {@code setNewGame} permettant de créer un nouveau jeu.
     */
    public void setNewGame(){
        Game game = new Game();
        this.monsterView = new MonsterView(game.getMonster());
        this.hunterView = new HunterView(game.getHunter());
        this.currentView = this.monsterView;
        this.monsterView.attach(this);
        this.hunterView.attach(this);
        s = this.currentView.getMyScene();
    }

    /**
     * Méthode {@code getMyScene} héritée de la classe abstraite {@link View} qui retourne la {@code Scene} courante qui permettra d'affiché le jeu.<br><br>
     * 
     * @return La {@code Scene} courante qui permettra d'afficher le Jeu.
     */
    @Override
    public Scene getMyScene() {
        return this.s;
    }

    /**
     * Méthode {@code getTitle} héritée de la classe abstraite {@link View} qui retourne le titre de la fenêtre.
     * 
     * @return Le titre de la fenêtre.
     */
    @Override
    public String getTitle() {
        return PlayView.TITLE;
    }

    /**
     * Méthode {@code update} permettant de notifier un changement de vue à la classe {@link MenuView}. Ici il sagit d'un retour à la vue du Menu.
     */
    @Override
    public void update(Subject subj) {
        notifyObservers();
    }
    
    /**
     * Méthode {@code update} permettant de notifier un changement de vue à la classe {@link MenuView}. Ici il s'agit soit d'une vue gagnante, 
     * soit d'un changement de vue.
     */
    @Override
    public void update(Subject subj, Object data) {
        if(((AllViewEnum)data).equals(AllViewEnum.HUNTERWIN) || ((AllViewEnum)data).equals(AllViewEnum.MONSTERWIN)) notifyObservers(data);
        else changeView((AllViewEnum)data);
    }

    /**
     * Méthode {@code changeView} permettant de changer de vue. Alterner la vue entre la vue du Monstre et la vue du Chasseur.
     * @param view La vue courante.
     */
    private void changeView(AllViewEnum view){
        if(view.equals(AllViewEnum.MONSTERVIEW)) this.currentView = this.hunterView;
        else {
            this.currentView = this.monsterView;
        }

        s=this.currentView.getMyScene();

        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        notifyObservers(AllViewEnum.PLAYVIEW);
    }

    
}
