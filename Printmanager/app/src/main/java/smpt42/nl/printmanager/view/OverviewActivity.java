package smpt42.nl.printmanager.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.SORT_TYPE;
import smpt42.nl.printmanager.control.ScanManager;
import smpt42.nl.printmanager.control.SetTaskBar;
import smpt42.nl.printmanager.control.internet.GetScans;
import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.Scan;


public class OverviewActivity extends Activity {


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof SearchView)) {

            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(OverviewActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        setupUI(findViewById(R.id.historyParent));
        new SetTaskBar(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ArrayList<Scan> scans = null;
        try {
            scans = new GetScans().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ListView lView = (ListView) findViewById(R.id.listView);
        HistoryArrayAdapter hAdapter = new HistoryArrayAdapter(this, scans);
        lView.setAdapter(hAdapter);

        final Button btnDate = (Button) findViewById(R.id.btnDate);
        final Button btnCompany = (Button) findViewById(R.id.btnCompany);
        final Button btnStarred = (Button) findViewById(R.id.btnStarred);
        final ArrayList<Scan> finalScans = scans;
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDate.setBackground(getResources().getDrawable(R.drawable.sort_button_left));
                btnDate.setTextColor(Color.WHITE);
                btnCompany.setBackground(getResources().getDrawable(R.drawable.sort_button_centre_inactive));
                btnCompany.setTextColor(Color.RED);
                btnStarred.setBackground(getResources().getDrawable(R.drawable.sort_button_right_inactive));
                btnStarred.setTextColor(Color.RED);

                reorder(finalScans, SORT_TYPE.DATE);
            }
        });
        btnCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDate.setBackground(getResources().getDrawable(R.drawable.sort_button_left_inactive));
                btnDate.setTextColor(Color.RED);
                btnCompany.setBackground(getResources().getDrawable(R.drawable.sort_button_centre));
                btnCompany.setTextColor(Color.WHITE);
                btnStarred.setBackground(getResources().getDrawable(R.drawable.sort_button_right_inactive));
                btnStarred.setTextColor(Color.RED);

                reorder(finalScans, SORT_TYPE.COMPANY);
            }
        });
        btnStarred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDate.setBackground(getResources().getDrawable(R.drawable.sort_button_left_inactive));
                btnDate.setTextColor(Color.RED);
                btnCompany.setBackground(getResources().getDrawable(R.drawable.sort_button_centre_inactive));
                btnCompany.setTextColor(Color.RED);
                btnStarred.setBackground(getResources().getDrawable(R.drawable.sort_button_right));
                btnStarred.setTextColor(Color.WHITE);

                reorder(finalScans, SORT_TYPE.STARRED);
            }
        });
    }

    public void reorder(ArrayList<Scan> scans, SORT_TYPE type) {
        ScanManager sm = new ScanManager(scans);
        ListView lView = (ListView) findViewById(R.id.listView);
        HistoryArrayAdapter hAdapter = new HistoryArrayAdapter(this, sm.getScans(type));
        lView.setAdapter(hAdapter);

        System.out.println(sm.getScans(SORT_TYPE.DATE));
        System.out.println(sm.getScans(SORT_TYPE.COMPANY));
        System.out.println(sm.getScans(SORT_TYPE.STARRED));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

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
