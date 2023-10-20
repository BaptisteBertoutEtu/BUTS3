package fr.univlille.phonebook;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.univlille.exception.BugFixException;

public class PhoneBookTest {
    
    private PhoneBook phoneBook;

    @Before
    public void setUp(){
        this.phoneBook = new PhoneBook();
    }

    @Test
    public void testPutAndTestGetNumber(){
        PhoneNumber p1 = new PhoneNumber(33, 10, 20, 30, 50, 60);
        PhoneNumber p2 = new PhoneNumber(33, 11, 21, 31, 51, 61);
        PhoneNumber p3 = new PhoneNumber(33, 12, 22, 32, 52, 62);
        
        this.phoneBook.put("label1", p1);
        this.phoneBook.put("label2", p2);
        this.phoneBook.put("label3", p3);
        try {
            assertEquals(this.phoneBook.getNumber("label1"),p1);
            assertEquals(this.phoneBook.getNumber("label2"),p2);
            assertEquals(this.phoneBook.getNumber("label3"),p3);

        } catch (BugFixException e) {
            
        }
    }
}
