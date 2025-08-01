package cz.vse.adventura.logika;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Daniel Justa
 * @version 1.0
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody = new HashSet<>();   // obsahuje sousední místnosti
    private Set<Vec> veci = new HashSet<>();
    public Nestvura nestvury;

    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis) {
        this.nazev = nazev;
        this.popis = popis;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * projde seznam veci a porovna jestli obsahuje hledanou vec
     * @param nazevVeci
     * @return  true/false
     */
    public boolean obsahujeVec (String nazevVeci) {
        for (Vec vec: veci) {
            if (vec.getNazev().equals(nazevVeci)) {
                return true;
            }
        }
        return false;
    }
    /**
     * prida vec do obsahu, pokud v něm již neni
     * @param vec Vec
     * @return  true/false
     */
    public boolean pridejVec(Vec vec) {
        if (obsahujeVec(vec.getNazev())) {
            return false;
        }
        return veci.add(vec);
    }
    /**
     * odebere vec z obsahu, pokud lze odebrat
     * @param nazevVeci String
     * @return  true/false
     */
    public boolean odeberVec(String nazevVeci){
        Vec vec = this.getVec(nazevVeci);

        if (vec != null)
        {
            return veci.remove(vec);
        }
        return false;
    }
    /**
     * vrací věc pokud ji najde
     * @param nazevVeci String
     * @return  Vec
     */
    public Vec getVec(String nazevVeci){
        for (Vec vec: veci){
            if(nazevVeci.equals(vec.getNazev()))
                return vec;
        }
        return null;
    }

    /**
     * vrací nestvuru
     * @param nestvura Nestvura
     * @return  nestvura
     */
    public Nestvura pridejNestvuru(Nestvura nestvura){
        return nestvury = nestvura;

    }

    /**
     * odebere nestvuru
     */
    public void odeberNestvuru()
    {
        nestvury = null;
    }



    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Kde jsi: " + popis + ".\n"
                + popisVychodu() + "\n" + popisVeci() + "\n" + popisNestvury();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "Kam můžeš jít: ";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    public String popisVeci() {
        String vracenyText = "Věci co se tady válí: ";
        for (Vec veci : veci) {
            vracenyText += " " + veci.getNazev();
        }
        return vracenyText;
    }
    public String popisNestvury() {
        if (nestvury == null){return "";};
        String vracenyText = "\n"+  "Narazil jsi na nestvůru: ";

            vracenyText += "\n" + nestvury.getName() + "\n" + "Počet životů: " + nestvury.getHp() + "\n" + "Síla: " + nestvury.getAp();

        return vracenyText;
    }

    public boolean existujeNestvura(){
        if (nestvury != null) {
            return true;
        }
        return false;
    }
    public Nestvura getNestvura(){
        return nestvury;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
}
