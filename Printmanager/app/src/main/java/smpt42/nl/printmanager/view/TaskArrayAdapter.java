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
import java.util.Date;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.CashManager;
import smpt42.nl.printmanager.control.SharedPref;
import smpt42.nl.printmanager.model.Scan;
import smpt42.nl.printmanager.model.Task;

/**
 * Created by Jeroen on 2014-12-19.
 */
public class TaskArrayAdapter extends ArrayAdapter<Task> {
    private final Context context;
    private final ArrayList<Task> itemsArrayList;

    public TaskArrayAdapter(Context context, ArrayList<Task> itemsArrayList) {
        super(context, R.layout.task_item, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.task_item, parent, false);

        final Task task = itemsArrayList.get(position);

        TextView tbTaskType = (TextView) rowView.findViewById(R.id.tbTaskType);
        tbTaskType.setText(task.getTaskType());
        Date dateCompleted = task.getDateCompleted();
        if (dateCompleted != null) {
            TextView tbDateCompleted = (TextView) rowView.findViewById(R.id.tbDateCompleted);
            tbDateCompleted.setText(dateCompleted.toString());
        }
        return rowView;
    }
}
