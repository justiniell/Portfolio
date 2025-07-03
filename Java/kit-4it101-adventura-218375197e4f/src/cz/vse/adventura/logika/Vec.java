package cz.vse.adventura.logika;
/**
 *  Třída Vec - třída představující věc v adventuře.
 *
 *
 *@author     Daniel Justa
 *@version    1.0
 */
public class Vec {
    private String nazev;
    private boolean prenositelna;

    public Vec(String nazev, boolean prenositelna) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
    }

    /**
     * vrací název
     * @return  String
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * nastavi název
     *@param nazev String
     */
    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    /**
     * jestli existuje prenositelnost věci
     * @return  true/false
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }

}
