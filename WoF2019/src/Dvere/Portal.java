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
 * @author Andy
 */
public class Portal implements IDvere {
    
    private Miestnost modry;
    private Miestnost oranzovy;
    private String popis;
    private Boolean isOtvorene;
    private int silaMaterialu;

    public Portal(Miestnost modry, Miestnost oranzovy, String popis) {
        this.modry = modry;
        this.oranzovy = oranzovy;
        this.popis = popis;
        this.silaMaterialu = 2147483647;
        this.isOtvorene = false;
    }
    
    public void setModry(Miestnost miestnost) {
        this.modry = miestnost;
    }
    
    public void setOranzovy(Miestnost miestnost) {
        this.oranzovy = miestnost;
    }
    
    @Override
    public boolean isOtvorene() {
        return this.isOtvorene;
    }

    @Override
    public boolean skusPrejst(Hrac hrac) {
        return true;///////
    }

    @Override
    public Miestnost dajDruhuMiestnost(Miestnost prva) {
        return this.modry == prva ? this.oranzovy : this.modry;
    }

    @Override
    public String getNazov() {
        return "portal";
    }

    @Override
    public String getPopis() {
        return this.popis;
    }

    @Override
    public int getSilaMaterialu() {
        return silaMaterialu;
    }

    @Override
    public void znizSiluMaterialu(int silaUderu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
