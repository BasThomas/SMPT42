package smpt42.nl.printmanager.control.internet;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.Scan;
import smpt42.nl.printmanager.model.Task;

/**
 * Created by Jeroen on 2014-12-19.
 */
public class GetTasksByScanID extends AsyncTask<Integer, Void, ArrayList<Task>> {

    @Override
    protected ArrayList<Task> doInBackground(Integer... params) {
        JSONObject jsonObjectContainer = JSONfunctions.getJSONfromURL("http://moridrin.com/android/PrintManager/getTasksByScanID.php?scan_id=" + params[0]);
        if (jsonObjectContainer != null) {
            ArrayList<Task> returner = new ArrayList<>();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                JSONArray jsonObjects = jsonObjectContainer.getJSONArray("Tasks");
                for (int i = 0; i < jsonObjects.length(); i++) {
                    JSONObject jsonObject = jsonObjects.getJSONObject(i);
                    String taskType = (String) jsonObject.get("TASK_TYPE");
                    int taskOrder = Integer.parseInt((String) jsonObject.get("TASK_ORDER"));
                    Date dateCompleted = null;
                    try {
                        dateCompleted = format.parse((String) jsonObject.get("DATE_COMPLETED"));
                    } catch (ClassCastException e) {
                        //Do nothing (unfortunately it cannot convert null to String) and if the DateCompleted is null it means it isn't completed yet...
                    }
                    returner.add(new Task(taskType, taskOrder, dateCompleted));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return returner;
        }else{
            return new ArrayList<>();
        }
    }
}
