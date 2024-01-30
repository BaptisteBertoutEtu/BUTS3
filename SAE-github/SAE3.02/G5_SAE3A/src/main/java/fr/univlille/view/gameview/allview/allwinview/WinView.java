package main.java.fr.univlille.view.gameview.allview.allwinview;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.allview.View;

/**
 * 
 * Classe abstraite {@code WinView} permettant d'afficher la page gagnante d'une entité lorsqu'elle a gagné.<br>
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 * 
 * @see View
 */
public abstract class WinView extends View{
    protected Scene s ;
    protected static final String TITLE = "WIN";  
    protected String label;
    protected String text;

    
    /**
     * Méthode {@code getMyScene} héritée de la classe abstraite {@link View} qui retourne la {@code Scene} courante qui permettra d'affiché le Menu.
     * 
     * @return La {@code Scene} courante qui permettra d'afficher la vue gagnante du Monstre.
     */
    @Override
    public Scene getMyScene() {
        return this.s;
    }

    /**
     * Méthode {@code getTitle} héritée de la classe abstraite {@link View} qui retourne le titre de la fenêtre.<br><br>
     * 
     * @return Le titre de la fenêtre.
     */
    @Override
    public String getTitle() {
        return WinView.TITLE;
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage de la page gagnante du Chasseur.
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);

        Label titleForLabel = new Label(this.label);
        titleForLabel.setFont(OftenUse.FONT_FOR_TITLE);

        
        Text t = new Text(this.text);
        t.setFont(OftenUse.FONT_FOR_WIN);
        
        Button b = new Button("Sortir");
        b.setFont(OftenUse.FONT_FOR_BUTTON);
        
        
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(t,b);

        bp.setTop(titleForLabel);
        bp.setCenter(v);
        BorderPane.setAlignment(titleForLabel, Pos.CENTER);

        
        b.setOnAction(e -> notifyObservers());
        
        s = new Scene(bp);

    }
}
