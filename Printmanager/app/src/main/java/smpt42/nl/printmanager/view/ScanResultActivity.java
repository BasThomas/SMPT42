package smpt42.nl.printmanager.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.GetScanByCode;
import smpt42.nl.printmanager.control.SetTaskBar;
import smpt42.nl.printmanager.model.Scan;

public class ScanResultActivity extends Activity {

    private Scan scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);



        final ImageButton ImageButtonBack = (ImageButton) findViewById(R.id.backImageButton);
        final ImageView imageViewStar = (ImageView) findViewById(R.id.ImageViewStar);



        ImageButtonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScanResultActivity.this, OverviewActivity.class);
                startActivity(intent);
            }
        });

        imageViewStar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isStarred(scan.getName()))
                {
                    imageViewStar.setImageResource(R.drawable.details_star);
                    removeStarred(scan.getName());
                }
                else
                {
                    imageViewStar.setImageResource(R.drawable.details_starred);
                    setStarred(scan.getName());
                }

            }
        });

        SetTaskBar setTaskBar = new SetTaskBar(this);

        GetScanByCode getScanByCode = new GetScanByCode();
        try {
            scan = getScanByCode.execute(getIntent().getExtras().getString("barcode")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        updateLabels();


    }

    private boolean isStarred(String name)
    {
        SharedPreferences sharedPref = getSharedPreferences("printmanager_starred_scans", Context.MODE_PRIVATE);
        Set<String> Set = new HashSet<>();
        Set = sharedPref.getStringSet("starred_scans", null);

        for (String temp : Set)
        {
            if(temp.equals(name))
            {
                return true;
            }
        }
        return false;
    }

    private void setStarred(String name)
    {
        SharedPreferences sharedPref = getSharedPreferences("printmanager_starred_scans", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> set = new HashSet<>();
        set = sharedPref.getStringSet("starred_scans", null);
        set.add(name);
        editor.putStringSet("starred_scans", set);
        editor.commit();
    }

    private void removeStarred(String name)
    {
        SharedPreferences sharedPref = getSharedPreferences("printmanager_starred_scans", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Set<String> set = new HashSet<>();
        set = sharedPref.getStringSet("starred_scans", null);

        set.remove(name);

        editor.putStringSet("starred_scans", set);
        editor.commit();
    }

    private void updateLabels(){
        TextView textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText(this.scan.getName());
        TextView textViewName2 = (TextView) findViewById(R.id.textViewNameValue);
        textViewName2.setText(this.scan.getName());
        TextView textViewCompany = (TextView) findViewById(R.id.textViewCompanyValue);
        textViewCompany.setText(this.scan.getCompany().getName());
        TextView textViewAdres = (TextView) findViewById(R.id.textViewAdresValue);
        textViewAdres.setText(this.scan.getCompany().getStreet() + ", " + this.scan.getCompany().getCity());
        TextView textViewTel = (TextView) findViewById(R.id.textViewTelValue);
        textViewTel.setText(this.scan.getCompany().getTelephone());
        TextView textViewScannedDate = (TextView) findViewById(R.id.textViewScannedDateValue);
        textViewScannedDate.setText(Calendar.getInstance().getTime().toString());
        TextView textViewPrintedDate = (TextView) findViewById(R.id.textViewPrintedDateValue);
        textViewPrintedDate.setText(this.scan.getPrintDate().toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scan_result, menu);
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
