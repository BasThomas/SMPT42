package smpt42.nl.printmanager.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.control.internet.SetTaskCompleted;
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
        final View rowView = inflater.inflate(R.layout.task_item, parent, false);

        final Task task = itemsArrayList.get(position);

        TextView tbTaskType = (TextView) rowView.findViewById(R.id.tbTaskType);
        tbTaskType.setText(task.getTaskType());
        final Date dateCompleted = task.getDateCompleted();
        final CheckBox checkBoxCompleted = (CheckBox) rowView.findViewById(R.id.checkBoxCompleted);
        if (dateCompleted != null) {
            TextView tbDateCompleted = (TextView) rowView.findViewById(R.id.tbDateCompleted);
            tbDateCompleted.setText(dateCompleted.toString());
            checkBoxCompleted.setChecked(true);
            checkBoxCompleted.setEnabled(false);
        } else {
            checkBoxCompleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    String[] args = new String[3];
                    args[0] = String.valueOf(task.getOrderID());
                    args[1] = task.getTaskType();
                    args[2] = String.valueOf(task.getScan_id());
                    new SetTaskCompleted().execute(args);
                    checkBoxCompleted.setEnabled(false);
                    TextView tbDateCompleted = (TextView) rowView.findViewById(R.id.tbDateCompleted);
                    tbDateCompleted.setText(new Date().toString());
                }
            });
        }
        return rowView;
    }
}
