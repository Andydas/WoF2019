 package Hra;


import Dvere.IDvere;
import Dvere.ZamykatelneDvere;
import Hrac.Hrac;
import Itemy.Item;
import Itemy.PortalGun;
import Itemy.Sekera;
import Itemy.SlotyVybavy;
import NPC.NPC;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Random;

/**
 * Trieda Hra je hlavna trieda aplikacie "World of FRI".
 * "World of FRI" je velmi jednoducha textova hra - adventura. 
 * Hrac sa moze prechadzat po niektorych priestoroch - miestnostiach fakulty. 
 * To je v tejto verzii vsetko. Hru treba skutocne zancne rozsirit,
 * aby bola zaujimava.
 * 
 * Ak chcete hrat "World of FRI", vytvorte instanciu triedy Hra (hra) 
 * a poslite jej spravu hraj.
 * 
 * Hra vytvori a inicializuje vsetky potebne objekty:
 * vytvori vsetky miestnosti, vytvori parser a zacne hru. Hra tiez vyhodnocuje
 * a vykonava prikazy, ktore vrati parser.
 * 
 * @author  Michael Kolling, David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
*/
 
public class Hra  {
    private Parser parser;
    private Mapa mapa;
    private Hrac hrac;
    //private Miestnost aktualnaMiestnost;
    
    /**
     * Vytvori a inicializuje hru.
     */
    public Hra() {
        this.mapa = new Mapa();
        Miestnost miestnost = mapa.getMiestnost("terasa");
        try {
            InputStream input = new BufferedInputStream(new FileInputStream("playerData.save"));
            //byte[] vstup = null;
            String vstupnyNazovMiestnosti = "";
            int returnVal = input.read();
            while (returnVal > 0) {
                // System.out.println(returnVal + "");
                vstupnyNazovMiestnosti += (char) returnVal;
                returnVal = input.read();
            }
            //input.read(vstup);
            Miestnost nacitanaMiestnost = mapa.getMiestnost(vstupnyNazovMiestnosti);
            if (nacitanaMiestnost != null) {
                miestnost = nacitanaMiestnost;
            } else {
                System.out.println("Miestnost v subore nebola najdena.");
            }
            input.close();
        } catch(IOException i) {
            System.out.println("Load sa nepodaril.");
        }
        this.hrac = new Hrac(this, miestnost);
        this.parser = new Parser(this.hrac);
    }

    /**
     * Vytvori mapu hry - miestnosti.
     */

    /**
     *  Hlavna metoda hry.
     *  Cyklicky opakuje kroky hry, kym hrac hru neukonci.
     */
    public void hraj() {            
        this.vypisPrivitanie();

        // Vstupny bod hlavneho cyklu.
        // Opakovane nacitava prikazy hraca
        // vykonava ich kym hrac nezada prikaz na ukoncenie hry.
                
        boolean jeKoniec;
        
        do {
            Prikaz prikaz = this.parser.nacitajPrikaz();
            jeKoniec = this.vykonajPrikaz(prikaz);
        } while (!jeKoniec);
        try {
            OutputStream output = new BufferedOutputStream(new FileOutputStream("playerData.save"));
            output.write(this.hrac.getAktualnaMiestnost().getNazov().getBytes());
            output.write(0);
            output.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
        System.out.println("Maj sa fajn!");
    }

    /**
     * Vypise privitanie hraca do terminaloveho okna.
     */
    private void vypisPrivitanie() {
        System.out.println();
        System.out.println("Vitaj v hre World of FRI!");
        System.out.println("World of FRI je nova, neuveritelne nudna adventura.");
        System.out.println("Zadaj 'pomoc' ak potrebujes pomoc.");
        System.out.println();
        System.out.println("Teraz si v miestnosti " + this.hrac.getAktualnaMiestnost().getPopis());
        System.out.print("Vychody: ");
        this.hrac.getAktualnaMiestnost().vypisVychody();
        System.out.println();
    }

    /**
     * Prevezne prikaz a vykona ho.
     * 
     * @param prikaz prikaz, ktory ma byt vykonany.
     * @return true ak prikaz ukonci hru, inak vrati false.
     */
    private boolean vykonajPrikaz(Prikaz prikaz) {
        boolean jeKoniec = false;

        if (prikaz.jeNeznamy()) {
            System.out.println("Nerozumiem, co mas na mysli...");
            return false;
        }

        String nazovPrikazu = prikaz.getNazov();
        NPC aktualneNPC = this.hrac.getAktualneNPC();
        if (aktualneNPC != null) {
            aktualneNPC.pouziPrikazNPC(prikaz, hrac);
            return false;
        }
        
        switch (nazovPrikazu) {
            case "pomoc":
                this.vypisNapovedu();
                return false;
            case "chod":
                this.chodDoMiestnosti(prikaz);
                return false;
            case "ukonci":
                return this.ukonciHru(prikaz);
            case "teleport":
                this.teleport(prikaz);
                return false;
            case "pozri":
                this.pozri(prikaz);
                return false;
            case "zober":
                this.zober(prikaz);
                return false;
            case "vypisInventar":
                this.vypisInventar(prikaz);
                return false;
            case "vytvorPortal":
                this.vytvorPortal(prikaz);
                return false;
            case "pozriDvere":
                this.pozriDvere(prikaz);
                return false;
            case "oblec":
                this.oblecItem(prikaz);
                return false;
            default:
                break;
        }
        
        Miestnost miestnost = this.hrac.getAktualnaMiestnost();
        
        if (miestnost instanceof IPrikaz) {
            boolean success = ((IPrikaz) miestnost).pouzi(prikaz, this.hrac);
            if (success) {
                return false;
            }
        }
        Collection<IDvere> dvereMiestnosti = miestnost.getVsetkyDvere();
        for (IDvere dvere : dvereMiestnosti) {
            if (dvere instanceof IPrikaz) {
                boolean success = ((IPrikaz) dvere).pouzi(prikaz, this.hrac);
                if (success) {
                    return false;
                }
            }
        }
        Collection<Item> itemyHraca = this.hrac.getInventar().getVsetkyItemy();
        for (Item item : itemyHraca) {
            if (item instanceof IPrikaz) {
                boolean success = ((IPrikaz) item).pouzi(prikaz, this.hrac);
                    if (success) {
                        return false;
                    }
            }
        }
        Collection<NPC> npcMiestnosti = miestnost.getVsetkyNPC();
        for (NPC npc : npcMiestnosti) {
            if (npc instanceof IPrikaz) {
                boolean success = ((IPrikaz) npc).pouzi(prikaz, this.hrac);
                if (success) {
                        return false;
                    }
            }
        }
        return false;
        
    }

    // implementacie prikazov:

    /**
     * Vypise text pomocnika do terminaloveho okna.
     * Text obsahuje zoznam moznych prikazov.
     */
    private void vypisNapovedu() {
        System.out.println("Zabludil si. Si sam. Tulas sa po fakulte.");
        System.out.println();
        System.out.println("Mozes pouzit tieto prikazy:");
        NazvyPrikazov.vypisPrikazy();
        Miestnost miestnost = this.hrac.getAktualnaMiestnost();
        if (miestnost instanceof IPrikaz) {
            ((IPrikaz) miestnost).vypisPrikazy();
        }
        Collection<IDvere> dvereMiestnosti = miestnost.getVsetkyDvere();
        for (IDvere dvere : dvereMiestnosti) {
            if (dvere instanceof IPrikaz) {
                ((IPrikaz) dvere).vypisPrikazy();
            }
        }
        Collection<Item> itemyHraca = this.hrac.getInventar().getVsetkyItemy();
        for (Item item : itemyHraca) {
            if (item instanceof IPrikaz) {
                ((IPrikaz) item).vypisPrikazy();
            }
        }
        Collection<NPC> npcMiestnosti = miestnost.getVsetkyNPC();
        for (NPC npc : npcMiestnosti) {
            if (npc instanceof IPrikaz) {
                ((IPrikaz) npc).vypisPrikazy();
            }
        }
        
        //if (this.hrac.getInventar().getItem("portalGun") != null) {
        //    System.out.print("vytvorPortal");
        //}
        //System.out.println("");
    }

    /** 
     * Vykona pokus o prechod do miestnosti urcenej danym smerom.
     * Ak je tym smerom vychod, hrac prejde do novej miestnosti.
     * Inak sa vypise chybova sprava do terminaloveho okna.
     */
    private void chodDoMiestnosti(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Chod kam?");
            return;
        }

        String nazovMiestnosti = prikaz.getParameter();

        IDvere dvere = this.hrac.getAktualnaMiestnost().getDvere(nazovMiestnosti);
        if (dvere == null) {
            System.out.println("Dvere sa nenasli.");
            return;
        }
        
        if (!dvere.skusPrejst(hrac)) {
            System.out.println("Nepodarilo sa prejst cez dvere.");
            return;
        }
        // Pokus o opustenie aktualnej miestnosti danym vychodom.
        Miestnost novaMiestnost = dvere.dajDruhuMiestnost(hrac.getAktualnaMiestnost());

        if (novaMiestnost == null) {
            System.out.println("Tam nie je vychod!");
        } else {
            this.hrac.chodDoMiestnosti(novaMiestnost);
        }
    }

    /** 
     * Ukonci hru.
     * Skotroluje cely prikaz a zisti, ci je naozaj koniec hry.
     * Prikaz ukoncenia nema parameter.
     * 
     * @return true, if this command quits the game, false otherwise.
     * @return true, ak prikaz konci hru, inak false.
     */
    private boolean ukonciHru(Prikaz prikaz) {
        if (prikaz.maParameter()) {
            System.out.println("Ukonci, co?");
            return false;
        } else {
            return true;
        }
    }
    
    private void teleport(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("TP kam?");
            return;
        }

        String nazovMiestnosti = prikaz.getParameter();
        this.hrac.teleportujDoMiestnosti(nazovMiestnosti);
    }

    public Mapa getMapa() {
        return mapa;
    }

    private void pozri(Prikaz prikaz) {
        this.hrac.getAktualnaMiestnost().vypisItemy();
        this.hrac.getAktualnaMiestnost().vypisNPC();
    }
    
    private void zober(Prikaz prikaz) {
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Nenapisal si nazov itemu.");
            return;
        }

        Item item = this.hrac.getAktualnaMiestnost().zoberItem(prikaz.getParameter());
        if (item == null) {
            System.out.println("Item sa nenasiel.");
            return;
        }
        
        this.hrac.getInventar().vlozItem(item);
    }

    private void vypisInventar(Prikaz prikaz) {
        this.hrac.getInventar().vypisItemy();
    }
    
    
    
    private void vytvorPortal(Prikaz prikaz){
        
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Nenapisal si akej farby ma byt portal.");
            return;
        }
        
        if (this.hrac.getInventar().getItem("portalGun") != null) {
            String farbaPortalu = prikaz.getParameter();
            this.hrac.vytvorPortal(farbaPortalu);
        } else {
            System.out.println("PortalGun je iba rozpravka pre male deti.");
        } 
    }
    
    /* to dole je blbost
    private void sekniDvere(Prikaz prikaz){
        if (!prikaz.maParameter()) {
            // ak prikaz nema parameter - druhe slovo - nevedno kam ist
            System.out.println("Nenapisal si ktore dvere.");
            return;
        }
        
        Sekera sekera = (Sekera) this.hrac.getInventar().getItem("sekera");
        
        if (sekera != null) {
            String nazovDveri = prikaz.getParameter();
            IDvere dvere = this.hrac.getAktualnaMiestnost().getDvere(nazovDveri);
            dvere.znizSiluMaterialu(sekera.getPoskodenie());
            
            if (dvere == null) {
                System.out.println("Dvere sa nenasli.");
                return;
            }
        } else {
            System.out.println("Chces si dolamat ruky? Najskor potrebujes sekeru.");
    }
    */
    
    public String pozriDvere(Prikaz prikaz) {
        String nazovDveri = prikaz.getParameter();
        IDvere dvere = this.hrac.getAktualnaMiestnost().getDvere(nazovDveri);
        return dvere.getPopis();
    }

    private void oblecItem(Prikaz prikaz) {
        String nazovItemu = prikaz.getParameter();
        Item item = this.hrac.getInventar().getItem(nazovItemu);
        if (item == null) {
            System.out.println("Item nebol najdeny");
            return;
        }
        if (item.getSlot() == SlotyVybavy.NIC) {
            System.out.println("Item sa neda obliect");
            return;
        }
        this.hrac.getInventarHraca().oblecItem(item);
    }

        
}
