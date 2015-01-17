package smpt42.nl.printmanager.control.internet;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import smpt42.nl.printmanager.control.JSONfunctions;
import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.Scan;

/**
 *
 * Created by Jeroen on 2014-12-19.
 */
public class GetScans extends AsyncTask<String, Void, ArrayList<Scan>> {

    @Override
    protected ArrayList<Scan> doInBackground(String... params) {
        ArrayList<Scan> scans = new ArrayList<>();
        JSONObject jsonObjectContainer = JSONfunctions.getJSONfromURL("http://moridrin.com/android/PrintManager/getPrints.php");
        try {
            JSONArray jsonObjects = jsonObjectContainer.getJSONArray("Scans");
            for (int i = 0 ; i < jsonObjects.length(); i++) {
                JSONObject jsonObject = jsonObjects.getJSONObject(i);
                int companyID = Integer.parseInt((String) jsonObject.get("COMPANY_ID"));
                String companyName = (String) jsonObject.get("COMPANY_NAME");
                String street = (String) jsonObject.get("STREET");
                String city = (String) jsonObject.get("CITY");
                String phone = (String) jsonObject.get("PHONE");
                int scanID = Integer.parseInt((String) jsonObject.get("SCAN_ID"));
                String scanName = (String) jsonObject.get("SCAN_NAME");
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date scanDate = format.parse((String) jsonObject.get("SCAN_DATE"));
                Date printDate = format.parse((String) jsonObject.get("PRINT_DATE"));
                String previewURL = (String) jsonObject.get("PREVIEW");
                String barcode = (String) jsonObject.get("BARCODE");

                scans.add(new Scan(scanID, new Company(companyID, companyName, street, city, phone), scanName, scanDate, printDate, barcode, previewURL));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return scans;
    }
}
