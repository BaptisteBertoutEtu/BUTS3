package main.java.fr.univlille.view.gameview.allview.allparameters;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.OftenUse;

public class PlayerParameter extends VBox{

    private Parameters para;

    /**
     * Constructeur de la classe {@link MovementParameter} permettant de construire la vue.
     * @param para L'objet {@link Parameters} permettant de mettre à jour les paramètres du jeu.
     */
    public PlayerParameter(Parameters para){
        this.para=para;
        this.start();
    }   

    
    /**
     * Méthode {@code start} permettant la construction de l'affichage la vue pour changer les paramètres de mouvements dans le labyrinthe.<br>
     */
    public void start(){

        Label explcationMouvLabel = new Label("Ici vous pouvez choisir le mode de jeu voulu.");

        explcationMouvLabel.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        RadioButton mode1 = new RadioButton("Uniquement monster en intelligence artificielle");
        RadioButton mode2 = new RadioButton("Uniquement hunter en intelligence artificielle");
        RadioButton mode3 = new RadioButton("Les deux en intelligence artificielle");
        RadioButton mode4 = new RadioButton("Les deux en joueurs");

        mode1.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        mode2.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        mode3.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        mode4.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);

        mode4.setSelected(true);

        mode1.setOnAction(e->{
            this.para.setMonsterAi();
            mode1.setSelected(true);
            mode4.setSelected(false);
            mode3.setSelected(false);
            mode2.setSelected(false);
        });

        mode2.setOnAction(e->{
            this.para.setHunterAi();
            mode2.setSelected(true);
            mode4.setSelected(false);
            mode3.setSelected(false);
            mode1.setSelected(false);
        });

        mode3.setOnAction(e->{
            this.para.setBothAi();
            mode3.setSelected(true);
            mode4.setSelected(false);
            mode1.setSelected(false);
            mode2.setSelected(false);
        });

        mode4.setOnAction(e->{
            this.para.setBothPlayer();
            mode4.setSelected(true);
            mode1.setSelected(false);
            mode2.setSelected(false);
            mode3.setSelected(false);
        });
        
        this.getChildren().addAll(explcationMouvLabel, mode1, mode2, mode3, mode4);
        this.setAlignment(Pos.CENTER);
    }
}
