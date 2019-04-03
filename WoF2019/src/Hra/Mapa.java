package Hra;


import Dvere.Dvere;
import Dvere.IDvere;
import Dvere.ISICDvere;
import Dvere.ZamykatelneDvere;
import Itemy.Item;
import Itemy.ISIC;
import Itemy.Kluc;
import Itemy.PortalGun;
import Itemy.Sekera;
import NPC.NPC;
import NPC.Predavac;
import java.util.HashMap;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author marek13
 */
public class Mapa {
    private HashMap<String, Miestnost> miestnosti;
    
    public Mapa() {
        this.miestnosti = new HashMap<>();
        this.vytvorMiestnosti();
    }
    
    private void vytvorMiestnosti() {
        // vytvorenie miestnosti
        Miestnost terasa = new Miestnost("terasa", "terasa - jeden z hlavnych vstupov na fakultu");
        this.miestnosti.put(terasa.getNazov(), terasa);
        Miestnost aula = new Miestnost("aula", "aula nwm na co sluzi");
        this.miestnosti.put(aula.getNazov(), aula);
        Miestnost bufet = new Miestnost("bufet", "ham ham");
        this.miestnosti.put(bufet.getNazov(), bufet);
        Miestnost labak = new Miestnost("labak", "pocitacove laboratorium");
        this.miestnosti.put(labak.getNazov(), labak);
        Miestnost kancelaria = new Miestnost("kancelaria", "kancelaria spravcu pocitacoveho laboratoria");
        this.miestnosti.put(kancelaria.getNazov(), kancelaria);
        
        Miestnost chodba = new Miestnost("chodba", "v rohu stoji nejaky divny typek");
        this.miestnosti.put(chodba.getNazov(), chodba);
        Miestnost wc = new Miestnost("wc", "to bolo len tak tak");
        this.miestnosti.put(wc.getNazov(), wc);
        Miestnost chill = new Miestnost("chill zone", "tu sa pekne rozloz");
        this.miestnosti.put(chill.getNazov(), chill);
        Miestnost atomKryt = new Miestnost("atomovy kryt", "tento kryt vydrzi aj najsilnejsiu slabu prehanku");
        this.miestnosti.put(atomKryt.getNazov(), atomKryt);
        Miestnost kumbal = new Miestnost("kumbal", "len nejake stare kramy");
        this.miestnosti.put(kumbal.getNazov(), kumbal);
        Miestnost vratnica = new Miestnost("vratnica", "vitaj / ahoj");
        this.miestnosti.put(vratnica.getNazov(), vratnica);
        Miestnost schody = new Miestnost("schody", "ziskany novy uspech: 12486 schodov");
        this.miestnosti.put(schody.getNazov(), schody);
        Miestnost vytah = new Miestnost("vytah", "po 34 minutach si konecne dorazil");
        this.miestnosti.put(vytah.getNazov(), vytah);
        Miestnost atrium = new Miestnost("atrium", "nemam co dodat");
        this.miestnosti.put(atrium.getNazov(), atrium);
        Miestnost tajKomnata = new Miestnost("tajomna_komnata", "co tu asi najdem?");
        this.miestnosti.put(tajKomnata.getNazov(), tajKomnata);
        
        // inicializacia miestnosti = nastavenie vychodov
        //vratnica
        Dvere vratnicaChodba = new Dvere(vratnica, chodba, "Obyčajný rám bez dverí. Hmm, čo sa tu asi stalo ?", 0);
        vratnica.nastavVychod(vratnicaChodba);
        //chodba
        Dvere kumbalChodba = new Dvere(chodba, kumbal, "Dvere z papiera, dufajme ...", 10);
        chodba.nastavVychod(kumbalChodba);
        chodba.nastavVychod(vratnicaChodba);
        Dvere chodbaChill = new Dvere(chodba, chill, "Sklenenné dvere, vídiš cez ne miesto na relax.", 2);
        chodba.nastavVychod(chodbaChill);
        Dvere chodbaVytah = new Dvere(chodba, vytah, "Zelezné dvere, ale možno uder do správneho miesta by bol riešením.", 0);
        chodba.nastavVychod(chodbaVytah);
        Dvere chodbaSchody = new Dvere(chodba, vytah, "Obycajne dvere", 1);
        chodba.nastavVychod(chodbaSchody);
        IDvere chodbaBufet = new ISICDvere(bufet, chodba, "Nachystaj si ISIC.", 0);
        chodba.nastavVychod(chodbaBufet);
        //kumbal
        kumbal.nastavVychod(kumbalChodba);
        //vytah
        vytah.nastavVychod(chodbaVytah);
        Dvere vytahAtrium = new Dvere(vytah, atrium, "Zelezné dvere, ale možno uder do správneho miesta by bol riešením.", 0);
        vytah.nastavVychod(vytahAtrium);
        //schody
        schody.nastavVychod(chodbaSchody);
        Dvere schodyAtrium = new Dvere(schody, atrium, "Klasické drevenne dvere.", 1);
        schody.nastavVychod(schodyAtrium);
        //atrium
        atrium.nastavVychod(schodyAtrium);
        atrium.nastavVychod(vytahAtrium);
        Dvere atriumBufet = new Dvere(atrium, bufet, "Do bufetu sa len tak nedostanes.", 5);
        atrium.nastavVychod(atriumBufet);
        Dvere atriumAtomKryt = new Dvere(atrium, bufet, "Ani tank ti nepomoze.", 1000000);
        atrium.nastavVychod(atriumAtomKryt);
        //atom kryt
        atomKryt.nastavVychod(atriumAtomKryt);
        //vychod terasa
        Dvere terasaAula = new Dvere(terasa, aula, "Obycane prechodove dvere", 2);
        terasa.nastavVychod(terasaAula);
        Dvere terasaLabak = new Dvere(terasa, labak, "Dvere s olovom, prajem vela stastia", 100);
        terasa.nastavVychod(terasaLabak);
        Dvere terasaBufet= new Dvere(terasa, bufet, "Do bufetu sa len tak nedostanes", 5);
        terasa.nastavVychod(terasaBufet);
        //vychod auly
        aula.nastavVychod(terasaAula);
        //vychod bufet
        bufet.nastavVychod(terasaBufet);
        bufet.nastavVychod(atriumBufet);
        Dvere bufetWC= new Dvere(bufet, wc, "Tieto dvere neprepustia nič.", 50);
        bufet.nastavVychod(bufetWC);
        bufet.nastavVychod(chodbaBufet);
        //wc
        wc.nastavVychod(bufetWC);
        //chill
        chill.nastavVychod(chodbaChill);
        Dvere chillLabak= new Dvere(chill, labak, "Nezabudni na navleky!", 2);
        chill.nastavVychod(chillLabak);
        //vychod labak
        labak.nastavVychod(terasaLabak);
        ZamykatelneDvere labakKancelaria =
                new ZamykatelneDvere(labak, kancelaria, "kanca", "Bez kluca neprejdes.", 2);
        labak.nastavVychod(labakKancelaria);
        labak.nastavVychod(chodbaChill);
        labak.nastavVychod(chillLabak);
        // vychod kancel
        kancelaria.nastavVychod(labakKancelaria);
        // tajomna komnata
        
        // itemy
        HashMap<String, Item> itemyVratnice = new HashMap<>();
        Item kluc = new Kluc("kanca", "Kluc od kancelarie", 1);
        itemyVratnice.put(kluc.getNazov(), kluc);
        vratnica.pridajItemy(itemyVratnice);
        
        HashMap<String, Item> itemyKancelarie = new HashMap<>();
        Item isic = new ISIC("ISIC", "Isic studenta", 1, 500);
        itemyKancelarie.put(isic.getNazov(), isic);
        Item portalGun = new PortalGun("portalGun", "Zbran vytvorena na tvorbu portalov.", 0);
        itemyKancelarie.put(portalGun.getNazov(), portalGun);
        kancelaria.pridajItemy(itemyKancelarie);
        
        HashMap<String, Item> itemyBufetu = new HashMap<>();
        Item sekera = new Sekera("sekera", "Sekera urcena na sekanie.", 0, 2, 10);
        itemyBufetu.put(sekera.getNazov(), sekera);
        bufet.pridajItemy(itemyBufetu);
        
        //NPCcka
        
        NPC vratnik = new NPC("vratnik", "Neotravuj");
        vratnica.pridajNPC(vratnik);
        
        Predavac bufetarka = new Predavac("bufetarka", "Kupuj a nevypytuj sa.");
        bufet.pridajNPC(bufetarka);
        bufetarka.pridajItem(sekera);
    }
    
    public Miestnost getMiestnost(String nazov) {
        return this.miestnosti.get(nazov);
    }
}
