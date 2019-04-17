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
public class Dvere implements IDvere {
    private Miestnost prva;
    private Miestnost druha;
    private String popis;
    private int silaMaterialu;

    public Dvere(Miestnost prva, Miestnost druha, String popis, int silaMaterialu) {
        this.prva = prva;
        this.druha = druha;
        this.popis = popis;
        this.silaMaterialu = silaMaterialu;
    }
    
    @Override
    public boolean isOtvorene() {
        return true;
    }

    @Override
    public boolean skusPrejst(Hrac hrac) {
        return isOtvorene();
    }    

    @Override
    public Miestnost dajDruhuMiestnost(Miestnost prva) {
        return this.prva == prva ? this.druha : this.prva;
    }

    @Override
    public String getNazov() {
        return "Dvere";
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
            System.out.println("GRATULUJEM!! Zbytocne si si zodral sekeru na otvorene dvere.");
        }
    }
    
    
}
