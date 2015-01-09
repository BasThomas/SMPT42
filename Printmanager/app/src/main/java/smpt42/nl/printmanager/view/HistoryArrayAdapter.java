package smpt42.nl.printmanager.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import smpt42.nl.printmanager.R;

import java.util.ArrayList;

/**
 * Created by Pieter on 19-12-2014.
 */
public class HistoryArrayAdapter extends ArrayAdapter<HistoryItemRow>
{
    private final Context context;
    private final ArrayList<HistoryItemRow> itemsArrayList;

    public HistoryArrayAdapter(Context context, ArrayList<HistoryItemRow> itemsArrayList)
    {
        super(context, R.layout.scan_item, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.scan_item, parent, false);

        TextView tbTitle = (TextView)rowView.findViewById(R.id.tbTitle);
        TextView tbCompany = (TextView)rowView.findViewById(R.id.tbCompany);
        tbTitle.setText(itemsArrayList.get(position).Title);
        tbCompany.setText(itemsArrayList.get(position).Company);

        return rowView;
    }
}
