package main.java.fr.univlille.view.gameview.allview.iaview;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import main.java.fr.univlille.view.gameview.allview.View;

/**
 * 
 * Classe {@code IAHunterView} permettant un affichage lorsque l'IA du Chasseur est en train de jouer.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 */
public abstract class IAView extends View{
    
    protected Scene s;
    protected String title;
    protected String text;
    protected AllViewEnum view;

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
        return this.title;
    }

    /**
     * Méthode {@code start()} permettant la construction de l'affichage de la vue de Chasseur.<br>
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);

        Label titleForScene = new Label(this.title);
        titleForScene.setFont(OftenUse.FONT_FOR_TITLE);

        Text t = new Text(this.text);
        t.setFont(OftenUse.FONT_FOR_WIN);

        Button go = new Button("Jouer");
        go.setFont(OftenUse.FONT_FOR_BUTTON);
        
        Button b = new Button("Sortir");
        b.setFont(OftenUse.FONT_FOR_BUTTON);
        
        VBox v = new VBox(OftenUse.SPACE_BETWEEN_LABEL);
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(t,go);

        VBox bottom = new VBox(b);
        bottom.setAlignment(Pos.CENTER);

        bp.setTop(titleForScene);
        bp.setCenter(v);
        bp.setBottom(bottom);
        BorderPane.setAlignment(titleForScene, Pos.CENTER);

        b.setOnAction(e -> notifyObservers());
        
        go.setOnAction(e -> notifyObservers(this.view));
        
        this.s = new Scene(bp);

    }
}
