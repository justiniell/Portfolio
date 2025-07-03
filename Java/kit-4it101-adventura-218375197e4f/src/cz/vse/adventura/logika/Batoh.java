package cz.vse.adventura.logika;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *  Třída Batoh - implementuje singleton pro správu věcí v hráčově inventáři
 *
 *@author     Daniel Justa
 *@version    1.0
 */
public class Batoh {

    private int kapacita;
    private int pocetUlozenychVeci;
    private List<Vec> veci;


    public Batoh(int kapacita) {
        this.kapacita = kapacita;
        this.pocetUlozenychVeci = 0;
        this.veci = new ArrayList<>();
    }

    /**
     * Metoda zjistujici jestli pocetUlozenychVeci je vetsi nez kapacita batohu
     * @return boolean - true/false
     */
    public boolean jePlny() {
        return pocetUlozenychVeci >= kapacita;
    }

    /**
     * Metoda zjistujici jestli pocetUlozenychVeci se rovna 0
     * @return boolean - true/false
     */
    public boolean jePrazdny() {
        return pocetUlozenychVeci == 0;
    }

    /**
     * metoda která slouží pro přidání věci z prostoru do batohu
     * @param vec typu Vec, kterou chceme přidat do batohu.
     * @return boolean - true/false a jestli je možné danou věc přidat
     */

    public boolean pridejVec(Vec vec) {
        if (jePlny()) {
            System.out.println("Batoh je plný");
            return false;
        }
        if (!vec.isPrenositelna()) {
            System.out.println("Tato věc není přenositelná");
            return false;
        }
        pocetUlozenychVeci++;
        return veci.add(vec);
    }
    /**
     * metoda která slouží pro přidání věci do batohu
     * @param nazevVeci typu String
     * @return Vec
     */
    public Vec getVeciVBatohu(String nazevVeci) {
        for (Vec vec : veci) {
            if (nazevVeci.equals(vec.getNazev()))
                return vec;
        }
        return null;
    }
    /**
     * metoda která slouží pro vypsání obsahu batohu
     * @return String
     */
    public String vypisBatoh(){
        StringBuilder obsah = new StringBuilder();
        for (Vec item :veci){
            obsah.append(item.getNazev()).append(" ");
        }
        return obsah.toString();
    }
    /**
     * metoda která slouží pro odebrání věci z batohu
     * @param vec typu Vec
     * @return boolean - true/false
     */
    public boolean odeberVec(Vec vec) {
        if (jePrazdny()) {
            System.out.println("Batoh je prázdný");
            return false;
        }
        if (veci.contains(vec)) {
            veci.remove(vec);
            pocetUlozenychVeci--;
            System.out.println("Věc " + vec.getNazev() + " byla odebrána");
            return true;
        } else {
            System.out.println("Tento předmět v batohu nemáš");
            return false;
        }
    }
    /**
     * metoda která slouží pro získání počtu uložených věcí
     * @return pocetUlozenychVeci typu int
     */
    public int getPocetUlozenychVeci() {
        return pocetUlozenychVeci;
    }
}

