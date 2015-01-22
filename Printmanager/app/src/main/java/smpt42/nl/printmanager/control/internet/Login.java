package smpt42.nl.printmanager.control.internet;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.User;

/**
 * Momentum by Wout
 * Created by Jeroen on 2014-12-19.
 */
public class Login extends AsyncTask<String, Void, User> {

    @Override
    protected User doInBackground(String... params) {
        JSONObject jsonObject = JSONfunctions.getJSONfromURL("http://moridrin.com/android/PrintManager/login.php?username=" + params[0] + "&password=" + params[1]);
        User returner = null;
        try {
            if ((boolean) jsonObject.get("SUCCESS")) {
                String surname = (String) jsonObject.get("SURNAME");
                String lastname = (String) jsonObject.get("LASTNAME");
                String email = (String) jsonObject.get("EMAIL");
                String companyName = (String) jsonObject.get("COMPANY_NAME");
                String street = (String) jsonObject.get("STREET");
                String city = (String) jsonObject.get("CITY");
                String telephone = (String) jsonObject.get("TELEPHONE");
                String preview = (String) jsonObject.get("PREVIEW");
                returner = new User(new Company(companyName, street, city, telephone), params[0], surname, lastname, email, preview);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            returner = null;
        }
        return returner;
    }
}
