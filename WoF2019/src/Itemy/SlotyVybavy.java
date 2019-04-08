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
public enum SlotyVybavy {
    NIC(""),
    HLAVA("Hlava"),
    RAMENA("Ramena"),
    TRUP("Trup"),
    PLAST("Plast"),
    RUKY("Ruky"),
    NOHY("Nohy"),
    TOPANKY("Topanky"),
    NAVLEKY("Navleky"),
    NAUSNICE("Nausnice"),
    NAHRDELNIK("Nahrdelnik"),
    PRSTEN("Prsten"),
    NARAMOK("Naramok"),
    ZBRAN("Zbran"),
    SLOT_MAX("");
    
    private String nazovSlotu;
    
    SlotyVybavy (String nazov) {
    this.nazovSlotu = nazov;
    }

    public String getNazovSlotu() {
        return nazovSlotu;
    }


}
