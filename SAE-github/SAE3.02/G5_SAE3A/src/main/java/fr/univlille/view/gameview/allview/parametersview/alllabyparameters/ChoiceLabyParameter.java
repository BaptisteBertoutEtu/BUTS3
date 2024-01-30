package main.java.fr.univlille.view.gameview.allview.parametersview.alllabyparameters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.board.LabyPredef;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;

/**
 * 
 * Classe {@code ChoiceLabyParameter} permettant d'afficher la page de paramètre servant au changement de choix du labyrinthe.<br>
 * Dans cette page vous pouvez choisir entre deux labyrinthe. Soit un labyrinthe généré aléatoirement, soit un labyrinthe prédéfini. <br>
 * Par défaut, il y a 3 labyrinthes prédéfinit créés pour vous.
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
public class ChoiceLabyParameter extends VBox{
    
    private static final String PATH = "res/labyPredef/";
    private Parameters para;
    private List<String> listOfPath;
    private ChoiceBox<String> box;


    /**
     * Constructeur de la classe {@code ChoiceLabyParameter} permettant de construire la vue.
     * @param para Ce paramètre représente l'objet {@link Parameters} permettant de mettre à jour les paramètres du jeu.
     */
    public ChoiceLabyParameter(Parameters para){
        this.para = para;
        box = new ChoiceBox<>();
        this.listOfPath = new ArrayList<>();
        this.setAllPath();
        this.start();
    }

    /**
     * Méthode {@code setAllPath()} permettant d'afficher l'ensemble des labrinthes prédéfinis se trouvant dans le répertoire "res/labyPredef/".
     */
    public void setAllPath(){
        this.listOfPath.clear();
        box.getItems().clear();
        File dir  = new File(PATH);
        File[] liste = dir.listFiles();
        LabyPredef.nbLabySave = liste.length;
        for(File item : liste){
            this.listOfPath.add(item.getName());
            box.getItems().add(item.getName());
        }
    }

    /**
     * Méthode {@code start} permettant la construction de l'affichage la vue pour changer les paramètres de connaissance du labyrinthe.<br>
     */
    public void start(){
        

        Label explicationLaby = new Label("Ici, vous pouvez choisir le labyrinthe que vous voulez utiliser, soit un labyrinthe aléatoire, soit l'un des trois labyrinthes prédéfinis.");
        Label numLabyPredef = new Label("Labyrinthes prédéfini :");

        explicationLaby.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        numLabyPredef.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        RadioButton labyRandom = new RadioButton("Labyrinthe aléatoire");
        RadioButton labyPredef = new RadioButton("Labyrinthe prédéfini");

        labyPredef.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        labyRandom.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        labyRandom.setSelected(true);
        box.setDisable(true);

        labyRandom.setOnAction(e->{
            this.para.labyRandom();
            labyRandom.setSelected(true);
            labyPredef.setSelected(false);

            box.setDisable(true);
        });

        labyPredef.setOnAction(e->{
            labyPredef.setSelected(true);
            labyRandom.setSelected(false);

            box.setDisable(false);
        });

        box.setOnAction( e -> {
            int temp = box.getSelectionModel().selectedIndexProperty().intValue();
            this.para.setLabyPredef(this.listOfPath.get(temp));
        });


        HBox h = new HBox();
        h.setAlignment(Pos.CENTER);
        h.getChildren().addAll(numLabyPredef,box);

        this.getChildren().addAll(explicationLaby, labyRandom, labyPredef, h);

        this.setAlignment(Pos.CENTER);

        HBox.setMargin(box, new Insets(0, 0, 0, 10));

    }
}
