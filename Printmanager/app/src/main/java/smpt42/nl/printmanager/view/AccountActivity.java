package smpt42.nl.printmanager.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.SetTaskBar;
import smpt42.nl.printmanager.control.SharedPref;
import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.User;

public class AccountActivity extends Activity {

    private SharedPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        SetTaskBar setTaskBar = new SetTaskBar(this);
        pref = new SharedPref(this);

        User user = pref.getUser();

        final ImageButton ImageButtonBack = (ImageButton) findViewById(R.id.backImageButton);
        ImageButtonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, OverviewActivity.class);
                startActivity(intent);
            }
        });

        final Button buttonLogout = (Button) findViewById(R.id.logoutBtn);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pref.RemoveLogin();
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        final TextView textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText(user.getName());

        final TextView textViewNameValue = (TextView) findViewById(R.id.textViewNameValue);
        textViewNameValue.setText(user.getName());

        final TextView textViewCompanyValue = (TextView) findViewById(R.id.textViewCompanyValue);
        textViewCompanyValue.setText(user.getCompany().getName());

        final TextView textViewAdresValue = (TextView) findViewById(R.id.textViewAdresValue);
        textViewAdresValue.setText(user.getCompany().getAdress());

        final TextView textViewTelValue = (TextView) findViewById(R.id.textViewTelValue);
        textViewTelValue.setText(user.getCompany().getTelephone());

        final ImageView imageView = (ImageView) findViewById(R.id.userImage);
        if (user.getImageURL() != null) {
            new AsyncTask<String, Void, Bitmap>() {

                @Override
                protected Bitmap doInBackground(String... params) {

                    try {
                        String urlString = params[0];
                        URL url = new URL(urlString);
                        InputStream in = url.openStream();
                        return BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        /* TODO log error */
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            }.execute(user.getImageURL());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
