package main.java.fr.univlille.view.entity;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.Game;
import main.java.fr.univlille.model.cell.CellEvent;
import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.entity.Hunter;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.gameview.AllViewEnum;
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
 * @see Observer
 */
public class HunterView extends View implements Observer{
    private Scene s ;
    private static final String TITLE = "Vue du Chasseur";  

    private Hunter hunter;
    private GridPane gp;

    private Map<Coordinate,Button> mapText;
    private Label nbrTour;




    /**
     * Constructeur de la classe {@link HunterView} qui permet de construire l'affichage de la vue du Chasseur.
     * 
     * @param hunter Ce paramètre représente le Chasseur lié à sa vue.
     * @see Hunter
     */
    public HunterView(Hunter hunter){
        this.mapText= new HashMap<>();
        this.hunter = hunter;
        this.hunter.attach(this);
        this.start();
    }
    
    
    /**
     * Méthode {@code getMyScene()} héritée de la classe abstraite {@link View} qui retourne la {@code Scene} courante qui permettra d'affiché le jeu.<br><br>
     * 
     * @return Cette méthode retourne la {@code Scene} courante qui permettra d'affiché la vue du Chasseur.
     */
    @Override
    public Scene getMyScene() {
        return this.s;
    }

    /**
     * Méthode {@code getTitle()} héritée de la classe abstraite {@link View} qui retourne le titre de la fenêtre.
     * 
     * @return Cette méthode retourne le titre de la fenêtre.
     */
    @Override
    public String getTitle() {
        return HunterView.TITLE;
    }

    /**
     * Méthode {@code update(Subject)} non utilisée ici.
     * 
     * @param subj Ce paramètre représente l'expéditeur de l'information.
     */
    @Override
    public void update(Subject subj) {
        // non utilisée
    }

    /**
     * Méthode {@code update(Subject, Object)} permettant de recevoir une information et de mettre à jour l'affichage du chasseur en concéquence.
     * 
     * @param subj Ce paramètre représente l'expéditeur de l'information.
     * @param data Ce paramètre représente la donnée envoyée.
     */
    @Override
    public void update(Subject subj, Object data) {
        if (subj instanceof Hunter && data instanceof AllViewEnum) {
            notifyObservers(data);
        }

        else if(subj instanceof Hunter && data instanceof CellEvent){
            CellEvent temp = (CellEvent) data;
            if(temp.isMonsterPassed()) {
                int turn = temp.getTurn();

                if(turn<=9) this.mapText.get(new Coordinate(temp.getCoord().getCol(), temp.getCoord().getRow())).setText(" "+turn+" ");
                else this.mapText.get(new Coordinate(temp.getCoord().getCol(), temp.getCoord().getRow())).setText(""+turn);
            }
            else{
                this.setProperties(temp);
            }
            nbrTour.setText("Tour n°"+Game.currentTurn);
            if(!this.hunter.isIA()) notifyObservers(AllViewEnum.HUNTERVIEW);
        }

        
    }



    /**
     * Méthode privée {@code setAllCell()} permettant de définir l'ensemble des boutons nécessaire au bon fonctionnement du jeu.
     */
    private void setAllCell(){
        Coordinate tempCoordinate;
        Button button;
        for (int y = 0; y < Parameters.lengthLabyrinth[1]; y++) {
            for (int x = 0; x < Parameters.lengthLabyrinth[0]; x++) {
                tempCoordinate = new Coordinate(x, y);
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
     * Méthode privée privée {@code setAllProperties(CellEvent)(CellEvent)} permettant de définir les propriétées des boutons : la couleur de fond, la taille de police, les bordures.
     * 
     * @param event Ce paramètre représente un événement à partir duquel 
     * TODO: revoir ce que fait cette méthode
     * 
     * @param event Ce paramètre représente un événement à partir duquel 
     */
    private void setProperties(CellEvent event){
        for (Coordinate coord : mapText.keySet()) {
            if(coord.getCol() == event.getCoord().getCol() && coord.getRow() == event.getCoord().getRow()){
                Button tempButton = this.mapText.get(coord);
                tempButton.setStyle(event.isWall() ? OftenUse.STYLE_BACKGROUND_BLACK : OftenUse.STYLE_BACKGROUND_WHITE);
            }
        }
    }

    /**
     * Méthode privée {@code setEvent()} permettant de définir les actions à réaliser lorsqu'on appuie sur un bouton. Ici on tire sur la case et on agit en conséquence.
     */
    private void setEvent(){
        for (Map.Entry<Coordinate,Button> me : this.mapText.entrySet()) {
            me.getValue().setOnAction(e -> this.hunter.shoot(me.getKey()));
        }
    }

    /**
     * Méthode {@code start()} permettant la construction de l'affichage de la vue de Chasseur.<br>
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);

        Label title = new Label("Allez "+Game.nameHunter+", Tirez !");
        title.setFont(OftenUse.FONT_FOR_TITLE);

        nbrTour = new Label("Tour n°"+Game.currentTurn);
        nbrTour.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        this.setAllCell();
        
        Button b = new Button("Sortir");
        b.setFont(OftenUse.FONT_FOR_BUTTON);

        VBox.setMargin(b, new Insets(20, 0, 0, 0));
        
        DropShadow dropShadow = new DropShadow();
        b.setEffect(dropShadow);
        
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(gp,b);

        VBox top = new VBox();
        top.setAlignment(Pos.CENTER);
        top.getChildren().addAll(title,nbrTour);

        bp.setTop(top);
        bp.setCenter(v);
        BorderPane.setAlignment(title, Pos.CENTER);

        
        b.setOnAction(e -> notifyObservers());
        
        s = new Scene(bp);
    }
}