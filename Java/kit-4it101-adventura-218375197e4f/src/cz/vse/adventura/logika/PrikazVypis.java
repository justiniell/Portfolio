package cz.vse.adventura.logika;
/**
 *  Třída PrikazVypis - implementuje příkaz vypiš do aventury.
 *
 *
 *@author     Daniel Justa
 *@version    1.0
 */
public class PrikazVypis implements IPrikaz{
    private static final String NAZEV = "vypiš";
    private Batoh batoh;
    public PrikazVypis (Batoh batoh){
        this.batoh= batoh;
    }

    /**
     *  Provádí příkaz "vypiš". Vypíše všechny předměty v batohu, případně vypíše prázdný batoh.
     *
     *@param parametry
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (batoh.jePrazdny()){
            return "Batoh je prázdný";
        }
        else {
            return "V batohu máš: " + batoh.vypisBatoh();
        }
    }
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
