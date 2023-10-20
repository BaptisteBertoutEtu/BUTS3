package fr.univlille.effect;

import fr.univlille.Game;
import fr.univlille.entity.Renaud;

public class LevelChoice {
    public static final int NB_CHOICES = 3;
    
    public static Bonus[] drawBonuses(Renaud player) {
        Bonus[] res = new Bonus[NB_CHOICES];
        for (int i = 0; i < NB_CHOICES; i++) {
            res[i] = Bonus.random(player.getBonusDrawList());
        }
        return res;
    }

    public static Bonus pickBonus(Renaud player) {
        Bonus[] choices = drawBonuses(player);
        int choice = 0;
        StringBuilder res = new StringBuilder("");
        while(choice < 1 || choice > 3) {
            for (int i = 0; i < choices.length; i++) {
                res.append(""+(i+1) + " : " + choices[i].getName() + ", ");
                if(choices[i].getBonusType().equals(BonusType.BUFF)) {
                    res.append("ajoute +");
                } else if(choices[i].getUseType().equals(UseType.DAMAGE)) {
                    res.append("inflige ");
                } else {
                    res.append("soigne ");
                }
                if(choices[i].getBonusType().equals(BonusType.BUFF)) {
                    res.append(choices[i].getValue() + "% en " + choices[i].getSpellType() +
                        " (Augmentation de statistique) \n");
                } else if(choices[i].getBonusType().equals(BonusType.SPELL_SCALING)) {
                    res.append(choices[i].getValue() + "% de la stat "  + choices[i].getSpellType() +
                        ", Tours de recharge : " + choices[i].getCooldown() +
                        " (Sort dépendant d'une statistique) \n");
                } else {
                    res.append(choices[i].getValue() + " points de vie, Tours de recharge : " + 
                        choices[i].getCooldown() + " (Sort à dégât fixe) \n");             
                }
            }
            System.out.println(res + "\n Sélectionnez un bonus : ");
            choice = Game.readIntNotNull();
        }
        return choices[choice-1];
    }
}
