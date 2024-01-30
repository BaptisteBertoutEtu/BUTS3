package main.java.fr.univlille.model.board;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe {@code LabyPredef} permettant de prédéfinir 3 labyrinthes. <br>
 * Ces 3 labyrinthes peuvent être utilisée par le(s) joueur(s) à la place d'un labyrinthe généré aléatoirement.
 * 
 * @author Baptiste Bertout
 * @author Pierre Planchon
 * @author Arthur Keller
 * @author Gaspard Souliez  
 * @author Mathis Decoster
 */
public class LabyPredef {

    /**
     * Le chemin pour trouver les labyrinthes sauvegardés
     */
    private static final String PATH = "res/labyPredef/";

    /**
     * Nom avec chemin à définir à un labyrinthe lors de sa sauvegarde
     */
    private static final String NAME_FOR_SAVE = "res/labyPredef/labyrinthe n°";

    /**
     * compteur de labyrinthe ssauvegardés pour créer des noms uniques
     */
    public static int nbLabySave = 0;
    
    /**
     * Méthode {@code loadLabyPredef(String)} permettant de charger un labyrinthe prédéfini à l'aide d'un nom.
     * 
     * @param fileName le nom du labyrinthe à changer.
     * 
     * @return Un tableau à double dimension de booléen représentant le labyrinthe chargé.
     */
    public static boolean[][] loadLabyPredef(String fileName){

        List<List<Boolean>> tab = new ArrayList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(new File(PATH + fileName)))) {
            
            while (bf.ready()) {
                List<Boolean> temp = new ArrayList<>();
                String line = bf.readLine();

                for (int i = 0; i < line.length(); i++) {
                    temp.add(line.charAt(i) != '#');
                }

                tab.add(temp);
            }

            boolean[][] laby = new boolean[tab.size()][tab.get(0).size()];

            for (int y = 0; y < laby.length; y++) {
                for (int x = 0; x < laby[0].length; x++) {
                    laby[y][x] = tab.get(y).get(x);
                }
            }

            return laby;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new boolean[0][0];
        }

    }

    /**
     * Méthode {@code saveLaby()} permettant de sauvegarder un labyrinthe à partir d'un 
     * tableau à double dimensions de booléens.
     * 
     * @param maze le labyrinthe à enregistrer.
     * 
     * @throws FileNotFoundException Cette exception est levée si le fichier n'a pas été trouvé.
     * @throws SecurityException Cette exception est levée si une violation de sécurité est détéctée.
     * @throws NullPointerException Cette exception est levée si {@code null} est utilisé à la place d'un objet.
     */
    public static void saveLaby(boolean[][] maze) throws FileNotFoundException, SecurityException, NullPointerException{
        PrintWriter pr = new PrintWriter(new File(NAME_FOR_SAVE+(LabyPredef.nbLabySave+1)+".txt"));
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[0].length; x++) {
                pr.print(maze[y][x] ? ' ' : '#');
            }
            pr.println();
        }
        pr.close();
    }
}
