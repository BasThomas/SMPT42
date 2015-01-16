package smpt42.nl.printmanager.control;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import smpt42.nl.printmanager.view.LoginActivity;
import smpt42.nl.printmanager.view.OverviewActivity;

/**
 * Created by Mark on 16-1-2015.
 */
public class SharedPref {

    public boolean isLoggedIn()
    {
        SharedPreferences sharedPref = getSharedPreferences("printmanager_shared_preferences", Context.MODE_PRIVATE);
        String user = sharedPref.getString("username", null);
        String pass = sharedPref.getString("password", null);

        if(user != null && pass != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
