package cz.vse.adventura.logika;

import java.util.Random;
/**
 *  Třída Postava - třída představující postavy v adventuře.
 *
 *
 *@author     Daniel Justa
 *@version    1.0
 */


public class Postava {
    public String name;
    public int hp;
    public int ap;
    public int gold;
    private Random rand = new Random();
    private Hra hra;



    public Postava(String name, int hp, int ap, int gold, Hra hra) {
        this.name = name;
        this.hp = hp;
        this.ap = ap;
        this.gold = gold;
        this.hra = hra;
    }

    /**
     * vrací jméno postavy
     * @return  String
     */
    public String getName() {
        return name;
    }

    /**
     * vrací počet životu postavy
     * @return  int
     */
    public int getHp() {
        if (this.hp <=0)
        {
            this.hp = 0;
        }
        return hp;
    }

    /**
     * vrací sílu postavy
     * @return  int
     */
    public int getAp() {
        return ap;
    }

    /**
     * vrací počet zlaťáků postavy
     * @return  int
     */
    public int getGold() {
        return gold;
    }

    /**
     * pridava zlaťaky postavě
     * @param amount typu int
     */
    public void addGold(int amount) {
        this.gold += amount;
    }

    /**
     * odebira zlaťaky postavě
     * @param amount typu int
     * @return false/true
     */
    public boolean spendGold(int amount) {
        if (amount > gold) {
            return false;
        }
        this.gold -= amount;
        return true;
    }

    /**
     * přidava životy postavě
     * @param hodnota typu int
     */
    public void zvysHp(int hodnota){
        this.hp += hodnota;}

    /**
     * pridava silu postavě
     * @param hodnota typu int
     */
    public void zvysAp(int hodnota){
        this.ap += hodnota;}

    /**
     * snizuje životy postavě
     * @param hodnota typu int
     */
    public void snizHp(int hodnota){
        this.hp -= hodnota;}

    /**
     * vraci hodnotu poškozeni zpusobene nestvure
     * @return int
     */
    public int utok() {
        return rand.nextInt(ap) + (ap);
    }

    /**
     * zjistuje jestli ma postava meně nebo rovno životu než nula
     */
    public void zivy (){
        if (this.hp <=0){
            System.out.println("Umřel jsi. Tak třeba příště.");
            hra.setKonecHry(true);
        }
    }
    /**
     * počet zlaťaku
     * @return int
     */
    public int gold (){
        return this.gold;
    }
}
