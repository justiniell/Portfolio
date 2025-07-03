package cz.vse.adventura.logika;


import java.util.Random;
/**
 *  Třída Nestvura - třída představující nestvůru v adventuře.
 *
 *
 *@author     Daniel Justa
 *@version    1.0
 */
public class Nestvura {
    private int hp;
    private int ap;
    private String name;
    private Random rand = new Random();;
    private HerniPlan plan;

    public Nestvura(String name, int hp, int ap, HerniPlan plan) {
        this.name = name;
        this.hp = hp;
        this.ap = ap;
        this.plan = plan;
    }

    /**
     * vrací životy nestvury
     * @return  int - hp
     */
    public int getHp() {

        return hp;
    }
    /**
     * vrací životy sílu
     * @return  int - ap
     */
    public int getAp() {

        return ap;
    }

    /**
     * vrací název nestvůry
     * @return  String - name
     */
    public String getName() {

        return name;
    }

    /**
     * snižuje životy nestvury
     * @param damage typu int
     */
    public void snizHp(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
            Prostor aktualniProstor = plan.getAktualniProstor();
            aktualniProstor.odeberNestvuru();
            plan.vybranaPostava.addGold(dropGold());
        }
    }

    /**
     * vrací kolik nestvura zpusobi poškozeni
     * @return  int
     */
    public int utok() {
        if(this == null){return 0;}
        return rand.nextInt(ap) + (ap);
    }

    /**
     * vraci počet ziskanych zlaťáků za zabiti nestvury
     * @return  int
     */
    public int dropGold() {
        int golds = rand.nextInt(50) + 1;
        System.out.println("Za zabití jsi získal " + golds +" zlaťáků");
        return golds;
    }

}

