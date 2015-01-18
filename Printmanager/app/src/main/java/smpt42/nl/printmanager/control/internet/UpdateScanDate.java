package smpt42.nl.printmanager.control.internet;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Momentum by Wout
 * Created by Jeroen on 2014-12-19.
 */
public class UpdateScanDate extends AsyncTask<String, Void, Boolean> {

    @Override
    protected Boolean doInBackground(String... params) {
        JSONObject jsonObject = JSONfunctions.getJSONfromURL("http://moridrin.com/android/PrintManager/updateLastScanned.php?barcode=" + params[0]);
        try {
            Boolean success = (Boolean) jsonObject.get("SUCCESS");
            return success;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            return false;
        }
        // Return false when action failed.
        return false;
    }
}
