package cz.vse.adventura.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček
 * @version   pro školní rok 2016/2017
 */
public class PrikazyTest
{
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    
    @BeforeEach
    public void setUp() {
        hra = new Hra(1);
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan());
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(null, seznPrikazu.vratPrikaz("nápověda"));
    }
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("nápověda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("nápověda"));
        assertEquals(false, nazvy.contains("Konec"));
    }
    @Test
    public void testDalsichPrikazu() {
        hra.zpracujPrikaz("jdi náměstí");
        hra.zpracujPrikaz("jdi čaroobchod");

        assertEquals("čaroobchod",hra.getHerniPlan().getAktualniProstor().getNazev());

        hra.zpracujPrikaz("nakup elixirZivotu");
        assertEquals("elixirZivotu", hra.batoh.getVeciVBatohu("elixirZivotu").getNazev());

        hra.zpracujPrikaz("vypiš");

        hra.zpracujPrikaz("polož elixirZivotu");
        assertEquals(0,hra.batoh.getPocetUlozenychVeci());

        hra.zpracujPrikaz("zlaťáky");
        assertEquals(0, hra.vybranaPostava.getGold());

        hra.zpracujPrikaz("jdi náměstí");

        hra.zpracujPrikaz("jdi prvníPodlaží");

        zautoc(4);

        assertTrue(hra.vybranaPostava.getGold() > 0);


    }
    void zautoc (int pocet){
        for (int i = 0; i < pocet; i++) {
            hra.zpracujPrikaz("útok");
        }
    }
}
