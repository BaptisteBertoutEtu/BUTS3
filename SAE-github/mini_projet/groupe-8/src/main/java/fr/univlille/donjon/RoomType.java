package fr.univlille.donjon;

import fr.univlille.Color;

public enum RoomType {
    ADVICE(Color.FG_BLUE, 'A'),
    ENEMY(Color.FG_RED, 'E'),
    BOSS(Color.FG_YELLOW, 'B');

    private Color color;
    private char cara;

    private RoomType(Color color, char cara){
        this.color = color;
        this.cara = cara;
    }

    public Color getColor(){
        return color;
    }

    public char getCara(){
        return cara;
    }

    @Override
    public String toString(){
        return getClass().getSimpleName() + "[color:" + color + ", cara:" + cara + "]";
    }
}