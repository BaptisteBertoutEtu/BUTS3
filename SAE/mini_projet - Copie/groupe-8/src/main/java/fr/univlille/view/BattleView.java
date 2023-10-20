package fr.univlille.view;

import fr.univlille.Battle;
import fr.univlille.Game;
import fr.univlille.file.FileFinder;
import fr.univlille.file.FileLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BattleView {
    public static final String FILENAME_BATTLE = "battle.txt";
    private static final String RENAUD = "renaud.txt";
    private static final File FILERENAUD = FileFinder.find(RENAUD);
    private static final String TAB = "\t\t\t\t\t";
    private static final String PHRASE_PV = " HP : ";


    public static void afficheBattle(){
        Game.clearScreen();
        System.out.println(FileLoader.load(FILENAME_BATTLE));
        Game.pressToContinue();
        Game.clearScreen();
    }
    
    public static void afficheSprites(Battle bt){
        File file = FileFinder.find(bt.getMob().getFileName());
        StringBuilder sbName = new StringBuilder();
        StringBuilder sbHp = new StringBuilder();
        

        try(BufferedReader br1 = new BufferedReader(new FileReader(BattleView.FILERENAUD))){

            try (BufferedReader br2 = new BufferedReader(new FileReader(file))){
                while(br1.ready()){
                    System.out.println(br1.readLine() + BattleView.TAB + br2.readLine());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


        int pvPlayer = bt.getPlayer().getCurrentHp();
        int pvMob = bt.getFoe().getCurrentHp();
        String phrasePvPlayer = BattleView.PHRASE_PV + pvPlayer + " ";
        int setSpaceRenaudHp = (getMaxCarac(BattleView.FILERENAUD) - ( phrasePvPlayer ).length())/2 ;
        String phrasePvMob = BattleView.PHRASE_PV + pvMob + " ";
        int setSpaceMobHp = (getMaxCarac(file) - ( phrasePvMob ).length())/2 ;

        int setSpaceRenaudName = (getMaxCarac(BattleView.FILERENAUD) - "Renaud".length())/2 ;
        int setSpaceMobName = (getMaxCarac(file) - ( bt.getMob().getName() ).length())/2 ;


        sbName.append(setSpace( setSpaceRenaudName) + "Renaud" + setSpace(setSpaceRenaudName));
        sbName.append(setSpace(BattleView.TAB.length()*7));
        sbName.append(setSpace( setSpaceMobName) + bt.getMob().getName() + setSpace(setSpaceMobName));

        sbHp.append(setSpacePoint( setSpaceRenaudHp) + phrasePvPlayer + setSpacePoint(setSpaceRenaudHp));
        sbHp.append(setSpace(BattleView.TAB.length()*7));
        sbHp.append(setSpacePoint( setSpaceMobHp) + phrasePvMob + setSpacePoint(setSpaceMobHp));

        System.out.println();
        System.out.println(sbName.toString());
        System.out.println(sbHp.toString());

    }

    private static int getMaxCarac(File f){
        int max =0;
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            while(br.ready()){
                String temp = br.readLine();
                if(temp.length()>max){
                    max = temp.length();
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return max;
    }

    private static String setSpacePoint(int n){
        return setString(n, '.');
    }

    private static String setString(int n,char c){
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    private static String setSpace(int n){
        return setString(n, ' ');
    }
}
