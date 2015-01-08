package smpt42.nl.printmanager.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.GetScanByCode;
import smpt42.nl.printmanager.control.PHPDatabase;
import smpt42.nl.printmanager.model.Scan;

public class ScanResultActivity extends ActionBarActivity {

    private Scan scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);
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
