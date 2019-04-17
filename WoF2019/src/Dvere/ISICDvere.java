/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dvere;

import Miestnostii.Miestnost;
import Hrac.Hrac;

/**
 *
 * @author kajanek6
 */
public class ISICDvere implements IDvere {
    private Miestnost gula;
    private Miestnost klucka;
    private String popis;
    private int silaMaterialu;

    public ISICDvere(Miestnost gula, Miestnost klucka, String popis, int silaMaterialu) {
        this.gula = gula;
        this.klucka = klucka;
        this.popis = popis;
        this.silaMaterialu = silaMaterialu;
    }

    @Override
    public boolean isOtvorene() {
        return true;
    }

    @Override
    public boolean skusPrejst(Hrac hrac) {
        if (silaMaterialu == 0) {
            return true;
        }
        if (hrac.getAktualnaMiestnost() == gula)
            return hrac.getInventar().getItem("ISIC") != null;
        else return true;
    }
    
    @Override
    public Miestnost dajDruhuMiestnost(Miestnost prva) {
        return this.gula == prva ? this.klucka : this.gula;
    }
    
    @Override
    public String getNazov() {
        return "ISIC Dvere";
    }

    @Override
    public String getPopis() {
        return popis;
    }

    @Override
    public int getSilaMaterialu() {
        return silaMaterialu;
    }

    @Override
    public void znizSiluMaterialu(int silaUderu) {
        silaMaterialu = Math.max(0, silaMaterialu - silaUderu);
        if (silaMaterialu == 0) {
            System.out.println("Znicil si isic dvere.");
        }
    }
}
