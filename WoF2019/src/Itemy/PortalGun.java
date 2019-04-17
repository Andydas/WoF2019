/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Itemy;

import Dvere.Portal;
import Miestnostii.Miestnost;
import Hrac.Hrac;

/**
 *
 * @author Andy
 */
public class PortalGun extends Item {

    
    private Miestnost modry;
    private Miestnost oranzovy;
    private Portal prechod;
    
    private static PortalGun portalGun;
    public static PortalGun getPortalGun() {
        return PortalGun.portalGun;
    } 
    
    public PortalGun(String nazov, String popis, int cena) {
        super(nazov, popis, 0, SlotyVybavy.NIC);
        this.modry = null;
        this.oranzovy = null;
        PortalGun.portalGun = this;
    }
    
    
        
    public void vytvorPortal(Miestnost miestnost, String farba){
        switch (farba) {
            case "modry":
                if (this.modry == null) {
                    this.modry = miestnost;
                    if (this.oranzovy != null) {
                        this.prechod = new Portal(this.modry, this.oranzovy, "Okruhly portal medzi miestnostami.");
                        this.modry.nastavVychod(this.prechod);
                        this.oranzovy.nastavVychod(this.prechod);
                    }
                } else {
                    if (this.oranzovy != null) {
                        this.modry.odstranVychod(this.prechod);
                        this.oranzovy.odstranVychod(this.prechod);
                        this.modry = miestnost;
                        this.prechod.setModry(this.modry);
                        this.modry.nastavVychod(this.prechod);
                        this.oranzovy.nastavVychod(this.prechod);
                        
                    }
                }
                return;
            case "oranzovy":
                if (this.oranzovy == null) {
                    this.oranzovy = miestnost;
                    if (this.modry != null) {
                        this.prechod = new Portal(this.modry, this.oranzovy, "Okruhly portal medzi miestnostami.");
                        this.oranzovy.nastavVychod(this.prechod);
                        this.modry.nastavVychod(this.prechod);
                    }  
                } else {
                    if(this.modry != null) {
                        this.modry.odstranVychod(this.prechod);
                        this.oranzovy.odstranVychod(this.prechod);
                        this.oranzovy = miestnost;
                        this.prechod.setOranzovy(this.oranzovy);
                        this.oranzovy.nastavVychod(this.prechod);
                        this.modry.nastavVychod(this.prechod);
                    }
                }
                return;
            default:
                System.out.println("Neexistujuca farba.");
                return;
        }
        
    }
    
    
    
}
