package main.java.fr.univlille.model.cell;

import fr.univlille.iutinfo.cam.player.perception.ICoordinate;

/**
 * 
 * Classe {@code Coordinate} permettant l'implémentation de linterface {@link ICoordinate}.<br>
 * Cette implémentation permet d'avoir accès au méthodes de {@link ICoordinate}.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see ICoordinate
 */
public class Coordinate implements ICoordinate{


    /**
     * Valeurs correspondant aux lignes et aux colonnes dans le terrain de jeu.
     */
    private int numRow;
    private int numCol;


    /**
     * Constructeur de la classe {@link Coordinate} permettant de créer une coordonnée avec les numéros de colone et de ligne spécifiés.
     * 
     * @param numCol Le numéro de colone.
     * @param numRow Le numéro de ligne.
     */
    public Coordinate(int numCol, int numRow){
        this.numCol = numCol;
        this.numRow = numRow;
    }


    /**
     * Méthode {@code getCol} qui retourne le numéro de colonne de la coordonnée.
     * 
     * @return Le numéro de la colonne.
     * 
     * @see ICoordinate
     */
    @Override
    public int getCol() {
        return numCol;
    }


    /**
     * Méthode {@code getRow} qui  retourne le numéro de ligne de la coordonnée.
     * 
     * @return Le numéro de la ligne.
     * 
     * @see ICoordinate
     */
    @Override
    public int getRow() {
        return numRow;
    }

    /**
     * Méthode {@code compareRow} qui permet de calculer et de retourner la différence entre la ligne de base et une autre ligne spécifiée.
     * 
     * @param otherRow La ligne à comparer.
     * @return La différence entre la ligne de base et la ligne spécifiée.
     */
    public int compareRow(int otherRow){
        return otherRow - this.getRow();
    }

    /**
     * Méthode {@code compareRow} qui permet de calculer et de retourner la différence entre la colonne de base et une autre colonne spécifiée.
     * 
     * @param otherCol La colonne à comparer.
     * @return La différence entre la colonne de base et la colonne spécifiée.
     */
    public int compareCol(int otherCol){
        return otherCol - this.getCol();
    }

    /**
     * Méthode {@code hashCode} permettant de générer le hashCode.
     * @return Le hashCode.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numRow;
        result = prime * result + numCol;
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
        Coordinate other = (Coordinate) obj;
        if (numRow != other.numRow)
            return false;
        return numCol == other.numCol;
    }
    
}
