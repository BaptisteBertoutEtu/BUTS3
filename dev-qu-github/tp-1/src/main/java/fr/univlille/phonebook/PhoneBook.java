package fr.univlille.phonebook;

import java.util.HashMap;
import java.util.Map;

import fr.univlille.exception.BugFixException;

/**
 * @author Antoine Nongaillard, Fabien Delecroix
 * Répertoire téléphonique permettant la gestion de numéros de téléphone de correspondants.
 */
public class PhoneBook {

    /** Le répertoire sous la forme d'une table associative */
    protected Map<String, PhoneNumber> directory;
    
    /**
     * Constructeur pour instancier un répertoire vide.
     */
    public PhoneBook() {
        this.directory = new HashMap<>();
    }

    /**
     * Ajoute un correspondant et son numéro de téléphone
     * @param label le libellé du correspondant à ajouter
     * @param tel le numéro de téléphone du correspondant à ajouter
     */
    public void put(String label, PhoneNumber tel) {
        this.directory.put(label, tel);
    }

    /**
     * Supprime un correspondant donné
     * @param label le libellé du correspondant à supprimer
     */
    public void remove(String label) {
        this.directory.remove(label);
    }

    /**
     * Donne le numéro de téléphone d'un correspondant donné à partir de son nom
     * @param label le libellé du correspondant
     * @return le numéro de téléphone du correspondant
     */
    public PhoneNumber getNumber(String label) throws BugFixException{
        PhoneNumber temp = this.directory.get(label);
        if (temp== null) {
            throw new BugFixException();
        }
        else{
            return temp;
        }

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        final String NL = System.getProperty("line.separator");
        for(Map.Entry<String, PhoneNumber> e: this.directory.entrySet()) {
            res.append(e.getKey() + " : " + e.getValue() + NL);
        }
        return res.toString();
    }
    
}
