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

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.SORT_TYPE;
import smpt42.nl.printmanager.control.ScanManager;
import smpt42.nl.printmanager.control.SetTaskBar;
import smpt42.nl.printmanager.model.Company;
import smpt42.nl.printmanager.model.HistoryItemRow;
import smpt42.nl.printmanager.model.Scan;


public class OverviewActivity extends Activity {


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof SearchView)) {

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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        setupUI(findViewById(R.id.historyParent));
        SetTaskBar setTaskBar = new SetTaskBar(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Company company = new Company("Fontys", "Rachelsmolen 1", "Eindhoven", "0681789369");
        Company company2 = new Company("test", "test", "test", "test");

        final ArrayList<Scan> scans = new ArrayList<Scan>();
        scans.add(new Scan(company2, "Nachtwacht", new Date(), new Date(), "barcode1"));
        scans.add(new Scan(company, "Marcel K", new Date(), new Date(), "barcode2"));
        scans.add(new Scan(company2, "Darude", new Date(), new Date(), "barcode3"));
        scans.add(new Scan(company, "Ribs Factory", new Date(), new Date(), "barcode4"));
        scans.add(new Scan(company2, "DDW", new Date(), new Date(), "barcode5"));
        scans.add(new Scan(company, "Pepper's Ghost", new Date(), new Date(), "barcode6"));
        ListView lView = (ListView)findViewById(R.id.listView);
        HistoryArrayAdapter hAdapter = new HistoryArrayAdapter(this, scans);
        lView.setAdapter(hAdapter);

        final Button btnDate = (Button)findViewById(R.id.btnDate);
        final Button btnCompany = (Button)findViewById(R.id.btnCompany);
        final Button btnStarred = (Button)findViewById(R.id.btnStarred);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDate.setBackground(getResources().getDrawable(R.drawable.sort_button_left));
                btnDate.setTextColor(Color.WHITE);
                btnCompany.setBackground(getResources().getDrawable(R.drawable.sort_button_centre_inactive));
                btnCompany.setTextColor(Color.RED);
                btnStarred.setBackground(getResources().getDrawable(R.drawable.sort_button_right_inactive));
                btnStarred.setTextColor(Color.RED);

                reorder(scans, SORT_TYPE.DATE);
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

                reorder(scans, SORT_TYPE.COMPANY);
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

                reorder(scans, SORT_TYPE.STARRED);
            }
        });
    }

    public void reorder(ArrayList<Scan> scans, SORT_TYPE type)
    {
        ScanManager sm = new ScanManager(scans);
        ListView lView = (ListView)findViewById(R.id.listView);
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
