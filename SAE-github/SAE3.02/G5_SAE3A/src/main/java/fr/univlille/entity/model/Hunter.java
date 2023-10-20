package main.java.fr.univlille.entity.model;

import fr.univlille.iutinfo.cam.player.hunter.IHunterStrategy;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;

/**
 * 
 * Classe Hunter peremttant l'initialisation du chasseur.<br>
 * Le chasseur ne peut que tirer sur une case du plateau.<br>
 * Cette classe implémente l'interface IHunterStrategy pour avoir accès à ses méthodes.<br>
 * Elle implémente également l'interface Observer et hérite de la classe abstraite Subject, 
 * qui lui permettent de notifier l'objet Monster lorsqu'il effectue un tir, 
 * et d'avoir accès aux données de la case sur laquelle il vient de tirer
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see Subject
 * @see IHunterStrategy
 * @see Observer
 */
public class Hunter extends Subject implements IHunterStrategy, Observer {
    
}
