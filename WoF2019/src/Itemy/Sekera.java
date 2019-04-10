/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Itemy;

import Dvere.IDvere;
import Hra.IPrikaz;
import Hra.Prikaz;
import Hrac.Hrac;

/**
 *
 * @author drzik2
 */
public class Sekera extends Item implements IPrikaz{
    private int poskodenie;
    private int pocetPouziti;
    
    public Sekera(String nazov, String popis, int cena, int poskodenie, int pocetPouziti) {
        super(nazov, popis, cena, SlotyVybavy.ZBRAN);
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

    @Override
    public boolean pouzi(Prikaz prikaz, Hrac hrac) {
        //mam sekeru
        Item item = hrac.getInventar().getItem("sekera");
        if (item == null) {
            System.out.println("Hrac nema sekeru.");
            return false;
        }
        if (item instanceof Sekera) {
            Sekera sekera = (Sekera) item;
            //existuju dvere
            String nazovDveri = prikaz.getParameter();
        
            IDvere dvere = hrac.getAktualnaMiestnost().getDvere(nazovDveri);
            if (dvere == null) {
                System.out.println("Dvere sa nenasli.");
                return false;
            }
            //su rozbite dvere
            if (dvere.getSilaMaterialu() == 0) {
                System.out.println("Dvere su uz davno rozbite.");
            } else { 
                //zniz silu dveri
                System.out.println("Uspesne si zatal do dveri.");
                dvere.znizSiluMaterialu(sekera.getPoskodenie());
                //pouzi sekeru
                sekera.pouzi();
                //je sekera znicena? = vymaz ju
                if (sekera.getPocetPouziti() == 0) {
                    hrac.getInventar().vyberItem("sekera");
                }
                return true;
            }
           
        } else {
            System.out.println("Hrac ma item co sa vola sekera ale nie je sekerou: Nema sa nikdy stat.");
            return false;
        }
        return false;
    }

    @Override
    public boolean jePrikaz(String nazov) {
        return nazov.equals("sekniDvere");
    }

    @Override
    public void vypisPrikazy() {
        System.out.println("sekniDvere");
    }
    
    
    
}
