package main.java.fr.univlille.view.gameview.allview.allcreateview;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.board.LabyPredef;
import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.utils.OftenUse;

/**
 * 
 * Classe {@code CreateLabyView} permettant d'afficher l'interface permettant de créer son propre labyrinthe.
 * Vous pouvez placer des mûrs où vous le souhaitez sauf dans les coins, où sera placer aléatoirement la sortie.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 */
public class CreateLabyView extends VBox{
    private int widht;
    private int height;

    private GridPane gp;
    private Label verifSave = new Label();
    private boolean[][] maze;
    private Map<Coordinate,Button> mapText;

    
    public CreateLabyView(int widht, int height){
        this.widht = widht;
        this.height = height;
        this.mapText = new HashMap<>();
        this.gp = new GridPane();
        this.maze = new boolean[height][widht];
        this.setFullTrue();
        this.start();
    }

    private void setFullTrue() {
        for (int y = 0; y < this.maze.length; y++) {
            for (int x = 0; x < this.maze[0].length; x++) {
                this.maze[y][x] = true;
            }
        }
    }

    private void setAllCell(){
        Coordinate tempCoordinate;
        Button button;
        int[][] temp = setPostion();
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.widht; x++) {

                tempCoordinate = new Coordinate(x, y);
                button = new Button(OftenUse.EMPTY_TEXT_FOR_BUTTON);
                
                button.setStyle(OftenUse.STYLE_BACKGROUND_WHITE);
                button.setFont(OftenUse.FONT_FOR_TEXT_IN_BUTTON);
                
                for (int i = 0; i < temp.length; i++) {
                    if(temp[i][0]==x && temp[i][1]==y) button.setDisable(true);
                }
                this.mapText.put(tempCoordinate, button);
                this.gp.add(button, tempCoordinate.getCol(), tempCoordinate.getRow());
            }
        }
        this.gp.setStyle(OftenUse.STYLE_FOR_GRIDPANE);
    }


    private int[][] setPostion(){
        return new int[][]{
            {this.maze[0].length-1, 0},
            {0,this.maze.length-1},
            {this.maze[0].length-1, this.maze.length-1},
            {0, 0}
        };
    }

    private void saveThisLabyrinthe(){
        try{
            LabyPredef.saveLaby(maze);
            this.verifSave.setText("Labyrinthe enregistré");
        } catch (Exception e) {
            this.verifSave.setText("Erreur : labyrinthe non enregistré");
        }
    }

    private void changeColor(Coordinate c,Button b){
        String style = b.getStyle();
        b.setStyle(style.equals(OftenUse.STYLE_BACKGROUND_WHITE) ? OftenUse.STYLE_BACKGROUND_BLACK : OftenUse.STYLE_BACKGROUND_WHITE);
        style = b.getStyle();
        this.maze[c.getRow()][c.getCol()] = style.equals(OftenUse.STYLE_BACKGROUND_WHITE);
    }

    private void start(){
        this.setAllCell();

        for (Map.Entry<Coordinate,Button> me : this.mapText.entrySet()) {
            me.getValue().setOnAction(e -> this.changeColor(me.getKey(),me.getValue()));
        }

        Button save = new Button("Enregistrer le labyrinthe");
        save.setFont(OftenUse.FONT_FOR_BUTTON);
        save.setOnAction(e -> this.saveThisLabyrinthe());

        HBox hForGp = new HBox(gp);
        hForGp.setAlignment(Pos.CENTER);

        HBox h = new HBox(OftenUse.SPACE_FOR_BUTTON,save,verifSave);
        h.setAlignment(Pos.CENTER);
        
        this.getChildren().addAll(hForGp,h);
        this.setAlignment(Pos.CENTER);
    }
}
