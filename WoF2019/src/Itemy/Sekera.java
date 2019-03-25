/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Itemy;

/**
 *
 * @author drzik2
 */
public class Sekera extends Item {
    private int poskodenie;
    private int pocetPouziti;
    
    public Sekera(String nazov, String popis, int cena, int poskodenie, int pocetPouziti) {
        super(nazov, popis, cena);
        this.poskodenie  = poskodenie;
        this.pocetPouziti = pocetPouziti;
    }

    public int getPoskodenie() {
        return poskodenie;
    }

    public int getPocetPouziti() {
        return pocetPouziti;
    }
    
    //vrati true ak je sekera znicena
    public boolean pouzi() {
        return --this.pocetPouziti == 0;
    }
    
    
    
}
