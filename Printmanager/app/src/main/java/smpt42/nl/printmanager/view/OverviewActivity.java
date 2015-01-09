package smpt42.nl.printmanager.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

import smpt42.nl.printmanager.R;


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


        ArrayList<HistoryItemRow> exampleRows = new ArrayList<HistoryItemRow>();
        exampleRows.add(new HistoryItemRow(1, "Olifant", "Oce"));
        exampleRows.add(new HistoryItemRow(2, "Poster", "Fontys Eindhoven"));
        exampleRows.add(new HistoryItemRow(3, "De Nachtwacht", "Van Gogh"));
        exampleRows.add(new HistoryItemRow(4, "Nog een item", "Geen Inspiratie inc."));
        exampleRows.add(new HistoryItemRow(5, "McKroket", "McDonalds"));
        exampleRows.add(new HistoryItemRow(6, "Sandstorm", "Darude"));
        ListView lView = (ListView)findViewById(R.id.listView);
        HistoryArrayAdapter hAdapter = new HistoryArrayAdapter(this, exampleRows);
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
            }
        });
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
