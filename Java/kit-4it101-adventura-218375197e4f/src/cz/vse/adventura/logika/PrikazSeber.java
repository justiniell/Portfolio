package cz.vse.adventura.logika;
/**
 *  Třída PrikazSeber - implementuje příkaz seber do aventury.
 *
 *
 *@author     Daniel Justa
 *@version    1.0
 */
public class PrikazSeber implements IPrikaz {
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Batoh batoh;


    public PrikazSeber(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }

    /**
     *  Provádí příkaz "seber". Nejdřív si ověří jestli je věc v prostoru, jinak neprovede přikaz seber.
     *Pokud je daná věc v prostoru, tak vyjme předmět z prostoru a vloží ho do batohu.
     *
     *@param parametry
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Co mám sebrat? Musíš zadat název věci.";
        }
        String nazevVeci = parametry[0];

        Vec vecVProstoru = plan.getAktualniProstor().getVec(nazevVeci);

        if (vecVProstoru == null) {
            return "Taková věc tady není.";
        }
        try {
            if (batoh.pridejVec(vecVProstoru))
            {
                plan.getAktualniProstor().odeberVec(nazevVeci);
                return " Věc přidána do batohu. \n " + plan.getAktualniProstor().popisVeci();
            }
            else {return "";}
        } catch (IllegalStateException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}

