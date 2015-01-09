package smpt42.nl.printmanager.control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.ContentHandler;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.view.AccountActivity;
import smpt42.nl.printmanager.view.MainActivity;
import smpt42.nl.printmanager.view.OverviewActivity;
import smpt42.nl.printmanager.view.ScanResultActivity;

/**
 * Created by Mark on 9-1-2015.
 */
public class SetTaskBar  {

    private Activity activity;

    public SetTaskBar(Activity act)
    {
        if(act == null)
        {
            return;
        }

        activity = act;
        final ImageView imageButtonHistory = (ImageView) activity.findViewById(R.id.historyImage);
        final ImageView imageButtonScan = (ImageView) activity.findViewById(R.id.scanImage);
        final ImageView imageButtonAccount = (ImageView) activity.findViewById(R.id.accountImage);

        final TextView textViewHistory = (TextView) activity.findViewById(R.id.accountText);
        final TextView textViewScan = (TextView) activity.findViewById(R.id.scanText);
        final TextView textViewAccount = (TextView) activity.findViewById(R.id.accountText);

        SetOnClickImageView(imageButtonHistory, imageButtonScan, imageButtonAccount);
        SetOnClickTextView(textViewHistory, textViewScan, textViewAccount);

    }

    private void SetOnClickImageView(ImageView IVhistory, ImageView IVscan, ImageView IVaccount)
    {
        IVhistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, OverviewActivity.class);
                activity.startActivity(intent);
            }
        });

        IVscan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }
        });

        IVaccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, AccountActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    private void SetOnClickTextView(TextView TVhistory, TextView TVscan, TextView TVaccount)
    {
        TVhistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, OverviewActivity.class);
                activity.startActivity(intent);
            }
        });

        TVscan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }
        });

        TVaccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, AccountActivity.class);
                activity.startActivity(intent);
            }
        });
    }
}
