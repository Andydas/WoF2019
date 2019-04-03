/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hrac;

import Itemy.Inventar;
import Itemy.Item;
import Itemy.SlotyVybavy;

/**
 *
 * @author drzik2
 */
public class InventarHraca extends Inventar{
    private Item[] sloty;

    public InventarHraca() {
        this.sloty = new Item[SlotyVybavy.SLOT_MAX.ordinal()];
    }
    
    
}
