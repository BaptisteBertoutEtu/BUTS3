package fr.univlille.view;

import fr.univlille.Color;
import fr.univlille.entity.Renaud;

public class RenaudView {
    private static Renaud player;

    public RenaudView(Renaud player) {
        RenaudView.player = player;
    }

    public void printStats() {
        String txt = '│' + stageRoomString() + '│' + lvlString() + '│' + expString() 
            + '│' + hpString() + '│' + atkString() + '│' + defString() + '│';
        System.out.print('╭');
        int lengthColor = Color.stringToColor(Color.FG_RED, "").length() +
            Color.stringToColor(Color.FG_BLUE, "").length() + 
            (Color.stringToColor(Color.FG_YELLOW, "").length()*3) + 
            (Color.stringToColor(Color.FG_GREEN, "").length()*2) +
            (Color.stringToColor(Color.FG_PURPLE, "").length()*2);
        for (int i = 0; i < txt.length()-(2+lengthColor); i++) {
            System.out.print('\u2500');
        }
        System.out.println('╮');
        System.out.println(txt);
        System.out.print('╰');
        for (int i = 0; i < txt.length()-(2+lengthColor); i++) {
            System.out.print('\u2500');
        }
        System.out.println('╯');
    }

    public void printBattleStats() {
        String txt = '│' + stageRoomString() + '│' + lvlString() + '│' + expString() 
            + '│' + atkString() + '│' + defString() + '│';
        System.out.print('╭');
        int lengthColor = Color.stringToColor(Color.FG_RED, "").length() +
            Color.stringToColor(Color.FG_BLUE, "").length() + 
            (Color.stringToColor(Color.FG_YELLOW, "").length()*3) + 
            (Color.stringToColor(Color.FG_PURPLE, "").length()*2);
        for (int i = 0; i < txt.length()-(2+lengthColor); i++) {
            System.out.print('\u2500');
        }
        System.out.println('╮');
        System.out.println(txt);
        System.out.print('╰');
        for (int i = 0; i < txt.length()-(2+lengthColor); i++) {
            System.out.print('\u2500');
        }
        System.out.println('╯');
    }

    public String lvlString() {
        return "Niveau:" + Color.stringToColor(Color.FG_YELLOW, "" + player.getLevel());
    }
    public String expString() {
        return "Expérience:" + Color.stringToColor(Color.FG_YELLOW, "" + player.getExpCurrent()) + "/" + Color.stringToColor(Color.FG_YELLOW, "" + player.getExpNeeded());
    }
    public String hpString() {
        return "Points de vie:" + Color.stringToColor(Color.FG_GREEN, "" +player.getCurrentHp()) + "/" + Color.stringToColor(Color.FG_GREEN, "" +player.getHp());
    }
    public String atkString() {
        return "Attaque:" + Color.stringToColor(Color.FG_RED, "" + player.getAtk());
    }
    public String defString() {
        return "Défense:" + Color.stringToColor(Color.FG_BLUE, "" + player.getDef());
    }
    public String stageRoomString() {
        return "Stage:" + (Color.stringToColor(Color.FG_PURPLE, "" + (player.getStage()+1))) + "-" + (Color.stringToColor(Color.FG_PURPLE, "" + (player.getRoom()+1)));
    }
}
