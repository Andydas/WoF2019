package Hrac;

import Itemy.Inventar;
import Hra.Hra;
import Hra.Miestnost;
import Itemy.PortalGun;
import NPC.NPC;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kajanek6
 */
public class Hrac {
    private Hra hra;
    private Miestnost aktualnaMiestnost;
    private Inventar inventar;
    private PortalGun portalGun;
    private NPC aktualneNPC;
    
    
    public Hrac(Hra hra, Miestnost aktualnaMiestnost) {
        this.hra = hra;
        this.aktualnaMiestnost = aktualnaMiestnost;
        this.inventar = new Inventar();
        portalGun = PortalGun.getPortalGun();
        this.aktualneNPC = null;
    }
    
    public Miestnost getAktualnaMiestnost() {
        return aktualnaMiestnost;
    }
    
    public void chodDoMiestnosti(Miestnost aktualnaMiestnost) {
        this.aktualnaMiestnost = aktualnaMiestnost;
        System.out.println("Teraz si v miestnosti " + this.aktualnaMiestnost.getNazov() + ": " + this.aktualnaMiestnost.getPopis());
        System.out.print("Vychody: ");
        this.aktualnaMiestnost.vypisVychody();
        System.out.println();
    }
    
    public boolean teleportujDoMiestnosti(String nazov) {
        Miestnost miestnost = hra.getMapa().getMiestnost(nazov);
        if (miestnost == null) return false;
        this.aktualnaMiestnost = miestnost;
        System.out.println("Teraz si v miestnosti " + this.aktualnaMiestnost.getNazov() + ": " + this.aktualnaMiestnost.getPopis());
        System.out.print("Vychody: ");
        this.aktualnaMiestnost.vypisVychody();
        System.out.println();
        return true;
    }

    public Inventar getInventar() {
        return inventar;
    }   
    
    public void vytvorPortal(String farba) {
        this.portalGun.vytvorPortal(this.getAktualnaMiestnost(), farba);
    }
    
    public NPC getAktualneNPC() {
        return aktualneNPC;
    } 
    
    public void setAktualneNPC (NPC aktualneNPC) {
        this.aktualneNPC = aktualneNPC;
    }
}
