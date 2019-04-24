/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generika;

/**
 *
 * @author drzik2
 */
public class Cislo implements Comparable<Cislo> {

    private int cislo;

    public Cislo(int cislo) {
        this.cislo = cislo;
    }

    public int getCislo() {
        return cislo;
    }

    public void setCislo(int cislo) {
        this.cislo = cislo;
    }

    @Override
    public int compareTo(Cislo other) {
        if (this.cislo == other.cislo) {
            return 0;
        } else if (this.cislo > other.cislo) {
            return 1;
        } else {
            return -1;
        }
    }

}
