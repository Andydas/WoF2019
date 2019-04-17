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
public class ZamykatelneDvere implements IDvere {
    private Miestnost prva;
    private Miestnost druha;
    private String nazovKluca;
    private boolean isOtvorene;
    private String popis;
    private int silaMaterialu;

    public ZamykatelneDvere(Miestnost prva, Miestnost druha, String nazovKluca, String popis, int silaMaterialu) {
        this.prva = prva;
        this.druha = druha;
        this.nazovKluca = nazovKluca;
        this.isOtvorene = false;
        this.popis = popis;
        this.silaMaterialu = silaMaterialu;
    }
    
    @Override
    public boolean isOtvorene() {
        return getSilaMaterialu() == 0 || this.isOtvorene;
    }

    @Override
    public boolean skusPrejst(Hrac hrac) {
       return isOtvorene();
    }

    @Override
    public Miestnost dajDruhuMiestnost(Miestnost prva) {
        return this.prva == prva ? this.druha : this.prva;
    }
    
    public void pouziKluc(Hrac hrac) {
        if (hrac.getInventar().getItem(nazovKluca) != null) {
            if (this.isOtvorene) {
                System.out.println("Dvere sa zamkli.");
            } else {
                System.out.println("Dvere sa odomkli.");
            }            
            this.isOtvorene = !this.isOtvorene;
        }
        else {
            System.out.println("Nebol najdeny spravny kluc.");
        }            
    }

    @Override
    public String getNazov() {
        return "Zamykatelne Dvere";
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
            System.out.println("Znicil si zamykatelne dvere.");
        }
    }
}
