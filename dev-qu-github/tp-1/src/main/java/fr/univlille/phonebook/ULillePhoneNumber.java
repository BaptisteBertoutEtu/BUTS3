package fr.univlille.phonebook;

public class ULillePhoneNumber extends PhoneNumber{

    public static final String U_LILLE_PREFIX = "3596";

    public ULillePhoneNumber(String fiveDigits)  {
        super(PhoneNumber.FRANCE_CODE, 
        Integer.valueOf(U_LILLE_PREFIX.substring(0, 1)),
        Integer.valueOf(U_LILLE_PREFIX.substring(1, 3)),
        Integer.valueOf(U_LILLE_PREFIX.substring(3, 4)+fiveDigits.substring(0, 1)),
        Integer.valueOf(fiveDigits.substring(1, 3)), 
        Integer.valueOf(fiveDigits.substring(3, 5)));
    }

}
