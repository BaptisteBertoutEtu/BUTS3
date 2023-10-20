package fr.univlille.donjon;

import java.util.List;

import fr.univlille.bestiary.Bestiary;

public class DonjonRoom {
    private int roomIdMax;
    private int roomId;
    private RoomType type;
    private Theme theme;

    private Bestiary mob;
    private Advice advice;

    public DonjonRoom(int roomIdMax, int roomId, RoomType type, Theme theme){
        this.roomIdMax = roomIdMax;
        this.roomId = roomId;
        this.type = type;
        this.theme = theme;
        generateRoom();
    }

    public int getRoomIdMax(){ return roomIdMax; }
    public int getRoomId(){ return roomId; }
    public RoomType getType(){ return type; }
    public Theme getTheme(){ return theme; }
    public Bestiary getMob(){ return mob; }
    public Advice getAdvice(){ return advice; }

    public void generateRoom(){
        if(theme == Theme.FINALE){
            if(roomId == roomIdMax) mob = Bestiary.getFinalBoss();
            else mob = Bestiary.randomBossNotFinal();
            return;
        }

        List<Bestiary> mobs = Bestiary.getMobs(theme);
        switch(type){
            case ADVICE:
                advice = Advice.random();
                break;
            case ENEMY:
                mob = Bestiary.random(mobs, false);
                break;
            case BOSS:
                mob = Bestiary.random(mobs, true);
                break;
        }
    }

    public void print(){
        print(0);
    }

    protected void print(int distance){
        for(int i = 0; i < distance; i++) System.out.print("\t");
        System.out.println(this);
    }

    public String toString(){
        return getClass().getSimpleName() + "[type:" + type + ", theme:" + theme + ", mob:" + mob + ", advice:" + advice + "]";
    }
}