package main.java.fr.univlille.view.gameview.allview;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.AllParametersView;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import main.java.fr.univlille.view.gameview.allview.allparameters.KnowledgeParameter;
import main.java.fr.univlille.view.gameview.allview.allparameters.MovementParameter;
import main.java.fr.univlille.view.gameview.allview.allparameters.PlayerParameter;
import main.java.fr.univlille.view.gameview.allview.allparameters.SizeParameter;


/**
 * 
 * Classe {@code ParametersView} permettant d'afficher en interface graphique les paramètres du jeu.<br>
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
public class ParametersView extends View{
    private Scene s ;
    private static final double WIDTH = MainView.bounds.getWidth();
    private static final double HEIGHT = MainView.bounds.getHeight();
    private static final String TITLE = "Paramètres";

    private Map<AllParametersView,VBox> map;
    private final AllParametersView[] allParameters;
    private int currentParameters;
    private VBox currentV ;
    private Button buttonSuivant;
    private Button buttonPrecedent;
    private Button currenChoice;
    private Map<Integer,Button> mapButtons;
    private Parameters para;
    private HBox h;

    /**
     * Constructeur de la classe {@link ParametersView} permettant de construire l'affichage des paramètres.
     * 
     * @param para Les paramètres liés à la vue.
     */
    public ParametersView(Parameters para){
        this.para=para;
        this.allParameters = new AllParametersView[]{AllParametersView.SIZE_PARAMETERS,AllParametersView.MOVEMENT_PARAMETERS, AllParametersView.KNOLEDGE_PARAMETERS, AllParametersView.PLAYER_PARAMETERS};
        this.currentParameters = 0;
        this.map = new HashMap<>();
        this.mapButtons = new HashMap<>();
        this.map.put(AllParametersView.SIZE_PARAMETERS, new SizeParameter(para));
        this.map.put(AllParametersView.MOVEMENT_PARAMETERS, new MovementParameter(para));
        this.map.put(AllParametersView.KNOLEDGE_PARAMETERS, new KnowledgeParameter(para));
        this.map.put(AllParametersView.PLAYER_PARAMETERS, new PlayerParameter(para));
        this.currentV = this.map.get(AllParametersView.SIZE_PARAMETERS);

        this.start();
    }


    /**
     * Méthode {@code getMyScene} héritée de la classe abstraite {@link View} qui retourne la {@code Scene} courante qui permettra d'affiché les paramètres.<br><br>
     * 
     * @return La {@code Scene} courante qui permettra d'afficher les paramètres.
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
        return ParametersView.TITLE;
    }

    /**
     * Affiche les règles suivantes et active le bouton précédent si possible.
     * 
     * @return Un conteneur VBox contenant les règles suivantes.
     */
    private VBox changeParametersPlus(){
        this.buttonPrecedent.setDisable(false);
        if(this.currentParameters+2 >= this.allParameters.length) buttonSuivant.setDisable(true);
        this.currentParameters++;
        this.currenChoice.setBorder(null);
        this.currenChoice = this.mapButtons.get(this.currentParameters);
        this.currenChoice.setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
        return this.map.get(this.allParameters[this.currentParameters]);
    }

    /**
     * Méthode {@code changeParameters} permettant de naviguer entre les différentes vues des paramètres, déclenchée par les boutons de navigation en bas de la page.
     * @param i
     * @return
     */
    private VBox changeParameters(int i){
        if(i==0) {
            this.buttonPrecedent.setDisable(true);
            this.buttonSuivant.setDisable(false);
        }
        else if(i==this.allParameters.length-1) {
            this.buttonPrecedent.setDisable(false);
            this.buttonSuivant.setDisable(true);
        }
        else{
            this.buttonPrecedent.setDisable(false);
            this.buttonSuivant.setDisable(false);
        }
        this.currentParameters = i;
        return this.map.get(this.allParameters[this.currentParameters]);
    }

    /**
     * Affiche les règles précédentes et active le bouton suivant si besoin.
     * 
     * @return Un conteneur VBox contenant les règles précédentes.
     */
    private VBox changeParametersMoins(){
        if(this.currentParameters+1<=this.allParameters.length) this.buttonSuivant.setDisable(false); 
        if(this.currentParameters-1 == 0) buttonPrecedent.setDisable(true);
        currentParameters--;
        this.currenChoice.setBorder(null);
        this.currenChoice = this.mapButtons.get(this.currentParameters);
        this.currenChoice.setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
        return this.map.get(this.allParameters[currentParameters]);
    }

    /**
     * Méthode {@code setAllEventOnNavigableButton} permettant de définir l'ensemble des opérations à effectuer lorsqu'un bouton de navigation est activé.
     * @param bp Le {@code BorderPane} dans lequel on affiche les différentes vues des paramètres.
     */
    private void setAllEventOnNavigableButton(BorderPane bp) {
        for (Map.Entry<Integer,Button> me : mapButtons.entrySet()) {
            me.getValue().setOnAction(e-> {
                bp.setCenter(this.changeParameters(me.getKey()));
                this.currenChoice.setBorder(null);
                me.getValue().setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
                this.currenChoice = me.getValue();
                notifyObservers(AllViewEnum.PARAMETERSVIEW);
            });
            h.getChildren().addAll(me.getValue());
        }
    }

    /**
     * Méthode {@code setAllNavigableButton} permettant de définir l'ensemble des boutons servant à naviguer entre les différentes pages des paramètres.
     */
    private void setAllNavigableButton() {
        Button temp;
        for (int i = 0; i < this.allParameters.length; i++) {
            temp = new Button(""+(i+1));
            temp.setFont(OftenUse.FONT_FOR_BUTTON);
            mapButtons.put(i, temp);

        }
        this.currenChoice = mapButtons.get(0);
        this.currenChoice.setBorder(OftenUse.BORDER_FOR_NVAIGABLE_BUTTON);
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage des paramètres.
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(ParametersView.WIDTH, ParametersView.HEIGHT-20);

        Label title = new Label("Tous les paramètres");
        title.setFont(Font.font(70));

        Label explcationMouvLabel = new Label("Ici vous pouvez choisir le type de déplacement du monstre.");

        explcationMouvLabel.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        
        RadioButton mouvementEight = new RadioButton("Mouvement en 8");
        RadioButton mouvementFour = new RadioButton("Mouvement en 4");

        mouvementEight.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        mouvementFour.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        h = new HBox(OftenUse.TEXT_SIZE);

        Button buttonExit = new Button("Quitter les paramètres");
        this.buttonSuivant = new Button(">");
        this.buttonPrecedent = new Button("<");
        bp.setLeft(buttonPrecedent);
        bp.setRight(buttonSuivant);
        BorderPane.setAlignment(buttonPrecedent, Pos.CENTER);
        BorderPane.setAlignment(buttonSuivant, Pos.CENTER);


        
        setAllNavigableButton();
        h.getChildren().add(buttonExit);
        setAllEventOnNavigableButton(bp);
        
        
        this.buttonPrecedent.setDisable(true);
        
        buttonPrecedent.setFont(OftenUse.FONT_FOR_BUTTON);
        buttonSuivant.setFont(OftenUse.FONT_FOR_BUTTON);
        buttonExit.setFont(OftenUse.FONT_FOR_BUTTON);
        
        h.setAlignment(Pos.CENTER);
        
        bp.setTop(title);
        bp.setCenter(this.currentV);
        bp.setBottom(h);

        BorderPane.setAlignment(title, Pos.CENTER);

        s = new Scene(bp);

        buttonSuivant.setOnAction(e -> {
            bp.setCenter(this.changeParametersPlus());
            notifyObservers(AllViewEnum.PARAMETERSVIEW);
        });

        buttonPrecedent.setOnAction(e -> {
            bp.setCenter(this.changeParametersMoins());
            notifyObservers(AllViewEnum.PARAMETERSVIEW);
        });

        buttonExit.setOnAction(e -> {
            this.para.updateParameters();
            notifyObservers();
        });
    }
    
}
