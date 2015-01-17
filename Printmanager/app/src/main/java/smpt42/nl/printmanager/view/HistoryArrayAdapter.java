package smpt42.nl.printmanager.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.SharedPref;
import smpt42.nl.printmanager.model.Scan;

/**
 * Created by Pieter on 19-12-2014.
 */
public class HistoryArrayAdapter extends ArrayAdapter<Scan> {
    private final Context context;
    private final ArrayList<Scan> itemsArrayList;

    public HistoryArrayAdapter(Context context, ArrayList<Scan> itemsArrayList) {
        super(context, R.layout.scan_item, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.scan_item, parent, false);

        SharedPref pref = new SharedPref((Activity) context);

        TextView tbTitle = (TextView) rowView.findViewById(R.id.tbTitle);
        TextView tbCompany = (TextView) rowView.findViewById(R.id.tbCompany);
        TextView tbTime = (TextView) rowView.findViewById(R.id.tbTime);
        TextView tbDate = (TextView) rowView.findViewById(R.id.tbDate);
        ImageView imageStarred = (ImageView) rowView.findViewById(R.id.imageStarred);
        final ImageView imagePreview = (ImageView) rowView.findViewById(R.id.scan_image);
        if (itemsArrayList.get(position).getPreviewURL() != null) {
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
                        imagePreview.setImageBitmap(bitmap);
                    }
                }
            }.execute(itemsArrayList.get(position).getPreviewURL());
        }

        tbTitle.setText(itemsArrayList.get(position).getName());
        tbCompany.setText(itemsArrayList.get(position).getCompany().getName());
        tbDate.setText(itemsArrayList.get(position).getPrintDateOnly());
        tbTime.setText(itemsArrayList.get(position).getPrintTimeOnly());
        if (pref.IsStarred(itemsArrayList.get(position))) {
            imageStarred.setImageResource(R.drawable.history_starred);
        } else {
            imageStarred.setImageResource(R.drawable.history_starred_white);
        }
        return rowView;
    }
}
