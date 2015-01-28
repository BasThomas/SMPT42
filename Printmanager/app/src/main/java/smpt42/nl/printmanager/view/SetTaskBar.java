package smpt42.nl.printmanager.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import smpt42.nl.printmanager.R;

/**
 * Created by Mark on 9-1-2015.
 */
public class SetTaskBar {

    private Activity activity;

    public SetTaskBar(Activity act) {
        if (act == null) {
            return;
        }

        activity = act;
        final ImageView imageButtonHistory = (ImageView) activity.findViewById(R.id.historyImage);
        final ImageView imageButtonScan = (ImageView) activity.findViewById(R.id.scanImage);
        final ImageView imageButtonAccount = (ImageView) activity.findViewById(R.id.accountImage);

        final TextView textViewHistory = (TextView) activity.findViewById(R.id.historyText);
        final TextView textViewScan = (TextView) activity.findViewById(R.id.scanText);
        final TextView textViewAccount = (TextView) activity.findViewById(R.id.accountText);

        SetOnClickImageView(imageButtonHistory, imageButtonScan, imageButtonAccount);
        SetOnClickTextView(textViewHistory, textViewScan, textViewAccount);

    }

    private void SetOnClickImageView(ImageView IVhistory, ImageView IVscan, ImageView IVaccount) {
        IVhistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, OverviewActivity.class);
                activity.startActivity(intent);
                activity.setResult(Activity.RESULT_CANCELED);
                activity.finish();
            }
        });


        if (activity.getIntent().getBooleanExtra("fromScanner", false)) {
            IVscan.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    activity.setResult(Activity.RESULT_CANCELED);
                    activity.finish();
                }
            });
        } else {
            IVscan.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.setResult(Activity.RESULT_CANCELED);
                    activity.finish();
                }
            });
        }

        IVaccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, AccountActivity.class);
                activity.startActivity(intent);
                activity.setResult(Activity.RESULT_CANCELED);
                activity.finish();
            }
        });
    }

    private void SetOnClickTextView(TextView TVhistory, TextView TVscan, TextView TVaccount) {
        TVhistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, OverviewActivity.class);
                activity.startActivity(intent);
                activity.setResult(Activity.RESULT_CANCELED);
                activity.finish();
            }
        });

        if (activity.getIntent().getBooleanExtra("fromScanner", false)) {
            TVscan.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    activity.setResult(Activity.RESULT_CANCELED);
                    activity.finish();
                }
            });
        } else {
            TVscan.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(activity, MainActivity.class);
                    activity.startActivity(intent);
                    activity.setResult(Activity.RESULT_CANCELED);
                    activity.finish();
                }
            });
        }

        TVaccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(activity, AccountActivity.class);
                activity.startActivity(intent);
                activity.setResult(Activity.RESULT_CANCELED);
                activity.finish();
            }
        });
    }
}
