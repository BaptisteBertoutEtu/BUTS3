package fr.univlille.donjon;

public enum Theme{
    INTERIEUR,EDUCATION,OPPOSITION,CULTURE,FINALE;

    public static int getSize(){
        return values().length;
    }
}
