package fr.univlille.view;

import fr.univlille.Game;
import fr.univlille.donjon.Advice;

public class AdviceView {
    public static void load(Advice advice){
        Game.clearScreen();
        System.out.println("Advice");
        System.out.println(advice.getHelp());
        Game.pressToContinue();
    }
}
