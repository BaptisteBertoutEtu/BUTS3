package main.java.fr.univlille.entity.model;

import fr.univlille.iutinfo.cam.player.monster.IMonsterStrategy;
import main.java.fr.univlille.utils.Observer;
import main.java.fr.univlille.utils.Subject;

/**
 * 
 * Classe Monster peremttant l'initialisation du monstre.<br>
 * Le Monstre ne peut que se déplacer sur une case du plateau adjacente et qui est libre.<br>
 * Cette classe implémente l'interface IMonsterStrategy pour avoir accès à ses méthodes.<br>
 * Elle implémente également l'interface Observer et hérite de la classe abstraite Subject, 
 * qui lui permettent de notifier l'objet Hunter lorsque celui-ci lui demande les données de la case sur lequel il vient de tirer.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez
 * @author Mathis Decoster
 * 
 * @see Subject
 * @see IMonsterStrategy
 * @see Observer
 */
public class Monster extends Subject implements IMonsterStrategy, Observer{
    
}
