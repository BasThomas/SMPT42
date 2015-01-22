package smpt42.nl.printmanager.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.model.enums.SORT_TYPE;
import smpt42.nl.printmanager.control.ScanManager;
import smpt42.nl.printmanager.control.internet.GetScans;
import smpt42.nl.printmanager.model.Scan;


public class OverviewActivity extends Activity {

    ArrayList<Scan> scans = null;
    EditText editTextFind;
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            filter(editTextFind.getText().toString());

        }
    };

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        this.finish();
        this.startActivity(getIntent());
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
        final Intent scanResultIntent = new Intent(this, ScanResultActivity.class);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contents = scans.get(position).getBarcode();
                if (contents != null) {
                    scanResultIntent.putExtra("barcode", contents);
                    startActivityForResult(scanResultIntent, 0);
                }
            }
        });

        editTextFind = (EditText) findViewById(R.id.editTextFind);
        /*editTextFind.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                filter(v.getText().toString());
                return true;
            }
        });*/
        editTextFind.addTextChangedListener(textWatcher);

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

                editTextFind.setText("");
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

                editTextFind.setText("");
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

                editTextFind.setText("");
                reorder(finalScans, SORT_TYPE.STARRED);
            }
        });
        reorder(finalScans, SORT_TYPE.DATE);
    }

    private void filter(String s) {
        String lowerS = s.toLowerCase();
        ScanManager sm = new ScanManager(scans);
        ListView lView = (ListView) findViewById(R.id.listView);
        HistoryArrayAdapter hAdapter = new HistoryArrayAdapter(this, sm.getScans(lowerS));
        lView.setAdapter(hAdapter);
    }

    public void reorder(ArrayList<Scan> scans, SORT_TYPE type) {
        ScanManager sm = new ScanManager(scans);
        ListView lView = (ListView) findViewById(R.id.listView);
        HistoryArrayAdapter hAdapter = new HistoryArrayAdapter(this, sm.getScans(type));
        lView.setAdapter(hAdapter);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK){
            finish();
        }
    }
}
