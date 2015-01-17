package smpt42.nl.printmanager.control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.User;
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

    public boolean IsStarred(String scanName)
    {
        SharedPreferences sharedPref = activity.getSharedPreferences("printmanager_starred_scans", Context.MODE_PRIVATE);
        String starred = sharedPref.getString(scanName, null);

        return (starred != null);
    }

    public void AddStarred(String scanName)
    {
        SharedPreferences sharedPref = activity.getSharedPreferences("printmanager_starred_scans", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(scanName, scanName);
        editor.commit();
    }

    public void RemoveStarred(String scanName)
    {
        SharedPreferences sharedPref = activity.getSharedPreferences("printmanager_starred_scans", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(scanName);
        editor.commit();
    }

    public void setUser(User user) {
        SharedPreferences sharedPref = activity.getSharedPreferences("printmanager_user", Context.MODE_PRIVATE);
        sharedPref.edit().putString("SURNAME", user.getSurname()).apply();
        sharedPref.edit().putString("LASTNAME", user.getLastname()).apply();
        sharedPref.edit().putString("EMAIL", user.getEmail()).apply();
        sharedPref.edit().putString("COMPANY_NAME", user.getCompany().getName()).apply();
        sharedPref.edit().putString("STREET", user.getCompany().getStreet()).apply();
        sharedPref.edit().putString("CITY", user.getCompany().getCity()).apply();
        sharedPref.edit().putString("TELEPHONE", user.getCompany().getTelephone()).apply();
        sharedPref.edit().putString("IMAGE", user.getImageURL()).apply();
    }

    public User getUser() {
        SharedPreferences sharedPref = activity.getSharedPreferences("printmanager_user", Context.MODE_PRIVATE);
        String username = sharedPref.getString("USERNAME", null);
        String surname = sharedPref.getString("SURNAME", null);
        String lastname = sharedPref.getString("LASTNAME", null);
        String email = sharedPref.getString("EMAIL", null);
        String companyName = sharedPref.getString("COMPANY_NAME", null);
        String street = sharedPref.getString("STREET", null);
        String city = sharedPref.getString("CITY", null);
        String telephone = sharedPref.getString("TELEPHONE", null);
        String preview = sharedPref.getString("IMAGE", null);
        return new User(new Company(companyName, street, city, telephone), username, surname, lastname, email, preview);
    }
}
