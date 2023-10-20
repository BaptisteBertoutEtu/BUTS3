package fr.univlille;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fr.univlille.bestiary.BestiaryLoader;
import fr.univlille.file.FileLoader;
import fr.univlille.view.GameView;

public class Game {

    /**
     *
     */
    private static final String CLEAR = "\033[H\033[2J";
    private static final char NEUF = '9';
    private static final char ZERO = '0';
    public static final String FILENAME_TEST = "Titlescreen.txt";
    public static final String PRESS_TO_CONTINUE = "Appuyez sur 'Entrée' pour continuer";
    public static final String START_GAME = "a";
    public static final String START_BESTIARY = "b";

    private static boolean end = false;

    public static void main(String[] args) {
        startTitleScreen();
    }

    public static void startTitleScreen(){
        clearScreen();
        FileLoader.print(FILENAME_TEST);
        
        do{
            String choice = readStringNotNull();

            if(keyEquality(choice, START_GAME)){ GameView.start(); }
            if(keyEquality(choice, START_BESTIARY)) { BestiaryLoader.load(); }
        }while(!end);
    }

    public static boolean keyEquality(String a, String b){
        return a.equalsIgnoreCase(b);
    }

    public static int readIntNotNull(){
        String s = null;
        try {
            do{
                s = readString();
            }while(s == null || s.isEmpty() || !isNumber(s));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Integer.parseInt(s);
    }

    public static boolean isNumber(String s){
        for(char c : s.toCharArray()) if(!isFigure(c)) return false;
        return true;
    }

    public static boolean isFigure(char c){
        return c >= ZERO && c <= NEUF;
    }

    public static String readStringNotNull(){
        String s = null;
        try{
            do{
                s = readString();
            }while(s == null);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return s;
    }

    public static String readString() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static void pressToContinue(){
        System.out.println();
        System.out.print(PRESS_TO_CONTINUE);
        Game.readStringNotNull();
        System.out.println();
    }

    public static void clearScreen(){
        System.out.println(CLEAR);
        System.out.flush();
    }
}
