package smpt42.nl.printmanager.control.internet;

import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by Jeroen on 2014-12-19.
 */
public class SetTaskCompleted extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        JSONObject jsonObjectContainer = JSONfunctions.getJSONfromURL("http://moridrin.com/android/PrintManager/setTaskCompleted.php?task_order=" + params[0] + "&task_type='" + params[1] + "'&scan_id=" + params[2]);
        return null;
    }
}
