package main.java.fr.univlille.view.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.java.fr.univlille.model.cell.CellEvent;
import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.entity.Hunter;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import main.java.fr.univlille.view.gameview.allview.MainView;
import main.java.fr.univlille.view.gameview.allview.View;

/**
 * 
 * Classe {@code HunterView} permettant d'avoir une Interface Homme Machine. Celle-ci nous permet de joeur au jeu.<br>
 * La vue du plateau du chasseur est simple. Le chasseur ne peut voir que les cases sur lesquelles il a tiré.<br>
 * Lorsqu'il tire sur une case, celle-ci lui est révélée et il a accès aux données de cette case.<br>
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see ICellEvent
 * @see Stage
 * @see Observer
 */
public class HunterView extends View implements Observer{
    private Scene s ;
    private static final double WIDTH = MainView.bounds.getWidth();
    private static final double HEIGHT = MainView.bounds.getHeight();
    private static final String TITLE = "Vue du Chasseur";  

    private Hunter hunter;
    private GridPane gp;

    private Map<Coordinate,Button> mapText;
    private Map<Coordinate,Integer> path  = new HashMap<>();
    private List<CellEvent> discoveredCell = new ArrayList<>();



    /**
     * Constructeur de la classe {@link HunterView} qui permet de construire l'affichage de la vue du Chasseur.
     * 
     * @param hunter Le Chasseur lié à sa vue.
     * @see Hunter
     */
    public HunterView(Hunter hunter){
        this.mapText= new HashMap<>();
        this.hunter = hunter;
        this.hunter.attach(this);
        this.start();
    }
    
    
    /**
     * Méthode {@code getMyScene} héritée de la classe abstraite {@link View} qui retourne la {@code Scene} courante qui permettra d'affiché le jeu.<br><br>
     * 
     * @return La {@code Scene} courante qui permettra d'affiché la vue du Chasseur.
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
        return HunterView.TITLE;
    }

    @Override
    public void update(Subject subj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Subject subj, Object data) {
        if (subj instanceof Hunter && data instanceof AllViewEnum) {
            notifyObservers((AllViewEnum) data);
        }

        else if(subj instanceof Hunter && data instanceof CellEvent){
            CellEvent temp = (CellEvent) data;
            this.discoveredCell.add(temp);
            if(temp.isMonsterPassed()) {
                this.path.put((Coordinate)temp.getCoord(), (Integer)temp.getTurn());
                int path = temp.getTurn();
                if(path<=9) this.mapText.get(temp.getCoord()).setText(" "+path+" ");
                else this.mapText.get(temp.getCoord()).setText(""+path);
            }
            else{
                this.setProperties(temp);
            }
            notifyObservers(AllViewEnum.HUNTERVIEW);
        }

        
    }



    /**
     * Méthode {@code setAllCell} permettant de définir l'ensemble des boutons nécessaire au bon fonctionnement du jeu.
     */
    private void setAllCell(){
        Coordinate tempCoordinate;
        Button button;
        for (int i = 0; i < Parameters.lengthLabyrinth[0]; i++) {
            for (int j = 0; j < Parameters.lengthLabyrinth[1]; j++) {
                tempCoordinate = new Coordinate(i, j);
                button = new Button(OftenUse.EMPTY_TEXT_FOR_BUTTON);

                button.setStyle(OftenUse.STYLE_BACKGROUND_WHITE);
                button.setFont(OftenUse.FONT_FOR_TEXT_IN_BUTTON);

                this.mapText.put(tempCoordinate, button);
                this.gp.add(button, tempCoordinate.getCol(), tempCoordinate.getRow());
            }
        }
        this.setEvent();
        this.gp.setStyle(OftenUse.STYLE_FOR_GRIDPANE);
    }

    /**
     * Méthode {@code setAllProperties} permettant de définir les propriétées des boutons : la couleur de fond, la taille de police, les bordures.
     */
    private void setProperties(CellEvent event){
        Button tempButton = this.mapText.get(event.getCoord());
        tempButton.setStyle(event.isWall() ? OftenUse.STYLE_BACKGROUND_BLACK : OftenUse.STYLE_BACKGROUND_WHITE);
    }

    /**
     * Méthode {@code setEvent} permettant de définir les actions à réaliser lorsqu'on appuie sur un bouton. Ici on tire sur la case et on agit en conséquence.
     */
    private void setEvent(){
        for (Map.Entry<Coordinate,Button> me : this.mapText.entrySet()) {
            me.getValue().setOnAction(e -> {
                this.hunter.shoot(me.getKey());
            });
        }
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage de la vue de Chasseur.<br>
     */
    public void start(){
        BorderPane bp = new BorderPane();

        bp.setPrefSize(HunterView.WIDTH, HunterView.HEIGHT);


        Label title = new Label("Tirez !");
        title.setFont(OftenUse.FONT_FOR_TITLE);

        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);

        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        this.setAllCell();

        Button b = new Button("Sortir");
        b.setFont(OftenUse.FONT_FOR_BUTTON);
        v.getChildren().addAll(gp,b);

        bp.setTop(title);
        bp.setCenter(v);
        BorderPane.setAlignment(title, Pos.CENTER);

        
        b.setOnAction(e -> notifyObservers());
        
        s = new Scene(bp);
    }
}

