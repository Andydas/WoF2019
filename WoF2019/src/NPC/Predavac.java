/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NPC;

import Hra.Prikaz;
import Hrac.Hrac;
import Hrac.Inventar;
import Itemy.Item;

/**
 *
 * @author drzik2
 */
public class Predavac extends NPC{
    private Inventar inventar;
    
    
    public Predavac(String meno, String privitanie) {
        super(meno, privitanie);
        this.inventar = new Inventar();
    }
    
    public void pridajItem(Item item) {
        this.inventar.vlozItem(item);
    }

    @Override
    public boolean pouziPrikazNPC(Prikaz prikaz, Hrac hrac) {
        switch(prikaz.getNazov()) {
            case "ponuka":
                return true;
            case "kup": 
                return true;
            default:
                return super.pouziPrikazNPC(prikaz, hrac);
        }
        
    }

    @Override
    public boolean jePrikazNPC(String nazov) {
        switch(nazov) {
            case "ponuka":
                return true;
            case "kup": 
                return true;
            default:
                return super.jePrikazNPC(nazov);
        }
    }

    @Override
    public void vypisPrikazyNPC() {
        System.out.println("ponuka kup");
        super.vypisPrikazyNPC(); 
    }
    
    
    
}
