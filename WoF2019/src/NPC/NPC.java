/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NPC;

import Hra.IPrikaz;
import Hra.Prikaz;
import Hrac.Hrac;

/**
 *
 * @author drzik2
 */
public class NPC implements IPrikaz{

    private String meno;  
    private String privitanie;
    
    public NPC(String meno, String privitanie) {
        this.meno = meno;
        this.privitanie = privitanie;
    }

    public String getMeno() {
        return meno;
    }
    
    @Override
    public boolean pouzi(Prikaz prikaz, Hrac hrac) {
        if (prikaz.getNazov().equals("hovor")) {
            String nazovNPC = prikaz.getParameter();
            NPC npc = hrac.getAktualnaMiestnost().getNPC(nazovNPC);
            if (npc == null) {
                System.out.println("Takeho nikoho tu nevidim.");
                return true;
            }
            hrac.setAktualneNPC(npc);
            npc.vypisPrivitanie();
            npc.vypisPrikazyNPC();
            return true;
        }
        
        
        return false;
    }
 
    @Override
    public boolean jePrikaz(String nazov) {
        return nazov.equals("hovor");               
    }

    @Override
    public void vypisPrikazy() {
        System.out.println("hovor");
    }
    
    public void vypisPrivitanie() {
        System.out.println(this.privitanie);
    }
    
    public void vypisPrikazyNPC() {
        System.out.println("nehovor");
    }
    
    public boolean jePrikazNPC(String nazov) {
        return nazov.equals("nehovor");
    }
    
    
    public boolean pouziPrikazNPC(Prikaz prikaz, Hrac hrac) {
        switch(prikaz.getNazov()) {
            case "nehovor": 
                hrac.setAktualneNPC(null);
                return true;
        }
        return false;
    }
}
