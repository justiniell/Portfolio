package cz.vse.adventura.logika;
/**
 *  Třída PrikazNakup implementuje pro hru příkaz nakup.
 *
 *@author     Daniel Justa
 *@version    1.0
 *
 */
public class PrikazNakup implements IPrikaz {
    private static final String NAZEV = "nakup";
    private Postava postava;
    private Batoh batoh;
    private HerniPlan herniPlan;

    public PrikazNakup(Postava postava, Batoh batoh, HerniPlan herniPlan) {
        this.postava = postava;
        this.batoh = batoh;
        this.herniPlan = herniPlan;
    }
    /**
     *  Provádí příkaz "nakup". Nejdřív si ověří jestli jsme v prostoru čaroobchodu, jinak neprovede přikaz nakup.
     *  Pokud jsme v čaoobchodě, tak vezme zlaťáky a vloží předmět do batohu.
     *
     *@param parametry
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0)
        {
            return "Nevím, co chceš nakoupit.";
        }

        if (!herniPlan.getAktualniProstor().getNazev().equals("čaroobchod")) {
            return "Nákup lze provést pouze v obchodě.";
        }

        String nazevVeci = parametry[0];
        if (nazevVeci.equals("elixirSily")) {
            if (postava.spendGold(20)) {
                batoh.pridejVec(new Vec("elixirSily", true));
                return "Koupil sis elixírSíly za 20 zlaťáků.";
            } else
            {
                return "Nemáš dost zlaťáků na nákup elixíru síly.";
            }
        } else if (nazevVeci.equals("elixirZivotu")) {
            if (postava.spendGold(20)) {
                batoh.pridejVec(new Vec("elixirZivotu", true));
                return "Koupil sis elixirŽivotů za 20 zlaťáků.";
            } else {
                return "Nemáš dost zlaťáků na nákup elixirŽivotů.";
            }
        } else {
            return "Tento předmět nelze koupit.";
        }
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}