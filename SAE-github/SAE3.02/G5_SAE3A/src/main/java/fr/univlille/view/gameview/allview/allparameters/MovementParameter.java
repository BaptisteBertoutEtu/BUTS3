package main.java.fr.univlille.view.gameview.allview.allparameters;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;

/**
 * 
 * Classe {@code KnowledgeParameter} permettant d'afficher la vue pour changer les paramètres de mouvements dans le labyrinthe.<br>
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
public class MovementParameter extends VBox{

    private Parameters para;

    /**
     * Constructeur de la classe {@link MovementParameter} permettant de construire la vue.
     * @param para L'objet {@link Parameters} permettant de mettre à jour les paramètres du jeu.
     */
    public MovementParameter(Parameters para){
        this.para=para;
        this.start();
    }   

    
    /**
     * Méthode {@code start} permettant la construction de l'affichage la vue pour changer les paramètres de mouvements dans le labyrinthe.<br>
     */
    public void start(){

        Label explcationMouvLabel = new Label("Ici vous pouvez choisir le type de déplacement du monstre.");

        explcationMouvLabel.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        RadioButton mouvementEight = new RadioButton("Mouvement en 8");
        RadioButton mouvementFour = new RadioButton("Mouvement en 4");

        mouvementEight.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        mouvementFour.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        mouvementEight.setSelected(true);

        mouvementEight.setOnAction(e->{
            this.para.mouvementEight();
            mouvementEight.setSelected(true);
            mouvementFour.setSelected(false);
        });

        mouvementFour.setOnAction(e->{
            this.para.mouvementFour();
            mouvementFour.setSelected(true);
            mouvementEight.setSelected(false);
        });
        
        this.getChildren().addAll(explcationMouvLabel, mouvementEight, mouvementFour);
        this.setAlignment(Pos.CENTER);
    }
}
