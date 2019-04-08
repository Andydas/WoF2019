/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Itemy;

import Dvere.IDvere;
import Dvere.ZamykatelneDvere;
import Hra.IPrikaz;
import Hra.Prikaz;
import Hrac.Hrac;

/**
 *
 * @author kajanek6
 */
public class Kluc extends Item implements IPrikaz{
    

    public Kluc(String nazov, String popis, int cena) {
        super (nazov, popis, cena, SlotyVybavy.NIC);
    }

    @Override
    public boolean pouzi(Prikaz prikaz, Hrac hrac) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Nenapisal si meno dveri.");
            return false;
        }
        
        String nazovDveri = prikaz.getParameter();
        
        IDvere dvere = hrac.getAktualnaMiestnost().getDvere(nazovDveri);
        if (dvere == null) {
            System.out.println("Dvere sa nenasli.");
            return false;
        }
        
        if (dvere instanceof ZamykatelneDvere) {
            ((ZamykatelneDvere) dvere).pouziKluc(hrac);
        } else {
            System.out.println("Dvere nie su zamykatelne.");
            return false;
        }
        return true;
    }

    @Override
    public boolean jePrikaz(String nazov) {
        return nazov.equals("pouziKluc");
    }

    @Override
    public void vypisPrikazy() {
        System.out.println("pouziKluc");
    }

    
    
}
