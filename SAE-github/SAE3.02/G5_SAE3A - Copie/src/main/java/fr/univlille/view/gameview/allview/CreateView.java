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

public class CreateView extends View{
    private Scene s ;
    private static final String TITLE = "CreateView";
    private BorderPane bp = new BorderPane();

    private VBox sizeView;
    private VBox createLabyView;

    private int widht;
    private int height;

    public CreateView(){
        this.sizeView = new SizeForCreateLaby(this);
        this.start();
    }

    @Override
    public Scene getMyScene() {
        bp.setCenter(this.sizeView);
        return this.s;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    public void createLabyrinthe(){
        this.createLabyView = new CreateLabyView(widht, height);
        bp.setCenter(this.createLabyView);
    }

    public void setSize(int widht,int height){
        this.widht = widht;
        this.height = height;
    }

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
