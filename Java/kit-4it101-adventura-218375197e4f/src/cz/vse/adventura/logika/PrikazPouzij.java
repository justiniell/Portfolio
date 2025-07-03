package cz.vse.adventura.logika;
/**
 *  Třída PrikazPouzij - implementuje příkaz použij do aventury.
 *
 *
 *@author     Daniel Justa
 *@version    1.0
 */
public class PrikazPouzij implements IPrikaz {
    private static final String NAZEV = "použij";
    private Postava vybranaPostava;
    private Batoh batoh;

    public PrikazPouzij(Postava postava, Batoh batoh) {
        this.vybranaPostava = postava;
        this.batoh = batoh;
    }

    /**
     *  Provádí příkaz "použij". Nejdřív si ověří jestli je věc v batohu, jinak neprovede přikaz použij.
     *  Pokud je daná věc v batohu, tak vyjme předmět z batohu a použije ho.
     *
     *@param parametry
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        // Prohledá batoh
        String obsahBatohu = batoh.vypisBatoh();
        if (obsahBatohu.contains("elixirSily")) {
            // Zvýší sílu vybrané postavy o 5
            vybranaPostava.zvysAp(5);

            // Odeber elixír síly z batohu
            batoh.odeberVec(batoh.getVeciVBatohu("elixirSily"));
            return "Použil jsi elixír síly. Tvoje síla je nyní: " + vybranaPostava.getAp() + ".";

        } else if (obsahBatohu.contains("elixirZivotu")) {
            // Zvýší HP vybrané postavy o 20
            vybranaPostava.zvysHp(20);

            // Odeber elixír životu z batohu
            batoh.odeberVec(batoh.getVeciVBatohu("elixirZivotu"));
            return "Použil jsi elixír životů. Tvůj počet životů je nyní: " + vybranaPostava.getHp() + ".";
        }

        // Pokud nebyl nalezen žádný elixír v batohu
        return "V batohu nemáš žádný elixír k použití.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}