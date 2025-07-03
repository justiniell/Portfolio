package cz.vse.adventura.logika;
/**
 *  Třída PrikazUtok - implementuje příkaz utok do aventury.
 *
 *
 *@author     Daniel Justa
 *@version    1.0
 */
public class PrikazUtok implements IPrikaz {
    private static final String NAZEV = "útok";
    private HerniPlan plan;
    private Postava vybranaPostava;


    public PrikazUtok(HerniPlan plan,Postava vybranaPostava) {
        this.plan = plan;
        this.vybranaPostava = vybranaPostava;
    }

    /**
     *  Provádí příkaz "útok". Nejdřív si ověří jestli je nestvura v prostoru, jinak provede přikaz utok, ale zpusobi poškozeni sobě.
     *Pokud je nestvura v prostoru, tak ji zpusobi poškozeni.
     *
     *@param parametry
     *@return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Tento příkaz nebere parametry.";
        }
        Prostor aktualniProstor = plan.getAktualniProstor();

        // kontrola existence nestvůry v aktuálním prostoru
        if (!aktualniProstor.existujeNestvura()) {
            vybranaPostava.snizHp(1);
            return "V prostoru není nestvůra na kterou můžeš útočit, promáchl jsi a zranil ses. Tvůj počet životů je nyní: "+ vybranaPostava.getHp();
        }

            aktualniProstor.getNestvura().snizHp(vybranaPostava.utok());
        if (aktualniProstor.getNestvura() != null) {
            vybranaPostava.snizHp(aktualniProstor.getNestvura().utok());
            vybranaPostava.zivy();
        }
        if (aktualniProstor.getNestvura()==null){
            return "Podařilo se ti zabít nestvůru.";
        }
        else if (vybranaPostava.getHp()>0) {
            return "Aktualni počet životů nestvůry: " + aktualniProstor.getNestvura().getHp() + "\n"
                    + aktualniProstor.getNestvura().getName()+" útočí na tebe." + "\n"
                    + "Tvůj počet životů je nyní: "+ vybranaPostava.getHp();
        }
        else {return"";}
    }
    @Override
    public String getNazev() {
        return NAZEV;
    }
}