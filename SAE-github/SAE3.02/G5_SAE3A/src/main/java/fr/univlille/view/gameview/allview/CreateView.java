package main.java.fr.univlille.view.gameview.allview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.view.gameview.allview.allcreateview.CreateLabyView;
import main.java.fr.univlille.view.gameview.allview.allcreateview.SizeForCreateLaby;

/**
 * 
 * Classe {@code CreateView} permettant l'affichage de la fonctionnalité "création de labyrinthe".
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
public class CreateView extends View{
    private Scene s ;
    private static final String TITLE = "CreateView";
    private BorderPane bp = new BorderPane();

    private VBox sizeView;
    private VBox createLabyView;

    private int widht;
    private int height;

    /**
     * Constructeur de la classe {@link CreateView} permettant de construire la vue.
     */
    public CreateView(){
        this.sizeView = new SizeForCreateLaby(this);
        this.start();
    }

    /**
     * Retourne la scène associée à la vue de creation de labyrinthe.
     *
     * @return La scène de la vue de creation de labyrinthe.
     */
    @Override
    public Scene getMyScene() {
        bp.setCenter(this.sizeView);
        return this.s;
    }

    /**
     * Méthode {@code getTitle} qui retourne le titre de la vue
     * 
     * @return Le titre de la vue.
     */
    @Override
    public String getTitle() {
        return TITLE;
    }

    /**
     * Méthode {@code createLabyrinthe} qui crée la vue pour la construction du labyrinthe en utilisant les dimensions spécifiées.
     */
    public void createLabyrinthe(){
        this.createLabyView = new CreateLabyView(widht, height);
        bp.setCenter(this.createLabyView);
    }

    /**
     * Méthode {@code setSize} qui définit la taille du labyrinthe à créer avec la longueur et la largeur spécifiées.
     * 
     * @param widht La longueur du labyrinthe.
     * @param height La largeur du labyrinthe.
     */
    public void setSize(int widht,int height){
        this.widht = widht;
        this.height = height;
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage la vue pour la construction de labyrinthe.<br>
     */
    private void start(){
        bp.setPrefSize(View.WIDTH, View.HEIGHT);

        Label title = new Label(TITLE);
        title.setFont(OftenUse.FONT_FOR_TITLE);

        HBox h = new HBox(OftenUse.TEXT_SIZE);

        Button buttonExit = new Button("Revenir en arrière");
        
        h.getChildren().add(buttonExit);

        buttonExit.setFont(OftenUse.FONT_FOR_BUTTON);
        
        h.setAlignment(Pos.CENTER);
        
        bp.setTop(title);
        bp.setCenter(this.sizeView);
        bp.setBottom(h);

        BorderPane.setAlignment(title, Pos.CENTER);

        s = new Scene(bp);

        buttonExit.setOnAction(e -> notifyObservers());

        HBox.setMargin(buttonExit, new Insets(0, 0, 20, 0));


        DropShadow dropShadow = new DropShadow();
        buttonExit.setEffect(dropShadow);
    }
    
}
