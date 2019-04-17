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
    
    public void oblecItem(Item item){
        if (item == null) {
            System.out.println("Item:OblecItem:Preco toto robis.");
            return;
        }
        vyberItem(item.getNazov());
        int slot = item.getSlot().ordinal();
        if (sloty[slot] != null) {
            Item oblecenie = sloty[slot];
            this.oblecItem(oblecenie);
        }
        sloty[slot] = item;
        System.out.println("Uspesne si obliekol " + item.getNazov());
    }
    
    @Override
    public void vypisItemy(){
        for (int i = SlotyVybavy.NIC.ordinal(); 
                i < SlotyVybavy.SLOT_MAX.ordinal(); i++) {
            Item item = sloty[i];
            if (item != null) {
                System.out.println(item.getSlot().getNazovSlotu()
                        + ": " + item.getNazov());
            }
        }
        super.vypisItemy();
    }
    
    public Item getItemVSlote (SlotyVybavy slot) {
        return this.sloty[slot.ordinal()];
    }
    
}
