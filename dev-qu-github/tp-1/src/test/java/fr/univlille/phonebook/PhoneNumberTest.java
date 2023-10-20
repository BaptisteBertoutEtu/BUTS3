package fr.univlille.phonebook;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PhoneNumberTest {

    private PhoneNumber nationalNumber;

    @BeforeEach
    public void setUp(){
        nationalNumber = new PhoneNumber(33, 3, 59, 03, 21, 06);
    }

    @Test
    public void testStandardFormat(){
        assertEquals("03.59.03.21.06", nationalNumber.standardFormat());
    }

    @Test
    public void testInternationalFormat(){
        assertEquals("+33.3.59.03.21.06", nationalNumber.internationalFormat());
    }

}
