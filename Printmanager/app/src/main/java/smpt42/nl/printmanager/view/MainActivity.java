package smpt42.nl.printmanager.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.internet.UpdateScanDate;


public class MainActivity extends ActionBarActivity {

    private static final int RESULT_NOT_FOUND = 8128;
    private IntentIntegrator scanIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
        new SetTaskBar(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        if (requestCode != 0) {
            IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            Intent scanResultIntent = new Intent(this, ScanResultActivity.class);
            String contents = scanningResult.getContents();
            if (contents != null) {
                new UpdateScanDate().execute(contents);
                scanResultIntent.putExtra("barcode", contents);
                scanResultIntent.putExtra("fromScanner", true);
                startActivityForResult(scanResultIntent, 0);
            }
        } else if (resultCode != RESULT_NOT_FOUND) {
            scanIntegrator.initiateScan();
        }
    }
}
