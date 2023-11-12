package main.java.fr.univlille.model.parameters;

import main.java.fr.univlille.utils.Subject;

/**
 * 
 * Classe {@code Parameters} permettant de définir les paramètres par défaut du jeu. Tous ces paramètres pourront être changé au lancement du jeu
 * dans l'onglet paramètres.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see AllParameters
 */
public class Parameters extends Subject{
    /**
     * Paramètre {@code labyrinthKnowledge} permettant de définir le mode de connaissance du labyrinthe.
    */
    public static AllParameters labyrinthKnowledge = AllParameters.COMPLETE_KNOWLEDGE; 

    /**
     * Paramètre {@code movement} permettant de définir le mode de mouvement du {@code Monstre}.
    */
    public static AllParameters movement = AllParameters.MOVEMENT_8;

    /**
     * Paramètre {@code maxMouvementPartialKnowledgeValue} qui définit le nombre de case maximum que le Monstre peut voir autour de lui 
    */
    public static int maxMouvementPartialKnowledgeValue = 5;

     /**
     * Paramètre {@code minMouvementPartialKnowledgeValue} qui définit le nombre de case maximum que le Monstre peut voir autour de lui 
    */
    public static int minMouvementPartialKnowledgeValue = 2;

    /**
     * Paramètre {@code mouvementPartialKnowledgeValue} qui définit le nombre de case par défaut que le Monstre peut voir autour de lui 
     * (Ce paramètre est utilisé seulement si le paramètre {@code labyrinthKnowledge} est définit sur {@code AllParameters.PARTIAL_KNOWLEDGE}).
    */
    public static int mouvementPartialKnowledgeValue = Parameters.minMouvementPartialKnowledgeValue;
    
    /**
     * Paramètre {@code maxLengthLabyrinth} définissant la taille maximale du labyrinthe.<br>
     * Respectivement 30 case de largeur et 20 case de hauteur.
     */
    public static final int[] maxLengthLabyrinth = new int[]{30,20};

    /**
     * Paramètre {@code minLengthLabyrinth} définissant la taille minimale du labyrinthe.<br>
     * Respectivement 10 case de largeur et 8 case de hauteur.
     */
    public static final int[] minLengthLabyrinth = new int[]{10,8};

    /**
     * Paramètre {@code LENGTH_LABYRINTH} qui définit la taille par défaut du labyrinthe. Ici elle est définit sur la valeur maximale.
    */
    public static int[] lengthLabyrinth = Parameters.maxLengthLabyrinth;

    public static AllParameters modeJeu = AllParameters.BOTH_PLAYER;

    private int height;
    private int width;
    private int value;

    /**
     * Méthode permettant de construire une variable Parameters initialisant la hauteur et la largeur.
     */
    public Parameters(){
        this.height = Parameters.lengthLabyrinth[1];
        this.width = Parameters.lengthLabyrinth[0];
        this.value = Parameters.mouvementPartialKnowledgeValue;
    }

    /**
     * Méthode {@code setRowMaze} permettant de fixer une nouvelle hauteur (ou nombres de lignes).
     * 
     * @param nbRow La nouvelle hauteur du labyrinthe ( ou nombre de lignes ).
     */
    public void setRowMaze(int nbRow){
        this.height = nbRow;
        notifyObservers();
    }
    /**
     * Méthode {@code setColMaze} permettant de fixer une nouvelle largeur (ou nombres de colonnes).
     * 
     * @param nbCol La nouvelle largeur du labyrinthe (ou nombres de colonnes).
     */
    public void setColMaze(int nbCol){
        this.width = nbCol;
        notifyObservers();
    }

    /**
     * Méthode {@code getHeight} permettant de récupérer la hauteur du labyrinthe.
     * 
     * @return La hauteur du labyrinthe.
     */
    public int getHeight(){
        return height;
    }

    /**
     * Méthode {@code getWidth} permettant de récupérer la largeur du labyrinthe.
     * 
     * @return La largeur du labyrinthe.
     */
    public int getWidth(){
        return width;
    }

    /**
     * Méthode {@code incrementHeight} permettant d'incrémenter la hauteur de 1.
     */
    public void incrementHeight(){
        setRowMaze(height+1);
    }

    /**
     * Méthode {@code decrementHeight} permettant de décrémenter la hauteur de 1.
     */
    public void decrementHeight(){
        setRowMaze(height-1);
    }

    /**
     * Méthode {@code incrementWidth} permettant d'incrémenter la largeur de 1.
     */
    public void incrementWidth(){
        setColMaze(width+1);
    }

    /**
     * Méthode {@code decrementWidth} permettant de décrémenter la largeur de 1.
     */
    public void decrementWidth(){
        setColMaze(width-1);
    }

    /**
     * Methode {@code mouvementEight} permettant de changer le paramètre du mouvement et de le passer en 8.
     */
    public void mouvementEight(){
        Parameters.movement = AllParameters.MOVEMENT_8;
    }

    /**
     * Methode {@code mouvementFour} permettant de changer le paramètre du mouvement et de le passer en 4.
     */
    public void mouvementFour(){
        Parameters.movement = AllParameters.MOVEMENT_4;
    }

    /**
     * Methode {@code knowledgeComplete} permettant de changer le paramètre de la connaissance du labyrinthe et de le passer en connaissance complète.
     */
    public void knowledgeComplete(){
        Parameters.labyrinthKnowledge = AllParameters.COMPLETE_KNOWLEDGE;
    }

    /**
     * Methode {@code knowledgePartial} permettant de changer le paramètre de la connaissance du labyrinthe et de le passer en connaissance partielle.
     */
    public void knowledgePartial(){
        Parameters.labyrinthKnowledge = AllParameters.PARTIAL_KNOWLEDGE;
    }

    
    public int getValue(){
        return this.value;
    }
    
    /**
     * Méthode {@code incrementValue} permettant d'incrémenter la hauteur de 1.
     */
    public void incrementValue(){
        setValue(value+1);
    }
    
    /**
     * Méthode {@code decrementValue} permettant de décrémenter la hauteur de 1.
     */
    public void decrementValue(){
        setValue(value-1);
    }
    
    /**
     * Méthode {@code setValue} permettant de fixer le paramètre value.
     */
    public void setValue(int value){
        this.value=value;
    }

    public void setMonsterAi(){
        modeJeu = AllParameters.MONSTER_AI_ONLY;
    }

    public void setHunterAi(){
        modeJeu = AllParameters.HUNTER_AI_ONLY;
    }
    public void setBothAi(){
        modeJeu = AllParameters.BOTH_AI;
    }

    public void setBothPlayer(){
        modeJeu = AllParameters.BOTH_PLAYER;
    }

    /**
     * Méthode {@code updateParameters} permettant de modifier la taille du labyrinthe avec les nouvelles valeurs.
     */
    public void updateParameters(){
        Parameters.lengthLabyrinth = new int[]{this.width, this.height};
        Parameters.mouvementPartialKnowledgeValue=this.value;
    }
}
