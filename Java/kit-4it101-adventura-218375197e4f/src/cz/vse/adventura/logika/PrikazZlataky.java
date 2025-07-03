package cz.vse.adventura.logika;
/**
 *  Třída PrikazZlataky - implementuje příkaz zlaťáky do aventury.
 *
 *
 *@author     Daniel Justa
 *@version    1.0
 */
public class PrikazZlataky implements IPrikaz{
    private static final String NAZEV = "zlaťáky";
    private Postava postava;
    public PrikazZlataky (Postava postava){
        this.postava= postava;
    }

    /**
     *  Provádí příkaz "zlaťáky". Vypíše počet zlaťáků postavy.
     *
     *@param parametry
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return  "Tvůj počet zlaťáků: " + postava.gold();
    }
    @Override
    public String getNazev() {
        return NAZEV;
    }
}