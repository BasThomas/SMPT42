package smpt42.nl.printmanager.control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import smpt42.nl.printmanager.model.Scan;
import smpt42.nl.printmanager.model.enums.SORT_TYPE;

/**
 * Created by Daan on 12/19/2014.
 */
public class ScanManager {
    public static Comparator<Scan> sortByDate = new Comparator<Scan>() {
        public int compare(Scan scan1, Scan scan2) {
            return scan1.getPrintDate().compareTo(scan2.getPrintDate());
        }
    };
    public static Comparator<Scan> sortByCompany = new Comparator<Scan>() {
        public int compare(Scan scan1, Scan scan2) {
            return scan1.getCompany().toString().compareTo(scan2.getCompany().toString());
        }
    };
    public static Comparator<Scan> sortByStarred = new Comparator<Scan>() {
        public int compare(Scan scan1, Scan scan2) {
            Boolean s1 = scan1.getIsStarred();
            Boolean s2 = scan2.getIsStarred();
            return s1.compareTo(s2);
        }
    };
    ArrayList<Scan> scans;

    /**
     * Constructor
     */
    public ScanManager(ArrayList<Scan> scans) {
        this.scans = scans;
        //db = new Database();
        //try {
        //    scans = db.getScans();
        //} catch (ParseException pe) {
        //    pe.printStackTrace();
        //}
    }

    /**
     * Voeg een scan toe aan de lijst met scans
     *
     * @param scan De toe te voegen scan
     */
    public void addScan(Scan scan) {
        scans.add(scan);

    }

    /**
     * Haalt de scans op, gesorteerd op de aangegeven eigenschap
     *
     * @param sorttype De eigenschap waarop gesorteerd moet worden
     * @return De gesorteerde lijst met scans
     */
    public ArrayList<Scan> getScans(SORT_TYPE sorttype) {
        System.out.println(scans);
        if (sorttype == SORT_TYPE.DATE) {
            Collections.sort(scans, sortByDate);
        } else if (sorttype == SORT_TYPE.COMPANY) {
            Collections.sort(scans, sortByCompany);
        } else if (sorttype == SORT_TYPE.STARRED) {
            Collections.sort(scans, sortByStarred);
        } else {
            return null;
        }
        return scans;
    }

    /**
     * Zoekt naar scans waarvan de naam (deels) bestaat uit de ingevulde zoekterm.
     *
     * @param input String met de zoekterm van de gebruiker
     * @return Gevonden scans, null bij geen resultaat
     */
    public List<Scan> searchByName(String input) {
        List<Scan> result = null;
        for (Scan s : scans) {
            if (s.getName().contains(input)) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Zoekt naar de scan met de opgegeven barcode
     *
     * @param barcode String met de barcode waarop gezocht moet worden
     * @return Gevonden scan, null bij geen resultaat
     */
    public Scan searchByBarcode(String barcode) {
        Scan result = null;
        for (Scan s : scans) {
            if (s.getBarcode() == barcode) {
                // Immediately cancel after barcode has been found.
                return result;
            }
        }
        return null;
    }
}
