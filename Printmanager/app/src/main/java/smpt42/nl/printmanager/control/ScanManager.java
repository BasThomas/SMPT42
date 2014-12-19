package smpt42.nl.printmanager.control;

import java.util.*;

import smpt42.nl.printmanager.model.Scan;

/**
 * Created by Daan on 12/19/2014.
 */
public class ScanManager {
    List<Scan> scans;

    public void addScan(Scan scan) {
        scans.add(scan);
    }

    public List<Scan> getScans(SORT_TYPE sorttype) {
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

    public List<Scan> searchByName(String input) {
        List<Scan> result = null;
        for (Scan s : scans) {
            if (s.getName().contains(input)) {
                result.add(s);
            }
        }
        return result;
    }

    public Scan searchByBarcode(String barcode) {
        Scan result = null;
        for (Scan s : scans) {
            if (s.getBarcode() == barcode) {
                result = s;
            }
        }
        return result;
    }

    public static Comparator<Scan> sortByDate = new Comparator<Scan>() {
        public int compare(Scan scan1, Scan scan2) {
            return scan1.getScanDate().compareTo(scan2.getScanDate());
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
}
