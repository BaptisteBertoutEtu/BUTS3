package fr.univlille.manager;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.univlille.exception.BugFixException;
import fr.univlille.phonebook.PhoneBook;
import fr.univlille.phonebook.PhoneNumber;

public class ConsolePhoneBookManager {

    private static Scanner scan = new Scanner(System.in);


    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        indicatifConfig();
        menu(phoneBook);
    }

    private static void indicatifConfig() {
        int choiceNum = askChoice("Souhaitez-vous passer des appels depuis", "la France", "l'international");

        if (choiceNum == 0){
            PhoneNumber.setCountryCodeReference(PhoneNumber.FRANCE_CODE);
        }else if (choiceNum == 1){
            PhoneNumber.setCountryCodeReference(askCountryCode());
        }
    }

    private static int askCountryCode() {
        System.out.println("Entrez l'indicatif du pays : ");
        String answer = scan.nextLine();
            try{
                int userCountryCode = Integer.parseInt(answer);
                if (userCountryCode<=0)
                    throw new NumberFormatException();
                else
                    return userCountryCode;
            }catch(NumberFormatException nfe){
                System.out.println("Valeur incorrecte : nombre positif attendu");
                return askCountryCode();
            }
    }

    private static int askChoice(String question, String... choices) {
        System.out.println(question);
        int num=1;
        for(String choice : choices)
            System.out.println(num++ + " - " + choice);

        try{
            int choice = scan.nextInt()-1;
            if (choice>=0 && choice < choices.length){
                scan.nextLine();
                return choice;
            }
            else
                throw new InputMismatchException();
        }catch(InputMismatchException ime){
            scan.nextLine();
            return askChoice(question, choices);            
        }
    }

    private static void menu(PhoneBook phoneBook) {
        System.out.println("Que souhaitez-vous faire ?");
        System.out.println("\t* : afficher tout le répertoire");
        System.out.println("\t+ : ajouter une entrée");
        System.out.println("\t- : supprimer une entrée");
        System.out.println("\t? : consulter les coordonnées téléphoniques d'un correspondant particulier");        
        System.out.println("\tq : quitter le programme");
        chooseAction(phoneBook);
    }

    private static void chooseAction(PhoneBook phoneBook) {        
        String input = scan.nextLine();
        switch(input){
            case "*": System.out.println(phoneBook); break;
            case "+": addEntry(phoneBook);  break;
            case "-": removeEntry(phoneBook);  break;
            case "?": getNumber(phoneBook);  break;
            case "q": scan.close(); System.exit(0); break;
            default : break; 
        } 
        menu(phoneBook);      
    }

    private static void getNumber(PhoneBook phoneBook) {
        String name = askName();
        try {
            System.out.println(phoneBook.getNumber(name));
            
        } catch (BugFixException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String askName(){
        System.out.println("Entrez un nom ");
        return scan.nextLine();
    }

    private static void removeEntry(PhoneBook phoneBook) {
        String name = askName();
        phoneBook.remove(name);
    }

    private static void addEntry(PhoneBook phoneBook) {
        int area, sector, one, two, three;
        String name = askName();

        System.out.println("Entrez un indicatif");
        int country = askCountryCode();

        System.out.println("Entrez un numéro sous la forme xx.xx.xx.xx.xx");
        scan.useDelimiter("\\.|\n");
        area = scan.nextInt();
        sector = scan.nextInt();
        one = scan.nextInt();
        two = scan.nextInt();
        three = scan.nextInt();

        phoneBook.put(name,new PhoneNumber(country, area, sector, one, two, three));
        scan.nextLine();
        scan.reset();
    }
    
}
