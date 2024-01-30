package main.java.fr.univlille.view.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.fr.univlille.model.Game;
import main.java.fr.univlille.model.board.LabyPredef;
import main.java.fr.univlille.model.cell.CellEvent;
import main.java.fr.univlille.model.cell.Coordinate;
import main.java.fr.univlille.model.entity.Monster;
import main.java.fr.univlille.model.exception.UnsupportedMove;
import main.java.fr.univlille.model.parameters.AllParameters;
import main.java.fr.univlille.model.parameters.Parameters;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.OftenUse;
import main.java.fr.univlille.utils.Subject;
import main.java.fr.univlille.view.gameview.AllViewEnum;
import main.java.fr.univlille.view.gameview.allview.View;

/**
 * 
 * Classe {@code MonsterView} permettant d'avoir une Interface Homme Machine. Celle-ci nous permet de joeur au jeu.<br>
 * Le monstre connait toujours l'emplacement de la sortie et connait la taille du labyrinthe.<br>
 * <br>
 * Pour la vue des mûrs, deux options seront possibles :<br>
 * Le monstre sait où sont les murs du labyrinthe (connaissance complète du labyrinthe).<br><br>
 * ou<br><br>
 * Le monstre a une visibilité limitée à une portée de x cases autour de l’emplacement où il se trouve (connaissance partielle du labyrinthe).<br><br>
 * Pour les déplacements seront soit droite, gauche, haut, bas (4 cases) ou avec les diagonales en suplément (8 cases).<br>
 * Tous ces paramètres seront définir par le joueur dans le menu.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * Voir <a href="https://docs.oracle.com/javafx/2/api/">JavaFx</a> pour trouver la documentation lié à JavaFx.
 * 

 * @see Observer
 */
public class MonsterView extends View implements Observer{
    private Scene s ;
    private static final String TITLE = "Vue du Monstre";  

    private boolean[][] maze;
    private Monster monster;
    private GridPane gp;
    private Map<Coordinate,Button> mapText;
    private Coordinate lastReplaceShoot;
    private String lastValueChange;
    private List<Coordinate> zone;
    private Label nbrTour;
    private Label verifSave = new Label();

    /**
     * Constructeur de la classe {@link MonsterView} qui permet de construire l'affichage de la vue du Monstre.
     * 
     * @param monster Le Monstre lié à sa vue.
     * @see Monster
     */
    public MonsterView(Monster monster){
        this.mapText= new HashMap<>();
        this.zone = new ArrayList<>();
        this.monster = monster;
        this.maze = this.monster.getMaze();
        this.monster.attach(this);
        this.start();
    }
    
    
    /**
     * Méthode {@code getMyScene()} héritée de la classe abstraite {@link View} qui retourne la {@code Scene} courante qui permettra d'affiché le jeu.<br><br>
     * 
     * @return Cette méthode retourne la {@code Scene} courante qui permettra d'affiché la vue du Monstre.
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
        return MonsterView.TITLE;
    }

    /**
     * Méthode {@code update(Subject)} permettant d'afficher la vue gagnante du Monstre.
     * 
     * @param subj Ce paramètre représente l'expéditeur de l'information.
     */
    @Override
    public void update(Subject subj) {
        notifyObservers(AllViewEnum.MONSTERWIN);
    }
    

    /**
     * Méthode {@code update(Subject, Object)} permettant de mettre à jour la vue du Monstre en conséquence des données reçu.
     * 
     * @param subj Ce paramètre représente l'expéditeur de l'information.
     * @param data Ce paramètre représente la donnée envoyée.
     */
    @Override
    public void update(Subject subj, Object data) {
        if (subj instanceof Monster && data instanceof AllViewEnum) {
            notifyObservers(data);
        }
        else if(subj instanceof Monster && data instanceof CellEvent && ((CellEvent)data).isHunter()) {
            this.setAllBadShoots();
            changeLastShoot((CellEvent)data);
        }
        else if(subj instanceof Monster && data instanceof ICoordinate){
            Coordinate temp = new Coordinate(((ICoordinate) data).getCol(), ((ICoordinate) data).getRow());
            
            int turn = Game.currentTurn-1;
            if(turn<=9) this.mapText.get(this.monster.getLastCoord()).setText(" "+turn+" ");
            else this.mapText.get(this.monster.getLastCoord()).setText(""+turn);
            
            this.mapText.get(this.monster.getLastCoord()).setDisable(false);
            this.mapText.get(this.monster.getLastCoord()).setStyle(this.maze[this.monster.getLastCoord().getRow()][this.monster.getLastCoord().getCol()] ? OftenUse.STYLE_BACKGROUND_WHITE : OftenUse.STYLE_BACKGROUND_BLACK);
            this.mapText.get(temp).setText(OftenUse.M_TEXT_FOR_BUTTON);
            this.mapText.get(temp).setStyle(OftenUse.STYLE_BACKGROUND_BLUE);
            this.mapText.get(temp).setDisable(true);
            if(Parameters.labyrinthKnowledge.equals(AllParameters.PARTIAL_KNOWLEDGE)){
                updatePartialKnowledge();
            }
            this.setAllBadShoots();
            nbrTour.setText("Tour n°"+Game.currentTurn);
            if(!this.monster.isIA()) notifyObservers(AllViewEnum.MONSTERVIEW);
        }
    }

    /**
     * Méthode privée {@code setAllCell()} permettant de définir l'ensemble des boutons nécessaire au bon fonctionnement du jeu.
     */
    private void setAllCell(){
        Coordinate tempCoordinate = null;
        Button button;
        for (int y = 0; y < Parameters.lengthLabyrinth[1]; y++) {
            for (int x = 0; x < Parameters.lengthLabyrinth[0]; x++) {
                tempCoordinate = new Coordinate(x, y);
                button = new Button(OftenUse.EMPTY_TEXT_FOR_BUTTON);

                button.setStyle(this.maze[y][x] ? OftenUse.STYLE_BACKGROUND_WHITE : OftenUse.STYLE_BACKGROUND_BLACK);
                button.setFont(OftenUse.FONT_FOR_TEXT_IN_BUTTON);


                this.mapText.put(tempCoordinate, button);
                this.gp.add(button, tempCoordinate.getCol(), tempCoordinate.getRow());
            }
        }
        this.gp.setStyle(OftenUse.STYLE_FOR_GRIDPANE);
        
        this.mapText.get(this.monster.getExit()).setText(OftenUse.S_TEXT_FOR_BUTTON);

        this.mapText.get(this.monster.getCurrentCoord()).setText(OftenUse.M_TEXT_FOR_BUTTON);
        this.mapText.get(this.monster.getCurrentCoord()).setStyle(OftenUse.STYLE_BACKGROUND_BLUE);
        this.mapText.get(this.monster.getCurrentCoord()).setDisable(true);
    }

    /**
     * Méthode privée {@code setAllBadShoots()} permettant de remettre à l'état 0 les cases qui constitues des mauvais déplacement. <br>
     * En effet, en interface graphique, les mauvais déplacements transforment les cases en cases rouge. La remise à 0 représente une remise dans leur
     * état initiale, donc blanc pour une case vide et noir pour un mûr.
     */
    private void setAllBadShoots(){
        List<ICoordinate> list = this.monster.getBadShoots();
        Button temp;
        for (ICoordinate coord : list) {
            temp = this.mapText.get(coord);
            if((Parameters.labyrinthKnowledge.equals(AllParameters.PARTIAL_KNOWLEDGE) && this.zone.contains(coord))
             || Parameters.labyrinthKnowledge.equals(AllParameters.COMPLETE_KNOWLEDGE)){
                temp.setStyle(this.maze[coord.getRow()][coord.getCol()] ? OftenUse.STYLE_BACKGROUND_WHITE : OftenUse.STYLE_BACKGROUND_BLACK);
            }
        }
    }

    /**
     * Méthode privée {@code changeLastShoot()} permettant d'afficher dans le labyrinthe le dernier endroit où le Chasseur a tiré.
     * 
     * @param event Ce paramètre représente l'événement lié au dernier tir du chasseur.
     */
    private void changeLastShoot(CellEvent event){

        if(this.lastReplaceShoot!=null && !this.lastReplaceShoot.equals(this.monster.getCurrentCoord())) this.mapText.get(this.lastReplaceShoot).setText(this.lastValueChange);
        Button tempButton = null;
        Coordinate tempCoord = null;
        for (Coordinate coord : mapText.keySet()) {
            if(coord.getCol() == event.getCoord().getCol() && coord.getRow() == event.getCoord().getRow()){
                tempButton = this.mapText.get(coord);
                this.lastValueChange = tempButton.getText();
                this.lastReplaceShoot = coord;
                tempCoord = coord;
                tempButton.setText(OftenUse.T_TEXT_FOR_BUTTON);
                tempButton.setFont(OftenUse.FONT_FOR_TEXT_IN_BUTTON);
            }
        }
        
        if(tempButton != null){
            if(Parameters.labyrinthKnowledge.equals(AllParameters.PARTIAL_KNOWLEDGE)){
                if(this.zone.contains(tempCoord)){
                    tempButton.setTextFill(this.maze[tempCoord.getRow()][tempCoord.getCol()] ? OftenUse.BLACK_COLOR_FOR_TEXT : OftenUse.WHITE_COLOR_FOR_TEXT);
                }
                else{
                    tempButton.setTextFill(OftenUse.BLACK_COLOR_FOR_TEXT);
                }
            }
            else{
                tempButton.setTextFill(this.maze[tempCoord.getRow()][tempCoord.getCol()] ? OftenUse.BLACK_COLOR_FOR_TEXT : OftenUse.WHITE_COLOR_FOR_TEXT);
            }
        }
    }

    /**
     * Méthode privée {@code updatePartialKnowledge()} permettant de mettre à jour la vue du Monstre lorsque celui-ci joue en connaissance partielle du labyrinthe.
     */
    private void updatePartialKnowledge(){
        int zoneInt = Parameters.mouvementPartialKnowledgeValue;
        this.zone.clear();
        resetAllCell();
        this.getZone(zoneInt);
        for (Map.Entry<Coordinate,Button> me : this.mapText.entrySet()) {
            if(!this.zone.contains(me.getKey())){
                me.getValue().setStyle(OftenUse.STYLE_BACKGROUND_GREY);
            }
        }
    }

    /**
     * Méthode privée {@code resetAllCell()} permettant de remettre à zéro l'ensemble des cases du labyrinthe.
     */
    private void resetAllCell(){
        for (Map.Entry<Coordinate,Button> me : this.mapText.entrySet()) {
            me.getValue().setStyle(this.maze[me.getKey().getRow()][me.getKey().getCol()] ? OftenUse.STYLE_BACKGROUND_WHITE : OftenUse.STYLE_BACKGROUND_BLACK);
        }
    }

    /**
     * Méthode privée {@code getZone(int)} permettant de définir les cases à afficher lorsque le joueur joue en connaissance partielle du labyrinthe.
     * 
     * @param zoneInt Ce paramètre représente le rayon à partir duquel les cases à afficher sont selectionnées.
     */
    private void getZone(int zoneInt){
        ICoordinate temp = this.monster.getCurrentCoord();

        int debutX = Math.max(0, temp.getCol() - zoneInt);
        int finX = Math.min(this.maze[0].length - 1, temp.getCol() + zoneInt);

        int debutY = Math.max(0, temp.getRow() - zoneInt);
        int finY = Math.min(this.maze.length - 1, temp.getRow() + zoneInt);
        for (int i = debutX; i <= finX; i++) {
            for (int j = debutY; j <= finY; j++) {
                this.zone.add(new Coordinate(i, j));
            }
        }
    }

    /**
     * Méthode privée {@code saveThisLabyrinthe()} permettant de sauvgarder le labyrinthe courant et d'afficher un message de bon fonctionnement de la sauvgarde.
     */
    private void saveThisLabyrinthe(){
        try{
            LabyPredef.saveLaby(maze);
            this.verifSave.setText("Labyrinthe enregistré");
        } catch (Exception e) {
            this.verifSave.setText("Erreur : labyrinthe non enregistré");
        }
    }


    /**
     * Méthode {@code start()} permettant la construction de l'affichage de la vue du Monstre.<br>
     */
    public void start(){
        BorderPane bp = new BorderPane();
        bp.setPrefSize(View.WIDTH, View.HEIGHT);


        
        Label title = new Label("Allez "+Game.nameMonster+", Déplacez-vous !");
        title.setFont(OftenUse.FONT_FOR_TITLE);
        
        nbrTour = new Label("Tour n°"+Game.currentTurn);
        nbrTour.setFont(OftenUse.FONT_FOR_PARAMETERSVIEW);
        
        gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        this.setAllCell();
        

        Button b = new Button("Sortir");
        b.setFont(OftenUse.FONT_FOR_BUTTON);
        
        Button save = new Button("Enregistrer le labyrinthe");
        save.setFont(OftenUse.FONT_FOR_BUTTON);

        HBox.setMargin(b, new Insets(20, 0, 0, 0));
        HBox.setMargin(save, new Insets(20, 0, 0, 0));

        DropShadow dropShadow = new DropShadow();
        b.setEffect(dropShadow);
        save.setEffect(dropShadow);
        
        HBox h = new HBox(OftenUse.SPACE_FOR_BUTTON,b,save,verifSave);
        h.setAlignment(Pos.CENTER);
        
        VBox v = new VBox();
        v.setAlignment(Pos.CENTER);
        v.getChildren().addAll(gp,h);
        
        VBox top = new VBox();
        top.setAlignment(Pos.CENTER);
        top.getChildren().addAll(title,nbrTour);
        
        bp.setTop(top);
        bp.setCenter(v);
        BorderPane.setAlignment(title, Pos.CENTER);

        if(Parameters.labyrinthKnowledge.equals(AllParameters.PARTIAL_KNOWLEDGE)){
            updatePartialKnowledge();
        }

        b.setOnAction(e -> notifyObservers());

        save.setOnAction(e -> this.saveThisLabyrinthe());
        
        s = new Scene(bp);

        this.lastValueChange = OftenUse.EMPTY_TEXT_FOR_BUTTON;
        
        for (Map.Entry<Coordinate,Button> me : this.mapText.entrySet()) {
            me.getValue().setOnAction(e -> {
                try {
                    this.monster.move(me.getKey());
                } catch (UnsupportedMove ex) {   
                    this.mapText.get(me.getKey()).setStyle(OftenUse.STYLE_BACKGROUND_RED);
                }
            });
        }
    }
}