package fr.univlille;

public enum Color {
	BLACK("\u001B[40m"),
	RED("\u001B[41m"),
	GREEN("\u001B[42m"),
	YELLOW("\u001B[43m"),
	BLUE("\u001B[44m"),
	PURPLE("\u001B[45m"),
	CYAN("\u001B[46m"),
	WHITE("\u001B[47m"),

    FG_BLACK("\u001B[30m"),
	FG_RED("\u001B[31m"),
	FG_GREEN("\u001B[32m"),
	FG_YELLOW("\u001B[33m"),
	FG_BLUE("\u001B[34m"),
	FG_PURPLE("\u001B[35m"),
	FG_CYAN("\u001B[36m"),
	FG_WHITE("\u001B[37m");

	public static final String RESET = "\u001B[0m";
    private String code;

    private Color(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

	public static String charToColor(Color color, char c){
		return color.getCode() + c + RESET;
	}

	public static String stringToColor(Color color, String s){
		return color.getCode() + s + RESET;
	}
}
