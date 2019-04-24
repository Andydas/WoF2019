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
public class Generika {

    public static <T extends Comparable<T>> T max (T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
    //nemusim toto:
    //public static Cislo max(Cislo a, Cislo b) {
    //   ...
    //}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cislo a = new Cislo(24);
        Cislo b = new Cislo(4);
        Cislo c = Generika.max(a, b);
        System.out.println("Cislo: "+ c.getCislo());
    }
    
}
