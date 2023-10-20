package fr.univlille.view;

import fr.univlille.Game;
import fr.univlille.bestiary.Bestiary;
import fr.univlille.file.FileLoader;

public class MonsterView {
    public static void load() {
        for(Bestiary m : Bestiary.values()){
            System.out.println("Mob : " + m.getName());
            FileLoader.print(m.getFileName());
            Game.pressToContinue();
        }
    }
}