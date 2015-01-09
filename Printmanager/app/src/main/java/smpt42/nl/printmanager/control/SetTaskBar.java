package smpt42.nl.printmanager.control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;

import java.net.ContentHandler;

import smpt42.nl.printmanager.R;
import smpt42.nl.printmanager.view.OverviewActivity;

/**
 * Created by Mark on 9-1-2015.
 */
public class SetTaskBar extends activity {

    private Activity activity;

    public SetTaskBar(Activity act)
    {
        activity = act;
        final ImageView ImageButtonHistory = (ImageView) activity.findViewById(R.id.historyImage);
        final ImageView ImageButtonScan = (ImageView) activity.findViewById(R.id.scanImage);
        final ImageView ImageButtonAccount = (ImageView) activity.findViewById(R.id.accountImage);
        SetOnClick(ImageButtonHistory, ImageButtonScan, ImageButtonAccount);

    }

    private void SetOnClick(ImageView IVhistory, ImageView IVscan, ImageView IVaccount)
    {
        IVhistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, OverviewActivity.class);
                activity.startActivity(intent);
            }
        });

        IVscan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, OverviewActivity.class);
                activity.startActivity(intent);
            }
        });

        IVaccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, OverviewActivity.class);
                activity.startActivity(intent);
            }
        });
    }
}
