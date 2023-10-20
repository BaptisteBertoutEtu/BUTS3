package fr.univlille.phonebook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ULillePhoneNumberTest {
    
        private ULillePhoneNumber ulille;
        private ULillePhoneNumber ulille2;

    @Before
    public void setUp(){
        ulille = new ULillePhoneNumber("53468");
        ulille2 = new ULillePhoneNumber("41682");
    }

    @Test
    public void testStandardFormat(){
        assertEquals("03.59.65.34.68", ulille.standardFormat());
        assertEquals("03.59.64.16.82", ulille2.standardFormat());
    }

    @Test
    public void testInternationalFormat(){
        assertEquals("+33.3.59.65.34.68", ulille.internationalFormat());
        assertEquals("+33.3.59.64.16.82", ulille2.internationalFormat());
    }
}
