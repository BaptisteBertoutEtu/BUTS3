package main.java.fr.univlille.model.entity.iastrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent;
import fr.univlille.iutinfo.cam.player.perception.ICoordinate;
import fr.univlille.iutinfo.cam.player.perception.ICellEvent.CellInfo;


/**
 * Classe {@code IAMonstre} permettant l'interface {@code IMonsterStrategy} et permettant de créer une "Intelligence Artificielle" 
 * qui jouera contre le Joueur humain ou contre l'IA du Monstre. <br>
 * Elle implémente l'algorithme de parcours en largeur pour trouver le plus court chemin vers la sortie. <br>
 * Grâce à ce chemin, le monstre ce déplace de façon "intelligente".
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 */
public class IAMonstre implements IMonsterStrategy{

    /*
     * Labyrinthe du monstre
     */
    private boolean[][] maze;

    /*
     * les coordonées actuelles du monstre
     */
    private ICoordinate currentCoord;

    /*
     * les coordonnées de la sortie
     */
    private ICoordinate exit;

    /*
     * Un random permettant de générer divers valeurs aléatoires 
     */
    private static final Random RD = new Random();

    /**
     * Méthode {@code play()} hérité de {@code IMonsterStrategy} qui permet à l'IA du Monstre de se déplacer 
     * dans le labyrinthe. <br>
     * 
     * @return Cette méthode retourne une instance de {@code InnerIAMonsterCoordinate}. Autrement dit une coordonnée.
     */
    @Override
    public ICoordinate play() {
        return chooseNextMove();
    }

    /**
     * Méthode {@code update()} hérité de l'interface {@code IMonsterStrategy} qui permet de mettre à jour 
     * la position courante du Monstre.
     */
    @Override
    public void update(ICellEvent info) {
        if(info != null && info.getState() == CellInfo.MONSTER){
            this.currentCoord = info.getCoord();
        }else if(info != null && info.getState() == CellInfo.EXIT){
            this.exit = info.getCoord();
        }
    }

    /**
     * Méthode {@code initialize} permettant d'initialiser un labyrinthe à partir d'un tableau de booléen.
     * 
     * @param maze Le tableau de booléen représentant le labyrinthe.
     */
    @Override
    public void initialize(boolean[][] maze) {
        this.maze = maze;
    }

    /**
     * Methode privée {@code findShortestPath} permettant de rechercher le plus court chemin entre deux points. <br>
     * 
     * @param start Ce paramètre représente le point de départ.
     * @param goal Ce paramètre représente le point d'arrivée.
     * 
     * @return Cette méthode retourne la {@code liste} des coordonnées représentant le plus court chemin.
     */
    private List<ICoordinate> findShortestPath(ICoordinate start, ICoordinate goal) {
        Queue<List<ICoordinate>> queue = new LinkedList<>();
        Set<ICoordinate> visited = new HashSet<>();
        queue.add(new ArrayList<>(Collections.singletonList(start)));
        visited.add(start);
    
        while (!queue.isEmpty()) {
            List<ICoordinate> path = queue.poll();
            ICoordinate current = path.get(path.size() - 1);
    
            if (current.getCol() == goal.getCol() && current.getRow() == exit.getRow()) {
                // Chemin trouvé
                return path;
            }
    
            for (ICoordinate neighbor : possibility(current, path)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    List<ICoordinate> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        // Pas de chemin trouvé
        return Collections.emptyList();
    }

    /**
     * Méthode privée {@code chooseNextMove} permettant de choisir le prochain mouvement du monstre.
     * 
     * @return Cette méthode retourne le prochain mouvement du monstre.
     */
    private ICoordinate chooseNextMove() {
        List<ICoordinate> path = findShortestPath(currentCoord, exit);
    
        // Si un chemin est trouvé, retourne le prochain mouvement
        if (!path.isEmpty() && path.size() > 1) {
            return path.get(1); // Le premier élément est la position actuelle
        } 
        else {
            // Aucun chemin trouvé, retourne un mouvement aléatoire parmi les possibilités
            List<ICoordinate> possibleMoves = possibility(currentCoord, path);  
            return possibleMoves.isEmpty() ? currentCoord : possibleMoves.get(RD.nextInt(possibleMoves.size()));
        }
    }

    /**
     * Méthode privée {@code possibility} permettant de lister les mouvements possibles à partir d'une position donnée, en excluant le chemin actuel.
     * 
     * @param current Ce paramètre représente la position actuelle.
     * @param path Ce paramètre représente le chemin actuel.
     * 
     * @return Cette méthode retourne la {@code liste} des mouvements possibles.
     */
    private List<ICoordinate> possibility(ICoordinate current, List<ICoordinate> path) {
        List<ICoordinate> liste = new ArrayList<>();
        for (ICoordinate iCoordinate : around(current)) {
            if (inRange(iCoordinate) && this.maze[iCoordinate.getRow()][iCoordinate.getCol()] && !path.contains(iCoordinate)) {
                liste.add(iCoordinate);
            }
        }
        return liste;
    }

    /**
     * Méthode privée {@code around} permettant de lister les voisins d'une coordonnée donnée.
     * 
     * @param coord Ce paramètre représente la coordonnée.
     * 
     * @return Cette méthode retourne la {@code liste} des voisins de la coordonée passée en paramètre.
     */
    private List<ICoordinate> around(ICoordinate coord) {
        List<ICoordinate> liste = new ArrayList<>();
        int row = coord.getRow();
        int col = coord.getCol();
        liste.add(new InnerIAMonstreCoordinate(col + 1, row));
        liste.add(new InnerIAMonstreCoordinate(col - 1, row));
        liste.add(new InnerIAMonstreCoordinate(col, row + 1));
        liste.add(new InnerIAMonstreCoordinate(col, row - 1));
        return liste;
    }

    /**
     * Méthode privée {@code inRange} permettant de vérifier si une coordonnée est dans les limites du labyrinthe.
     * 
     * @param coord Ce paramètre représente la coordonnée.
     * 
     * @return Cette méthode retourne un {@code booléen} selon si la coordonnée est valide ou non.
     */
    private boolean inRange(ICoordinate coord){
        return (coord.getRow() >= 0 && coord.getRow() < this.maze.length) && 
                (coord.getCol() >= 0 && coord.getCol() < this.maze[0].length);
    }
    
    /**
     * 
     * Classe {@code InnerIAMonstreCoordinate} permettant l'implémentation de linterface {@code ICoordinate}.<br>
     * Cette implémentation permet d'avoir accès au méthodes de {@code ICoordinate}. <br>
     * Cette classes est une classe interne à la classe {@code IAMonster} ce qui permet la portabilité de la classe {@code IAMonster}
     * dans le jeu des autres équipes.
     * 
     * @author Baptiste Bertout
     * @author Pierre Planchon
     * @author Arthur Keller
     * @author Gaspard Souliez
     * @author Mathis Decoster
     */
    public class InnerIAMonstreCoordinate implements ICoordinate{

        /**
         * Valeur correspondant au nombre de ligne dans le terrain de jeu.
         */
        private int row;

        /**
         * Valeur correspondant au nombre de colonne dans le terrain de jeu.
         */
        private int col;

        /**
         * Constructeur de la classe {@link InnerIAMonstreCoordinate} permettant de créer une coordonnée avec les numéros de colone et de ligne spécifiés.
         * 
         * @param col Le numéro de colone ( x ).
         * @param row Le numéro de ligne ( y ).
         */
        public InnerIAMonstreCoordinate(int col, int row){
            this.col = col;
            this.row = row;
        }

        /**
         * Méthode {@code getCol} qui retourne le numéro de colonne de la coordonnée.
         * 
         * @return Le numéro de la colonne.
         */
        @Override
        public int getCol() {
            return this.col;
        }

        /**
         * Méthode {@code getRow} qui  retourne le numéro de ligne de la coordonnée.
         * 
         * @return Le numéro de la ligne.
         */
        @Override
        public int getRow() {
            return this.row;
        }

        /**
         * Méthode {@code hashCode} permettant de générer le hashCode.
         * @return Le hashCode.
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + row;
            result = prime * result + col;
            return result;
        }

        /**
         * Méthode {@code equals} permettant de comparer deux coordonées et de retourner
         * un booléen en fonction de si ils sont égaux ou non.
         * 
         * @param obj La deuxième coordonée pour la comparaison.
         * @return Le booléen qui définit si ils sont égaux ou non.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ICoordinate other = (ICoordinate) obj;
            if (row != other.getRow())
                return false;
            return col == other.getCol();
        }
    }
}