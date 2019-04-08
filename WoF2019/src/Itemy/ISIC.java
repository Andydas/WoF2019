/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Itemy;

/**
 *
 * @author kajanek6
 */
public class ISIC extends Item {
    private int kredit;

    public ISIC(String nazov, String popis, int cena, int kredit) {
        super (nazov, popis, cena, SlotyVybavy.NIC);
        this.kredit = kredit;
    }


    public int getKredit() {
        return kredit;
    }
    
    public boolean modifikujKredit(int suma) {
        if (kredit + suma < 0) {
            return false;
        }
        kredit += suma;
        return true;
    }
}
