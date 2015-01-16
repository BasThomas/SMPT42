package smpt42.nl.printmanager.control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import smpt42.nl.printmanager.view.LoginActivity;
import smpt42.nl.printmanager.view.OverviewActivity;

/**
 * Created by Mark on 16-1-2015.
 */
public class SharedPref {

   private Activity activity;

   public SharedPref(Activity act)
   {
       activity = act;
   }

    public boolean IsLoggedIn()
    {
        SharedPreferences sharedPref = activity.getSharedPreferences("printmanager_shared_preferences", Context.MODE_PRIVATE);
        String user = sharedPref.getString("username", null);
        String pass = sharedPref.getString("password", null);

        return (user != null && pass != null);
    }

    public void AddLogin(String user, String pass)
    {
        SharedPreferences sharedPref = activity.getSharedPreferences("printmanager_shared_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", user);
        editor.putString("password", pass);
        editor.commit();
    }

    public void RemoveLogin()
    {
        SharedPreferences sharedPref = activity.getSharedPreferences("printmanager_shared_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("username");
        editor.remove("password");
        editor.commit();
    }

    private boolean IsStarred(String scanName)
    {
        SharedPreferences sharedPref = activity.getSharedPreferences("printmanager_starred_scans", Context.MODE_PRIVATE);
        String starred = sharedPref.getString(scanName, null);

        return (starred != null);
    }


}
