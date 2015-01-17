package smpt42.nl.printmanager.control.internet;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import smpt42.nl.printmanager.control.JSONfunctions;
import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.Scan;

/**
 *
 * Created by Jeroen on 2014-12-19.
 */
public class GetImage extends AsyncTask<String, Void, Drawable> {

    @Override
    protected Drawable doInBackground(String... params) {
        InputStream is = null;
        try {
            is = (InputStream) new URL(params[0]).getContent();
            return Drawable.createFromStream(is, "User");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
