package fr.univlille.bestiary;

import fr.univlille.Game;
import fr.univlille.view.MonsterView;
import fr.univlille.donjon.Theme;

public class BestiaryLoader{
    private static final Theme[] THEMES = Theme.values();
    private static final int BESTIARY_EXIT = 0;
    private static final int BESITARY_SPRITES = 9;

    public static void load() {
        int choice = 0;
        
        System.out.println("Bienvenue dans le bestiaire");
        do {
            Game.clearScreen();
            System.out.println("-> Afficher tous les sprites des mobs ? (SPOIL) (press 9)");
            System.out.println("-> Retourner à l'écran titre ? (press 0)");
            System.out.println();
            System.out.println("Quelle thèmes voulez-vous voir ?");
            System.out.println();

            printThemes();
            
            System.out.println();
            System.out.print("Veuiller entrer le chiffre corréspondant : ");

            choice = Game.readIntNotNull();

            System.out.println();
            if(choice == BESITARY_SPRITES){
                MonsterView.load();
                Game.pressToContinue();
            }

            if(choiceThemeIsValid(choice)){
                printBestiary(THEMES[choice - 1]);
            }

        } while (choice != BESTIARY_EXIT);
        Game.startTitleScreen();
    }

    public static boolean choiceThemeIsValid(int index){
        return index >= 1 && index <= THEMES.length;
    }

    public static void printThemes(){
        for (int i = 0; i < THEMES.length; i++){
            System.out.println("Theme " + (i + 1) + " : " + THEMES[i]);
        }
    }

    public static void printBestiary(Theme theme){
        for(Bestiary m : Bestiary.getMobs(theme)){
            if(m.isBoss()){
                System.out.println("!BOSS! " + m.getName());
            }else{
                System.out.println(m.getName());
            }
        }
        Game.pressToContinue();
    }
}