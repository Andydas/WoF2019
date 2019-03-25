/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dvere;

import Hra.Miestnost;
import Hrac.Hrac;

/**
 *
 * @author kajanek6
 */
public interface IDvere {
    boolean isOtvorene();
    boolean skusPrejst(Hrac hrac);
    Miestnost dajDruhuMiestnost(Miestnost prva);
    String getNazov();
    String getPopis();
    int getSilaMaterialu();
    void znizSiluMaterialu(int silaUderu);
}
