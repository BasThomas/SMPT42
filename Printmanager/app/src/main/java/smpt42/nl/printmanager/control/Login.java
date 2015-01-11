package smpt42.nl.printmanager.control;

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
public class Login extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {
        JSONObject jsonObject = JSONfunctions.getJSONfromURL("http://moridrin.com/android/PrintManager/login.php?username=" + params[0] + "&password=" + params[1]);
        boolean success =false;
        try {
            success = (boolean) jsonObject.get("SUCCESS");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            success = false;
        }
        return success;
    }
}
