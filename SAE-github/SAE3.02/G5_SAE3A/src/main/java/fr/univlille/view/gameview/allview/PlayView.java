package main.java.fr.univlille.view.gameview.allview;

import javafx.scene.Scene;
import main.java.fr.univlille.model.Game;
import main.java.fr.univlille.model.entity.Hunter;
import main.java.fr.univlille.model.entity.Monster;
import main.java.fr.univlille.model.parameters.AllParameters;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.entity.HunterView;
import main.java.fr.univlille.view.entity.MonsterView;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import main.java.fr.univlille.view.gameview.allview.changeview.ChangePlayerHunter;
import main.java.fr.univlille.view.gameview.allview.changeview.ChangePlayerMonster;
import main.java.fr.univlille.view.gameview.allview.iaview.IAHunterView;
import main.java.fr.univlille.view.gameview.allview.iaview.IAMonsterView;
import main.java.fr.univlille.view.gameview.allview.otherview.NameView;



/**
 * 
 * Classe {@code PlayView} permettant d'afficher en interface graphique le jeu en lui même (les différentes vues du jeu).<br>
 * *
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

    private Monster monster;
    private Hunter hunter;

    private IAMonsterView iaMonsterView;
    private IAHunterView iaHunterView;

    private ChangePlayerHunter changePlayerHunter;
    private ChangePlayerMonster changePlayerMonster;

    /**
     * Méthode {@code setName()} permettant de définir les noms des joueurs.
     */
    public void setName(){
        View nameView = new NameView();
        nameView.attach(this);
        s = nameView.getMyScene();
    }
    
    /**
     * Méthode {@code setNewGame} permettant de créer un nouveau jeu.
     */
    public void setNewGame(){
        Game game = new Game();

        this.monster = game.getMonster();
        this.hunter = game.getHunter();

        this.monsterView = new MonsterView(this.monster);
        this.hunterView = new HunterView(this.hunter);

        this.monsterView.attach(this);
        this.hunterView.attach(this);

        if(Parameters.gameMode.equals(AllParameters.HUNTER_AI_ONLY) || Parameters.gameMode.equals(AllParameters.MONSTER_AI_ONLY)) {
            this.iaMonsterView = new IAMonsterView();
            this.iaHunterView = new IAHunterView();
            this.iaHunterView.attach(this);
            this.iaMonsterView.attach(this);

            if(this.monster.isIA()){
                this.currentView = iaMonsterView;
                this.monster.play();
            }
            else{
                this.currentView = monsterView;
            }

        }
        else if(this.monster.isIA() && Parameters.gameMode.equals(AllParameters.BOTH_AI)){
            this.monster.play();
            this.currentView = monsterView;
            this.setCurrentView();
            this.changeView(AllViewEnum.MONSTERVIEW);
        }
        else {
            this.changePlayerHunter = new ChangePlayerHunter();
            this.changePlayerMonster = new ChangePlayerMonster();
            this.changePlayerHunter.attach(this);
            this.changePlayerMonster.attach(this);


            this.currentView = monsterView;
        }

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
        if(((AllViewEnum)data).equals(AllViewEnum.NAMEVIEW)) {
            this.setNewGame();
            notifyObservers(AllViewEnum.PLAYVIEW); 
        }
        else if(((AllViewEnum)data).equals(AllViewEnum.HUNTERWIN) || ((AllViewEnum)data).equals(AllViewEnum.MONSTERWIN)) notifyObservers(data);
        else changeView((AllViewEnum)data);
    }
    
    /**
     * Méthode privée {@code changeView} permettant de changer de vue. Alterner la vue entre la vue du Monstre et la vue du Chasseur.
     * 
     * @param view La vue courante.
     */
    private void changeView(AllViewEnum view){
        if(Parameters.gameMode.equals(AllParameters.BOTH_AI)){
            this.changeViewBothIA(view);
        }
        else if (Parameters.gameMode.equals(AllParameters.MONSTER_AI_ONLY) || Parameters.gameMode.equals(AllParameters.HUNTER_AI_ONLY)) {
            this.changeViewForOneIa(view);
        }
        else if(view.equals(AllViewEnum.CHANGEPLAYERHUNTER) || view.equals(AllViewEnum.CHANGEPLAYERMONSTER)){
            this.currentView = view.equals(AllViewEnum.CHANGEPLAYERHUNTER) ? this.monsterView : this.hunterView;
            this.setCurrentView();
        }
        else {
            this.currentView = view.equals(AllViewEnum.MONSTERVIEW) ? this.changePlayerMonster : this.changePlayerHunter;
            this.setCurrentView();
        }
    }

    /**
     * Méthode privée {@code changeViewBothIA} permettant de changer de vue lorsque les deux entitées sont des IA. Alterner la vue entre la vue du Monstre et la vue du Chasseur.
     * 
     * @param view La vue courante.
     */
    private void changeViewBothIA(AllViewEnum view){
        if(view.equals(AllViewEnum.MONSTERVIEW)){
            this.hunter.play();
            this.changeView(AllViewEnum.HUNTERVIEW);
        }
        else if(view.equals(AllViewEnum.HUNTERVIEW)){
            this.monster.play();
            this.changeView(AllViewEnum.MONSTERVIEW);
        }
    }

    /**
     * Méthode privée {@code changeViewForOneIa} permettant de changer de vue lorsque une des deux entités est une IA. Alterner la vue entre la vue du Monstre et la vue du Chasseur.
     * 
     * @param view La vue courante.
     */
    private void changeViewForOneIa(AllViewEnum view){
        if(view.equals(AllViewEnum.MONSTERVIEW) && this.hunter.isIA()) {
            this.currentView = iaHunterView;
            this.hunter.play();
            if(!this.hunter.getIsMonster()) this.setCurrentView();
        }
        else if(view.equals(AllViewEnum.HUNTERVIEW) && this.monster.isIA()) {
            this.currentView = iaMonsterView;
            if (!this.monster.play()) this.setCurrentView();
        }
        else{
            this.currentView = view.equals(AllViewEnum.HUNTERVIEW) ? this.monsterView : this.hunterView;
            this.setCurrentView();
        }
    }

    /**
     * Méthode {@code setCurrentView()} permettant de changer la scène de la vue courante.
     */
    /**
     * Méthode {@code setCurrentView()} permettant de changer la scène de la vue courante.
     */
    private void setCurrentView(){
        s=this.currentView.getMyScene();
        notifyObservers(AllViewEnum.PLAYVIEW);
    }
}