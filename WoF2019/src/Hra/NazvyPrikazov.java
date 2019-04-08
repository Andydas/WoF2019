package Hra;

/**
 * Trieda NazvyPrikazov udrzuje zoznam nazvov platnych prikazov hry. 
 * Za ulohu ma rozpoznavat platne prikazy.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * @author  lokalizacia: Lubomir Sadlon, Jan Janech
 * @version 2012.02.21
 */

public class NazvyPrikazov {
    // konstantne pole nazvov prikazov
    private static final String[] PLATNE_PRIKAZY = {
        "chod", "ukonci", "pomoc", "teleport", "pozri", "zober", "vypisInventar","vytvorPortal", "pozriDvere", "oblec"
    };

    /**
     * Kontroluje, ci nazov v parametri je platny prikaz.
     *  
     * @return true ak je parameter platny prikaz,
     * false inak.
     */
    public boolean jePrikaz(String nazov) {
        for (int i = 0; i < NazvyPrikazov.PLATNE_PRIKAZY.length; i++) {
            if (NazvyPrikazov.PLATNE_PRIKAZY[i].equals(nazov)) {
                return true;
            }
        }
        // ak algoritmus dosiahne tento bod, parameter nie je platny prikaz
        return false;
    }
    
    public static void vypisPrikazy() {
        for (String string : PLATNE_PRIKAZY) {
            if (!string.equals("vytvorPortal")) {
            System.out.print(string + " ");
            }
        }
    }
}
