package test.java.fr.univlille.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.univlille.iutinfo.cam.player.IStrategy;
import main.java.fr.univlille.model.entity.Entity;
import main.java.fr.univlille.model.entity.Hunter;
import main.java.fr.univlille.model.entity.Monster;
import main.java.fr.univlille.model.entity.iastrategy.IAHunter;

public class EntityTest {
    private IStrategy iaHunter;

    @Before
    public void itit(){
        this.iaHunter = new IAHunter();
    }
    
    @Test
    public void getIa_should_be_not_null(){
        Entity monstre = new Monster(iaHunter);
        assertNotNull(monstre.getIA());
    }

    @Test
    public void getIa_should_be_equals_to_iaHunter(){
        Entity monstre = new Monster(iaHunter);
        assertEquals(monstre.getIA(), iaHunter);
    }

    @Test
    public void isIa_should_be_true(){
        Entity hunter = new Hunter(new IAHunter());
        assertTrue(hunter.isIA());
    }

    @Test
    public void isIa_should_be_false(){
        Entity hunter = new Hunter();
        assertFalse(hunter.isIA());
    }
}
