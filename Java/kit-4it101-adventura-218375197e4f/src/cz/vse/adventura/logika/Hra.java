package cz.vse.adventura.logika;
import java.util.Scanner;
/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Daniel Justa
 *@version    1.0
 */
//factory
public class Hra implements IHra {
    public Batoh batoh;
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    public Postava vybranaPostava;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra (){ //konstruktor pro normal uživ. vstup
        this(vyberPostavu());
    }
    public Hra(int choice) { //přetížený konstruktor se specifickym výběrem charakteru
        batoh = new Batoh(5);
        vybranaPostava = vyberPostavu(choice);
        herniPlan = new HerniPlan(vybranaPostava, this);
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazVypis(batoh));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(vybranaPostava, batoh));
        platnePrikazy.vlozPrikaz(new PrikazNakup(vybranaPostava, batoh, herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazUtok(herniPlan, vybranaPostava));
        platnePrikazy.vlozPrikaz(new PrikazZlataky(vybranaPostava));
    }
    /**
     *  Vrátí úvodní zprávu pro hráče.
     *  @return String
     */
    public String vratUvitani() {
        return
                "Vybral sis postavu:\n" +
                vybranaPostava.name + "\n" +
                "Počet životů: " + vybranaPostava.hp + "\n" +
                "Síla: " + vybranaPostava.ap + "\n" +
                "\n" +
                "Úkol už znáš a pokud budeš potřebovat s něčím helpnout napiš 'nápověda'.\n" +
                "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     *  @return String
     */
    public String vratEpilog() {
        return "Díky za službu hrdino.";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     * @return true/false
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return String - vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return HerniPlan    odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }

    /**
     * Vrací vybrané číslo postavy
     * @return int
     */
    private static int vyberPostavu() {
         Scanner scanner = new Scanner(System.in);
        // Nabídneme hráči výběr postavy
        System.out.println(" __      ___________ ______     _       _    _ _____  _____ _____ _   _  ____  \n" +
                " \\ \\    / /_/__   __|  ____|   | |     | |  | |  __ \\|  __ \\_   _| \\ | |/ __ \\ \n" +
                "  \\ \\  / /_ _| | |  | |__      | |     | |__| | |__) | |  | || | |  \\| | |  | |\n" +
                "   \\ \\/ / | |  | |  |  __| _   | |     |  __  |  _  /| |  | || | | . ` | |  | |\n" +
                "    \\  /  | |  | |  | |___| |__| |     | |  | | | \\ \\| |__| || |_| |\\  | |__| |\n" +
                "     \\/  |___| |_|  |______\\____/      |_|  |_|_|  \\_\\_____/_____|_| \\_|\\____/ \n" +
                "                                                                              ");
        System.out.println("Vypadáš jako někdo, kdo by byl ochotnej za pár zlaťáků a trochu slávy udělat cokoliv.Tvým úkolem je zabít draka a pokusit se u toho neumřit. Zvládneš to?.");
        System.out.println("Nejdřív ale...");
        System.out.println("Vyber si svou postavu a napiš její číslo:");
        System.out.println("1. Válečník");
        System.out.println("2. Mág");
        System.out.println("3. Lukostřelec");

        int choice = scanner.nextInt();
        return choice;}

        // Definujeme několik postav
        private Postava vyberPostavu(int choice){
            Postava valecnik = new Postava("Válečník", 500, 20, 20,this);
            Postava mag = new Postava("Mág", 80, 30, 0,this);
            Postava lukostrelec = new Postava("Lukostřelec", 90, 25, 0, this);

        switch (choice) {
            case 1:
                return valecnik;
            case 2:
                return mag;
            case 3:
                return lukostrelec;
            default:
                System.out.println("Vybrala se ti defaultní postava válečník.");
                return valecnik;
        }
     }
}

