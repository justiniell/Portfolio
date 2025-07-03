package cz.vse.adventura.logika;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;
    private Hra hra2;
    private Hra hra3;


    @BeforeEach
    public void setUp() {
        hra1 = new Hra(1);
        hra2 = new Hra (2);
        hra3 = new Hra (3);

    }

    @AfterEach
    public void tearDown() {
    }
    @Test
    public void testValecnik() {
        // Ensure the correct character was selected
        assertEquals("Válečník", hra1.vybranaPostava.getName());
        assertEquals(500, hra1.vybranaPostava.getHp());
        assertEquals(20, hra1.vybranaPostava.getAp());
        assertEquals(20, hra1.vybranaPostava.getGold());
    }
    @Test
    public void testMag() {
        // Ensure the correct character was selected
        assertEquals("Mág", hra2.vybranaPostava.getName());
        assertEquals(80, hra2.vybranaPostava.getHp());
        assertEquals(30, hra2.vybranaPostava.getAp());
        assertEquals(0, hra2.vybranaPostava.getGold());
    }
    @Test
    public void testLukostrelec() {
        // Ensure the correct character was selected
        assertEquals("Lukostřelec", hra3.vybranaPostava.getName());
        assertEquals(90, hra3.vybranaPostava.getHp());
        assertEquals(25, hra3.vybranaPostava.getAp());
        assertEquals(0, hra3.vybranaPostava.getGold());
    }
    @Test
    public void testPrubehHry() {
        assertEquals("chatrč", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("seber elixirSily");

        assertEquals(1,hra1.batoh.getPocetUlozenychVeci());
        assertEquals("elixirSily", hra1.batoh.getVeciVBatohu("elixirSily").getNazev());

        hra1.zpracujPrikaz("seber elixirZivotu");

        assertEquals(2,hra1.batoh.getPocetUlozenychVeci());
        assertEquals("elixirZivotu", hra1.batoh.getVeciVBatohu("elixirZivotu").getNazev());

        hra1.zpracujPrikaz("jdi náměstí");

        assertFalse(hra1.konecHry());
        assertEquals("náměstí", hra1.getHerniPlan().getAktualniProstor().getNazev());

        hra1.zpracujPrikaz("jdi prvníPodlaží");

        assertFalse(hra1.konecHry());
        assertEquals("prvníPodlaží", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Kostlivec",hra1.getHerniPlan().getAktualniProstor().getNestvura().getName());

        hra1.zpracujPrikaz("jdi druhéPodlaží");

        assertEquals("prvníPodlaží", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(80, hra1.getHerniPlan().getAktualniProstor().getNestvura().getHp());

        hra1.zpracujPrikaz("útok");

        assertTrue(hra1.getHerniPlan().getAktualniProstor().getNestvura().getHp()<80);

        zautoc(3);

        assertNull(hra1.getHerniPlan().getAktualniProstor().getNestvura());

        hra1.zpracujPrikaz("použij elixirSily");
        assertEquals(25, hra1.vybranaPostava.getAp());

        hra1.zpracujPrikaz("použij elixirZivotu");
        hra1.zpracujPrikaz("jdi druhéPodlaží");
        zautoc(4);

        hra1.zpracujPrikaz("jdi třetíPodlaží");
        zautoc(4);

        hra1.zpracujPrikaz("jdi doupěDraka");
        zautoc(6);

        hra1.zpracujPrikaz("jdi poklad");
        assertTrue(hra1.konecHry());
    }
    void zautoc (int pocet){
        for (int i = 0; i < pocet; i++) {
            hra1.zpracujPrikaz("útok");
        }
    }
}
