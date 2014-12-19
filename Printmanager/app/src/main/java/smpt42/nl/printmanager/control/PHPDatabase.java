package smpt42.nl.printmanager.control;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.Scan;

/**
 * Momentum by Wout
 * Created by Jeroen on 2014-12-19.
 */
public abstract class PHPDatabase {

    public static Scan getScanByCode(String code) {
        JSONObject jsonObject = JSONfunctions.getJSONfromURL("http://moridrin.com/android/PrintManager/getPrintByCode.php?barcode=1234567890");
        int companyID = 0;
        String companyName = null;
        String street = null;
        String city = null;
        String phone = null;
        int scanID = 0;
        String scanName = null;
        Date scanDate = null;
        Date printDate = null;
        String previewURL = null;
        try {
            companyID = (int) jsonObject.get("COMPANY_ID");
            companyName = (String) jsonObject.get("COMPANY_NAME");
            street = (String) jsonObject.get("STREET");
            city = (String) jsonObject.get("CITY");
            phone = (String) jsonObject.get("PHONE");
            scanID = (int) jsonObject.get("SCAN_ID");
            scanName = (String) jsonObject.get("SCAN_NAME");
            scanDate = (Date) jsonObject.get("SCAN_DATE");
            printDate = (Date) jsonObject.get("PRINT_DATE");
            previewURL = (String) jsonObject.get("PREVIEW");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Scan(new Company(companyID, companyName, street, city, phone), scanName, scanDate, printDate);
    }
}
