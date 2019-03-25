package Hrac;


import Itemy.Item;
import java.util.Collection;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kajanek6
 */
public class Inventar {
    private HashMap<String, Item> itemy;

    public Inventar() {
        this.itemy = new HashMap<>();
    }
    
    public void vlozItem(Item item) {
        this.itemy.put(item.getNazov(), item);
    }
    
    public Item vyberItem(String nazov) {
        return this.itemy.remove(nazov);
    }
    
    public void vypisItemy() {
        System.out.println("Itemy:");
        for (Item item : itemy.values()) {
            System.out.println(item.getNazov() + " :" + item.getPopis());
        }
    }
    
    public Item getItem(String nazov) {
        return this.itemy.get(nazov);
    }
    
    public Collection<Item> getVsetkyItemy() {
        return this.itemy.values();
    }
}
