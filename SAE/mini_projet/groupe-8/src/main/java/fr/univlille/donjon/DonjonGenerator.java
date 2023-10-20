package fr.univlille.donjon;

import fr.univlille.Color;
import fr.univlille.entity.Renaud;

public class DonjonGenerator {
    public static final int FLOOR_COUNT = Theme.getSize();
    public static final int ROOM_WIDTH = 25;
    public static final int ROOM_HEIGHT = 9;
    public static final int ROOM_BETWEEN_WAY = 5;
    public static final char CHAR_UP_LEFT = '╯';
    public static final char CHAR_UP_RIGHT = '╰';
    public static final char CHAR_DOWN_LEFT = '╮';
    public static final char CHAR_DOWN_RIGHT = '╭';
    public static final char CHAR_VERTICAL = '│';
    public static final char CHAR_HORIZONTAL = '─';
    public static final char CHAR_WAY = '═';

    private Donjon donjon;
    private Renaud player;
    private String[][] map;

    public DonjonGenerator(Donjon donjon, Renaud player){
        this.donjon = donjon;
        this.player = player;
        loadCurrentStage();
    }

    public Donjon getDonjon(){
        return donjon;
    }

    public Renaud getPlayer(){
        return player;
    }

    public DonjonFloor getCurrentFloor(){
        return donjon.getFloor(player.getStage());
    }

    public DonjonRoom getCurrentRoom(){
        return donjon.getRoom(player.getStage(), player.getRoom());
    }

    public Theme getCurrentTheme(){
        DonjonRoom dr = getCurrentRoom();
        if(dr == null) return null;
        return dr.getTheme();
    }

    private void loadCurrentStage(){
        int currentStage = player.getStage();
        generateStage(currentStage);
        loadRooms(currentStage);
    }

    private void generateStage(int stage){
        int roomsCount = donjon.getRoomsCount(stage);

        int width = roomsCount * ROOM_WIDTH + (roomsCount - 1) * ROOM_BETWEEN_WAY;
        int height = ROOM_HEIGHT;

        map = new String[height][width];

        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                int valueX = x % (ROOM_WIDTH + ROOM_BETWEEN_WAY);
                RoomType currentType = null;
                char c = ' ';
                
                if(valueX >= 0 && valueX <= ROOM_WIDTH - 1){
                    if(valueX % (ROOM_WIDTH - 1) == 0) c = CHAR_VERTICAL;
                    if(y % (ROOM_HEIGHT - 1) == 0) c = CHAR_HORIZONTAL;
                    if(y == 0){
                        if(valueX == 0) c = CHAR_DOWN_RIGHT;
                        if(valueX == ROOM_WIDTH - 1) c = CHAR_DOWN_LEFT;
                    }else if(y == ROOM_HEIGHT - 1){
                        if(valueX == 0) c = CHAR_UP_RIGHT;
                        if(valueX == ROOM_WIDTH - 1) c = CHAR_UP_LEFT;
                    }
                    currentType = getRoomTypeByWidthValue();
                }else if(valueX >= ROOM_WIDTH && y == ROOM_HEIGHT / 2){
                    c = CHAR_WAY;
                }

                String s = String.valueOf(c);
                if(currentType != null){
                    s = Color.charToColor(currentType.getColor(), c);
                }
                map[y][x] = s;
            }
        }
    }

    private RoomType getRoomTypeByWidthValue(){
        return RoomType.BOSS;
    }

    private void loadRooms(int stage){
        DonjonFloor floor = donjon.getFloor(stage);
        if(floor == null) return;
        DonjonRoom[] rooms = floor.getRooms();
        for(int i = 0; i < rooms.length; i++){
            loadRoom(rooms[i], i);
        }
    }

    private void loadRoom(DonjonRoom dr, int room){
        RoomType type = dr.getType();
        int x = ROOM_WIDTH / 2 + ROOM_WIDTH * room + ROOM_BETWEEN_WAY * room;
        int y = ROOM_HEIGHT / 2;
        char c = 'X';

        switch(type){
            case ADVICE : c = RoomType.ADVICE.getCara(); break;
            case ENEMY : c = RoomType.ENEMY.getCara(); break;
            case BOSS : c = RoomType.BOSS.getCara(); break;
        }
        
        map[y][x] = String.valueOf(c);
    }

    public void drawDonjon(){
        if(donjon == null) System.err.println("Le donjon est null");
        else if(player == null) System.err.println("Le joueur est null");
        else{
            loadCurrentStage();
            drawRooms();
            drawPlayer();
        }
    }

    private void drawRooms(){
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                String c = map[y][x];
                sb.append(c + "");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private void drawPlayer(){
        StringBuilder sb = new StringBuilder();
        int room = player.getRoom();
        int width = ROOM_WIDTH / 2 + ROOM_WIDTH * room + ROOM_BETWEEN_WAY * room;
        for(int y = 0; y < 4; y++){
            for(int x = 0; x < width; x++){
                sb.append(" ");
                String name = player.getName();
                if(x == width - name.length()/2 && y == 3){
                    sb.append(name);
                }
            }
            if(y == 0) sb.append('ᐱ');
            if(y == 1) sb.append('|');
            if(y == 2) sb.append('o');
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void print(){
        if(donjon == null) return;
        donjon.print();
    }

    public String toString(){
        return getClass().getSimpleName() + "[donjon:" + donjon + ", player:" + player + "]";
    }
}
