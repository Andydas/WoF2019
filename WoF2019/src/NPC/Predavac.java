/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NPC;

import Hra.Prikaz;
import Hrac.Hrac;
import Itemy.Inventar;
import Itemy.ISIC;
import Itemy.Item;

/**
 *
 * @author drzik2
 */
public class Predavac extends NPC{
    private Inventar inventar;
    
    
    public Predavac(String meno, String privitanie) {
        super(meno, privitanie);
        this.inventar = new Inventar();
    }
    
    public void pridajItem(Item item) {
        this.inventar.vlozItem(item);
    }

    @Override
    public boolean pouziPrikazNPC(Prikaz prikaz, Hrac hrac) {
        switch(prikaz.getNazov()) {
             case "ponuka": {
                this.inventar.vypisItemy();
                return true;
                }
            case "kup": {
                String nazovItemu = prikaz.getParameter();
                Item nakupovany = this.inventar.getItem(nazovItemu);
                if (nakupovany == null) {
                    System.out.println("NPC nema item.");
                    return true;
                }
                
                Item temp = hrac.getInventar().getItem("ISIC");
                if (temp instanceof ISIC) {
                    ISIC isic = (ISIC)temp;
                    int kredit = isic.getKredit();
                    if (kredit < nakupovany.getCena()) {
                        System.out.println("Nemas dost penazi.");
                        return true;
                    }
                    isic.modifikujKredit(-nakupovany.getCena());
                    hrac.getInventar().vlozItem(this.inventar.vyberItem(nazovItemu));
                    return true;
                }
                System.out.println("ISIC nebol najdeny.");
                return true;
            }
            default:
                return super.pouziPrikazNPC(prikaz, hrac);
        }
        
    }

    @Override
    public boolean jePrikazNPC(String nazov) {
        switch(nazov) {
            case "ponuka":
                return true;
            case "kup": 
                return true;
            default:
                return super.jePrikazNPC(nazov);
        }
    }

    @Override
    public void vypisPrikazyNPC() {
        System.out.println("ponuka kup");
        super.vypisPrikazyNPC(); 
    }
    
    
    
    /*
    public boolean kup(Prikaz prikaz, Hrac hrac) {
        //isic, dost penazi, ci item existuje, pridat do inventara hraca(metoda clone)   
        int cena;
        int kredit;
        Item itemPredavaca;
        //spravne zadany parameter
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Nenapisal si co chces kupit.");
            return false;
        }
        String nazovItemu = prikaz.getParameter();
        //existuje item ktory chcem kupit
        if (this.inventar.getItem(nazovItemu) != null) {
            itemPredavaca = this.inventar.getItem(nazovItemu);
            cena = itemPredavaca.getCena();
        } else {
            System.out.println("Nic take nepoznam.");
            return false;
        }
        //ma hrac isic
        if (hrac.getInventar().getItem("isic") != null) {
            Item item = hrac.getInventar().getItem("isic"); 
            if (item instanceof ISIC) {
                kredit = ((ISIC) item).getKredit();
                //ma dostatocny kredit
                if (kredit >= cena) {
                    Item vybratyItem = this.inventar.vyberItem(nazovItemu);
                    hrac.getInventar().vlozItem(vybratyItem);
                    ((ISIC) item).modifikujKredit(-cena);
                    System.out.println("Uspesne si kupil item " + vybratyItem);
                } else {
                    System.out.println("Nedostatocny kredit");
                    return false;
                }
            } 
        } else {
            System.out.println("Nemas isic ty drlo!");
            return false;
        }
        return true;

    }*/
            
}
