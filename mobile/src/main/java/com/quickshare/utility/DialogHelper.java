package com.quickshare.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.quickshare.R;

public class DialogHelper {

    public static void showAboutMessage(Context context) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context).setPositiveButton(context.getResources().getString(R.string.dismiss_dialog),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                }).create();
        alertDialog.setTitle(R.string.app_name);
        alertDialog.setMessage(context.getResources().getString(R.string.copyright_message) + "\n\n" + context.getResources().getString(R.string.contact_mail));
        alertDialog.show();
    }

    public static void launchPlayStore() {

    }
}
