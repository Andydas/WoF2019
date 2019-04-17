/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Miestnostii;

import Hrac.Hrac;
import Itemy.SlotyVybavy;

/**
 *
 * @author drzik2
 */
public class PodmienenaMiestnost extends Miestnost{
    private String podmienka;
    
    public PodmienenaMiestnost(String nazov, String popis, String podmienka) {
        super(nazov, popis);
        this.podmienka = podmienka;
    }
    
    @Override
    public String getPodmienkuVstupu() {
        return podmienka;
    }
    
    @Override
    public boolean skusVojst(Hrac hrac) {
        if (hrac.getInventarHraca().getItemVSlote(SlotyVybavy.NAVLEKY) == null) {
            System.out.println("Na vstup do miestnosti potrebujes " + podmienka);
            return false;
        } else {
            return true;
        }
        
    }
    
}
