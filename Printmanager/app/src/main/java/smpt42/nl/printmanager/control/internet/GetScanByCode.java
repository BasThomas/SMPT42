package smpt42.nl.printmanager.control.internet;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.Scan;

/**
 * Momentum by Wout
 * Created by Jeroen on 2014-12-19.
 */
public class GetScanByCode extends AsyncTask<String, Void, Scan> {

    @Override
    protected Scan doInBackground(String... params) {
        JSONObject jsonObject = JSONfunctions.getJSONfromURL("http://moridrin.com/android/PrintManager/getPrintByCode.php?barcode=" + params[0]);
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
            companyID = Integer.parseInt((String) jsonObject.get("COMPANY_ID"));
            companyName = (String) jsonObject.get("COMPANY_NAME");
            street = (String) jsonObject.get("STREET");
            city = (String) jsonObject.get("CITY");
            phone = (String) jsonObject.get("PHONE");
            scanID = Integer.parseInt((String) jsonObject.get("SCAN_ID"));
            scanName = (String) jsonObject.get("SCAN_NAME");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            scanDate = format.parse((String) jsonObject.get("SCAN_DATE"));
            printDate = format.parse((String) jsonObject.get("PRINT_DATE"));
            previewURL = (String) jsonObject.get("PREVIEW");

            // Return Scanobject when parsing succeeded.
            return new Scan(new Company(companyID, companyName, street, city, phone), scanName, scanDate, printDate, params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            return null;
        }
        // Return null when action failed.
        return null;
    }
}
