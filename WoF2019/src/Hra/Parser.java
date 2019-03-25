package Hra;

import Dvere.IDvere;
import Hrac.Hrac;
import Itemy.Item;
import java.util.Collection;
import java.util.Scanner;

/**
 * Trieda Parser cita vstup zadany hracom do terminaloveho okna a pokusi sa
 * interpretovat ho ako prikaz hry. Kazda sprava dajPrikaz sposobi, ze parser
 * precita jeden riadok z terminaloveho okna a vyberie z neho prve dve slova.
 * Tie dve slova pouzije ako parametre v sprave new triede Prikaz.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */
public class Parser {
    private NazvyPrikazov prikazy;  // odkaz na pripustne nazvy prikazov
    private Scanner citac;         // zdroj vstupov od hraca
    private Hrac hrac;

    /**
     * Vytvori citac na citanie vstupov z terminaloveho okna.
     */
    public Parser(Hrac hrac) {
        this.hrac = hrac;
        this.prikazy = new NazvyPrikazov();
        this.citac = new Scanner(System.in);
    }

    /**
     * @return prikaz zadany hracom
     */
    public Prikaz nacitajPrikaz() {
        System.out.print("> ");     // vyzva pre hraca na zadanie prikazu

        String vstupnyRiadok = this.citac.nextLine();

        String prikaz = null;
        String parameter = null;

        // najde prve dve slova v riadku 
        Scanner tokenizer = new Scanner(vstupnyRiadok);
        if (tokenizer.hasNext()) {
            prikaz = tokenizer.next();      // prve slovo
            if (tokenizer.hasNext()) {
                parameter = tokenizer.next();      // druhe slovo
                // vsimnite si, ze zbytok textu sa ignoruje
            }
        }
        
        //carymary z cvika 
        boolean najdeny = this.prikazy.jePrikaz(prikaz);
        if (!najdeny) {
            Miestnost miestnost = this.hrac.getAktualnaMiestnost();
            if (miestnost instanceof IPrikaz) {
                najdeny = ((IPrikaz) miestnost).jePrikaz(prikaz);
            }
            if (!najdeny) {
                Collection<IDvere> dvereMiestnosti = miestnost.getVsetkyDvere();
                for (IDvere dvere : dvereMiestnosti) {
                    if (dvere instanceof IPrikaz) {
                        najdeny = ((IPrikaz) dvere).jePrikaz(prikaz);
                        if (najdeny) {
                            break;
                        }
                    }
                }   
            }
            if (!najdeny) {
                Collection<Item> itemyHraca = this.hrac.getInventar().getVsetkyItemy();
                for (Item item : itemyHraca) {
                    if (item instanceof IPrikaz) {
                        najdeny = ((IPrikaz) item).jePrikaz(prikaz);
                        if (najdeny) {
                            break;
                        }
                    }
                }
            }
        } 
        
        // kontrola platnosti prikazu   
        if (this.prikazy.jePrikaz(prikaz)) {
            // vytvori platny prikaz
            return new Prikaz(prikaz, parameter);
        } else {
            // vytvori neplatny - "neznamy" prikaz
            return new Prikaz(null, parameter); 
        }
        
        
        
        
        
        
        
    }
}
