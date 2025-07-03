package cz.vse.adventura.logika;


import java.util.HashSet;
import java.util.Set;

/**
 *  Třída HerniPlan - třída představující mapu a stav adventury.
 *
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Daniel Justa
 *@version    1.0
 */
public class HerniPlan {


    private Prostor aktualniProstor;
    private Set<Prostor> prostory = new HashSet<>();
    private Set<Vec> veci = new HashSet<>();
    public Postava vybranaPostava;
    private Hra hra;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     */
    public HerniPlan(Postava vybranaPostava, Hra hra) {
        this.vybranaPostava = vybranaPostava;
        this.hra = hra;
        zalozProstoryHry();

    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví chatrč.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor chatrc = new Prostor("chatrč", "Středověká chatrč hrdiny");
        Prostor namesti = new Prostor("náměstí", "Městské náměstí");
        Prostor caroobchod = new Prostor("čaroobchod", "Obchod s elixíry " +"\n" + "elixirZivotu - 20 zlaťáků"+"\n" +"elixirSily - 20 zlaťáků");
        Prostor prvniPodlazi = new Prostor("prvníPodlaží", "První podlaží podzemí");
        Prostor tajemnaJeskyne = new Prostor("tajemnáJeskyně", "Jeskyně s truhlou");
        Prostor druhePodlazi = new Prostor("druhéPodlaží", "Druhé podlaží podzemí");
        Prostor tajuplnaJeskyne = new Prostor("tajuplnáJeskyně", "Jeskyně s propastí");
        Prostor tretiPodlazi = new Prostor("třetíPodlaží", "Třetí podlaží podzemí");
        Prostor doupeDraka = new Prostor("doupěDraka", "Drak ve své celé kráse i se svým pokladem");
        Prostor poklad = new Prostor("poklad", "");


        prostory.add(chatrc);
        prostory.add(namesti);
        prostory.add(caroobchod);
        prostory.add(prvniPodlazi);
        prostory.add(tajemnaJeskyne);
        prostory.add(druhePodlazi);
        prostory.add(tajuplnaJeskyne);
        prostory.add(tretiPodlazi);
        prostory.add(doupeDraka);
        prostory.add(poklad);


        Vec elixirSily = new Vec("elixirSily", true);
        Vec elixirZivotu = new Vec("elixirZivotu", true);
        Vec obrovskyKamen = new Vec("obrovskýKámen", false);


        veci.add(elixirSily);
        veci.add(elixirZivotu);
        veci.add(obrovskyKamen);


        chatrc.pridejVec(elixirSily);
        chatrc.pridejVec(elixirZivotu);
        tajemnaJeskyne.pridejVec(elixirZivotu);
        namesti.pridejVec(obrovskyKamen);
        tretiPodlazi.pridejVec(elixirSily);


        Nestvura kostlivec = new Nestvura("Kostlivec", 80, 10, this);
        prvniPodlazi.pridejNestvuru(kostlivec);

        Nestvura nocniGhul = new Nestvura("Noční ghůl", 90, 20, this);
        druhePodlazi.pridejNestvuru(nocniGhul);

        Nestvura golem = new Nestvura("Golem", 100, 30, this);
        tretiPodlazi.pridejNestvuru(golem);

        Nestvura zlatyDrak = new Nestvura("Zlatý drak", 150, 35, this);
        doupeDraka.pridejNestvuru(zlatyDrak);


        // přiřazují se průchody mezi prostory (sousedící prostory)
        chatrc.setVychod(namesti);
        namesti.setVychod(chatrc);
        namesti.setVychod(caroobchod);
        caroobchod.setVychod(namesti);
        namesti.setVychod(prvniPodlazi);
        prvniPodlazi.setVychod(namesti);
        prvniPodlazi.setVychod(tajemnaJeskyne);
        tajemnaJeskyne.setVychod(prvniPodlazi);
        prvniPodlazi.setVychod(druhePodlazi);
        druhePodlazi.setVychod(prvniPodlazi);
        druhePodlazi.setVychod(tajuplnaJeskyne);
        tajuplnaJeskyne.setVychod(druhePodlazi);
        druhePodlazi.setVychod(tretiPodlazi);
        tretiPodlazi.setVychod(druhePodlazi);
        tretiPodlazi.setVychod(doupeDraka);
        doupeDraka.setVychod(tretiPodlazi);
        doupeDraka.setVychod(poklad);


        aktualniProstor = chatrc;  // hra začíná v chatrči
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
     *
     * @return aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        if (prostor.getNazev().equals("tajuplnáJeskyně")) {
            vybranaPostava.snizHp(50);
            System.out.println("Málem jsi spadnul do propasti!" + "\n"
                    + "Leknutím ztrácíš 50 životů. Zbývá ti: " + vybranaPostava.getHp() + " HP.");
        }
        if (prostor.getNazev().equals("tajemnáJeskyně")) {
            vybranaPostava.addGold(60);
            System.out.println("Našel jsi truhlu, ze které jsi získal 60 zlaťáků");
        }
        if (prostor.getNazev().equals("poklad")) {
            hra.setKonecHry(true);
        }
    }
}
