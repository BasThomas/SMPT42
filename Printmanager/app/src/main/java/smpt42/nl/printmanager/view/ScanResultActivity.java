package smpt42.nl.printmanager.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.CashManager;
import smpt42.nl.printmanager.control.SharedPref;
import smpt42.nl.printmanager.control.internet.GetScanByCode;
import smpt42.nl.printmanager.model.Scan;
import smpt42.nl.printmanager.model.Task;


public class ScanResultActivity extends Activity {

    private static final int RESULT_NOT_FOUND = 8128;
    private Scan scan;
    private SharedPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        if (getIntent().getBooleanExtra("fromScanner", false)) {
            ImageView imageView = (ImageView) findViewById(R.id.scanImage);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.qr_red));
            imageView = (ImageView) findViewById(R.id.historyImage);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.history));
            TextView textView = (TextView) findViewById(R.id.scanText);
            textView.setTextColor(Color.RED);
            textView = (TextView) findViewById(R.id.historyText);
            textView.setTextColor(Color.BLACK);
        }

        final ImageButton ImageButtonBack = (ImageButton) findViewById(R.id.backImageButton);
        ImageButtonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final ImageView imageViewStar = (ImageView) findViewById(R.id.ImageViewStar);
        final RelativeLayout RelativeLayoutBack = (RelativeLayout) findViewById(R.id.RelativeButton);
        pref = new SharedPref(this);

        RelativeLayoutBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ScanResultActivity.this, OverviewActivity.class);
                startActivity(intent);
            }
        });

        imageViewStar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pref.IsStarred(scan)) {
                    imageViewStar.setImageResource(R.drawable.details_star);
                    pref.RemoveStarred(scan);
                } else {
                    imageViewStar.setImageResource(R.drawable.details_starred);
                    pref.AddStarred(scan);
                }

            }
        });

        new SetTaskBar(this);

        GetScanByCode getScanByCode = new GetScanByCode();
        try {
            scan = getScanByCode.execute(getIntent().getExtras().getString("barcode")).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (scan != null) {
            final ImageView scanPreview = (ImageView) findViewById(R.id.scanPreview);
            if (scan.getPreviewURL() != null) {
                Bitmap bitmapFromMemCache = CashManager.getBitmapFromMemCache(scan.getPreviewURL());
                if (bitmapFromMemCache != null) {
                    scanPreview.setImageBitmap(bitmapFromMemCache);
                } else {
                    new AsyncTask<String, Void, Bitmap>() {

                        @Override
                        protected Bitmap doInBackground(String... params) {

                            try {
                                String urlString = params[0];
                                URL url = new URL(urlString);
                                URLConnection urlConnection = url.openConnection();
                                urlConnection.setConnectTimeout(500);
                                urlConnection.setReadTimeout(500);
                                InputStream in = urlConnection.getInputStream();
                                return BitmapFactory.decodeStream(in);
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                System.err.println(e.getMessage());
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Bitmap bitmap) {
                            if (bitmap != null) {
                                scanPreview.setImageBitmap(bitmap);
                                CashManager.addBitmapToMemoryCache(scan.getPreviewURL(), bitmap);
                            }
                        }
                    }.execute(scan.getPreviewURL());
                }
                if (pref.IsStarred(scan)) {
                    imageViewStar.setImageResource(R.drawable.details_starred);
                }
            }
            updateLabels();
            final ListView listViewTasks = (ListView) findViewById(R.id.listViewTasks);
            listViewTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ArrayList<Task> tasks = scan.getTasks();
                    TaskArrayAdapter taskArrayAdapter = new TaskArrayAdapter(ScanResultActivity.this, tasks);
                    listViewTasks.setAdapter(taskArrayAdapter);
                }
            });
            setResult(RESULT_OK);
        } else {
            setResult(RESULT_NOT_FOUND);
            finish();
        }
    }

    private void updateLabels() {
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
        textViewScannedDate.setText(this.scan.getScanDate().toString());
        TextView textViewPrintedDate = (TextView) findViewById(R.id.textViewPrintedDateValue);
        textViewPrintedDate.setText(this.scan.getPrintDate().toString());
        ListView listViewTasks = (ListView) findViewById(R.id.listViewTasks);
        ArrayList<Task> tasks = scan.getTasks();
        TaskArrayAdapter taskArrayAdapter = new TaskArrayAdapter(this, tasks);
        listViewTasks.setAdapter(taskArrayAdapter);
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
