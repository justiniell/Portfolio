package cz.vse.adventura.logika;
/**
 *  Třída PrikazPoloz - implementuje příkaz polož do aventury.
 *
 *
 *@author     Daniel Justa
 *@version    1.0
 */
public class PrikazPoloz implements IPrikaz{
    private static final String NAZEV = "polož";
    private HerniPlan plan;
    private Batoh batoh;


    public PrikazPoloz(HerniPlan plan, Batoh batoh) {
        this.plan = plan;
        this.batoh = batoh;
    }
    /**
     *  Provádí příkaz "polož". Nejdřív si ověří jestli je věc v batohu, jinak neprovede přikaz polož.
     *  Pokud je daná věc v batohu, tak vyjme předmět z batohu a položí do prostoru.
     *
     *@param parametry
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mám položit? Musíš zadat název věci";
        }
        String nazevVeci = parametry[0];

        Vec vecVBatohu = batoh.getVeciVBatohu(nazevVeci);

        if (vecVBatohu == null) {
            return "Taková věc není v batohu";
        }
        try {
            if (batoh.odeberVec(vecVBatohu))
            {
                plan.getAktualniProstor().pridejVec(vecVBatohu);
                return " Věc odebrána z batohu a vložena do prostoru." ;
            }
            else {return "";}
        } catch (IllegalStateException ex) {
            return ex.getMessage();
        }
    }

    @Override
    public String getNazev() {return NAZEV;
    }
}
