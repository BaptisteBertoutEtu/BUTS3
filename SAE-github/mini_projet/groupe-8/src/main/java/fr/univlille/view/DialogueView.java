package fr.univlille.view;

import java.io.File;

import fr.univlille.Game;
import fr.univlille.bestiary.Bestiary;
import fr.univlille.donjon.Theme;
import fr.univlille.file.FileFinder;
import fr.univlille.file.FileLoader;

public class DialogueView {
    public static final String PATH = FileFinder.PATH + "lore" + File.separator;
    public static final String START_SCREEN = "StartGame.txt";
    public static final String WIN_SCREEN = "WinScreen.txt";
    public static final String DEAD_SCREEN = "DeadScreen.txt";

    public static final String START_BOSS_INTERIEUR = "Start_Boss_INTERIEUR.txt";
    public static final String START_BOSS_EDUCATION = "Start_Boss_EDUCATION.txt";
    public static final String START_BOSS_OPPOSITION = "Start_Boss_OPPOSITION.txt";
    public static final String START_BOSS_CULTURE = "Start_Boss_CULTURE.txt";
    public static final String START_BOSS_FINALE = "Start_Boss_FINALE.txt";

    public static final String START_INTERIEUR = "Start_INTERIEUR.txt";
    public static final String START_EDUCATION = "Start_EDUCATION.txt";
    public static final String START_OPPOSITION = "Start_OPPOSITION.txt";
    public static final String START_CULTURE = "Start_CULTURE.txt";
    public static final String START_FINALE = "Start_FINALE.txt";

    public static void startGame(){
        clearScreen();
        print(START_SCREEN);
        Game.pressToContinue();
    }

    public static void startBoss(Bestiary mob){
        clearScreen();
        switch(mob.getTheme()){
            case INTERIEUR: print(START_BOSS_INTERIEUR); break;
            case EDUCATION: print(START_BOSS_EDUCATION); break;
            case OPPOSITION: print(START_BOSS_OPPOSITION); break;
            case CULTURE: print(START_BOSS_CULTURE); break;
            case FINALE: print(START_BOSS_FINALE); break;
        }
        Game.pressToContinue();
    }

    public static void startStage(Theme theme){
        clearScreen();
        switch(theme){
            case INTERIEUR: print(START_CULTURE); break;
            case EDUCATION: print(START_EDUCATION); break;
            case OPPOSITION: print(START_OPPOSITION); break;
            case CULTURE: print(START_CULTURE); break;
            case FINALE: print(START_FINALE); break;
        }
        Game.pressToContinue();
    }
    
    public static void playerDead(){
        clearScreen();
        print(DEAD_SCREEN);
        Game.pressToContinue();
        Game.startTitleScreen();
    }
    
    public static void playerWin(){
        clearScreen();
        print(WIN_SCREEN);
        Game.pressToContinue();
        Game.startTitleScreen();
    }

    public static void print(String fileName){
        FileLoader.print(fileName);
    }

    public static void clearScreen(){
        Game.clearScreen();
    }
}